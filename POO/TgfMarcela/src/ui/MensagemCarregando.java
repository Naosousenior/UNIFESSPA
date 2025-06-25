package ui;

import dominio.MensagemTexto;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class MensagemCarregando extends MensagemUI {
    private static final Color COR_PADRAO = Color.LIGHTGRAY;
    private static final double TAMANHO_PONTO = 5;
    private static final double ESPACAMENTO = 8;
    
    private HBox pontosContainer;
    private Timeline animacao;
    
    public MensagemCarregando() {
        super(null,new MensagemTexto("Aborora","                  ",null));
        setCor(COR_PADRAO);
        criarAnimacao();
        this.setOnMouseClicked(null);
    }
    
    private void criarAnimacao() {
        // Remove o componente de texto original
        getChildren().removeIf(node -> node instanceof TextArea);
        
        // Cria container para os pontos de animação
        pontosContainer = new HBox(ESPACAMENTO);
        pontosContainer.setAlignment(Pos.CENTER);
        pontosContainer.setPadding(new Insets(10));
        
        // Cria 3 pontos
        for (int i = 0; i < 3; i++) {
            Circle ponto = new Circle(TAMANHO_PONTO, COR_PADRAO.darker());
            ponto.setOpacity(0.3);
            pontosContainer.getChildren().add(ponto);
        }
        
        // Adiciona ao layout
        getChildren().add(pontosContainer);
        
        // Cria animação
        animacao = new Timeline(
            new KeyFrame(Duration.ZERO, e -> resetPontos()),
            new KeyFrame(Duration.seconds(0.3), e -> animarPonto(0)),
            new KeyFrame(Duration.seconds(0.6), e -> animarPonto(1)),
            new KeyFrame(Duration.seconds(0.9), e -> animarPonto(2)),
            new KeyFrame(Duration.seconds(1.2))
        );
        animacao.setCycleCount(Animation.INDEFINITE);
        animacao.play();
    }
    
    private void resetPontos() {
        for (int i = 0; i < 3; i++) {
            Circle ponto = (Circle) pontosContainer.getChildren().get(i);
            ponto.setOpacity(0.3);
            ponto.setRadius(TAMANHO_PONTO);
        }
    }
    
    private void animarPonto(int index) {
        Circle ponto = (Circle) pontosContainer.getChildren().get(index);
        ponto.setOpacity(1.0);
        ponto.setRadius(TAMANHO_PONTO * 1.3);
    }
    
    public void iniciarAnimacao() {
        if (animacao != null) {
            animacao.play();
        }
    }
    
    public void pararAnimacao() {
        if (animacao != null) {
            animacao.stop();
            resetPontos();
        }
    }
}