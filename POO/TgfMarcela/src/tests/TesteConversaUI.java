package tests;

import dominio.MensagemTexto;
import dominio.MensagemUsuario;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.ConversaUI;
import ui.MensagemUI;

public class TesteConversaUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        ConversaUI conversa = new ConversaUI(null);
        
        // Adicionando mensagens
        MensagemUI msg1 = new MensagemUI(new MensagemUsuario("Matheus", "Olá, tudo bem?"));
        conversa.addMensagem(msg1, true); // Alinhar à direita
        
        MensagemUI msg2 = new MensagemUI(new MensagemTexto("IA","Olá, como e bom ver você!"));
        conversa.addMensagem(msg2, false); // Alinhar à esquerda
        
        MensagemUI msg3 = new MensagemUI(new MensagemUsuario("Matheus", "Como eu faço pra acessar a internet?"));
        conversa.addMensagem(msg3, true); // Alinhar à direita
        
        Scene scene = new Scene(conversa, 400, 500);
        primaryStage.setTitle("Conversa UI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}