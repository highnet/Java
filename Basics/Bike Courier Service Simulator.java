import java.util.LinkedList;
import java.util.Queue;

public class District {
	
    
    String districtName;
    int tickCountMinutes;
    
    
    
    public District(String startDistrictName,int startMinutesCount){
    	districtName = startDistrictName;
    	tickCountMinutes = startMinutesCount;

    }
    
    public void tick(){
    	
    	++this.tickCountMinutes;
    }
    
    public void arrive(String name, District originDistrictObject, Queue<String> originDistrictQueue, District destinationDistrictObject) { // TODO: Move function on top of main.
		
    

    	
    	System.out.println("<ARRIVE>");
		tickAll(originDistrictObject, destinationDistrictObject);

		
    	if (this.tickCountMinutes < 10){
		System.out.println("Um <8:0" + this.tickCountMinutes + "> ist <" + name + "> in <" + destinationDistrictObject.districtName +"> eingetroffen.");
    	}
    	else  if (this.tickCountMinutes < 60){
    		System.out.println("Um <8:" + this.tickCountMinutes + "> ist <" + name + "> in <" + destinationDistrictObject.districtName +"> eingetroffen.");
    		
    	}
		originDistrictQueue.offer(name);
		
		
	}
    
	private static void printQueues(Queue<String> d1Queue, Queue<String> d2Queue) {
		System.out.println(d1Queue);
		System.out.println(d2Queue);
		System.out.println();
		System.out.println();
	}

	private static void tickAll(District districtOne, District districtTwo) {
		districtOne.tick(); 
    	districtTwo.tick();	
    	System.out.println("Tick(): " + (districtOne.tickCountMinutes-1) + "-->" + districtOne.tickCountMinutes);

    	
	}

	private void job(District originDistrictObject, District destinationDistrictObject,Queue<String> originDistrictQueue, Queue<String> destinationDistrictQueue) {
	
		
		tickAll(originDistrictObject, destinationDistrictObject);
   
		System.out.println("<JOB>");
		if (this.tickCountMinutes < 100){
			System.out.println("Um <8:0" + this.tickCountMinutes + "> fährt <"+ originDistrictQueue.peek() +"> von <"+ originDistrictObject.districtName +"> nach <"+destinationDistrictObject.districtName + ">.");	    	
			}
	    	else {
				System.out.println("Um <8:" + this.tickCountMinutes + "> fährt <"+ originDistrictQueue.peek() +"> von <"+ originDistrictObject.districtName +"> nach <"+destinationDistrictObject.districtName + ">.");	    	
	    	}
		
		
		String queuePolledBikerName = originDistrictQueue.poll();
		System.out.println();
		System.out.println();
		this.arrive(queuePolledBikerName, originDistrictObject, destinationDistrictQueue,destinationDistrictObject);
	}

	public void help(District originDistrictObject,District destinationDistrictObject, Queue<String> originDistrictQueue, Queue<String> destinationDistrictQueue) {
		
		
		System.out.println("<HELP>");
		tickAll(originDistrictObject, destinationDistrictObject);

		if (this.tickCountMinutes < 60){
			System.out.println("Um <8:0" + this.tickCountMinutes + "> ist <" + originDistrictQueue.peek() + "> in <" + this.districtName +"> eingetroffen.");
	    	}
	    	else {
	    		System.out.println("Um <8:" + this.tickCountMinutes + "> ist <" + originDistrictQueue.peek() + "> in <" + this.districtName +"> eingetroffen.");

	    	}
		String queuePolledBikerName = originDistrictQueue.poll();
		destinationDistrictQueue.offer(queuePolledBikerName);

	}
    

    



	public static void main(String[] args) {
    	
	       Queue <String> d1Queue = new LinkedList<String>();
	       Queue <String> d2Queue = new LinkedList<String>();
    	
    	   District districtOne = new District("erste bezirk",0);
    	   District districtTwo = new District("zweite bezirk",0);
    	   
    	districtOne.arrive("bee",districtTwo,d1Queue,districtOne);
    	printQueues(d1Queue,d2Queue);

       	districtOne.arrive("bear",districtTwo,d1Queue,districtOne);   
    	printQueues(d1Queue,d2Queue);


    	districtOne.arrive("lion",districtTwo,d1Queue,districtOne);
    	printQueues(d1Queue,d2Queue);
   

    	districtOne.arrive("bird",districtTwo,d1Queue,districtOne);
    	printQueues(d1Queue,d2Queue);


    	districtTwo.arrive("dog",districtOne,d2Queue,districtTwo);
    	printQueues(d1Queue,d2Queue);

    	districtTwo.arrive("cat",districtOne,d2Queue,districtTwo);
    	printQueues(d1Queue,d2Queue);
  
        districtTwo.help(districtOne,districtTwo,d1Queue,d2Queue);
    	printQueues(d1Queue,d2Queue);

    	
    	districtOne.job(districtOne,districtTwo, d1Queue,d2Queue);
    	printQueues(d1Queue,d2Queue);
    	
    	districtOne.arrive("a",districtTwo,d1Queue,districtOne);
    	printQueues(d1Queue,d2Queue);
    	
    	districtOne.arrive("b",districtTwo,d1Queue,districtOne);
    	printQueues(d1Queue,d2Queue);
    	
   
    	


    
    }


}
