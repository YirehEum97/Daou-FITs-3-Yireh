// 실습 과제 53
#define ONE

//#ifdef !ONE	// 오류 발생, 논리부정연산자 사용 불가
#ifndef ONE
int a = 1;
#else
int a = 2;
#endif
void practice_53() {
	printf("a: %d", a);
}

// 실습 과제 54
#define X 1

void practice_54() {
#if (X==1)
	printf("X is 1");
#elif (X==2)
	printf("X is 2");
#else
	printf("X is nothing");
#endif
}