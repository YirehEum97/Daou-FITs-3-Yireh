#include <stdio.h>

int main() {
	printf("=====================\n");
	printf("1. 원의 둘레 구하기\n");
	printf("2. 원의 넓이 구하기\n");
	printf("3. 구의 부피 구하기\n");
	printf("4. 그만두기\n");
	printf("=====================\n");
	int menu;
	do {
		printf("메뉴를 선택하시오\n");
		scanf_s("%d", &menu);
		double r;
		switch (menu) {
		case 1:
			printf("반지름을 입력하시오\n");
			scanf_s("%lf", &r);
			printf("원의 둘레는 %.1f입니다.\n", 2 * 3.141592 * r);
			break;
		case 2:
			printf("반지름을 입력하시오\n");
			scanf_s("%lf", &r);
			printf("원의 넓이는 %.1f입니다.\n", 3.141592 * r * r);
			break;
		case 3:
			printf("구의 반지름을 입력하시오\n");
			scanf_s("%lf", &r);
			printf("구의 부피는 %.1f입니다.\n", 4.0 / 3.0 * 3.141592 * r * r * r);
			break;
		}
	} while (menu != 4);
	
	printf("정수 n 입력 >>");
	int n;
	scanf_s("%d", &n);
	int a = n / 2;
	printf("정수 1에서 %d이하의 짝수들의 합은 %d입니다.\n", n, a * (a + 1));

	return 0;
}