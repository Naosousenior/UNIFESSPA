package app;

import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import servicosTecnicos.ConexaoBD;
import servicosTecnicos.Env;

public class Main {
	public static void finalizarPrograma(String titulo,String texto) {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle(titulo);
		alerta.setContentText(texto);
		alerta.show();
		ConexaoBD.fechaConexao();
		System.exit(1);
	}
	public static void main(String[] args) {
		
		try {
			Env.load("src//assets/environment.env");
			System.out.println("Chaves API:\nDeepSeek: %s\nChatGPT:%s".formatted(Env.get("DEEPSEEK_KEY"),Env.get("CHATGPT_KEY")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
}