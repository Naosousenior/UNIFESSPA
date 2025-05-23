
public class Diretor extends Funcionario {
	private String setor;
	
	public Diretor(String nome, String setor) {
		super(nome);
		this.setor = setor;
	}
	
	@Override
	public void exibirDados() {
		System.out.println("Funcionario: Diretor");
		System.out.printf("Nome: %s\nSetor: %s\n",this.nome,this.setor);
	}
	
	public void tomarDecisao() {
		System.out.println("Desviar 50.000 de verba");
	}
}
