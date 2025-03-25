#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "transaction.h"
#include "oci_db.h"

int main() {
	int choice;
	init_oci();
	while (1) {
		printf("1. 거래 추가\n2. 거래 내역 출력\n3. 고객 거래 검색\n4. 거래수정\n5. 거래 삭제\n6. 종료\n선택: ");
		scanf_s("%d", &choice);
		if (choice == 1) {
			add_transaction();
		}
		else if (choice == 2) {
			print_transactions();
		}
		else if (choice == 3) {
			search_transaction();
		}
		else if (choice == 4) {
			edit_transaction();
		}
		else if (choice == 5) {
			delete_transaction();
		}
		else if (choice == 6) {
			free_transactions();
			break;
		}
		else {
			printf("잘못된 선택입니다.\n");
		}
	}
	return 0;
}