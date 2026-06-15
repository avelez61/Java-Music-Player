import java.util.Scanner;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		try {
			File soundFile = new File("Problems.wav");
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			
			System.out.println("CLI Java Music App");
			
			boolean running = true;
			boolean playingSong = false;
			int pausedPosition = 0;
			
			
			while (running) {
				System.out.print("Enter a command: ");
				String input = scanner.nextLine().toUpperCase();
				
				if (input.equals("PLAY")) {
					if (!playingSong) {
						System.out.println("Started song");
						clip.setMicrosecondPosition(pausedPosition);
						clip.start();
						playingSong = true;
					}
				}
				else if (input.equals("PAUSE")) {
					System.out.println("Paused song");
					clip.stop();
					pausedPosition = (int) clip.getMicrosecondPosition();
					playingSong = false;
				}
				else if (input.equals("RESTART")) {
					System.out.println("Restarted song");
					clip.setMicrosecondPosition(0);
					clip.start();
					playingSong = true;
				}
				else if (input.equals("EXIT")) {
					running = false;
				}
			}
			
			clip.close();
		} catch (Exception e) {
			System.out.println("Error playing audio: " + e.getMessage());
		}
		
		scanner.close();
	}
}