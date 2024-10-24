<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	// 인코딩 설정
	request.setCharacterEncoding("utf-8");
	
	// contextPath 설정
// 	String contextPath = request.getContextPath();
%>

<c:set var = "contextPath" value = "${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<img src="${contextPath}/img/naeyeok.jpg">
		<br><br>
		
		<table width="1000" border="1" align="center">
			<tr align="center">
				<td align="center" width="150">차량이미지</td>
				<td align="center" width="100">차량명</td>
				<td align="center" width="100">대여일</td>
				<td align="center" width="50">대여기간</td>
				<td align="center" width="100">한대 가격</td>
				<td align="center" width="70">보험</td>
				<td align="center" width="70">와이파이</td>
				<td align="center" width="70">네비게이션</td>
				<td align="center" width="70">베이비시트</td>
				<td align="center" width="100">예약수정</td>
				<td align="center" width="100">예약취소</td>
			</tr>
<%-- 
	request 내장 객체에 저장된 vector의 정보가 비어있으면? (CarConfirmVo 객체들이 없으면? 
	요약 : 조회된 예약 정보가 없으면?
--%>
<c:if test="${empty requestScope.v}">
			<tr align="center">
				<td colspan="11">예약한 정보가 없습니다.</td>
			</tr>
</c:if>
<c:if test="${not empty requestScope.v }">
	<c:forEach var="vo" items="${requestScope.v}">
			<tr align="center">
				<td align="center" width="150">
					<img src="${contextPath }/img/${vo.carimg }" width="140" height="90" />
				</td>
				<td align="center" width="100">${vo.carname }</td>
				<td align="center" width="100">${vo.carbegindate }</td>
				<td align="center" width="50">${vo.carreserveday }</td>
				<td align="center" width="100">${vo.carprice }</td>
				<td align="center" width="70">
					<c:if test="${vo.carins == 1 }"> 적용</c:if>
					<c:if test="${vo.carins == 0 }"> 미적용</c:if>
				</td>
				<td align="center" width="70">
					<c:if test="${vo.carwifi == 1 }"> 적용</c:if>
					<c:if test="${vo.carwifi == 0 }"> 미적용</c:if>
				</td>
				<td align="center" width="70">
					<c:if test="${vo.carnave == 1 }"> 적용</c:if>
					<c:if test="${vo.carnave == 0 }"> 미적용</c:if>
				</td>
				<td align="center" width="70">
					<c:if test="${vo.carbabyseat == 1 }"> 적용</c:if>
					<c:if test="${vo.carbabyseat == 0 }"> 미적용</c:if>
				</td>
				<td align="center" width="100">
					<a href="${contextPath }/Car/update.do?orderid=${vo.orderid}&
															carimg=${vo.carimg}&
															memberpass=${requestScope.memberpass}&
															memberphone=${requestScope.memberphone}">
						예약수정
					</a>
				
				<!-- 이렇게 전송하는 방법도 있음!! 
					<form action="${contextPath}/Car/update.do" method="post">
					    <input type="hidden" name="orderid" value="${vo.orderid}">
					    <input type="hidden" name="carimg" value="${vo.carimg}">
					    <input type="hidden" name="memberpass" value="${requestScope.memberpass}">
					    <input type="hidden" name="memberphone" value="${requestScope.memberphone}">
					    <button type="submit">예약수정</button>
					</form>
				 -->					
					
				</td>
				<td align="center" width="100">
					<a href="${contextPath}/Car/delete.do?orderid=${vo.orderid}&memberphone=${requestScope.memberphone}&center=Delete.jsp">
						예약취소
					</a>
				</td>
				
			</tr>
	</c:forEach>
</c:if>

		</table>
	</center>
</body>
</html>

