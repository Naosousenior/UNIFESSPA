package dominio;

import java.time.LocalDateTime;

public class MensagemTexto extends Mensagem {

	public MensagemTexto(String autor,String texto) {
		super(autor, texto,LocalDateTime.now());
	}
	
	public MensagemTexto(String autor,String texto, LocalDateTime momento) {
		super(autor, texto,momento);
	}
	
	@Override
	public String prepareTexto() {
		// TODO Auto-generated method stub
		return this.texto;
	}

}