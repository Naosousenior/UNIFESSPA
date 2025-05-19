
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Carro c1 = new Carro("Gol","Volkswagen",2010);
		Carro c2 = new Carro("Palio","Fiat",2013);
		Carro c3 = new Carro("Onix","Chevrolet",2020);
		Carro c4 = new Carro("HB20","Hyundai",2019);
		Carro c5 = new Carro("Ka","Ford",2018);
		Carro c6 = new Carro("Kwid","Renault",2022);
		Carro c7 = new Carro("Corolla","Toyota",2015);
		Carro c8 = new Carro("Renegade","Jeep",2021);
		
		Pessoa p1 = new Pessoa("Joao Botao","23502418429");
		Pessoa p2 = new Pessoa("Joselina Fina","34613529530");
		
		p1.usarCarro(c1);
		p2.usarCarro(c2);
		p1.usarCarro(c3);
		p2.usarCarro(c4);
		p1.usarCarro(c5);
		p2.usarCarro(c6);
		p1.usarCarro(c7);
		p2.usarCarro(c8);
		
		System.out.println(p1.getNome()+" dirigiu os seguintes carros:");
		System.out.println(p1.registroCarros());
		System.out.println(p2.getNome()+" dirigiu os carros:");
		System.out.println(p2.registroCarros());
		
		p1.comprarCarro(c6);
		p2.comprarCarro(c3);
	}

}
