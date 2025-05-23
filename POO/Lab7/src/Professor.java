
public class Professor extends Funcionario implements PresencaRegistravel{
	private String disciplina;
	
	public Professor(String nome,String disciplina) {
		super(nome);
		this.disciplina = disciplina;
	}
	@Override
	public void exibirDados() {
		// TODO Auto-generated method stub
		System.out.println("Funcionário: Professor");
		System.out.printf("Nome: %s\nDisciplina: %s\n",this.nome,this.disciplina);
	}

	public void lecionar() {
		System.out.printf("%s está ensinando %s para os burrinhos",this.nome,this.disciplina);
	}
	
	@Override
	public void registraPresenca() {
		// TODO Auto-generated method stub
		System.out.println(this.nome + " está registrando presencao para sua aula de "+this.disciplina);
	}
}
