import java.util.ArrayList;
import java.util.List;

public class ListaDeCompras {
	private List<String> itens;
	
	public ListaDeCompras() {
		this.itens = new ArrayList<String>();
	}
	
	public void adicionarItem(String item) {
		this.itens.add(item);
	}
	
	public void removeItem(String item) {
		this.itens.remove(item);
	}
	
	public void exibirItens() {
		System.out.println("Itens da lista de compras:");
		for (String i : this.itens) {
			System.out.println(i);
		}
	}
	
	public boolean contemItem(String item) {
		return this.itens.contains(item);
	}
	
	public void limparLista() {
		this.itens.clear();
	}
}
