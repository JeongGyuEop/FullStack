<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="sec01.ex02.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	//MemberBean객체 2개 생성시 생성자를 호출해서 인스턴스변수값 초기화
	MemberBean memberbean1 = new MemberBean("lee","1234","이순신","lee@test.com");
	MemberBean memberbean2 = new MemberBean("son","1234","손흥민","son@test.com");

	//위 생성한 MemberBean객체 2개를 ArrayList배열에 추가 
	List membersList = new ArrayList();
		 membersList.add(memberbean1);
		 membersList.add(memberbean2);
		 
	//request내장객체 메모리에 ArrayList배열 자체를 바인딩(저장)	 
	request.setAttribute("list", membersList);
				 //속성명 "list" , 값 new ArrayList(); 배열 
				 		 
				 

	//member9.jsp를 디스패처 방식으로 포워딩(재요청)
%>
	<jsp:forward  page="member9.jsp" />













