#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

int nmax(int a, int b);
int nmin(int a, int b);

int hw27() {
	int (*fpmm)(int, int);
	int num1, num2, flag = 0;
	scanf("%d %d", &num1, &num2);
	printf("구하려는 것은 1.최대값  2.최소값\n");
	scanf("%d", &flag);
	if (flag == 1) {
		fpmm = nmax;
	}
	else {
		fpmm = nmin;
	}
	printf("%d", fpmm(num1, num2));

	return 0;
}

int nmax(int a, int b) {
	return a >= b ? a : b;
}

int nmin(int a, int b) {
	return a < b ? a : b;
}

