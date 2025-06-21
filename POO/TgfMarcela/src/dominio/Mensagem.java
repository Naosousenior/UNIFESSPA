package dominio;

import java.time.LocalDateTime;

public abstract class Mensagem {
	protected String texto;
	protected String autor;
	protected LocalDateTime momento;
	
	protected Mensagem(String texto,String autor) {
		this.texto = texto;
		this.autor = autor;
	}
	
	public abstract String getTexto();
	public abstract void prepareTexto();
}
