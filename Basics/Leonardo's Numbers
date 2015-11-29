public class leonardoNumbers {
	
	public static int iter(int n){ // Leonardo's Numbers
		
		double goldenRatio = 1.6180339887498948482045868343656381177203091798057628;
		double phi = -0.61803398874989484820458683436563811772030917980576;
		double sqrtFive = 2.2360679774997896964091736687312762354406183596115257;
		
		
		
		double goldenRatioPowNPlusOne = goldenRatio;
		double phiPowNPlusOne = phi;
		
		for(int i = 1; i < n+1; i++){ // Equivalent to Math.pow(goldenRatio,n+1)
		goldenRatioPowNPlusOne *= goldenRatio; 
		}
		
		for(int i = 1; i < n+1; i++){ // Equivalent to Math.pow(phi, n+1)
			phiPowNPlusOne *= phi; 
			}

		
		switch(n){
		case 0: return 1;
		case 1: return 1;
		}
		
		return  (int) ((2/sqrtFive) * (goldenRatioPowNPlusOne - phiPowNPlusOne) - 1);
			
		}
	
	
	public static int rec(int n){ // Leonardos number
		
		switch(n){
		case 0: return 1;
		case 1: return 1;
		default: return rec(n-1) + rec(n-2) +1;
		}
		
	}

    // invokes iter as well as rec with all integers from 0 to 30 and prints the results
    // (without empty lines or other output)
    public static void main(String[] args) {
    	
    	for(int i = 0; i <= 30; i ++){
    	System.out.println("rec(" + i + ") = " + rec(i) + " iter(" + i + ") = " + iter(i) );

    }
    }
}
