import 'package:flutter/material.dart';

/// Representa um processo a ser alocado na memória.
///
/// Cada processo possui um identificador único ([name]), um [size] em KB
/// que determina quantas células de 1 KB ocupará, e uma [color] para
/// distingui-lo visualmente na grid.
class Process {
  final String name;
  final int size;
  final Color color;

  Process({required this.name, required this.size, required this.color});
}

/// Algoritmo de alocação a ser utilizado pelo [MemoryManager].
enum FitType { firstFit, worstFit }

/// Nó da lista encadeada (linear) que representa um bloco contíguo de memória.
///
/// Cada bloco pode estar livre ([isFree] == true) ou ocupado por um [process].
/// Os campos [start] e [size] definem o intervalo contíguo de endereços
/// (em unidades de 1 KB) que este bloco representa dentro do espaço total
/// de 256 KB.
///
/// O encadeamento é linear: o último bloco da lista tem [next] igual a `null`.
class MemoryBlock {
  int start;
  int size;
  bool isFree;
  Process? process;
  MemoryBlock? next;

  MemoryBlock({
    required this.start,
    required this.size,
    this.isFree = true,
    this.process,
    this.next,
  });
}

/// Gerencia a memória simulada de 256 KB utilizando uma lista encadeada linear.
///
/// A memória total é de 256 KB, representada visualmente como uma grid 16x16
/// (cada célula = 1 KB). Internamente, blocos livres e ocupados são organizados
/// em uma lista encadeada linear ordenada por endereço inicial. O primeiro
/// bloco ([head]) sempre começa no endereço 0, e o último bloco tem
/// [next] igual a `null`.
///
/// Oferece dois algoritmos de alocação:
///   - [FitType.firstFit]: aloca no primeiro bloco livre com espaço suficiente.
///   - [FitType.worstFit]: aloca no maior bloco livre disponível.
///
/// Também provê desalocação de processos e coalescência (junção de blocos
/// livres adjacentes) para combater a fragmentação externa.
class MemoryManager {
  static const int totalSize = 256;
  MemoryBlock head;
  final List<Process> _processes = [];

  MemoryManager() : head = MemoryBlock(start: 0, size: totalSize);

  /// Retorna uma lista somente-leitura dos processos atualmente alocados.
  List<Process> get processes => List.unmodifiable(_processes);

  /// Tenta alocar [process] na memória usando o algoritmo [fitType].
  ///
  /// Percorre a lista encadeada linear a partir de [head] em busca de um bloco
  /// livre que satisfaça o critério de seleção:
  ///
  ///   - **First-Fit**: seleciona o primeiro bloco livre com [size] >= [process.size].
  ///   - **Worst-Fit**: seleciona o maior bloco livre com [size] >= [process.size].
  ///
  /// Se o bloco encontrado for exatamente do tamanho necessário, ele é marcado
  /// como ocupado. Caso contrário, o bloco é particionado: um novo nó
  /// ([MemoryBlock]) é inserido para o processo, e o bloco original tem seu
  /// [start] e [size] ajustados para representar o fragmento restante.
  ///
  /// Retorna `true` se a alocação foi bem-sucedida, ou `false` se não houver
  /// espaço suficiente.
  bool allocate(Process process, FitType fitType) {
    if (process.size <= 0 || process.size > totalSize) return false;

    MemoryBlock? target;
    MemoryBlock? targetPrev;

    MemoryBlock? prev;
    MemoryBlock? current = head;

    while (current != null) {
      if (current.isFree && current.size >= process.size) {
        final bool select;
        if (target == null) {
          select = true;
        } else if (fitType == FitType.worstFit &&
            current.size > target.size) {
          select = true;
        } else {
          select = false;
        }

        if (select) {
          target = current;
          targetPrev = prev;
          if (fitType == FitType.firstFit) break;
        }
      }
      prev = current;
      current = current.next;
    }

    if (target == null) return false;

    if (target.size == process.size) {
      target.isFree = false;
      target.process = process;
    } else {
      final allocated = MemoryBlock(
        start: target.start,
        size: process.size,
        isFree: false,
        process: process,
        next: target,
      );
      target.start += process.size;
      target.size -= process.size;
      if (targetPrev != null) {
        targetPrev.next = allocated;
      } else {
        head = allocated;
      }
    }

    _processes.add(process);
    return true;
  }

  /// Remove [process] da memória, marcando seu bloco como livre.
  ///
  /// Percorre a lista encadeada linear à procura do bloco ocupado cujo
  /// [process] seja [process] (comparação por identidade de objeto). Quando
  /// encontrado, o bloco é marcado como livre e o processo é removido da
  /// lista interna.
  ///
  /// Retorna `true` se o processo foi encontrado e desalocado, ou `false`
  /// caso contrário.
  bool deallocate(Process process) {
    MemoryBlock? current = head;
    while (current != null) {
      if (!current.isFree && current.process == process) {
        current.isFree = true;
        current.process = null;
        _processes.remove(process);
        return true;
      }
      current = current.next;
    }
    return false;
  }

  /// Une blocos livres adjacentes para reduzir a fragmentação externa.
  ///
  /// Percorre a lista encadeada linear a partir de [head]. Para cada bloco
  /// livre encontrado, verifica se o próximo bloco ([next]) também é livre;
  /// em caso afirmativo, absorve o próximo bloco no atual (somando [size] e
  /// desviando o ponteiro [next]).
  void coalesce() {
    MemoryBlock? current = head;
    while (current != null) {
      if (current.isFree) {
        while (current.next != null && current.next!.isFree) {
          current.size += current.next!.size;
          current.next = current.next!.next;
        }
      }
      current = current.next;
    }
  }

  /// Realoca (compacta) todos os processos alocados para o início da memória,
  /// deixando um único bloco livre contíguo no final.
  ///
  /// Coleta todos os blocos ocupados na ordem atual, recalcula seus endereços
  /// de forma sequencial a partir de 0, e insere um único bloco livre com o
  /// espaço restante. Isso elimina toda a fragmentação externa.
  void defrag() {
    final allocated = <MemoryBlock>[];
    MemoryBlock? current = head;
    while (current != null) {
      if (!current.isFree) {
        allocated.add(current);
      }
      current = current.next;
    }

    if (allocated.isEmpty) return;

    int nextStart = 0;
    MemoryBlock? prev;
    for (final block in allocated) {
      block.start = nextStart;
      nextStart += block.size;
      block.next = null;
      if (prev != null) {
        prev.next = block;
      } else {
        head = block;
      }
      prev = block;
    }

    final remaining = totalSize - nextStart;
    if (remaining > 0) {
      prev!.next = MemoryBlock(start: nextStart, size: remaining);
    }
  }

  /// Retorna uma lista de 256 [Color] representando o estado atual da grid.
  ///
  /// Cada índice corresponde a uma célula de 1 KB na grid 16x16.
  /// Células livres são representadas por [Colors.black]; células ocupadas
  /// recebem a cor do processo [Process.color] alocado naquele endereço.
  List<Color> getGridColors() {
    final colors = List.filled(totalSize, Colors.black);
    MemoryBlock? current = head;
    while (current != null) {
      if (!current.isFree && current.process != null) {
        for (int i = current.start;
            i < current.start + current.size && i < totalSize;
            i++) {
          colors[i] = current.process!.color;
        }
      }
      current = current.next;
    }
    return colors;
  }
}
