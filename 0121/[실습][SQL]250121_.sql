-- 실습
--1. 직무별 상위 3명의 직원
--각 직무별로 급여가 가장 높은 3명의 직원 정보를 조회하세요. 출력 컬럼은 직무, 사번, 이름, 급여, 순위이고 직무, 순위로 정렬합니다.
SELECT *
FROM
    (SELECT
        JOB_ID, EMPLOYEE_ID, NAME, SALARY,
        ROW_NUMBER() OVER(PARTITION BY JOB_ID ORDER BY SALARY) AS RANK_
    FROM EMPLOYEES)
WHERE RANK_<=3
;


--2. 직원 입사 연도별 총 급여 및 누적 급여
--직원들의 입사 연도별 총 급여와 해당 연도의 누적 급여를 계산하세요.
SELECT
    DISTINCT HIRE_YEAR,
    SUM(SALARY) OVER(PARTITION BY HIRE_YEAR) AS SALARY_YEAR,
    SUM(SALARY) OVER(ORDER BY HIRE_YEAR) AS SALARY_TOTAL
FROM
    (SELECT EMPLOYEE_ID, SALARY,
        TO_CHAR(HIRE_DATE,'YYYY') AS HIRE_YEAR
    FROM EMPLOYEES
    WHERE SALARY IS NOT NULL)
;



--3. 직원별 급여 순위와 부서 평균 급여 비교
--모든 직원에 대해 급여 순위를 계산하고, 각 직원의 급여가 부서 평균 급여보다 높은지 확인하세요. 부서 평균 급여보다 높은 경우
--‘높다’라고 출력하고 낮은 경우 ‘낮다’라고 출력합니다. (출력 컬럼은 사번, 이름, 급여, 급여 순위, 부서 평균 급여, 높은지 낮은지 여부)
SELECT
    EMPLOYEE_ID,NAME,SALARY,RANK_,AVG_SALARY,
    (CASE
        WHEN SALARY > AVG_SALARY THEN '높다'
        ELSE '낮다'
    END) AS COMPARE
FROM
    (SELECT
        EMPLOYEE_ID, NAME, DEPARTMENT_ID, SALARY,
        ROUND(AVG(SALARY) OVER(PARTITION BY DEPARTMENT_ID),2) AVG_SALARY,
        RANK() OVER(ORDER BY SALARY DESC NULLS LAST) AS RANK_
    FROM EMPLOYEES)
;


-- 4. 최근 1년간 대출을 받은 고객 중 대출 금액 상위 5명
-- 최근 1년동안 대출 받은 고객의 총 대출 금액을 계산하고, 이 금액이 상위 5명인 고객의 이름과 대출 금액을 출력하세요
SELECT
    (SELECT NAME FROM CUSTOMERS C WHERE C.CUSTOMER_ID = T.CUSTOMER_ID) AS NAME,
    TOTAL_AMOUNT
FROM
    (SELECT
        DISTINCT CUSTOMER_ID,
        SUM(AMOUNT) OVER(PARTITION BY CUSTOMER_ID) AS TOTAL_AMOUNT
    FROM LOANS L
    WHERE MONTHS_BETWEEN(SYSDATE, LOAN_DATE) <= 12
        AND STATUS = 'APPROVED'
    ORDER BY TOTAL_AMOUNT DESC) T
WHERE ROWNUM <= 5
;

-- 5. 이동 평균 (Moving Average)
-- 직원 테이블에서 직원의 급여(SALARY)를 기준으로 직원 ID 순서대로 3명의 이동 평균을 계산하세요.
-- 여기서 이동 평균은 현재 직원의 급여와 바로 이전 직원 2명의 급여를 포함합니다.
-- 직원 ID, 이름, 급여, 그리고 이동 평균을 출력하세요. 이동 평균값은 반올림하여 소수점 아래 둘째 자리까지 표현합니다.
SELECT
    EMPLOYEE_ID, NAME, SALARY,
    ROUND(AVG(SALARY) OVER(ORDER BY EMPLOYEE_ID
    ROWS 2 PRECEDING),2) AS AVG_SALARY
FROM
    EMPLOYEES
;
    
    (SELECT *
    FROM (SELECT * FROM EMPLOYEES ORDER BY EMPLOYEE_ID)
    WHERE ROWNUM<=3)
;

-- 6. 계좌 잔액이 평균 잔액 이상인 고객과 대출 금액이 평균 대출 금액 이상인 고객의 병합
-- 계좌 잔액이 전 계좌의 평균 잔액 이상인 고객과 대출 금액이 전체 평균 대출 금액 이상인 고객을 병합하여 고객 이름과 금액을 조회하세요.
-- 출력 데이터에 출처가 계좌인지 대출인지도 하나의 컬럼으로 나타내주세요.
SELECT
    (SELECT NAME FROM CUSTOMERS C WHERE C.CUSTOMER_ID=L.CUSTOMER_ID) NAME,
    AMOUNT AS AMT, '대출' AS TYPE_
FROM
    (SELECT CUSTOMER_ID, AMOUNT, AVG(AMOUNT) OVER() AVG_AMT
    FROM LOANS
    WHERE STATUS = 'APPROVED') L
WHERE AMOUNT > AVG_AMT
UNION ALL
SELECT
    (SELECT NAME FROM CUSTOMERS C WHERE C.CUSTOMER_ID=A.CUSTOMER_ID),
    BALANCE, '계좌'
FROM
    (SELECT CUSTOMER_ID, BALANCE, AVG(BALANCE) OVER() AVG_AMT
    FROM ACCOUNTS) A
WHERE BALANCE > AVG_AMT
;

-- 7.직무별 평균 급여가 부서별 평균 급여 이상인 직무 조회
-- 직무별 평균 급여가 소속된 부서의 평균 급여 이상인 경우를 조회하세요.
-- 출력 데이터는 직무 ID, 부서 이름, 직무별 평균 급여, 부서별 평균 급여입니다.
-- 각 평균 급여는 반올림하여 정수로 표현해주세요.
SELECT *
FROM
    (SELECT
        DISTINCT JOB_ID,
        (SELECT DEPARTMENT_NAME FROM DEPARTMENTS D WHERE D.DEPARTMENT_ID = E.DEPARTMENT_ID)
        AS DEPARTMENT_NAME,
        ROUND(AVG(SALARY) OVER(PARTITION BY JOB_ID)) AS JOB_AVG_SALARY,
        ROUND(AVG(SALARY) OVER(PARTITION BY DEPARTMENT_ID)) AS DEP_AVG_SALARY
    FROM EMPLOYEES E
    WHERE DEPARTMENT_ID IS NOT NULL)
WHERE JOB_AVG_SALARY >= DEP_AVG_SALARY
;

-- 8.지점별 고객 수와 승인율
-- 지점별 대출 건수를 계산하고, 대출 승인율을 계산하여 결과를 출력하세요.
-- 대출 승인율은 반올림하여 소수점 아래 둘째자리까지 표현합니다.
SELECT
    DISTINCT BRANCH_ID,
    COUNT(*) OVER(PARTITION BY BRANCH_ID) AS CNT_LOAN,
    ROUND(100*COUNT(FLAG) OVER(PARTITION BY BRANCH_ID) / COUNT(*) OVER(PARTITION BY BRANCH_ID),2) AS RATE_LOAN
FROM
    (SELECT
        BRANCH_ID,
        CASE WHEN STATUS = 'APPROVED' THEN 1 END AS FLAG
    FROM LOANS)
;

-- 9.직무별 최고 급여를 받는 직원
-- 각 직무에서 가장 높은 급여를 받는 직원의 이름과 급여를 조회하세요. 직무 기준으로 정렬하세요.
SELECT *
FROM
    (SELECT
        NAME, JOB_ID, SALARY,
        RANK() OVER(PARTITION BY JOB_ID ORDER BY SALARY DESC NULLS LAST) AS RANK_
    FROM EMPLOYEES)
WHERE RANK_=1
ORDER BY JOB_ID;


-- 10. 상사와 직원 관계를 기반으로 부서별 직원 통계
--각 부서에서 상사가 있는 직원의 수와 상사가 없는 직원의 수를 계산하세요. 부서 이름과 각 통계 값을 출력하세요.
SELECT
    DISTINCT (SELECT DEPARTMENT_NAME FROM DEPARTMENTS D WHERE D.DEPARTMENT_ID = E.DEPARTMENT_ID) DEP_NAME,
    COUNT(MANAGER_ID) OVER(PARTITION BY DEPARTMENT_ID) NUM_HAVE_MANAGER,
    COUNT(CASE WHEN MANAGER_ID IS NULL THEN 1 END) OVER(PARTITION BY DEPARTMENT_ID) NUM_NO_MANAGER
FROM EMPLOYEES E
;