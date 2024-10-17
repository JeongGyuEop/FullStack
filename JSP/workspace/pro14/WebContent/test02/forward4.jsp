<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   //첫번째 jsp인  forward4.jsp입니다.
   
   //순서1. 클라이언트가 웹브라우저 주소창에 
   //      http://localhost:8090/pro14/test02/forward4.jsp입력하여 
   //      톰캣 서버에 forward4.jsp페이지를 요청합니다.
   
   //순서2. forward4.jsp페이지 내부에 
   //     request내장객체, session내장객체, application내장객체 영역에 각각 바인딩 하는 작업을 해 놓습니다
   request.setCharacterEncoding("UTF-8");

   //request내장객체 영역에 바인딩 
   request.setAttribute("id","hong");  //(속성명,값)
   request.setAttribute("pwd", "1234");
   request.setAttribute("address", "서울시 강남구"); 
   
   
   //session내장객체 영역에 바인딩
   session.setAttribute("name", "홍길동");
   
   //application내장객체 영역에 바인딩
   application.setAttribute("email", "hong@test.com");
   
   
	//순서3. forward4.jsp페이지 내부에서  위 순서2.의 코드가 실행된후   member10.jsp로 포워딩(재요청)시킵니다.
	RequestDispatcher dispatcher = request.getRequestDispatcher("member10.jsp");
					  dispatcher.forward(request, response);
%>    









