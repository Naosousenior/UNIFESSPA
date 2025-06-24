package dominio;

import java.time.LocalDateTime;

public class MensagemUsuario extends Mensagem {
	
	public MensagemUsuario(String autor,String texto) {
		super(autor, texto,LocalDateTime.now());
	}
	
	public MensagemUsuario(String autor,String texto,LocalDateTime momento) {
		super(autor, texto,momento);
	}

	@Override
	public String prepareTexto() {
		// TODO Auto-generated method stub
		return "Com base em todos os dados, resposta o texto:\n\n %s".formatted(this.autor,this.texto);
	}

}
