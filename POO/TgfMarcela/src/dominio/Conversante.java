package dominio;

import java.nio.file.Path;

import org.json.JSONObject;

public interface Conversante {
	public String responder(String texto) throws Exception;
	public void prepare(Path[] arquivos);
}