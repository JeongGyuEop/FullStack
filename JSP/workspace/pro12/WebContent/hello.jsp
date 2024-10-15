<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- 
	JSP 스크립트 요소란?
	
		- JSP 페이지에서 HTML 태그에 자바코드를 넣을 수 있는 태그들을 말한다.
		
	JSP 스크립트 요소들 종류
		1. 선언문 태그 <%! %>
			: JSP 페이지에서 변수나 메소드를 선언해 놓을 영역의 태그
		2. 스크립틀릿 <% %> 
			: JSP 페이지에서 자바코드를 작성할 영역의 태그
		3. 표현식 태그 <= %>
			: JSP 페이지에 웹브라우저로 응답할 데이터를 넣는 태그
			  값 또는 변수명을 넣어서 웹브라우저에 출력할 수 있는 태그

 --%>
 
 <%-- 선언문 태그 영역에 전역변수 name과 getName()메소드를 만들어 놓자 --%>
 <%!
 	// 변수 선언
 	String name = "듀크";
 
 	// 메소드 선언 
 	public String getName() {
 		return name;
 	}
 
 %>
 
<%-- 표현식 태그 영역을 이용해 선언문 태그영역에 만들어 놓은
 name변수의 값을 얻어 출력(웹브라우저로 응답) --%>
안녕하세요. <%= name %> 님!~