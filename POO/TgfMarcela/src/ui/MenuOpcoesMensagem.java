package ui;

import dominio.Mensagem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class MenuOpcoesMensagem extends ContextMenu {
	private Mensagem referencia;
	public MenuOpcoesMensagem(Mensagem mensagem) {
		super();
		this.referencia = mensagem;
		configurarItens();
	}
	
	public void configurarItens() {
		MenuItem editar = new MenuItem("Editar");
		editar.setOnAction(event -> {
			System.out.println(referencia.getTexto());
		});
		
		MenuItem apagar = new MenuItem("Apagar");
		apagar.setOnAction(e -> {
			System.out.println("Apagando mensagem de: "+referencia.getAutor());
		});
		
		this.getItems().add(editar);
		this.getItems().add(apagar);
	}
}
