-- ��Ʈī ȸ�翡 ��� ���� ������ ����Ǵ� carlist���̺� ����

create table carlist(   
    carno NUMBER NOT NULL PRIMARY KEY, -- �� ��ȣ 
    carname VARCHAR2(50) NOT NULL, -- �� �̸�
    carcompany VARCHAR2(50) NOT NULL, -- �� ������ �� 
    carprice NUMBER NOT NULL, -- �� �Ѵ�� ��Ʈ ����
    carusepeople NUMBER NOT NULL, -- �ν� ���� 
    carinfo VARCHAR2(500) NOT NULL, -- ���� �� ���� 
    carimg VARCHAR2(50) NOT NULL, -- ���� �̹��� ��
    carcategory VARCHAR2(10) NOT NULL -- �� ����( ���� or ���� or  ����)
); 

-- carlist���̺� ���� ������ �߰�
INSERT INTO carlist VALUES(1,'�ƹ���','����',80000,4,'�������� ������������ �߳����ϴ�.','avante.jpg','Small');
INSERT INTO carlist VALUES(2, 'ī����', '����', 90000, 4, '�������� ������������ �߳����ϴ�.', 'carens.jpg', 'Small' );
INSERT INTO carlist VALUES(3, 'ī�Ϲ�', '���', 100000, 9, '�������� ������������ �߳����ϴ�.', 'canival.jpg', 'Small' );
INSERT INTO carlist VALUES(4, '�ڶ���', '�ֿ�', 110000, 4, '�������� ������������ �߳����ϴ�.', 'co.jpg', 'Small' );
INSERT INTO carlist VALUES(101, '����', '����', 120000, 5, '�������� ������������ �߳����ϴ�.', 'equus.jpg', 'Mid' );
INSERT INTO carlist VALUES(102, '�׷���', '����', 130000, 5, '�������� ������������ �߳����ϴ�.', 'grandeur.jpg', 'Mid' );
INSERT INTO carlist VALUES(103, 'k3', '���', 140000, 4, '�������� ������������ �߳����ϴ�.', 'k3.jpg', 'Mid' );
INSERT INTO carlist VALUES(104, 'k5', '���', 150000, 4, '�������� ������������ �߳����ϴ�.', 'k5.jpg', 'Mid' );
INSERT INTO carlist VALUES(201, 'k7', '���', 160000, 4, '�������� ������������ �߳����ϴ�.', 'k7.jpg', 'Big' );
INSERT INTO carlist VALUES(202, 'k9', '���', 170000, 4, '�������� ������������ �߳����ϴ�.', 'k9.jpg', 'Big' );
INSERT INTO carlist VALUES(203, '������', 'GM', 180000, 4, '�������� ������������ �߳����ϴ�.', 'malibu.jpg', 'Big' );
INSERT INTO carlist VALUES(204, 'bmw528i', 'bmw', 190000, 5, '�������� ������������ �߳����ϴ�.', 'bmw.jpg', 'Big' );
?
COMMIT;

Create Table non_carorder(
         non_orderid number PRIMARY KEY, -- �ڵ��� ��Ʈ(�뿩) �ֹ�id ����
         carno number not null, -- ��Ʈ�� ���� ����ȣ ����
         carqty number not null, -- ��Ʈ ���� �뿩 ���� ����
         carreserveday number not null, -- �뿩�Ⱓ ����
         carbegindate varchar2(50) not null, -- �ڵ����� ��Ʈ(�뿩)�� ���۳�¥ ����.
         carins number not null, -- ��Ʈ�� �������� ���� 1 �Ǵ� 0 ����
         carwifi number not null, -- ��Ʈ�� ����wifi���� ���� 1 �Ǵ� 0 ����
         carnave number not null, -- ��Ʈ�� �׺���̼� ���뿩�� ����  (����� �����ϸ�1, �������̸�0)
         carbabyseat number not null, -- ��Ʈ�� ���̺��Ʈ���� ���� 1�Ǵ� 0 ����
         memberphone varchar2(50) not null, -- ��ȸ������ ��Ʈ�� ��� ����ȣ ����
         memberpass varchar2(50) not null -- ��ȸ������ ��Ʈ�� �ֹ� �н����� ���� 
);

/*
����Ŭ�� **������(Sequence)**�� �����ͺ��̽����� ������ ���� ���� �ڵ����� �����ϴ� ��ü�Դϴ�. 
�������� ����ϸ� �ڵ����� �����ϴ� ���� ID ���� ������ �� �־�, 
���̺��� **�⺻ Ű(Primary Key)**�� ���� ���� �ʿ��� �ٸ� �÷��� ���˴ϴ�
?
������ �⺻ ����

CREATE SEQUENCE �������̸�
    INCREMENT BY ������       -- ������ ���� ���� ���� (�⺻���� 1)
    START WITH ���۰�         -- �������� ���� ��
    MINVALUE �ּҰ�           -- �������� ���� �� �ִ� �ּ� ��
    MAXVALUE �ִ밪           -- �������� ���� �� �ִ� �ִ� ��
    NOCYCLE                  -- �ִ밪�� �����ϸ� ���� (CYCLE�� ����ϸ� �ٽ� ó�� ������ ���ư�)
    NOCACHE;                 -- ������ ���� ĳ������ ���� (ĳ���ϸ� ������ ��������, ĳ�õ� ���� ���ǵ� �� ����)
*/

?

create sequence non_carorder_non_orderid
       increment BY 1         -- ������ ���� 1�� ����
       start with 1           -- ������ ���� ���� 1
       minvalue 1             -- �������� �ּ� ���� 1
       maxvalue 9999          -- �������� �ִ� ���� 9999
       nocycle                -- �ִ밪�� �����ص� �ٽ� 1�� �ǵ��ư��� ���� (��ȯ���� ����)
       nocache                -- ������ ���� ĳ������ ����
       noorder;               -- �������� ���������� �������� ���� �� ����

SELECT * FROM carlist NATURAL JOIN non_carorder 
WHERE sysdate < TO_DATE(carbegindate, 'YYYY-MM-DD') 
AND memberphone='01011112222' AND memberpass='3344';

select * from non_carorder where non_orderid=4;

select * from non_carorder;


-- ȸ�� ���̺� member ����
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

-- ������� member ���̺� ���� ����
desc member;

commit; -- ���� �ݿ�

select decode( count(*), 1, 'true', 'false' ) as result 
from member
where id='admin';

-- �亯���� �޼� �ְ� ����¡ ó���� ������ �Խ��� board���̺� ����
CREATE TABLE board(
    b_idx number  PRIMARY KEY, -- �Խ����� ���� ������(�۹�ȣ)
    b_id  varchar2(20) not null, -- ���� �ۼ��� ����� ���̵�
    b_pw  varchar2(10), -- �ۼ��ϴ� ���� ��й�ȣ 
    b_name varchar2(20), -- ���� �ۼ��� ����� �̸�
    b_email varchar2(50), -- ���� �ۼ��� ����� �̸���
    b_title varchar2(100), -- �ۼ��ϴ� ���� ����
    b_content varchar2(4000), -- �ۼ��ϴ� ���� ����
    b_group number, -- �ֱ� �� �亯�� �׷����� �����ټ� �ִ� �׷��ȣ
    b_level number, -- �ۼ��� �亯���� �鿩���� ���� ���� ��
    b_date Date, -- ���� �ۼ��� ��¥
    b_cnt number, -- �� ��ȸ�� 
    
     -- id �÷��� ȸ�����̺� member��  id�÷��� ���� �ܷ�Ű�� �����մϴ�.
    CONSTRAINT FK_BOARD_b_ID FOREIGN KEY(b_id)
    REFERENCES member(id) ON DELETE CASCADE
    --ON DELETE CASCADE
        --�����Ǵ� �θ� ���̺� �࿡ ���� DELETE�� ����Ѵ�.
        --��, �����Ǵ� �θ� ���̺� ���� �����Ǹ� ���������� �ڽ� ���̺� �� ���� �����ȴٴ� �ǹ��̴�.
);
?
-- ������ ����
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
SELECT border_b_idx.nextval, -- b_idx ���� �������� �ڵ� ����
       m.id AS b_id,           -- member ���̺��� id�� b_id�� ����
       '1234' AS b_pw,    -- �⺻ ��й�ȣ �� ����
      '�庸��' AS b_name,       -- member ���̺��� name�� b_name���� ����
       m.email AS b_email,     -- member ���̺��� email�� b_email�� ����
       '������7' AS b_title,     -- �⺻ ����
       '�۳���7' AS b_content, -- �⺻ ����
       1 AS b_group,           -- �⺻ �׷� ��ȣ (�ʿ信 ���� ���� ����)
       0 AS b_level,           -- �⺻ �鿩���� ����
       SYSDATE AS b_date,      -- ���� ��¥�� �ۼ� ��¥�� ����
       0 AS b_cnt              -- ��ȸ���� 0���� ����
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

-- �亯���� �޼� �ְ� ����¡ ó���� �����ϰ� ����÷�� �� �ٿ�ε� ������
-- �Խ��� FileBoard���̺� ����
CREATE TABLE FileBoard(
    b_idx number  PRIMARY KEY, -- �Խ����� ���� ������(�۹�ȣ)
    b_id  varchar2(20) not null, -- ���� �ۼ��� ����� ���̵�
    b_pw  varchar2(10), -- �ۼ��ϴ� ���� ��й�ȣ 
    b_name varchar2(20), -- ���� �ۼ��� ����� �̸�
    b_email varchar2(50), -- ���� �ۼ��� ����� �̸���
    b_title varchar2(100), -- �ۼ��ϴ� ���� ����
    b_content varchar2(4000), -- �ۼ��ϴ� ���� ����
    b_group number, -- �ֱ� �� �亯�� �׷����� �����ټ� �ִ� �׷��ȣ
    b_level number, -- �ۼ��� �亯���� �鿩���� ���� ���� ��
    b_date Date, -- ���� �ۼ��� ��¥
    b_cnt number, -- �� ��ȸ��
    ofile   varchar2(200),  -- ÷��(���ε�)�� �������� �� 
    sfile   varchar2(30),   -- ÷��(���ε�)�� ���� ���ϸ� (����� ���ϸ�)
    downcount number(5) default 0 not null, -- �ٿ�ε��� Ƚ�� 
    
     -- id �÷��� ȸ�����̺� member��  id�÷��� ���� �ܷ�Ű�� �����մϴ�.
    CONSTRAINT FK_FileBoard_b_ID FOREIGN KEY(b_id)
    REFERENCES member(id) ON DELETE CASCADE
);       

-- sfile   varchar2(30) �ٿ���ϴ� ���ϸ��� �� ��� ������ ���⶧����
--  sfile varchar2(1000) ���� ���� 
ALTER TABLE FileBoard MODIFY(sfile varchar2(1000));       

commit; 

-- ������ ����
create sequence FileBorder_b_idx
       increment BY 1
       start with 1
       minvalue 1
       maxvalue 9999
       nocycle
       nocache
       noorder;       

INSERT INTO FileBoard (b_idx, b_id, b_pw, b_name, b_email, b_title, b_content, b_group, b_level, b_date, b_cnt, ofile, sfile, downcount)
SELECT FileBorder_b_idx.nextval, -- b_idx ���� �������� �ڵ� ����
       m.id AS b_id,             -- member ���̺��� id�� b_id�� ����
       'defaultPW' AS b_pw,      -- �⺻ ��й�ȣ �� ����
       m.name AS b_name,         -- member ���̺��� name�� b_name���� ����
       m.email AS b_email,       -- member ���̺��� email�� b_email�� ����
       '�ȳ�' AS b_title,       -- �⺻ ����
       '�ȳ��ϼ���' AS b_content,   -- �⺻ ����
       1 AS b_group,             -- �⺻ �׷� ��ȣ (�ʿ信 ���� ���� ����)
       0 AS b_level,             -- �⺻ �鿩���� ����
       SYSDATE AS b_date,        -- ���� ��¥�� �ۼ� ��¥�� ����
       0 AS b_cnt,               -- ��ȸ���� 0���� ����
       NULL AS ofile,            -- ���� ���ϸ��� NULL�� �ʱ�ȭ
       NULL AS sfile,            -- ����� ���ϸ��� NULL�� �ʱ�ȭ
       0 AS downcount            -- �ٿ�ε� Ƚ���� 0���� �ʱ�ȭ
FROM member m;

commit; 

select max(b_idx) from fileBoard;

-- delete from fileboard;