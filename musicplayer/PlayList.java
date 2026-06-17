package musicplayer;

import java.util.ArrayList;

/*
	Wrapper class over an ArrayList
	The benefit of this class is that the underlying structure can
	change to something like a linked list for example and the implementation
	of the public interface does not need to change.
*/ 
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