public class encodeNumbers {

	private static void encodeBin(long n) {

		boolean findNewTarget = true;
		int counter = 1;
		int currentTarget = 0;
		long userInput = n; // Convert 'n' into an 'int userInput'

		System.out.println("[Msg] Die Zahl \"" + userInput
				+ "\" Generiert folgenden codierten Output:"); // user
		System.out.println();

		while (userInput > 0) {

			if (findNewTarget == true) {
				currentTarget = (int) (userInput % 10);
				findNewTarget = false;
			}

			// System.out.println("[Dev] Current Target is " + currentTarget);

			if (userInput / 10 % 10 == currentTarget) {
				counter++;
			}

			if (userInput / 10 % 10 != currentTarget) {
				findNewTarget = true;
				System.out.println(currentTarget + " -> " + counter);
				counter = 1;
			}

			userInput /= 10;

		}
		System.out.println();
		return;

	}
	
	private static Object encodeBinRecursively(long userInput, int currentTarget, int counter, boolean findNewTarget, boolean printMessage) {
		
		counter++;
		
		if (userInput == 0){
			return 0;
		}
		
		if (findNewTarget == true) {
			currentTarget = (int) (userInput % 10);
		}
		
		if (printMessage == true){
			System.out.println("[Msg] Die Zahl \"" + userInput + "\" Generiert folgenden codierten Output:"); // user
			System.out.println();
		}
		
		if (userInput / 10 % 10 == currentTarget) {
			return encodeBinRecursively(userInput / 10, currentTarget, counter++, false, false);
		}
		
		else if (userInput / 10 % 10 != currentTarget) {
			System.out.println(currentTarget + " -> " + counter);
			return encodeBinRecursively(userInput / 10, currentTarget, 0, true, false);
		}
		return null;
	}
	
	public static void main(String[] args) {
		
		int userInput = 122333444;
		int lastDigit = userInput % 10;
		int counter = 0;
		boolean findNewTarget = false;
		boolean printMessage = true;

		encodeBin(userInput); // Prints an encoded binary system.
		encodeBinRecursively(userInput,lastDigit,counter,findNewTarget,printMessage);

	}



	
}
