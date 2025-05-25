import java.util.Random;

public class Dev extends FuncionarioTI {
	private String mainLang;
	private String nivel;
	
	public Dev(String nome,int matricula,String mainLang, String nivel) {
		super(nome,matricula);
		
		this.mainLang = mainLang;
		this.nivel = nivel;
	}
	
	public void exibirDados() {
		super.exibirDados();
		System.out.println("Linguagem principal: "+this.mainLang);
		System.out.println("Nível de experiência: "+this.nivel);
	}
	
	public Projeto desenvolverProjeto(String nome,String descricao) {
		return new Projeto(nome,descricao,this);
	}
	
	public void atualizarProjeto(Projeto projeto) {
		projeto.setNome(projeto.getNome()+"-Versão Final");
	}
	
	public void exibirTecsUsadas() {
		switch(this.mainLang) {
		case "Python":
			System.out.println("IDE utilizada: PyCharm");
			System.out.println("Frameworks favoritos: DJango, Kivy, Anaconda");
			break;
		case "C":
			System.out.println("IDE utilizada: Dev Cpp");
			System.out.println("Bibliotecas favoritas: SDL, GLib");
			break;
		case "C++":
			System.out.println("IDE utilizada: VS Code");
			System.out.println("Bibliotecas favorititas: Qt");
			break;
		case "JavaScript":
			System.out.println("IDE utilizada: Visual Studio");
			System.out.println("Frameworks favoritos: NodeJS, React");
			break;
		case "Java":
			System.out.println("IDE utilizada: NetBeans");
			System.out.println("Bibliotecas favoritas: OpenFX, Jakarta");
			break;
		case "Go":
			System.out.println("IDE utilizada: VS Code");
			System.out.println("Bibliotecas favoritas: Echo, RayLib");
		}
	}
	
	public void revisarCodigo(Projeto projeto) {
		System.out.println("-----------------------------------------------------");
		System.out.println("Revisando o código de um projeto");
		System.out.println(projeto.relatorio());
		Random random = new Random();
		
		if (random.nextBoolean()) {
			System.out.println("O projeto parece ótimo!!");
		} else {
			System.out.println("O projeto não parece muito bom");
		}
		System.out.println("-----------------------------------------------------");
	}

}
