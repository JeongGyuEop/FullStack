# 맛보기 예제

# 회원(member)테이블에 저장된 모든 열의 정보 조회
# 문법
#     SELECT 조회할_열명 FROM 조회할_테이블명 WHERE 조건식; 

SELECT member_id, member_name, member_addr
FROM member;

# 참고. 많은 열명을 기술해야 할때  * 기호를 작성하여
#      모든 열명을 기술합니다.
SELECT * 
FROM member;

# member테이블에 저장된 회원정보들 중에서
# 회원이름(member_name열에 저장된 데이터)이 
# '아이유'인 회원 한 사람의 정보를 조회
SELECT * FROM member
WHERE member_name = '아이유';

-- -----------------------------------
# ㅇㄴㅁㅇㄴㅁ
/*
ㅇㄴㅁㅇ
ㅁㅇㄴ
ㄴㅁ
ㅇㄴㅁ
ㅇ
ㄴㅁㅇ
ㄴㅁ
*/
-- 개체(객체)? 데이터로 표현하고자 하는 데이터베이스 구성요소

-- 개체(객체)의 종류 : 테이블, 인덱스, 뷰, 스토어드프로시저, 트리거, 함수, 커서 등 

# 인덱스(index) 개체 
#  데이터베이스 테이블에 저장된 데이터의 검색속도를 향상시키기위한 개체.

# 인덱스 개체를 사용하지 않고 member테이블에 저장되어 있는
# 이름이 아이유인 한사람의 정보를 조회

# SELECT * FROM member WHERE member_name = '아이유';
# 해석 : member_name열에 저장된 데이터가 '아이유'와 같은 행에 대한 ~~~
#       member테이블에 저장된 열의 데이터들을 조회 하는데~~
#       * 모든 열의 데이터를 SELECT조회 하겠다.  

SELECT * FROM member WHERE member_name = '아이유';

# 인덱스 개체 만들기 문법
# CREATE INDEX 생성할인덱스개체명 ON 테이블명(열명);

# member테이블의 member_name열에 인덱스 개체를 생성하자.
CREATE INDEX idx_member_name ON member(member_name);

# member테이블의 member_name열에 저장된 값이 '아이유'인 행에 관한
# 모든 아이유의 열 정보를 조회 
SELECT * FROM MEMBER
WHERE member_name = '아이유';

-- -------------------------------------------------------
# 뷰 : 가상의 테이블(가짜 테이블)

# 뷰 개체 만들기 문법
# CREATE VIEW 만들뷰명
# AS SELECT * FROM 조회할테이블명;

# member테이블과 연결되는 회원뷰(member_view)생성
CREATE VIEW member_view
AS SELECT * FROM MEMBER;

# member테이블이 아닌 회원뷰(member_view)가상 뷰 로 조회 
SELECT * FROM member_view;

# 테이블을 사용하지 않고 굳이 뷰를 사용하여 조회한 이유는?
# 1. member테이블을 조작하면 데이터가 변경되거나 삭제 될수 있어 보안데 좋지 않음
#    그래서 뷰명으로 조회 하면 member테이블을 직접 만져서 조회하지 않기때문에 보안에 좋음 
# 2. 긴 조회 SELECT SQL문을 간략하게 만들수도 있다.

-- ------------------------------------------------------

# 스토어드 프로시저 : 프로그램 코드를 묶어 놓은 함수와 같은 개체 

# 회원 테이블(member)테이블에 저장된 member_name열에 저장된 값이 '나훈아'인 
# 행 에 관한 모든 열의 값 조회
SELECT * FROM MEMBER WHERE member_name = '나훈아';

# 상품 테이블(product)테이블에 저장된 product_name열에 값이 '삼각김밥' 인
# 행 에 관한 모든 열의 값 조회 
SELECT * FROM product WHERE product_name = '삼각김밥';
 
# 참고 -> 조회 결과를 보면 매번 두 줄 SQL을 입력해야 한다면 상당히 불편할 것이고
# SQL의 문법을 잊어버리거나 오타를 입력할수도 있습니다. 
# 두 줄의 SELECT SQL문을 하나로 묶어서 관리 하는 스토어드 프로시저 개체를 만들어 사용하자 

/*
	스토어드 프로시저 개체 만드는 문법
     
      DELIMITER //
      CREATE PROCEDURE 만들프로시저명()
      BEGIN
		프로그래밍할 SQL 구문;
      END //
      DELIMITER;
      
      참고. 위 첫 행과 마지막 행에 구분 문자라는 의미의 DELIMITER // 와 
           DELIMITER; 문을 작성 하였는데...
           일단 이것은 스토어드 프로시저를 만들기 위해 묶어 주는 약속의 문법 이라고 생각 하면됩니다.
           
*/
# 위 두 SELECT문을 하나의 기능인 스토어드 프로시저 개체로 만들어 보겠습니다
DELIMITER //
CREATE PROCEDURE myProc()
BEGIN
	SELECT * FROM MEMBER WHERE member_name = '나훈아';
    SELECT * FROM product WHERE product_name = '삼각김밥';
END //
DELIMITER ;
 
# 바로 위에서 만든 myProc라는 이름의 스토어드 프로시저를 호출하기 위해서는
# CALL 문을 작성하여 실행시키면 된다.
CALL myProc();
CALL myProc();

-- -----------------------------------------------------------
-- 주제 : 기본 조회문 SELECT ~ FROM 절 배우기 

-- USE 문
--   SELECT문으로 테이블에 저장된 데이터를 조회하기 전에
--   먼저 사용할 데이터베이스를 선택할때 사용되는 예약어

-- 문법
--    USE 사용할데이터베이스명;
USE market_db;

-- SELECT문?
--  특정 테이블에 저장되어 있는 데이터를 조회하여 가져올떄 사용되는 구문.

/*
   SELECT 전체 문법
	
		SELECT 조회할_데이터가_저장되어있는_열명 
        FROM 조회할_데이터가_저장된_테이블명
        WHERE 조건식
        GROUP BY 그룹으로_묶을_데이터들이_저장된_열명
		HAVING 조건식
        ORDER BY 정렬할데이터가_저장된_열명 ASC 또는 DESC
        LIMIT 숫자;

   SELECT 핵심 문법 1
		SELECT 조회할_데이터가_저장되어있는_열명 
        FROM 조회할_데이터가_저장된_테이블명
        WHERE 조건식;
*/
-- member테이블에 저장된 모든 열의 값을 행 단위로 조회
SELECT mem_id, mem_name, mem_number, addr,
       phone1, phone2, height, debut_date 
FROM member;

-- SELECT -> 테이블에서 데이터를 조회해서 가져올떄 사용하는 예약어
--  *  ->  조회해 올 데이터가 저장된 모든 열명 
-- FROM -> 테이블에서 데이터를 조회해 온다는 의미의 예약어
-- member -> 조회할 데이터가 저장된 테이블명 
SELECT *
FROM member;
-- 풀어서 해석하면 member테이블에 저장된 모든 열의 내용을 조회해서 가져와라~의 의미.

-- 실습1. 회원테이블(member)테이블에 그룹이름이 저장된 mem_name열의 데이터를 조회
-- 문법
--     SELECT 조회할 데이터가 저장된_열명  FROM 조회할테이블명;
       SELECT mem_name              FROM member;
-- 실습2. 회원테이블(member)테이블에 주소addr, 입사년도debut_date, 그룹이름mem_name열의
-- 데이터들만 조회 
	   SELECT  addr, debut_date, mem_name FROM member;

-- 실습3. 회원테이블(member)에 조회할 열명 대신 별칭을 지어서 조회된 결과창에서 보기위해서는
--       다음 문법을 사용하자.
--       SELECT 조회할열명 별칭명, 조회할열명2 별칭명2
--       FROM 조회할테이블명;
--  또는
--       SELECT 조회할열명 as 별칭명,  조회할열명2 as 별칭명2
--       FROM 조회할데이블명; 

select addr as "주소",  debut_date as "데뷔일자", mem_name "그룹명"
from member;
-- --------------------------------------------------------------------

-- 실습4. 회원테이블(member)에서 회원그룹명이 '블랙핑크'인 모든 열의 데이터를 조회
-- 		 SELECT 조회할데이터가저장된_열명
-- 		 FROM 조회할테이블명
-- 		 WHERE 조건식
SELECT * FROM MEMBER WHERE MEM_NAME = '블랙핑크';

-- --------------------------------------------------------------------
SELECT * FROM MEMBER;
-- 실습 5. member테이블에서 회원 그룹인원(mem_number 열의 데이터)이 4명인 그룹의 
-- 		  모든 열의 데이터 조회 
SELECT * FROM MEMBER WHERE MEM_NUMBER = 4;

-- 실습 6. 관계(비교) 연산자 기호 <=, >=, <, >
-- 		  member 테이블에서 회원 그룹 평균키 데이터가 162 이상인
-- 		  회원 그룹의 아이디와 회원그룹명을 조회
SELECT MEM_ID, MEM_NAME FROM MEMBER WHERE HEIGHT >= 162;

-- 실습 7-1. 관계(비교) 연산자 기호 <=, >=, <, >
-- 			논리 연산자 기호 AND OR

-- member 테이블에서 회원그룹 평균키(height 열에 저장된 데이터들)가 165이상이면서!
-- 그룹 인원(mem_number열에 저장된 데이터들)이 6명 초과인 회원그룹의
-- 회원 그룹명(mem_name 열의 데이터), 그룹평균키(height 열에 저장된 데이터),
-- 그룹 인원(mem_number 열의 데이터) 조회!!
SELECT mem_name, mem_number FROM member WHERE height > 165 AND mem_number > 6;

-- member 테이블에서 평균키가 165이상이거나 또는 그룹 인원이 6명 초과인 회원의 열 정보 조회
-- 조회할 열명은 회원그룹명, 회원평균키, 회원그룹인원수 조회
SELECT mem_name, height, mem_number
FROM member
WHERE height >= 165 OR mem_number > 6;

-- 실습 7-1-1. BETWEEN AND 절을 미사용한 경우 
-- 	그룹의 평균키가 163이상 그리고 165이하인 회원그룹의 그룹명, 평균키, 그룹인원수를 조회 
SELECT mem_name, height, mem_number 
FROM member
WHERE height >= 163 AND height <= 165;

-- BETWEEN AND 절 작성 문법
-- where 조건값들이저장된열명 between 범위의최소값 and 범위의 최댓값;

-- 실습 7-1-1. BETWEEN AND 절을 사용한 경우 
-- 	그룹의 평균키가 163이상 그리고 165이하인 회원그룹의 그룹명, 평균키, 그룹인원수를 조회 
SELECT mem_name, height, mem_number
FROM member
WHERE height BETWEEN 163 AND 165;

-- 실습 8. 그룹평균키가 165 이상이거나 또는 그룹인원이 6명 초과인 
-- 		  회원그룹명, 그룹평균키, 그룹인원 데이터 조회 
SELECT mem_name, height, mem_number
FROM member
WHERE height >= 165 OR mem_number > 6;

-- 실습 8-1. 회원그룹이 사는 지역(addr 열에 저장된 값들)이 
-- 			경기 또는 전남 또는 경남 중 한곳이라도 해당되는 그룹의 이름, 주소 조회 
-- IN() 절을 사용하지 않고 조회 
SELECT mem_name, addr
FROM member
WHERE addr = '경기' OR addr = '전남' OR addr = '경남';

-- 실습 8-2. 회원그룹이 사는 지역(addr 열에 저장된 값들)이 
-- 			경기 또는 전남 또는 경남 중 한곳이라도 해당되는 그룹의 이름, 주소 조회 
-- IN() 절을 사용해서 조회 
SELECT mem_name, addr
FROM member
WHERE addr IN('경기', '전남', '경남');

-- 실습 9. LIKE 절
/*
	문자열 데이터의 일부 그랒가 열의 데이터로 포함되어 있는 행에 대한 열값 조회하는 예약어
    예를 들어 그룹명(mem_name 열에 저장된 값)의 첫 글자가 '우'로 시작하는 단어를 포함하는 
    데이터가 있는 행에 관한 열의 데이터를 조회할 수 있다. 
    
    - 문법
		WHERE 조건열명 LIKE '우%';
        
*/
-- member 테이블에서 그룹명 주에서 '우' 단어로 시작하는 단어가 포함된 데이터가 있으면 
-- 그 행에 관한 모든 열의 데이터 조회
SELECT * FROM member
WHERE mem_name LIKE '우%';

-- 실습 9-1. LIKE 절에 _언더바기호 사용
-- member 테이블에서 그룹명 중에서 앞 두글자는 상관없고 뒷 단어가 '핑크'인
-- 그룹 회원의 이름이 있으면? 이른이 있는 행에 관한 모든 열의 데이터를 조회 
SELECT * FROM member
WHERE mem_name LIKE '__핑크';

-- 실습 9-2. LIKE절에 %단어% 사용 
-- member 테이블에서 그룹명 중에서 '마' 문자를 포함하고 있는 그룹명이 있으면? 
-- 그 그룹의 행에 관한 모든 열의 데이터를 조회 
SELECT * FROM member WHERE  mem_name LIKE '%마%';
		
-- 실습 9-2-1. LIKE 절에 '%단어' 사용 
-- member 테이블에서 그룹명 중에서 '친구; 단어로 끝나는 그룹명이 있으면?
-- 그 그룹의 행에 관한 모든 열의 데이터 조회 
SELECT * FROM member WHERE mem_name LIKE '%친구';

-- 실습 10. 서브쿼리 구문
-- 		   안쪽 SELECT 구문을 이용하여 조회한 결과 데이터들을
-- 		   바깥쪽 SELECT문을 이용해 다시 조회하는 전체 구문을 말한다.
-- 		문법 
-- 			SELECT * FROM 조회할테이블명
-- 		 	WHERE 조건열명 > (SELECT * FROM 조회할테이블명 WHERE 안쪽조건열명 = 조건값);


-- 실습 10-1. 서브쿼리를 사용하지 않고 두개의 SELECT 문을 사용한 예제
-- 			 회원 그룹명이 에이핑크인 회원그룹의 평균키보다~~ 큰! 그룹회원의 정보를 조회하고 싶다. 
-- 	순서 1. 일단 에이핑크 그룹의 평균키 162 조회!
SELECT height FROM member WHERE mem_name = '에이핑크';
-- 	순서 2. 에이핑크의 그룹의 평균키는 순서1. 에서 조회했으므로 164이다.
-- 		   164를 대입해서 164보다 큰! 그룹의 이름과 평균키를 조회해오자 
SELECT mem_name, height FROM member WHERE height > 164;


-- 실습 10-2. 서브 쿼리 사용
SELECT mem_name, height FROM member 
WHERE height > (SELECT height FROM member WHERE mem_name = '에이핑크');


-- 실습 10-3. 서브 쿼리 사용
-- 			 그룹명이 블랙핑크인 그룹의 아이디값을 이용해서 
-- 			 블랙핑크 그룹이 구매한 상품의 이름, 가격, 수량을 buy테이블로부터 조회 
SELECT prod_name, price, amount FROM BUY
WHERE mem_id = (SELECT mem_id FROM member WHERE mem_name = "블랙핑크");


/*
	서브쿼리의 중요성
		동적 필터링 : 서브쿼리를 사용하면, 메인 쿼리의 조건을 동적으로 설정할 수 있다.
				   이로 인해 쿼리가 더 유연해진다. 
		복잡한 조건 처리 : 서브쿼리는 복잡한 조건을 쉽게 처리할 수 있게 해주며, 특정 값을 
						기준으로 다른 값을 조회할 수 있다. 
*/

-- ---------------------------------------------
-- 연습문제
-- 1번. 회원테이블에서 모든 회원의 ID와 이름을 조회해라.
SELECT mem_id, mem_name FROM member; 

-- 2번. 회원 테이블에서 평균키가 167CM이상인 회원의 모든 열의 정보 조회해라. 
SELECT * FROM member WHERE height >= 167;

-- 3번. 회원 테이블에서 그룹인원수가 5명 이하인 그룹의 이름과 인원수 조회해라. 
SELECT mem_name, mem_number FROM member WHERE mem_number <= 5;

-- 4번. 구매 테이블에서 가격이 100 이상인 상품의 이름과 가격을 조회해라.
SELECT prod_name, price FROM buy WHERE price >= 100;

-- 5번. 회원 테이블에서 주소가 '경기' 회원의 모든 열 정보를 조회해라. 
SELECT * FROM member WHERE addr = '경기';

-- 6번. 구매 테이블에서 '패션' 분류의 상품 이름과 수량을 조회해라.
SELECT prod_name, amount FROM buy WHERE group_name = '패션';

-- 7번. 회원 테이블에서 '서울'에 사는 회원 이름과 전화번호를 조회해라.
SELECT mem_name, phone1, phone2 FROM member WHERE addr = '서울'; 

-- 8번. 회원 테이블에서 그룹명이 '트와이스'인 회원의 모든 열 정보를 조회해라.
SELECT * FROM member WHERE mem_name = '트와이스';

-- 9번. 구매 테이블에서 특정 그룹('우주소녀')의 구매내역을 조회해라.
SELECT * FROM buy WHERE mem_id = (SELECT mem_id FROM member WHERE mem_name = '우주소녀');

-- 10번. 회원 테이블에서 인원수가 8명인 그룹의 모든 열 정보를 조회해라.
SELECT * FROM member WHERE mem_number = 8;

-- 11번. 구매 테이블에서 상품이름에 '지갑'이 포함된 상품의 모든 열 정보를 조회해라.
SELECT * FROM buy WHERE prod_name LIKE '%지갑%';

-- 12번. 회원 테이블에서 평균키가 165cm 이하인 그룹의 이름과 평균키를 조회해라.
SELECT mem_name, height FROM member WHERE height <= 165;

-- 13번. 회원 테이블에서 '여자친구' 또는 '트와이스' 그룹 이름을 가진 모든 열 정보를 조회해라.
SELECT * FROM member WHERE mem_name IN ('여자친구', '트와이스');

-- 14번. 구매 테이블에서 수량이 3 이상인 상품의 이름과 가격을 조회해라.
SELECT prod_name, price FROM buy WHERE amount >= 3;

-- 15번. 회원 테이블에서 사는 지역이 '강남'인 회원의 이름과 주소를 조회해라.
SELECT mem_name, addr FROM member WHERE addr = '강남';

-- 16번. 구매 테이블에서 '디지털' 분류의 상품 중에서 가격이 200 이하인 상품의 이름을 조회해라.
SELECT prod_name FROM buy WHERE group_name = '디지털' AND price <= 200;

-- 17번. 회원 테이블에서 그룹 평균키가 162 이상인 그룹의 이름을 조회해라.
SELECT mem_name FROM member WHERE height >= 162;

-- 18번. 구매 테이블에서 특정 그룹 ('레드벨벳')의 구매 내역에서 가격이 50 이상인 상품의 열 정보 조회해라.
SELECT * FROM buy WHERE mem_id = (SELECT mem_id FROM member WHERE mem_name = '레드벨벳') AND price >= 50;

-- 19번. 














