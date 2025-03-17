#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

typedef struct student {
	char name[20];
	char sex;
	int stid;
	int sub1, sub2, sub3;
	double avg;
} ST;

int input(ST* st);

int hw31() {
	int i;
	ST st[3] = { {"kdhong", 'm', 1508001, 0, 0, 0.0}, 
		{"yhkim", 'f', 1608001, 0, 0, 0.0}, 
		{"cskim", 'm', 1608021, 0, 0, 0.0} };
	for (i = 0; i < 3; i++) {
		input(&st[i]);
		printf("main() 함수에서 출력된 값은 %d %d %d 입니다.\n", st[i].sub1, st[i].sub2, st[i].sub3);
	}
}

int input(ST* st) {
	printf("%s 학생의 3과목 성적을 입력하시오(공란으로 구분) : ", st->name);
	scanf("%d %d %d", &st->sub1, &st->sub2, &st->sub3);
	printf("input() 함수에 입력된 값은 %d %d %d 입니다.\n", st->sub1, st->sub2, st->sub3);
}