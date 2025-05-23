public class Vaca extends Animal {
	public Vaca(String nome) {
		super(nome,"vaca.wav");
	}
	@Override
	public void mover() {
		// TODO Auto-generated method stub
		System.out.println(this.nome +" saiu pastando");
	}

}
