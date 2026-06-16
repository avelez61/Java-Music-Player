import java.util.Scanner;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import musicplayer.MusicPlayer;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MusicPlayer player = new MusicPlayer("Problems.wav");
		
		boolean running = true;
		while (running) {
			System.out.print("Enter a command: ");
			String input = scanner.nextLine().toUpperCase();
			
			if (input.equals("PLAY")) {
				System.out.println("Started song");
				player.play();
			}
			else if (input.equals("PAUSE")) {
				System.out.println("Paused song");
				player.stop();
			}
			else if (input.equals("RESTART")) {
				System.out.println("Restarted song");
				player.restart();
			}
			else if (input.equals("LOOP")) {
				if (!player.isLooping()) {
					System.out.println("Enabled Looping");
				}
				else {
					System.out.println("Disabled Looping");
				}
				player.loop();
			}
			else if (input.equals("SKIP FORWARD")) {
				System.out.println("Skipped Forward 10 Seconds");
				player.skipForward();
			}
			else if (input.equals("SKIP BACK")) {
				System.out.println("Skipped Back 10 Seconds");
				player.skipBack();
			}
			else if (input.equals("ADD SONG")) {
				System.out.print("Enter a song title: ");
				String title = scanner.nextLine();
				
				System.out.print("Enter the artist: ");
				String artist = scanner.nextLine();
				
				System.out.print("Enter the file path: ");
				String filePath = scanner.nextLine();
				
				System.out.println("Added Song");
				player.addSong(title, artist, filePath);
			}
			else if (input.equals("REMOVE SONG")) {
				System.out.print("Enter a song number: ");
				int index = Integer.parseInt(scanner.nextLine()) - 1;
				
				System.out.println("Removed Song");
				player.removeSong(index);
			}
			else if (input.equals("SKIP")) {
				System.out.println("Skipped Song");
				player.skipSong();
			}
			
			else if (input.equals("EXIT")) {
				running = false;
			}
		}
		
		scanner.close();
		player.close();
	}
}