package dominio;

import java.util.List;
import java.util.Vector;

import app.Main;

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
	
	/*
	 * Esta funcao guarda duas funcoes dentro de si
	 * se a opcao de apagar a mensagem estiver habilidata, esta acao era feita
	 * caso contrario, a mensagem sera simplesmente editada
	 */
	public void manipulaMensagem(Mensagem m,boolean apagar,String texto) {
		if (apagar) {
			this.historicoConversa.apagarMensagem(m);
			return;
		} else {
			this.historicoConversa.editaMensagem(m, texto);
		}
	}
	
	public Mensagem enviaMensagem(Mensagem mensagem) {
		this.historicoConversa.addMensagem(mensagem);
		try {
			String resposta = this.conversante.responder(mensagem.prepareTexto());
			Mensagem resultado = new MensagemTexto("IA", resposta);
			this.historicoConversa.addMensagem(resultado);
			return resultado;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Main.mostraAlerta("Erro ao enviar mensagem", e.getMessage());
		}
		
		return null;
	}
	
	public void salvarMensagens() {
		this.historicoConversa.salvarNovasMensagens();
	}
	
	public Vector<String> getPerguntasProntas() {
		return this.perguntasProntas;
	}
	
	public List<Mensagem> getUltimasMensagens() {
		return this.historicoConversa.getMensagensAntigas();
	}
}
