<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//실습할 첫번쨰 JSP인 forward1.jsp입니다.

	//순서1. 클라이언트가 웹브라우저 주소창에 
	//      http://localhost:8090/pro14/test02/forward1.jsp입력하여
	//      톰캣 서버에 forward1.jsp페이지를 요청합니다.
	
	//순서2. forward1.jsp페이지 내부에 
	//      request내장객체, session내장객체, application내장객체
	//      영역에 각각 바인딩 하는 작업을 해 놓습니다.

	request.setCharacterEncoding("utf-8");

//request 내장객체 메모리 영역에 바인딩
	//- 다른 서버페이지를 재요청하지 않으면 톰캣이 자동 소멸 합니다.
	//  그래서 다른 서버페이지 요청시 메모리 영역 유지 
	request.setAttribute("id", "hong"); //("속성명",값)
	request.setAttribute("pwd", "1234");
	
//session  내장객체 메모리 영역에 바인딩
	//- 요청한 브라우저 창이 닫히기 전까지 메모리 영역 유지 	
	session.setAttribute("name", "홍길동");

//application 내장객체 메모리 영역에 바인딩
	//- 톰캣 서버 중지 되기 전까지 메모리 영역 유지
	//  (웹 애플리케이션이 종료되기 전까지 메모리 영역 유지)
	application.setAttribute("email", "hong@test.com");


	//순서3. forward1.jsp페이지 내부에서 위 순서2.의 코드가 실행된 후 
	//      member7.jsp로 포워딩(재요청)시킵니다.
	RequestDispatcher dispatcher = 
					  request.getRequestDispatcher("member7.jsp");
	dispatcher.forward(request, response);
	

%>












