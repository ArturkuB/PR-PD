#include <stdio.h>
#include "mpi.h"
#include <math.h>

int main(int argc, char **argv){
int p; // p-numer procesu
int pi=0; //liczba która podnosimy do potegi
int n;
int tag=50;
double temp;
double sum = 0;
int it = 0;
MPI_Init(&argc, &argv);
MPI_Comm_rank(MPI_COMM_WORLD, &p);
MPI_Comm_size(MPI_COMM_WORLD, &n);
MPI_Status status;
if(p==0){ // proces zerowy przekazuje do procesu pierwszego wartosci A, potega, suma
MPI_Send(&sum, 1, MPI_DOUBLE ,p+1 ,tag, MPI_COMM_WORLD);
MPI_Send(&it, 1, MPI_INT ,p+1 ,tag, MPI_COMM_WORLD);
}
if((p>0)&&(p<n-1)){ // procesu od 1 do 9

/* odbiera dane od prosesu o jeden mnniejszego */
MPI_Recv(&sum, 1, MPI_DOUBLE, p-1, tag, MPI_COMM_WORLD, &status);
MPI_Recv(&it, 1, MPI_INT, p-1, tag, MPI_COMM_WORLD, &status);
temp = 0;
temp = pow(-1, it) / (2 * it + 1);
it++;
sum += temp;
printf("npr procesu: %d , Przyblizenie PI: %.5lf\n", p, sum * 4);
if(p!=n-1){ // proces 9 (ostatni) nie bŕdzie wysy│a│ dalej
/* po wykonaniu obliczeni wysyla wyniki do procesu o 1 wiekszego */
MPI_Send(&sum, 1, MPI_DOUBLE ,p+1 ,tag, MPI_COMM_WORLD);
MPI_Send(&it, 1, MPI_INT ,p+1 ,tag, MPI_COMM_WORLD);

}
}
MPI_Finalize();
return 0;
}