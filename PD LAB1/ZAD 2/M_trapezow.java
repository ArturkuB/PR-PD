package pakiet;

public class M_trapezow extends Thread {
	
	private double a;
	public double wynik;
	
	public M_trapezow(double ai) {
		this.a = ai;
	}

	public void run() {
			wynik = Main.fun(a);
		}
		
	}

class Trapez {
	
	private double n;
	private double b;
	private double a;
	
	
	public Trapez(double n, double a, double b) {
		this.n = n;
		this.a = a;
		this.b = b;
	}
	
	
	public void licz() throws InterruptedException {
		 double h = (b - a)/n;
	        double x[] = new double[(int) (n+1)];
	        
	        M_trapezow sum[] = new M_trapezow[(int) (n+1)];
	        
	        for(int i = 1; i < n; i++)
			{
				double temp = i / n;
				x[i-1] = a + (temp * (b - a));
			}

	        
	       for (int i = 0; i < n-1; i++) {
	    	  
	    	   sum[i] = new M_trapezow(x[i]);
	    	   sum[i].start();
	       }
	       
	       for (int i = 0; i < n-1; i++) {
	    	  sum[i].join();  	   
	       }
	        double suma = 0;
	       
	       for (int i = 0; i < n-1; i++) {
	     	  suma = suma + sum[i].wynik;
	        }
	       
	       suma = suma + (Main.fun(a) / 2) + (Main.fun(b)/2);
	       suma = suma * h;
	      System.out.println(suma);
	}
	
}
	

