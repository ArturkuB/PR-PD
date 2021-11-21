#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <math.h>
#include <time.h>
#include <sys/types.h>
#include <sys/wait.h>
#include "err.h"

double fun(double x)
{
	return (4 * x) - (6 * x) + 5;
}
	


int main ()
{
pid_t pid;



int NR_PROC;
printf("Podaj liczbe procesow: ");
scanf("%d", &NR_PROC);
/* tworzy nowe procesy */
int i;
for (i = 1; i <= NR_PROC; i++)
switch (pid = fork()) {
case -1: /* blad */
break;
case 0: /* proces potomny */
srand(time(NULL) - i*2);
int aa, bb, nn;
aa = rand() % (10 + 1 - 0) + 0;
bb = rand() % (20 + 1 - 0) + 0;
nn = rand() % (1 + 1 - 10) + 10;

double a = (double)aa;
double b = (double)bb;
double n = (double)nn;
while(a > b)
{
    a = rand() % (1 + 1 - 10) + 10;
}
printf("A = %d, B = %d, n = %d ", aa, bb, nn);

double h = (b - a) / n;
double x[nn];
double sum = 0.0;
int j;
for(j = 1; j < n; j++)
{
	double temp = j / n;
	x[j-1] = a + (temp * (b - a));
}
int k;
for(k = 0; k < n-1; k++)
{
		sum = sum + fun(x[k]);
}

sum = sum + (fun(a) / 2) + (fun(b) / 2);
printf("Wynik: %lf\n",sum * h);
return 0;
} 

for (i = 1; i <= NR_PROC; i++)
if (wait(0) == -1)
return 0;

return 0;
}
	




