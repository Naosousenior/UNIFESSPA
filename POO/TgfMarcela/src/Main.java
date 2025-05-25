
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		ChatBot bot = new ChatBot(ler.nextLine());
        FrontEnd front = new FrontEnd();
        front.setAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					front.setResponse(bot.getCompletion(front.getPrompt()));
				} catch (Exception exce) {
					exce.printStackTrace();
				}
			}
		});
        
		front.setVisible(true);
    }
}