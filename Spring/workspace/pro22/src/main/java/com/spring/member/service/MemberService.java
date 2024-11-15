package com.spring.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.member.vo.MemberVO;

//MVC 디자인 패턴 Model을 만들기 위한 
//단위기능별 메소드를 제공하는 인터페이스

public interface MemberService {
	//모든 회원을 조회 하는 기능 
	public List listMembers() throws DataAccessException;

	//회원 가입 추가 기능 
	public void addMembers(MemberVO vo) throws DataAccessException;


	//회원정보  수정을 위해 회원 한명의 정보 조회 기능
	public MemberVO detailMembers(String id) throws DataAccessException;

	//회원정보 수정 기능 
	public void UpdateMember(MemberVO vo) throws DataAccessException;
	
	//회원 삭제 기능 
	public void delMembers(String id) throws DataAccessException;
}






