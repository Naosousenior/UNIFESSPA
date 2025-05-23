import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public abstract class Animal {
	protected String nome;
	protected File arquiAudio;
	
	public Animal(String nome,String caminho) {
		this.nome = nome;
		this.arquiAudio = new File("/home/adolfo/Documentos/projetos/UNIFESSPA/POO/Lab6/src/audios/"+caminho);
	}
	
	public void emitirSom() {
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(this.arquiAudio);
			Clip clip = AudioSystem.getClip();
			
			clip.open(audio);
			clip.start();
			System.out.println(this.nome + " esta fazendo som");
			Thread.sleep(clip.getMicrosecondLength()/1000);
			clip.stop();
			clip.close();
			audio.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract void mover();
}