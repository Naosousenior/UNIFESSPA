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
		Button botao2 = new Button("Enviar");
		Button botao1 = new Button("Perguntas Frequentes");
		
		//por fim, crio o menu de perguntas prontas
		MenuOpcoesProntas opcoes = this.conversaui.getMenuPerguntasProntas(inputText);
		botao1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				// TODO Auto-generated method stub
				opcoes.show(botao1,botao1.getHeight(),botao2.getWidth());
			}
		});
		
		HBox.setHgrow(inputText, Priority.ALWAYS);
		botao2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(inputText.getText());
				if(conversaui.enviarMensagem(inputText.getText())) {
					inputText.setText("");
				}
			}
		});
		botao1.setMaxWidth(Double.MAX_VALUE);
		botao2.setMaxWidth(Double.MAX_VALUE);
		
		this.getChildren().addAll(
				new ToolBar(choice),
				this.conversaui,
				new ToolBar(botao1,inputText,botao2)
			);
	}
}
