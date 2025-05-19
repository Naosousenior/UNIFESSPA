import java.util.Random;

public class Carro {
	private String modelo;
	private String marca;
	private int ano;
	private boolean ligado;
	
	public Carro(String modelo,String marca,int ano) {
		this.modelo = modelo;
		this.marca = marca;
		this.ano = ano;
	}
	
	public void dirigir(int velocidade) {
		System.out.printf("%s andando a %d km/h\n",this.modelo,velocidade);
	}
	
	public void ligar() {
		if (this.ligado) {
			System.out.printf("O %s ja esta ligado\n",this.modelo);
		} else {
			Random aux = new Random();
			if (aux.nextBoolean()) {
				ligado = true;
				System.out.printf("%s ligado com sucesso\n", this.modelo);
			} else {
				System.out.println("Nao foi possivel ligar o carro.");
			}
		}
	}
	public void desligar() {
		if (!this.ligado) {
			System.out.println("Desligando");
		}
	}
	
	public int getAno() {return this.ano;}
	public String getModelo() {return this.modelo;}
	public String getMarca() {return this.marca;}
}
