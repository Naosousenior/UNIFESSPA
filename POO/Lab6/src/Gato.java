
public class Gato extends Animal {
	public Gato (String nome) {
		super(nome,"gato.wav");
	}
	
	public void mover() {
		System.out.println(this.nome + " saiu andando");
	}
}
