<%@page import="java.util.List"%>

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
<title>CarList.jsp 조회된 차량 정보를 보여주는 VIEW</title>
</head>
<body>
	<center>
		<img src="${contextPath }/img/cis.jpg">	
		
		<form action="${contextPath }/Car/carcategory.do">
			<table width="1000" height="470">
				<c:set var="j" value="0" />
<%--
	CarController로 부터 재요청 받은 request 영역에서 Vector 배열을 꺼내와
	Vector 배열에 저장된 CarListVo 객체의 갯수만큼 반복해서 얻어 정보를 출력
 --%>			
 				<c:forEach var="vo" items="${requestScope.v}">
 					<%-- 한 행에 4열씩 보여주기 위해서 --%>
					<c:if test="${j % 4 == 0 }">
						<tr align="center">
					</c:if> 
							<td>
								<a href="${contextPath }/Car/CarInfo.do?carno=${vo.carno }">
									<img src="${contextPath }/img/${vo.carimg}" width="220" height="180"><br>
									차량명 : ${vo.carname } <br>
									한 대당 금액 : ${vo.carprice } <br>
								</a>
							</td>
					<c:set var="j" value="${j + 1}"/>					
 				</c:forEach>
 						</tr>
 						
 						<tr height="70">
 							<td colspan="4" align="center">
 								차량 검색 : 
 								<select name="carcategory">
									<option value="Small">소형</option>
									<option value="Mid">중형</option>
									<option value="Big">대형</option>
 								</select>
 								&nbsp;&nbsp;&nbsp;
 								<input type="submit" value="검색">
 							</td>
 						</tr>
			</table>
		</form>
	</center>
	

</body>
</html>