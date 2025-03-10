#define _CRT_SECURE_NO_WARNINGS
#define SIZE 5

#include <stdio.h>

int main() {
	int i, repeat, temp, b[SIZE] = { 1,2,3,5,4 };
	printf("정렬 전 배열\n");
	for (i = 0; i < SIZE; i++) {
		printf("%d ", b[i]);
	}
	printf("\n");

	char swap;
	for (repeat = 1; repeat < SIZE; repeat++) {
		swap = 'N';
		for (i = 0; i < SIZE - repeat; i++) {
			if (b[i] > b[i + 1]) {
				temp = b[i];
				b[i] = b[i + 1];
				b[i + 1] = temp;
				swap = 'Y';
			}
		}
		if (swap == 'N') {
			break;
		}
	}

	printf("정렬 후 배열\n");
	for (i = 0; i < SIZE; i++) {
		printf("%d ", b[i]);
	}
	printf("\n");

	return 0;
}