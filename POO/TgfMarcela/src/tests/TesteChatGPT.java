package tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import dominio.Conversante;
import servicosTecnicos.ChatGPT;
import servicosTecnicos.Env;

public class TesteChatGPT {
	public static void main(String []args) {
		try {
			Env.load("src/assets/environment.env");
			Conversante r = new ChatGPT(Env.get("CHATGPT_KEY"));
			File pasta = new File("src/assets/tutoriais/");
			File[] arquivos = pasta.listFiles();
			Path[] paths = new Path[arquivos.length];
			for(int i = 0;i<arquivos.length;i++) {
				paths[i] = arquivos[i].toPath();
			}
			
			r.prepare(paths);
			System.out.println(r.responder("Como eu acesso os ambientes virtuais?"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
