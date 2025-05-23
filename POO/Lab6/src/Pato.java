import java.util.Random;

public class Pato extends Animal {
	public Pato(String nome) {
		super(nome,"pato.wav");
	}
	@Override
	public void mover() {
		Random acao = new Random();
		String[] acoes = {"andando","nadando","voando"};
		
		System.out.println(this.nome + " saiu "+acoes[acao.nextInt(0, 3)]);
	}
}
