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