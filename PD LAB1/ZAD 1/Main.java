package pakiet;

public class Main {

	public static void main(String[] args) {
            int totalStations = 43;
            int totalTrains = 200;
            Station trainStation = new Station(3, totalTrains);
            for(int i = 1; i <= totalTrains; i++) {
                new Train(i, trainStation).start();
            }
	}
}

