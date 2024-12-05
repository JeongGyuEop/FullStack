package com.myspring.pro29.ex01;


//회원 정보를 저장시킬 클래스 
public class MemberVO {
	
	private String id;
	private String pwd;
	private String name;
	private String email;
	
	//각변수에 저장된 회원정보들을 얻어 문자열로 만든 뒤 반환 하는 메소드 
	@Override
	public String toString() {
		String info = id + ", " + pwd + ", " + name + ", " + email;
		return info;
	}
	
	//각 변수에 대한 getter/setter 메소드들
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
	

	
	
}
