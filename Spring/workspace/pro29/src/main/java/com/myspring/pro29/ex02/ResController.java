package com.myspring.pro29.ex02;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.pro29.ex01.MemberVO;


/*
 	주제 : @ResponseBody 어노테이션 사용하기
 	내용 : 지금까지 사용한 컨트롤러의 메소드들은  JSP를 뷰리졸버로 리턴한후  브라우저에서 결과를 표시했습니다.
 	      그런데 컨트롤러의 특정 메소드에 @ResponseBody를 적용하면 JSP가 아닌 텍스트나 JSON으로 결과를 웹브라우저에 전송해서 출력할수 있습니다
 */

@Controller
public class ResController {

	@RequestMapping(value="/res1")	
	@ResponseBody
	public Map<String, Object> res1(){
		
		Map<String, Object> map = new HashMap<String, Object>();
							map.put("id", "hong");
							map.put("name", "홍길동");
		return map; //Map을 JsonObject객체로 자동 변환해서 웹브라우저로 내보내 출력(응답)
		
	}
	
	//메소드 호출시 home.jsp를 브라우저로 전송합니다.
	@RequestMapping(value="/res2")
	public ModelAndView res2() {
		
		return new ModelAndView("home");
	}
	

	@RequestMapping(value="/res3")
	public ResponseEntity res3() {
		
		//HttpHeaders 클래스를 이용해  ResponseEntity로 전송할 데이터의 종류와  한글 인코딩을 설정하자
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; chartset=utf-8");
		
		//웹브라우저로 전송할 자바스크립트 코드를 문자열로 작성합니다.
		String message = "<script>";
			   message += " alert('새 회원을 등록합니다.'); ";
			   message += " location.href='/pro29/test/membersList2'; ";
			   message += "</script>";
			   
		return new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);	   
			  	
	}	
	
}
















