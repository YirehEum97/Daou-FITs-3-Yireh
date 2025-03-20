#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

//hw48
int main() {
	FILE* f = fopen("customers.txt", "wb");

	char str[] = "C 프로그래밍";

	if (f) {
		fwrite(str, 1, sezeof(str) - 1, f);
		fclose(f);
	}
	else printf("Error: %d, %s", errno, strerror(errno));

	return 0;
}

//hw49
int main() {
	FILE* f = fopen("customers.txt", "wb+");
	if (f) {
		fputc('A', f);

		fseek(f, 0, SEEK_SET);
		printf("%c\n", fgetc(f));
		
		fclose(f);
	}
	else printf("Error: %d, %s", errno, strerror(errno));

	return 0;
}

//hw50
int main() {
	FILE* f = fopen("customers.txt", "wb+");
	char str[128];
	if (f) {
		fputs("0123456789\r\n0123456789", f);
		fseek(f, 0, SEEK_SET);
		fgets(str, sizeof(str), f);
		printf("read : %s\n", str);
		fclose(f);
	}
	else printf("Error: %d, %s", errno, strerror(errno));
	return 0;
}