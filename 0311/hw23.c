#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

int hw23() {
	int i, * ip = &i;
	int sum = 0, * sump = &sum;
	for (*ip = 1; *ip <= 100; (*ip)++) {
		*sump += *ip;
	}
	printf("포인터 변수를 사용한 1부터 100까지의 합 : %d\n", sum);

	return 0;
}