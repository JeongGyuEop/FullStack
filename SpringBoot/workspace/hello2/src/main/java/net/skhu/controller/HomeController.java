package net.skhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	/*
	액션 메소드
	@GetMapping("/") 어노테이션의 "/" 부분은
	그 액션 메소드를 호출하기 위한 URL 이다.

	return "home/index";
	액션 메소드가 리턴한 문자열은 "home/index" 이다.
	  액션 메소드가 리턴한 후
	  src/main/resources/templates/home/index.html 뷰 템플릿 파일이 실행된다.
	*/
	//http://localhost:8088/
	@GetMapping("/")
	public String index() {
		return "home/index";
	}



}





