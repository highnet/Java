public class transposeMatrix {
	
	
private static void runTests(String arrayType, int magnitudeParameter) {
		
		switch(arrayType){
		
		case "pyramid":{
		
	      int[][]topDownPyramidArray = createArrayType("pyramid",magnitudeParameter);
	      
	      System.out.println("{-----------------------------------------}");
	      System.out.println("{There is a total of " + fillArray(topDownPyramidArray) + " elements in the array}");
	      System.out.println("{There is a total of " + printArray(topDownPyramidArray) + " rows in the array}");
	      transpose(topDownPyramidArray);
	      System.out.println("{There is a total of " + printArray(topDownPyramidArray) + " rows in the array}");
	      System.out.println("{-----------------------------------------}");
	      break;
		
		
	}
		
		case"square matrix":{
			 int[][]squareMatrixArray = createArrayType("square matrix",magnitudeParameter);
		      
		      System.out.println("{-----------------------------------------}");
		      System.out.println("{There is a total of " + fillArray(squareMatrixArray) + " elements in the array}");
		      System.out.println("{There is a total of " + printArray(squareMatrixArray) + " rows in the array}");
		      transpose(squareMatrixArray);
		      System.out.println("{There is a total of " + printArray(squareMatrixArray) + " rows in the array}");
		      System.out.println("{-----------------------------------------}");
		      break;

		 
		}

	
		}
	}

    private static int fillArray(int[][] data) {
    	int counter = 0;
 
    	for(int i = 0; i < data.length; i++)
    	   {
    	
    	      for(int j = 0; j < data[i].length; j++)
    	      {
    	    	  data[i][j] += counter++;
    	//    	  System.out.println("data["+i+"]["+j+"] += " + (counter-1));

    	    	
    	    	  }
    	    	
    	      }
       
    	return counter;
    }

    private static int printArray(int[][] data) {
    	 System.out.println("Printing the Array...");
    	 System.out.println();
    	 
    	 int rowCounter = 0;
    
    	for(int i = 0; i < data.length; i++){
    		
    	
    	      for(int j = 0; j < data[i].length; j++){
    	      
    	
    	    		  System.out.printf("%2d",data[i][j]);
    	    		  System.out.print(",");
    	    		
    	      }
    	      
    	      rowCounter++;
    	      System.out.println();
    	  
    	   }
       
    	return rowCounter;
    }
    

    private static int[][] transpose(int[][] data) {
    	
    	
    	int temp = 0;
    	int start = 1;
  
    	System.out.println();
    	 System.out.print("Transposing the Array...");
    	 System.out.println();
    	 
    	
    	for(int i = 0; i < data.length; i++){
    		
  	      for(int j = start; j < data[i].length; j++){  
  	    	temp = data[i][j];
			data[i][j] = data[j][i];
			data[j][i] = temp;
  	      
  	      }
  	      	start++;
  	  
  	   }
	      System.out.println();

    	return data;
    }
    
    private static int[][] createArrayType(String type,int magnitudeParameter) {
		
    	
    	switch(type){
    	
    	case "pyramid":{
    		int [][] topDownPyramidArray = new int [magnitudeParameter][];
    
    		for(int i = topDownPyramidArray.length-1 ; i >= 0; i--){
  
    		
	        	topDownPyramidArray[i] = new int[(topDownPyramidArray.length-i)];


	        	
	        }
    		return topDownPyramidArray;
    	}
    	
    	case "square matrix":{
    		int [][] squareMatrixArray = new int [magnitudeParameter][magnitudeParameter];
    		return squareMatrixArray;
    	}
    	
    	}
		return null;
    	
		
	}

    // Just for testing ...
    public static void main(String[] args) {
    	
    	
    	int magnitudeParameter = 10;
    	
    	runTests("pyramid",magnitudeParameter);
    	runTests("square matrix", magnitudeParameter);
    	
    	System.out.println("End of program");
      
    }

	
}
