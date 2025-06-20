// Define o pacote onde esta classe está localizada
package dominio;

// Importa a classe responsável pelo acesso aos dados dos alunos (DAO = Data Access Object)
import servicosTecnicos.AlunoDAO;

// Importa a classe Aluno
import dominio.Aluno;

// Importa a classe List para trabalhar com listas de alunos
import java.util.List;

// Classe responsável por controlar as ações relacionadas ao Aluno (Controlador na arquitetura MVC)
public class AlunoController {

    // Cria uma instância do AlunoDAO, que será usada para salvar e listar alunos no sistema
    private AlunoDAO dao = new AlunoDAO();

    // Método para salvar um novo aluno no sistema
    public void salvarAluno(String matricula, String nome, String curso, int idade) {
        // Cria um objeto Aluno com os dados recebidos
        Aluno aluno = new Aluno(matricula, nome, curso, idade);

        // Chama o metodo do DAO para salvar esse aluno (ou seja, armazenar no banco)
        dao.salvar(aluno);
    }

    // Metodo para listar todos os alunos cadastrados
    public List<Aluno> listarAlunos() {
        // Retorna a lista de alunos fornecida pelo DAO
        return dao.listarTodos();
    }
}
