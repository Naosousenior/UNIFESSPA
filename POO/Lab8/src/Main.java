import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		ListaDeCompras lista = new ListaDeCompras();
		InterfaceUsuario front = new InterfaceUsuario(scanner, lista);
		front.executar();
	}

}
