#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <math.h>
#include <string.h>
#include <stdlib.h>

void f901();
void f902();
void f903();
void f911();
void f912();
void f913();
void f921();
//int add(int a, int b) {
//	return a + b;
//}
//int sub(int a, int b) {
//	return a - b;
//}
//int mul(int a, int b) {
//	return a * b;
//}
//int div(int a, int b) {
//	return a / b;
//}
void f923();
int compare_asc(const int* a, const int* b) { //비교 요소의 형식을 갖춘 매개변수
	return(*a - *b);//오름차순
}
int compare_desc(const int* a, const int* b) { //비교 요소의 형식을 갖춘 매개변수
	return(*b - *a);//오름차순
}
void f931();

int main() {
	f931();

	return 0;
}

void f901() {
	int balance[5] = { 1000,5000,3000,7000,2000 };
	int* p = balance;
	printf("현재 계좌 잔액 : ");
	for (int i = 0; i < 5; i++) {
		printf("%d ", *(p + i));
	}
	printf("\n\n");
	int transact[5] = { 500,-1000,2000,-500,1000 };
	for (int i = 0; i < 5; i++) {
		printf("%d번 고객 %s할 금액: %d\n", i, i > 0 ? "출금" : "입금", abs(transact[i]));
		*(p + i) += transact[i];
	}
	printf("\n\n업데이트된 계좌 잔액 : ");
	int M = *p;
	int m = *p;
	for (int i = 0; i < 5; i++) {
		printf("%d ", *(p + i));
		M = M < *(p + i) ? *(p + i) : M;
		m = m > *(p + i) ? *(p + i) : m;
	}
	printf("\n\n최대 잔액 : %d\n최소 잔액 : %d", M,m);
}

void f902() {
	int accounts[] = { 101,102,103,104,105 };
	int passwords[] = { 1234,4321,5678,8765,3456 };

	printf("계좌번호 입력: ");
	int account;
	scanf("%d", &account);
	printf("비밀번호 입력: ");
	int password;
	scanf("%d", &password);

	int i;
	for (i = 0; i < 5; i++) {
		if (accounts[i] == account) {
			if (passwords[i] == password) {
				printf("로그인 성공\n");
				break;
			}
			else {
				printf("비밀번호가 틀렸습니다\n");
				break;
			}
		}
	}
}

void f903() {
	int score[5][5];
	float avg[5] = { 0.0 };

	printf("학생들의 성적을 입력하십시오 (각 학생당 5과목)\n");
	for (int i = 0; i < 5; i++) {
		printf("%d번 학생의 성적: ", i + 1);
		for (int j = 0; j < 5; j++) {
			//scanf("%d", &score[i][j]);
			scanf("%d", (*(score + i)) + j);
			avg[i] += score[i][j];
		}
		avg[i] /= 5.0;
	}
	printf("\n\n학생들의 평균 성적:\n");
	for (int i = 0; i < 5; i++) {
		printf("학생 %d 평균: %.1f\n", i, avg[i]);
	}
	int M = 0;
	for (int i = 1; i < 5; i++) {
		M = avg[M] > avg[i] ? M : i;
	}
	printf("\n가장 높은 평균을 가진 학생은 학생 %d 입니다.", M + 1);
}

void f911() {
	printf("3X3 행렬을 입력하십시오\n");
	int matrix[3][3];
	int* ptr[3] = { matrix[0],matrix[1],matrix[2] };
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			scanf("%d", &matrix[i][j]);

		}
	}

	printf("\n제곱된 행렬:\n");
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			*(*(ptr + i) + j) *= *(*(ptr + i) + j);
			printf("%2d ", *(*(ptr + i) + j));
		}
		printf("\n");
	}

	printf("\n대각선 반전된 행렬 (전치행렬):\n");
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			printf("%2d ", *(*(ptr + j) + i));
		}
		printf("\n");
	}
}

void f912() {
	char str[3][20];
	int length[3] = { 0 };
	printf("문자열 3개를 입력하세요.\n");
	for (int i = 0; i < 3; i++) {
		gets_s(str[i], 20);
		length[i] = strlen(str[i]);
	}

	printf("\n대문자로 변환된 문자열:\n");
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < length[i]; j++) {
			if (str[i][j] >= 'a' && str[i][j] <= 'z') {
				*(*(str + i) + j) -= 32;
			}
			printf("%c", *(*(str + i) + j));
		}
		printf("\n");
	}

	printf("\n\n문자열 길이순 정렬:\n");
	for (int i = 0; i < 2; i++) {
		for (int j = i + 1; j < 3; j++) {
			if (length[i] > length[j]) {
				char temp[20];
				strcpy(temp, str[i]);
				strcpy(str[i], str[j]);
				strcpy(str[j], temp);
				int t = length[i];
				length[i] = length[j];
				length[j] = t;
			}
		}
	}
	for (int i = 0; i < 3; i++) {
		printf("%s\n", *(str+i));
	}
}

void f913() {
	int matrix[3][3];
	int* ptr[3] = { matrix[0],matrix[1],matrix[2] };
	printf("3X3 행렬을 입력하십시오\n");
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			scanf("%d", (*(ptr + i)) + j);
		}
	}
	printf("\n\n90도 회전된 행렬 :\n");
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			printf("%d ", *(*(ptr + 2 - j) + i));
		}
		printf("\n");
	}
}

//void f921() {
//	int num1, num2, choice;
//	int (*fp)(int, int);
//	printf("1. 덧셈\n2. 뺄셈\n3. 곱셈\n4. 나눗셈\n선택: ");
//	scanf("%d", &choice);
//	printf("두 정수를 입력하십시오.\n");
//	scanf("%d %d", &num1, &num2);
//
//	switch (choice) {
//	case 1:
//		fp = add;
//		break;
//	case 2:
//		fp = sub;
//		break;
//	case 3:
//		fp = mul;
//		break;
//	case 4:
//		fp = div;
//		break;
//	default:
//		fp = add;
//		break;
//	}
//	printf("결과: %d", fp(num1, num2));
//}

void f923() {
	int arr[5], choice;
	int (*compare)(const int*, const int*);
	printf("5개의 정수를 입력하십시오\n");
	for (int i = 0; i < 5; i++) {
		scanf("%d", &arr[i]);
	}
	printf("1. 오름차순 정렬\n2. 내림차순 정렬\n선택: ");
	scanf("%d", &choice);
	if (choice == 1) {
		compare = compare_asc;
	}
	else {
		compare = compare_desc;
	}
	qsort(arr,5,sizeof(int),compare);
	printf("\n정렬된 배열: ");
	for (int i = 0; i < 5; i++) {
		printf("%d ", arr[i]);
	}
}

void f931() {
	int matrix[5][5], row, col, del;
	printf("행과 열의 수를 입력하시오 : ");
	scanf("%d %d", &row, &col);
	printf("배열의 초기값은 자동으로 입력됩니다.\n");
	for (int i = 0; i < row; i++) {
		for (int j = 0; j < col; j++) {
			matrix[i][j] = 10 * i + j;
			printf("%2d ", matrix[i][j]);
		}
		printf("\n");
	}
	printf("\n\n삭제하려는 행의 번호를 입력하시오 : \n");
	scanf("%d", &del);
	if (del < 0 || del >= row) {
		printf("잘못된 행 번호입니다.\n");
	}
	else {
		for (int i = del; i < row - 1; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = matrix[i + 1][j];
			}
		}
		row--;
		printf("\n\n수정된 배열:\n");
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				printf("%2d ", matrix[i][j]);
			}
			printf("\n");
		}
	}
}










