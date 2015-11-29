public class clock {
	
	int seconds;
	int minutes;
	int hours;
	int minutesCounter;
	String clockName;
	
	public clock(String startName){
		
		clockName = startName;
	
	
		
		System.out.println("Clock generated");
	}

	public void setTime(int tickCounts) {
		
	
	if (this.minutes == 60 && this.seconds == 0){
		this.seconds = 0;
		this.minutes = 0;
		this.hours++;
	}
		
	if (this.seconds < 59){
		this.seconds = tickCounts - 60 * ((tickCounts / 60));
	}
	
	else if (this.seconds == 59){
		this.minutes++;
		this.seconds = 0;
	}
		
	}

	public void printTime() {
		
		if (this.seconds <= 9 && this.minutes <= 9){
		System.out.println(this.clockName + ": " + this.hours + ":0" + this.minutes + ":0" + this.seconds);
		} 
		else if (this.seconds <= 9 ){
			System.out.println(this.clockName +": " +this.hours + ":" + this.minutes + ":0" + this.seconds);
		
		} 
		else if ( this.minutes <= 9){
			System.out.println(this.clockName +": " + this.hours + ":0" + this.minutes + ":" + this.seconds);
		
		}
		
		else  {
			System.out.println(this.clockName + ": "  + this.hours + ":"+ this.minutes + ":" + this.seconds);
		}

}
}
