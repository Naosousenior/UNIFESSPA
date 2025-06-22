package dominio;

import java.time.LocalDateTime;

public abstract class Mensagem {
	protected String texto;
	protected String autor;
	protected LocalDateTime momento;
	
	protected Mensagem(String autor,String texto,LocalDateTime momento) {
		this.texto = texto;
		this.autor = autor;
		this.momento = momento;
	}
	
	public String getAutor() {
		return this.autor;
	}
	
	public LocalDateTime getDataTime() {
		return this.momento;
	}
	
	public String getTexto() {
		return this.texto;
	}
	
	public abstract String prepareTexto();
}
