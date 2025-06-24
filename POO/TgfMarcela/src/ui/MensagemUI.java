package ui;

import dominio.Mensagem;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class MensagemUI extends StackPane {
	private Pane balao;
	private Label textoLabel;
	private Color cor = Color.LIGHTGRAY;
	private MenuOpcoesMensagem menu;
	
	private Mensagem mensagem;
	
	public MensagemUI(Mensagem mensagem) {
		this.mensagem = mensagem;
		
		criarComponentes();
		configurarEventos();
		getChildren().addAll(balao, textoLabel);
		setStyle("-fx-padding: 0 0 10 0;");
		
		this.setTexto(mensagem.getTexto());
		if(mensagem.getAutor().equals("IA")) {
			this.setCor(Color.MEDIUMSEAGREEN);
		} else {
			this.setCor(Color.MEDIUMSLATEBLUE);
		}
	}
	
	private void criarComponentes() {
		this.menu = new MenuOpcoesMensagem(this.mensagem);
		
		textoLabel = new Label();
		textoLabel.setWrapText(true);
		textoLabel.setMaxWidth(300);
		textoLabel.setPadding(new Insets(5));
		textoLabel.setAlignment(Pos.TOP_LEFT);
		
		textoLabel.textProperty().addListener((obs, oldVal, newVal) -> {
	        textoLabel.applyCss();
	        textoLabel.layout();
	        requestLayout();
	    });
		
		balao = new Pane();
		balao.setBackground(new Background(new BackgroundFill(cor, new CornerRadii(10), null)));
		balao.setEffect(new DropShadow(5, Color.GRAY));
	}
	
	private void configurarEventos() {
		setOnMouseClicked(event -> {
			menu.show(this,event.getScreenX(),event.getScreenY());
			});
	    
		setOnMouseEntered(event -> {
			setEffect(new javafx.scene.effect.Glow(0.2));
			});
	    
		setOnMouseExited(event -> {
			setEffect(new DropShadow(5, Color.GRAY));
			});
	}
	
	public void setCor(Color cor) {
		this.cor = cor;
		balao.setBackground(new Background(new BackgroundFill(cor, new CornerRadii(10), null)));
	}
	
	public void setTexto(String texto) {
		textoLabel.setText(texto);
	}
	
	public String getTexto() {
		return textoLabel.getText();
	}
	
	@Override
	protected void layoutChildren() {
		super.layoutChildren();
		textoLabel.applyCss();
	    textoLabel.layout();
	    
	    double width = textoLabel.prefWidth(-1) + 20;
	    double height = textoLabel.prefHeight(width) + 20;
	        
		balao.setMinSize(width, height);
		balao.setPrefSize(width, height);
		balao.setMaxSize(width, height);
	}
}