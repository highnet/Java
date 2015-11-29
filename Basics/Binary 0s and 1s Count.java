public class binaryCounting {

	/*
	 * 1 & 1 = 1 1 & 0 = 0
	 * 
	 * beispiel:
	 * 
	 * p=101 101 & 1 = 1 // Onecounter++
	 * 
	 * 101 >> 1 == 10
	 * 
	 * 10 & 1 = 0 // !onecounter++
	 * 
	 * 10 >> 1 == 1
	 * 
	 * 1 & 1 == 1 // onecounter++
	 */
	private static int einsen(long p) {
		p = (p < 0 ? -p : p);
		if (0 == p)
			return 0;

		int Count = 0;
		for (; p > 0; p >>= 1) { // for loop ist hier schlecht. weil es uns egal
									// ist wie viele iterationen wir durchgehen.
			if (1 == (p & 1)) {
				Count++;
			}
		}
		return Count;

	}
	
	private static long einsenFactorial(long p){
		System.out.println("P: " + p);

		if (p < 0){
			return -p;
		}
		
		if (p == 0){
			return 0;
		}
		
		if ((p & 1) == 1){
			return 1 + einsenFactorial(p >> 1);
		}
		
		return einsenFactorial( p >> 1);
	
	}
	
	private static long nullenFactorial(long p){
		
		System.out.println("P: " + p);
		if (p < 0){
			return -p;
		}
		
		if (p == 0){
			System.out.println ("Count++");
			return 1;
		}
		
		if ((p & 1) == 0){
			System.out.println ("Count++");
			return 1 + einsenFactorial(p >> 1);
			
		}
		
		return einsenFactorial( p >> 1);
	
	}

	private static int nullen(long p) {

		p = (p < 0 ? -p : p);
		if (0 == p)
			return 1;
		int Count = 0;
		while (p > 0) {
			if (0 == (p & 1))
				Count++;
			p >>= 1;
		}
		return Count;

	}

	// was macht 'unknown'?
	private static String unknown(long n) {
		String s = "";
		n = n < 0 ? -n : n;
		do {
			s = (n % 2) + s;
		} while ((n /= 2) != 0);
		return s;
	}

	// just for testing ...
	public static void main(String[] args) {
		System.out.println(unknown(12));
		System.out.println("Zero Count =" + nullen(12));
		System.out.println("Zero Count Recursive =" + nullenFactorial(12));

		System.out.println("One Count =" + einsen(12));
		System.out.println("One Count Recursive =" + einsenFactorial(12));

	}
}
