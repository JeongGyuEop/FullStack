<%@page import="sec01.ex02.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 중에서 core라이브러리에 속한 태그를 불러와서 사용하기위해  taglib 디렉티브태그 작성 --%>        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); 

   List membersList = new ArrayList();

   membersList.add( new MemberBean("son","1234","손흥민","son@test.com") );
   membersList.add( new MemberBean("ki","4321","기성용","ki@test.com") );
   membersList.add( new MemberBean("park","1212","박지성","park@test.com") );   
%>    
<c:set var="memberList" value="<%=membersList%>" scope="page"/>    
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align="center" width="100%">
		<tr align="center" bgcolor="green">
			<td width="7%">아이디</td>
			<td width="7%">비밀번호</td>
			<td width="5%">이름</td>
			<td width="11%">이메일</td>	
		</tr>

<c:forEach var="i" begin="0"  end="2"  step="1">
		<tr align="center">
			<td>${memberList[i].id}</td>
			<td>${memberList[i].pwd}</td>
			<td>${memberList[i].name}</td>
			<td>${memberList[i].email}</td>
		</tr>
</c:forEach>

		
	</table>
 	
		
		
</body>
</html>












