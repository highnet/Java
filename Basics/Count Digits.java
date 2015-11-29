public class countDigits {

    public static int countDigits(int value){
    	int numberOfDigits = 0;
    	
    	
    	while (value > 0){
    		value /= 10;
    		
    		numberOfDigits++;
    	}
    	
		return numberOfDigits;
    	
    }
    
    public static int countDigitsFactorial(int value){
    	
    	if (value == 0) return 0;
    	
    	else return 1 + countDigitsFactorial(value/10);
    }

    public static void main(String[] args) {
    	
    	int n = 12;
    	
        System.out.println(countDigits(n));
        System.out.println(countDigitsFactorial(n));
    }
}
