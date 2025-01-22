-- 1. 직원 계층에서 상사의 이름 표시
--직원의 계층 구조를 조회하되 각 계층에서 상사 이름도 함께 표시하세요.
--출력 컬럼은 LEVEL, 사번, 이름, 직무, 상사의 사번, 상사의 이름입니다. (단, 상사가 없는 경우 ‘대표’라고 표시하고 계층별로 이름으로 오름차순 정렬합니다.)
SELECT
    LEVEL, EMPLOYEE_ID, NAME, JOB_ID, MANAGER_ID,
    CASE
        WHEN MANAGER_ID IS NULL THEN '대표'
        ELSE (SELECT NAME FROM EMPLOYEES WHERE EMPLOYEE_ID = E.MANAGER_ID)
    END AS MANAGER_NAME
    
FROM EMPLOYEES E
START WITH MANAGER_ID IS NULL
CONNECT BY PRIOR EMPLOYEE_ID = MANAGER_ID
ORDER SIBLINGS BY NAME
;

--2. 각 계층별 급여 순위
--직원 계층 쿼리를 작성하고 각 계층별로 급여 상위 3위 까지의 직원들만 조회하세요. 출력 컬럼은 LEVEL, 사번, 이름, 급여입니다.
SELECT "LEVEL", EMPLOYEE_ID, NAME, SALARY
FROM
    (SELECT
        LEVEL AS "LEVEL", EMPLOYEE_ID, NAME, SALARY,
        ROW_NUMBER() OVER (PARTITION BY LEVEL
            ORDER BY SALARY DESC NULLS LAST) AS RN
    FROM EMPLOYEES E
    START WITH MANAGER_ID IS NULL
    CONNECT BY PRIOR EMPLOYEE_ID = MANAGER_ID)
WHERE RN <= 3
;


--3. 각 계층별 평균 급여
--직원 계층 구조를 생성하고 각 계층별 평균 급여와 함께 출력하세요. 출력 컬럼은 LEVEL, 사번, 이름, 급여, 해당 계층의 평균 급여이고 평균 급여는 반올림하여 정수로 표현합니다.
SELECT
    LEVEL, EMPLOYEE_ID, NAME, SALARY,
    ROUND(AVG(SALARY) OVER(PARTITION BY LEVEL)) AS AVG_SALARY
FROM EMPLOYEES E
START WITH MANAGER_ID IS NULL
CONNECT BY PRIOR EMPLOYEE_ID = MANAGER_ID
;

--4. 상사와의 급여 비교
--직원의 급여가 상사의 급여보다 높은 경우만 조회하세요. 출력 컬럼은 사번, 이름, 급여, 상사 이름, 상사의 급여입니다.
SELECT
    E1.EMPLOYEE_ID, E1.NAME, E1.SALARY, E2.NAME AS MANAGER_NAME, E2.SALARY AS MANAGER_SALARY
FROM EMPLOYEES E1, EMPLOYEES E2
WHERE E1.MANAGER_ID = E2.EMPLOYEE_ID
    AND E1.SALARY > E2.SALARY
;


--5. 직원의 부서 경로 조회
--각 직원이 속한 부서를 포함하여 해당 직원의 부서명 경로를 표시하세요. 부서가 없는 경우 "No DEPT"로 표시하세요. 출력 컬럼은 이름, 부서명, 부서명 경로입니다.
SELECT
    EMPLOYEE_ID, MANAGER_ID,
    NVL2(DEPARTMENT_NAME, SYS_CONNECT_BY_PATH(DEPARTMENT_NAME,'->'), 'No DEPT') AS PATH
FROM
    (SELECT
        E.EMPLOYEE_ID, E.MANAGER_ID, D.DEPARTMENT_NAME
    FROM EMPLOYEES E LEFT OUTER JOIN DEPARTMENTS D ON E.DEPARTMENT_ID = D.DEPARTMENT_ID)
START WITH MANAGER_ID IS NULL
CONNECT BY PRIOR EMPLOYEE_ID = MANAGER_ID
;


--6. 직무별, 지역별 대출 금액 합계 조회
--대출 테이블에서 직무별, 지점별 대출 금액 합계를 계산하고 모든 조합, 즉 다차원 집계를 표시하세요.
SELECT
    E.JOB_ID, L.BRANCH_ID, SUM(L.AMOUNT)
FROM EMPLOYEES E, LOANS L
WHERE E.EMPLOYEE_ID = L.CUSTOMER_ID
GROUP BY CUBE(E.JOB_ID, L.BRANCH_ID)
ORDER BY E.JOB_ID, L.BRANCH_ID
;


-- 7. 직원 급여 통계 요약(GROUPING SETS)
-- 사원 테이블에서 전체 급여 합계, 부서별 급여 합계, 직무별 급여 합계를 각각 표시하세요.
SELECT
    DEPARTMENT_ID, JOB_ID, SUM(SALARY)
FROM EMPLOYEES
GROUP BY GROUPING SETS(DEPARTMENT_ID, JOB_ID)
ORDER BY JOB_ID NULLS FIRST, DEPARTMENT_ID NULLS FIRST
;

--8. 대출 통계 요약
--대출 테이블에서 지점별, 대출 상태별 금액 합계를 계산하고
--각 지점별 소계와 전체 합계를 표시하세요. 단, 지점별 소계를
--나타내는 행은 상태값 컬럼에 ‘==========’ 표시를 해주고 총계는 ‘Total’로 표시합니다.
SELECT
    NVL(TO_CHAR(BRANCH_ID),'TOTAL') BRANCH_ID,
    NVL(STATUS, '==========') STATUS,
    SUM(AMOUNT)
FROM LOANS
GROUP BY ROLLUP(BRANCH_ID, STATUS)
ORDER BY BRANCH_ID, STATUS DESC
;

--9. 계층별 직원 수 조회
--직원의 계층 구조를 생성하고 각 계층별 직원의 수를 출력하세요.
SELECT
    LEVEL,
    COUNT(*) CNT
FROM EMPLOYEES
START WITH MANAGER_ID IS NULL
CONNECT BY PRIOR EMPLOYEE_ID = MANAGER_ID
GROUP BY LEVEL
;

--10. 고객별, 대출 상태별 대출 금액
--고객별, 대출 상태별 대출 금액과 고객별 대출 금액 소계, 그리고 전체 대출 금액 합계를 구하세요.
--출력 컬럼은 고객 이름, 대출 상태, 대출 금액입니다. 고객별 대출 금액 소계는 ‘All Status’로 표시하고 전체 대출 금액 합계는 Total로 표시해 주세요.
SELECT
    NVL2(CUSTOMER_ID,
        (SELECT NAME FROM CUSTOMERS C WHERE C.CUSTOMER_ID = L.CUSTOMER_ID),
        'Total') AS CUSTOMER_ID,
    CASE
        WHEN GROUPING(STATUS)=1 AND GROUPING(CUSTOMER_ID)=0 THEN 'All Status'
        WHEN GROUPING(STATUS)=1 AND GROUPING(CUSTOMER_ID)=1 THEN 'Total'
        ELSE STATUS
    END AS STATUS_,
    SUM(AMOUNT) SUM_AMOUNT
FROM LOANS L
GROUP BY ROLLUP(CUSTOMER_ID, STATUS)
ORDER BY CUSTOMER_ID, STATUS NULLS LAST
;
