#pragma once

#ifndef TRANSACTION
#define TRANSACTION

#define MAX_NAME_LEN 100
// 거래 유형 정의
typedef enum {
	BUY,
	SELL
} TransactionType;


// 거래 구조체
typedef struct {
	int id; // 거래 ID
	char customer_name[MAX_NAME_LEN];
	char stock[MAX_NAME_LEN];
	TransactionType type; // 거래 유형
	int amount;
	double price;
} Transaction;


//int get_next_id();
//void checkerr(OCIError* errhp, sword status);
void init_oci();
void end_oci();
void add_transaction();
void print_transactions();
void free_transactions();
void search_transaction();
void edit_transaction();
void delete_transaction();

#endif