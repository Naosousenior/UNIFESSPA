package tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dominio.HistoricoController;
import dominio.Mensagem;
import dominio.MensagemUsuario;
import servicosTecnicos.ConexaoBD;
import servicosTecnicos.Env;

public class TestaHistorico {
	public static void main(String[] args) {
		try {
			Env.load("src/assets/environment.env");
			ConexaoBD.initConnection(Env.get("BD_URL"),Env.get("BD_USER"),Env.get("BD_PASSWORD"));
			HistoricoController hc = new HistoricoController();
			
			List<Mensagem> mensagensAntigas = hc.getMensagensAntigas();
			for(Mensagem m :mensagensAntigas) {
				System.out.println("[%s %s]: %s".formatted(m.getAutor(),m.getDataTime().toString(),m.getTexto()));
			}
			
			hc.addMensagem(new MensagemUsuario("adolfo","Eai meu brother"));
			Thread.sleep(1000);
			hc.addMensagem(new MensagemUsuario("IA","ola meu peixe"));
			Thread.sleep(1000);
			hc.addMensagem(new MensagemUsuario("adolfo","como vc ta"));
			Thread.sleep(1000);
			hc.addMensagem(new MensagemUsuario("IA","to bem meu peixe"));
			Thread.sleep(1000);
			hc.salvarNovasMensagens();
			Thread.sleep(1000);
			ConexaoBD.fechaConexao();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
