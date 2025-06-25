package ui;

import java.util.Vector;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class MenuOpcoesProntas extends ContextMenu {
	private TextField field; 

	public MenuOpcoesProntas(Vector<String> opcoes,TextField input) {
		super();
		
		this.field = input;
		this.criarComponentes(opcoes);
	}
	
	public void criarComponentes(Vector<String> opcoes) {
		MenuItem item;
		
		for(String pergunta :opcoes) {
			item = new MenuItem(pergunta);
			
			item.setOnAction(e -> {
				this.field.setText(pergunta);
			});
			
			this.getItems().add(item);
		}
	}
}
