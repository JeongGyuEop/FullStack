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


































































