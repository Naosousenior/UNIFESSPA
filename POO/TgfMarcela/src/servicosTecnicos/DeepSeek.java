package servicosTecnicos;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

import org.json.JSONObject;

import dominio.Respondendo;

public class DeepSeek implements Respondendo {
    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";
    private final String API_KEY;

    public DeepSeek(String apiKey) {
        this.API_KEY = apiKey;
    }

    public String responder(String text) throws Exception {
    	// Criar o objeto JSON para a requisição
    	JSONObject requestBody = new JSONObject();
    	requestBody.put("model", "deepseek/deepseek-chat:free");
    	requestBody.put("messages", new JSONObject[] {
    			new JSONObject().put("role", "user").put("content", text)
    			});
    	requestBody.put("temperature", 1);
    	requestBody.put("stream",false);

    	HttpClient client = HttpClient.newHttpClient();
    	HttpRequest request = HttpRequest.newBuilder()
    			.uri(new URI(DeepSeek.API_URL))
    			.header("Authorization", "Bearer " + this.API_KEY)
    			.header("Content-Type", "application/json")
    			.POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
    			.build();
        
    	HttpResponse<String> resposta = client.send(request, HttpResponse.BodyHandlers.ofString());
    	if(resposta.statusCode() != 200) {
    		throw new RuntimeException("Erro na requisição: " + resposta.statusCode() + " - " + resposta.body());
    		} else {
    			return new JSONObject(resposta.body())
    					.getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .get("content").toString();
            }
    }
        
    public void prepare(Path[] arquivos) {
    	
    }
}