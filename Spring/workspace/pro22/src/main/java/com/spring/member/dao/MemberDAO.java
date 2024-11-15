package com.spring.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.member.vo.MemberVO;

public interface MemberDAO {

	//��� ȸ���� ��ȸ �ϴ� ���
	public List selectAllMembers() throws DataAccessException;

	//ȸ�� �߰� ��� 
	public void InsertMember(MemberVO vo) throws DataAccessException;

	//ȸ�� ���� ��� 
	public void DeleteMember(String id) throws DataAccessException;

	//ȸ������  ������ ���� ȸ�� �Ѹ��� ���� ��ȸ ���
	public MemberVO oneMember(String id) throws DataAccessException;

	//ȸ������ ���� ���
	public void UpdateMember(MemberVO vo) throws DataAccessException;
	
	
	
}
