public class Factorial {

	public static boolean isFacultyDiv(int n, int div) {
		int factorialAnswer = 1;

		for (int i = 1; i <= n; i++) {

			factorialAnswer *= i;

		}

		if (factorialAnswer % div == 0) {
			return true;
		}

		return false;
	}
	
	public static boolean isFacultyDivRecursive(int n, int div){
		
		if (nFactorialRecursive(n) % div == 0){
			return true;
		}
		
		return false;
		
	
	}

	private static int nFactorialRecursive(int n) {
		
		if (n == 0){
			return 1;
		}
		else return n * nFactorialRecursive(n-1);
	}

	public static void main(String[] args) {
		if (isFacultyDivRecursive(10, 10) == true) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}
}
