import 'package:flutter/material.dart';
import 'memory.dart';

/// Ponto de entrada da aplicação.
///
/// Inicializa o [MaterialApp] com a página principal [MemorySimulatorPage],
/// sem o banner de debug e com o título "Simulador de Memória".
void main() {
  runApp(const MaterialApp(
    title: 'Simulador de Memória',
    debugShowCheckedModeBanner: false,
    home: MemorySimulatorPage(),
  ));
}

/// Página principal do simulador de gerenciamento de memória.
///
/// A tela é dividida horizontalmente em dois painéis:
///   - **Painel esquerdo (controle):** alternância de algoritmo First-Fit /
///     Worst-Fit, botões para criar, deletar e realocar processos.
///   - **Painel direito (visualizador):** grid 16x16 representando os
///     256 KB de memória, onde cada célula muda de cor conforme o
///     processo alocado.
class MemorySimulatorPage extends StatefulWidget {
  const MemorySimulatorPage({super.key});

  @override
  State<MemorySimulatorPage> createState() => _MemorySimulatorPageState();
}

class _MemorySimulatorPageState extends State<MemorySimulatorPage> {
  final MemoryManager _memoryManager = MemoryManager();
  FitType _fitType = FitType.firstFit;

  /// Exibe um diálogo para criação de um novo processo.
  ///
  /// O diálogo contém:
  ///   - Campo textual para o **nome** do processo.
  ///   - Campo numérico para o **tamanho** em KB.
  ///   - Seletor de **cor** entre 12 opções predefinidas.
  ///
  /// Ao confirmar, tenta alocar o processo via [_memoryManager.allocate].
  /// Se não houver memória suficiente, exibe um [SnackBar] de erro.
  void _showCreateDialog() {
    final nameCtrl = TextEditingController();
    final sizeCtrl = TextEditingController();
    Color selectedColor = Colors.red;

    showDialog(
      context: context,
      builder: (ctx) {
        return StatefulBuilder(
          builder: (ctx, setDState) {
            return AlertDialog(
              title: const Text('Criar Processo'),
              content: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  TextField(
                    controller: nameCtrl,
                    decoration: const InputDecoration(
                      labelText: 'Nome',
                      border: OutlineInputBorder(),
                    ),
                  ),
                  const SizedBox(height: 12),
                  TextField(
                    controller: sizeCtrl,
                    decoration: const InputDecoration(
                      labelText: 'Tamanho (KB)',
                      border: OutlineInputBorder(),
                    ),
                    keyboardType: TextInputType.number,
                  ),
                  const SizedBox(height: 16),
                  const Text('Cor:'),
                  const SizedBox(height: 8),
                  Wrap(
                    spacing: 8,
                    runSpacing: 8,
                    children: [
                      Colors.red,
                      Colors.blue,
                      Colors.green,
                      Colors.orange,
                      Colors.purple,
                      Colors.teal,
                      Colors.pink,
                      Colors.amber,
                      Colors.indigo,
                      Colors.lime,
                      Colors.cyan,
                      Colors.brown,
                    ].map((c) {
                      return GestureDetector(
                        onTap: () => setDState(() => selectedColor = c),
                        child: Container(
                          width: 36,
                          height: 36,
                          decoration: BoxDecoration(
                            color: c,
                            shape: BoxShape.circle,
                            border: selectedColor == c
                                ? Border.all(color: Colors.white, width: 3)
                                : null,
                          ),
                        ),
                      );
                    }).toList(),
                  ),
                ],
              ),
              actions: [
                TextButton(
                  onPressed: () => Navigator.pop(ctx),
                  child: const Text('Cancelar'),
                ),
                ElevatedButton(
                  onPressed: () {
                    final name = nameCtrl.text.trim();
                    final size = int.tryParse(sizeCtrl.text.trim()) ?? 0;
                    if (name.isEmpty || size <= 0) return;
                    final proc = Process(
                      name: name,
                      size: size,
                      color: selectedColor,
                    );
                    if (_memoryManager.allocate(proc, _fitType)) {
                      Navigator.pop(ctx);
                      setState(() {});
                    } else {
                      ScaffoldMessenger.of(ctx).showSnackBar(
                        const SnackBar(content: Text('Memória insuficiente!')),
                      );
                    }
                  },
                  child: const Text('Criar'),
                ),
              ],
            );
          },
        );
      },
    );
  }

  /// Exibe um diálogo para deletar um processo ativo.
  ///
  /// Lista todos os processos atualmente alocados em um [DropdownButton].
  /// O usuário seleciona um processo e confirma a remoção, que desaloca
  /// o bloco correspondente via [_memoryManager.deallocate].
  ///
  /// Se não houver processos ativos, exibe um [SnackBar] informativo.
  void _showDeleteDialog() {
    final procs = _memoryManager.processes;
    if (procs.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Nenhum processo ativo.')),
      );
      return;
    }

    Process? selected;
    showDialog(
      context: context,
      builder: (ctx) {
        return StatefulBuilder(
          builder: (ctx, setDState) {
            return AlertDialog(
              title: const Text('Deletar Processo'),
              content: DropdownButton<Process>(
                value: selected,
                hint: const Text('Selecione um processo'),
                isExpanded: true,
                items: procs.map((p) {
                  return DropdownMenuItem(
                    value: p,
                    child: Row(
                      children: [
                        Container(width: 16, height: 16, color: p.color),
                        const SizedBox(width: 8),
                        Text('${p.name} (${p.size} KB)'),
                      ],
                    ),
                  );
                }).toList(),
                onChanged: (p) => setDState(() => selected = p),
              ),
              actions: [
                TextButton(
                  onPressed: () => Navigator.pop(ctx),
                  child: const Text('Cancelar'),
                ),
                ElevatedButton(
                  onPressed: selected != null
                      ? () {
                          _memoryManager.deallocate(selected!);
                          Navigator.pop(ctx);
                          setState(() {});
                        }
                      : null,
                  child: const Text('Deletar'),
                ),
              ],
            );
          },
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    final gridColors = _memoryManager.getGridColors();

    return Scaffold(
      appBar: AppBar(
        title: const Text('Simulador de Memória'),
      ),
      body: Row(
        children: [
          /// Painel esquerdo — controles
          Expanded(
            flex: 1,
            child: Padding(
              padding: const EdgeInsets.all(16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: [
                  const Text(
                    'Algoritmo:',
                    style: TextStyle(fontWeight: FontWeight.bold),
                  ),
                  const SizedBox(height: 8),
                  ToggleButtons(
                    isSelected: [
                      _fitType == FitType.firstFit,
                      _fitType == FitType.worstFit,
                    ],
                    onPressed: (i) {
                      setState(() {
                        _fitType = i == 0
                            ? FitType.firstFit
                            : FitType.worstFit;
                      });
                    },
                    children: const [Text('First Fit'), Text('Worst Fit')],
                  ),
                  const SizedBox(height: 24),
                  ElevatedButton.icon(
                    onPressed: _showCreateDialog,
                    icon: const Icon(Icons.add),
                    label: const Text('Criar Processo'),
                  ),
                  const SizedBox(height: 12),
                  ElevatedButton.icon(
                    onPressed: _showDeleteDialog,
                    icon: const Icon(Icons.delete),
                    label: const Text('Deletar Processo'),
                  ),
                  const SizedBox(height: 12),
                  ElevatedButton.icon(
                    onPressed: () {
                      _memoryManager.coalesce();
                      setState(() {});
                    },
                    icon: const Icon(Icons.memory),
                    label: const Text('Coalescência'),
                  ),
                  const SizedBox(height: 12),
                  ElevatedButton.icon(
                    onPressed: () {
                      _memoryManager.defrag();
                      setState(() {});
                    },
                    icon: const Icon(Icons.compress),
                    label: const Text('Realocar processos'),
                  ),
                ],
              ),
            ),
          ),
          const VerticalDivider(width: 1),

          /// Painel direito — visualizador da memória
          Expanded(
            flex: 2,
            child: Padding(
              padding: const EdgeInsets.all(16),
              child: GridView.builder(
                itemCount: 256,
                gridDelegate:
                    const SliverGridDelegateWithFixedCrossAxisCount(
                  crossAxisCount: 16,
                  crossAxisSpacing: 1,
                  mainAxisSpacing: 1,
                ),
                itemBuilder: (_, i) => Container(
                  decoration: BoxDecoration(
                    color: gridColors[i],
                    border: Border.all(
                      color: Colors.grey.shade800,
                      width: 0.5,
                    ),
                  ),
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
