package dominio;

public interface Responder {
	public String responder(String texto) throws Exception;
	public void prepare(String role,String content);
}