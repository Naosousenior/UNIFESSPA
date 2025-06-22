package dominio;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import app.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import servicosTecnicos.ConexaoBD;
import servicosTecnicos.HistoricoDAO;

public class HistoricoController {
	private HistoricoDAO historicoDAO;
	private Historico historico;
	
	public HistoricoController() {
		this.historicoDAO = new HistoricoDAO();
		this.carregarMensagensBD();
	}
	
	private void carregarMensagensBD() {
		try {
			this.historicoDAO.criarTabelaHistorico();
			List<Mensagem> mensagensList = this.historicoDAO.carregarMensagens();
			Mensagem[] mensagensArray = new Mensagem[mensagensList.size()];
			mensagensList.toArray(mensagensArray);
			
			this.historico = new Historico(mensagensArray);
		} catch (SQLException e) {
			Main.finalizarPrograma("Problemas com o banco de dados", e.getMessage());
		}
	}
	
	public void addMensagem(Mensagem mensagem) {
		this.historico.addMensagem(mensagem);
	}
	
	public void salvarNovasMensagens() {
		List<Mensagem> novasMensagens = this.historico.getNovasMensagens();
		try {
			this.historicoDAO.salvaMensagens(novasMensagens);
		} catch (SQLException e) {
			Main.finalizarPrograma("Problemas com o banco de dados", e.getMessage());
		}
	}
	
	public void editaMensagem(Mensagem m, String novoTexto) {
		try {
			Mensagem novaMensagem = new MensagemUsuario(m.getAutor(),novoTexto,m.getDataTime());
			
			if (this.historico.substituirMensagem(m, novaMensagem)) {
				historicoDAO.atualizarMensagem(m.getDataTime(), novoTexto);
			}
			
			
		} catch (SQLException e) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Não foi possível atualizar a mensagem");
			alerta.setContentText(e.getMessage());
			alerta.show();
		}
	}
	
	public void apagarMensagem(Mensagem mensagem) {
		if(this.historico.apagarMensagem(mensagem)) {
			try {
				this.historicoDAO.deletarMensagem(mensagem.getDataTime());
			} catch (SQLException e) {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Não foi possível apagar a mensagem");
				alerta.setContentText(e.getMessage());
				alerta.show();
			}
		}
	}
	
	//e importante notar que este metodo nao retorna o historico completo,
	//mas apenas as ultimas 10 mensagens
	public List<Mensagem> getMensagensAntigas() {
		return this.historico.getUltimasMensagens(10);
	}
}
