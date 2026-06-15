package musicplayer;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {
	private Clip clip;
	
	public MusicPlayer(String filePath) {
		try {
			File soundFile = new File(filePath);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
			this.clip = AudioSystem.getClip();
			this.clip.open(audioStream);
		} catch(Exception e) {
			System.out.println("Error reading audio file");
		}
	}
	
	public void play() {
		this.clip.start();
	}
	
	public void stop() {
		this.clip.stop();
	}
	
	public void restart() {
		this.clip.setMicrosecondPosition(0);
		this.clip.start();
	}
	
	public void close() {
		this.clip.close();
	}
}