import java.util.ArrayList;
import java.util.List;

public class Sistema {
	private List<FuncionarioTI> funcioanariosTI;
	public Sistema() {
		this.funcioanariosTI = new ArrayList<FuncionarioTI>();
	}
	
	public void addFuncionario(FuncionarioTI funcionario) {
		this.funcioanariosTI.add(funcionario);
	}
	
	public void listarFuncionarios() {
		System.out.println("Funcionarios do sistema:");
		for(FuncionarioTI f : this.funcioanariosTI) {
			f.exibirDados();
			System.out.println("\n");
		}
		System.out.println("--------------------------------------------------");
	}
}
