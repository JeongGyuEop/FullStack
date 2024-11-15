package com.spring.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.member.vo.MemberVO;

public interface MemberDAO {

	//모든 회원을 조회 하는 기능
	public List selectAllMembers() throws DataAccessException;

	//회원 추가 기능 
	public void InsertMember(MemberVO vo) throws DataAccessException;

	//회원 삭제 기능 
	public void DeleteMember(String id) throws DataAccessException;

	//회원정보  수정을 위해 회원 한명의 정보 조회 기능
	public MemberVO oneMember(String id) throws DataAccessException;

	//회원정보 수정 기능
	public void UpdateMember(MemberVO vo) throws DataAccessException;
	
	
	
}
