import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("CLI Java Music App");
		
		boolean running = true;
		while (running) {
			System.out.print("Enter a command: ");
			String input = scanner.nextLine().toUpperCase();
			
			if (input.equals("EXIT")) {
				running = false;
			}
		}
		
		scanner.close();
	}
}