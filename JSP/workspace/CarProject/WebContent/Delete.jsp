<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<% request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="text-align:center; background-color:pink">
		
		<h1>예약 취소를 위해 비밀번호를 입력해서 예약 취소요청하는 VIEW</h1>
		
		<br><br><br>
		
		<form action="${contextPath }/Car/deletePro.do" method="post">
				
				<input type="hidden" name="orderid" value="${param.orderid }">
				<input type="hidden" name="memberphone" value="${param.memberphone }">
			
				<table width="100%">
					
					<tr align="center">
						<td align="center">
							비밀번호 입력 : 
							<input type="password" name="memberpass">
							<input type="submit" value="예약 취소">
						</td>
					</tr>
	
				</table>
		
		</form>
		
	</div>
</body>
</html>