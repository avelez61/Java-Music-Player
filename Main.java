import java.util.Scanner;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import musicplayer.MusicPlayer;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MusicPlayer player = new MusicPlayer();
		
		boolean running = true;
		while (running) {
			System.out.print("Enter a command: ");
			String input = scanner.nextLine().toUpperCase();
			
			switch (input) {
				case "ADD SONG":
					System.out.print("Enter a song title: ");
					String title = scanner.nextLine();
					
					System.out.print("Enter the artist: ");
					String artist = scanner.nextLine();
					
					System.out.print("Enter the file path: ");
					String filePath = scanner.nextLine();
					
					player.addSong(title, artist, filePath);
					break;
					
				case "REMOVE SONG":
					System.out.print("Enter a song number: ");
					try {
						int index = Integer.parseInt(scanner.nextLine()) - 1;
						player.removeSong(index);
					} catch (Exception e) {
						System.out.println("Song number is not a number!");
					}
					break;
					
				case "SKIP":
					player.skipSong();
					break;
					
				case "PREVIOUS":
					player.previousSong();
					break;
					
				case "PLAY":
					player.play();
					break;
					
				case "PAUSE":
					player.stop();
					break;
					
				case "RESTART":
					player.restart();
					break;
					
				case "LIST":
					player.printList();
					break;
				
				case "EXIT":
					running = false;
					break;
					
				case "HELP":
					System.out.println("List of available commands:");
					System.out.println("ADD SONG, REMOVE SONG, SKIP, PREVIOUS, PLAY, PAUSE, RESTART, LIST, EXIT");
					break;
					
				default:
					System.out.println("Command not recognized, try typing in 'HELP'");
			}
		}
		
		scanner.close();
		player.close();
	}
}