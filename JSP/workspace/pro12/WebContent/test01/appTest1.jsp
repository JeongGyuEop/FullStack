<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//순서1. 클라이언트가 웹브라우저 주소창에
	//      http://localhost:8090/pro12/test01/appTest1.jsp 요청주소를 
	//      입력하여 TomCat서버에 주소를 보내어 요청합니다
	
	//순서2. TomCat서버가 실행하는 appTest1.jsp페이지에
	//      우리개발자가 session과 application내장객체 메모리 영역에
	//      각각 바인딩 (묶어서 저장)한후 
	//	       두번째 웹페이지 appTest2.jsp를 클라이언트가 요청할수 있게
	//     <a>태그를 만들어 놓자 
	
	//session내장객체 메모리 영역 소멸시점
	//- 요청한 웹브라우저 창을 닫으면 session메모리 영역또한 소멸 된다.
	session.setAttribute("name", "이순신"); 

	//application내장객체 메모리 영역 소멸시점
	//- 요청한 웹브라우저 종류와 상관없이  톰캣서버를 중지하면 웹애플리케이션도 중지되니
	//  그 시점에 application내장객체 메모리 영역 소멸 된다.
	application.setAttribute("address", "서울시 성동구");	
%>    
    
     <h1>session과 application내장객체 영역에 각각 바인딩 함</h1>
     <a href="appTest2.jsp">두 번째 서버페이지 이동</a>
    
    
    
    
    
    
    
    
    
    
    
    
