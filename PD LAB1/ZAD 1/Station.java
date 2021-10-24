package pakiet;


public class Station {
	int totalStations;
	int occupiedStations;
	int totalTrains;
        
	Station(int a,int b){
		this.totalStations=a;
		this.totalTrains=b;
		this.occupiedStations=0;
	}
        
	synchronized void startJourney(int a){
		occupiedStations--;
	}
        
	synchronized String endJourney() throws InterruptedException{
            Thread.currentThread().sleep(1000);
		if(occupiedStations < totalStations) {
			occupiedStations++;
			return "atStation";
		} else
                    return "journeyEnded";

	}
	synchronized int decrease(){
		totalTrains--;
                if (totalTrains == 0) {
                    return 0;
                } else
                    return 1;
                
        }

}
