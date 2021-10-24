package pakiet;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class Train extends Thread {
        
	int id;
	String state;
	Station current;
        int enginePower;
        static int MAXPOWER = 1000;

	public Train(int id, Station a){
		this.id = id;
                current = a;
		this.state = "atStation";
                this.enginePower = MAXPOWER;
	}
        
        public String getTrainState() {
            return state;
        }
	
        public int getTrainId() {
            return id;
        }
	
        public boolean wasCrashed() {
            if (this.enginePower < 10) {
                return true;
            } else
                return false;
        }
        
        public void run(){
            Random ran = new Random();
            boolean keepRunning = true;
            
		while(keepRunning){
                    switch (this.getTrainState()) {
                        case "atStation" -> {
                            this.enginePower = MAXPOWER;
                            System.out.println("Train " + this.getTrainId() + " engine power is restored at the station ");
                            System.out.println("Train " + this.getTrainId() + " is about to start its journey ");
                            current.startJourney(this.getTrainId());
                            state = "journeyStarted";
                        }
                        case "journeyStarted" -> {
                            System.out.println("Train " + this.getTrainId() + " has started its journey ");
                            this.enginePower = ran.nextInt(enginePower);
                            if (wasCrashed() == true) {
                                state = "crash";
                            } else
                                state = "journeyInProgress";
                        }
                        case "journeyInProgress" -> {
                            System.out.println("Train " + this.getTrainId() + " is about to reach next station ");
                            this.enginePower = ran.nextInt(enginePower);
                            if (wasCrashed() == true) {
                                state = "crash";
                            } else
                                state = "journeyEnded";
                        }
                        case "journeyEnded" -> {
                            System.out.println("Train " + this.getTrainId() + " has ended its journey ");
                        try {
                            state = current.endJourney();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }
                        case "crash" -> {
                            System.out.println("Train " + +this.getTrainId() + " has crashed on the way ");
                            if (current.decrease() == 1) {
                                try {
                                    this.sleep(10000);
                                    state = "atStation";
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                              System.out.println("All trains have been crashed"); 
                            keepRunning = false;
                            }
                        }
                            
                    }
		}
	}

}
