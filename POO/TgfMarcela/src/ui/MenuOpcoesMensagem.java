package ui;

import dominio.Conversa;
import dominio.Mensagem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class MenuOpcoesMensagem extends ContextMenu {
	private Mensagem mensagem;
	private Conversa conversa;
	private MensagemUI mensagemui;
	
	public MenuOpcoesMensagem(Conversa conversa) {
		super();
		this.conversa = conversa;
		configurarItens();
	}
	
	public void setMensagem(MensagemUI ui, Mensagem mensagem) {
		this.mensagemui = ui;
		this.mensagem = mensagem;
	}
	
	public void configurarItens() {
		MenuItem editar = new MenuItem("Editar");
		editar.setOnAction(event -> {
			String novoTexto = DialogoInputTexto.mostrarDialogo("Edite a mensagem", "Atualize o texto");
			if (novoTexto == null) {
				return;
			}
			conversa.manipulaMensagem(mensagem, false, novoTexto);
			mensagemui.setTexto(novoTexto);
		});
		
		MenuItem apagar = new MenuItem("Apagar");
		apagar.setOnAction(e -> {
			conversa.manipulaMensagem(mensagem, true, "");
			mensagemui.setTexto("Mensagem apagada.");
		});
		
		this.getItems().add(editar);
		this.getItems().add(apagar);
	}
}
