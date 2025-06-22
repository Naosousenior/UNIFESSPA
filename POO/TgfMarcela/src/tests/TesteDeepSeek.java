package tests;

import dominio.Conversante;
import servicosTecnicos.DeepSeek;
import servicosTecnicos.Env;

public class TesteDeepSeek {
	public static void main(String[] args) {
		try {
			Env.load("src/assets/environment.env");
			System.out.println(Env.get("DEEPSEEK_KEY"));
			Conversante deepseek = new DeepSeek(Env.get("DEEPSEEK_KEY"));
			System.out.println(deepseek.responder("Eai meu chapa"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
