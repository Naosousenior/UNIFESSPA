package servicosTecnicos;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class ConexaoAPIIA {
	private final URI URL;
	private final String API_KEY;
	private JSONArray contexto;
	private HttpClient client;
	
	
	protected ConexaoAPIIA(String url,String key) throws URISyntaxException {
		this.URL = new URI(url);
		this.API_KEY = key;
		this.contexto = new JSONArray();
		
		this.client = HttpClient.newHttpClient();
	}
	
	//como cada API lida de uma forma diferente com arquivos de texto, e o contexto está em pdf
	//codificado com base 64, eu obrigo que cada classe concreta implemente este objeto
	protected abstract JSONObject criaJSONContext(String texto);
	
	protected JSONArray getContexto() {
		return this.contexto;
	}
    
	//recebe como argumento um array de Strings que guardam o conteúdo dos PDFs em base64
	protected void preparaContexto(String[] texto) {
		
		//para preparar o contexto, primeiro eu informo a IA o que esta acontecendo
		this.contexto.put(new JSONObject()
				.put("role", "assistant")
				.put("content", "Você é um assistente digital"
						+"que ajuda alunos a entenderem as funcionalidades da plataforma SIGAA da UNIFESSPA Marabá."
						+" Para isto, utilize os dados a seguir"
						)
			);
		
		//agora, crio um objeto array que guardara a lista de textos do contexto para a qual estou preparando a IA
		JSONArray objetosContexto = new JSONArray();
		
		//alimento a lista com o meotodo criaJSONContext implementado na classe concreta
		for(String t : texto) {
			objetosContexto.put(this.criaJSONContext(t));
		}
		
		this.contexto.put(new JSONObject()
				.put("role", "assistant")
				.put("content", objetosContexto)
			);
	}
	
	/*
	 * Sem duvidas um dos metodos mais dificeis
	 * ele recebe como argumento os dados preparados pelas classes concrectas em forma de JSON
	 * e tambem um objeto JSON com a mensagem
	 * insere dentro do objeto de dados o contexto preparado previamente
	 * assim como a mensagem escrita agora
	 * faz a requisicao HTTP para a API
	 * e retorna em texto o resultado da IA
	 */
	protected String fazRequisicao(JSONObject data) throws IOException,InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(this.URL)
				.header("Authorization", "Bearer " + API_KEY)
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(data.toString()))
				.build();
			
        HttpResponse<String> resposta = this.client.send(request, HttpResponse.BodyHandlers.ofString());
        if (resposta.statusCode() != 200) {
    		throw new RuntimeException("Erro na requisição: " + resposta.statusCode() + " - " + resposta.body());
		} else {
			return new JSONObject(resposta.body())
					.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .get("content").toString();
        }
	}
}
