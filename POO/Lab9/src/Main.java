public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conta c1 = new Conta(600,300);
		
		try {
			c1.deposita(0);
			
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			c1.deposita(-12000);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			c1.deposita(1000000000);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(c1.getLimite());
		System.out.println(c1.getSaldo());
	}

}