package tests;

import java.io.File;
import java.nio.file.Path;

import dominio.Conversante;
import servicosTecnicos.DeepSeek;
import servicosTecnicos.Env;

public class TesteDeepSeek {
	public static void main(String[] args) {
		try {
			Env.load("src/assets/environment.env");
			Conversante r = new DeepSeek(Env.get("DEEPSEEK_KEY"));
			File pasta = new File("src/assets/tutoriais/");
			File[] arquivos = pasta.listFiles();
			Path[] paths = new Path[arquivos.length];
			for(int i = 0;i<arquivos.length;i++) {
				paths[i] = arquivos[i].toPath();
			}
			
			r.prepare(paths);
			System.out.println(r.responder("Como eu acesso os ambientes virtuais?"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
