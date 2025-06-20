// Define o pacote onde esta classe está localizada
package dominio;

// Classe que representa um Aluno no sistema
public class Aluno {
    // Atributos (ou propriedades) privados do aluno
    private String matricula; // Identificador único do aluno
    private String nome;      // Nome completo do aluno
    private String curso;     // Curso que o aluno está matriculado
    private int idade;        // Idade do aluno

    // Construtor da classe: utilizado para criar um novo aluno com os dados informados
    public Aluno(String matricula, String nome, String curso, int idade) {
        this.matricula = matricula;
        this.nome = nome;
        this.curso = curso;
        this.idade = idade;
    }

    // Métodos Getters e Setters:
    // Permitem acessar (get) ou modificar (set) os valores dos atributos privados

    // Retorna a matrícula do aluno
    public String getMatricula() { return matricula; }

    // Altera a matrícula do aluno
    public void setMatricula(String matricula) { this.matricula = matricula; }

    // Retorna o nome do aluno
    public String getNome() { return nome; }

    // Altera o nome do aluno
    public void setNome(String nome) { this.nome = nome; }

    // Retorna o curso do aluno
    public String getCurso() { return curso; }

    // Altera o curso do aluno
    public void setCurso(String curso) { this.curso = curso; }

    // Retorna a idade do aluno
    public int getIdade() { return idade; }

    // Altera a idade do aluno
    public void setIdade(int idade) { this.idade = idade; }
}
