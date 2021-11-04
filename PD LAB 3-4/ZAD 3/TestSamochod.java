package pakiet;

class Samochod extends Thread {
	
	private String nrRej;
	private int pojZbiornika;
	private int paliwo;
	private Boolean ruch = false;
	
	public Samochod (String nr, int _pojZbiornika) {
		nrRej = nr;
		pojZbiornika = _pojZbiornika;
		paliwo = this.pojZbiornika;
	}
	public void tankowanie (int _paliwo) {
		if(this.paliwo + _paliwo > pojZbiornika) {
			this.paliwo = pojZbiornika;
		}
		else {
			this.paliwo = this.paliwo + _paliwo;
		}
	}
	public void startuj() {
		ruch = true;
	}
	public void stoj() {
		ruch = false;
	}
	public void run() {
		if(ruch == true) {
				while(true) {			
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					paliwo--;
					System.out.println(nrRej + " :" + paliwo);
					if(paliwo == 0) {
						ruch = false;
					}
				}
		}
	}
	
	

} 

public class TestSamochod {
	public static void main(String args[]) throws InterruptedException {
		Samochod a = new Samochod("AB", 300);
		a.start();
		a.startuj();
		Samochod b = new Samochod("ABC", 300);
		b.start();
		b.startuj();
		Samochod c = new Samochod("ABD", 300);
		c.start();
		c.startuj();
	}
}
// symulacja działania klasy Samochod dla 1,2,3, ... samochodów