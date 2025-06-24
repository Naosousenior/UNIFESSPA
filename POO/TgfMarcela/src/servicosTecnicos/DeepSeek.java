package servicosTecnicos;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

import org.json.JSONArray;
import org.json.JSONObject;

import dominio.Conversante;

public class DeepSeek extends ConexaoAPIIA implements Conversante {
	public DeepSeek(String chave) throws URISyntaxException {
        super("https://openrouter.ai/api/v1/chat/completions",chave);
    }
	
	@Override
	protected JSONObject criaJSONContext(String texto) {
		// TODO Auto-generated method stub
		char[] nomeChar = new char[10];
		texto.getChars(0, 10, nomeChar, 0);
		String nome = String.valueOf(nomeChar);
		return new JSONObject()
				.put("type", "file")
				.put("file",
						new JSONObject()
						.put("filename", nome+".pdf")
						.put("file_data", "data:application/pdf;base64,"+texto)
					);
	}

    public String responder(String text) throws Exception {
    	// Criar o objeto JSON para a requisição
    	JSONObject data = new JSONObject();
    	data.put("model", "deepseek/deepseek-r1-0528-qwen3-8b:free");
    	data.put("temperature", 1);
    	data.put("stream",false);

    	JSONObject promptJSON = new JSONObject().put("role", "user").put("content", text);
    	JSONArray contexto = this.getContexto();
        JSONArray mensagens = new JSONArray();
        
        for (int i = 0; i<contexto.length();i++) {
        	mensagens.put(contexto.get(i));
        }
        
        mensagens.put(promptJSON);
        
        data.put("messages",mensagens);
        data.put("plugins", 
        		new JSONArray()
        		.put(
        				new JSONObject()
        				.put("id", "file-parser")
        				.put("pdf", 
        						new JSONObject()
        						.put("engine", "pdf-text")
        						)
        				)
        		);
    	
    	JSONObject resposta = this.fazRequisicao(data);
    	
    	return resposta.getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");
    }
        
    public void prepare(Path[] arquivos) {
    	String[] textosBase = new String[arquivos.length];
		for(int i = 0;i<arquivos.length;i++) {
			try {
				textosBase[i] = Encodador.toBase64(arquivos[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.preparaContexto(textosBase);
    }
}