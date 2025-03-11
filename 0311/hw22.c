#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

int hw22() {
	double d = 100.0;
	double* dpoint = &d;
	printf("변수 d의 값 : %f\n", d);
	printf("변수 d의 주소 : %p\n", &d);
	printf("포인터 변수 dpoint의 값 : %p\n", dpoint);
	printf("포인터 변수 dpoint가 가리키는 값 : %f\n", *dpoint);
	printf("변수 d의 크기 : %d\n", sizeof(d));
	printf("변수 d의 주소의 크기 : %d\n", sizeof(&d));
	printf("포인터 변수 dpoint의 크기 : %d\n", sizeof(dpoint));
	printf("포인터 변수 dpoint가 가리키는 값의 크기 : %d\n", sizeof(*dpoint));

	return 0;
}