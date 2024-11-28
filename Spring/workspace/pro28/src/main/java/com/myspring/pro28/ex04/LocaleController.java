package com.myspring.pro28.ex04;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LocaleController {

	
	@RequestMapping(value = "/test/locale.do")
	public String locale(HttpServletRequest request, 
						 HttpServletResponse response) throws Exception {
		
		System.out.println("LocaleController클래스의 locale메소드 호출됨");
		
		//디스패처 서블릿으로 VIEW명 반환
		return "locale";
		
	}
	
	
}















