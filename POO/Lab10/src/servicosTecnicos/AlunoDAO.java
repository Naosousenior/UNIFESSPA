// Define o pacote onde a classe está localizada
package servicosTecnicos;

// Importa a classe de conexão com o banco de dados
import servicosTecnicos.Conexao;

// Importa a classe Aluno, que será usada para armazenar os dados vindos do banco
import dominio.Aluno;

// Importa bibliotecas necessárias para trabalhar com banco de dados
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Classe responsável por acessar e manipular os dados dos alunos no banco de dados
public class AlunoDAO {

    // Método para salvar um aluno no banco de dados
    public void salvar(Aluno aluno) {
        // Comando SQL com parâmetros (placeholders ?) para inserir os dados
        String sql = "INSERT INTO alunos (matricula, nome, curso, idade) VALUES (?, ?, ?, ?)";

        // Bloco try-with-resources: garante que a conexão e o statement sejam fechados automaticamente
        try (
                // Abre a conexão com o banco de dados
                Connection conn = Conexao.getConnection();
                // Prepara o comando SQL para execução
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            // Define os valores dos parâmetros do SQL com os dados do objeto Aluno
            stmt.setString(1, aluno.getMatricula());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getCurso());
            stmt.setInt(4, aluno.getIdade());

            // Executa o comando de inserção
            stmt.executeUpdate();

        } catch (SQLException e) {
            // Em caso de erro, imprime o erro no console
            e.printStackTrace();
        }
    }

    // Método para buscar todos os alunos cadastrados no banco de dados
    public List<Aluno> listarTodos() {
        // Cria uma lista para armazenar os alunos recuperados do banco
        List<Aluno> lista = new ArrayList<>();

        // Comando SQL para selecionar todos os registros da tabela
        String sql = "SELECT * FROM alunos";

        // Bloco try-with-resources para garantir o fechamento das conexões
        try (
                Connection conn = Conexao.getConnection(); // Abre a conexão
                Statement stmt = conn.createStatement();   // Cria um statement simples
                ResultSet rs = stmt.executeQuery(sql)      // Executa a consulta e obtém os resultados
        ) {
            // Enquanto houver registros no resultado da consulta
            while (rs.next()) {
                // Cria um novo aluno com os dados do banco
                Aluno aluno = new Aluno(
                        rs.getString("matricula"),
                        rs.getString("nome"),
                        rs.getString("curso"),
                        rs.getInt("idade")
                );
                // Adiciona o aluno à lista
                lista.add(aluno);
            }

        } catch (SQLException e) {
            // Em caso de erro, imprime o erro no console
            e.printStackTrace();
        }

        // Retorna a lista de alunos recuperados do banco
        return lista;
    }
}
