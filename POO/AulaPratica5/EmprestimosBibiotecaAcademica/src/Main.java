
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Revista r1 = new Revista(
				"Adley descobre nova linguagem de programacao",
				"q93ng9in4voiern",
				5,
				2025,
				9844559
			);
		
		Revista r2 = new Revista(
				"Erika inventa uma nova cor",
				"3o0invoeqnvov",
				2,
				2023,
				287292984
			);
		
		Livro l1 = new Livro(
				"Como rebolar leite pros crias",
				"qeirv9rvroqoero",
				"Adolfo Araújo",
				"???"
			);
		
		r1.emprestar();
		r2.emprestar();
		l1.emprestar();
		l1.emprestar();
		l1.emprestar();
		l1.emprestar();
		l1.emprestar();
	}

}
