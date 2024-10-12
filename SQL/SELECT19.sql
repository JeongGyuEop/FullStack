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

-- 실습4. 회원테이블(member)에서 회원그룹명(mem_name열에 저장된 데이터들)이 
--       '블랙핑크'와 같은 행이 있는  모든 열의 데이터를 조회
--       SELECT 조회할데이터가저장된_열명
--       FROM 조회할테이블명
--       WHERE 조건식;
SELECT *
FROM MEMBER
WHERE mem_name = '블랙핑크';

-- -----------------------------------------------------------
SELECT * FROM MEMBER;

-- 실습5. member테이블에서 회원그룹인원(mem_number열의 데이터)이 4명인 그룹의 
--       모든 열의 데이터 조회 
SELECT * FROM MEMBER  WHERE mem_number = 4;

-- 실습6. 관계(비교) 연산자 기호  <=   >=    <    >
--  member테이블에서 회원 그룹 평균키(height열의 데이터) 데이터가 162이상인 
--  회원 그룹의 아이디(mem_id열에 저장된 데이터)와 
--  회원그룹명(mem_name열에 저장된 데이터)을 조회 
SELECT mem_id, mem_name
FROM MEMBER 
WHERE height >=  162;

-- 실습 7-1. 관계(비교) 연산자 기호   <=   >=  <   >  
--          논리 연산자 기호   AND   OR

-- member테이블에서 회원그룹 평균키(height열에 저장된 데이터들)가 165 이상이면서!
-- 그룹 인원(mem_number열에 저장된 데이터들)이 6명 초과인 회원그룹의 
--  회원 그룹명(mem_name열의 데이터), 그룹평균키(height열에 저장된 데이터),
--  그룹 인원(mem_number열의 데이터) 조회!!!!!!!!
select mem_name, height, mem_number
from member
where height  >= 163  AND   mem_number >  6;

-- member테이블에서  평균키가 165이상이거나 또는~ 그룹 인원이 6명 초과인 회원의 열 정보 조회
-- 조회할 열명은  회원그룹명, 회원평균키, 회원그룹인원수 조회 
select mem_name, height, mem_number
from member
where height >=  165  OR  mem_number >  6;


-- 실습 7-1-1. BETWEEN AND절 미사용 한 경우 
--  그룹의 평균키가 163이상 그리고 165이하인 회원그룹의 그룹명, 평균키, 그룹 인원수 조회
select mem_name, height, mem_number
from member
where height >= 163 AND  height  <= 165;

-- BETWEEN AND 절 작성 문법
	-- where 조건값들이저장된열명 between 범위의최솟값 and 범위의최댓값;
    --              height   between   163     and  165;
    
-- 실습 7-1-2. BETWEEN AND절 사용 한 경우 
--  그룹의 평균키가 163이상 그리고 165이하인 회원그룹의 그룹명, 평균키, 그룹 인원수 조회
select mem_name, height, mem_number
from member
where height between 163 and 165;

-- 실습 8. 그룹평균키가 165이상이거나 또는 그룹인원이 6명 초과인 
--        회원그룹명, 그룹평균키, 그룹인원 데이터 조회
select mem_name, height, mem_number
from member
where height >= 165 OR mem_number > 6;

-- 실습 8-1. 회원그룹이 사는 지역(addr열에 저장된 값들)이 
--          경기 또는 전남 또는 경남중 한곳이라도 해당되는 그룹의 이름, 주소 조회
--  IN()절을 사용하지 않고 조회 
select mem_name, addr
from member
where addr = '경기' OR  addr = '전남' OR  addr = '경남';

-- 실습 8-2. 회원그룹이 사는 지역(addr열에 저장된 값들)이 
--          경기 또는 전남 또는 경남중 한곳이라도 해당되는 그룹의 이름, 주소 조회
--  IN()절을 사용하여  조회 
select mem_name, addr
from member
where addr IN('경기','전남','경남');

-- 실습 9. LIKE절
/*
	- 문자열 데이터의 일부 글자가 열의 데이터로 포함되어 있는 행에 대한 열값 조회하는 예약어
      예를 들어  그룹명(mem_name열에 저장된 값)의  첫글자가 '우'로 시작하는 단어를 포함하는
      데이터가 있는 행에 관한 열의 데이터를 조회할수 있습니다.
	- 문법
		where 조건열명 LIKE '우%';
	
member테이블에서 그룹명 중에서 '우' 단어로 시작하는 단어가 포함된  데이터가 있으면
그 행에관한 모든 열의 데이터 조회     
*/
select * from member
where mem_name LIKE '우%';

-- 실습 9-1. LIKE절에  _언더바기호 사용
-- member테이블에서 그룹명중에서 앞 두글자는 상관없고 뒷 단어가 '핑크'인 
-- 그룹 회원의 이름이 있으면? 이름이 있는 행에 관한 모든 열의 데이터를 조회
select * from member  
where mem_name LIKE '__핑크';

-- 실습 9-2. LIKE절에 %단어% 사용
-- member테이블에서 그룹명 중에서 '마' 문자 를 포함하고 있는 그룹명이 있으면?
-- 그 그룹의 행에 관한 모든 열의 데이터를 조회
select * from member where mem_name LIKE '%마%';

-- 실습 9-2-1. LIKE절에 '%단어' 사용
-- member테이블에서 그룹명중에서 '친구' 단어로 끝나는 그룹명이 있으면?
-- 그 그룹의 행에 관한 모든 열의 데이터 조회 
select * from member where mem_name LIKE '%친구';

-- 실습 10. 서브쿼리 구문
--        - 안쪽 SELECT구문을 이용하여 조회한 결과 데이터들을
--          바깥쪾 SELECT문을 이용해 다시 조회하는 전체 구문을 말함
--        - 문법
--              SELECT  * FROM 조회할테이블명
--              WHERE 조건열명 > ( SELECT * FROM 조회할테이블명 
--                               WHERE 안쪽조건열명 = 조건값 );

-- 실습 10-1. 서브쿼리를 사용하지 않고 두개의 SELECT문을 사용한 예

-- 회원 그룹명이 에이핑크인 회원그룹의 평균키보다~~ 큰!  그룹회원의 정보를 조회 하고싶다

-- 순서1. 일단 에이핑크 그룹의 평균키 164 조회!
select height from member
where  mem_name = '에이핑크';
-- 순서2. 에이핑크 그룹의 평균키는 순서1.에서 조회했으므로 164입니다
--        164를 대입해서 164보다 큰! 그룹의 이름과 평균키를 조회해 오자
select mem_name, height from member
where height > 164;

-- 실습 10-2. 서브 쿼리 사용
select mem_name, height from member
where height > ( select height from member where mem_name='에이핑크');

-- 실습 10-3. 서브 쿼리 사용
--   그룹명이 블랙핑크인 그룹의 아이디값을 이용해
--   블랙핑크 구룹이 구매한 상품의 이름, 가격, 수량을  buy테이블로 부터 조회 

select prod_name, price, amount from buy
where mem_id = (select mem_id from member where mem_name = '블랙핑크');
/*
서브쿼리의 중요성
동적 필터링: 서브쿼리를 사용하면, 메인 쿼리의 조건을 동적으로 설정할 수 있습니다. 
             이로 인해 쿼리가 더 유연해집니다.
복잡한 조건 처리: 서브쿼리는 복잡한 조건을 쉽게 처리할 수 있게 해 주며, 
                  특정 값을 기준으로 다른 값을 조회할 수 있습니다.
*/
-- --------------------------------------------------------------------------------------------------------
-- 연습문제
--  1번.  회원테이블에서 모든 회원의 ID와 이름을 조회 해라
SELECT mem_id, mem_name FROM member;

--  2번.  회원테이블에서 평균키가 167CM이상인 회원의 모든 열의 정보 조회 해라.
SELECT * FROM member WHERE height >= 167;

--  3번.  회원테이블에서 그룹인원수가 5명 이하인 그룹의 이름과 인원수 조회 해라.
SELECT mem_name, mem_number FROM member
WHERE mem_number <= 5;

--  4번.  구매 테이블에서 가격이 100 이상인 상품의 이름과 가격을 조회 해라.
SELECT prod_name, price FROM buy
WHERE  price >= 100;

--  5번.  회원 테이블에서 주소가 '경기' 회원의 모든 열정보를 조회 해라.
SELECT * FROM member
WHERE addr = '경기';

--  6번.  구매 테이블에서 '패션' 분류의 상품 이름과 수량을 조회 해라.
SELECT prod_name, amount FROM buy
WHERE group_name = '패션';

--  7번.  회원 테이블에서 '서울'에 사는 회원 이름과 전호번호를 조회하라. 
SELECT mem_name, phone1, phone2 FROM member
WHERE addr = '서울';

--  8번.  회원 테이블에서 그룹명이 '트와이스'인 회원의 모든 열정보를 조회해라.
SELECT * FROM MEMBER  WHERE mem_name = '트와이스';

--  9번.  구매 테이블에서 특정그룹('우주소녀')의 구매 내역을 조회하라.
SELECT * FROM buy WHERE mem_id = (SELECT mem_id FROM member WHERE mem_name = '우주소녀');

SELECT * FROM buy WHERE mem_id = (SELECT mem_id FROM member WHERE mem_name = '블랙핑크');


--  10번. 회원 테이블에서 인원수가 8명인 그룹의 모든 열정보를 조회 하라 
SELECT * FROM member WHERE mem_number = 8;

--  11번. 구매 테이블에서 상품이름에 '지갑'이 포함된 상품의 모든 열정보를 조회 하라 
SELECT * FROM buy  WHERE prod_name LIKE '%지갑%';

--  12번. 회원 테이블에서 평균키가 165cm 이하인 그룹의 이름과 평균키를 조회 하라
SELECT mem_name, height FROM member WHERE height <= 165;

--  13번. 회원 테이블에서 '여자친구' 또는 '트와이스' 그룹이름가진 모든 열정보를 조회 하라
SELECT * FROM member WHERE mem_name IN('여자친구','트와이스');

-- 14번. 구매 테이블에서 수량이 3이상인 상품의 이름과 가격을 조회하라 
SELECT prod_name,price FROM buy WHERE amount  >= 3;

-- 15번. 회원 테이블에서 사는지역이 '강남'인 회원의 이름과 주소를 조회 하라 
SELECT mem_name, addr FROM member WHERE addr = '강남';    

-- 16번. 구매 테이블에서 '디지털' 분류의 상품 중 가격이 200이하인 상품의 이름을 조회 하라
SELECT prod_name FROM buy 
WHERE group_name = '디지털' AND price <= 200;

-- 17번. 회원 테이블에서 그룹 평균키가 162cm이상인 그룹의 이름을 조회하라
SELECT mem_name FROM member
WHERE height  >= 162;

-- 18번. 구매 테이블에서 특정그룹('레드벨벳')의 구매 내역에서 가격이 50이상인 상품의 열정보 조회하라
SELECT * FROM buy WHERE mem_id = (SELECT mem_id from member where mem_name='레드벨벳') 
								 AND price >= 50;

SELECT * FROM buy WHERE mem_id = (SELECT mem_id from member where mem_name='블랙핑크') 
								 AND price >= 50;
-- ----------------------------------------------------------------------------

-- ORDER BY 절 문법

-- SELECT * FROM 조회할테이블명
-- WHERE 조건식
-- ORDER BY 정렬할_데이터가_저장된_열명 ASC 또는 DESC;

-- 참고.  ASC -> Ascending의 약자로 오름차순 정렬을 의미 
--       DESC -> Descending의 약자로 내림차순 정렬을 의미 
select * from member;

-- 실습1. 데뷔일자데이터(debut_date열에 저장된 날짜들)를 기준으로 
--       오름 차순 정렬(데뷔날짜가 빠른 날짜순) 하여 조회시 ORDER BY 절을 사용합니다
select * from member
order by debut_date ASC;

-- 실습2. 데뷔일자를 기준으로 내림차순 정렬(데뷔날짜가 늦은 순)하여 조회시
--        ORDER BY 절을 사용합니다
select * from member
order by debut_date DESC;

-- 실습3. ORDER BY절과 WHERE조건절 함께 사용하기
-- 그룹평균키(height열에 저장된 데이터들)가 164이상인 
-- 그룹회원들의 키가 큰 순서대로(내림차순)정렬 해서 
-- 그룹명(mem_name열에 저장된 데이터들), 
-- 그룹아이디(mem_id열에 저장된 데이터들), 
-- 그룹평균키(height열에 저장된 데이터들), 
-- 데뷔날짜(debut_date열에 저장된 세로방향의 데이터들) 조회 
SELECT mem_name, mem_id, height, debut_date
FROM member
WHERE height >= 164
ORDER BY height DESC;

-- 실습4. ORDER BY절과 WHERE조건절 함께 사용하기2 
--       (정렬 조건 하나이상 설정가능)

-- 그룹 평균키(height열에 저장된 데이터들)가 큰(내림차순) 순서대로 조회하되,
-- 같은 평균키를 가진 그룹들이 있으면  데뷔일자가 빠른순서(오름차순)로 정렬해서 조회
select mem_name, mem_id, height, debut_date
from member
where height >= 164
order by height desc, debut_date asc;
-- ----------------------------------------------
-- LIMIT 예약어 : 테이블에 저장된 전체행들 중에서
--               원하는 행의 갯수를 정해서 조회할때 사용하는 예약어
/*
	문법
		SELECT * FROM 조회할_테이블명
	    WHERE 조건식
        ORDER BY 정렬할_데이터가_저장된_열명 ASC 또는 DESC
        LIMIT 조회할_행의갯수를_숫자로_작성;
*/
-- 실습5. member테이블에서 전체 행 데이터(레코드)들 중에서
--       3개의 행만 조회 
SELECT * FROM MEMBER
LIMIT 3; -- 0,3 같은 의미로 
		 -- 0 index행 번호 위치의 조회된 행부터 3개의 행을 잘라서 조회해옴

-- LIMIT 조회할행의index위치번호, 몇개의행을 조회할건지  행갯수;

-- 실습6. member테이블에서 전체 행데이터들 중에서
--       데뷔일자가 빠른 순으로 회원정보 3건만 조회하려면?
--       order by 절과 limit 시작행_인덱스위치번호,  조회할 행의 갯수;
select * from member
order by debut_date asc
limit 0, 3;

-- 실습7. member테이블에서 회원그룹평균키가 큰 순으로 정렬해서 조회하되,
--       정렬해서 조회한 결과 데이터들 중에서 
--       3 index위치 행의 레코드 부터 2개의 행(레코드)만 조회!
select * from member
order by height desc
limit 3, 2;

-- -------------
-- DISTINCT 예약어 : 조회할 열의 데이터들이 중복되면 중복된 데이터를 1개만 남기고
--                 조회시키는 예약어.
--          요약 : 중복된 열의 데이터가 저장되어 있으면 하나로 조회하는 예약어

/*	
  문법
		SELECT DISINCT 조회할열명 
        FROM 조회할테이블명
        WHERE 조건식
        ORDER BY 정렬기준의_열명  정렬방식
        LIMIT 숫자;
*/
-- 실습8. 모든 그룹회원 사는 지역(addr열에 저장된 데이터들)을 조회 
select addr, mem_name 
from member;

-- 실습8-1. ORDER BY절을 사용해 같은 지역에 사는 주소들을 오름차순 정렬해서 조회
select addr from member
order by addr ASC;

-- 실습8-2. DISITNCT 사용해서 열에 중복된 데이터를 하나로 통일해서 하나로 조회
select distinct addr from member
order by addr DESC;

-- ----------------------------------------


-- GROUP BY 절 
-- 열에 저장된 데이터들을 그룹으로 묶어서 표현하는 구문.
-- GROUP BY 절은 집계함수들 중 하나랑 같이 작성해서 사용해야 합니다. 

-- 제공해 주는 집계함수들
-- SUM()  :  열명을 SUM(열명)로 지정하면 열에 저장된 데이터들을 합계를 하여 반환해 줍니다. 
-- AVG()  :  열명을 AVG(열명)로 지정하면 열에 저장된 데이터들의 평균을 구하여 반환해 줍니다.
-- MIN()  :  열명을 MIN(열명)로 지정하면 열에 저장된 데이터들 중 최소 작은 데이터를 반환해 줍니다.
-- MAX()  :  열명을 MAX(열명)로 지정하면 열에 저장된 데이터들 중 가장 큰 데이터를 반환해줍니다.
-- COUNT() : 행의 갯수를 세어서 숫자로 반환해주는 함수 
-- COUNT(DISTINCT)  : 행의 갯수를 셉니다.(중복된 데이터 1개만 인정) 

-- GROUP BY 절 문법
	-- SELECT 열명1, 집계함수(열명2) FROM 조회할테이블명
	-- GROUP BY 그룹으로묶을 데이터가 저장된 열명
    -- HAVING 조건식 
    -- ORDER BY 정렬기준열명 정렬방식
    -- LIMIT 숫자


-- buy(회원그룹들이 구해만 상품정보들이 저장된 테이블)테이블의 
-- 모든 열에 대한 데이터들 조회
select * from buy;

-- 실습9. buy테이블에서 조회하는데
--       그룹아이디(mem_id열에 저장된 데이터들)별로! 그룹으로 묶은 후
--       구매수량(amount열에 저장된 데이터들)들의 합계를 구하기 위해
--       SUM(amount) 집계함수를 사용하여 
--       그룹아이디(mem_id열에 저장된 데이터), 총구매수량을 조회
select mem_id as '그룹아이디', SUM(amount) as '총_구매수량'
from buy  
group by mem_id;
 
-- 실습10. 
-- buy테이블에서 조회하는데...
-- 그룹아이디별로 묶은 후 
-- 그룹아이디별로 구매한 상품들의 총 구매 금액을 계산하여
-- 그룹아이디, 총구매수량, 총구매_금액 을 조회하자
select mem_id as '그룹아이디',  
       SUM(amount) as '총_구매수량',
	   SUM(amount * price) as '총구매_금액'
from buy
group by mem_id;
       
-- 실습11. 전체 회원그룹이 구매한 총 구매 수량의 평균을 구해서 조회된 결과를 보여주자
select AVG(amount) as '총구매수량의 평균' from buy;

-- 실습12. 각 ~~ 회원그룹들이(mem_id) 몇개의 상품을 각각 평균 구매했는지 
--        평균 구매 개수 조회

-- 참고.  각 그룹회원들을 식별할 값은 mem_id열에 저장된 그룹id를 그룹으로 묶어주자
select mem_id as '회원그룹아이디',  AVG(amount) as '평균 구매 개수'
from buy
group by mem_id;

-- 실습13. member테이블에 저장된 그룹회원의 전체 행의 갯수 조회
select count(*) from member;

-- 실습13-1. member테이블에서 연락처(phone1,phon2열데 저장된 데이터들)가
--          저장되어 있는 그룹의 그룹회원의 레코드(행) 갯수 조회
select count(phone1) as '연락처가있는그룹수' 
from member;

-- HAVING 조건절 
-- 			WHERE 조건절 대신 그룹으로 묶어준 데이터의 조건검사 하는 구문 

-- 문법
--    SELECT 열명1, 집계합수(열명2) FROM 조회할테이블명
--    GROUP BY 그룹으로묶을데이터가저장된 열명 
--    HAVING 조건식
--    ORDER BY 정렬기준데이터가저장된_열명  DESC 또는 ASC;

-- 실습 14. buy테이블에서 조회합니다.
-- 		   회원 그룹 아이디를 그룹으로 묶어서 
--         그룹아이디 별로 총구매금액과  그룹아이디열의 데이터 조회
SELECT mem_id as '회원그룹아이디', SUM(price*amount) as '총 구매 금액'
FROM buy
GROUP BY mem_id;

-- 실습 14-1. 
-- 위 실습14.는 그룹아이디별로 총구매한 금액을 조회해서 보여줍니다
-- 만약 그룹아이디별로 총구매한 금액이 1000이상이면 사은품을 증정하려고한다면
-- 그룹 아이디별로 총구매한 금액이 1000이상인 그룹의 총구매금액, 그룹아이디를 조회합니다 
SELECT mem_id as '회원그룹아이디', SUM(price*amount) as '총 구매 금액'
FROM buy
GROUP BY mem_id
HAVING  SUM(price*amount) >= 1000;

-- 실습 14-2. 
-- 위 실습14는 그룹아이디별로 총구매한금액을 조회해서 보여줍니다. 
-- 만약 그룹아이디별로 총구매한 금액이 1000이상이면 사은품을 증정하려고 한다면
-- 그룹아이디별로 총구매한 금액이 1000이상인 그룹을 조회 해야 합니다.
-- 또한 사은품은 순위에 따라 사은품이 다를수 있기떄문에 
-- 총 구매 금액이 큰 순서대로(내림차순정렬) 하여 최종 조회해서 보여줌 
SELECT mem_id as '회원그룹아이디', SUM(price*amount) as '총 구매 금액'
FROM buy
GROUP BY mem_id
HAVING  SUM(price*amount) >= 1000
ORDER BY SUM(price*amount) DESC;

-- -----------------------------------------------------------
/*
 주제 :  데이터베이스 내부에 만든 테이블에 
        데이터를 추가(입력)/수정/삭제하는 SQL문을 배우자 
        
      INSERT문 :   테이블에 행 데이터를 추가(입력)해서 저장할떄 사용되는 SQL문
      
      INSERT문 기본 문법
		insert into 행을추가할테이블명(추가할값이저장될_열명1, 열명2, 열명3)
        values(열명1에_추가할_값1, 열명2에_추가할_값2, 열명3에_추가할_값3);
*/
-- market_db데이터베이스 사용하기 위해 선택 
USE market_db;

/*
	테이블 생성 문법
    
		create table 생성할테이블명(
          
           생성할열명1 열에저장할데이터유형,
           생성할열명2 열에저장할데이터유형,
           생성할명명3 열에저장할데이터유형

        );
*/
-- hongong1이라는 이름의 테이블 생성
create table hongong1(
	toy_id INT,        -- 장난감 아이디
    toy_name CHAR(4),  -- 장난감 이름
    age INT            -- 장난감 나이 
);

-- hongong1테이블에 저장된 모든열의 데이터를 행단위로 조회
SELECT * FROM hongong1;

/* 
INSERT문 기본 문법
		insert into 행을추가할테이블명(추가할값이저장될_열명1, 열명2, 열명3)
        values(열명1에_추가할_값1, 열명2에_추가할_값2, 열명3에_추가할_값3);

*/

-- hongong1테이블에 하나의 행 데이터 추가하여 저장 
insert into hongong1(toy_id, toy_name, age) 
values(1,    '우디', 25);

-- hongong1테이블에 toy_id열과 toy_name열에만 데이터를 추가하여 저장할 값 넣어보자
insert into hongong1(toy_id, toy_name)
values(2, '버즈');

-- hongong1테이블에 열명의 순서를 바꿔서 추가로 행을 저장
-- 주의 할점은 테이블명()사이에 작성한 열명의 순서에 맞게 
-- values()사이에 저장할 값을 넣어서 추가 해야 합니다 
insert into hongong1(toy_name, age, toy_id)
values('제시', 20, 3);


-- hongong1 테이블에 (열명1,열명2,열명3) 을 생략하고
-- values(열_추가값1,열_추가값2,열_추가값3) 구문만 작성 해
-- 새로운 행을 추가할수 있다. 
-- 단! 주의 할점은 테이블 생성시 만든 열의 순서에 맞게 값을 작성 해야 합니다. 

						-- toy_id, toy_name, age
insert into hongong1 values(4, '영구', 30);

/*
  AUTO_INCREMENT 예약어
   - 테이블을 새로 생성할때 열이름 옆(뒤)에 설정하는 예약어로
     열 에 대한 값을 INSERT문장으로 추가하지 않아도 
     자동으로 1씩 증가되면서 추가가 되게 하는 예약어.
*/
-- hongong2 테이블 새로 생성
create table hongong2(
    toy_id INT auto_increment primary key,
    toy_name CHAR(4),
    age INT
);
-- hongong2 테이블에 자동 증가 하는 열의 데이터를 null값으로 채워 놓고 데이터 추가
insert into hongong2(toy_id, toy_name, age)
values(null, '보핍', 25);

insert into hongong2(toy_id, toy_name, age)
values(null, '슬링키', 22);

insert into hongong2(toy_id, toy_name, age)
values(null, '렉스', 21);

select * from hongong2;

-- toy_id열에 추가할 값을 작성하지 않고 다른 열의 값을 추가시키면
-- auto_increment 제약조건 예약어를 설정해 놓은 toy_id열의 값은 
-- 자동으로 1증가하면서 값이 추가된다.
insert into hongong2(toy_name, age)
values('맹구', 100);


-- hongong2테이블의 toy_id열에는 auto_increment 제약조건을 설정 해 놓았기떄문에
-- 자동증가값이 4 까지 설정되어 있다는 것을 확인 할수 있지만 
-- 자동으로 증가된 값이 얼마마큰 되었는지 확인하는 검색 구문
select last_insert_id();

/*
 auto_increment 제약조건을 지정한 열은 1부터 insert가 되기 때문에
 특정 값 부터 insert되기 하기 위해 auto_increment 제약조건의 속성의 값을 설정해야한다
*/
alter table hongong2 auto_increment = 100; -- 초기 100부터 1씩 증가되도록
										   -- 초기값 100으로 설정 

insert into hongong2 (toy_name, age) values('재남', 35);

select * from hongong2;
  
/*
   auto_increment 제약조건을 지정한 열은  100부터 1씩 증가되면서 insert가 됩니다.
   하지만 3씩 증가 즉! 103, 106, 109 형태로 증가 시킬수 있게 
   @@auto_increment_increment 변수의 값을 변경 시키면 된다.
*/
-- hongong3 테이블 새로 생성 
create table hongong3(
   toy_id INT auto_increment primary key,
   toy_name CHAR(4),
   age    INT
);

-- auto_increment 의 자동증가 시작되는 값을 1000으로 설정
alter table hongong3 auto_increment = 1000;

-- auto_increment는 1000부터 열의 데이터가 추가되어 1씩 증가되어 추가되지만
-- 3씩 증가하여 추가를 시키려면 다음과 같이 시스템변수 의 값을 설정
set @@auto_increment_increment = 3; -- 자동으로 증가 되는 값을 3으로 설정

insert into hongong3 values(null, '토마스', 20);
insert into hongong3 values(null, '제임스', 23);
insert into hongong3 values(null, '고든', 25);

insert into hongong3(toy_name,age) values('개똥이',100);
insert into hongong3(toy_name,age) values('똘똘이',5);

select * from hongong3;

/*
insert into ~ select 구문
- 특정 테이블에 select구문을 이용해 조회한 표 형태의 결과 데이터들을
  insert into 문장을 이용해 테이블에 행의 데이터들을 한번에 추가시키는 구문
- 문법
		insert into 테이블명(열명1, 열명2, 열명3)
        select 열명1, 열명2, 열명3 from 테이블명;
*/
-- world 데이터베이스 사용을 위해 선택
USE world;

-- world 데이터베이스 내부에 만들어져 있는 테이블 목록 검색
SHOW tables;

-- world 데이터베이스에 만들어져 있는 city테이블의 레코드(행)의 총 갯수 조회
select count(*) as '총 레코드 수' from city;

-- 테이블 내부에 열이 어떻게 만들어져 있는지 열의 구성 확인 구문
-- desc 테이블명;
desc city;

-- city 테이블에 저장된 전체 4079행 데이터 중에서 
-- 5개의 행데이터만 조회 
select * from city limit 0,5;

/*
	테이블 생성 문법
    
		create table 생성할테이블명(
          
           생성할열명1 열에저장할데이터유형,
           생성할열명2 열에저장할데이터유형,
           생성할명명3 열에저장할데이터유형

        );
*/

-- city테이블에 저장된 도시명과 인구수를 조회해서 저장할 
-- city_popul 테이블 생성하기
create table city_popul(
	city_name char(35), -- 도시명 
	population int      -- 각 도시에 사는 인구수 
);
/*
  insert into ~ select구문을 이용하여 
  다른 테이블인 city테이블에서 도시명(name)과 인구수(population)를 
  조회 한 4079 행의 정보를 
  다른 테이블 city_popul테이블의 city_name과 population열에 각각 저장시킴
*/
insert into city_popul(city_name, population)
select name, population from city;

-- city_popul테이블에 city테이블에서 조회한 4079행의 정보가 제대로 
-- city_name열과 population열에 추가되어 저장되는지 나중에 확인
select * from city_popul;

select count(*) from city_popul;


/*
  insert into 다른_데이터베이스명.추가할데이터의_테이블명 (city_name,population)
  select name,population from 검색할_데이터베이스명.검색할테이블명;
*/
-- insert into market_db.city_popul(city_name, population)
-- select name,population from world.city;

-- -----------------------------------------------

/*
   UPDATE ?
   - 테이블에 이미 저장되어 있는 열의 데이터를 수정(변경)하는 SQL문 중 하나    
   - 문법
		 UPDATE 수정할데이터가저장된_테이블명
         SET 수정할데이터가저장된_열명1 = 수정할값1, ......
         WHERE 조건식;
         
         UPDATE 테이블명 
         SET 수정할_열명 = 수정될_값
         WHERE 조건절;		
*/
-- city_popul테이블에 저장된 도시이름이 'Seoul'인 모든열의 데이터 조회
select * from city_popul
where city_name = 'Seoul';

-- city_popul테이블의 도시 이름중에서 
-- 영문 'Seoul' 데이터를  한글 '서울' 로 수정 하자 
UPDATE city_popul 
SET city_name = '서울'
WHERE city_name = 'Seoul';

-- city_popul테이블에 저장된 도시이름이 '서울'인 모든열의 데이터 조회
select * from city_popul
where city_name = '서울';

-- city_popul테이블의 city_name열에 저장된 데이터가 'New York'을 '뉴욕'으로 수정하고 동시에
--                  population열에 저장된 인구수를 0으로 수정 
-- 조건 -> city_name열에 저장된 데이터가 'New York'인 열의 값이면  수정 해야 합니다.

-- 순서1. 먼저 수정할 데이터 조회
select city_name, population
from city_popul
where city_name = 'New York';
-- 순서2. city_popul테이블의 city_name열에 저장된 데이터가
--       영문 'New York'을 한글 '뉴욕'으로 수정하는 동시에
--       population열에 저장된 인구수를 0으로 수정 하자 
UPDATE city_popul
SET city_name = '뉴욕', population = 0
WHERE city_name = 'New York';

-- 순서3. 수정된 열의 데이터 확인을 위해 조회
select city_name, population
from city_popul
where city_name = '뉴욕';

-- city_popul테이블에 city_name열에 저장된 도시이름들을 모두~ '서울'로 수정
-- 아래 구문은 눈으로만 보고 실행하지 말자~~~
-- WHERE절이 없는 UPDATE문은 전체 행의 열데이터를 모두 수정하기 떄문에 주의하자.
/*
UPDATE city_popul
SET city_name = '서울';
*/

select * from city_popul;

-- city_popul테이블에 저장된 모든 인구수를 10000으로 나눈 계산된 값들을
-- population열의 값들로 수정
update city_popul
set population = population / 10000;

-- 수정된 열 값을 조회 (단 ! 0 index위치의 행부터 5개의 행만 조회)
select * from city_popul
limit 0,5;

-- ---------------------------------------------------------

/*
	 DELETE 문
     - 테이블에 저장된 행 단위로 데이터를 삭제하는 SQL문
     - 문법
			DELETE FROM 테이블명
            WHERE 조건식;
*/
select * from city_popul
where city_name LIKE 'New%';

-- city_popul테이블에 city_name열에 저장된 데이터들 중에서 
-- 'New'단어로 시작하는 도시이름의 행 11개를 삭제
DELETE FROM city_popul
WHERE city_name LIKE 'New%';

-- city_popul테이블에 저장된 총 행의 갯수는 4079개 중에서 
-- 4068행의 갯수로 조회 되게 전체 행의 갯수 조회
select count(*) from city_popul;

-- -----------------------------------------------------------------------
/*
	대용량의 데이터가 저장된 테이블을 삭제 하기 위해 먼저 실습 준비
    
    대용량 데이터를 저장하기 위해 일단 테이블 3개 준비
    방법 : 대용량의 데이터들이 저장된 테이블을 SELECT구문으로 조회해 와서
          CREATE구문을 이용하여 총 3개의 테이블을 생성 
*/
create table market_db.big_table1(
	SELECT * FROM world.city, sakila.country
);

-- 몇개의 행 데이터가 저장되어있는행 행의 갯수 조회 
select count(*) from market_db.big_table1;
 

create table market_db.big_table2(
	SELECT * FROM world.city, sakila.country
);

-- 몇개의 행 데이터가 저장되어있는행 행의 갯수 조회 
select count(*) from market_db.big_table2;


create table market_db.big_table3(
	SELECT * FROM world.city, sakila.country
);

-- 몇개의 행 데이터가 저장되어있는행 행의 갯수 조회 
select count(*) from market_db.big_table3;

-- DELETE : 테이블 저장된 행 단위 데이터 삭제하는 SQL문 
DELETE FROM market_db.big_table1;  -- 2.179초 
-- 삭제 되었는지 조회 해보기 
SELECT * FROM market_db.big_table1;

-- DROP : 테이블 자체를 삭제하는 SQL문
-- 문법
--     DROP TABLE 삭제할테이블명;
DROP TABLE market_db.big_table2;  -- 0.031초 


-- TRUNCATE 문 : 테이블에 저장된 행단위의 데이터 삭제하는 Sql문 
--               단, where문을 사용할 수 없습니다. 
--               조건식 없이 전체 행을 삭제할떄만 TRUNCATE 문 사용 !!!!!!!!
-- 문법 TRUNCATE TABLE 삭제할테이블명;
truncate table big_table3;   -- 0.015초 

-- 모든 행이 삭제 되었는지 확인을 위해 조회
select * from big_table3;

-- ---------------------------------------------------------------
/*
	MYSQL DMBS를 관리하기 위한 언어는 SQL입니다.

		SQL종류
        1. DDL(Data Deinition Language) - 데이터 구조를 정의하고 관리 하는 작업을 처리하는 SQL문들
           종류
				CREATE 문 : 데이터베이스 생성, 테이블생성, 인덱스생성, 뷰 생성 등의  개체를 생성할때 사용되는 구문
                ALTER 문  : 기존 테이블의 열의 구조를 변경하거나 수정할때 사용되는 구문 
                DROP 문  : 데이터베이스 삭제, 테이블 삭제, 인덱스 등을 삭제 할떄 사용되는 구문 
                TRUNCATE 문 : 테이블의 모든 행 데이터를 삭제하고 초기화할때 사용되는 구문 
        
        2. DML(Data Manipulation Language) - 데이터를 조작하고 관리하는 작업을 처리하는 SQL문
			 종류
				SELECT 문 : 테이블에 저장된 열의 값을 조회 합니다. 
                INSERT 문 : 새로운 행(데이터)를 테이블에 삽입 합니다. 
                UPDATE 문 : 기존 테이블에 저장된 열의 데이터를 수정합니다.
                DELETE 문 : 기존 테이블에 저장된 행(데이터)를 삭제 합니다. 
				
	    3. DCL(Data Control Language) - 데이터 접근 및 보안 범위를 제어하는 작업을 처리하는 SQL문들
			종류
				GRANT 문 : 새로 만든 사용자아이디에게 데이터베이스나 테이블에 접근권한을 부여할때 사용하는 SQL문
                REVOKE 문 : 사용자아이디에게 데이터에 대한 접근권한을 제거 할때 사용하는 SQL문 
        
        4. TCL(Transaction Control Language) - 데이터베이스 트랜잭션을 관리하고 제어하는 작업을 처리하는 SQL문들
			
            트랜잭션이란? 데이터베이스의 데이터를 일관성을 유지하면서 데이터를 안전하게 관리하기 하나의 작업 단위.
             
			 하나의 은행 업무 작업
             순서1. 계좌 번호를 적는다.
             순서2. 계좌이체 요청을 한다.
             순서3. 본인 계좌 남은 금액을 확인한다.

			종류 
				commit : 데이터베이스에 트랜잭션 변경사항을 확정하는 SQL문 (영구 반영하는 SQL문)
					     예) INSERT문 작성했음. 테이블에 영구 반영 되었을까?  X
							 영구반영하겠다고 commit명령을 실행 해야 합니다.  그럼 영구반영 됩니다. 
				
                rollback : 트랜잭션 변경 사항을 이전 상태로 되돌리는 SQL문 
				savepoint : 트랜잭션 중간 상태를 영구 반영하는 SQL문 
		
*/

-- --------------------------------------------------------------------------
-- 주제 : 데이터 유형 단원
use market_db;

create table hongong4(
  tinyint_col tinyint,  -- 정수 127까지 열에 저장할수 있다 
  smallint_col smallint, -- 정수 32767까지 열에 저장할수 있다
  int_col int, -- 정수 2147483647까지 저장 
  bigint_col bigint   -- 정수 약 900경 까지 저장 
);
-- INSERT 구문을 이용하여 hongong4테이블에 새로운 행(데이터) 추가
insert into hongong4(tinyint_col, smallint_col, int_col, bigint_col)
              values(127, 32767, 2147483647,  90000000000000000);
-- 조회 후 확인
select * from hongong4;

-- 각 숫자에 1을 더해서 입력해 봅시다.
-- 마지막 값에는 0을 하나더 추가하세요
-- INSERT구문을 이용하여 hongong4테이블에 새로운 행(데이터)를 추가
insert into hongong4(tinyint_col, smallint_col, int_col, bigint_col)
values(128, 32768, 2147483648,  900000000000000000);

create table big_table(
	data1 char(256),  -- char 255자 까지 저장 가능 
    data2 varchar(16384) -- varchar 16383자 까지 저장가능 
);

-- netflix_db 데이터베이스 를 사용하기 위해  데이터베이스 생성
-- 데이터베이스 생성 문법
--  CREATE DATABASE 생성할데이터베이스명;
CREATE DATABASE netflix_db;

-- 생성한 netflix_db 데이터베이스 사용을 위해 선택
USE netflix_db;

-- netflix_db데이터베이스 내부에 movie테이블(영화 정보 저장) 생성
create table movie(
	movie_id INT, -- 영화 구분 아이디를 정수로 저장
    movie_title varchar(30), -- 영화 제목 
    movie_director varchar(20),  -- 감독명 
    movie_star varchar(20), -- 별 점수 
    movie_srcipr longtext, -- 영화 자막 텍스트파일의 내용저장 최대 4GB
    movie_film   longblob  -- 영화 동영상 파일  최대 4GB
);

/*

MySQL 8에서 제공하는 날짜형 데이터 타입은 총 5가지로, 각각의 용도와 저장 형식이 다릅니다. 아래는 MySQL 8에서 사용할 수 있는 날짜 및 시간 관련 데이터 타입입니다:

1. DATE
   - 형식: `YYYY-MM-DD`
   - 설명: 날짜만 저장할 수 있으며, 시간 정보는 포함되지 않습니다. 
          예를 들어, `2024-10-02`와 같은 값을 저장할 수 있습니다.

2. TIME
   - 형식: `HH:MM:SS`
   - 설명: 시간만 저장할 수 있으며, 날짜 정보는 포함되지 않습니다. 
          예를 들어, `12:30:45`와 같은 값을 저장할 수 있습니다.

3. DATETIME
   - 형식: `YYYY-MM-DD HH:MM:SS`
   - 설명: 날짜와 시간을 함께 저장할 수 있습니다. 
          예를 들어, `2024-10-02 12:30:45`와 같은 값을 저장할 수 있습니다.

4. TIMESTAMP
   - 형식: `YYYY-MM-DD HH:MM:SS`
   - 설명: `DATETIME`과 비슷하지만, 
           `TIMESTAMP`는 UTC 기준으로 저장된 후, 
            조회할 때 서버의 타임존에 맞게 변환됩니다. 
            서버의 시간대에 의존하는 데이터를 처리할 때 유용합니다.

5. YEAR
   - 형식: `YYYY`
   - 설명: 연도만 저장할 수 있습니다. 
          예를 들어, `2024`와 같은 값을 저장할 수 있습니다.


------------------------------------------------------------------------------------------------------------------------
참고.

    TIMESTAMP 데이터 타입을 쉽게 설명하자면, MySQL에서 시간을 저장할 때 
    'UTC(세계 표준시)'라는 기준 시간을 사용해 저장하고, 
    나중에 데이터를 조회할 때는 서버가 위치한 곳의 시간대에 맞게 변환해주는 타입입니다.

    UTC(세계 표준시): 전 세계가 동일하게 사용하는 기준 시간이 있습니다. 
                      예를 들어, 한국은 이 UTC 시간보다 9시간 빠릅니다. 
                      그래서 만약 UTC 기준으로 2024-10-02 12:00:00가 있으면, 
                      한국 시간으로는 2024-10-02 21:00:00이 됩니다.

    TIMESTAMP 작동 방식:
        저장할 때: 데이터베이스는 시간을 UTC 기준으로 저장합니다. 
                   예를 들어, 한국에서 2024-10-02 21:00:00이라는 시간을 저장하면, 
                   데이터베이스에는 UTC 기준으로 2024-10-02 12:00:00으로 저장됩니다.
        조회할 때: 나중에 이 데이터를 조회하면, 
                   데이터베이스는 저장된 UTC 시간을 다시 한국 시간대로 변환해 2024-10-02 21:00:00으로 보여줍니다.


즉, TIMESTAMP는 언제나 동일한 기준(UTC)으로 시간을 저장하고, 
이를 각 나라나 서버의 시간대에 맞춰 보여주기 때문에, 
여러 나라에서 동시에 사용하는 시스템에서는 아주 유용합니다.

*/

-- ----------------------
/*
 주제 :  사용자 변수를 생성해서 사용할 수 있다.
 
 1. 변수를 생성하는 문법
 SET @변수명 = 변수에 저장할값;
 
 SET @num = 100;
 
 2. 변수에 저장된 값을 조회하는 문법
 SELECT @변수명;
 
 SELECT @num;
*/
USE market_db;

-- 변수를 선언(생성,만듬)하고 정수 저장
SET @myVar1 = 5;

-- 변수를 선언(생성,만듬)하고 실수 저장
SET @myVar2 = 4.25;

-- 변수를 선언하고 문자열과 정수 저장
SET @txt = '가수 이름=>';
SET @height = 166;

-- market_db데이터베이스의 member테이블을 조회합니다
-- 그룹의 평균키(height열에 저장된 데이터들)가  166보다 큰  그룹의 이름 조회 
select @txt as "가수이름", mem_name as "그룹명"
from member
where height > @height;

/*
  SELECT문에 전체 행 중에서 특정 행의 갯수를 제한해서 조회할때 LIMIT을 사용했습니다
  제한 할 행의 갯수도 변수를 선언하여 저장해 놓고 
  변수명을 이용해서 값을 불러와서 사용할수 있다.
*/
/*
SET @count = 3;

select mem_name, height from member
order by height ASC
limit @count;  <---- 문법상 limit 뒤에는 변수명이 올수 없다.
*/

-- 위 SET으로 선언한 @count변수에 저장된 값은 limit구문의 값으로 사용지 못하였다 
-- 그러나 사용할수 있는 해결방법은  PREPARE 와 EXECUTE구문을 사용하면 됩니다.
SET @count = 3;

-- prepare 구문에 mySQL이름에 'SELECT'구문을 미리 준비해 놓고 사용합니다.
-- 여기서 ? 기호는 ?기호 대신 아직 값이 정해져 있지 않아 나중에 결정하겠다는 의미의 기호입니다.
prepare mySQL FROM 'select mem_name, height from member order by height limit ?';

-- execite 구문으로 mysql이름으로 만든 select문장을 실행하기 전에 
-- using구문을 이용해 아직 결정되지 않은 ? 기호에 대응되는 값을  @count변수에 저장된 값으로 설정하고
-- select문장을 select mem_name, height from member order by height limit 3; 완성하고 
-- 실행한 후 조회 함!
execute mySQL using @count;

/*
	데이터 형변환 이란?
    - 정수를 실수로 변환 한다거나, 문자형을 정수로 변환 시키는것을 데이터의 형태를 변환한다고해서
      데이터 형변환이라 한다.
	
    데이터 형변환 하는 방법
    1. 개발자가 직접 제공되는 함수를 이용해 강제로 형변환 (명시적 형변환)
    2. 자동형변환 (암시적 형변환) 
    
    1. 명시적 형변환 
		CAST() 함수 
        문법
			SELECT CAST(형변환할값 AS 변환할데이터유형) "열별칭명"
            FROM 조회할테이블명;

		CONVERT() 함수
        문법 
			SELECT CONVERT(형변환할값, 변환할데이터유형) "열별칭명"
            FROM 조회할테이블명;
*/

-- 실습1. market_db데이터베이스 내부에 만들어져 있는 구매(buy)테이블에서 
--       가수그룹들이 구매한 평균가격 조회해서 가져오자.
-- 		 조회한 평균 가격은 142.9167 즉! 실수값으로 조회되어 나옵니다. 이게 문제 입니다. 
SELECT  AVG(price) as "평균 구매 가격" FROM BUY;

-- 실습2. 실수값을 정수 데이터로 형변환을 하여 조회된 결과를 보여주자 
-- 참고. CAST함수 내부에 작성할 데이터 유형은
--      CHAR, SIGNED, UNSIGNED, DATE, TIME, DATETIME 등입니다.
-- 여기서 SIGNED 는 부호가 있는 정수 ,  UNSIGNED는 부호가 없는 정수 
SELECT   CAST(  AVG(price) AS signed ) AS "평균 구매 가격" FROM BUY;
				
              -- 형변환할 값,  변환할 데이터 유형 
              --  142.9167, 부호가 있는 정수 
SELECT   CONVERT(  AVG(price), signed ) "평균 구매 가격" FROM BUY;

-- 실습3. 날짜 데이터를 YYYY-MM-DD 날짜 형식을 만들기 위해 
--       데이터 유형을 DATE 사용해 데이터 형변환 해 보자 

-- 		CAST(형변환할값  AS 변환할_데이터_유형)

-- '2024$12$12' 문자열을  2024-12-12 DATE유형의 데이터로 형 변환 
SELECT  CAST('2024$12$12' AS DATE); 

-- '2024/12/12' 문자열을  2024-12-12 DATE유형의 데이터로 형 변환 
SELECT  CAST('2024/12/12' AS DATE); 

-- '2024%12%12' 문자열을  2024-12-12 DATE유형의 데이터로 형 변환 
SELECT  CAST('2024%12%12' AS DATE); 

-- '2024@12@12' 문자열을  2024-12-12 DATE유형의 데이터로 형 변환 
SELECT  CAST('2024@12@12' AS DATE); 

-- 실습4. 조회결과를 원하는 날짜형으로 조회할수 있다. 
SELECT * FROM BUY;

SELECT num,
       CONCAT( cast(  price as char ), 'X', cast( amount as char ),'=' )  as '가격X수량=',
       price * amount as '구매액'
FROM BUY;
/*
 2. 자동 형변환(암시적 형변환) 실습 
*/
-- 실습1. '100' + '200'
SELECT '100' + '200';
-- 설명 :  + 연산을 할때 문자열이 정수로 각각 자동형변환 된 후 
--        + 연산자를 통해 계산합니다. 계산결과 300이 조회됨

-- 실습2. 문자열 '100'과 문자열 '200'을 하나로 합친 '100200'하나의 문자열로 
--       만들어서 조회되게 하려면 CONCAT()함수를 사용하자 
SELECT CONCAT('100','200');

-- 실습3. 숫자와 문자열을 CONCAT함수를 호출할떄 전달하면 
--       숫자는 문자열 '100'으로 자동 형변환되고 문자열'200'과 합쳐진 '100200'로 조회됨
SELECT CONCAT(100, '200');

-- 실습4.  + 연산자를 사용해 숫자 + '문자열' 계산을 하면
--      앞 숫자를 기준으로 뒤 '문자열'이 숫자로 자동형변환되어서 200숫자로 자동형변환된 다음에
--      100과 더합니다.  100 + 200  = 300 
SELECT 100  +  '200';   -- 300

SELECT '200' + 100;  -- 300 

-- -------------------------------------------------------
-- 조인 (join) : 두개 이상의 테이블의 열에 저장된 데이터들을 묶어서 
--              하나의 표형태의 결과로 조회 하는 구문

-- 조인 종류
-- 1. 내부조인 (Inner JOIN)
-- 2. 외부조인 (OUTER JOIN)
-- 3. 상호조인 
-- 4. 자체조인 
/*
	1. 내부조인
		두 테이블 양쪽의 열에 저장된 데이터가 있을때 사용되는 조인.
        
        문법
			SELECT 첫번째테이블명.첫번쨰테이블의열명,  두번째테이블명.두번째테이블의열명
            FROM 첫번째테이블명 Inner JOIN 두번쨰테이블명
            ON 첫번쨰테이블열명 = 두번째테이블열명 
            WHERE 검색조건식;
*/
/*
	실습1. 구매(buy)테이블과 , 회원(member)테이블을 사용하자.
		  구매 테이블에는 구매한 상품 정보만 저장되어 있고,
          회원 테이블에는 가입한그룹의 회원정보만 저장되어 있습니다.
          회원에게 구매한 상품정보를 배송하려면 회원의 주소, 연락처가 있는 member테이블과 
          구매 상품정보 (buy테이블)를 결합해서 조회해서 가져 와야 합니다.

구매(buy)테이블에서 GRL이라는 그룹아이디를 가진 회원그룹이 구매한 물건을 배송하기 위해 
inner join을 통해  회원그룹이름/회원그룹주소/연락처/구매한상품명 등을 조회할수 있습니다.
*/
SELECT member.mem_name,  member.addr, member.phone1, member.phone2,   
       buy.prod_name
FROM buy Inner JOIN member
ON buy.mem_id = member.mem_id 
WHERE buy.mem_id = 'GRL';

/*
  buy테이블과 member테이블을 inner join 하여 
  전체 그룹회원의 아이디, 이름, 구매한 제품명, 주소정보 조회 하는데
  전체 그룹회원 아이디를 기준으로 오름차순 정렬 해서 조회 !!!!!!!!!
*/
SELECT M.mem_id, M.mem_name, B.prod_name, M.addr
FROM buy B inner join member M
ON B.mem_id = M.mem_id
ORDER BY M.mem_id ASC;

/*
	우리 사이트에서 한번이라도 구매한 기록이 있는 회원그룹들에게 감사의 안내문을 발송해야 한다면
    회원 그룹 아이디, 회원 그룹이름, 주소 를 중복없이
    buy테이블과 member테이블의 inner join을 하여 열을 조회
*/
SELECT distinct m.mem_id, m.mem_name, m.addr 
FROM buy b inner join member m
ON b.mem_id = m.mem_id
ORDER BY m.mem_id DESC;


/*
 외부 조인 (OUTER JOIN)
 - 두 테이블 중에 한쪽 테이블의 열에만 데이터가 저장되어 있어도 
   두 테이블의 열을 모두 조회 하기 위한 조인

 외부 조인 종류
 - 1. LEFT OUTER JOIN
 - 2. RIGHT OUTER JOIN
 - 3. FULL OUTER JOIN

	1. LEFT OUTER JOIN
       기준이 되는 테이블의 열 데이터를 모두 조회 되어야 한다는 의미 
*/
-- 실습 1. 전체 그룹 회원 중에서 구매기록이 없는 그룹회원의 정보도 함께 모두 조회 하기 위해
--        LEFT OUTER JOIN 사용
SELECT M.mem_id, M.mem_name, M.addr, B.prod_name
FROM member M  LEFT OUTER JOIN buy B
ON M.mem_id = B.mem_id
ORDER BY M.mem_id ASC;
/*
2.  RIGHT OUTER JOIN
    - 오른쪽에 작성한 테이블을 기준으로 열에 모든데이터가 저장되어 있으면
	  왼쪽 테이블에 열의 데이터가 저장되어 있지 않아도 모든 열값을 조회 
*/
-- 실습2. 전체  그룹 회원 중에서 구매기록이 없는 그룹회원의 열 정보도 함께 모두 조회 하기위해 
--       RIGHT OUTER JOIN 사용

-- member테이블의 mem_id열데이터(그룹회원아이디들)가 
-- buy테이블의 mem_id열데이터(구매한기록이있는 그룹회원아이디들)가 일치하는 행이 있으면 
-- member테이블을 기준으로 모든 열(mem_id, mem_name, addr)의 값이 조회되고
-- buy테이블을의 열의 값이 존재하지 않아도 빈공 백으로 조회
SELECT M.mem_id, M.mem_name, M.addr, B.prod_name
FROM buy B RIGHT OUTER JOIN member M
ON M.mem_id = B.mem_id
ORDER BY M.mem_id ASC;

-- 실습3. 전체 회원 그룹중에서 한번도 구매한 기록이 없는 그룹회원 목록을 조회 하기 위해
--       LEFT OUTER JOIN사용
--       단! 열의 데이터가 중복되면 하나로 합쳐서 하나의 데이터로 조회!
SELECT DISTINCT member.mem_id, buy.prod_name, member.mem_name, member.addr
FROM member LEFT OUTER JOIN buy
ON member.mem_id = buy.mem_id
WHERE buy.prod_name IS NULL
ORDER BY member.mem_id asc;

-- ------------------------------

-- 주제 : LEFT OUTER JOIN,   RIGHT OUTER JOIN 예 

-- A테이블 생성
create table A (
	id int primary key,  -- 회원 id
    name varchar(50)  -- 회원 이름 
);

-- A테이블에  데이터 삽입
INSERT INTO A(id, name) VALUES(1,'Alice'),
							  (2,'Bob'),
                              (3, 'Charlie');

SELECT * FROM A;

-- B테이블 생성
create table B (
   id int primary key,    -- 주문 id
   order_item varchar(50) -- 주문한 상품이름 
);

-- B테이블에 데이터 삽입
INSERT INTO B (id, order_item) VALUES (2, 'Laptop'),
									  (3, 'Smartphone'),
									  (4, 'Tablet');

select * from B;

-- LEFT OUTER  JOIN
select A.id, A.name, B.order_item
from A LEFT OUTER JOIN B
on A.id = B.id;
-- id 1은 A테이블에만 있으므로 order_item 값이 NULL로 조회되어 표시 됩니다
-- id 2와 id 3은 양쪽테이블에 모두 있으므로 name과 order_item이 모두 조회되어 표시됩니다.
/*
id  name     order_item
1	Alice		NULL
2	Bob			Laptop
3	Charlie		Smartphone
*/

-- RIGHT OUTER JOIN
SELECT A.id, A.name, B.order_item
FROM A RIGHT OUTER JOIN B
ON A.id = B.id;
-- 설명 : 
-- id 2와 id3은 양쪽 테이블에 모두 있으므로 name과 order_item열의값이
-- 모두 조회됩니다.
-- 하지만 id 4는  B테이블에만 있으므로 A테이블의 name열값은 NULL값으로 조회되고
-- B테이블의 order_item열값은 Tablet으로 조회됩니다.    
/*
id  name 	order_item
2	Bob		Laptop
3	Charlie	Smartphone
NULL  NULL	Tablet
*/

/*
A테이블 
----------------------
id   name  열명 
1	Alice
2	Bob
3	Charlie

B테이블
----------------------------
id  order_item  열명
2	Laptop
3	Smartphone
4	Tablet


 LEFT OUTER JOIN 
 - 왼쪽에 작성한 테이블의 모든 행을 기준으로 데이터를 조회하며,
   오른쪽에 작성한 테이블에 대응되는 데이터가 없으면 그열은 NULL로 채워져 조회하는 구문 

 RIGHT OUTER JOIN
 - 오른쪽에 작성한 테이블의 모든 행을 조회 결과로 포함하며,
   왼쪽에 작성한 테이블의 열에 일치하는 데이터가 없더라고 오른쪽 테이블의 열 데이터를 모두 조회함
   만약 왼쪽 테이블에 일치하는 행에 관한 열의 데이터가 없다면 
   해당 열값은 NULL로 표시되어 조회하는 구문
*/
-- ---------------------------------------------------------------------------------------------------
/*
 상호 조인(CROSS JOIN)? 
          한쪽 테이블의 모든 행과 다른쪽 테이블의 모든 행을 랜덤으로 조인해서 조회 하는 구문
          그래서 상호 조인 조회 결과의 전체 행수 개수는 
		  두 테이블의 각행의 개수를 곱한 개수가 됩니다. 
		  대용량 데이터를 조회해서 만들어 테이블을 새로 생성할떄 사용됨 
  상호 조인 특징
  - ON 구문을 사용할수 없습니다. 
  - 조회 결과 행의 열 데이터들에 의미가 없습니다. 랜덤으로 조인하기 때문입니다.
  - 상호 조인의 주용도는 대용량 데이터를 테스트하기 위해  데이터를 생성할때 조회하게 됩니다
          
*/
-- buy테이블과 member테이블 상호 조인
SELECT * 
FROM buy CROSS JOIN member;

SELECT count(*)
FROM buy CROSS JOIN member;  -- 120 개 행이 렌덤으로 조회 됨 

-- 샘플 데이터 베이스인 sakila데이터베이스의 inventory테이블에 
-- 저장된 행의 갯수 조회
SELECT count(*) FROM sakila.inventory; -- 4581행 데이터

-- 샘플 데이터 베이스인 world데이터베이스의 city테이블에 
-- 저장된 행의 갯수 조회
SELECT count(*) FROM world.city; -- 4079행 데이터 

-- 위 두 inventory테이블의 행 데이터와 
--      city테이블의 행 데이터를 상호 조인 한 전체 조회 행갯수 출력
-- 4581 X 4079 =  18685899 조회된 행의 갯수 
SELECT count(*) AS "행(데이터)의 갯수"
FROM sakila.inventory  cross join world.city; 
-- ------------------------------------
/*
  대용량 데이터가 저장되는 테이블을 만들고 싶다면
  방법 : CREATE TABLE ~ SELECT문을 사용
*/
CREATE TABLE cross_table
	SELECT * FROM sakila.actor -- 200건
				  CROSS JOIN
                  world.country; -- 239건
                  
-- cross_table테이블에 저장된 총 행(레코드) 갯수 조회
SELECT count(*) FROM cross_table; -- 47800 행(레코드) 조회 
-- --------------------------------------------------
/*
   자체 조인 
	- 자신의 테이블의 두개의 열 데이터를 이용해 조회하기 위한 조인 구문.
      자체 조인은 하나의 테이블에 서로다른 별칭을 붙여서 조인하는 구문.
    
   자체 조인 문법
		SELECT 조회할열명 
        FROM 테이블명1 별칭A INNER JOIN 테이블명1 별칭B
        ON 조인이 될 조건식
        WHERE 검색 조건식;
*/
-- 실습1. market_db데이터베이스 내부에 
--       emp_table 라는 이름의 테이블 생성후 각 데이터 저장
CREATE TABLE emp_table(
	emp char(4), -- 회사 직급 정보 저장 ('대표', '영업이사', '인사부장' 등등)
	manager char(4), -- 직속상관의 직급 정보 저장 ( '대표' ,'영업이사', '정보이사' 등등)
    phone varchar(8) -- 사내 연락처 저장 ('1111-1', '2222-1' 등)
);

insert into emp_table values('대표', NULL, '0000');

insert into emp_table values('영업이사', '대표', '1111');
insert into emp_table values('관리이사', '대표', '2222');
insert into emp_table values('정보이사', '대표', '3333');

insert into emp_table values('영업과장', '영업이사', '1111-1');
insert into emp_table values('경리부장', '관리이사', '2222-1');
insert into emp_table values('인사부장', '관리이사', '2222-2');
insert into emp_table values('개발팀장', '정보이사', '3333-1');

insert into emp_table values('개발주임', '정보이사', '3333-1-1');

select * from emp_table;

-- 실습2. 경리부장 직속상관(관리이사)의 연락처 정보와 함께 조회하기 위해 자체조인 사용!
SELECT A.emp as "직원직급",
	   B.emp as "직속상관직급정보",
       B.phone as "직속상관연락처"
FROM emp_table as A INNER JOIN emp_table as B
ON A.manager = B.emp
WHERE A.emp = '경리부장';
/*
조회 결과 

열명 	직원직급  직속상관직급정보      직속상관연락처 
		경리부장	관리이사				2222
*/

-- -----------------------------------------------------------
/*
	스토어드 프로시저란?
		데이터베이스 서버에 저장되어 있는 일련SQL문장들로 구성된 프로그램(개체)
        
	스토어드 프로시저 생성 문법
		-- 여러 줄의 SQL구문을 작성 해 놓을 스토어드 프로시저를 생성하기위해
        -- MySQL의 기본 구분자(문장의 끝을 나타내는기호)를 변경을 위해 $$로 설정
        DELIMITER $$

			CREATE procedure 스토어드-프로시저_이름()
				begin
					-- 이영역에 SQL 프로그래밍을 코딩합니다. 
                end $$

		-- 기본 구분자 기호를 ;세미콜론 기호로 변경하기위한 설정 구문 
		DELIMITER ;
        
        
    -- 생성한 스토어드 프로시저 호출하여 실행 하는 문법!
    CALL 스토어드_프로시저_이름();
        
	-- --------------------------------------------------------
    
    IF문  : 조건식의 결과가 참이면 SQL문을 실행하고, 거짓이면 IF문을 빠져 나가는 구문
    
    IF문 문법
		IF 조건식 THEN
			조건식이 참일때 실행할 코드
		END IF;
*/
-- 실습1. 만약 ifProc1이라는 이름의 스토어드 프로시저 개체가 이미 만들어져 있으면
--       삭제 시키자
DROP procedure if exists ifProc1;

DELIMITER $$

CREATE procedure ifProc1()
begin
	IF 100=100 THEN
		select '100은 100과 같습니다' as "조회결과";
	END IF;
end $$

DELIMITER ;

-- 바로 위에서 만든 ifProc1이라는 이름의 스토어드 프로시저 개체를
-- 호출하여 실행 시킵니다
CALL ifProc1();

/*
	if ~ else문
		- if 조건식이 참이면 SQL문장1을 수행하고 거짓이면 SQL문장2를 수행 하는 구문
        - 문법
			if 조건식 then
				SQL문장1;
            else
				SQL문장2;
            end if;
*/

--   만약 ifProc2이라는 이름의 스토어드 프로시저 개체가 이미 만들어져 있으면
--   삭제 시키자
DROP procedure if exists ifProc2;

DELIMITER $$

CREATE procedure ifProc2()
begin
	declare myNum int; -- declare예약어를 사용해 myNum변수를 선언 
    set myNum = 200; -- set예약어를 사용해 myNum변수에 200을 대입해서 저장.
    
    -- 만약에 myNum변수메모리에 저장된 값이 100과 같으면?(조건식)
    if myNum = 100 then
		select '100입니다.';
    -- myNum변수에 저장된 값이 100이 아니라면?(if 조건식의 결과가 거짓이면?)    
    else
		select '100이 아닙니다.';
    end if;
    
end $$

DELIMITER ;

-- 스토어드 프로시저 호출
CALL ifProc2();


/*
market_db 데이터베이스 내부에 만들어져 있는 
member테이블의 정보를 활용해서 스토어드 프로시저 생성후 사용

실습. 그룹아이디가 APN(에이핑크)인 회원그룹의 데뷔일자가 5년이 넘었는지 확인해보고 
      5년이 넘었으면 축하 메세지를 만들어서 출력 해 보자.
*/
-- 이미 ifProc3 스토어드 프로시저가 생성되어 있으면? 삭제
DROP procedure if exists ifProc3;

DELIMITER $$
CREATE procedure ifProc3()
begin
	declare debutDate DATE; -- 데뷔일을 저장할 변수 선언   2011-02-10
    declare curDate DATE;  -- 오늘 현재 날짜 정보를 저장할 변수 선언 
    declare days INT; -- 데뷔일로부터 오늘 현재날짜까지 활동한 일수를 저장할 변수 선언
    
    -- 에이핑크 그룹의 데뷔일을 조회해서 debutDate변수에 저장하기 위해 into절 작성
    -- 문법     조회할열명 into 저장할변수명 
    SELECT debut_date into debutDate
    FROM market_db.member
    WHERE mem_id = 'APN';
    
    -- 참고. 오늘 컴퓨터의 시스템날짜 정보를 반환하는 함수 -> current_date()함수
    SET curDate = current_date(); -- 2024-10-02
								  -- 현재 날짜 정보를 반환
    -- 참고. datediff(날짜2,날짜1)함수 
    --       : 날짜2부터 날짜1까지 일수를 계산해서 반환하는 함수 
    
    -- 데뷔날짜로 부터 현재 날짜까지 활동한 일수를 일단위로 구해서 변수에 저장
    set days = datediff(curDate, debutDate);
    
    -- 데뷔 년도가 5년이 지났다면?
    if (days/365) >= 5 then
	   -- '데뷔한지 몇일이 지났습니다 핑순이들 축하합니다!!' 검색결과 보여주자 
       select concat('데뷔한지', days, '일이 지났습니다. 핑순이들 축하합니다!');
    else -- 데뷔 년도가 5년이 지나지 않았다면?
       select concat('데뷔한지',  days, '일 밖에 안되었네요. 핑순이들 화이팅!');		
	end if;	
end $$

DELIMITER ;


-- 스토어드 스프로 시저 호출
call ifProc3();

-- 참고.  현재 오늘날짜 정보와  시간정보를 함꼐 얻고 싶을떄 
--       -> current_timestamp() 함수 호출!

/*
	CASE문
		- 2가지 이상의 조건식중 선택해야 하는 경우 사용되는 선택문.
        
        - 문법 
				CASE
					WHEN 조건식1 THEN
						SQL문장들1;
                    WHEN 조건식2 THEN
						SQL문장들2;
					WHEN 조건식3 THEN
						SQL문장들3;
					ELSE
						조건식1,조건식2,조건식3이 모두 거짓일때 실행할 SQL문장들;
				END CASE;
*/
-- 실습1. 시험점수와 학점을 생각해 봅시다.
-- 90점이상은 A학점, 
-- 80점이상은 B학점, 
-- 70점이상은 C학점, 
-- 60점이상은 D학점, 
-- 60점 미만은 F학점 

-- 이미 caseProc라는 이름의 스토어드 프로시저가 만들어져 있으면 삭제
drop procedure if exists caseProc;

DELIMITER $$
CREATE procedure caseProc()
begin
	declare point int; -- 시험 점수 저장할 변수 선언
    declare credit char(1); -- A 또는 B또는 C 등의 학점을 저장할 변수 선언
    set point = 88; -- 시험 점수 88을 point변수에 저장
    
    case
		when point >= 90 then -- 시험점수가 90점 이상이면?
            set credit = 'A'; -- A 학점 저장
		when point >= 80 then -- 시험점수가 80점 이상이면?
            set credit = 'B'; -- B 학점 저장 	
		when point >= 70 then -- 시험점수가 70점 이상이면?
            set credit = 'C'; -- C 학점 저장 	 
		when point >= 60 then -- 시험점수가 60점 이상이면?
            set credit = 'D'; -- D 학점 저장             
		else -- 60점 미만이면?
			set credit = 'F'; -- F 학점 저장 
    end case;
    
    select concat('취득점수->', point) as '취득점수', 
		   concat('학점->', credit) as '학점';
    
end $$

DELIMITER ;


-- 만들어진 스토어드 프로시저 호출
call caseProc();

-- ---------------------------------------------------
/*
   실습2. market_db에서 buy테이블에는 회원그룹이 구매한 상품정보가 저장되어 있습니다
	     회원그룹 아이디별로 총 구매액을 계산해서 
         회원등급을 4단계( 최우수고객, 우수고객, 일반고객, 유령고객)로
         나누어서 조회 합니다.
*/
-- 순서1. 회원 그룹 아이디 별로  총 구매액을 조회
SELECT mem_id, SUM(price * amount) "총구매액"
FROM buy
GROUP BY mem_id; 
-- 순서2. 총 구매액이 큰순으로 정렬(내림 차순 DESC정렬)해서 조회
SELECT mem_id, SUM(price * amount) "총구매액"
FROM buy
GROUP BY mem_id
ORDER BY SUM(price * amount) DESC;
-- 순서3. 총 구매액과 함께 회원그룹의 정보(회원 이름)도 같이 조회
SELECT B.mem_id, M.mem_name, SUM(price * amount) "총구매액"
FROM buy B  INNER JOIN member M
ON B.mem_id = M.mem_id
GROUP BY B.mem_id
ORDER BY SUM(price * amount) DESC;

-- 순서4. 주의 할점은 구매테이블에는 4개의 그룹회원만 구매 있으므로,
--       나머지 6그룹에 대한 아이디 정보가 없으므로 
--       구매 하지 않은 회원 그룹의 정보도 같이 조회 
SELECT M.mem_id, M.mem_name, SUM(price * amount) "총구매액"
FROM buy B  RIGHT OUTER JOIN member M
ON B.mem_id = M.mem_id
GROUP BY M.mem_id
ORDER BY SUM(price * amount) DESC;

-- 순서5. 회원 등급 별로 나누자
SELECT M.mem_id, M.mem_name, SUM(price * amount) "총구매액",
	   case
			when ( SUM(price * amount) >= 1500 )  then '취우수고객'
			when ( SUM(price * amount) >= 1000 )  then '우수고객'
			when ( SUM(price * amount) >= 1 )  then '일반고객'            
            else '유령고객'  
       end '회원등급'

FROM buy B  RIGHT OUTER JOIN member M
ON B.mem_id = M.mem_id
GROUP BY M.mem_id
ORDER BY SUM(price * amount) DESC;

-- ---------------------------------------------------------------
-- 이미 만들어져 있는 totalLevel이라는 이름의 스토어드 프로시저가 있으면 삭제
DROP procedure if exists totalLevel;

DELIMITER $$
CREATE procedure totalLevel()
begin
	SELECT M.mem_id, M.mem_name, SUM(price * amount) "총구매액",
	   case
			when ( SUM(price * amount) >= 1500 )  then '취우수고객'
			when ( SUM(price * amount) >= 1000 )  then '우수고객'
			when ( SUM(price * amount) >= 1 )  then '일반고객'            
            else '유령고객'  
       end '회원등급'

	FROM buy B  RIGHT OUTER JOIN member M
	ON B.mem_id = M.mem_id
	GROUP BY M.mem_id
	ORDER BY SUM(price * amount) DESC;
end $$

DELIMITER ;

-- 회원 그룹 아이디 별로 총 구매한 구매액에 따라
-- 회원등급을 보여주는 프로시저 호출
call totalLevel();

/*
	WHILE 반복문
		조건식 이 참일 경우 반복실행할 SQL문을 실행할 구문 
        
    WHILE 반복문 문법
		
        WHILE (조건식) DO
			SQL문장들
		END WHILE;
*/

-- 실습1.  1에서 100까지의 값을 모두 더하는 간단한 기능의 WHILE구문 구현

-- 이미 만들어져 있는 whileProc라는 스토어드프로시저가 만들어져 있으면 삭제 
drop procedure if exists whileProc;

DELIMITER $$
CREATE procedure whileProc()
begin
	declare i INT; -- 1에서 100까지 1씩 증가할 값이 저장 될 변수
    declare hap INT; -- 1부터 100까지 누적한 값을 저장할 변수     
    set i = 1;  -- i변수 초기값 1로 저장
    set hap = 0; -- 초깃값 0 저장
    
    while (i<=100) do
		set hap = hap + i; -- hap변수에 i변수값 누적 
        set i = i + 1;    -- i변수에 저장된 값 1증가 해서 저장 
    end while;
    
    select '1부터 100까지의 합==>' , hap;
    
end $$

DELIMITER ;

-- 바로 위에서 만든 whilePorc스토어드 프로시저 호출!
call whileProc();

-- 실습2. 1에서 100까지 합계에서 4의 배수를 제외한 숫자들의 합을 계산하고,
--       숫자들의 합이 1000이 넘으면 더이상 합치지 않고 while반복문을 벗어나라!

-- 이미 만들어져 있는 whileProc2라는 스토어드프로시저가 만들어져 있으면 삭제 
drop procedure if exists whileProc2;

DELIMITER //
CREATE procedure whileProc2()
begin
	declare i INT; -- 1에서 100까지 1씩 증가할 값이 저장될 변수
    declare hap INT; -- 1부터 1000까지 누적한 값 저장할 변수 
    set i = 1;
    set hap = 0;
    
    -- myWhile이라는 이름으로 레블 지정
    myWhile:
	while (i <= 100) do
        -- 현재 i변수에 저장되어 있는 값이 4의 배수와 같다면?
        if (i % 4 = 0) then
           -- i변수에 저장되는 값을 1증가 해서 다시 저장
           set i = i + 1;
           -- 지정한 myWhile 레이브의 위치로 올라가서 
		   -- 다시  while반복문의 조건식을 검사해~ 라고 말해야 합니다. 
           iterate myWhile;
        end if;
        
        -- 1부터 100까지의 합을 hap변수에 누적
        set hap = hap + i;
        
        -- hap변수에 누적된 값이 1000을 초과 하면 while반복문을 빠져나가라 
        -- leave 예약어 사용
        if (hap > 1000) then
			leave myWhile; -- myWhile이라는 레이블 바깥 위치로 빠져나감  
        end if;
        
        set i =  i + 1;  -- i변수값 1증가 
	
    end while;
    
	select '1~100까지의 숫자 중에서 4의 배수를 제외한 숫자들의 누적합계를 
            그리고 합계가 1000을 넘으면 while종료',
		    hap as "합계"; 
    
end //

DELIMITER ;

CALL whileProc2();

-- -----------------------------------------------------------------------------------

-- 동적 SQL문 이란?
--  SQL문이 고정된 것이 아니라 상황에따라 where 조건열의 값을 변경해서 
--  필요할때마다 추가해서 사용되는 SQL구문 

-- 참고.  prepare 예약어를 사용한 구문은 SQL문을 미리 준비 해 놓는 구문.
-- 참고.  execute 예약어를 사용한 구문은 prepare 예약어를 사용해 준비 해 놓은 SQL문을 실행하는 구문.

-- market_db 데이터베이스의 테이블 이용 


-- 실습1.member테이블에 저장된 "BLK"라는 그룹 아이디를 가진 그룹 회원의 정보를 조회 하기 위해
--      미리 prepare 예약어를 사용한 구문에 준비 해 두고 
--      execute 예약어를 사용해 준비 해 놓은 SQL문을 실행해서 조회 하자.
use market_db;

-- 미리 준비 해 놓은 SQL문을 가져와서 실행할 이름을  prepare예약어로 설정
prepare myQuery FROM 'SELECT * FROM MEMBER WHERE mem_id = "BLK" ';

-- prepare 예약어로 미리 준비 해서 만들어 놓은 myQuery라는 이름으로 지정한 SQL문 실행!
execute myQuery;

--  실습2. 출입한 날짜와 시간 정보를 저장해 두는 테이블을 생성하고
--        출입한 날짜와 시간 정보를 동적으로 바뀌는 데이터이므로 INSERT문장을 만들어 놓고 실행!

-- 출입한 날짜와 시간정보를 저장 해두는 gate_table이라는 이름의 테이블 생성
CREATE TABLE gate_table(

	id int auto_increment primary key, -- 출입한 회원 아이디가 저장될 열 
    
    -- 출입한 날짜와 시간 정보가 함께 저장될 열 
    entry_time DATETIME   -- 'YYYY-MM-DD hh:mm:ss'
						  -- '2024-10-04 14:43:59'
);
-- 현재 출입한 현재 날짜와 시간 정보를 얻어서 변수에 저장
set @curDate = current_timestamp();

select @curDate;

-- prepare구문을 이용해  출입한 현재 날짜와 시간정보를 
-- 동적으로 gate_talbel테이블에 추가하기위해
-- ? 기호를 설정해 향후 입력될값을 비워 둡니다.
prepare mySQL FROM 'insert into gate_table (id, entry_time) values(null, ?)';
				   			
-- execute구문 내부에 using 예약어를 사용해 @curDate변수에 저장된 현재 날짜와 시간정보를  
-- ?기호 대신 투입해서  insert문을 실행하게 됩니다.
-- execute예약어 구문을 실행하는 시점이 
-- insert문장이 실행해서 그시점의 날짜와 시간정보가 테이블에 저장되게 된다.
execute mySQL using @curDate;


select * from gate_table;

-- ---------------------------------------------------------

/*
 주제 : 데이터베이스 설계 

  데이터베이스 설계란? 
	데이터베이스를 효과적으로 구성하고 관리 하기 위한 과정입니다.
    
  데이터베이스 설계 단계
    1단계 : 고객의 요구 사항 분석
    - 목표. 데이터베이스의 목적과 사용자 요구사항을 파악합니다
    - 활동. 
		   사용자와의 인터뷰, 설문 조사등을 통해 데이터베이스에서 필요한 기능과 데이터를 식별합니다
			예)  고객 정보, 제품 정보, 주문 관리 등.
	
    2단계 : 개념적 설계
    - ER 다이어그램 그리기(데이터베이스의 주요 객체(엔티티)와 이들 간의 관계를 시각적으로 표현합니다
    - 주요 객체(구성) 요소
      엔티티 : 데이터베이스에서 관리할 객체 (예: 고객, 제품, 주문)
      속성 :  각 엔티티의 특성  (예: 고객의 이름, 제품의 가격 )
      관계  :  엔티티 간의 관계 설정 ( 예: 고객 엔티티와 주문 엔티티는 1:N 관계)
    
    3단계 : 논리적 설계
     - 테이블 구조 정의 단계 : 개념적 설계를 바탕으로 테이블을 정의 합니다
     - 테이블 예시 
		 Customers 테이블 : 고객 정보를 저장 
         Products 테이블 : 제품 정보를 저장
         Orders 테이블 : 주문 정보를 저장
         OrderItems 테이블 : 주문한 제품 정보를 저장 
         
     - 외래 키 설정  : 테이블 간의 관계를 정의하여 데이터의 무결성을 유지하게 설정 
    
    4단계.  물리적 설계 
    - DBMS선택 : 사용항 데이터베이스 관리 시스템의 종류를 결정(MySQL 또는 PostgreSQL)
    - 테이블 생성 : 실제 SQL쿼리를 사용하여 테이블을 생성합니다.
    - 인덱스 개체 및 열에 특정 제약 조건 설정 
      : 성능을 향상시키기 위해 인덱스 개체를 추가하고,
        데이터 무결성을 유지 하기 위한 제약조건을 열에 설정합니다
        
    5단계.  데이터베이스 구현
    - 실제 데이터 입력 : 더미 데이터나 초기 데이터를 입력하여 데이터베이스를 구성합니다
    - 테스트 및 검증  : 데이터베이스가 요구사항을 충족하는지 확인 하기 위해 쿼리를 실행하고 결과를 검증합니다
   
   
  4단계에서 작성할 테이블이란?
    - 하나의 데이터베이스 내부에 생성하는 하나의 개체 
    - 표형 태로 구성된 2차원 구조로, 행과 열로 구성된 데이터를 열의 셀단위로 저장할 개체.
 
  테이블 생성 기본 문법
	
    CREATE TABLE 생성할_테이블명(
		
		열1 데이터_형식 [제약 조건],
        열2 데이터_형식 [제약 조건],
        .......
        
        [테이블 제약 조건]
    );
 -----------------------------
   
   테이블 주요 구성 요소
	1. 데이터_형식
		MYSQL에서 사용할수 있는 데이터 형식 종류
         숫자형 : INT, FLOAT, DOUBLE, DECIMAL 등 
         문자열형 : VARCHAR, CHAR, TEXT, BLOB 등
         날짜/시간형 : DATE, TIME, DATETIME, TIMESTAMP 등
         논리형  : BOOLEAN
    
    2. 열 제약조건 
		열에 추가하여 데이터의 유효성을 보장하는 제약조건들입니다
        종류
			PRIMARY KEY : 테이블 내에서 고유하고 NULL이 될수 없는 기본키 설정
            FORENIGN KEY : 다른 테이블의 기본키 열의 데이터를 참조하여 테이블간의 관계 설정
            UNIQUE : 열의 모든 값이 고유해야 함 
            NOT NULL : 열에 NULL값을 허용하지 않음
            DEFAULT : 데이터가 INSERT하지 않을 경우 INSERT될 기본값 설정하는 제약조건 
            CHECK : 열의 값이 특정 조건을 만족해야 열에 값을 저장할 제약조건 (MYSQL 8이상지원) 
            AUTO_INCREMENT:  자동으로 값이 증가(주로 INT형 열에 사용)
    
    3. 테이블 제약 조건
		테이블 수준에서 정의할수 있는 제약 조건
         
         PRIMARY KEY : 하나이상의  열을 기본키로 설정 
         FOREIGN KEY : 다른 테이블과의 관계를 설정
         UNIQUE (열목록) : 여러 열의 조합이 고유해야함 
         CHECK : 특정 조건을 만족해야함 
*/
-- 실습 순서1. 인터넷 마켓(InternetMarket) 데이터베이스 만들기
CREATE DATABASE InternetMarket;
USE InternetMarket;

-- 실습 순서2. 테이블 생성

-- 실습 순서2-1.  Customers(고객) 테이블 생성
CREATE TABLE Customers (
    -- 고객 ID를 저장하는 열
    -- INT 타입으로 설정하고 자동 증가(AUTO_INCREMENT) 기능을 사용하여
    -- 새로운 고객이 추가될 때마다 이전 고객 ID보다 1씩 증가합니다.
    -- 기본 키(PRIMARY KEY)로 설정하여 고객 ID가 유일하게 유지되도록 합니다.
    customer_id INT AUTO_INCREMENT PRIMARY KEY,

    -- 고객의 이름을 저장하는 열
    -- VARCHAR 타입으로 최대 100자까지 저장할 수 있으며,
    -- NOT NULL 제약조건을 통해 이 필드는 필수로 값을 입력해야 합니다.
    name VARCHAR(100) NOT NULL,

    -- 고객의 이메일 주소를 저장하는 열
    -- VARCHAR 타입으로 최대 100자까지 저장할 수 있으며,
    -- UNIQUE 제약조건을 통해 모든 고객의 이메일 주소가 유일해야 함을 보장합니다.
    -- NOT NULL 제약조건을 통해 이 필드는 필수로 값을 입력해야 합니다.
    email VARCHAR(100) UNIQUE NOT NULL,

    -- 고객의 전화번호를 저장하는 열
    -- VARCHAR 타입으로 최대 20자까지 저장할 수 있으며,
    -- 이 필드는 필수는 아니므로 NULL 값을 허용합니다.
    phone VARCHAR(20),

    -- 고객의 주소를 저장하는 열
    -- VARCHAR 타입으로 최대 255자까지 저장할 수 있으며,
    -- 이 필드는 필수는 아니므로 NULL 값을 허용합니다.
    address VARCHAR(255),

    -- 고객의 등록 날짜와 시간을 저장하는 열
    -- TIMESTAMP 타입으로 설정하고, DEFAULT CURRENT_TIMESTAMP를 통해
    -- 고객이 등록된 시간에 자동으로 현재 시간이 저장됩니다.
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- 실습 순서2-2.  Products(제품) 정보가 저장되는 테이블 생성
CREATE TABLE Products (
    -- 제품 ID를 저장하는 열
    -- INT 타입으로 설정하고 자동 증가(AUTO_INCREMENT) 기능을 사용하여
    -- 새로운 제품이 추가될 때마다 이전 제품 ID보다 1씩 증가합니다.
    -- 기본 키(PRIMARY KEY)로 설정하여 제품 ID가 유일하게 유지되도록 합니다.
    product_id INT AUTO_INCREMENT PRIMARY KEY,

    -- 제품의 이름을 저장하는 열
    -- VARCHAR 타입으로 최대 100자까지 저장할 수 있으며,
    -- NOT NULL 제약조건을 통해 이 필드는 필수로 값을 입력해야 합니다.
    name VARCHAR(100) NOT NULL,

    -- 제품에 대한 설명을 저장하는 열
    -- TEXT 타입으로 설정하여 긴 텍스트를 저장할 수 있습니다.
    -- 이 필드는 필수는 아니므로 NULL 값을 허용합니다.
    description TEXT,

    -- 제품의 가격을 저장하는 열
    -- DECIMAL(10, 2) 타입으로 설정하여 최대 10자리 숫자 중 소수점 아래 2자리까지
    -- 저장할 수 있습니다. 이 필드는 필수로 값을 입력해야 하므로
    -- NOT NULL 제약조건을 적용합니다.
    price DECIMAL(10, 2) NOT NULL,

    -- 제품의 재고 수량을 저장하는 열
    -- INT 타입으로 설정하며, 이 필드는 필수로 값을 입력해야 하므로
    -- NOT NULL 제약조건을 적용합니다.
    stock INT NOT NULL,

    -- 제품의 등록 날짜와 시간을 저장하는 열
    -- TIMESTAMP 타입으로 설정하고, DEFAULT CURRENT_TIMESTAMP를 통해
    -- 제품이 등록된 시간에 자동으로 현재 시간이 저장됩니다.
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 실습 순서2-3.  고객이 주문한 주문정보 가 저장되는 주문(Orders)테이블 생성
CREATE TABLE Orders (
    -- 주문 ID를 저장하는 열
    -- INT 타입으로 설정하고 자동 증가(AUTO_INCREMENT) 기능을 사용하여
    -- 새로운 주문이 추가될 때마다 이전 주문 ID보다 1씩 증가합니다.
    -- 기본 키(PRIMARY KEY)로 설정하여 주문 ID가 유일하게 유지되도록 합니다.
    order_id INT AUTO_INCREMENT PRIMARY KEY,

    -- 주문을 한 고객의 ID를 저장하는 열
    -- INT 타입으로 설정하며, 이 필드는 필수로 값을 입력해야 하므로
    -- NOT NULL 제약조건을 적용합니다.
    -- 이 열은 FOREIGN KEY로 설정되어 Customers 테이블의 customer_id를 참조합니다.
    customer_id INT NOT NULL,

    -- 주문 날짜와 시간을 저장하는 열
    -- TIMESTAMP 타입으로 설정하고, DEFAULT CURRENT_TIMESTAMP를 통해
    -- 주문이 생성된 시간에 자동으로 현재 시간이 저장됩니다.
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- 주문 상태를 저장하는 열
    -- ENUM 타입(열거형 타입)으로 설정하여 'Pending', 'Shipped', 'Delivered', 'Cancelled' 중
    -- 하나의 값만을 가질 수 있도록 합니다.
    -- 기본값은 'Pending'으로 설정되어, 주문 생성 시 상태를 자동으로 'Pending'으로 지정합니다.
    status ENUM('Pending', 'Shipped', 'Delivered', 'Cancelled') DEFAULT 'Pending',
/*
참고. 외래 키(Foreign Key)란?
외래 키는 한 테이블의 열의 데이터가 
다른 테이블의 기본키로 설정된 특정 열의 데이터를 참조하기 위해  
이를 통해 두 테이블 간의 관계를 설정하고, 데이터를 연결할 수 있습니다.
*/
    -- customer_id 열에 대한 외래 키(Foreign Key) 제약조건
    -- Orders 테이블의 customer_id는 Customers 테이블의 customer_id를 참조합니다.
    -- 이를 통해 Orders 테이블과 Customers 테이블 간의 관계를 설정합니다.
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
/*
FOREIGN KEY 제약조건을 열에 설정 하면? 어떤 일이 일어 나는가?

1. 고객(Customers)테이블의 customer_id열과  주문(Orders)테이블의 customer_id열의 관계 이해하기
Customers테이블에 각 고객에게는 고유한 customer_id열의 아이디가 있습니다. 
이 customer_id열의 아이디는 고객을 유일하게 식별하는 데 사용됩니다. 
이 customer_id열에는 **기본 키(Primary Key)** 제약 조건을 설정 했으며,
현재 생성하고 있는 주문(Orders)테이블에서는 customer_id라는 열에 고객의 아이디가 저장되게 하여
주문(Orders)테이블에서는 customer_id열은 **외래 키(Foreign Key)** 제약 조건을 설정 했습니다.

2. 연결고리
주문(Orders)테이블에서도 고객(Customers)테이블에 저장된 고객 정보를 참조하고 싶습니다. 
예를 들어, 어떤 주문이 어떤 고객에 의해 이루어졌는지를 알기 위해, 
주문(Orders)테이블에서도 customer_id라는 열을 만들 수 있습니다.
여기서 주문(Orders)테이블의 customer_id열의 아이디는 
      고객(Customers) 테이블의 customer_id열의 아이디 데이터를 참조하는 역할을 하게 됩니다. 
      이때 주문 테이블의 customer_id를 **외래 키(Foreign Key)**라고 부릅니다.

3. 어떻게 동작하나요?
외래 키로 설정된 주문(Orders)테이블의 customer_id라는 열에 저장된 아이디는 
"이 주문은 고객(Customers)테이블의 customer_id열의 아이디 3이 만든 주문입니다"라는 정보를 제공합니다.
즉, 외래 키는 주문정보과 고객정보 간의 연결을 만들어 줍니다.
주문 테이블의 customer_id가 고객 테이블에 있는 customer_id와 일치해야만 
해당 주문이 유효하다고 볼 수 있습니다. 이렇게 함으로써 두 테이블 간의 관계가 형성됩니다.

4. 데이터 무결성 유지
외래 키를 사용하면 데이터의 일관성을 유지할 수 있습니다. 
예를 들어, 고객(Customers)테이블에 고객 정보(하나의 행 데이터)가 삭제되면, 
그 고객의 모든 주문(Orders테이블에 저장된 모든 주문정보 행들)도 함께 자동으로 삭제 처리할 수 있습니다. 
이처럼 외래 키는 데이터베이스에서 정보를 안전하고 정확하게 관리하는 데 중요한 역할을 합니다.
*/
);


-- 실습 순서2-4. 각 주문에 포함된 제품 정보를 저장할  OrderItems 테이블 생성
CREATE TABLE OrderItems (
    -- 주문 항목 ID를 저장하는 열
    -- INT 타입으로 설정하고 자동 증가(AUTO_INCREMENT) 기능을 사용하여
    -- 새로운 주문 항목이 추가될 때마다 이전 주문 항목 ID보다 1씩 증가합니다.
    -- 기본 키(PRIMARY KEY)로 설정하여 주문 항목 ID가 유일하게 유지되도록 합니다.
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,

    -- 관련된 주문의 ID를 저장하는 열
    -- INT 타입으로 설정하며, 이 필드는 필수로 값을 입력해야 하므로
    -- NOT NULL 제약조건을 적용합니다.
    -- 이 열은 FOREIGN KEY로 설정되어 Orders 테이블의 order_id를 참조합니다.
    order_id INT NOT NULL,

    -- 주문 항목에 해당하는 제품의 ID를 저장하는 열
    -- INT 타입으로 설정하며, 이 필드는 필수로 값을 입력해야 하므로
    -- NOT NULL 제약조건을 적용합니다.
    -- 이 열은 FOREIGN KEY로 설정되어 Products 테이블의 product_id를 참조합니다.
    product_id INT NOT NULL,

    -- 주문한 제품의 수량을 저장하는 열
    -- INT 타입으로 설정하며, 이 필드는 필수로 값을 입력해야 하므로
    -- NOT NULL 제약조건을 적용합니다.
    quantity INT NOT NULL,

    -- 주문한 제품의 가격을 저장하는 열
    -- DECIMAL(10, 2) 타입으로 설정하여 최대 10자리 숫자 중 소수점 아래 2자리까지
    -- 저장할 수 있습니다. 이 필드는 필수로 값을 입력해야 하므로
    -- NOT NULL 제약조건을 적용합니다.
    price DECIMAL(10, 2) NOT NULL,

    -- order_id 열에 대한 외래 키 제약조건
    -- OrderItems (주문 항목) 테이블의 order_id는 Orders (주문) 테이블의 order_id를 참조합니다.
    -- 이는 주문 항목이 어떤 주문에 속하는지를 나타내는 중요한 연결 고리입니다.
    -- 즉, OrderItems의 order_id는 반드시 Orders 테이블에 존재하는 유효한 order_id와 일치해야 합니다.
    -- 이렇게 함으로써 데이터베이스에서 잘못된 정보가 들어오는 것을 방지할 수 있습니다.
    -- 만약 Orders 테이블에서 특정 주문이 삭제된다면, 그 주문에 관련된 모든 주문 항목도 함께
    -- 처리할 수 있도록 설정되어 있어 데이터의 일관성을 유지할 수 있습니다.
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),

    -- product_id 열에 대한 외래 키 제약조건
    -- OrderItems (주문 항목) 테이블의 product_id는 Products (제품) 테이블의 product_id를 참조합니다.
    -- 이는 주문 항목이 어떤 제품을 나타내는지를 명확하게 연결해 줍니다.
    -- 즉, OrderItems의 product_id는 반드시 Products 테이블에 있는 유효한 product_id와 일치해야 합니다.
    -- 이렇게 함으로써 잘못된 제품 정보가 입력되는 것을 방지할 수 있습니다.
    -- 만약 Products 테이블에서 특정 제품이 삭제된다면, 그 제품에 관련된 모든 주문 항목도 함께
    -- 관리될 수 있도록 설정되어 있어 데이터의 일관성을 유지할 수 있습니다.
    FOREIGN KEY (product_id) REFERENCES Products(product_id)

);

-- 실습 순서3. 더미 데이터 추가 

-- 실습 순서3-1. Customers(고객)테이블에 더미 데이터 추가 
-- 고객정보 (이름, 이메일, 전화번호, 주소)를 포함하여 5명의 고객 추가 
INSERT INTO Customers (name, email, phone, address) VALUES
('홍길동', 'hong@example.com', '010-1234-5678', '서울시 강남구'),
('김철수', 'kim@example.com', '010-2345-6789',  '서울시 송파구'),
('이영희', 'lee@example.com', '010-3456-7890',  '부산시 해운대구'),
('박준형', 'park@example.com', '010-4567-8901', '대구시 중구'),
('최지우', 'choi@example.com', '010-5678-9012', '인천시 남구');

-- 실습 순서3-2. Products(제품) 테이블에 더미 데이터 추가
-- Products (제품): 5가지 제품의 이름, 설명, 가격 및 재고 수량을 추가했습니다.
INSERT INTO Products (name, description, price, stock) VALUES
('스마트폰', '최신형 스마트폰, 128GB', 799000.00, 50),
('노트북', '고성능 게이밍 노트북, 16GB RAM', 1999000.00, 30),
('무선 이어폰', '액티브 노이즈 캔슬링 기능', 150000.00, 100),
('스마트워치', '건강 모니터링 기능이 탑재된 스마트워치', 299000.00, 70),
('태블릿', '10인치 태블릿, 64GB', 450000.00, 40);

-- 실습 순서3-3. Orders(주문)테이블에 더미 데이터 추가 
-- Order(주문) : 각 주문에 대해 고객ID, 주문 날짜, 상태를 포함하여 주문정보 추가
INSERT INTO Orders(customer_id, order_date, status) VALUES
				  (1, '2024-10-01 10:30:00','Pending'),
				  (2, '2024-10-02 11:00:00', 'Shipped'),
                  (1, '2024-10-03 12:15:00', 'Delivered'),
                  (3, '2024-10-04 14:45:00',  'Cancelled'),
                  (2, '2024-10-05 16:00:00',  'Pending');
delete from orders;
select * from orders;

alter table orders auto_increment = 1;
-- 실습 순서3-4. OrderItems(주문 항목) 테이블에 더미 데이터 추가
-- OrderItems (주문 항목): 각 주문에 포함된 제품 정보를 입력했습니다. 
-- 각 주문과 관련된 제품 ID, 수량, 가격을 설정했습니다. 
-- 주문 ID는 Orders 테이블에서의 ID와 연결되도록 설정했습니다.
INSERT INTO OrderItems (order_id, product_id, quantity, price) VALUES
(1, 1, 1, 799000.00),  -- 홍길동이 스마트폰 1개 주문
(1, 3, 2, 150000.00),  -- 홍길동이 무선 이어폰 2개 주문
(2, 2, 1, 1999000.00), -- 김철수가 노트북 1개 주문
(3, 4, 1, 299000.00),  -- 홍길동이 스마트워치 1개 주문
(4, 5, 1, 450000.00),  -- 이영희가 태블릿 1개 주문
(5, 2, 2, 1999000.00); -- 김철수가 노트북 2개 주문

delete from orderItems;

alter table orderItems auto_increment = 1;

select * from OrderItems;


-- ----------------------------------------------------------------------------------

-- 실습4. `InternetMarket` 데이터베이스의 테이블에서 사용할 수 있는 `SELECT` 문제 10개

### 문제 1: 모든 고객 정보 조회
-- 고객 테이블에서 모든 고객의 정보를 조회하는 SQL 쿼리를 작성하시오.
SELECT * FROM Customers;

### 문제 2: 특정 제품의 가격 조회
-- Products` 테이블에서 "스마트폰"의 가격을 조회하는 SQL 쿼리를 작성하시오.
SELECT price FROM Products WHERE name = '스마트폰';


### 문제 3: 고객 이름과 이메일 조회
-- 고객 테이블에서 고객의 이름과 이메일만 조회하는 SQL 쿼리를 작성하시오.
SELECT name, email FROM Customers;

### 문제 4: 주문 상태가 'Pending'인 주문 조회
-- Orders` 테이블에서 주문 상태가 'Pending'인 주문의 정보를 조회하는 SQL 쿼리를 작성하시오.
SELECT * FROM Orders WHERE status = 'Pending';

### 문제 5: 특정 고객이 주문한 모든 주문 조회
-- 고객 ID가 1인 고객이 주문한 모든 주문의 정보를 조회하는 SQL 쿼리를 작성하시오.
SELECT * FROM Orders WHERE customer_id = 1;

### 문제 6: 제품 재고가 50개 이상인 제품 조회
-- Products` 테이블에서 재고 수량이 50개 이상인 제품의 이름과 재고 수량을 조회하는 SQL 쿼리를 작성하시오.
SELECT name, stock FROM Products WHERE stock >= 50;

### 문제 7: 주문 항목의 총 가격 조회
-- `OrderItems` 테이블에서 각 주문 항목의 총 가격(수량 × 가격)을 계산하여 조회하는 SQL 쿼리를 작성하시오.
SELECT order_item_id, (quantity * price) AS total_price FROM OrderItems;

### 문제 8: 주문 날짜로 정렬하여 조회
-- `Orders` 테이블에서 모든 주문 정보를 주문 날짜 기준으로 내림차순 정렬하여 조회하는 SQL 쿼리를 작성하시오.
SELECT * FROM Orders ORDER BY order_date DESC;


### 문제 9: 주문과 고객 정보를 함께 조회
-- `Orders` 테이블과 `Customers` 테이블을 조인하여 각 주문의 고객 이름과 주문 상태를 조회하는 SQL 쿼리를 작성하시오.
SELECT O.order_id, C.name, O.status
FROM Orders O
JOIN Customers C 
ON O.customer_id = C.customer_id;

### 문제 10: 특정 제품이 포함된 모든 주문 조회
-- `OrderItems` 테이블에서 "스마트폰"이 포함된 모든 주문 정보를 조회하는 SQL 쿼리를 작성하시오.
SELECT O.*
FROM Orders O JOIN OrderItems I 
ON O.order_id = I.order_id
JOIN Products P 
ON I.product_id = P.product_id
WHERE P.name = '스마트폰';

-- ----------------------------------------------------------------------------------
-- 실습5. `InternetMarket` 데이터베이스의 테이블에서 사용할 수 있는 `UPDATE` 문제 5개

### 문제 1: 고객 이메일 수정
-- 고객 ID가 1인 고객의 이메일 주소를 "newemail@example.com"으로 수정하는 SQL 쿼리를 작성하시오
UPDATE Customers 
SET email = 'newemail@example.com' 
WHERE customer_id = 1;

### 문제 2: 제품 가격 인상
-- "스마트폰"의 가격을 50,000원 인상하는 SQL 쿼리를 작성하시오. (예: 현재 가격이 300,000원이면 350,000원으로 수정)
UPDATE Products 
SET price = price + 50000 
WHERE name = '스마트폰';


### 문제 3: 주문 상태 변경
-- 주문 ID가 2인 주문의 상태를 'Shipped'로 변경하는 SQL 쿼리를 작성하시오.

UPDATE Orders 
SET status = 'Shipped' 
WHERE order_id = 2;


### 문제 4: 고객 전화번호 업데이트
-- 고객 ID가 3인 고객의 전화번호를 "010-1234-5678"로 수정하는 SQL 쿼리를 작성하시오.

UPDATE Customers 
SET phone = '010-1234-5678' 
WHERE customer_id = 3;


### 문제 5: 제품 재고 수량 수정
-- "노트북" 제품의 재고 수량을 100으로 업데이트하는 SQL 쿼리를 작성하시오.
UPDATE Products 
SET stock = 100 
WHERE name = '노트북';

-- --------------------------------------------------------------------------------------------------------------
-- 실습6. `InternetMarket` 데이터베이스의 테이블에서 사용할 수 있는 `DELETE` 문제 5개
-- 참고. 외래 키 제약 조건으로 인해 삭제가 불가능한 상황을 다루고 있습니다.

### 문제 1: 고객 삭제
-- 고객 ID가 4인 고객을 삭제하는 SQL 쿼리를 작성하시오.
DELETE FROM Customers 
WHERE customer_id = 4;

### 문제 2: 제품 삭제
-- 제품 ID가 2인 제품을 삭제하는 SQL 쿼리를 작성하시오.
DELETE FROM Products 
WHERE product_id = 2;

/*
DELETE 문이 실패하는 이유는, 
제품 ID가 2인 제품이 이미 주문 항목(OrderItems)에 참조되고 있을 가능성이 있습니다. 
데이터베이스는 외래 키 제약 조건을 통해 참조 무결성을 유지하기 때문에, 
다른 테이블에서 참조되고 있는 데이터를 직접 삭제할 수 없습니다.

해결 방법
제품 ID가 2인 제품을 삭제하기 위해서는 먼저 해당 제품이 포함된 모든 주문 항목을 삭제해야 합니다. 
다음은 그 과정입니다:

해결 순서1. 주문 항목 삭제: 먼저 OrderItems 테이블에서 제품 ID가 2인 모든 항목을 삭제합니다.

해결 순서2. 제품 삭제: 그 후에 Products 테이블에서 제품 ID가 2인 제품을 삭제합니다.
*/
-- Step 1: OrderItems에서 제품 ID가 2인 모든 주문 항목 삭제
DELETE FROM OrderItems 
WHERE product_id = 2;

-- Step 2: Products 테이블에서 제품 ID가 2인 제품 삭제
DELETE FROM Products 
WHERE product_id = 2;


### 문제 3: 주문 삭제
-- 주문 ID가 1인 주문을 삭제하는 SQL 쿼리를 작성하시오.
DELETE FROM Orders 
WHERE order_id = 1;
/*
DELETE 문이 실패하는 이유는, 
주문 ID가 1인 주문이 이미 주문 항목(OrderItems)에 참조되고 있을 가능성이 있습니다. 
외래 키 제약 조건에 따라, 다른 테이블에서 참조되고 있는 데이터를 직접 삭제할 수 없습니다.

해결 방법
주문 ID가 1인 주문을 삭제하기 위해서는 먼저 해당 주문에 속한 모든 주문 항목을 삭제해야 합니다. 
이 과정은 다음과 같습니다:

해결 순서1. 주문 항목 삭제: 먼저 OrderItems 테이블에서 주문 ID가 1인 모든 항목을 삭제합니다.
해결 순서2. 주문 삭제: 그 후에 Orders 테이블에서 주문 ID가 1인 주문을 삭제합니다.
*/
-- Step 1: OrderItems에서 주문 ID가 1인 모든 주문 항목 삭제
DELETE FROM OrderItems 
WHERE order_id = 1;

-- Step 2: Orders 테이블에서 주문 ID가 1인 주문 삭제
DELETE FROM Orders 
WHERE order_id = 1;

### 문제 4: 주문 항목 삭제
-- 주문 항목 ID가 3인 주문 항목을 삭제하는 SQL 쿼리를 작성하시오.
DELETE FROM OrderItems 
WHERE order_item_id = 3;

### 문제 5: 외래 키 제약 조건으로 인한 삭제 실패
 -- 고객 ID가 1인 고객을 삭제하려고 시도하는 SQL 쿼리를 작성하시오. 
 -- 이 고객이 주문을 한 경우, 삭제가 실패하는 이유와 해결 방법을 설명하시오.
DELETE FROM Customers 
WHERE customer_id = 1;

-- **이유**: 고객 ID가 1인 고객이 주문(Orders) 테이블에서 외래 키로 참조되고 있기 때문에 삭제가 불가능합니다. 
--          데이터베이스는 참조 무결성을 유지하기 위해, 외래 키로 연결된 데이터를 삭제할 수 없습니다.

-- **해결 방법**: 먼저 해당 고객과 관련된 모든 주문(Orders)와 주문 항목(OrderItems)을 삭제해야 합니다. 
--             아래와 같은 쿼리를 사용할 수 있습니다:
                                           -- 즉, 고객 ID가 1인 고객이 만든 모든 주문의 order_id를 가져옵니다.
DELETE FROM OrderItems WHERE order_id IN (SELECT order_id FROM Orders WHERE customer_id = 1);
DELETE FROM Orders WHERE customer_id = 1;
DELETE FROM Customers WHERE customer_id = 1;








