package dominio;

import java.util.Vector;

public class Conversa {
	private HistoricoController historicoConversa;
	private Conversante conversante;
	private Vector<String> perguntasProntas;
	
	public Conversa(Vector<String> perguntas) {
		this.perguntasProntas = perguntas;
		this.historicoConversa = new HistoricoController();
	}
	
	public void setConversante(Conversante conversante) {
		this.conversante = conversante;
	}
	
	public void manipulaMensagem(Mensagem m,boolean apagar,String texto) {
		if (apagar) {
			this.historicoConversa.apagarMensagem(m);
			return;
		} else {
			this.historicoConversa.editaMensagem(m, texto);
		}
	}
	
	
}
