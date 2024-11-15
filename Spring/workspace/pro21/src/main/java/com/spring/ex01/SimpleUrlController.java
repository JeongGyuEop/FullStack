package com.spring.ex01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/*
 * 	MVC 중에서 C 역할을 하는 클래스
 * 
 * action-servlet.xmp 설정 파일의 요청을 처리하기 위해
 * Controller 인터페이스르 구현해서 만들어야 하며
 * SimpleUrlController 클래스로 요청이 들어오면
 * ModelAndView 객체 메모리를 생성하여 웹브라우저로 응답할 VIEW이름인 
 * index.jsp 중에서 index만
 * 생성자로 전달하여 ModelAndView 객체 메모리에 바인딩(저장)후
 * 디스패처 서블릿으로 변환한다.
 * 
 */


public class SimpleUrlController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, 
									  HttpServletResponse response) 
											  throws Exception {
		
		// 작업을 마친후 웹브라웅저로 응답할 뷰이름을 
		// ModelAndView 객체 메모리에 저장후
		// 디스패처 서블릿으로 ModelAndView 객체를 반환
		return new ModelAndView("index");
		
	}
	
}
