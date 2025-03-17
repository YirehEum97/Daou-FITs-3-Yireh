#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

typedef struct student {
	char name[20];
	char sex;
	int stid;
	int sub1, sub2, sub3;
	double avg;
	struct student* nextst;
} ST;

int input(ST* st);

int hw33() {
	int i;
	ST st1 = { "kdhong", 'm', 1508001, 0, 0, 0, 0.0, NULL };
	ST st2 = { "yhkim", 'f', 1608001, 0, 0, 0, 0.0, NULL };
	ST st3 = { "cskim", 'm', 1608021, 0, 0, 0, 0.0, NULL };
	st1.nextst = &st2;
	st2.nextst = &st3;
	input(&st1);
	printf("%s 학생의 3과목 평균 : %5.2f입니다.\n", st1.name, st1.avg);
	printf("%s 학생의 3과목 평균 : %5.2f입니다.\n", st2.name, st2.avg);
	printf("%s 학생의 3과목 평균 : %5.2f입니다.\n", st3.name, st3.avg);


	return 0;
}

int input(ST* st) {
	int i;
	while (1) {
		printf("%s 학생의 3과목 성적을 입력하세요(공란으로 구분) : ", st->name);
		scanf("%d %d %d", &st->sub1, &st->sub2, &st->sub3);
		st->avg = (double)(st->sub1 + st->sub2 + st->sub3) / 3.0;
		printf("%s 학생의 3과목 성적으로 입력된 값은 %d, %d, %d입니다.\n", st->name, st->sub1, st->sub2, st->sub3);

		if (st->nextst == NULL) break;
		else st = st->nextst;																											
	}
}