public class secretDigits {

	private static long getSecretDigit(long n) {

		long lastDigit = 0;

		while (n > 0) {
			System.out.println("n = " + n);
			lastDigit = n % 10;
			System.out.println("Last Digit = " + lastDigit);

			n /= powerAB(10, lastDigit); // n /= Math.pow(10, lastDigit);

			System.out.println("Removing " + lastDigit + " from n");
			System.out.println();

		}

		return lastDigit;
	}
	

	private static long powerAB(long a, long b) {
		long answer = 1;

		for (int i = 0; i < b; i++) {

			answer *= a;

		}

		return answer;
	}

	public static void main(String[] args) {

		long n = 1238593L;

		System.out.println("Secret Digit is: " + getSecretDigit(n));

	}

}
