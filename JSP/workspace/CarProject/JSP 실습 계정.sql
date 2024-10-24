-- 렌트카 회사에 모든 차량 정보가 저장되는 carlist테이블 생성

create table carlist(   
    carno NUMBER NOT NULL PRIMARY KEY, -- 차 번호 
    carname VARCHAR2(50) NOT NULL, -- 차 이름
    carcompany VARCHAR2(50) NOT NULL, -- 차 제조사 명 
    carprice NUMBER NOT NULL, -- 차 한대당 렌트 가격
    carusepeople NUMBER NOT NULL, -- 인승 정보 
    carinfo VARCHAR2(500) NOT NULL, -- 차량 상세 정보 
    carimg VARCHAR2(50) NOT NULL, -- 차량 이미지 명
    carcategory VARCHAR2(10) NOT NULL -- 차 유형( 소형 or 중형 or  대형)
); 

-- carlist테이블에 차량 정보들 추가
INSERT INTO carlist VALUES(1,'아반테','현대',80000,4,'이차량은 소형차량으로 잘나갑니다.','avante.jpg','Small');
INSERT INTO carlist VALUES(2, '카렌스', '현대', 90000, 4, '이차량은 소형차량으로 잘나갑니다.', 'carens.jpg', 'Small' );
INSERT INTO carlist VALUES(3, '카니발', '기아', 100000, 9, '이차량은 소형차량으로 잘나갑니다.', 'canival.jpg', 'Small' );
INSERT INTO carlist VALUES(4, '코란도', '쌍용', 110000, 4, '이차량은 소형차량으로 잘나갑니다.', 'co.jpg', 'Small' );
INSERT INTO carlist VALUES(101, '에쿠스', '현대', 120000, 5, '이차량은 중형차량으로 잘나갑니다.', 'equus.jpg', 'Mid' );
INSERT INTO carlist VALUES(102, '그렌져', '현대', 130000, 5, '이차량은 중형차량으로 잘나갑니다.', 'grandeur.jpg', 'Mid' );
INSERT INTO carlist VALUES(103, 'k3', '기아', 140000, 4, '이차량은 중형차량으로 잘나갑니다.', 'k3.jpg', 'Mid' );
INSERT INTO carlist VALUES(104, 'k5', '기아', 150000, 4, '이차량은 중형차량으로 잘나갑니다.', 'k5.jpg', 'Mid' );
INSERT INTO carlist VALUES(201, 'k7', '기아', 160000, 4, '이차량은 대형차량으로 잘나갑니다.', 'k7.jpg', 'Big' );
INSERT INTO carlist VALUES(202, 'k9', '기아', 170000, 4, '이차량은 대형차량으로 잘나갑니다.', 'k9.jpg', 'Big' );
INSERT INTO carlist VALUES(203, '말리부', 'GM', 180000, 4, '이차량은 대형차량으로 잘나갑니다.', 'malibu.jpg', 'Big' );
INSERT INTO carlist VALUES(204, 'bmw528i', 'bmw', 190000, 5, '이차량은 대형차량으로 잘나갑니다.', 'bmw.jpg', 'Big' );
?
COMMIT;

Create Table non_carorder(
         non_orderid number PRIMARY KEY, -- 자동차 렌트(대여) 주문id 저장
         carno number not null, -- 렌트할 차량 차번호 저장
         carqty number not null, -- 렌트 차량 대여 수량 저장
         carreserveday number not null, -- 대여기간 저장
         carbegindate varchar2(50) not null, -- 자동차를 렌트(대여)할 시작날짜 저장.
         carins number not null, -- 렌트시 보험적용 여부 1 또는 0 저장
         carwifi number not null, -- 렌트시 무선wifi적용 여부 1 또는 0 저장
         carnave number not null, -- 렌트시 네비게이션 적용여부 저장  (무료로 적용하면1, 미적용이면0)
         carbabyseat number not null, -- 렌트시 베이비시트적용 여부 1또는 0 저장
         memberphone varchar2(50) not null, -- 비회원으로 렌트할 사람 폰번호 저장
         memberpass varchar2(50) not null -- 비회원으로 렌트시 주문 패스워드 저장 
);

/*
오라클의 **시퀀스(Sequence)**는 데이터베이스에서 고유한 숫자 값을 자동으로 생성하는 객체입니다. 
시퀀스를 사용하면 자동으로 증가하는 고유 ID 값을 생성할 수 있어, 
테이블의 **기본 키(Primary Key)**나 고유 값이 필요한 다른 컬럼에 사용됩니다
?
시퀀스 기본 문법

CREATE SEQUENCE 시퀀스이름
    INCREMENT BY 증가값       -- 시퀀스 값의 증가 단위 (기본값은 1)
    START WITH 시작값         -- 시퀀스의 시작 값
    MINVALUE 최소값           -- 시퀀스가 가질 수 있는 최소 값
    MAXVALUE 최대값           -- 시퀀스가 가질 수 있는 최대 값
    NOCYCLE                  -- 최대값에 도달하면 종료 (CYCLE을 사용하면 다시 처음 값으로 돌아감)
    NOCACHE;                 -- 시퀀스 값을 캐싱하지 않음 (캐싱하면 성능이 향상되지만, 캐시된 값이 유실될 수 있음)
*/

?

create sequence non_carorder_non_orderid
       increment BY 1         -- 시퀀스 값이 1씩 증가
       start with 1           -- 시퀀스 시작 값이 1
       minvalue 1             -- 시퀀스의 최소 값은 1
       maxvalue 9999          -- 시퀀스의 최대 값은 9999
       nocycle                -- 최대값에 도달해도 다시 1로 되돌아가지 않음 (순환하지 않음)
       nocache                -- 시퀀스 값을 캐싱하지 않음
       noorder;               -- 시퀀스가 순차적으로 생성되지 않을 수 있음

SELECT * FROM carlist NATURAL JOIN non_carorder 
WHERE sysdate < TO_DATE(carbegindate, 'YYYY-MM-DD') 
AND memberphone='01011112222' AND memberpass='3344';

select * from non_carorder where non_orderid=4;

select * from non_carorder;