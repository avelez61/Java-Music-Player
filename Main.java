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
				System.out.println("Added Song");
				player.addSong("Problems", "Alterclad", "Problems.wav");
			}
			else if (input.equals("ADD SONG 2")) {
				System.out.println("Added Song");
				player.addSong("Ninomae Ina'nis", "Tako Takeover", "TakoTakeover.wav");
			}
			else if (input.equals("ADD SONG 3")) {
				System.out.println("Added Song");
				player.addSong("Nerrisa Ravencroft", "Alterclad", "Birdbrain.wav");
			}
			
			else if (input.equals("EXIT")) {
				running = false;
			}
		}
		
		scanner.close();
		player.close();
	}
}