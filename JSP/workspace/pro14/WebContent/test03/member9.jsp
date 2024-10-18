<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 중에서 core라이브러리에 속한 태그를 불러와서 사용하기위해  taglib 디렉티브태그 작성 --%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"    %>   
  
<%  request.setCharacterEncoding("UTF-8"); %>  
  
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" align="center">
		<tr align="center" bgcolor="#99ccff">
			<td width="7%"><b>아이디</b></td>
			<td width="7%"><b>비밀번호</b></td>
			<td width="7%"><b>이름</b></td>
			<td width="7%"><b>이메일</b></td>
		</tr>
<c:choose>
	<c:when test="${empty param.id}"> <%--아이디를 입력하지 않았다면? --%>
		<tr align="center">
			<td colspan="4">아이디를 입력하세요!</td>
		</tr>
	</c:when>
	<c:otherwise> <%-- 아이디를 입력 했다면? --%>
		<tr align="center">
			<td><c:out value="${param.id}"/></td>
			<td><c:out value="${param.pwd}"/></td>
			<td><c:out value="${param.name}"/></td>
			<td><c:out value="${param.email}"/></td>
		</tr>
	</c:otherwise>
</c:choose>	
	
	
	
	
	</table>

</body>
</html>











