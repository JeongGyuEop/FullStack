<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- 선언문 태그 영역 이용 : 변수 선언, 메소드 선언 --%>
<%!
	String name = "이순신";

	public String getName() {
		return name;
	}

%>
    
<%-- 스크립틀릿 태그 영역 이용 : 자바코드를 작성 --%>
<%
	// 클라이언트가 웹브라우저 주소창에 요청한 주소에 해당하는 요청한 
	// 값 age=22의 22를 얻자
	String age = request.getParameter("age");
%>

<%-- 표현식 태그영역 이용 : 값 or 변수의값 or 자바코드의 결과값 출력 --%>
<h1>안녕하세요 <%= name %>입니다.</h1>
<h1>나이는 <%=age %>입니다.</h1>
<h1>키는 <%=180 %>cm 입니다.</h1>
<h1>나이 + 10을 계산한 결과는 <%=Integer.parseInt(age)+10 %></h1>


