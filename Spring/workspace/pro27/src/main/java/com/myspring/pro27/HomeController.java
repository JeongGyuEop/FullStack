package com.myspring.pro27;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//<definition>태그 name 속성 뷰 이름을 타일즈 뷰 리졸버 로 반환할 컨트롤러 !!!!!

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//http://localhost:8090/pro27/main.do 주소를 입력하여
	//요청시 home메소드가 호출되고 
	//아래의 return "main"; 구문을 통해  디스패처 서블릿이 받아
	//tiles_mamber.xml파일의 <definition>태그 로 전달 하여
	//설정한 뷰이름 main을 UrlBasedViewResolver로 전달하여 사용합니다. 
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		
		return "main";
	}
	
}
