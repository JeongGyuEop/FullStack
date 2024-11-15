package com.spring.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.member.vo.MemberVO;

//MVC ������ ���� Model�� ����� ���� 
//������ɺ� �޼ҵ带 �����ϴ� �������̽�

public interface MemberService {
	//��� ȸ���� ��ȸ �ϴ� ��� 
	public List listMembers() throws DataAccessException;

	//ȸ�� ���� �߰� ��� 
	public void addMembers(MemberVO vo) throws DataAccessException;


	//ȸ������  ������ ���� ȸ�� �Ѹ��� ���� ��ȸ ���
	public MemberVO detailMembers(String id) throws DataAccessException;

	//ȸ������ ���� ��� 
	public void UpdateMember(MemberVO vo) throws DataAccessException;
	
	//ȸ�� ���� ��� 
	public void delMembers(String id) throws DataAccessException;
}






