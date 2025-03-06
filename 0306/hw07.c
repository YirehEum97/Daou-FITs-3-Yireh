#include <stdio.h>

int hw07()
{
	char s;
	int age;
	float height;
	char name[10];

	printf("나이는?");
	scanf_s("%d", &age);
	printf("성별은?(남자라면 M 여자라면 F)");
	scanf_s("%c", &s, 1);
	s = getchar();	
	printf("키는?");		
	scanf_s("%f", &height);

	printf("=============================\n");
	printf("성별 : %c\n", s);
	printf("나이 : %d\n", age);
	printf("키 : %.1fcm\n\n", height);

	return 0;
}