package pakiet;

import java.util.*;

class MyThread extends Thread{
    int points;
    int insidePoints;

    public MyThread(int insidePoints, int points){
      this.points = points;
      this.insidePoints = insidePoints; 
    }
    
    public void run(){
          int prec = 1000000;
          Random rand = new Random();
          for( int i=0 ; i < points ; ++i){
              double x = (double)rand.nextInt(prec+1)/(double)prec;
              double y = (double)rand.nextInt(prec+1)/(double)prec;

             if( (x*x + y*y) <= 1){
                insidePoints++;
             }
         }
         System.out.println("Watek " + Thread.currentThread().getId() + " dziala, liczba punktow w srodku " + insidePoints);
    }

    public int getCount(){
         return this.insidePoints;
    }
}

public class Main{
       
    public static void main(String[] args){

        int totalPoints = 1000000;
            int insidePoints = 0;
            int threadsToBeUsed = 1;

            Scanner scan = new Scanner(System.in);
            threadsToBeUsed = 4;
            
             try{
                int eachThreadPoints = totalPoints/threadsToBeUsed;
                MyThread[] threads = new MyThread[threadsToBeUsed];

                for(int i = 0; i < threadsToBeUsed ; ++i){
                    if( i == (threadsToBeUsed-1)){
                       eachThreadPoints += (totalPoints)%eachThreadPoints ;
                    }    
                 threads[i] = new MyThread(insidePoints, eachThreadPoints);
                 threads[i].start();
              }

              for( int  i=0; i < threadsToBeUsed; ++i)
                 threads[i].join();
              
              for( int  i=0; i < threadsToBeUsed; ++i)
                  insidePoints = insidePoints + threads[i].getCount();

               System.out.println("Number of inside points :" + insidePoints);
               System.out.println("Pi/4 = " + (double)insidePoints/(double)totalPoints);
               System.out.println("Pi   = " + 4*(double)insidePoints/(double)totalPoints);

               } catch(Exception e){
               }
          }
}