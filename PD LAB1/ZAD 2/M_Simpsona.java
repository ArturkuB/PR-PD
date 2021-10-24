package pakiet;

import java.util.ArrayList;
import java.util.Collections;

public class M_Simpsona extends Thread {
	
	private double a;
	public double wynik;
	
	public M_Simpsona(double ai) {
		this.a = ai;
	}

	public void run() {
			wynik = Main.fun(a);
		}
		
	}

class Simpson {
	
	private double n;
	private double b;
	private double a;
	
	
	public Simpson(double n, double a, double b) {
		this.n = n;
		this.a = a;
		this.b = b;
	}
	
	
	public void licz() throws InterruptedException {
		
		
		ArrayList<Double> t = new ArrayList<Double>();
	        double x[] = new double[(int) (n+1)];
	        
	        M_Simpsona sumt[] = new M_Simpsona[(int) (n+1)];
	        M_Simpsona sumx[] = new M_Simpsona[(int) (n+1)];
	        
	        for(int i = 0; i < n+1; i++)
			{
				double temp = i / n;
				x[i] = a + (temp * (b - a));
			}

	        for(int i = 0; i < n; i++)
			{
				t.add((x[i+1] + x[i]) / 2);			
			}
	        
	        Collections.sort(t);
			double h = (x[1] - x[0]) / 2;
			double sumat = 0;
			double sumax = 0;
	        
	       for (int i = 0; i < n; i++) {
	    	  
	    	   sumt[i] = new M_Simpsona(t.get(i));
	    	   sumt[i].start();
	       }
	       
	       for (int i = 0; i < n-1; i++) {
		    	  
	    	   sumx[i] = new M_Simpsona(t.get(i+1));
	    	   sumx[i].start();
	       }
	       
	       for (int i = 0; i < n-1; i++) {
	    	  sumt[i].join();  	   
	       }
	       
	       for (int i = 0; i < n-1; i++) {
		    	  sumx[i].join();  	   
		       }
	       
	       
	       for (int i = 0; i < n-1; i++) {
		    	  sumat = sumat + sumt[i].wynik;
		       }
	          sumat = sumat * 4;
		       
		       for (int i = 0; i < n-1; i++) {
		    	   sumax = sumax + sumx[i].wynik;
			       }
		       sumax = sumax * 2;
		       double sum = 0;
		       
		       sum = Main.fun(x[0]) + sumat + sumax + Main.fun(x[x.length-1]);
				sum = sum * (h / 3);
				System.out.print("Wynik: " + sum);
	        
	}
	
}
	

