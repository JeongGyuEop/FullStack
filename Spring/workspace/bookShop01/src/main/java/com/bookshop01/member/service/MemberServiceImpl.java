package com.bookshop01.member.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
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
	 private static final String CLIENT_ID = "f6d8eb0ebbe1cc122b97b3f7be2a2b1a"; // 카카오 개발자 센터에서 발급받은 REST API 키
	 private static final String REDIRECT_URI = "http://localhost:8090/bookShop01/member/kakao/callback"; // 설정한 리디렉션 URL

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
	
	@Override
	public String getAccessToken(String code) {
	    RestTemplate restTemplate = new RestTemplate();

	    // 요청 헤더 설정
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	    // 요청 파라미터 설정
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.add("grant_type", "authorization_code");
	    params.add("client_id", CLIENT_ID);
	    params.add("redirect_uri", REDIRECT_URI);
	    params.add("code", code);
	    params.add("scope", "account_email,gender,birthday,birthyear,phone_number");

	    // 요청 엔티티 생성
	    HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

	    try {
	        // POST 요청으로 액세스 토큰 가져오기
	        ResponseEntity<String> response = restTemplate.exchange(
	                KAKAO_TOKEN_URL, HttpMethod.POST, requestEntity, String.class);

	        // JSON 응답 파싱
	        Gson gson = new Gson();
	        JsonObject jsonResponse = gson.fromJson(response.getBody(), JsonObject.class);

	        // 액세스 토큰 반환
	        return jsonResponse.get("access_token").getAsString();

	    } catch (HttpClientErrorException e) {
	        System.err.println("Request failed: " + e.getResponseBodyAsString());
	        throw new RuntimeException("Failed to fetch access token", e);
	    } catch (Exception e) {
	        throw new RuntimeException("Unexpected error while fetching access token", e);
	    }
	}



	@Override
	public KakaoVO getUserInfo(String accessToken) {
	    RestTemplate restTemplate = new RestTemplate();

	    // Authorization 헤더 설정
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", "Bearer " + accessToken);

	    // HTTP 요청 엔티티 생성
	    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

	    try {
	        // 사용자 정보 요청 (GET)
	        ResponseEntity<String> response = restTemplate.exchange(
	            KAKAO_USER_INFO_URL, HttpMethod.GET, requestEntity, String.class
	        );

	        // 응답 확인
	        if (response == null || response.getBody() == null) {
	            throw new RuntimeException("Kakao API response is null");
	        }

	        // JSON 응답 파싱
	        Gson gson = new Gson();
	        JsonObject jsonResponse = gson.fromJson(response.getBody(), JsonObject.class);

	        // id 확인
	        if (!jsonResponse.has("id")) {
	            throw new RuntimeException("ID field is missing in Kakao API response");
	        }
	        String id = jsonResponse.get("id").getAsString();

	        // kakao_account 확인
	        JsonObject kakaoAccount = jsonResponse.has("kakao_account")
	            ? jsonResponse.getAsJsonObject("kakao_account")
	            : null;

	        if (kakaoAccount == null) {
	            throw new RuntimeException("Kakao account is missing in response");
	        }

	        // 필요한 데이터 추출
	        String email = kakaoAccount.has("email") ? kakaoAccount.get("email").getAsString() : null;
	        String gender = kakaoAccount.has("gender") ? kakaoAccount.get("gender").getAsString() : null;
	        String birthday = kakaoAccount.has("birthday") ? kakaoAccount.get("birthday").getAsString() : null;
	        String birthyear = kakaoAccount.has("birthyear") ? kakaoAccount.get("birthyear").getAsString() : null;
	        String phoneNumber = kakaoAccount.has("phone_number") ? kakaoAccount.get("phone_number").getAsString() : null;

	        // KakaoVO 객체 생성
	        KakaoVO kakaoUser = new KakaoVO();
	        kakaoUser.setId(id);
	        kakaoUser.setAccountEmail(email);
	        kakaoUser.setGender(gender);
	        kakaoUser.setBirthday(birthday);
	        kakaoUser.setBirthyear(birthyear);
	        kakaoUser.setPhoneNumber(phoneNumber);

	        return kakaoUser;

	    } catch (Exception e) {
	        throw new RuntimeException("Error while fetching user info from Kakao API", e);
	    }
	}

	@Override
    public boolean checkKakaoUser(String kakaoId) {
        // Kakao ID로 사용자 존재 여부 확인
        return memberDAO.selectKakaoUser(kakaoId) != null;
    }

	@Override
    public void loginByKakao(String kakaoId, HttpServletRequest request) {
        MemberVO member = memberDAO.selectKakaoUser(kakaoId);
        HttpSession session = request.getSession();
        session.setAttribute("isLogOn", true);
        session.setAttribute("memberInfo", member);
    }

    @Override
    public void registerKakaoUser(KakaoVO kakaoUser) {
        memberDAO.insertKakaoUser(kakaoUser);
    }

}
