
public class Papagaio extends Animal {
	public Papagaio(String nome) {
		super(nome,"papagaio.wav");
	}
	
	public void mover() {
		System.out.println(this.nome + " saiu voando");
	}
}
