package pakiet;

import java.util.Set;

public class Main {

    public static void main(String[] args) throws InterruptedException {
  
        
        Trapez a = new Trapez(32, 1.0, 4.0);
        a.licz();
        
         Simpson b = new Simpson(32, 1.0, 4.0);
         b.licz();
    }
    
    
    static double fun(double x) {
        //return x * x;
        return Math.sin(1.3 * x + 0.4) / (2.2 + Math.cos((0.6 * Math.pow(x, 2)) + 1.1));
    }
    
}

    

