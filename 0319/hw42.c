#include <stdio.h>

struct Flags {
	unsigned int isVisible : 1;
	unsigned int isClickable : 1;
	unsigned int isDraggable : 1;
	unsigned int type : 2;
};

int hw42() {
	struct Flags flags = { 1, 0, 1, 2 };
	printf("isVisible: %d\n", flags.isVisible);
	printf("isClickable: %d\n", flags.isClickable);
	printf("isDraggable: %d\n", flags.isDraggable);
	printf("type: %d\n", flags.type);
	return 0;
}