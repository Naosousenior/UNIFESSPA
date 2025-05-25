
public class AdmRede extends FuncionarioTI {
	private String certificacao;
	private String soResponsavel;
	
	public AdmRede(String nome, int matricula, String certificado,String so) {
		super(nome,matricula);
		
		this.certificacao = certificado;
		this.soResponsavel = so;
	}
	
	public void monitorarRede() {
		System.out.println("Rede de internet com sistema "+this.soResponsavel+" sendo monitorada");
	}
	public void realizarBackup() {
		System.out.println("Realizando backup do sistema "+this.soResponsavel);
	}
	
	public String getCertificado() {
		return this.certificacao;
	}
}
