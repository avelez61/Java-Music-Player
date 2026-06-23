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
		this.currentSongIndex = 0;
	}
	
	public int getCurrentSongIndex() {
		return currentSongIndex;
	}

	public void setCurrentSongIndex(int index) {
		currentSongIndex = index;
	}

	public void incrementIndex() {
		if (currentSongIndex < getSize() - 1) {
			currentSongIndex++;
		}
	}

	public void decrementIndex() {
		if (currentSongIndex > 0) {
			currentSongIndex--;
		}
	}

	public void addSong(Song song) {
		songs.add(song);
	}
	
	public void removeSong(int index) {
		if (isEmpty()) { return; }
		if (index < 0 || index >= getSize()) { return; }

		if (index < currentSongIndex) {
			decrementIndex();
		}
		songs.remove(index);
	}
	
	public Song getCurrentSong() {
		return songs.get(currentSongIndex);
	}
	
	public int getSize() {
		return songs.size();
	}
	
	public boolean isEmpty() {
		return songs.isEmpty();
	}
	
	public void printList() {
		if (songs.size() == 0) {
			System.out.println("Playlist Empty");
		}
		
		for (Song song : songs) {
			System.out.println("Title: " + song.getTitle() + " " + "Artist: " + song.getArtist());
		}
	}
}