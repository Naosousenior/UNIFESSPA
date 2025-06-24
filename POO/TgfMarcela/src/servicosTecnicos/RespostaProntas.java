package servicosTecnicos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import dominio.Conversante;

public class RespostaProntas extends HashMap<String,String> implements Conversante{
	private static final long serialVersionUID = 1L;

	public RespostaProntas() {
		super();
	}
	
	@Override
	public String responder(String texto) throws Exception {
		// TODO Auto-generated method stub
		return this.get(texto);
	}
	
	/*
	 * esta classe e preparada com arquivos json
	 * estes arquivos contem uma sequencia de perguntas e respostas prontas
	 * tais perguntas e respostas foram obtidas do site da propria unifessa em:
	 * https://crca.unifesspa.edu.br/perguntas-frequentes_discente.html 
	 */
	@Override
	public void prepare(Path[] arquivos) {
		// TODO Auto-generated method stub
		//trato os possiveis casos de erro:
		if (arquivos == null) {return;}
		if (arquivos.length <= 0) {return;}
		
		for (Path p :arquivos) {
			this.carregaArquivo(p);
		}
	}
	
	private void carregaArquivo(Path path) {
		try {
			String texto_json = new String(Files.readAllBytes(path));
			
			JSONArray array = new JSONArray(texto_json);
			for (int i =0;i<array.length();i++) {
				JSONObject obj = array.getJSONObject(i);
				this.put(obj.getString("questao"), obj.getString("resposta"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Vector<String> getPerguntasProntas() {
		Vector<String> resultado = new Vector<String>(this.size());
		Set<String> chaves = this.keySet();
		
		for(String s :chaves) {
			resultado.add(s);
		}
		
		return resultado;
	}
}










