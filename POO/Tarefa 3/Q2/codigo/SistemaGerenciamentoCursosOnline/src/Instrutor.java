
public class Instrutor {
	private String nome;
	private String formacao;
	private String email;
	private int experiencia; //representa os anos de experiencia
	
	public Instrutor(String nome,String formacao,String email,int experiencia) {
		this.nome = nome;
		this.formacao = formacao;
		this.email = email;
		this.experiencia = experiencia;
	}
	
	public void ministrarAula(Curso c) {
		System.out.printf("%s esta ministrando aula de %s\n",this.nome,c.getNome());
		System.out.println("Informacoes sobre o curso:");
		System.out.println(c.getInformacoes());
		System.out.println("Instrutores do curso:");
		System.out.println(c.listaInstrutores());
	}
	
	public String getNome() {return this.nome;}
	public String getFormacao() {return this.formacao;}
	public String getEmail() {return this.email;}
	public int getexperiencia() {return this.experiencia;}
}
