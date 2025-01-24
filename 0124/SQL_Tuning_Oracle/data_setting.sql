CREATE TABLE salaries (
    emp_no        INT      NOT NULL,
    salary        INT      NOT NULL,
    from_date     DATE     NOT NULL,
    to_date       DATE     NOT NULL,
    use_yn        CHAR(1)  DEFAULT '',
    PRIMARY KEY (emp_no, from_date)
) ;


CREATE TABLE departments  (
    dept_no       CHAR(4)         NOT NULL,
    dept_name     VARCHAR2(40)    NOT NULL,
    description   VARCHAR2(40),
    PRIMARY KEY (dept_no),
    UNIQUE (dept_name)
) ;


CREATE TABLE dept_manager (
   emp_no       INT             NOT NULL,
   dept_no      CHAR(4)         NOT NULL,
   from_date    DATE            NOT NULL,
   to_date      DATE            NOT NULL,
   PRIMARY KEY (emp_no, dept_no)
) ;


CREATE TABLE dept_emp (
    emp_no      INT             NOT NULL,
    dept_no     CHAR(4)         NOT NULL,
    from_date   DATE            NOT NULL,
    to_date     DATE            NOT NULL,
    PRIMARY KEY (emp_no, dept_no) 
) ;


CREATE TABLE employees (
    emp_no      INT             NOT NULL,
    birth_date  DATE            NOT NULL,
    first_name  VARCHAR2(14)    NOT NULL,
    last_name   VARCHAR2(16)    NOT NULL,
    gender      VARCHAR2(1)     NOT NULL,    
    hire_date   DATE            NOT NULL,
    PRIMARY KEY (emp_no)
) ;



CREATE TABLE grade (
    emp_no         INT             NOT NULL,
    grade_name     VARCHAR2(50)    NOT NULL,
    from_date      DATE            NOT NULL,
    to_date        DATE,
  PRIMARY KEY (emp_no, grade_name, from_date)
) ;
