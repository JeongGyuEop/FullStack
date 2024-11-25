package com.myspring.pro27.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

//VO???
//Value Object?¼?Š” ?œ»?œ¼ë¡?
//?—­?• 1. ?°?´?„°ë² ì´?Š¤?— ???¥?œ ?šŒ?› ?•œ?‚¬?Œ?˜ ? •ë³´ë?? ì¡°íšŒ?•œ ?›„
//     VO?´?˜?Š¤?˜ ë³??ˆ˜?— ???¥?•  ?—­?• 
//?—­?• 2. ?ƒˆë¡œìš´ ?šŒ?›? •ë³? ?•˜?‚˜ë¥? ?°?´?„°ë² ì´?Š¤?— insert?•˜ê¸°ì „?—
//     ?„?‹œë¡? VO?´?˜?Š¤?˜ ê°ë??ˆ˜?— ???¥?•  ?—­?• 

/*
 	<bean id="memberVO" class="com.spring.memebr.vo.MemberVO" /> ê°ì²´(ë¹?) ??™ ?ƒ?„±?•´ ì¤??‹¤.
 */
@Component("memberVO")
public class MemberVO {

	//ë³??ˆ˜
	//t_member?…Œ?´ë¸”ì˜ ì»¬ëŸ¼ ?´ë¦„ê³¼ ?™?¼?•œ ?ë£Œí˜•ê³? ?´ë¦„ìœ¼ë¡? 
	//ë³??ˆ˜?“¤?„ ?„ ?–¸?•©?‹ˆ?‹¤.
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date  joinDate;
	
	
	//setter, getter ë©”ì†Œ?“œ
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









