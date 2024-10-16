<%@page import="member.MemberDAO"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//1. join.jsp에서 요청한 가입할 정보 한글처리
	request.setCharacterEncoding("utf-8");

	// 2. join.jsp 에서 입력한 가입할 정보를 request 에 얻어
	// 3. MemberBean 객체를 생성해서 변수에 각각 저장한다.
%>
	<jsp:useBean id="memberbean" class="member.MemberBean" />
	<jsp:setProperty property="*" name="memberbean"/>
	
<%
	//3.1 MemberBean 객체에 setReg_date 메소드를 하여
	//    현재 회원가입하는 날짜와 시간 정보를 생성하여 저장
	memberbean.setReg_date(new Timestamp(System.currentTimeMillis()));

	// 4. member 테이블에 입력한 한 사람의 정보를 추가(회원가입)
	int result = new MemberDAO().insertMember(memberbean);

	// 5. 회원가입에 성공했다면 login.jsp를 포워딩
	response.sendRedirect("login.jsp");
%>	
	
	
	
	
	
	
	
	
	