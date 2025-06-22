package dominio;

import java.time.LocalDateTime;

public class MensagemPronta extends Mensagem {

	public MensagemPronta(String autor,String texto, LocalDateTime momento) {
		super(autor, texto,momento);
	}
	
	@Override
	public String prepareTexto() {
		// TODO Auto-generated method stub
		return this.texto;
	}

}
