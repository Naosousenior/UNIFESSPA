package tests;

import java.nio.file.Path;
import java.nio.file.Paths;

import dominio.Respondendo;
import servicosTecnicos.RespostaProntas;

public class TesteRespostasProntas {
	public static void main(String[] args) {
		Respondendo respo = new RespostaProntas();
		Path[] arquivos = new Path[1];
		arquivos[0] = Paths.get("src/assets/perguntas_respostas.json");
		
		respo.prepare(arquivos);
		
		try {
			System.out.println(respo.responder("Qual a diferença entre Habilitação e Matrícula?"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
