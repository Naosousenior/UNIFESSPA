import java.time.LocalDate;

public class Revista extends MaterialBibliografico{
	public int[] mesAnoEdicao;
	
	public Revista(String titulo,String codigo,int mes, int ano, int edicao) {
		super(titulo, codigo);
		this.mesAnoEdicao = new int[3];
		this.mesAnoEdicao[0] =  mes;
		this.mesAnoEdicao[1] = ano;
		this.mesAnoEdicao[2] =  edicao;
	}
	
	public void emprestar() {
		if (this.validarData()) {
			System.out.printf("Revista %s edicao %d emprestada\n",this.titulo,this.mesAnoEdicao[2]);
			return;
		}
		System.out.println("Revista não emprestada, pois está desatualizada");
	}
	
	private boolean validarData() {
		LocalDate data = LocalDate.now();
		int mes = data.getMonthValue();
		int ano = data.getYear();
		if (this.mesAnoEdicao[0] != mes) {
			return false;
		}
		if (this.mesAnoEdicao[1] != ano) {
			return false;
		}
		
		return true;
	}
}
