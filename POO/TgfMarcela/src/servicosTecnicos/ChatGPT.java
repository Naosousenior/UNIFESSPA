package servicosTecnicos;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

import org.json.JSONArray;
import org.json.JSONObject;

import dominio.Conversante;


public class ChatGPT extends ConexaoAPIIA implements Conversante{
	
	public ChatGPT(String chave) throws URISyntaxException {
		super("https://api.openai.com/v1/chat/completions",chave);
		
	}
	
	@Override
	protected JSONObject criaJSONContext(String texto) {
		// TODO Auto-generated method stub
		char[] nomeChar = new char[10];
		texto.getChars(0, 10, nomeChar, 0);
		String nome = String.valueOf(nomeChar);
		return new JSONObject().put("type", "file").put("file",new JSONObject().put("filename", nome+".pdf").put("file_data", "data:application/pdf;base64,"+texto));
	}
	
	public String responder(String prompt) throws Exception {
        JSONObject data = new JSONObject();
        data.put("model", "gpt-4-turbo");
        data.put("max_tokens", 4000);
        data.put("temperature", 1.0);
        
        JSONObject promptJSON = new JSONObject()
                .put("role", "user")
                .put("content", prompt);
        
        JSONArray contexto = this.getContexto();
        JSONArray mensagens = new JSONArray();
        
        for (int i = 0; i<contexto.length();i++) {
        	mensagens.put(contexto.get(i));
        }
        
        mensagens.put(promptJSON);
        
        data.put("messages",mensagens);
        
        return fazRequisicao(data);
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

		
		
		
		
		
		
		
		
