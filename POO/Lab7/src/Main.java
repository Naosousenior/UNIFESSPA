public class Main {
	
	public static void main(String[] args) {
		Funcionario f1 = new Diretor("Diego Kasuo","Tecnologia");
		Funcionario f2 = new Professor("Mariana","Sistema Embarcados");
		Funcionario f3 = new Secretaria("Anahi Colucci","noite");
		Terceirizado f4 = new Terceirizado("Aninha");
		
		Escola e = new Escola();
		e.adicionarFuncionario(f1);
		e.adicionarFuncionario(f2);
		e.adicionarFuncionario(f3);
		e.registraPresenca(f4);
	}
}