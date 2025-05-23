
public class Livro extends MaterialBibliografico{
	private String autor;
	private String ISBN;
	private int qtdEmprestados = 0;
	
	public Livro(String titulo, String codigo, String autor, String ISBN) {
		super(titulo, codigo);
		this.autor = autor;
		this.ISBN = ISBN;
	}
	
	@Override
	public void emprestar() {
		super.emprestar();
		if (this.qtdEmprestados > 2) {
			System.out.println("Não é possível emprestar o livro "+this.autor
					+" pois ja foram feitos 3 emprestimos.");
			return;
		}
		this.qtdEmprestados += 1;
		
		System.out.printf("Livro %s, de %s emprestado. Codigo: %s\n",this.titulo,this.autor,this.codigo);
	}
	
	public String getISBN() {return this.ISBN;}
}
