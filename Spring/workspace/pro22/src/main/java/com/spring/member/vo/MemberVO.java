package com.spring.member.vo;

import java.sql.Date;

//VO��?
//Value Object��� ������
//����1. �����ͺ��̽��� ����� ȸ�� �ѻ���� ������ ��ȸ�� ��
//   VOŬ������ ������ ������ ����
//����2. ���ο� ȸ������ �ϳ��� �����ͺ��̽��� insert�ϱ�����
//   �ӽ÷� VOŬ������ �������� ������ ����
public class MemberVO {
	
	//������� 
	//t_member���̺��� �÷��̸��� ������ �ڷ����� �̸����� ����
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	
	
	//setter,getter �޼ҵ� 
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











