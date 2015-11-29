public class StreetMap {

    // TODO: Object variables shall be declared here.
	
	 Queue <String> addresses = new LinkedList<String>();
	 Queue <String> districts = new LinkedList<String>();
	 Queue <Double> longitudes = new LinkedList<Double>();
	 Queue <Double> latitudes = new LinkedList<Double>();

    // The parameters specify the initial contents of a street map:
    //     addr:  addresses that can be found in the street map
    //     distr: names of the districts of corresponding addresses
    //     lon:   longitudes in the GPS coordinates
    //     lat:   latitude in the GPS coordinates
    // All queues in the parameters are of the same size.
    // All entries at the same position (1st, 2nd, 3rd, ...) belong together.
    public StreetMap(Queue<String> addr, Queue<String> distr, Queue<Double> lon, Queue<Double> lat) {
        addresses = addr;
        districts = distr;
        longitudes = lon;
        latitudes = lat;
    }

    // Returns all data (district and GPS coordinates) for address addr.
    // Returns null if no data can be found for this address.
    public MapData find(String addr) {
       
    	
        MapData data = new MapData("",0,0);
    	
    	Queue <String> dummyAddr = new LinkedList<String>(this.addresses);
    	Queue <String> dummyDistr = new LinkedList<String>(this.districts);
    	Queue <Double> dummyLongitudes = new LinkedList<Double>(this.longitudes);
    	Queue <Double> dummyLatitudes = new LinkedList<Double>(this.latitudes);
    	
    	
    	int length = dummyAddr.size();
    	
    	for(int i = 0; i < length; i++){
    		String tempAddr = dummyAddr.poll();
    		String tempDistr = dummyDistr.poll();
    		double tempLongitude = dummyLongitudes.poll();
    		double tempLatitude = dummyLatitudes.poll();
    		
    		if (tempAddr == addr){
    			
    			data.district = tempDistr;
    			data.longitude = tempLongitude;
    			data.latitude = tempLatitude;
    			
    			return data;
    		}
    	}
    	
        return null;
    }

    // Returns true if (and only if) address addr is in district distr.
    public boolean inDistrict(String addr, String distr) {

    	
    	Queue<String> dummyAddr = new LinkedList<String>(this.addresses);
    	Queue<String> dummyDistr = new LinkedList<String>(this.districts);

    	int sizeOfQueue = this.addresses.size();
		 System.out.println("Looking for match : " + addr + " in the : " + distr);

    	for(int i = 0; i < sizeOfQueue; i++ ){
    		String tempAddr = dummyAddr.poll();
    		String tempDistr = dummyDistr.poll();
    		if (tempAddr == addr && tempDistr == distr){
    			System.out.println("match found");
    			return true;
    		}
    	}
    	return false;
    }

    // Adds a new address addr to the street map, where newData are the data to be associated with this address.
    // However, if the address already exists, the old data are replaced with newData.
    // true is returned if the address was replaced, false if a new address was added.
    public boolean newData(String addr, MapData newData) {
          	
    	int sizeOfQueue = this.addresses.size() ;
    	
    	 for(int i = 0; i < sizeOfQueue; i++){

    		 String temp = this.addresses.poll();
			 System.out.println("temp: " + temp);
    		 if (temp == addr){
    			 this.districts.poll();
    			 this.longitudes.poll();
    			 this.latitudes.poll();
    		 }
    		 
    		 if (temp != addr){
    			 System.out.println("temp != addr");
    			this.addresses.offer(temp); 
    		 }
    		 
    	 }
    	 
    	 // String temp = this.addresses.poll()
    	 // if (addr != temp){
    	 //  newQueue.offer(temp)
    	 // else{ }
    	 // 
    	this.addresses.offer(addr);
    	 System.out.println(this.addresses.size());

    	this.districts.offer(newData.district);
    	this.longitudes.offer(newData.longitude);
    	this.latitudes.offer(newData.latitude);
    	
        return false;
    }

    // To test the implementation several objects of StreetMap (each with several addresses) are created,
    // all methods are called, and data associated with found addresses are printed.
    public static void main(String[] args) {
    	
   //     Map<String, String> streetMaps = new TreeMap<String, String>();
        
     Queue <String> addresses = new LinkedList<String>();
   	 Queue <String> districts = new LinkedList<String>();
   	 Queue <Double> longitudes = new LinkedList<Double>();
   	 Queue <Double> latitudes = new LinkedList<Double>();
        
       StreetMap myMap = new StreetMap(addresses, districts, longitudes, latitudes);
       System.out.println(myMap.addresses + "," + myMap.districts + "," + myMap.longitudes + "," + myMap.latitudes);

       
       MapData dataA = new MapData("second",0D,0D);
       MapData dataB = new MapData("first",4D,20D);
       MapData dataC = new MapData("fourth",12D,12D);
       

       myMap.newData("czerningasse", dataA);
       System.out.println(myMap.addresses + "," + myMap.districts + "," + myMap.longitudes + "," + myMap.latitudes);

       myMap.newData("Stephansplatz",dataB);
       System.out.println(myMap.addresses + "," + myMap.districts + "," + myMap.longitudes + "," + myMap.latitudes);

       myMap.newData("hartmangasse", dataC);
   
       System.out.println(myMap.addresses + "," + myMap.districts + "," + myMap.longitudes + "," + myMap.latitudes);
       
       
      System.out.println(myMap.inDistrict("Stephansplatz","first"));
      System.out.println(myMap.addresses + "," + myMap.districts + "," + myMap.longitudes + "," + myMap.latitudes);

      System.out.println(myMap.inDistrict("czerningasse","second"));
      System.out.println(myMap.addresses + "," + myMap.districts + "," + myMap.longitudes + "," + myMap.latitudes);

      System.out.println(myMap.inDistrict("hartmangasse","kkk"));
      System.out.println(myMap.addresses + "," + myMap.districts + "," + myMap.longitudes + "," + myMap.latitudes);
      
      
      MapData dataD = myMap.find("hartmangasse");
      
      System.out.println(dataD.district);
      System.out.println(dataD.longitude);
      System.out.println(dataD.latitude);
    }
}

// Objects of MapData hold all data (district and GPS coordinates) associated with an address in the street map.
// It is necessary to get access to the data in objects of this type.
 class MapData {

	 String district;
	 double longitude;
	 double latitude;
	 
	 public MapData(String startDistrict, double startLongitude, double startLatitude){
		 
		 district = startDistrict;
		 longitude = startLongitude;
		 	latitude = startLatitude;
	 }
	 
	  
 }
