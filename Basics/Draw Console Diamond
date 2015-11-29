public class drawDiamonds {
	
	public static Object recdrawNumDiamond(int height, int index, int counter,int indexTwo,int indexThree){
		
		while(index < height /2 ){
			
			 System.out.printf("%" + ((height / 2) - index +1 ) + "d", ++counter); // Set the format for the first letter.
			 
			 for (int j = 0; j < index; j++) { // once the format is set, print j*2 many counters.
	                System.out.print(counter);
	                System.out.print(counter);
	            }
	            System.out.println();
	   		 return recdrawNumDiamond(height, index+1, counter, indexTwo, indexThree);
		}
		
		
			while (indexTwo < height){
				System.out.print(counter+1);
				return recdrawNumDiamond(height, index, counter, indexTwo+1, indexThree);
			}
			
           
		while(indexThree < height/2){
            System.out.println();

			System.out.printf("%" + (2 + indexThree) + "d", counter); //set the format of the first letter,
			
		

	            for (int j = 1; j < counter ; j++) { // print counter to fill the rest of the line
	                System.out.print(counter);
	                System.out.print(counter);
	            }
	         
	            return recdrawNumDiamond(height, index, counter-1, indexTwo, indexThree+1);
	            
	        }
		  

			
		return null;
		
	}

    public static void drawNumDiamond(int height) {

        int counter = 0; // Initialize a counter.

        for (int i = 0; i < height / 2; i++) { // Loop for the top half(height/2) of the diamond.  FORMULA: [(height/2) - (i+1)]+[numbers]]...
            System.out.printf("%" + ((height / 2) - i+1 ) + "d", ++counter); // Set the format for the first letter.

            for (int j = 0; j < i; j++) { // once the format is set, print j*2 many counters.
                System.out.print(counter);
                System.out.print(counter);
            }
            System.out.println();
        }

        counter++; // increase counter by one for the median row.

       for (int i = 0; i < height ; i++){ // print the
           System.out.print(counter);
       }
        System.out.println();
        for (int i = 0; i < height/2; i++) { // Loop for the bottom half(height/2) of the diamond
            System.out.printf("%" + (2 + i) + "d", --counter); //set the format of the first letter, FORMULA: [space]+[numbers],[space][space]+[numbers]...

            for (int j = 1; j < counter ; j++) { // print counter to fill the rest of the line
                System.out.print(counter);
                System.out.print(counter);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int h = 9;
      //  drawNumDiamond(h);
        recdrawNumDiamond(7,0,0,0,0);
    }
}
