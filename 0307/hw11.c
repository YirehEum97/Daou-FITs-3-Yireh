#include <stdio.h>

void calculator();
void multiple();
void underUpper();

int hw11() {
	//calculator();
	//multiple();
	underUpper();

	return 0;
}

void calculator() {
	printf("가감승제를 원하는 두 수를 입력하세요 : ");
	int a, b;
	scanf_s("%d %d", &a, &b);
	printf("%d + %d = %d\n", a, b, a + b);
	printf("%d - %d = %d\n", a, b, a - b);
	printf("%d * %d = %d\n", a, b, a * b);
	printf("%d / %d = %f\n", a, b, (float)a / (float)b);
}

void multiple() {
	printf("구구단 몇단을 출력할까요? : ");
	int n;
	scanf_s("%d", &n);
	for (int i = 1; i <= 9; i++) {
		printf("%d X %d = %d\n", n, i, n * i);
	}
}

void underUpper() {
	printf("문자열 입력 : ");
	char c[100];
	gets_s(c, 100);

	for (int i = 0; c[i] != '\0'; i++) {
		if (c[i] >= 'a' && c[i] <= 'z') {
			c[i] -= 32;
		}
		else if (c[i] >= 'A' && c[i] <= 'Z') {
			c[i] += 32;
		}
	}
	printf("변환된 문자열 : %s\n", c);
}