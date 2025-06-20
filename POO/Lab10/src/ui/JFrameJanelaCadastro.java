// Define o pacote onde a classe está localizada
package ui;

// Importa o controlador que será usado para salvar o aluno
import dominio.AlunoController;

// Importa as bibliotecas gráficas do Swing
import javax.swing.*;
import java.awt.*;

// Classe que representa a janela gráfica de cadastro de aluno
// Ela herda de JFrame (janela principal do Swing)
public class JFrameJanelaCadastro extends JFrame {

    // Campos de texto para entrada de dados
    private JTextField txtMatricula, txtNome, txtCurso, txtIdade;

    // Referência ao controlador para chamar o método de salvar aluno
    private AlunoController controller;

    // Construtor da janela de cadastro. Recebe o controlador como parâmetro
    public JFrameJanelaCadastro(AlunoController controller) {
        this.controller = controller;

        // Define o título da janela
        setTitle("Cadastro de Aluno");

        // Define o tamanho da janela
        setSize(300, 250);

        // Define o que acontece ao fechar a janela (apenas fecha essa janela, sem encerrar o programa)
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Define o layout da janela como uma grade com 5 linhas e 2 colunas
        setLayout(new GridLayout(5, 2));

        // Cria e adiciona os rótulos e os campos de texto ao formulário
        add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        add(txtMatricula);

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("Curso:"));
        txtCurso = new JTextField();
        add(txtCurso);

        add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        add(txtIdade);

        // Cria o botão "Salvar" e define o que ele faz ao ser clicado
        JButton btnSalvar = new JButton("Salvar");

        // Adiciona um ouvinte de ação ao botão
        btnSalvar.addActionListener(e -> {
            // Lê os dados digitados nos campos
            String matricula = txtMatricula.getText();
            String nome = txtNome.getText();
            String curso = txtCurso.getText();
            int idade = Integer.parseInt(txtIdade.getText()); // Converte a idade de texto para inteiro

            // Chama o controlador para salvar o aluno com os dados preenchidos
            controller.salvarAluno(matricula, nome, curso, idade);

            // Exibe uma mensagem de confirmação
            JOptionPane.showMessageDialog(this, "Aluno salvo com sucesso!");
        });

        // Adiciona o botão à janela
        add(btnSalvar);

        // Torna a janela visível
        setVisible(true);
    }
}
