
public class Conta {
	private double saldo;
	private double limite;
	
	public Conta(double saldo, double limite) {
		this.saldo = saldo;
		this.limite = limite;
	}
	
	public void deposita(double valor) throws IllegalArgumentException {
		if (valor <= 0) {
			throw new IllegalArgumentException("O valor depositado é inválido, deve ser positivo.");
		}
		
		this.saldo += valor;
	}
	
	public double getSaldo() {
		return this.saldo;
	}
	public double getLimite() {
		return this.limite;
	}
	
	
}
