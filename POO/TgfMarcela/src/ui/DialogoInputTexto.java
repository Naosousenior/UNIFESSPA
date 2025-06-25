package ui;

import java.util.Optional;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class DialogoInputTexto {

    public static String mostrarDialogo(String titulo, String mensagem) {
        // Cria o diálogo
        Dialog<Pair<String, String>> dialogo = new Dialog<>();
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(mensagem);
        
        // Configura os botões
        ButtonType botaoOk = new ButtonType("OK", ButtonData.OK_DONE);
        dialogo.getDialogPane().getButtonTypes().addAll(botaoOk, ButtonType.CANCEL);
        
        // Cria o layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        
        TextField campoTexto = new TextField();
        campoTexto.setPromptText("Digite aqui");
        
        grid.add(new Label(mensagem + ":"), 0, 0);
        grid.add(campoTexto, 0, 1);
        
        dialogo.getDialogPane().setContent(grid);
        
        // Foca no campo de texto quando abre
        Platform.runLater(campoTexto::requestFocus);
        
        // Configura o resultado
        dialogo.setResultConverter(botao -> {
            if (botao == botaoOk) {
                return new Pair<>("OK", campoTexto.getText());
            }
            return null;
        });
        
        // Mostra o diálogo e retorna o resultado
        Optional<Pair<String, String>> resultado = dialogo.showAndWait();
        
        return resultado.map(Pair::getValue).orElse(null);
    }
}