
public class Projeto {
	private String nome;
	private String descricao;
	private Dev criador;
	
	public Projeto(String nome,String descricao,Dev criador) {
		this.nome = nome;
		this.descricao = descricao;
		this.criador = criador;
	}
	
	public String getNome() {
		return this.nome;
	}
	public void setNome(String novoNome) {
		this.nome = novoNome;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public String relatorio() {
		String resultado = "";
		
		resultado += "Criador: "+this.criador.getNome()+"\n";
		resultado += "Nome do projeto: "+this.nome+"\n";
		resultado += "Descricao do projeto: "+this.descricao;
		
		return resultado;
	}
}
