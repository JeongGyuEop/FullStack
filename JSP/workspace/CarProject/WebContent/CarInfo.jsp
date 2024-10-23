<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<% request.setCharacterEncoding("UTF-8"); %>

<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약할 차량 한대의 정보 조회 후 정보를 보여주는 VIEW</title>
</head>
<body>
	
	<center>
		<%-- <차량 정보 보기> 이미지 --%>
		<img src="${contextPath }/img/cis.jpg" />
		
		<%-- 조회된 차량 정보를 화면에서 보고 대여수량을 선택해 옵션을 추가로 선택하는 화면 요청 --%>
		<form action="${contextPath }/Car/CarOption.do" method="post">
		
			<%-- 옵션 선택하는 페이지 요청시 조회된 예약할 
			차량 번호, 이미지명, 대여금액을 같이 전달한다. --%>
			<input type="hidden" name="carno" value="${vo.carno }">
			<input type="hidden" name="carimg" value="${vo.carimg }">
			<input type="hidden" name="carprice" value="${vo.carprice }">
		
			<table width="1000">
				<tr align="center">
					<td rowspan="6" width="600">
						<img src="${contextPath }/img/${requestScope.vo.carimg}" width="500">
					</td>
					<td align="center" width="200">차량 이름</td>
					<td align="center" width="200">${vo.carname}</td>
				</tr>
				<tr>
					<td align="center" width="200">대여수량</td>
					<td align="center" width="200">
						<select name="carqty">
							<option value="1">1대</option>
							<option value="2">2대</option>
							<option value="3">3대</option>
							<option value="4">4대</option>
							<option value="5">5대</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" width="200">차량 분류</td>
					<td align="center" width="200">${vo.carcategory }</td>
				</tr>
				<tr>
					<td align="center" width="200">대여 금액</td>
					<td align="center" width="200">${vo.carprice }</td>
				</tr>
				<tr>
					<td align="center" width="200">제조회사 명</td>
					<td align="center" width="200">${vo.carcompany }</td>
				</tr>
				<tr>
					<td align="center" width="200">
						<input type="button" value="이전화면"
								onclick="location.href='${contextPath}/Car/CarList.do'">
					</td>
					<td align="center" width="200">
						<input type="submit" value="옵션 선택하기">
					</td>
				</tr>
			</table>
		</form>
		<p>
			<b>차량 정보 상세 내용 : </b>
			${requestScope.vo.carinfo}
		</p>
	</center>

</body>
</html>