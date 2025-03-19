#include <stdio.h>
#include <errno.h>
#include <string.h>

int hw42() {
	FILE* f = fopen("newtest.txt", "wb");
	if (f) {
		fclose(f);
	}
	else {
		printf("Error: %s\n", strerror(errno));
	}
	return 0;
}