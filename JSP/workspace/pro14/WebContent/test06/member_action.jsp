<%@page import="sec01.ex02.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sec01.ex02.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%-- 한글 설정 --%>
<% request.setCharacterEncoding("UTF-8"); %>

<%-- 
	2, 3. 입력한 모든 회원 정보를 request 객체 메모리에엇 얻어서
		  MemberBean 객체의 모든 set 으로 시작하는 메소드를 호출해서
	      모든 변수에 입력한 정보들을 저장시킨다. 
--%>
<jsp:useBean id="memberbean" class="sec01.ex02.MemberBean" />
<jsp:setProperty property="*" name="memberbean"/>

<%-- 
	4. MemberDAO 객체를 생성해서 addMember 메소드 호출 시 
		addMember 메소드의 매개변수로 입력한 가입할 회원 정보들이 저장된
		바로 위애서 만든 MemberBean 객체의 주소르 ㄹ전달해서 insert 명령
 --%>
 	<%
 		MemberDAO memberDAO = new MemberDAO();
 		memberDAO.addMember(memberbean); // 회원 정보를 DB의 t_member 테이블에 Insert명령
 	
 		// 5. INSERT (회원가입) 진행 후 t_member 테이블에 저장된 모든 회원을 조회해오자
 		//    MemberDAO 객체의 listMembers() 메소드를 호출하여 조회한 결과를 ArrayList 담아 반환받자
 		ArrayList arrayList = memberDAO.listMembers();
 		
 		// 6. 조회한 정보(ArrayList 배열 정보)를 membersList.jsp에 보여주기 위해 
 		//    일단 request 내장 객체 메모리 영역에 바인딩
 		request.setAttribute("list", arrayList);
 		
 	%>
 	<%-- 조회한 정보 (ArrayList 배열 정보)를 리스트 형식으로 보여줄 memberList.jsp에
 		디스패처 방식으로 포워딩(재요청) --%>
	<jsp:forward page="membersList.jsp" />
	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>