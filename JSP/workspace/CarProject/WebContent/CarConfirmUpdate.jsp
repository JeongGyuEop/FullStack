<%@page import="Vo.CarConfirmVo"%>

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
<title>수정 요청하는 화면 view</title>
</head>
<body> 
	<center>
		<h1> 예약 정보 수정 화면 </h1>
		
<%
	//바인딩된 조회된 예약한 정보들이 저장된 CarConfirmVo객체를 request내장객체 영역에서 꺼내오기
	CarConfirmVo vo = (CarConfirmVo)request.getAttribute("vo");
	
	int carqty = vo.getCarqty(); //대여수량
	int orderid = vo.getOrderid(); //예약한 아이디
	String carimg = vo.getCarimg(); //예약한 차량 이미지
	String carbegindate = vo.getCarbegindate();	//대여시작날짜
	int carreserveday = vo.getCarreserveday(); //대여기간
	int carins = vo.getCarins(); //보험적용여부
	int carwifi = vo.getCarwifi(); //wifi적용여부
	int carnave = vo.getCarnave(); //네비적용여부
	int carbabyseat = vo.getCarbabyseat(); //베이비시트적용여부

%>		
		
		<%-- 예약 정보를 수정 요청! --%>
		<form action="<%=contextPath%>/Car/updatePro.do?memberphone=${requestScope.memberphone}" method="post">
			
			<input type="hidden" name="orderid" value="<%=orderid %>">
			<input type="hidden" name="carimg" value="<%=carimg %>">
			<table width="1000">
				<tr align="center">
					<td rowspan="7" width="600">
						<img src="<%=contextPath%>/img/<%=carimg %>" width="500">
					</td>
					<td align="center" width="200">대여 기간</td>
					<td align="center" width="200">
						<select name="carreserveday">
							<option value="1" <%if(carreserveday==1){%> selected<%} %>>1일</option>
							<option value="2" <%if(carreserveday==2){%> selected<%} %>>2일</option>
							<option value="3" <%if(carreserveday==3){%> selected<%} %>>3일</option>
							<option value="4" <%if(carreserveday==4){%> selected<%} %>>4일</option>
							<option value="5" <%if(carreserveday==5){%> selected<%} %>>5일</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" width="200">대여수량</td>
					<td align="center" width="200">
						<select name="carqty">
							<option value="1" <%if(carqty==1){%> selected<%} %>>1대</option>
							<option value="2" <%if(carqty==2){%> selected<%} %>>2대</option>
							<option value="3" <%if(carqty==3){%> selected<%} %>>3대</option>
							<option value="4" <%if(carqty==4){%> selected<%} %>>4대</option>
							<option value="5" <%if(carqty==5){%> selected<%} %>>5대</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" width="200">대여일</td>
					<td align="center" width="200"><input type="date" name="carbegindate" value="<%=carbegindate%>"></td>
				</tr>
				<tr>
					<td align="center" width="200">보험적용</td>
					<td align="center" width="200">
						<select name="carins">
							<option value="1" <%if(carins==1){%> selected<%} %>>보험적용(1일 1만원)</option>
							<option value="0" <%if(carins==0){%> selected<%} %>>미적용</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" width="200">무선WIFI</td>
					<td align="center" width="200">
						<select name="carwifi">
							<option value="1" <%if(carwifi==1){%> selected<%} %>>적용(1일 1만원)</option>
							<option value="0" <%if(carwifi==0){%> selected<%} %>>미적용</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" width="200">네비게이션</td>
					<td align="center" width="200">
						<select name="carnave">
							<option value="1" <%if(carnave==1){%> selected<%} %>>적용(무료)</option>
							<option value="0" <%if(carnave==0){%> selected<%} %>>미적용</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" width="200">베이비시트</td>
					<td align="center" width="200">
						<select name="carbabyseat">
							<option value="1" <%if(carbabyseat==1){%> selected<%} %>>적용(1일 1만원)</option>
							<option value="0" <%if(carbabyseat==0){%> selected<%} %>>미적용</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" width="200" colspan="2">
					
						비밀번호 확인 : <input type="password" name="memberpass" size="10">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" value="예약정보 수정">
					</td>
				</tr>
			</table>
		
		</form>
	</center>
</body>
</html>