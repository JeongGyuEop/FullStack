 
 -- 제공한 member테이블과 buy테이블을 이용해 inner join 연습 해보자.
 select * from member;
 select * from buy;
 
 /*
 문제1. member와 buy 테이블을 mem_id를 기준으로 INNER JOIN 하여, 
        모든 회원이 구매한 제품의 이름(prod_name), 가격(price), 그리고 수량(amount)을 조회 
  
  결과 : 모든 회원과 그들이 구매한 제품 정보가 나옵니다. INNER JOIN을 사용하기 때문에, 구매 기록이 있는 회원만 출력됩니다.
*/
select m.mem_name, B.prod_name, B.price, B.amount
from member M inner join buy B
on B.mem_id = M.mem_id;



/*
 문제2.member 테이블에서 addr이 '서울'인 회원을 찾고, 
      그들이 구매한 제품 목록을 buy 테이블과 JOIN하여 조회
 
 결과 : '서울'에 거주하는 회원들이 구매한 제품의 이름을 보여줍니다.
*/

select m.mem_name, m.addr, b.prod_name
from member m inner join buy b
on m.mem_id = b.mem_id
where m.addr = '서울';


/*
문제3. 각 회원이 구매한 제품의 가격과 수량을 곱한 값을 계산하여 총 구매 금액을 출력합니다.
      GROUP BY를 사용하여 회원별로 그룹화하고, 각 회원이 사용한 총 금액을 계산하여 조회 

결과 : 각 회원의 총 구매 금액이 조회되어 표시 됩니다.
*/
select m.mem_name, SUM(b.price * b.amount)
from member m inner join buy b
on m.mem_id = b.mem_id
group by m.mem_name;


/*
문제4. group_name이 '디지털'인 제품을 구매한 회원과 그 제품 정보를 조회합니다.  

결과 : '디지털' 카테고리의 제품을 구매한 회원과 해당 제품 목록이 출력됩니다.
*/
select m.mem_name, b.prod_name
from member m inner join buy b
on m.mem_id = b.mem_id
where b.group_name = '디지털';


/*
문제5.  member 테이블에서 키가 165 이상인 회원들을 필터링하고, 
       그들이 구매한 제품의 이름과 가격을 조회
*/
select b.prod_name, b.price
from member m inner join buy b
on m.mem_id = b.mem_id
where m.height >= 165;

/*
문제6. 회원별 구매한 제품 종류 수 조회 
*/
select m.mem_name, count(distinct b.prod_name)
from member m inner join buy b
on m.mem_id = b.mem_id
group by m.mem_id;



/*
문제7. 구매한 제품 중 가격이 100 이상인 항목을 필터링하여,
      해당 제품을 구매한 회원이름과 제품 이름, 가격을 조회
*/
select m.mem_name, b.prod_name, b.price
from member m inner join buy b
on m.mem_id = b.mem_id
where b.price >= 100;


/*
문제8. 각 회원별로 구매한 제품의 평균 가격과 각회원이름 조회
*/
select AVG(distinct b.price), m.mem_name
from member m inner join buy b
on m.mem_id = b.mem_id
group by m.mem_name;

/*
문제9. 구매한 제품 수량이 3개 이상인 구매상품 명, 구매수량과 그룹회원 이름 같이 조회 
*/
select b.prod_name, b.amount, m.mem_name
from member m inner join buy b
on m.mem_id = b.mem_id
where b.amount >= 3;


/*
문제10. '혼공SQL' 책을 구매한 회원들의 이름과 구매 수량 조회
*/
select m.mem_name, b.amount
from member m inner join buy b
on m.mem_id = b.mem_id
where b.prod_name = '혼공SQL';


/*
문제11. 경남 지역에 거주하는 회원들이 구매한 제품의 총 금액과 그룹 회원명을  같이 조회 
	   참고. 지역별로 데이터를 필터링하고 회원별로 총액을 계산합니다.
*/
select SUM(b.amount * b.price), m.mem_name 
from member m inner join buy b
on m.mem_id = b.mem_id   
where m.addr = '경남'
group by m.mem_name;



/*
문제12. 가장 많이 구매한 제품명, 제품수량과 그룹회원명 같이 조회 하되 1행만 조회 되게 
*/
select b.prod_name, b.amount, m.mem_name
from member m inner join buy b
on m.mem_id = b.mem_id
order by b.amount DESC
limit 1;

/*
문제13. 연락처가 저장된 회원들의 이름,구매 제품명 조회  
 */
select m.mem_name, b.prod_name
from member m inner join buy b
on m.mem_id = b.mem_id
where m.phone1 is not null;
 
 
 