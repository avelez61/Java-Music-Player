package musicplayer;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class MusicPlayer implements LineListener {
	private Clip clip;
	private boolean paused;
	private boolean disableAutoPlay;
	private boolean triggerAutoPlay;
	private PlayList playList;
	private int currentSongIndex;
	
	
	public MusicPlayer() {		
		this.clip = null;
		this.paused = true;
		this.playList = new PlayList();
		this.currentSongIndex = 0;
		this.disableAutoPlay = false;
	}
	
	@Override
	public void update(LineEvent event) {
		if (event.getType() == LineEvent.Type.STOP && !paused && !disableAutoPlay) {
			currentSongIndex++;
			if (currentSongIndex < playList.getSize()) {
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
			clip.addLineListener(this);
		} catch(Exception e) {
			System.out.println("Error reading file");
		}
		triggerAutoPlay = false; // may not be necessary after called by skipSong
	}
	
	public void addSong(String title, String artist, String filePath) {		
		playList.addSong(new Song(title, artist, filePath));
	}
	
	public void removeSong(int index) {
		if (playList.isEmpty()) { return; }
		if (index < 0 || index >= playList.getSize()) { return; }
		
		disableAutoPlay = true;
		if (index == currentSongIndex) {
			clip.close();
			clip = null;
			currentSongIndex = 0;
		}
		else if (index < currentSongIndex) {
			currentSongIndex--;
		}
		disableAutoPlay = false;
		
		playList.removeSong(index);
	}
	
	public void skipSong() {
		if (playList.isEmpty()) { return; }
		if (clip == null) { return; }
		
		disableAutoPlay = true; // Disable autoplay to prevent clip.close from calling update
		clip.close();
		clip = null;
		
		if (currentSongIndex < playList.getSize() - 1) {
			currentSongIndex++;
			play();
		}
		disableAutoPlay = false;
	}
	
	public void previousSong() {
		if (playList.isEmpty()) { return; }
		if (clip == null) { return; }
		
		disableAutoPlay = true;  // Disable autoplay to prevent clip.close from calling update
		clip.close();
		clip = null;
		
		if (currentSongIndex > 0) {
			currentSongIndex--;
			play();
		}
		
		disableAutoPlay = false;
	}
	
	public void play() {
		if (playList.isEmpty()) { return; }
		
		if ((clip == null || triggerAutoPlay)) {
			this.loadSong(playList.getSong(this.currentSongIndex));
		}
		clip.start();
		paused = false;
	}
	
	public void stop() {
		if (playList.isEmpty()) { return; }
		if (clip == null) { return; }
		
		paused = true;
		clip.stop();
	}
	
	public void restart() {
		if (playList.isEmpty()) { return; }
		if (clip == null) { return; }
		
		clip.setMicrosecondPosition(0);
		play();
	}
	
	public void printList() {
		playList.printList();
	}
	
	public void close() {
		if (clip == null) { return; }
		clip.close();
	}
}