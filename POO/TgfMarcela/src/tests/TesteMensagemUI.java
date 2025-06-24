package tests;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.MensagemUI;

public class TesteMensagemUI extends Application {
	
	public static void main(String[] args) {
        Application.launch(args);
    }
	
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        
        // Mensagem com perninha à esquerda (padrão)
        MensagemUI msg1 = new MensagemUI(null);
        msg1.setTexto("Olá, esta é uma mensagem!");
        msg1.setCor(Color.LIGHTBLUE);
        
        // Mensagem com perninha à direita
        MensagemUI msg2 = new MensagemUI(null);
        msg2.setTexto("E esta é uma mensagem com a cor diferente!");
        msg2.setCor(Color.LIGHTCORAL);
        
        root.getChildren().addAll(msg1, msg2);
        
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Exemplo MensagemUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
