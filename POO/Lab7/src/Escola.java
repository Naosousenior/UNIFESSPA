import java.util.ArrayList;
import java.util.List;

public class Escola {
	private List<Funcionario> funcionarios;
	
	public Escola() {
		this.funcionarios = new ArrayList<Funcionario>();
	}
	
	public void registraPresenca(PresencaRegistravel p) {
		p.registraPresenca();
	}
	
	public void adicionarFuncionario(Funcionario f) {
		f.exibirDados();
		funcionarios.add(f);
	}
}
