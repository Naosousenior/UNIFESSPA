package servicosTecnicos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dominio.Mensagem;
import dominio.MensagemUsuario;

public class HistoricoDAO {
	private final DateTimeFormatter formatador;
	
	public HistoricoDAO() {
		this.formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	}
	
	public void criarTabelaHistorico() throws SQLException {
		ConexaoBD.executeInstrucao(
				"CREATE TABLE IF NOT EXISTS Historico (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "texto TEXT NOT NULL," +
                "autor VARCHAR(100) NOT NULL," +
                "horario DATETIME NOT NULL" +
                ")"
          );
	}
	
	public void salvaMensagens(List<Mensagem> mensagens) throws SQLException {
		PreparedStatement stmt = ConexaoBD.prepareInstrucao("INSERT INTO Historico (texto, autor, horario) VALUES (?, ?, ?)");
		for (Mensagem m :mensagens) {
			stmt.setString(1, m.getTexto());
            stmt.setString(2, m.getAutor());
            stmt.setString(3, m.getDataTime().format(this.formatador));
            stmt.execute();
		}
		
		System.out.println("Mensagens salvas");
	}
	
	public void deletarMensagem(LocalDateTime momento) throws SQLException {
		String sql = "DELETE FROM Historico WHERE horario = '%s'".formatted(momento.format(this.formatador));
		
		ConexaoBD.executeInstrucao(sql);
	}
	
	public void atualizarMensagem(LocalDateTime momento,String novoTexto) throws SQLException {
		String sql = "UPDATE Historico SET texto = '%s' WHERE momento = '%s'".formatted(novoTexto,momento.format(formatador));
		
		ConexaoBD.executeInstrucao(sql);
	}
	
	public List<Mensagem> carregarMensagens() throws SQLException {
		ResultSet resultadoMensagens = ConexaoBD.queryInstrucao("SELECT texto, autor, horario FROM Historico ORDER BY horario");
		
		List<Mensagem> mensagens = new ArrayList<>();
		
		while (resultadoMensagens.next()) {
			mensagens.add(
					new MensagemUsuario(
						resultadoMensagens.getString("autor"),
						resultadoMensagens.getNString("texto"),
						LocalDateTime.parse(resultadoMensagens.getString("horario"), formatador)
					)
				);
		}
		
		return mensagens;
	}
}
