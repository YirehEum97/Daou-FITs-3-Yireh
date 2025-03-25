#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <oci.h>
#include <string.h>
#include "transaction.h"

static OCIEnv* envhp;
static OCIError* errhp;
static OCISvcCtx* svchp;
static OCISession* usrhp;
static OCIServer* srvhp;
static OCIStmt* stmthp;
static OCIDefine* def1 = NULL, * def2 = NULL, * def3 = NULL;
static OCIDefine* def4 = NULL, * def5 = NULL, * def6 = NULL;
static sword status;

// 거래 배열
Transaction* transactions = NULL;
int transaction_count = 0;


// 에러 체크 함수 (필요시 주석 해제)
void checkerr(OCIError* errhp, sword status) {
    if (status != OCI_SUCCESS && status != OCI_SUCCESS_WITH_INFO) {
        text errbuf[512];
        sb4 errcode = 0;
        OCIErrorGet(errhp, 1, NULL, &errcode, errbuf, sizeof(errbuf), OCI_HTYPE_ERROR);
        printf("OCI Error: %s\n", errbuf);
    }
}

void init_oci() {
    // DB 로그인 정보
    char* username = "C##yireh";
    char* password = "dlfp153";
    char* dbname = "localhost:1521/xe";

    // OCI 환경 및 핸들 초기화
    OCIEnvCreate(&envhp, OCI_DEFAULT, NULL, NULL, NULL, NULL, 0, NULL);
    OCIHandleAlloc(envhp, (void**)&errhp, OCI_HTYPE_ERROR, 0, NULL);
    OCIHandleAlloc(envhp, (void**)&srvhp, OCI_HTYPE_SERVER, 0, NULL);
    OCIServerAttach(srvhp, errhp, (OraText*)dbname, (ub4)strlen(dbname), OCI_DEFAULT);
    OCIHandleAlloc(envhp, (void**)&svchp, OCI_HTYPE_SVCCTX, 0, NULL);
    OCIAttrSet(svchp, OCI_HTYPE_SVCCTX, srvhp, 0, OCI_ATTR_SERVER, errhp);
    OCIHandleAlloc(envhp, (void**)&usrhp, OCI_HTYPE_SESSION, 0, NULL);
    OCIAttrSet(usrhp, OCI_HTYPE_SESSION, username, (ub4)strlen(username), OCI_ATTR_USERNAME, errhp);
    OCIAttrSet(usrhp, OCI_HTYPE_SESSION, password, (ub4)strlen(password), OCI_ATTR_PASSWORD, errhp);
    status = OCISessionBegin(svchp, errhp, usrhp, OCI_CRED_RDBMS, OCI_DEFAULT);
    //checkerr(errhp, status);
    OCIAttrSet(svchp, OCI_HTYPE_SVCCTX, usrhp, 0, OCI_ATTR_SESSION, errhp);

    // SQL 구문 준비
    char* select_sql = "SELECT ID, customer_name, stock, type, amount, price FROM TRANSACTIONS ORDER BY ID";
    OCIHandleAlloc(envhp, (void**)&stmthp, OCI_HTYPE_STMT, 0, NULL);
    status = OCIStmtPrepare(stmthp, errhp, (text*)select_sql, (ub4)strlen(select_sql), OCI_NTV_SYNTAX, OCI_DEFAULT);
    //checkerr(errhp, status);

    // 프리패치 행 수 설정
    ub4 prefetch = 1;
    OCIAttrSet(stmthp, OCI_HTYPE_STMT, &prefetch, 0, OCI_ATTR_PREFETCH_ROWS, errhp);

    // SELECT 구문 실행 (SELECT는 DML과 달리 row count를 미리 알 수 없으므로 0을 지정)
    status = OCIStmtExecute(svchp, stmthp, errhp, 0, 0, NULL, NULL, OCI_DEFAULT);
    //checkerr(errhp, status);

    // fetch 시 사용할 임시 구조체 생성 (fetch된 데이터를 저장할 변수)
    Transaction temp;
    memset(&temp, 0, sizeof(Transaction));

    // 각 컬럼에 대해 별도의 indicator 변수를 선언
    sb2 ind_id = 0;
    sb2 ind_customer_name = 0;
    sb2 ind_stock = 0;
    sb2 ind_type = 0;
    sb2 ind_amount = 0;
    sb2 ind_price = 0;

    // 각 컬럼을 temp 변수에 바인딩 (indicator 변수도 함께 전달)
    OCIDefineByPos(stmthp, &def1, errhp, 1, &temp.id, sizeof(temp.id), SQLT_INT, &ind_id, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmthp, &def2, errhp, 2, temp.customer_name, MAX_NAME_LEN, SQLT_STR, &ind_customer_name, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmthp, &def3, errhp, 3, temp.stock, sizeof(temp.stock), SQLT_STR, &ind_stock, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmthp, &def4, errhp, 4, &temp.type, sizeof(temp.type), SQLT_INT, &ind_type, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmthp, &def5, errhp, 5, &temp.amount, sizeof(temp.amount), SQLT_INT, &ind_amount, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmthp, &def6, errhp, 6, &temp.price, sizeof(temp.price), SQLT_FLT, &ind_price, NULL, NULL, OCI_DEFAULT);

    // 행 단위로 fetch하여 transactions 배열에 저장
    while ((status = OCIStmtFetch(stmthp, errhp, 1, OCI_FETCH_NEXT, OCI_DEFAULT)) == OCI_SUCCESS) {
        // 필요시, 각 indicator 값이 -1 (NULL)인 경우 기본값 할당
        if (ind_id == -1)          temp.id = 0;
        if (ind_customer_name == -1) strcpy(temp.customer_name, "");
        if (ind_stock == -1)       strcpy(temp.stock, "");
        if (ind_type == -1)        temp.type = 0;
        if (ind_amount == -1)      temp.amount = 0;
        if (ind_price == -1)       temp.price = 0.0;

        // transactions 배열 재할당
        Transaction* new_transactions = realloc(transactions, sizeof(Transaction) * (transaction_count + 1));
        if (new_transactions == NULL) {
            printf("메모리 할당 실패\n");
            break;
        }
        transactions = new_transactions;
        transactions[transaction_count] = temp;
        transaction_count++;

        // 다음 행을 위한 temp와 indicator 변수 초기화
        memset(&temp, 0, sizeof(Transaction));
        ind_id = ind_customer_name = ind_stock = ind_type = ind_amount = ind_price = 0;
    }

    if (transaction_count == 0) {
        printf("No data found.\n");
    }
    /*else {
        for (int i = 0; i < transaction_count; i++) {
            printf("ID: %d, Customer: %s, Stock: %s, Type: %d, Amount: %d, Price: %f\n",
                transactions[i].id, transactions[i].customer_name,
                transactions[i].stock, transactions[i].type,
                transactions[i].amount, transactions[i].price);
        }
    }*/
}

void end_oci() {
	OCIHandleFree(stmthp, OCI_HTYPE_STMT);
	OCILogoff(svchp, errhp);
	OCIHandleFree(usrhp, OCI_HTYPE_SESSION);
	OCIHandleFree(svchp, OCI_HTYPE_SVCCTX);
	OCIHandleFree(srvhp, OCI_HTYPE_SERVER);
	OCIHandleFree(errhp, OCI_HTYPE_ERROR);
	OCIHandleFree(envhp, OCI_HTYPE_ENV);
}

void add_transaction() {
    transaction_count++;
    Transaction* temp = realloc(transactions, transaction_count * sizeof(Transaction));
    if (temp == NULL) {
        printf("메모리 할당 실패!\n");
        return;
    }
    transactions = temp;
    Transaction* new_transaction = &transactions[transaction_count - 1];

    // 고객 이름 입력
    printf("고객 이름: ");
    getchar(); // 버퍼 비우기
    fgets(new_transaction->customer_name, MAX_NAME_LEN, stdin);
    new_transaction->customer_name[strcspn(new_transaction->customer_name, "\n")] = '\0';

    // 주식 종목 입력
    printf("주식 종목명: ");
    fgets(new_transaction->stock, MAX_NAME_LEN, stdin);
    new_transaction->stock[strcspn(new_transaction->stock, "\n")] = '\0';

    // 거래 유형 입력
    printf("거래 유형 (0: 매수, 1: 매도): ");
    scanf("%d", &new_transaction->type);

    // 거래 정보 입력
    printf("거래 수량: ");
    scanf("%d", &new_transaction->amount);
    printf("거래 가격: ");
    scanf("%lf", &new_transaction->price);

    // -----------------------------
    // 1. 시퀀스의 NEXTVAL을 가져옴
    int id;
    char* id_sql = "SELECT id_seq.NEXTVAL FROM dual";
    OCIStmt* stmthp_id = NULL;
    OCIDefine* def_id = NULL;
    OCIHandleAlloc(envhp, (void**)&stmthp_id, OCI_HTYPE_STMT, 0, NULL);
    status = OCIStmtPrepare(stmthp_id, errhp, (text*)id_sql, (ub4)strlen(id_sql), OCI_NTV_SYNTAX, OCI_DEFAULT);
    // 먼저 바인딩하고
    OCIDefineByPos(stmthp_id, &def_id, errhp, 1, &id, sizeof(id), SQLT_INT, NULL, NULL, NULL, OCI_DEFAULT);
    // 그 후 실행 및 fetch
    status = OCIStmtExecute(svchp, stmthp_id, errhp, 1, 0, NULL, NULL, OCI_DEFAULT);
    status = OCIStmtFetch(stmthp_id, errhp, 1, OCI_FETCH_NEXT, OCI_DEFAULT);
    new_transaction->id = id;
    OCIHandleFree(stmthp_id, OCI_HTYPE_STMT);

    // -----------------------------

    // INSERT SQL 실행
    char* insert_sql = "INSERT INTO transactions (ID, CUSTOMER_NAME, STOCK, TYPE, AMOUNT, PRICE) VALUES (id_seq.CURRVAL, :1, :2, :3, :4, :5)";
    OCIStmt* stmthp_ins = NULL;  // INSERT용 별도 핸들
    OCIHandleAlloc(envhp, (void**)&stmthp_ins, OCI_HTYPE_STMT, 0, NULL);
    status = OCIStmtPrepare(stmthp_ins, errhp, (text*)insert_sql, (ub4)strlen(insert_sql), OCI_NTV_SYNTAX, OCI_DEFAULT);

    // 바인딩 변수 설정 (INSERT)
    OCIBindByPos(stmthp_ins, &def2, errhp, 1, new_transaction->customer_name, MAX_NAME_LEN, SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp_ins, &def3, errhp, 2, new_transaction->stock, MAX_NAME_LEN, SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp_ins, &def4, errhp, 3, &new_transaction->type, sizeof(new_transaction->type), SQLT_INT, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp_ins, &def5, errhp, 4, &new_transaction->amount, sizeof(new_transaction->amount), SQLT_INT, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp_ins, &def6, errhp, 5, &new_transaction->price, sizeof(new_transaction->price), SQLT_FLT, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);

    status = OCIStmtExecute(svchp, stmthp_ins, errhp, 1, 0, NULL, NULL, OCI_COMMIT_ON_SUCCESS);
    if (status != OCI_SUCCESS && status != OCI_SUCCESS_WITH_INFO) {
        checkerr(errhp, status);
    }
    OCIHandleFree(stmthp_ins, OCI_HTYPE_STMT); // INSERT용 핸들도 해제
}

// 거래 출력
void print_transactions() {
    for (int i = 0; i < transaction_count; i++) {
        Transaction* t = &transactions[i];
        printf("ID: %d, 고객 이름: %s, 종목: %s, 거래 유형: %s, 수량: %d, 가격: %.2f원\n",
            t->id, t->customer_name, t->stock, t->type == BUY ? "매수" : "매도", t->amount, t->price);
    }
}

void search_transaction(){
	printf("검색할 고객 이름: ");
	char search_name[MAX_NAME_LEN];
	getchar();
	fgets(search_name, MAX_NAME_LEN, stdin);
	search_name[strcspn(search_name, "\n")] = '\0';
	for (int i = 0; i < transaction_count; i++) {
		if (strcmp(transactions[i].customer_name, search_name) == 0) {
			printf("ID: %d, 고객 이름: %s, 종목: %s, 거래 유형: %s, 수량: %d, 가격: %.2f원\n",
				transactions[i].id, transactions[i].customer_name, transactions[i].stock, transactions[i].type == BUY ? "매수" : "매도", transactions[i].amount, transactions[i].price);
		}
	}
}

void edit_transaction() {
	printf("수정할 거래 ID: ");
	int edit_id;
	int edit_amount;
	double edit_price;
	scanf("%d", &edit_id);
	int flag = 0;
	for (int i = 0; i < transaction_count; i++) {
		if (transactions[i].id == edit_id) {
			Transaction* t = &transactions[i];
			printf("새 수량: ");
			scanf("%d", &edit_amount);
			t->amount = edit_amount;
			printf("새 가격: ");
			scanf("%lf", &edit_price);
			t->price = edit_price;
			flag = 1;
			break;
		}
	}
    if (flag == 0) {
        printf("해당 ID의 거래를 찾을 수 없습니다.\n");
        return;
    }

    char* update_sql = "UPDATE transactions SET AMOUNT = :1, PRICE = :2 WHERE CUSTOMER_ID = :3";
    OCIHandleAlloc(envhp, (void**)&stmthp, OCI_HTYPE_STMT, 0, NULL);
    OCIStmtPrepare(stmthp, errhp, (text*)update_sql, strlen(update_sql),
        OCI_NTV_SYNTAX, OCI_DEFAULT);
    // 바인딩 변수 설정 (UPDATE)
    OCIBindByPos(stmthp, &def1, errhp, 1, &edit_amount, sizeof(edit_amount),
        SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp, &def2, errhp, 2, &edit_price, sizeof(edit_price), SQLT_INT, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp, &def3, errhp, 3, &edit_id, sizeof(edit_id), SQLT_INT, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    // SQL 실행
    OCIStmtExecute(svchp, stmthp, errhp, 1, 0, NULL, NULL, OCI_COMMIT_ON_SUCCESS);
}

void delete_transaction() {
	printf("삭제할 거래 ID: ");
	int delete_id;
	scanf("%d", &delete_id);
    for (int i = 0; i < transaction_count; i++) {
        if (transactions[i].id == delete_id) {
            for (int j = i; j < transaction_count - 1; j++) {
                transactions[j] = transactions[j + 1];
            }
            transaction_count--;
            Transaction* temp = realloc(transactions, transaction_count * sizeof(Transaction));
            if (temp == NULL) {
                printf("메모리 할당 실패!\n");
                return;
            }
            transactions = temp;
            break;
        }
    }

    char* delete_sql = "DELETE FROM transactions WHERE ID = :1"; // ID를 기준으로 삭제
    OCIHandleAlloc(envhp, (void**)&stmthp, OCI_HTYPE_STMT, 0, NULL);
    OCIStmtPrepare(stmthp, errhp, (text*)delete_sql, strlen(delete_sql), OCI_NTV_SYNTAX, OCI_DEFAULT);
    // 바인딩 변수 설정 (DELETE)
    OCIBindByPos(stmthp, &def1, errhp, 1, &delete_id, sizeof(delete_id), SQLT_INT, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    // SQL 실행
    /*if (OCIStmtExecute(svchp, stmthp, errhp, 1, 0, NULL, NULL,
        OCI_COMMIT_ON_SUCCESS) != OCI_SUCCESS) {
        check_error(errhp);
    }*/
    OCIStmtExecute(svchp, stmthp, errhp, 1, 0, NULL, NULL, OCI_COMMIT_ON_SUCCESS);
}

// 메모리 해제
void free_transactions() {
	free(transactions);
	transactions = NULL;
	end_oci();
}