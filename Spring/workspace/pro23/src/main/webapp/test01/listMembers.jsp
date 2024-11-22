<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"   %>    
    
<%
	request.setCharacterEncoding("UTF-8");
%>    

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" align="center" width="80%">
		<tr align="center" bgcolor="lightgreen">
			<td><b>아이디</b></td>
			<td><b>비밀번호</b></td>
			<td><b>이름</b></td>
			<td><b>이메일</b></td>
			<td><b>가입일</b></td>
			<td><b>수정</b></td>	
			<td><b>삭제</b></td>		
		</tr>
<c:forEach var="member"  items="${requestScope.membersList}">
		<tr align="center">
			<td>${member.id}</td>
			<td>${member.pwd}</td>
			<td>${member.name}</td>
			<td>${member.email}</td>
			<td>${member.joinDate}</td>
			<td><b><a href="${contextPath}/member/memberDetail.do?id=${member.id}">수정</a></b></td>	
			<td><b><a href="${contextPath}/member/memberDel.do?id=${member.id}">삭제</a></b></td>	
		</tr>
</c:forEach>		
	</table>

	<a href="${contextPath}/member/memberForm.do">
		<h1 style="text-align: center">회원가입</h1>
	</a>
	


</body>
</html>











