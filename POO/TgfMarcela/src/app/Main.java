package app;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import dominio.Conversa;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import servicosTecnicos.ConexaoBD;
import servicosTecnicos.Env;
import servicosTecnicos.RespostaProntas;
import ui.TelaPrincipal;

public class Main extends Application {
	private static Alert alerta = new Alert(AlertType.ERROR);
	public static void finalizarPrograma(String titulo,String texto) {
		alerta.setTitle(titulo);
		alerta.setContentText(texto);
		alerta.show();
		ConexaoBD.fechaConexao();
		System.exit(1);
	}
	
	public static void mostraAlerta(String titulo, String texto) {
		alerta.setTitle(titulo);
		alerta.setContentText(texto);
		alerta.show();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		RespostaProntas respostas = new RespostaProntas();
		respostas.prepare(new Path[] {Paths.get("src/assets/perguntas_respostas.json")});
		
		Conversa conversa = new Conversa(respostas.getPerguntasProntas());
		conversa.setConversante(respostas);
		
		TelaPrincipal tela = new TelaPrincipal(conversa);
		Scene scene = new Scene(tela,600,400);
		stage.setTitle("App maneiro");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		
		try {
			Env.load("src/assets/environment.env");
			ConexaoBD.initConnection(Env.get("BD_URL"),Env.get("BD_USER"), Env.get("BD_PASSWORD"));
			launch(args);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
}