package dominio;

import java.util.ArrayList;
import java.util.List;

public class Historico {
	private List<Mensagem> mensagensSalvas;
	private List<Mensagem> mensagensNovas;
	
	public Historico(Mensagem[] mensagens) {
		this.mensagensSalvas = new ArrayList<Mensagem>();
		this.mensagensNovas = new ArrayList<Mensagem>();
		
		for (Mensagem m :mensagens) {
			mensagensSalvas.add(m);
		}
	}
	
	public void addMensagem(Mensagem mensagem) {
		this.mensagensNovas.add(mensagem);
	}
	
	//returna true para sinalizar ao controller que a mensagem esta no banco de dados
	//retorna false para sinalizar que nao e preciso atualizar no DAO
	public boolean substituirMensagem(Mensagem mensagemInicial, Mensagem mensagemFinal) {
		
		int posicao = this.mensagensSalvas.indexOf(mensagemInicial);
		
		if (posicao != 1) {
			this.mensagensSalvas.set(posicao, mensagemFinal);
			return true;
		}
		
		posicao = this.mensagensNovas.indexOf(mensagemInicial);
		if (posicao < 0) {
			return false;
		}
		
		this.mensagensNovas.set(posicao, mensagemFinal);
		return false;
	}
	
	public boolean apagarMensagem(Mensagem m) {
		if(this.mensagensSalvas.remove(m)) {
			return true;
		}
		
		return this.mensagensNovas.remove(m);
	}
	
	public List<Mensagem> getUltimasMensagens(int pos) {
		int length = this.mensagensSalvas.size();
		if (length <= pos) {
			return new ArrayList<Mensagem>();
		}
		
		return this.mensagensSalvas.subList(pos, length-1);
	}
	
	public List<Mensagem> getNovasMensagens() {
		return this.mensagensNovas;
	}
}
