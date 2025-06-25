package tests;

import java.sql.SQLException;
import java.util.Vector;

import dominio.Conversa;
import dominio.MensagemTexto;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import servicosTecnicos.ConexaoBD;
import servicosTecnicos.Env;
import ui.ConversaUI;
import ui.MensagemUI;

public class TesteLimiteCaracteres extends Application {

	private static String repetir(char caractere,int n) {
		String texto = "";
		for (int i = 0;i<n;i++) {
			texto += caractere;
			if(i % 10 == 0) {
				texto += "\n";
			}
		}
		return texto;
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		ConversaUI conversa = new ConversaUI(new Conversa(new Vector<String>()));
				
		for (int i = 0; i<600; i++) {
			conversa.addMensagem(
				new MensagemUI(
					new MensagemTexto("IA","%s: %s".formatted(i,repetir('a',i)))
				),
				false);
			}
		
		stage.setScene(new Scene(conversa));
		stage.show();
	}
	
	public static void main(String[] args) {
		
		try {
			Env.load("src/assets/environment.env");
			ConexaoBD.initConnection(Env.get("BD_URL"), Env.get("BD_USER"), Env.get("BD_PASSWORD"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		launch(args);
	}

}
