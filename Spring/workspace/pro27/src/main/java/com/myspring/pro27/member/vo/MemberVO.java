package com.myspring.pro27.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

//VO???
//Value Object?��?�� ?��?���?
//?��?��1. ?��?��?��베이?��?�� ???��?�� ?��?�� ?��?��?��?�� ?��보�?? 조회?�� ?��
//     VO?��?��?��?�� �??��?�� ???��?�� ?��?��
//?��?��2. ?��로운 ?��?��?���? ?��?���? ?��?��?��베이?��?�� insert?��기전?��
//     ?��?���? VO?��?��?��?�� 각�??��?�� ???��?�� ?��?��

/*
 	<bean id="memberVO" class="com.spring.memebr.vo.MemberVO" /> 객체(�?) ?��?�� ?��?��?�� �??��.
 */
@Component("memberVO")
public class MemberVO {

	//�??��
	//t_member?��?��블의 컬럼 ?��름과 ?��?��?�� ?��료형�? ?��름으�? 
	//�??��?��?�� ?��?��?��?��?��.
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date  joinDate;
	
	
	//setter, getter 메소?��
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









