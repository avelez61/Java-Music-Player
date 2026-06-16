package musicplayer;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.ArrayList;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class MusicPlayer implements LineListener {
	private Clip clip;
	private boolean looping;
	private boolean paused;
	private boolean triggerAutoPlay;
	private ArrayList<Song> playList;
	private int currentSongIndex;
	
	
	public MusicPlayer(String filePath) {		
		this.clip = null;
		this.looping = false;
		this.paused = true;
		this.playList = new ArrayList<Song>();
		this.currentSongIndex = 0;
	}
	
	@Override
	public void update(LineEvent event) {
		
		if (event.getType() == LineEvent.Type.STOP && !paused && !looping) {
			currentSongIndex++;
			if (currentSongIndex < playList.size()) {
				triggerAutoPlay = true;
				play();
			}
		}
	}
	
	private void loadSong(Song song) {
		if (clip != null) {
			clip.close();
		}
		
		try {
			File soundFile = new File(song.getFilePath());
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
		} catch(Exception e) {
			System.out.println("Error reading file");
		}
		clip.addLineListener(this);
		clip.setLoopPoints(0, clip.getFrameLength() - 1);
		triggerAutoPlay = false;
	}
	
	public boolean isLooping() {
		return looping;
	}
	
	public void loop() {
		if (clip == null) { return; }
		
		if (!looping) {
			looping = true;
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		else {
			
			looping = false;
			clip.loop(0);
		}
	}
	
	public void play() {
		if (playList.isEmpty()) { return; }
		
		if (clip == null || triggerAutoPlay) {
			this.loadSong(playList.get(this.currentSongIndex));
		}
		clip.start();
		
		// Pausing clears loop status so it must be re-enabled in play
		if (looping) {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		else {
			clip.loop(0);
		}
		paused = false;
	}
	
	public void stop() {
		if (clip == null) { return; }
		clip.stop();
		paused = true;
	}
	
	public void restart() {
		if (clip == null) { return; }
		clip.setMicrosecondPosition(0);
		play();
	}
	
	public void skipForward() {
		if (clip == null) { return; }
		clip.setMicrosecondPosition(clip.getMicrosecondPosition() + 10 * (1000000));
		if (clip.getMicrosecondPosition() >= clip.getMicrosecondLength()) {
			clip.setMicrosecondPosition(0);
		}
	}
	
	public void skipBack() {
		if (clip == null) { return; }
		clip.setMicrosecondPosition(clip.getMicrosecondPosition() - 10 * (1000000));
		if (clip.getMicrosecondPosition() < 0) {
			clip.setMicrosecondPosition(0);
		}
	}
	
	public void addSong(String title, String artist, String filePath) {		
		playList.add(new Song(title, artist, filePath));
	}
	
	public void close() {
		if (clip == null) { return; }
		clip.close();
	}
}