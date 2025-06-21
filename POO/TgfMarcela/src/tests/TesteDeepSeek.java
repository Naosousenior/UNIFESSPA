package tests;

import dominio.Respondendo;
import servicosTecnicos.DeepSeek;
import servicosTecnicos.Env;

public class TesteDeepSeek {
	public static void main(String[] args) {
		try {
			Env.load("src/assets/chaves.env");
			System.out.println(Env.get("DEEPSEEK_KEY"));
			Respondendo deepseek = new DeepSeek(Env.get("DEEPSEEK_KEY"));
			System.out.println(deepseek.responder("Eai meu chapa"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
