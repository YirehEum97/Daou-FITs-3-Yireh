#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

int hw15() {
	int freeze[] = { 15, 0, -20, -30, 50, -5, -120, -5, 10, -12 };
	int m = freeze[0];
	for (int i = 1; i < 10; i++) {
		m = m > freeze[i] ? freeze[i] : m;
	}

	printf("어는 점이 가장 낮은 물질의 어는 점은 %d도 입니다.\n", m);
}