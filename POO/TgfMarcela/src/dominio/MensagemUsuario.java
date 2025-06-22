package dominio;

import java.time.LocalDateTime;

public class MensagemUsuario extends Mensagem {
	
	public MensagemUsuario(String autor,String texto, LocalDateTime momento) {
		super(autor, texto,momento);
	}

	@Override
	public String prepareTexto() {
		// TODO Auto-generated method stub
		return "Meu nome é %s.\nMe diga:\n%s".formatted(this.autor,this.texto);
	}

}
