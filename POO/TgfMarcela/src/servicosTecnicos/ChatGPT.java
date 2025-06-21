package servicosTecnicos;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

import org.json.JSONArray;
import org.json.JSONObject;

import dominio.Respondendo;


public class ChatGPT implements Respondendo{
	private static final String API_URL = "https://api.openai.com/v1/chat/completions";
	private final String API_KEY;
	
	public ChatGPT(String chave) {
		this.API_KEY = chave;
	}
	
	public String responder(String prompt) throws Exception {
        JSONObject data = new JSONObject();
        data.put("model", "gpt-3.5-turbo");
        data.put("messages", new JSONArray()
            .put(new JSONObject()
                .put("role", "user")
                .put("content", prompt)
            )
        );
        data.put("max_tokens", 4000);
        data.put("temperature", 1.0);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(ChatGPT.API_URL))
            .header("Authorization", "Bearer " + API_KEY)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(data.toString()))
            .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro na requisição: " + response.statusCode() + " - " + response.body());
        }else{
            return new JSONObject(response.body())
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .get("content").toString();
        }
    }
	
	public void prepare(Path[] arquivos) {
		
	}
}

		
		
		
		
		
		
		
		
