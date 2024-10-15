<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<%
		// 순서 1. 요청한 데이터들을 request 메모리에서 꺼내오기 전에
		// 		   문자 처리 방식을 UTF-8로 설정
		request.setCharacterEncoding("UTF-8");
	
		// 순서 2. 요청한 데이터들 request 객체 메모리에서 꺼내오기(입력한 아이디, 비밀번호)
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
	
		// 순서 3. 요청한 값을 이용해 요청한 클라이언트의 웹 브라우저로 응답할 값을
		//		   웹 브라우저로 출력 (표현식 태그 이용)
		
	%>
	<h1>입력한 아이디는 : <%=user_id %></h1>
	<h1>입력한 비밀번호는 : <%=user_pw %></h1>
    
    
    
    
    
    
    
    
    
    
    
    
    
    