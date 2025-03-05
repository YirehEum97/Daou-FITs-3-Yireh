#include <stdio.h>

int hw5()
{
	int std_no, kor, world, math;
	double ave;

	std_no = 2013901;
	kor = 89;
	world = 100;
	math = 95;

	ave = (kor + world + math) / 3;

	printf("%-6s : %d\n","학번", std_no);
	printf("%-6s : %6d점\n", "국어", kor);
	printf("%-6s : %6d점\n", "세계사", world);
	printf("%-6s : %6d점\n", "수학", math);
	printf("%-6s : %6.2f점\n", "평균", ave);

	return 0;
}