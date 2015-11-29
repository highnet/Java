public class digitSum {

	public static int calcCrossSum(int value) {
		int digitSum = 0;
		int lastDigit = 0;

		while (value > 0) {
			lastDigit = value % 10; // letzte Ziffer der Zahl finden.
			digitSum += lastDigit; // diese Ziffer auf unserer digitSum summieren.
			value /= 10; // letzte ziffer wegschneiden.
		}

		return digitSum;
	}
	
	private static int calcCrossSumFactorial(int n) {
		if ( n == 0 ){
			return 0;
		}
		else return n % 10 + calcCrossSumFactorial(n / 10);
	}


	public static void main(String[] args) {
		
		System.out.println(calcCrossSum(12345));
		System.out.println(calcCrossSumFactorial(12345));
		
		
	}
}
