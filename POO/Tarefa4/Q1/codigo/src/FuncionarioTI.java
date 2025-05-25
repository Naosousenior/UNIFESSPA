
public abstract class FuncionarioTI {
	protected String nome;
	protected int matricula;
	
	public FuncionarioTI(String nome, int matricula) {
		this.nome = nome;
		this.matricula = matricula;
	}
	
	public void exibirDados() {
		System.out.println("Nome: "+this.nome);
		System.out.println("Matrícula: "+this.nome);
	}
	
	public void atualizarCadastro(String nome, int matricula) {
		System.out.println("Atualizando o nome de:");
		System.out.println(this.nome);
		System.out.println("Para:");
		System.out.println(nome);
		System.out.println("Atualizando matrícula de:");
		System.out.println(this.matricula);
		System.out.println("Para:");
		System.out.println(matricula);
		
		this.nome = nome;
		this.matricula = matricula;
	}
	
	public String getNome() {
		return this.nome;
	}
	public int getMatricula() {
		return this.matricula;
	}
}
