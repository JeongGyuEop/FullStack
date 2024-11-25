package com.myspring.pro27.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

//VO???
//Value Object?Ό? ?»?Όλ‘?
//?­? 1. ?°?΄?°λ² μ΄?€? ???₯? ?? ??¬?? ? λ³΄λ?? μ‘°ν? ?
//     VO?΄??€? λ³??? ???₯?  ?­? 
//?­? 2. ?λ‘μ΄ ??? λ³? ??λ₯? ?°?΄?°λ² μ΄?€? insert?κΈ°μ ?
//     ??λ‘? VO?΄??€? κ°λ??? ???₯?  ?­? 

/*
 	<bean id="memberVO" class="com.spring.memebr.vo.MemberVO" /> κ°μ²΄(λΉ?) ?? ??±?΄ μ€??€.
 */
@Component("memberVO")
public class MemberVO {

	//λ³??
	//t_member??΄λΈμ μ»¬λΌ ?΄λ¦κ³Ό ??Ό? ?λ£νκ³? ?΄λ¦μΌλ‘? 
	//λ³???€? ? ?Έ?©??€.
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date  joinDate;
	
	
	//setter, getter λ©μ?
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









