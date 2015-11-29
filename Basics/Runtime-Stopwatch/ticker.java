public class ticker {

	int tickCounts;

	public ticker() {
		System.out.println("New Ticker: " + this.tickCounts);
	}

	private void printTime() {

		// System.out.println("Tick : " + this.tickCounts);
	}

	public void tick() {

		++this.tickCounts;
		this.printTime();

	}

}

