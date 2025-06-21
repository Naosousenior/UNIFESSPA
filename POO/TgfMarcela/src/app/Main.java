package app;

import java.io.IOException;

import servicosTecnicos.Env;

public class Main {
	public static void main(String[] args) {
		try {
			Env.load("src//assets/chaves.env");
			System.out.println("Chaves API:\nDeepSeek: %s\nChatGPT:%s".formatted(Env.get("DEEPSEEK_KEY"),Env.get("CHATGPT_KEY")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
}