public class gcd {

    private static int rec(int x, int y) {
        if (x < 0) {  //  Wenn x negativ ist, positiv machen.
            return rec(-x, y);
        }
        if (y < 0) { // wenn y negativ ist, positiv machen
            return rec(x, -y);
        }
        if (x == 0) { // wenn x == 0 return 0
            return 0;
        }
        if (x > y) { // wenn x ist grösser als y return rec(y,x)
            return rec(y, x);
        }
        if (x == y) { // wenn x == y return x
            return x;
        }
        return rec(x, y - x); // else return rec(x, y-x)
    }

    // Does the same as rec.
    private static int iter(int x, int y) {
    	while(true){
    		
    		x = (x < 0 ? -x : x); 
    		
    		y = (y < 0 ? -y : y);
    		
    		if (x == 0){
    			return 0;}
    		
    		if (x > y) {int swapx = x;
    					int swapy = y; 
    					x = swapy; 
    					y = swapx;}
    		
        	if (x == y){
        		return x;}
        	
        	y = y-x;
    	}
        
    
    }

    // Just for testing ...
    public static void main(String[] args) {
    	
    	int x = 0;
    	int y = 0;
    	
    	for (int i = -10; i <= 10; i++){
    		
    		for(int j = -10; j <= 10; j++){
    			
    			x = i;
    			y = j;
    			 System.out.print("rec(" + x + "," + y + ") = " +rec(x,y));
    			 System.out.println(" -- iter(" + x + "," + y + ") = " +iter(x,y));
    			 
    		}
    	}
    
        System.out.print(rec(6328,190));

    }
    
	 
	 
}
