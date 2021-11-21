#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <math.h>
#include <time.h>
#include <sys/types.h>
#include <sys/wait.h>
#include "err.h"

int main ()
{
pid_t pid;
int i, j, n;
long double pi, term, sum;

int NR_PROC;
printf("Podaj liczbe procesow: \n");
scanf("%d", &NR_PROC);
/* tworzy nowe procesy */
for (i = 1; i <= NR_PROC; i++)
switch (pid = fork()) {
case -1: /* blad */

case 0: /* proces potomny */
srand(time(NULL) - i*2);
n = rand() % (5000 + 1 - 100) + 100;
sum = 0.0;
 for(j=0;j< n;j++)
 {
  term = pow(-1, j) / (2*j+1);
  sum += term;
 }
 pi = 4 * sum;

 printf("PI = %.10Lf\n", pi);

return 0;
default: 
break;
} 


return 0;
}



