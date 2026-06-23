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
			playList.incrementIndex();
			triggerAutoPlay = true;
			play();
			triggerAutoPlay = false; 
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
	}

	private void unloadSong() {
		if (clip == null) { return; }

		disableAutoPlay = true; // Disable autoplay to prevent clip.close from calling update
		clip.close();
		clip = null;
		disableAutoPlay = false;
	}
	
	public void addSong(String title, String artist, String filePath) {		
		playList.addSong(new Song(title, artist, filePath));
	}
	
	public void removeSong(int index) {
		if (index == playList.getCurrentSongIndex()) {
			unloadSong();
			playList.setCurrentSongIndex(0);
		}
		
		playList.removeSong(index);
	}
	
	public void skipSong() {
		if (playList.isEmpty()) { return; }
		
		unloadSong();
		playList.incrementIndex();
		play();
	}
	
	public void previousSong() {
		if (playList.isEmpty()) { return; }
		
		unloadSong();
		playList.decrementIndex();
		play();
	}
	
	public void play() {
		if (playList.isEmpty()) { return; }
		
		if ((clip == null || triggerAutoPlay)) {
			loadSong(playList.getCurrentSong());
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