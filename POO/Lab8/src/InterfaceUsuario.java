import java.util.Scanner;

public class InterfaceUsuario {
	private Scanner entrada;
	private ListaDeCompras lista;
	
	public InterfaceUsuario(Scanner scanner,ListaDeCompras lista) {
		this.entrada = scanner;
		this.lista = lista;
	}
	
	public void exibirMenu() {
		System.out.print(
				"Opções de ação da lista de compras:\n"
				+"1. Adicionar item a lista de compras\n"
				+"2. Remover item da lista de compras\n"
				+"3. Verificar se a lista um item\n"
				+"4. Exibir lista de compras\n"
				+"0. Sair\n\n"
				+"Escolha uma opção: "
			);
	}
	
	private int lerInteiro() {
		exibirMenu();
		int valor = 0;
		
		do {
			valor = entrada.nextInt();
		} while (valor < 0 && valor > 4);
		
		return valor;
	}
	
	public void executar() {
		int comando = 0;
		
		do {
			comando = this.lerInteiro();
			
			switch (comando) {
			case 1:
				System.out.println("Qual item você deseja adicionar?");
				this.lista.adicionarItem(entrada.next());
				System.out.println("Item adicionado com sucesso!");
				break;
			case 2:
				System.out.println("Qual item deseja remover?");
				this.lista.removeItem(entrada.next());
				System.out.println("Item removido com sucesso.");
				break;
			case 3:
				System.out.println("Qual item você deseja verificar a existencia?");
				if(this.lista.contemItem(entrada.next())) {
					System.out.println("A lista ja tem esse item");
				} else {
					System.out.println("A lista não contém este item");
				}
				break;
			case 4:
				this.lista.exibirItens();
				break;
			}
		} while(comando != 0);
	}
}
