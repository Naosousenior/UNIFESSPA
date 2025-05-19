public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String descricao = "Sejam bem vindos ao curso de magia de Hogwarts. Aos mais novos bruxos, deixo meu cumprimento.\n"
				+"Aqui vocês aprenderão sobre poções, transmutação de objetos, e defesa das artes das trevas.\nRecomendamos"
				+" fortemente tomarem cuidado durante as aulas de defesa das artes da trevas.\nAdemais, parabéns a todos que conseguiram entrar";
		Instrutor i1 = new Instrutor("McGonnagall", "Transmutação", "mcgonnagal@email.org", 30);
		Instrutor i2 = new Instrutor("Severo Snap", "Poções Mágicas", "severo@hotmail.com", 13);
		Instrutor i3 = new Instrutor("Remus Lupin", "Defesa das Artas das Trevas", "lupin@gmail.net", 1);
		
		Curso magiaHogwarts = new Curso("Magia em Hogwarts","Médio",descricao,1000);
		i3.ministrarAula(magiaHogwarts);
		
		magiaHogwarts.addInstrutor(i1);
		magiaHogwarts.addInstrutor(i2);
		magiaHogwarts.addInstrutor(i3);
		
		i1.ministrarAula(magiaHogwarts);
		i2.ministrarAula(magiaHogwarts);
	}
}