package musicplayer;

import java.util.ArrayList;

public class PlayList {
	private ArrayList<Song> songs;
	private int currentSongIndex;
	
	public PlayList() {
		this.songs = new ArrayList<Song>();
	}
	
	public void addSong(Song song) {
		songs.add(song);
	}
	
	public void removeSong(int index) {
		songs.remove(index);
	}
	
	public Song getSong(int index) {
		return songs.get(index);
	}
	
	public int getSize() {
		return songs.size();
	}
	
	public boolean isEmpty() {
		return songs.isEmpty();
	}
}