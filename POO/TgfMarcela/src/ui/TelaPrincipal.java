package ui;

import dominio.Conversa;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TelaPrincipal extends VBox {
	ConversaUI conversaui;
	public TelaPrincipal(Conversa conversa) {
		super();
		
		this.conversaui = new ConversaUI(conversa);
		this.conversaui.visualizarMensagens();
		this.criarComponentes();
	}
	
	public void criarComponentes() {
		VBox.setVgrow(conversaui, Priority.ALWAYS);
		
		//o componente da barra superior sera uma caixa de selecao
		//para escolher com quem conversar
		String[] opcoesConversante = new String[] {"ChatGPT","DeepSeek","Respostas Prontas"};
		ChoiceBox choice = new ChoiceBox(FXCollections.observableArrayList(opcoesConversante));
		choice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number value, Number new_value) {
				// TODO Auto-generated method stub
				conversaui.mudarConversante(new_value.intValue());
			}
		});
		choice.getSelectionModel().select(2);
		
		//agora vamos ter os componentes da barra de baixo:
		TextField inputText = new TextField();
		Button botao = new Button("Enviar");
		
		HBox.setHgrow(inputText, Priority.ALWAYS);
		botao.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(conversaui.enviarMensagem(inputText.getText())) {
					inputText.setText("");
				}
			}
		});
		botao.setMaxWidth(Double.MAX_VALUE);
		
		this.getChildren().addAll(
				new ToolBar(choice),
				this.conversaui,
				new ToolBar(inputText,botao)
			);
	}
}
