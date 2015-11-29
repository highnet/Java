import java.util.Scanner;

public class GuessingGame {

	public static void main(String[] args) {

		int attempts = 3;
		int answer = 4;
		int userInput = 0;

		Scanner sc = new Scanner(System.in);

		while (attempts != 0) {
			System.out.println("What is your guess?");
			
			
			if (sc.hasNextInt()){
				userInput = sc.nextInt();
			if (userInput == answer) {
				System.out.println("You won");
				break;	
			}
			
			else if (userInput == answer-1 || userInput == answer+1){
				System.out.println("Very close, free attempt earned");
			}
			else {
				System.out.println("Try again");
				attempts--;
				System.out.println("Attempts = " + attempts);
			}
		}
			else {
				System.out.println("ERROR: No Integer");
				sc.next();
			}

		if (attempts == 0) {
			System.out.println("You lost");
		}
	}

}
	
}
