package com.bookshop01.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.bookshop01.kakao.vo.KakaoVO;
import com.bookshop01.member.dao.MemberDAO;
import com.bookshop01.member.vo.MemberVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import net.sf.json.JSONObject;

@Service("memberService")
@Transactional(propagation=Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {
	
	 private static final String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
	 private static final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";
	 private static final String CLIENT_ID = "6d9c47e51202a4e281197512a48b13b0"; // 카카오 개발자 센터에서 발급받은 REST API 키
	 private static final String REDIRECT_URI = "YOUR_REDIRECT_URI"; // 설정한 리디렉션 URL

	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public MemberVO login(Map  loginMap) throws Exception{
		return memberDAO.login(loginMap);
	}
	
	@Override
	public void addMember(MemberVO memberVO) throws Exception{
		memberDAO.insertNewMember(memberVO);
	}
	
	@Override
	public String overlapped(String id) throws Exception{
		return memberDAO.selectOverlappedID(id);
	}
	
	public String getAccessToken(String code) {
	    RestTemplate restTemplate = new RestTemplate();

	    HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 요청 파라미터
        String body = "grant_type=authorization_code" +
                      "&client_id=" + CLIENT_ID +
                      "&redirect_uri=" + REDIRECT_URI +
                      "&code=" + code;

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        // POST 요청으로 액세스 토큰 가져오기
        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_TOKEN_URL, HttpMethod.POST, requestEntity, String.class);

        // Gson 사용하여 JSON 파싱
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response.getBody(), JsonObject.class);

        return jsonResponse.get("access_token").getAsString(); // 액세스 토큰 반환
    }

    public KakaoVO getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // GET 요청으로 사용자 정보 가져오기
        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_USER_INFO_URL, HttpMethod.GET, requestEntity, String.class);

        JSONObject jsonResponse = new JSONObject(response.getBody());
        String id = jsonResponse.getString("id");
        JSONObject kakaoAccount = jsonResponse.getJSONObject("kakao_account");

        String email = kakaoAccount.getString("email");
        String nickname = kakaoAccount.getJSONObject("profile").getString("nickname");

        return new KakaoVO(id, email, nickname); // 사용자 정보 객체 생성
    }
}
