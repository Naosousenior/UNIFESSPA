import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;

public class FrontEnd extends JFrame{
	private JTextPane area = new JTextPane();
	private JButton botao = new JButton("Enviar");
	
	public FrontEnd() {
		super();
		
		this.setSize(new Dimension(800,800));
		this.setLayout(new BoxLayout(rootPane, BoxLayout.PAGE_AXIS));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        BoxLayout layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        
        this.area.setSize(this.getWidth(), this.getHeight()-50);
        this.area.setLocation(0,0);
        
        this.botao.setLocation(0, 0);
        this.botao.setSize(this.getWidth(), 50);

        this.setLayout(layout);
        this.add(this.area);
        this.add(this.botao);
	}
	
	public String getPrompt() {
		return this.area.getText();
	}
	public void setResponse(String response) {
		this.area.setText(response);
	}
	
	public void setAction(ActionListener a) {
		this.botao.addActionListener(a);
	}
}
