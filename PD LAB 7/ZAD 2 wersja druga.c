//Obliczanie pola koła wpisanego w kwadrat o boku równym 2 za pomocą algorytmu Monte Carlo I MPI

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include "mpi.h"


int main(int argc, char **argv)
{
    
    int numer_procesu;
    int liczba_procesow;
    int pi;
    MPI_Init(&argc, &argv);
    
    MPI_Status status;
    MPI_Comm_rank(MPI_COMM_WORLD, &numer_procesu);
    MPI_Comm_size(MPI_COMM_WORLD, &liczba_procesow);
    
    if(argc != 2){
        if(numer_procesu == 0)
        printf("Użycie: mpirun -np [liczba watkow] nazwapliku.exe [max liczba punktow]\n");
        exit(0);
    }
    int totalPoints = atoi(argv[1]);

    int prec = 1000000;
    int points = totalPoints / liczba_procesow;
    int insidePoints = 0;
    
    srand(time(NULL) - numer_procesu*4);
    for( int i=0;i < points; ++i){
      
    double rX = (double)rand() / RAND_MAX;
    double rY = (double)rand() / RAND_MAX;
    rX = rX * (prec+1);
    rY = rY * (prec+1);
    double x = rX/(double)(prec);
    double y = rY/(double)(prec);

        
    if( (x*x + y*y) <= 1){
        insidePoints++;
    }
    }
    printf("Proces %d, Liczba punktow w srodku: %d\n", numer_procesu, insidePoints);
    MPI_Reduce(&insidePoints, &pi, 1, MPI_INT, MPI_SUM, 0, MPI_COMM_WORLD);
        
        if(numer_procesu == 0) {
            
            printf("Suma punktów: %d", pi);
            double pole = (double)(pi)/(double)(totalPoints);
            printf("Pole kola wynosi : %lf\n", pole*4);
        }
    MPI_Finalize();
 }
