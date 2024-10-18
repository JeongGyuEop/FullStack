<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Person List</h2>
	<table border="1">
		<tr>
			<th>No.</th>
			<th>Name</th>
			<th>짝수</th>
			<th>홀수</th>
		</tr> 
	
	<%-- items 속성을 사용하여 personList 반복 --%>
	<c:forEach var="person" items="${requestScope.personList}" varStatus="loop">
	
		<tr>
			<td>${loop.index + 1 }</td>
			<td>${person }</td>
			
			<%-- loop.even을 사용하여 짝수 번재 저장된 회원ㅇ인지 여부 확인 --%>
			<td><c:if test="${(loop.index + 1) % 2 == 0}">짝수</c:if></td>
            <td><c:if test="${(loop.index + 1) % 2 != 0}">홀수</c:if></td>
			<%-- <td>${loop.even }</td>
			<td>${loop.odd }</td> --%>
			
		</tr>
	
	</c:forEach>
		
	
	
	</table>
</body>
</html>