-- employees 테이블에 인덱스 추가
CREATE INDEX idx_gender ON employees (gender);
CREATE INDEX idx_gender_last_name ON employees (gender, last_name);
CREATE INDEX idx_hire_date ON employees (hire_date);

-- emp_record 테이블에 인덱스 추가
CREATE INDEX idx_gate ON emp_record (gate);
CREATE INDEX idx_locate ON emp_record (locate);
CREATE INDEX idx_access_time ON emp_record (access_time);

-- dept_emp 테이블에 인덱스 추가
CREATE INDEX idx_dept_no ON dept_emp (dept_no);

-- dept_manager 테이블에 인덱스 추가
CREATE INDEX idx_dept_no_man ON dept_manager (dept_no);
