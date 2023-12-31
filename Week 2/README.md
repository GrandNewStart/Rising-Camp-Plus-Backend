# 개발일지

## 과제 계획서

    토~수요일까지 강의 완강
    수~목요일까지 프로그래머스 문제 풀이
    금~토요일까지 ERD 완성

## 핵심 개념 정리

### 시작하기
    
    데이터베이스의 정의: 접근을 위한 인터페이스를 제공하는 전산화된 데이터 집합
    데이터베이스의 구성
        - 데이터의 집합
        - 데이터를 조작하거나 접근하기 위한 방법
    DBMS: 어플리케이션이 DB를 사용하기 위해 존재하는 중간 인터페이스
    보통 '데이터베이스'라고 하면 DB와 DBMS를 묶어서 정의한다.
    (DB+DBMS)의 종류: PostgreSQL, MySQL, Oracale DB, SQLite
    SQL: 데이터베이스와 '소통'하기 위한 언어
    SQL을 사용하는 관계형 DB: MySQL, SQLite, PostgreSQL, Oracle, ...
    다른 DB를 쓰더라도 SQL 문법은 '대부분' 공통이다. 조금씩 다른 부분들은 있다.
    MySQL 루트 실행 커맨드: mysql -u root -p
    
### 테이블 생성

    MySQL 데이터베이스 서버 안에 다수의 독립된 데이터베이스들이 존재한다.
    모든 데이터베이스를 조회하는 커맨드
        show databases; 
    새 데이터베이스 생성
        CREATE DATABASE <name>;
    데이터베이스 이름은 무엇이든 될 수 있지만 공백은 가급적 피하는 것이 좋다.
    SQL 키워드는 보통 대문자로 쓰는데 사실 필수는 아니다. 하지만 가독성 면에서 좋다.
    데이터베이스 삭제
        DROP DATABASE <name>;
    사용하고자 하는 데이터베이스를 지정
        USE <name>; 
            or 
        SELECT database(); 
    칼럼 데이터 타입의 종류
        Numberic Types
            - INT
            - SMALLINT
            - TINYINT
            - MEDIUMINT
            - BIGINT
            - DECIMAL
            - NUMERIC
            - FLOAT
            - DOUBLE
            - BIT
        String Types
            - CHAR
            - VARCHAR
            - BINARY
            - VARBINARY
            - BLOB
            - TINYBLOB
            - MEDIUMBLOB
            - LARGEBLOB
            - TEXT
            - TINYTEXT
            - MEDIUMTEXT
            - LONGTEXT
            - ENUM
        Date Tyes
            - DATE
            - DATETIME
            - TIMESTAMP
            - TIME
            - YEAR
    가장 많이 쓰이는 건 INT, VARCHAR
    데이터 타입에 대한 자세한 내용은 MySQL 공식 문서에서 확인
    테이블 생성과 동시에 칼럼 정의하기
        CREATE TABLE <name>
            (
                <column_name> <data_type>,
                <column_name> <data_type>,
            );
    현재 데이터베이스가 가진 모든 테이블들을 조회
        SHOW TABLES; 
    해당 이름의 테이블이 가진 칼럼값들을 조회
        SHOW COLUMNS FROM <table_name>;
            or
        DESC <table_name>;
    주석 
        -- 
    해당 이름의 테이블을 삭제
        DROP TABLE <table_name>; 
    
### 데이터 삽입

    특정 테이블에 특정 데이터 삽입하기
        INSERT INTO <table_name>(<column_name_1>, <column_name_2>, ...)
        VALUES (<column_value_1>, <column_value_2>, ...);
    컬럼 이름들의 순서는 상관없으나, 선언한 컬럼 이름의 순서대로 VALUES 값을 넣어줘야 한다.
    한번에 여러 데이터 삽입하기
        INSERT INTO <table_name>(<column_name_1>, <column_name_2>, ...)
        VALUES (<column_value_1>, <column_value_2>, ...)
                ,(<column_value_1>, <column_value_2>, ...)
                ,(<column_value_1>, <column_value_2>, ...)
                , ...;
    특정 테이블에서 모든 데이터 조회 
        SELECT * FROM <table_name>;
    데이터가 null 값을 허용하지 않도록 하는 수식어 NOT NULL
        ex) CREATE TABLE cats(name VARCHAR(100) NOT NULL, age INT NOT NULL);
    SQL을 쓸 때는 기본적으로 작은 따옴표를 쓰는게 좋다.
    다만 따옴표 안에 또다른 따옴표가 들어가는 케이스라면 escape(\)를 붙여야 한다.
    작은 따옴표 안에 큰따옴표가 들어가는 건 상관 없음.
    데이터를 입력하지 않았을 때 적용되는 기본값 수식어 DEFAULT
        ex) CREATE TABLE cats(name VARCHAR(100) DEFAULT 'unnamed', age INT DEFAULT 99)
    DEFAULT를 선언한다고 NULL 입력을 막아주진 않는다. 둘 다 써야 한다.
    Primary Key = 유일한 식별자
        CREATE TABLE <table_name>(
            <primary_key> <data_type>,
            <columne_name_1> <data_type>,
            <columne_name_2> <data_type>,
            ...,
            PRIMARY KEY(<primary_key>)
        );
        ex) CREATE TABLE cats (
            id INT,
            name VARCHAR(100),
            age INT
            PRIMARY KEY(id)
        );
    PRIMARY KEY는 기본적으로 NOT NULL을 포함한다.
    AUTO_INCREMENT를 붙이면 입력하지 않아도 자동으로 INT 값을 생성한다.
        ex) CREATE TABLE cats(
            id INT AUTO_INCREMENT,
            name VARCHAR(100),
            age INT,
            PRIMARY KEY(id)
        );
        
### 제약 조건 및 테이블 변경

    UNIQUE: 유일성 보장. 이미 존재하는 값이 입력되면 에러.
        ex) CREATE TABLE contacts(
            name VARCHAR(100) NOT NULL,
            phone VARCHAR(15) NOT NULL UNIQUE
        );
    CHECK: 특정 조건 보장. 조건에 맞지 않는 값이 입력되면 에러.
        ex) CREATE TABLE partiers(
            name VARCHAR(50),
            age INT CHECK (age > 18)
        );
    CONSTRAINT: 조건에 이름을 달아 해당 조건이 맞지 않는 오류가 발생할 때 해당 이름이 뜨도록 할 수 있다.
        ex) CREATE TABLE partiers (
            name VARCHAR(50),
            age INT,
            CONSTRAINT age_over_18 CHECK (age > 18)
        );
    ALTER TABLE: 테이블 정의를 바꿀 수 있다. 수많은 다른 문법과 연계되어 사용되는데 정확한 내용은 공식 문서 확인.
    ADD COLUMN: 새로운 컬럼을 추가. 기존의 데이터들은 DEFAULT 값을 주지 않으면 NULL 값으로 초기화된다.
        ex) ALTER TABLE companies
            ADD COLUMN city VARCHAR(25);
    DROP COLUMN: 칼럼 삭제 
        ex) ALTER TABLE companies
            DROP COLUMN city;
    RENAME TABLE: 테이블 이름 변경
        ex) RENAME TABLE companies TO suppliers;
                or
            ALTER TABLE companies
            RENAME TO suppliers;
    RENAME COLUMN: 컬럼 이름 변경
        ex) ALTER TABLE companies
            REANME COLUN name TO company_name;
    MODIFY: 컬럼의 데이터 타입 변경.
        ex) ALTER TABLE suppliers
            MODIFY biz_name VARCHAR(100);
    CHANGE: 컬럼의 이름도 변경하고 데이터 타입도 변경.
        ex) ALTER TABLE suppliers
            CHANGE business biz_name VARCHAR(50);
    ADD/DROP CONSTRAINT _ CHECK _: 새로운 제약조건을 걸거나 삭제할 수 있다.
        ex) ALTER TABLE houses 
            ADD CONSTRAINT 'positive_pprice'
            CHECK (purchase_price >= 0);
        ex) ALTER TABLE houses
            DROP CONSTRAINT positive_pprice;
            
### CRUD
    
    Create: INSERT INTO _ VALUES _
    Read: SELECT _ FROM _
        SELECT <column_name> FROM <table_name>;: 특정 테이블에서 특정 컬럼 값만 조회
        SELECT <column_1>, <column_2> FROM <table_name>;: 여러개도 가능
        SELECT * FROM <table_name> WHERE <condition>;: 조건을 만족하는 모든 데이터 조회
        SELECT <column_name> AS <alias> FROM <table_name>;: 컬럼 이름을 바꿔서(Alias) 조회 
    Update: UPDATE _ SET _ WHERE _
        UPDATE <table_name> SET <update> WHERE <condition>;: 특정 테이블에서 특정 조건을 만족하는 데이터를 일괄 변경
    Delete: DELETE FROM _ WHERE _
        DELETE FROM <table_name>: 조건을 걸지 않으면 테이블 데이터 전체가 지워진다.
        DELETE FROM <table_name> WHERE <condition>;: 테이블 내에서 조건에 맞는 데이터를 일괄 삭제.

### 문자열 함수
    
    수많은 문자열 함수가 존재하니 정확한 건 mysql 공식문서를 확인한다.
    CONCAT(_, _, ...): 여러 문자열을 이어준다.
        ex) SELECT CONCAT('h', 'e', 'l', 'l', 'o'); => hello
        ex) SELECT CONCAT(first_name, last_name) FROM contacts;
    CONCAT_WS(<separator>, _, _, ...);: 문자열을 붙이되, 첫번째 인자로 주어진 separator를 사용한다.
        ex) SELECT CONCAT_WS('-', '000', '1111', '2222'); => 000-1111-2222
    SUBSTRING(<string>, <start_index>, <char_count>);: 문자열의 일부 반환
        ex) SELECT SUBSTRING('Hello World', 1, 4); => Hell
        ex) SELECT SUBSTRING('Hello World', -4, 4); => orld
    SUBSTRING(<string>, <start_index>);: start_index부터 끝까지 반환
        ex) SELECT SUBSTRING('Hello World', 7); => World
        ex) SELECT SUBSTRING('Hello World', -3); => rld
    SUBSTRING 대신 SUBSTR을 써도 무방하다.
    REPLACE(<string>, <from>, <to>);: string 안에서 모든 from을 to로 바꾼다. Case-sensitive.
        ex) SELECT REPLACE('Hello World', 'Hell', 'Heaven'); => Heaveno World
    REVERSE(<string>);: string을 뒤집어서 반환한다.
        ex) SELECT REVERSE('chicken'); => nekcihc
    CHAR_LENGTH(<string>);: string의 길이를 반환한다.
        ex) CHAR_LENGTH('Hey!') => 4    
    UPPER(<string>)/LOWER(<string>);: 대문자화/소문자화.
    UPPER = UCASE
    LOWER = LCASE
    INSERT(<string>, <position>, <length>, <new_string>);
    LEFT(<string>, <length>);
    RIGHT(<string>, <length>);
    REPEAT(<string>, <count>);
    TRIM(<string>);
    TRIM(LEADING <string_to_trim> FROM <string>);
    
### 선택사항 정교화

    DISTINCT <column_name>;: 테이블 내에서 동일한 column 값을 가지지 않는 것만 반환.
        ex) SELECT DISTINC author_lnmae FROM books;
    ORDER BY <column_name> ASC|DESC;: 출력을 특정 column을 기준으로 오름/내림차순으로 정렬.
        ex) SELECT author_lname FROM books ORDER BY author_lname; (생략하면 ASC)
        ex) SELECT author_lname FROM books ORDER BY 2; (숫자를 쓰면 n번째 칼럼으로 인식한다)
        ex) SELECT author_lname FROM books ORDER BY author_lname, author_fname; (여러 칼럼을 쓸 수 있다)
        ex) SELECT CONCAT(author_fname, ' ', author_lname) AS author FROM books ORDER BY author (원래 칼럼에 없던 Alias를 기준으로 쓸 수도 있다)
    LIMIT <number>;: 출력되는 데이터의 갯수를 제한한다.
        ex) SELECT book_id FROM books LIMIT 5;
        ex) SELEcT book_id FROM books LIMIT 3, 5; (숫자 두개를 쓰면 n부터 m개의 데이터를 출력한다)
        숫자가 전체 데이터 수를 넘어도 상관없다.
    LIKE <string_pattern>;: 특정 패턴을 만족하는 문자열을 걸러낼 수 있다.
        ex) SELECT * FROM books WHERE author_fname LIKE '%da%' (%는 와일드카드라고 하며, 무엇이든 올 수 있다는 의미를 가진다)
        ex) SELECT * FROM books WHERE author_fname LIKE '____' (_는 한글자를 뜻하며, 4개면 4글자의 무언가를 뜻한다)
        ex) SELECT * FROM books WHERE author_fname LIKE '%\%%' (와일드카드가 아닌 %를 표현하려면 이스케이프(\)를 쓰면 된다)
        
### 데이터 타입

    VARCHAR(n)는 n이하 길이의 문자열을 수용한다.
    CHAR(n)는 반드시 n의 길이를 가져야하므로, 글자수가 모자라면 자동으로 공백이 채워진다.
    VARCHAR는 실제 데이터의 길이보다 1바이트 큰 용량을 차지한다.
    CHAR는 정확히 데이터의 길이만큼의 바이트를 차지한다.
    따라서 데이터의 글자 수가 고정되어있는데 용량에 민감하다면 CHAR를 쓰는 것이 좋다.
    TINYINT: -128 ~ 127, 1바이트
    SMALLINT: -32768 ~ 32767, 2바이트
    MEDIUMINT: -8388608 ~ 8388607, 3바이트
    INT: -2147382648 ~ 2147382647, 4바이트
    BIGINT: -2^63 ~ 2^63 - 1, 8바이트
    INT 계열 데이터 타입 뒤에 UNSIGNED를 붙여서 양수만 받을 수 있다.
    INT 계열로 데이터 타입을 선언하면 소수점이 들어간 데이터를 넣을 때, 소숫점 부분이 잘린 채 들어간다.
    DECIMAL(n, m): n=정수 자릿수, n=소숫점 자릿수
    DECIMAL(5,2)의 가장 큰 수는 999.99가 된다.
    DECIMAL(n, m)로 선언된 칼럼에 소숫점 자릿수가 m을 넘어가는 데이터를 넣을 경우, 반올림 되어 들어간다.
    FLOAT와 DOUBLE은 DECIMAL보다 큰 수를 더 적은 용량으로 처리할 수 있다. 대신 정확도의 한계가 존재한다.
    FLOAT: 4바이트, 소수점 7자리까지 정확도 보장
    DOUBLE: 8바이트, 소수점 15자리까지 정확도 보장
    DATE: YYYY-MM-DD/날짜만 표현한다
    TIME: HH:MM:SS/시간만 표현한다/여기서 시간(HH)은 -838에서 838까지 표현 가능하다
    DATETIME: YYYY-MM-DD HH:MM:SS/날짜와 시간을 모두 표현한다
        ex) INSERT INTO people (birthdate, birthtime, birthdatetime) 
            VALUES ('Elton', '2000-12-25', '11:00:00', '2000-12-25 11:00:00')
    CURRENT_DATE()/CURDATE(), CURRENT_TIME()/CURTIME(), NOW()를 통해 현재 시간을 구할 수 있다.
    DAY(<date> or <datetime>): 일(DD) 출력
    DAYOFWEEK(<date> or <datetime>): 해당 일이 그 주에서 몇번째 일인지 출력(1~7)
    DAYOFYEAR(<date> or <datetime>): 해당 일이 그 해에서 몇번째 일인지 출력(1~365)
    MONTHNAME(<date> or <datetime>): 해당 월의 이름을 출력(Jaunary~December)
    YEAR(<date> or <datetime>): 해당 연도를 출력
    HOUR(<time> or <datetime>): 해당 시간을 출력(0~23)
    MINUTE(<time> or <datetime>): 해당 븐을 출력(0~59)
    SECOND(<time> or <datetime>): 해당 초를 출력(0~59)
    DATE_FORMAT(<date> or <datetime>, <format_string>): 입력받은 날짜/시간을 특정 패턴에 맞게 변형하여 출력
        ex) DATE_FORMAT('2000-12-25', '%b') => Dec 
            DATE_FORMAT('2000-12-25', '%a %b') => Mon Dec
            DATE_FORMAT('2000-12-25', '%a %b %e') => Mon Dec 25 
            DATE_FORMAT('2000-12-25', '%a %b %D') => Mon Dec 25th
            DATE_FORMAT('2000-12-25 11:00:00', '%H %i') => 11:00
            ...
            수많은 specifier들이 있는데 이는 mysql 공식문서에서 확인하자.
    DATEDIFF(<date> or <date_time>, <date> or <date_time>): 두 날짜간의 차이를 일 단위로 반환
        ex) DATEDIFF('2007-12-31 23:59:59', '2007-12-30'): 1
            DATEDIFF('2010-11-30 23:59:59', '2010-12-31'): 31
    DATE_ADD(<date> or <date_time>, INERVAL <number> SECOND/MINUTE/HOURDAY/MONTH/YEAR): 입력받은 날짜에서 특정 시간만큼 더한 값을 반환
    DATE_SUB(<date> or <date_time>, INERVAL <number> SECOND/MINUTE/HOURDAY/MONTH/YEAR): 입력받은 날짜에서 특정 시간만큼 뺀 값을 반환
    TIMEDIFF(<time> or <datetime>, <time> or <datetime>): 두 시간간의 차이를 hh:mm:ss로 반환
    DATE/DATETIME/TIME 간에 +, - 연산자를 사용하면 DATE_ADD, DATE_SUB 등과 같은 효과
    TIMESTAMP: DATETIME과 같은 정보를 표현하되, 보다 적은 용량을 차지한다. 왜냐면 표현 범위가 적기 때문에...
    TIMESTAMPADD, TIMESTAMPDIFF 등, 동일한 함수도 있다.
    <column_name> TIMESTAMP default CURRENT_TIMESTAMP: 해당 데이터가 생성될 때 시점의 타임스탬프가 이 칼럼의 데이터로 들어간다. 주로 created_at과 같은 칼럼에 이용.
    <column_name> TIMESTAMP ON UPDATE CURRENT_TIMESTAMP: 헤당 데이터가 수정될 때 시점의 타임스탬프가 이 칼럼의 데이터로 들어간다. 주로 updated_at과 같은 칼럼에 이용.

### 논리 연산자
    
    =(equal), !=(not equal),
     
    >(greater than), >=(greater than or equal to) <(less than), <=(less than or equal to)
    
    %(modulo)
    
    NOT LIKE <-> LIKE
    
    AND, OR
    
    <column_name> BETWEEN n AND m = <column_name> >= n AND <column_name> <= m
    NOT BETWEEN도 가능
    
    CAST(<data> AS <data_type>): 형변환을 위한 연산
    
    SELECT <column> FROM <table> WHERE <column> IN (<data_1>, <data_2>, <data_3>)
        = SELECT <column> FROM <table> WHERE <column>=<data_1> OR <column>=<data_2> OR <column>=<data_3>
    NOT IN도 가능
    
    CASE 
        WHEN <condition_1> THEN <data_1> 
        WHEN <condition_2> THEN <data_2>
        ... 
        ELSE <data_n> 
    END 
    
    IS NULL/ = NULL
    
### 집계 함수

    SELECT COUNT(*) FROM <table> WHERE <condition>; => 조건을 만족하는 데이터의 갯수 출력
    SELECT COUNT(<columnn>) FROM <table> WHERE <condition>; => 조건을 만족하는 'NULL이 아닌' 컬럼 데이터의 갯수 출력
    SELECT COUNT(DISTINCT <columnn>) FROM <table> WHERE <condition>; => 조건을 만족하는 '서로 다른' 컬럼 데이터의 갯수 출력
     GROUP BY: 동일한 데이터를 하나의 행으로 합친다.
        ex) SELECT author_lname FROM books GROUP BY author_lname; => 같은 author_lname을 가진 행들이 그룹을 이루어 하나의 행으로 취급된다.
        ex) SELECT author_lname, COUNT(*) FROM books GROUP BY author_lname;
    * 집계 함수를 쓸 때, SELECT 이후에 오는 컬럼명은 각 그룹이 동일한 데이터 값을 가지는 컬럼으로 해야한다.
    MIN/MAX
        ex) SELECT MIN(released_yar) FROM books; => 전체 책 중 가장 발매된지 오래된 책 하나 출력
        ex) SELECT MAX(pages) FROM books; => 전체 책 중 가장 페이지 수가 많은 책 하나 출력
        ex) SELECT MIN(author_lname) FROM books; => 전체 책 중 작가의 성이 알파벳상 가장 빠른 글자로 시작하는 이름 출력
        ex) SELECT title, pages FROM books ORDER BY pages DESC LIMIT 1; => 가장 페이지 수가 많은 책의 페이지 수와 제목을 출력
        ex) SELECT title, pages FROM books WHERE pages=(SELECT MIN(pages) FROM books); => 이런 식으로도 쓸 수 있다.
    GROUP BY의 인자로 여러 값을 넣을 수 있다.
        ex) SELECT author_lname, COUNT(*) FROM books GROUP BY author_lname, author_fname; => author_lname + author_fname의 조합을 단위로 그룹을 묶는다
    GROUP BY와 MIN/MAX를 같이 사용하면 많은 것들을 할 수 있음.
    SUM(<column_name>): 인자로 받은 컬럼 값의 합을 반환
        ex) SELECT author_lname, SUM(pages) FROM books GROUP BY author_lname, author_fname; => 각 작가들이 쓴 페이지 수의 총 합
    AVG(<column_name>): 인자로 받은 칼럽 값의 평균 값을 반환
        ex) SELECT AVG(released_year) FROM books; => 전체 책들의 출판연도의 평균을 출력
        ex) SELECT AVG(stock_quantity) FROM books GROUP BY released_year; => 출판 연도에 따라 평균 수량을 출력
        
### 1-to-Many, Join
        
    일대일 관계 / 일대다 관계 / 다대다 관계
    Book - Review의 관계는 일대다 관계, Book - Authors의 관계는 다대다 관계
    일대다 관계가 가장 흔한 관계
    Customer(1) - Order(Many)의 관계
    Customer - Order를 한 테이블로 정리하면 데이터의 중복이 많이 일어남.
    따라서 분리를 하게되고 자연스레 관계가 발생한다.
    Customer
        - CUSTOMER_ID (INT, PK)
        - FIRST_NAME (VARCHAR)
        - LAST_NAME (VARCHAR)
        - EMAIL (VARCHAR)
    Order
        - ORDER_ID (INT, PK)
        - CUSTOMER_ID (INT, FK)
        - ORDER_DATE (DATE)
        - AMOUNT (INT)
    SQL:
        CREATE TABLE customers (
            id INT PRIMARY KEY AUTO_INCREMENT,
            first_name VARCHAR(50),
            last_name VARCHAR(50),
            email VARCHAR(50)
        );
        CREATE TABLE orders (
            id INT PRIMARY KEY AUTO_INCREMENT,
            order_date DATE,
            amount DECIMAL(8,2),
            customer_id INT,
            FOREIGN KEY (customer_id) REFERENCES customers(id)
        );
        => 이렇게 외래키를 설정하면 customers에 존재하지 않는 id값을 orders에 넣으려 할 때 에러가 날 것이다.
        
    SELECT * FROM customers, orders: 
        이를 크로스 조인이라 하며, customers와 orders의 모든 데이터 조합을 만들어낸다.
        이는 잘 사용되지 않는다.
        
    일대다 관계의 두 테이블을 합치는 것은 다음과 같다.
    
        - INNER JOIN: 두 테이블의 교집합만 결합.
            ex) SELECT * FROM customers JOIN orders ON customers.id=orders.customer_id;
                => customers 테이블에 orders를 합치되, orders.customer_id와 customers.id가 겹치는 데이터만 합친다.
            ex) SELECT first_name, last_name, SUM(amount) FROM customers 
                JOIN orders ON orders.customer_id=customers.id
                GROUP BY first_name, last_name;
                
        - LEFT JOIN: 좌변 테이블에 있는 데이터만 합친다.
            ex) SELECT * FROM customers LEFT JOIN ON orders ON customers.id=orders.customer_id;
                => customers 테이블에서 orders 테이블과 겹치는 부분에 데이터를 추가하고 겹치지 않는 부분은 NULL 처리한다.
            ex) SELECT first_name, last_name, SUM(amount) 
                FROM customers LEFT JOIN orders ON customers.id=orders.customer_id
                GROUP BY first_name, last_name;
                => SUM(amount)에 NULL 값이 올 수 있다. 이 때, IFNULL(SUM(amount), 0)를 써주면 0으로 바꿀 수 있다.
                
        - RIGHT JOIN: 우변 테이블에 있는 데이터만 합친다.
            ex) SELECT * FROM customers LEFT JOIN ON orders ON customers.id=orders.customer_id;
                => orders 테이블에서 customers 테이블과 겹치는 부분에 데이터를 추가하고 겹치지 않는 부분은 NULL 처리한다.
    
    다른 테이블에서 외래키로 참조하고 있는 데이터를 지우려고 하면 에러가 난다.
    외래키를 참조할 떄, FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE
    이렇게 선언하면 참조중인 데이터까지 한꺼번에 자동으로 지워지게 할 수 있다.

## 진행상황

    예상보다 강의 수강이 미뤄져 22일 토요일 밤을 새서 모두 완강.
    프로그래머스 문제는 목요일부터 풀기 시작, 토요일 새벽에 완료.
    ERD는 토요일 새벽, 프로그래머스 문제 풀이가 끝난 후부터 시작. 대략 2~3시간 소요.

## 피드백 

    N:N 관계 설정을 적극적으로 활용했어야
    맵핑 테이블, 그 생각을 못했네~
    대/중/소 카테고리 같은 수직적 관계의 엔티티를 짤 때는 바로 위 엔티티에대한 FK만 가지고 있으면 된다.
    식별관계와 비식별관계의 차이를 구분해야.

## 참고자료

- 식별/비식별 관계: https://deveric.tistory.com/108
- ERD에서 다대다 관계 구현: https://siyoon210.tistory.com/26

**쿠팡 ERD 과제**

![쿠팡 ERD](./쿠팡%20ERD.png)
