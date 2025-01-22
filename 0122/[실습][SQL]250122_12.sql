-- 실습 12
-- 사원 테이블에서 부서별로 급여의 합계를 계산하고 전체 합계를 함께 출력하시오.(부서데이터가 NULL인 사원은 제외
SELECT
    NVL(TO_CHAR(DEPARTMENT_ID), 'TOTAL') AS DEPARTMENT_ID, SUM(SALARY)
FROM EMPLOYEES
WHERE DEPARTMENT_ID IS NOT NULL
GROUP BY ROLLUP(DEPARTMENT_ID)
;

-- 같은 날짜에 입사한 사원이 2명 이상인 날짜와 입사한 사원수, 마지막 행은 총 직원수를 함께 출력하시오.
SELECT
    NVL(TO_CHAR(HIRE_DATE, 'YYYY-MM-DD'), 'TOTAL') AS DATE_,
    COUNT(*) AS CNT
FROM EMPLOYEES
GROUP BY ROLLUP(TO_CHAR(HIRE_DATE, 'YYYY-MM-DD'))
HAVING COUNT(*) >= 2
;

-- 각 지점별 대출 금액 합계와 모든 지점의 총합계를 출력하시오. 출력 컬럼은 지점명, 대출 금액 합계이며
-- 지점 총 합계는 All Branch로 출력하고 지점명으로 정렬하되 총합계는 맨 아래줄에 나타내시오.
SELECT 
    NVL(B.NAME, 'All Branch') AS BRANCH_NAME_,
    SUM(L.AMOUNT) AS CNT
FROM (SELECT * FROM LOANS WHERE STATUS='APPROVED') L
LEFT JOIN BRANCHES B
    ON L.BRANCH_ID = B.BRANCH_ID
GROUP BY ROLLUP(B.NAME)
ORDER BY LENGTH(B.NAME), B.NAME
;