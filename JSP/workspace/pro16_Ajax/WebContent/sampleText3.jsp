<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//클라이언트가 요청한 값 얻어 응답
	
	request.setCharacterEncoding("UTF-8");

//userid=홍길동&passwd=post
	String userid = request.getParameter("userid");
	String passwd = request.getParameter("passwd");

	out.println(userid + "\t" + passwd);
%>