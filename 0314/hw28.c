#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

struct game {
	char name[20];
	int R1, R2, R3;
};

int hw28() {
	struct game player;
	double avg;
	printf("선수의 이름은?");
	scanf("%s", player.name);
	printf("1,2,3라운드의 점수는?");
	scanf("%d %d %d", &player.R1, &player.R2, &player.R3);
	avg = (player.R1 + player.R2 + player.R3) / 3.0;
	printf("%s 선수의 평균 점수는 %.2f입니다.", player.name, avg);

	return 0;
}