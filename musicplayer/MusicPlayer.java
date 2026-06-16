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
		return looping;
	}
	
	public void loop() {
		if (!looping) {
			looping = true;
		}
		else {
			
			looping = false;
		}
	}
	
	public void play() {
		clip.start();
		
		// Pausing clears loop status so it must be re-enabled in play
		if (looping) {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		else {
			clip.loop(0);
		}
	}
	
	public void stop() {
		clip.stop();
	}
	
	public void restart() {
		clip.setMicrosecondPosition(0);
		play();
	}
	
	public void skipForward() {
		clip.setMicrosecondPosition(clip.getMicrosecondPosition() + 10 * (1000000));
		if (clip.getMicrosecondPosition() >= clip.getMicrosecondLength()) {
			clip.setMicrosecondPosition(0);
		}
		System.out.println(clip.getMicrosecondPosition());
	}
	
	public void skipBack() {
		clip.setMicrosecondPosition(clip.getMicrosecondPosition() - 10 * (1000000));
		if (clip.getMicrosecondPosition() < 0) {
			clip.setMicrosecondPosition(0);
		}
		System.out.println(clip.getMicrosecondPosition());
	}
	
	public void close() {
		clip.close();
	}
}