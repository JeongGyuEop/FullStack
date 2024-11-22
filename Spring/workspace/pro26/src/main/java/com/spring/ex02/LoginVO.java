package com.spring.ex02;

public class LoginVO {
	private String userID; // <input> 태그의 name 속성에 적힌 값과 같은 이름의 변수명으로 선언
	private String userName; // <input> 태그의 name 속성에 적힌 값과 같은 이름의 변수명으로 선언
	
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
