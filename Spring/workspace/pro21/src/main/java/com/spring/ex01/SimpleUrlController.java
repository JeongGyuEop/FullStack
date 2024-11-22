package com.spring.ex01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/*
  MVC 중에서 C역할을 하는 클래스
  
  action-servlet.xml설정파일의  요청을 처리 하기 위해
  Controller인터페이스를 구현해서 만들어야 하며
  SimpleUrlContrller클래스로 요청이 들어오면
  ModelAndView객체 메모리를 생성하여 웹브라우저로 응답할 VIEW이름인 index.jsp 중에서 index를
  생성자로 전달하여 ModelAndView객체 메모리에 바인딩(저장)후 
  디스패처 서블릿으로 반환합니다.
*/
public class SimpleUrlController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, 
								      HttpServletResponse response) 
								    		  throws Exception {
		

		//작업을 마친후 웹브라우저로 응답할 뷰이름(index.jsp 중에서 index)을 
		//ModelAndView객체 메모리에 저장후 
		//디스패처 서블릿으로 ModelAndView객체를 반환
		
		return  new ModelAndView("index");
	}

	
	
	
}

















