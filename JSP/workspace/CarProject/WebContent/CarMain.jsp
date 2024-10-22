<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<% request.setCharacterEncoding("UTF-8"); %>

<%-- 
	센터 중앙 화면 공간은 상위 메뉴(Top.jsp)를 클릭할 때마다 계속 변화되어 나타나기
	때문에 request 내장 객체 영역으로 부터 중앙화면 공간의 VIEW 주소를 얻어와 변수에 저장
 --%>
 <c:set var="center" value="${requestScope.center }" />
 
 <%-- 처음으로 CarMain.jsp를 요청했을 때 중앙화면은 Center.jsp로 보이게 설정 --%>
 <c:if test="${center == null }">
 	<c:set var="center" value="Center.jsp" />
 </c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<table width="1000" height="700">
			<tr align="center">
				<td ><jsp:include page="Top.jsp" /></td>
			</tr>
			<tr>
				<td height="500"><jsp:include page="${center}" /></td>
			</tr>
			<tr>
				<td ><jsp:include page="Bottom.jsp" /></td>
			</tr>
		</table>
	</center>
</body>
</html>