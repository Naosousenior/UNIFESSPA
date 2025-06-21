package dominio;

import java.util.ArrayList;
import java.util.List;

public class Historico {
	private List<Mensagem> mensagens;
	
	public Historico() {
		this.mensagens = new ArrayList<>();
	}
	
	public void addMensagem(Mensagem mensagem) {
		this.mensagens.add(mensagem);
	}
}
