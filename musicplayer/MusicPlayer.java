package musicplayer;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {
	private Clip clip;
	private boolean looping;
	
	public MusicPlayer(String filePath) {
		try {
			File soundFile = new File(filePath);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
			this.clip = AudioSystem.getClip();
			this.clip.open(audioStream);
		} catch(Exception e) {
			System.out.println("Error reading audio file");
		}		
		this.clip.setLoopPoints(0, this.clip.getFrameLength() - 1);
		this.looping = false;
	}
	
	public boolean isLooping() {
		return this.looping;
	}
	
	public void loop() {
		if (!this.looping) {
			this.looping = true;
		}
		else {
			
			this.looping = false;
		}
	}
	
	public void play() {
		this.clip.start();
		
		// Pausing clears loop status so it must be re-enabled in play
		if (this.looping) {
			this.clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		else {
			this.clip.loop(0);
		}
	}
	
	public void stop() {
		this.clip.stop();
	}
	
	public void restart() {
		this.clip.setMicrosecondPosition(0);
		this.play();
	}
	
	public void close() {
		this.clip.close();
	}
}