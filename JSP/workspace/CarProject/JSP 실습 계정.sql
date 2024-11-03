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


-- 회원 테이블 member 생성
CREATE TABLE MEMBER(
    id varchar2(12) not null primary key,
    pass varchar2(12) not null,
    name varchar2(20) not null,
    reg_date DATE not null
);

alter table member
add email varchar2(100);

alter table member
add tel varchar2(100);

alter table member
add hp varchar2(100);

alter table member 
add age number;

alter table member
add gender varchar2(5);

alter table member
add address varchar2(1000);

-- 만들어진 member 테이블 구조 보기
desc member;

commit; -- 영구 반영

select decode( count(*), 1, 'true', 'false' ) as result 
from member
where id='admin';

-- 답변글을 달수 있고 페이징 처리가 가능한 게시판 board테이블 생성
CREATE TABLE board(
    b_idx number  PRIMARY KEY, -- 게시판의 글의 순서값(글번호)
    b_id  varchar2(20) not null, -- 글을 작성한 사람의 아이디
    b_pw  varchar2(10), -- 작성하는 글의 비밀번호 
    b_name varchar2(20), -- 글을 작성한 사람의 이름
    b_email varchar2(50), -- 글을 작성한 사람의 이메일
    b_title varchar2(100), -- 작성하는 글의 제목
    b_content varchar2(4000), -- 작성하는 글의 내용
    b_group number, -- 주글 과 답변글 그룹으로 묶어줄수 있는 그룹번호
    b_level number, -- 작성한 답변글의 들여쓰기 정도 레벨 값
    b_date Date, -- 글을 작성한 날짜
    b_cnt number, -- 글 조회수 
    
     -- id 컬럼을 회원테이블 member의  id컬럼에 대해 외래키로 지정합니다.
    CONSTRAINT FK_BOARD_b_ID FOREIGN KEY(b_id)
    REFERENCES member(id) ON DELETE CASCADE
    --ON DELETE CASCADE
        --참조되는 부모 테이블 행에 대한 DELETE를 허용한다.
        --즉, 참조되는 부모 테이블 값이 삭제되면 연쇄적으로 자식 테이블 값 역시 삭제된다는 의미이다.
);
?
-- 시퀀스 생성
create sequence border_b_idx
       increment BY 1
       start with 1
       minvalue 1
       maxvalue 9999
       nocycle
       nocache
       noorder;

commit;

INSERT INTO board (b_idx, b_id, b_pw, b_name, b_email, b_title, b_content, b_group, b_level, b_date, b_cnt)
SELECT border_b_idx.nextval, -- b_idx 값을 시퀀스로 자동 생성
       m.id AS b_id,           -- member 테이블의 id를 b_id로 삽입
       '1234' AS b_pw,    -- 기본 비밀번호 값 설정
      '장보고' AS b_name,       -- member 테이블의 name을 b_name으로 삽입
       m.email AS b_email,     -- member 테이블의 email을 b_email로 삽입
       '글제목7' AS b_title,     -- 기본 제목
       '글내용7' AS b_content, -- 기본 내용
       1 AS b_group,           -- 기본 그룹 번호 (필요에 따라 변경 가능)
       0 AS b_level,           -- 기본 들여쓰기 레벨
       SYSDATE AS b_date,      -- 현재 날짜를 작성 날짜로 설정
       0 AS b_cnt              -- 조회수를 0으로 설정
FROM member m;

commit;
?
SELECT * FROM board ORDER BY b_group asc;

SELECT email, name, id FROM member WHERE id='admin';

SELECT * FROM board WHERE b_idx=3;

-- -------------------------------------------------------------------------------
DELETE FROM BOARD;
COMMIT;


-- ------------------------------------------------------------------------------------------

-- 답변글을 달수 있고 페이징 처리가 가능하고 파일첨부 및 다운로드 가능한
-- 게시판 FileBoard테이블 생성
CREATE TABLE FileBoard(
    b_idx number  PRIMARY KEY, -- 게시판의 글의 순서값(글번호)
    b_id  varchar2(20) not null, -- 글을 작성한 사람의 아이디
    b_pw  varchar2(10), -- 작성하는 글의 비밀번호 
    b_name varchar2(20), -- 글을 작성한 사람의 이름
    b_email varchar2(50), -- 글을 작성한 사람의 이메일
    b_title varchar2(100), -- 작성하는 글의 제목
    b_content varchar2(4000), -- 작성하는 글의 내용
    b_group number, -- 주글 과 답변글 그룹으로 묶어줄수 있는 그룹번호
    b_level number, -- 작성한 답변글의 들여쓰기 정도 레벨 값
    b_date Date, -- 글을 작성한 날짜
    b_cnt number, -- 글 조회수
    ofile   varchar2(200),  -- 첨부(업로드)한 원본파일 명 
    sfile   varchar2(30),   -- 첨부(업로드)한 실제 파일명 (저장된 파일명)
    downcount number(5) default 0 not null, -- 다운로드한 횟수 
    
     -- id 컬럼을 회원테이블 member의  id컬럼에 대해 외래키로 지정합니다.
    CONSTRAINT FK_FileBoard_b_ID FOREIGN KEY(b_id)
    REFERENCES member(id) ON DELETE CASCADE
);       

-- sfile   varchar2(30) 다운로하는 파일명이 길 경우 에러가 나기때문에
--  sfile varchar2(1000) 으로 수정 
ALTER TABLE FileBoard MODIFY(sfile varchar2(1000));       

commit; 

-- 시퀀스 생성
create sequence FileBorder_b_idx
       increment BY 1
       start with 1
       minvalue 1
       maxvalue 9999
       nocycle
       nocache
       noorder;       

INSERT INTO FileBoard (b_idx, b_id, b_pw, b_name, b_email, b_title, b_content, b_group, b_level, b_date, b_cnt, ofile, sfile, downcount)
SELECT FileBorder_b_idx.nextval, -- b_idx 값을 시퀀스로 자동 생성
       m.id AS b_id,             -- member 테이블의 id를 b_id로 삽입
       'defaultPW' AS b_pw,      -- 기본 비밀번호 값 설정
       m.name AS b_name,         -- member 테이블의 name을 b_name으로 삽입
       m.email AS b_email,       -- member 테이블의 email을 b_email로 삽입
       '안녕' AS b_title,       -- 기본 제목
       '안녕하세요' AS b_content,   -- 기본 내용
       1 AS b_group,             -- 기본 그룹 번호 (필요에 따라 변경 가능)
       0 AS b_level,             -- 기본 들여쓰기 레벨
       SYSDATE AS b_date,        -- 현재 날짜를 작성 날짜로 설정
       0 AS b_cnt,               -- 조회수를 0으로 설정
       NULL AS ofile,            -- 원본 파일명을 NULL로 초기화
       NULL AS sfile,            -- 저장된 파일명을 NULL로 초기화
       0 AS downcount            -- 다운로드 횟수를 0으로 초기화
FROM member m;

commit; 

select max(b_idx) from fileBoard;

-- delete from fileboard;