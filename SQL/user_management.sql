-- 데이터 베이스 생성
create database if not exists user_management;

-- user_management 데이터베이스 사용 
use user_management;

-- 회원 정보 테이블 생성 
create table if not exists users (
	user_id int auto_increment primary key, -- 회원 고유 ID
    username varchar(50) not null unique, -- 사용자명(로그인 ID)
    email varchar(100) not null unique, -- 이메일 (중복 불가)
    password varchar(255) not null, -- 암호화된 비밀번호 
    created_at timestamp default current_timestamp, -- 가입일
	updated_at timestamp default current_timestamp on update current_timestamp -- 정보 수정일
);

-- 로그인 기록 테이블 
create table if not exists login_history (
	login_id int auto_increment primary key, -- 로그인 기록 ID 
    user_id int,							 -- 회원 ID (외래 키)
    login_time timestamp default current_timestamp, -- 로그인 시간
    foreign key (user_id) references users(user_id) -- 외래 키 참조 
);

-- 샘플 사용자 데이터 추가
INSERT INTO users (username, email, password) VALUES
('john_doe', 'john@example.com', 'password123'),
('jane_smith', 'jane@example.com', 'password456'),
('alice_wonder', 'alice@example.com', 'password789');

-- 샘플 로그인 기록 추가
INSERT INTO login_history (user_id) VALUES
(1),
(2), 
(3);

-- phone_number 열 추가
ALTER TABLE users ADD COLUMN phone_number VARCHAR(20);

-- phone_number 열 삭제 
ALTER TABLE users DROP COLUMN phone_number;

-- 데이터 조회 예시
SELECT * FROM users;
SELECT * FROM login_history;


















