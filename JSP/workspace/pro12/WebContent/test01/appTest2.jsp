<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//첫번째 appTest1.jsp에서 session과 application내장객체에
	//바인딩한 값을 현재 appTest2.jsp에서 얻어 사용합시다

	//"이순신"
	String name = (String)session.getAttribute("name");
    //"서울시 성동구"
	String address = (String)application.getAttribute("address");
%>    
<h1>이름은 <%=name%>입니다.</h1> <!-- session에서 꺼내온 "이순신" 출력 -->
<h1>주소는  <%=address%>입니다.</h1> <!-- application에서 꺼내온 "서울시 성동구" 출력 -->