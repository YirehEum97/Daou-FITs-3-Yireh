#include <stdio.h>

int hw06()
{
	char s;
	int age;
	float height;
	char name [10];

	printf("성별은?(남자라면 M 여자라면 F)");
	//scanf_s("%c", &s, 1);
	s = getchar();
	printf("나이는?");
	scanf_s("%d", &age);
	printf("키는?");
	scanf_s("%f", &height);

	printf("=============================\n");
	printf("성별 : %c\n", s);
	printf("나이 : %d\n", age);
	printf("키 : %.1fcm\n\n", height);


	printf("이름은?");
	scanf_s("%s", name, 10);
	printf("이름 : %s\n", name);

	printf("=============================\n");	
	printf("두개 정수 입력");
	int a, b;
	scanf_s("%d %d", &a, &b);
	printf("첫번째 정수 : %d\n", a);
	printf("두번째 정수 : %d\n", b);
	return 0;
}