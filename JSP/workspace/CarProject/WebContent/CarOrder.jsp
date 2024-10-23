<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<% 
	request.setCharacterEncoding("UTF-8"); 
	String contextPath = request.getContextPath();
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제를 하기 위해 계산된 총 금액을 보여주는 VIEW</title>
</head>
<body>
	<form action="<%=contextPath %>/Car/CarOrder.do" method="post" >
	
		<%-- 예약할 정보들을 input type="hidden"으로 전달 --%>
		<input type="hidden" name="carno" value="${vo.carno }" />
		<input type="hidden" name="carqty" value="${vo.carqty }" />
		<input type="hidden" name="carreserveday" value="${vo.carreserveday }" />
		<input type="hidden" name="carbegindate" value="${vo.carbegindate }" />
		<input type="hidden" name="carins" value="${vo.carins }" />
		<input type="hidden" name="carwifi" value="${vo.carwifi }" />
		<input type="hidden" name="carnave" value="${vo.carnave }" />
		<input type="hidden" name="carbabyseat" value="${vo.carbabyseat }" />
		
		<div align="center">
			<%-- <결제하기> 이미지 --%>
			<img src="<%=contextPath %>/img/haki.jpg">
			
			<p>
				<font size="13" color="blue">
					차량 기본금액 비용 : ￦${requestScope.totalreserve }원
				</font>
			</p>
			<p>
				<font size="13" color="blue">
					차량 옵션 비용 : ￦${requestScope.totaloption }원
				</font>
			</p>
			<p>
				<font size="13" color="blue">
					총 비용 : ￦${totaloption + totalreserve }원
				</font>
			</p>
			<p>
				비회원 전화 번호 입력 : <input type="text" name="memberphone">
				&nbsp;&nbsp;&nbsp;
				비회원 비밀번호 입력 : <input type="password" name="memberpass">
				<input type="submit" value="결제 후 예약요청">
	 	</div>
 	</form>
</body>
</html>