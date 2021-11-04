package pakiet;

public class Main {

	public static void main(String[] args) {
		new Czasomierz().start();

	}

}


class Czasomierz extends Thread {
	
	private int ss = 0;
	private int mm = 0;
	private int gg = 0;
	String tempss, tempmm, tempgg;
	
	public void run() {
		while(true) {
			try {
				sleep(1000);
				if(ss < 10) {
					tempss = Integer.toString(ss);
					tempss = "0" + tempss;
				}
				else {
					tempss = Integer.toString(ss);
				}
				if(mm < 10) {
					tempmm = Integer.toString(mm);
					tempmm = "0" + tempmm;
				}
				else {
					tempmm = Integer.toString(mm);
				}
				if(gg < 10) {
					tempgg = Integer.toString(gg);
					tempgg = "0" + tempgg;
				}
				else {
					tempgg = Integer.toString(gg);
				}
				System.out.println(tempgg + ":" + tempmm + ":" + tempss);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ss++;
			if(ss == 60) {
				ss = 0;
				mm++;
			}
			if(mm == 60) {
				mm = 0;
				gg++;
			}
		}
	}
}
