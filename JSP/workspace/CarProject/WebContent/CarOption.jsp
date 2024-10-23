<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>옵션을 추가로 선택하는 화면 view</title>
</head>
<body>
	<center>
		<%-- <옵션 선택> 이미지 --%>
		<img src="<%=contextPath%>/img/option.jpg" border="0">
		
		<%-- 옵션을 추가로 선택하고 예약하기 버튼을 눌렀을때 결제 금액 계산 요청! --%>
		<form action="<%=contextPath%>/Car/CarOptionResult.do" method="post">
			
			<table width="1000">
				<tr align="center">
					<td rowspan="7" width="600">
						<img src="<%=contextPath%>/img/${param.carimg}" width="500">
					</td>
					<td align="center" width="200">대여 기간</td>
					<td align="center" width="200">
						<select name="carreserveday">
							<option value="1">1일</option>
							<option value="2">2일</option>
							<option value="3">3일</option>
							<option value="4">4일</option>
							<option value="5">5일</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" width="200">대여일</td>
					<td align="center" width="200"><input type="date" name="carbegindate"></td>
				</tr>
				<tr>
					<td align="center" width="200">보험적용</td>
					<td align="center" width="200">
						<select name="carins">
							<option value="1">보험적용(1일 1만원)</option>
							<option value="0">미적용</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" width="200">무선WIFI</td>
					<td align="center" width="200">
						<select name="carwifi">
							<option value="1">적용(1일 1만원)</option>
							<option value="0">미적용</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" width="200">네비게이션</td>
					<td align="center" width="200">
						<select name="carnave">
							<option value="1">적용(무료)</option>
							<option value="0">미적용</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" width="200">베이비시트</td>
					<td align="center" width="200">
						<select name="carbabyseat">
							<option value="1">적용(1일 1만원)</option>
							<option value="0">미적용</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" width="200">
						<%--옵션 선택 페이지로 예약할 차번호, 대여수량, 대여금액 전달 --%>
						<input type="hidden" name="carno" value="${param.carno }">
						<input type="hidden" name="carqty" value="${param.carqty }">
						<input type="hidden" name="carprice" value="${param.carprice }">
						<input type="button" value="차량검색" 
						onclick="location.href='<%=contextPath%>/Car/CarList.do'">
					</td>
					<td align="center" width="200">
						<input type="submit" value="예약정보계산">
					</td>
				</tr>
			</table>
		
		</form>
	</center>
</body>
</html>