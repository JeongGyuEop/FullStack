<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 메인 로고 이미지 -->
	<a href="#"><img src="img/RENT.jpg" alt="left" width="300" height="80" border="0"></a> 
	
	<!-- 메뉴 만들기 -->
	<table width="1000" background="img/aa.jpg" height="5">
		<tr>
			<!-- 예약하기 메뉴 -->
			<td bgcolor="red" align="center">
				<a href="CarMain.jsp?center=CarReservation.jsp">
					<img alt="" src="img/bb.jpg" border="0">
				</a>
			</td>
			<!-- 예약확인 메뉴 -->
			<td bgcolor="red" align="center">
				<a href="CarMain.jsp?center=CarReserveConfirm.jsp">
					<img alt="" src="img/cc.jpg" border="0">
				</a>
			</td>
			<!-- 자유게시판 컨트롤러 요청 -->
			<td bgcolor="red" align="center">
				<a href="CarMain.jsp?center=BoardListController.jsp">
					<img alt="" src="img/dd.jpg" border="0">
				</a>
			</td>
			<!-- 이벤트 메뉴 -->
			<td bgcolor="red" align="center">
				<a href="CarMain.jsp?center=CarEvent.jsp">
					<img alt="" src="img/even.jpg" border="0">
				</a>
			</td>
			<!-- 공지사항 게시판 컨트롤러 요청 -->
			<td bgcolor="red" align="center">
				<a href="CarMain.jsp?center=adminBoardListController.do">
					<img alt="" src="img/ee.jpg" border="0">
				</a>
			</td>
		</tr>
	</table>
	
	
	
	

</body>
</html>