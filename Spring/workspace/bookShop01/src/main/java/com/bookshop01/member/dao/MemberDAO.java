package com.bookshop01.member.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.bookshop01.kakao.vo.KakaoVO;
import com.bookshop01.member.vo.MemberVO;

public interface MemberDAO {
	public MemberVO login(Map loginMap) throws DataAccessException;
	public void insertNewMember(MemberVO memberVO) throws DataAccessException;
	public String selectOverlappedID(String id) throws DataAccessException;
	MemberVO selectKakaoUser(String kakaoId) throws DataAccessException;
	void insertKakaoUser(KakaoVO kakaoUser) throws DataAccessException;
}
