// Define o pacote da interface gráfica (UI)
package ui;

// Importa a classe de controle e a classe de modelo (Aluno)
import dominio.AlunoController;
import dominio.Aluno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

// Classe que representa a janela gráfica de consulta de alunos
// Herda de JFrame (janela do Swing)
public class JFrameJanelaConsulta extends JFrame {

    // Tabela para exibir os dados dos alunos
    private JTable tabela;

    // Controlador usado para buscar os alunos cadastrados
    private AlunoController controller;

    // Construtor da janela de consulta. Recebe o controlador como parâmetro
    public JFrameJanelaConsulta(AlunoController controller) {
        this.controller = controller;

        // Define título, tamanho e ação ao fechar a janela
        setTitle("Consulta de Alunos");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Define o layout como BorderLayout (permite organizar por regiões)
        setLayout(new BorderLayout());

        // Cria a tabela (inicialmente vazia) e adiciona dentro de um painel com rolagem
        tabela = new JTable();
        add(new JScrollPane(tabela), BorderLayout.CENTER); // Centraliza a tabela na janela

        // Chama o método para carregar os dados na tabela
        carregarTabela();

        // Torna a janela visível
        setVisible(true);
    }

    // Método que carrega os dados dos alunos na tabela
    private void carregarTabela() {
        // Obtém a lista de alunos do controlador
        List<Aluno> alunos = controller.listarAlunos();

        // Cria o modelo da tabela (estrutura de dados para preencher a JTable)
        DefaultTableModel model = new DefaultTableModel();

        // Define os nomes das colunas da tabela
        model.addColumn("Matrícula");
        model.addColumn("Nome");
        model.addColumn("Curso");
        model.addColumn("Idade");

        // Para cada aluno da lista, adiciona uma linha na tabela
        for (Aluno a : alunos) {
            model.addRow(new Object[]{
                    a.getMatricula(), a.getNome(), a.getCurso(), a.getIdade()
            });
        }

        // Associa o modelo preenchido à JTable
        tabela.setModel(model);
    }
}
