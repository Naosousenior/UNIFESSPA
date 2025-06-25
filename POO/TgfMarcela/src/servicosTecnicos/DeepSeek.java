package servicosTecnicos;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

import org.json.JSONArray;
import org.json.JSONObject;

import dominio.Conversante;

public class DeepSeek extends ConexaoAPIIA implements Conversante {
	public DeepSeek(String chave) throws URISyntaxException {
        super("https://openrouter.ai/api/v1/chat/completions",chave);
    }
	
	private String removeMarkdown(String markdownText) {
        if (markdownText == null || markdownText.isEmpty()) {
            return markdownText;
        }

        String result = markdownText;

        // Cabeçalhos: #, ##, ### etc.
        result = result.replaceAll("^#+\\s*(.*)", "$1");
        result = result.replaceAll("  ", "\n");
        // Negrito: **texto** ou __texto__
        result = result.replaceAll("\\*\\*(.*?)\\*\\*|__(.*?)__", "$1$2");
        // Itálico: *texto* ou _texto_
        result = result.replaceAll("\\*(.*?)\\*|_(.*?)_", "$1$2");
        // Links: [texto](url)
        result = result.replaceAll("\\[(.*?)\\]\\(.*?\\)", "$1");
        // Imagens: ![alt](src)
        result = result.replaceAll("!\\[(.*?)\\]\\(.*?\\)", "$1");
        // Blocos de código: ```código``` ou `código`
        result = result.replaceAll("```.*?```|`.*?`", "");
        // Linhas horizontais: ---, ***, ___
        result = result.replaceAll("^[-\\*_]{3,}\\s*$", ""); // Usa $ para garantir que pegue a linha inteira
        // Citações: > texto
        result = result.replaceAll("^>\\s*", "");
        // Listas: -, *, + seguido de espaço
        result = result.replaceAll("^[\\*\\-\\+]\\s+", "");
        // Tabelas (básico): |---| --- | etc. - Remover linhas da tabela.
        // Isso é mais complexo, pois pode remover conteúdo. Para simplicidade, vamos remover as barras.
        // Dependendo do que você quer remover, pode ser melhor reavaliar.
        result = result.replaceAll("\\|", " "); // Substitui barras por espaços

        // HTML básico (opcional)
        result = result.replaceAll("<.*?>", "");
        
        // Remove espaços extras e limpa o resultado final
        // Remove múltiplas quebras de linha para no máximo duas
        result = result.replaceAll("\\n{3,}", "\n\n");
        // Remove espaços no início e fim de cada linha, e múltiplos espaços
        result = result.replaceAll(" +", " ").trim();


        return result;
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
    	
    	String respostaComMarkDown = resposta.getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");
    	
    	return this.removeMarkdown(respostaComMarkDown);
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