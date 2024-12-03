package com.bookshop01.member.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bookshop01.kakao.vo.KakaoVO;
import com.bookshop01.member.vo.MemberVO;

public interface MemberService {
	public MemberVO login(Map  loginMap) throws Exception;
	public void addMember(MemberVO memberVO) throws Exception;
	public String overlapped(String id) throws Exception;
	public String getAccessToken(String code);
	public KakaoVO getUserInfo(String accessToken);
	public boolean checkKakaoUser(String id);
	public void loginByKakao(String id, HttpServletRequest request);
	void registerKakaoUser(KakaoVO kakaoUser);
}
