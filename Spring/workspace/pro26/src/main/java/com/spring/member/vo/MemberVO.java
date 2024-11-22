package com.spring.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

//VO란?
//Value Object라는 뜻으로
//역할1. 데이터베이스에 저장된 회원 한사람의 정보를 조회한 후
//     VO클래스의 변수에 저장할 역할
//역할2. 새로운 회원정보 하나를 데이터베이스에 insert하기전에
//     임시로 VO클래스의 각변수에 저장할 역할

/*
 	<bean id="memberVO" class="com.spring.memebr.vo.MemberVO" /> 객체(빈) 자동 생성해 준다.
 */
@Component("memberVO")
public class MemberVO {

	//변수
	//t_member테이블의 컬럼 이름과 동일한 자료형과 이름으로 
	//변수들을 선언합니다.
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date  joinDate;
	
	
	//setter, getter 메소드
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	
	
	
	
}









