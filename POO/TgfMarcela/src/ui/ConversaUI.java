package ui;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import app.Main;
import dominio.Conversa;
import dominio.Conversante;
import dominio.Mensagem;
import dominio.MensagemUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import servicosTecnicos.ChatGPT;
import servicosTecnicos.DeepSeek;
import servicosTecnicos.Env;
import servicosTecnicos.RespostaProntas;

public class ConversaUI extends ScrollPane {
    private VBox contentBox;
    private ObservableList<MensagemUI> mensagens;
    private Conversa conversa;
    
    private MensagemCarregando animacaoLoading = new MensagemCarregando();
    private HBox containerAnimacao = new HBox();
    private boolean esperandoMensagem = false;
    
    public ConversaUI(Conversa conversa) {
    	this.conversa = conversa;
    	
        contentBox = new VBox(10);
        contentBox.setPadding(new Insets(10));
        contentBox.setFillWidth(true);
        
        setContent(contentBox);
        setFitToWidth(true);
        setFitToHeight(true);
        setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        
        mensagens = FXCollections.observableArrayList();
        
        this.containerAnimacao.setAlignment(Pos.CENTER_LEFT);
        this.containerAnimacao.setFillHeight(true);
        this.containerAnimacao.getChildren().add(this.animacaoLoading);
    }
    
    public void visualizarMensagens() {
    	List<Mensagem> ultimasMensagens = this.conversa.getUltimasMensagens();
    	
    	for (Mensagem m :ultimasMensagens) {
    		boolean alinhamento = !m.getAutor().equals("IA");
    		
    		addMensagem(new MensagemUI(m),alinhamento);
    	}
    }
    
    public void addMensagem(MensagemUI mensagem, boolean alinharDireita) {
        // Configura o alinhamento da mensagem
        configurarAlinhamento(mensagem, alinharDireita);
        
        // Adiciona à lista de mensagens
        mensagens.add(mensagem);
        
        // Rolagem automática para a nova mensagem
        layout();
        setVvalue(1.0);
    }
    
    public boolean enviarMensagem(String texto) {
    	if (this.esperandoMensagem) {
    		Alert alerta = new Alert(AlertType.WARNING,"Espere receber a proxima mensagem");
    		alerta.show();
    		return false;
    	}
    	Mensagem mensagem = new MensagemUsuario("Usuario", texto);
    	this.addMensagem(new MensagemUI(mensagem), true);
    	this.mostrarMensagemCarregando();
    	
    	Task<Mensagem> tarefa = new Task<Mensagem>() {
			
			@Override
			protected Mensagem call() throws Exception {
				// TODO Auto-generated method stub
				return conversa.enviaMensagem(mensagem);
			}
		};
		
		tarefa.setOnFailed(e -> {
			pararAnimacao();
		});
		tarefa.setOnSucceeded(e -> {
			addMensagem(new MensagemUI(tarefa.getValue()), false);
			pararAnimacao();
		});
		
		new Thread(tarefa).start();
    	
    	return true;
    }
    
    private void configurarAlinhamento(MensagemUI mensagem, boolean alinharDireita) {
        HBox container = new HBox();
        container.setAlignment(alinharDireita ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        container.setFillHeight(true);
        container.getChildren().add(mensagem);
        
        contentBox.getChildren().add(container);
    }
    
    private void mostrarMensagemCarregando() {
    	this.animacaoLoading.iniciarAnimacao();
    	if (this.esperandoMensagem) {
        	return;
        }
            
        contentBox.getChildren().add(containerAnimacao);
        this.esperandoMensagem = true;
    }
    
    private void pararAnimacao() {
    	this.esperandoMensagem = false;
    	this.animacaoLoading.pararAnimacao();
    	this.contentBox.getChildren().remove(this.containerAnimacao);
    	
    	// Rolagem automática para a nova mensagem
        layout();
        setVvalue(1.0);
    }
    
    public void limparConversa() {
        contentBox.getChildren().clear();
        mensagens.clear();
    }
    
    public void mudarConversante(int valor) {
    	Conversante c = null; //isso e uma pessima pratica, porem, e o que eu posso fazer
    	try {
    		switch(valor) {
    		case 0:
    			c = new ChatGPT(Env.get("CHATGPT_KEY"));
    			File pasta1 = new File("src/assets/tutoriais/");
    			File[] arquivos1 = pasta1.listFiles();
    			Path[] paths1 = new Path[arquivos1.length];
    			for(int j = 0;j<arquivos1.length;j++) {
    				paths1[j] = arquivos1[j].toPath();
    			}
    			
    			c.prepare(paths1);
    			break;
    		case 1:
    			c = new DeepSeek(Env.get("DEEPSEEK_KEY"));
    			File pasta2 = new File("src/assets/tutoriais/");
    			File[] arquivos2 = pasta2.listFiles();
    			Path[] paths2 = new Path[arquivos2.length];
    			for(int j = 0;j<arquivos2.length;j++) {
    				paths2[j] = arquivos2[j].toPath();
    			}
    			
    			c.prepare(paths2);
    			break;
    			
    		case 2:
    			c = new RespostaProntas();
    			c.prepare(new Path[] {Paths.get("src/assets/perguntas_respostas.json")});
    		}
    		
    		//System.out.println(valor);
    		this.conversa.setConversante(c);
    	} catch (Exception e) {
    		Main.mostraAlerta("Erro ao conectar.", e.getMessage());
    	}
    }
}



