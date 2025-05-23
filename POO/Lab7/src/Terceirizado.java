public class Terceirizado implements PresencaRegistravel {
	private String nome;
	
	public Terceirizado(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	@Override
	public void registraPresenca() {
		// TODO Auto-generated method stub
		System.out.println(this.nome + " está registrando presença");
	}

	public void executarServico() {
		System.out.println(this.nome +" está trabalhando");
	}
}
