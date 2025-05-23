
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Animal pato = new Pato("Rodolfo");
		Animal papagaio = new Papagaio("Chico");
		Animal gato = new Gato("Jhered");
		Animal vaca = new Vaca("Mimosa");
		
		pato.emitirSom();
		papagaio.emitirSom();
		gato.emitirSom();
		vaca.emitirSom();
		
		pato.mover();
		papagaio.mover();
		gato.mover();
		vaca.mover();
	}

}
