
public class MaterialBibliografico {
	protected String titulo;
	protected String codigo;
	
	public MaterialBibliografico(String titulo,String codigo) {
		this.titulo = titulo;
		this.codigo = codigo;
	}
	
	public void emprestar() {
		System.out.printf("Titulo: %s. Codigo: %s",this.titulo,this.codigo);
	}
}
