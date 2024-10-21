<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조회한 회원 정보를 보여주는 화면</title>
</head>
<body>
	<table align="center" border="1">
		<tr align=center" bgcolor="lightgreen">
			<th width="7%">아이디</th>
			<th width="7%">비밀번호</th>
			<th width="7%">이름</th>
			<th width="7%">이메일</th>
			<th width="7%">가입일</th>
		</tr>
		
		<c:choose>
		<%-- 조회된 정보가 저장된 ArrayList 배열이 request 에 저장되어 있지 않으면? --%>
			<c:when test="${ empty requestScope.list} }">
				<tr align="center">
					<td colspan="5"><b>등록된 회원이 없습니다.</b></td>
				</tr>
			</c:when>
			<%-- 조회된 정보가 저장된 ArrayList 배열이 request에 저장되어 있으면? --%>
			<c:otherwise>
				<c:forEach var="data" items="${requestScope.list}">
					<tr align="center">
						<td width="7%">${data.id }</td>
						<td width="7%">${data.pwd }</td>
						<td width="7%">${data.name }</td>
						<td width="7%">${data.email }</td>
						<td width="7%">${data.joinDate }</td>
					</tr>	
				</c:forEach>
			</c:otherwise>
		</c:choose>
	
	</table>


</body>
</html>