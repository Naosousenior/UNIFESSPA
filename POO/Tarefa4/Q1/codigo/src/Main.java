
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dev d1 = new Dev("Adolfo",60402023,"Go","Júnior");
		Dev d2 = new Dev("Emanuel",60402025,"C","Pleno");
		Dev d3 = new Dev("Marcela",60402019,"Java","Sênior");
		Dev d4 = new Dev("Notch",20302017,"JavaScript","Pleno");
		
		AdmRede a1 = new AdmRede("Rikelme", 45802022, "iurnvrnvornw", "Windows 10");
		AdmRede a2 = new AdmRede("Ana", 45802021,"aiernviunpoa","Debian");
		AdmRede a3 = new AdmRede("Jãozinho",123456,"ianervinsviarn","Arch Linux");
		
		Sistema sistema = new Sistema();
		sistema.addFuncionario(a1);
		sistema.addFuncionario(a2);
		sistema.addFuncionario(a3);
		sistema.listarFuncionarios();
		
		sistema.addFuncionario(d1);
		sistema.addFuncionario(d2);
		sistema.addFuncionario(d3);
		sistema.addFuncionario(d4);
		sistema.listarFuncionarios();
		
		a1.monitorarRede();
		a2.realizarBackup();
		a3.realizarBackup();
		a3.monitorarRede();
		
		Projeto p = d1.desenvolverProjeto(
				"Linux 2", 
				"A ideia é fazer um sistema baseado em Linux que seja estremamente melhorado\n"
				    +"Talvez totalmente focado em ter uma gestão melhor de pacotes e programas\n"
				    +"E com recursos de emulação de outros sistemas operacionais.\n"
				    +"Desta forma, seria sem duvida um projeto voltado para sistemas grandes."
				);
		
		d1.revisarCodigo(p);
		d1.atualizarProjeto(p);
		System.out.println(p.getNome());
		
		System.out.println("Informções técnicas de: "+d2.getNome());
		d2.exibirTecsUsadas();
		System.out.println("Informções técnicas de: "+d3.getNome());
		d3.exibirTecsUsadas();
		System.out.println("Informções técnicas de: "+d4.getNome());
		d4.exibirTecsUsadas();
	}

}
