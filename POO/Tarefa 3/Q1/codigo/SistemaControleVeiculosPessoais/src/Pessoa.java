import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pessoa {
	private String nome;
	private String CNH;
	private List<Carro> registro;
	
	public Pessoa(String nome,String CNH) {
		this.nome = nome;
		this.CNH = CNH;
		this.registro = new ArrayList<Carro>();
	}
	
	public void usarCarro(Carro c) {
		if (this.CNH == "") {
			return;
		}
		Random aux = new Random();
		c.dirigir(aux.nextInt(10, 240));
		this.registro.add(c);
	}
	
	public void comprarCarro(Carro c) {
		System.out.printf("Carro da marca %s, modelo %s comprado por %s\n",c.getMarca(),c.getModelo(),this.nome);
	}
	
	public String getNome() {return this.nome;}
	public String getCNH() {return this.CNH;}
	public String registroCarros() {
		String texto = "";
		for(Carro c: this.registro) {
			texto += String.format("Marca: %s, Modelo: %s, Ano: %d\n", c.getMarca(),c.getModelo(),c.getAno());
		}
		
		return texto;
	}
}
