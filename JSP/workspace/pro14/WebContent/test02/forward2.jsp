<%@page import="sec01.ex02.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("utf-8");

   //forward2.jsp에서 MemberBean객체를 생성하고 
   //각 인스턴스변수에 회원정보를 수동으로 저장합니다.
    MemberBean memberbean = 
    			new MemberBean("lee","1234","이순신","lee@test.com");
   
   //그리고 request내장객체 메모리영역에 
   //"member"라는 이름으로 MemberBean객체의 주소를 같이 바인딩함
    request.setAttribute("member", memberbean);
   
    //마지막으로 member8.jsp 재요청(포워딩)합니다.
%>
	<jsp:forward  page="member8.jsp" />













