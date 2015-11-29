public class rangeCheck {

	// Hier sollten die benötigten Methoden stehen.

	// Just for testing ...
	public static void main(String[] args) {

		int sizeOfRandomArray = 10; // Determines how many random elements the array has in the printarray function
		int numberOfPartitions = 3; // Determines how many partitions there are in the printarray function

		printArray(createArray(sizeOfRandomArray), numberOfPartitions); // Prints how many elements are in each partition.

		double[] tester = new double[101];
		
		for(double i = 0; i < 101; i++){
			tester[(int) i] = i;
	//		System.out.println(tester[(int) i]);
		}
		
		
		
		halveArray(tester);
		

	}

	private static void printArray(int[] array, int partitions) { // Prints how many elements are in each partition.
		System.out.println("[Dev] START OF printArray(int[] array,"+ partitions + ")");

		System.out.println("[Dev] There are " + partitions + " partitions.");

		double[] ranges = new double[partitions + 1]; // Create an array indicating all the ranges of our partitions.
		
		System.out.println("[Dev] Created an Array ranges[" + (partitions + 1) + "].");

		for (int i = 0; i < partitions + 1; i++) { // Calculates the different ranges per partition.
			
			if (partitions == 0) {break;} // Break before dividing by 0!!!
			ranges[i] = (double) 100 / partitions * i;	   // formula used to find the ranges.
		}

		
		for (int i = 0; i < ranges.length; i++) { // Prints different ranges
			System.out.println("[Dev] ranges[" + i + "] = " + ranges[i]);
		}

		int[] answers = new int[partitions]; // create an array to store the answers(how many elements per range)
		System.out.println("[Dev] Created an Array answers["+ partitions+"].");

		for (int i = 0; i < array.length ; i++) { // Nested loop: Iterate every RNG array element...
		//	System.out.println("[Dev] " + i);
			
			secondaryLoop: // loop label for outer loop
			for (int j = 0; j < answers.length; j++) { // Nested loop: ... and check in which range it belongs to.
				
				
				if (array[i] >= ranges[j] && array[i] <= ranges[j + 1]) { // Checks in which range the RNG array element is.
					System.out.println("[Dev] array[" + i + "] (= " + array[i] + ") is in the range of ["+ ranges[j] + " <=> " + (ranges[j + 1]) + "], Incrementing answers[" + j + "].");
					answers[j]++; // increase the counter for that specific range
					break secondaryLoop;
				
				}
			}
		}
	

		for (int i = 0; i < answers.length; i++) { // Print the final answer
			System.out.println("[User] answers[" + i + "] = " +answers[i]);
		}
		
		System.out.println("[Dev] END OF printArray(int[] array,"+ partitions + ")");
		System.out.println();

	}

	private static int[] createArray(int size) {
		System.out.println("[Dev] START OF createArray(" + size + ")");
		int[] array = new int[size]; // Create a user-specified dynamic array.
		System.out.println("[Dev] Created an Array of size: " + size);

		for (int i = 0; i < array.length; i++) {
			array[i] = getRandomNumber(); // Populate that array element with a random number ( 0 to 100 )
			System.out.println("[Dev] array[" + i + "] = " + array[i]);
		}

		System.out.println("[Dev] END OF createArray(" + size + ")");
		System.out.println();
		return array;

	}

	private static int getRandomNumber() {
		return (int) (Math.random() * 101); // Generates a random number between 0 and 100.
	}
	
	private static void halveArray(double[] array){
		
		System.out.println("[Dev] Start of halveArray(int[] array");
		
		
		double[] halve = new double[array.length];
		
		for (double i: array){
			halve[(int) i] = (array[(int) i]/2);
			System.out.println("halve[" + i + "] = " + halve[(int) i]);
		}
		
		System.out.println("[Dev] End of of halveArray(int[] array)");
		

		
	}
	
}
