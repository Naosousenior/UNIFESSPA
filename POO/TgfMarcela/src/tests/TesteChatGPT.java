package tests;

import java.io.IOException;

import dominio.Respondendo;
import servicosTecnicos.ChatGPT;
import servicosTecnicos.Env;

public class TesteChatGPT {
	public static void main(String []args) {
		try {
			Env.load("src/assets/chaves.env");
			Respondendo r = new ChatGPT(Env.get("CHATGPT_KEY"));
			System.out.println(r.responder("Eai meu chapa"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
