
public class Secretaria extends Funcionario implements PresencaRegistravel{
	private String turno;
	
	public Secretaria(String nome,String turno) {
		super(nome);
		
		this.turno = turno;
	}
	@Override
	public void exibirDados() {
		// TODO Auto-generated method stub
		System.out.println("Funcionário: secretária");
		System.out.printf("Nome: %s\nTurno: %s\n",this.nome,this.turno);
	}

	public void organizarDocumentos() {
		System.out.printf("%s esta a organizar os documentos a respeito do desvio de 50.000\n",this.nome);
	}
	
	@Override
	public void registraPresenca() {
		// TODO Auto-generated method stub
		System.out.println(this.nome+" esta registrando presenca no seu turno da "+this.turno);
	}
}
