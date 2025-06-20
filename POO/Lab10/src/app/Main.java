// Define o pacote onde esta classe está localizada
package app;

// Importa a classe que gerencia a lógica de controle dos alunos
import dominio.AlunoController;

// Importa as janelas gráficas de cadastro e consulta de alunos
import ui.JFrameJanelaCadastro;
import ui.JFrameJanelaConsulta;

// Importações necessárias da biblioteca Swing e AWT para criar a interface gráfica
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Cria um objeto do controlador de alunos, que será usado pelas janelas
        AlunoController controller = new AlunoController();

        // Cria a janela principal com o título "Menu Principal"
        JFrame frame = new JFrame("Menu Principal");
        frame.setSize(300, 200); // Define o tamanho da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o app ao clicar no X
        frame.setLayout(new GridLayout(2, 1)); // Layout com 2 linhas e 1 coluna

        // Cria o botão "Cadastrar Aluno"
        JButton btnCadastrar = new JButton("Cadastrar Aluno");

        // Define a ação que ocorre ao clicar no botão: abrir a janela de cadastro
        btnCadastrar.addActionListener(e -> new JFrameJanelaCadastro(controller));

        // Adiciona o botão de cadastro à janela principal
        frame.add(btnCadastrar);

        // Cria o botão "Consultar Alunos"
        JButton btnConsultar = new JButton("Consultar Alunos");

        // Define a ação que ocorre ao clicar no botão: abrir a janela de consulta
        btnConsultar.addActionListener(e -> new JFrameJanelaConsulta(controller));

        // Adiciona o botão de consulta à janela principal
        frame.add(btnConsultar);

        // Torna a janela visível na tela
        frame.setVisible(true);
    }
}
