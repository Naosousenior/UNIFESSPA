import java.util.ArrayList;
import java.util.List;

public class Curso {
	private String nome;
	private int cargahoraria;
	private String nivel;
	private String descricao;
	private List<Instrutor> instrutores;
	
	public Curso(String nome,String nivel, String descricao, int cargahoraria) {
		this.nome = nome;
		this.nivel = nivel;
		this.descricao = descricao;
		this.cargahoraria = cargahoraria;
		this.instrutores = new ArrayList<Instrutor>();
	}
	
	public String getNome() {return this.nome;}
	
	public String getInformacoes() {
		if (this.instrutores.size() < 1) {
			return "O curso esta inapto, pois nao ha instrutor para ministra-lo";
		}
		
		return String.format(
				"Nome do curso: %s\nNivel de graduacao do curso: %s\nDescricao: %s"
				+"\nHoras: %d\n",
				this.nome,
				this.nivel,
				this.descricao,
				this.cargahoraria
			);
	}
	
	public String listaInstrutores() {
		String texto = "";
		
		for(Instrutor i:this.instrutores) {
			texto += String.format(
					"Nome: %s, Formacao: %s, Email: %s, %d anos de experiencia\n",
					i.getNome(),
					i.getFormacao(),
					i.getEmail(),
					i.getexperiencia()
				);
		}
		
		return texto;
	}
	
	public void addInstrutor(Instrutor instrutor) {this.instrutores.add(instrutor);}
}
