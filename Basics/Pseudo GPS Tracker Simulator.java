public class Position {

	private int latitude;
	private int longitude;
	private int timeSinceMidnight;

	public Position(int startLatitude, int startLongitude, int startTimeSinceMidnight) {

		latitude = startLatitude;
		longitude = startLongitude;
		timeSinceMidnight = startTimeSinceMidnight;
	}

	// Returns true if (and only if) longitude and latitude of this position
	// (the current object) differ from
	// longitude and latitude of x by at most eps.
	public boolean isClose(Position x, double eps) {
		
		System.out.println("isClose(...)") ;
		boolean isWithinRange = false; // boolean variable used to check if the object position is within the boundary(eps).
		
		if ((Math.abs(this.latitude - x.latitude) + (Math.abs(this.longitude - x.longitude) ) <= eps)) { // true when (eps < (dX+dY))
			
			System.out.println("> (" + this.latitude + "," + this.longitude + ") is within " + (int) eps + " units (" + x.longitude + "," + x.latitude + ")");
			isWithinRange = true;
		}
		else { // otherwise it is not in range
			System.out.println("> (" + this.latitude + "," + this.longitude + ") is not within " + (int) eps + " units (" + x.longitude + "," + x.latitude + ")");

		}

		return isWithinRange;
	}

	// Returns true if (and only if) longitude and latitude of this position
	// (the current object) differ from
	// longitude and latitude of any element in xs by at most eps.
	public boolean isCloseToPath(Position[] xs, double eps) {

		for (int i = 0; i < xs.length; i++) { // checks if every object in the array is in range
			if (this.isClose(xs[i], eps) == false) {
				return false;
			}
		}

		return true;
	}

	// Parameter xs contains the positions of a bikerLog. A tour is regarded as
	// being stopped at a position in the array
	// if this position is close (difference at most eps) to each of the
	// following 4 positions in the array.
	// The method returns the time (in seconds since midnight) of the first stop
	// (lowest index in the array),
	// or -1 if there is no stop.

	public static boolean isATour(Position[] xs, double eps) {
			
		// check if it is a tour
		if (xs[0].isClose(xs[1], eps) && xs[1].isClose(xs[2], eps)&& xs[2].isClose(xs[3], eps) && xs[3].isClose(xs[4], eps)) {
			return true;
		}
		return false;
	}

	public static int isStopped(Position[] xs, double eps) {
		// if it is a tour, return time of the lowest index.
		if (isATour(xs, eps) == true) {
			return xs[0].timeSinceMidnight;
		}
		return -1;
	}

	// Returns true if (and only if) the time of each position in xs is higher
	// than that of each other position at a
	// lower index.
	public static boolean chronologic(Position[] xs) {
		for(int i = xs.length-1; i != 0; i--){
			// check(backwards) every object element in a bikerLog and determine wether it is chronoligcally placed.
			for(int j = i-1 ; j > -1; j--){
				System.out.println(i + "," + j);
				System.out.println(xs[i].timeSinceMidnight + ">" + xs[j].timeSinceMidnight);
			if ((xs[i].timeSinceMidnight <= xs[(j)].timeSinceMidnight)){
				return false;
			}
		}
	
	}
		return true;
	}

	// Just for testing ...
	public static void main(String[] args) {

		int eps = 4;

		Position bikeOne = new Position(1, 1, 28800); // Compare position of two bikes											
		Position bikeTwo = new Position(3, 3, 28800);
		System.out.println(bikeOne.isClose(bikeTwo, 2));
		System.out.println();
		System.out.println();

		 Position[] positionsArray = new Position[3]; // compare position of  elements in an object array

		 positionsArray[0] = new Position(1,1,28800);
		 positionsArray[1] = new Position(3,3,28800+60);
		 positionsArray[2] = new Position(3,3,28800+60+60);
		 System.out.println("Bike one is close to path?: " +bikeOne.isCloseToPath(positionsArray, 1));
		 System.out.println();
		 System.out.println();
		 
		 

		Position[] bikerLog = new Position[5];

		bikerLog[0] = new Position(2, 2, 28800 + 60);
		bikerLog[1] = new Position(4, 4, 28800+ 60 + 60);
		bikerLog[2] = new Position(6, 6, 28800 + 60 + 60 + 60);
		bikerLog[3] = new Position(8, 8, 28800 + 60 + 60 + 60 + 60);
		bikerLog[4] = new Position(10, 10, 28800 + 60 + 60 + 60+ 60 + 60);

		System.out.println("is a tour?: " + isATour(bikerLog, eps));

		System.out.println("Biker is stopped for " + isStopped(bikerLog, eps) + " seconds since midnight");

		
		System.out.println("is chronologic?: " + chronologic(bikerLog));
	}
}
