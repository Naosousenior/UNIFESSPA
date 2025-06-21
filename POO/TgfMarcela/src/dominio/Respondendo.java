package dominio;

import java.nio.file.Path;

public interface Respondendo {
	public String responder(String texto) throws Exception;
	public void prepare(Path[] arquivos);
}