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

	<!-- 빨간 라인 부분 -->
	<hr width="1000" size="3" color="red">
	
	<!--  아래 로고 이미지 부분 -->
	<a href="#">
		<img alt="" src="img/bo.jpg" width="500" height="50" border="0">
	</a>
	<font size="2">
		<b>
			<!-- "회사 소개" 이미지 링크를 눌렷을 때 .. 회사 소개 페이지로 이동 -->
			<a href="CarMain.jsp?center=Company.jsp">
				<img alt="" src="img/sodog.jpg" border="0">
			</a>
			<!-- "개인정보 취급방침" 이미지 링크를 눌렸을 때 -->
			<a href="CarMain.jsp?center=Info.jsp">
				<img alt="" src="img/info.jpg" border="0"> | 사이버신문고 | 이용약관 | 인재채용
			</a>
		</b>
		<br><br>
		<!-- 글씨 작게 -->
		<small>
			(주) SM렌탈 사업자 등록번호 214-98754-9874 통신 판매업신고 번호 : 제 2010-충남-05호<br>
			
			서울시 강남구 역삼동 역삼빌딩 2층 21호 <br><br>
			
			대표전화 : 02-3546-6547 <br>
			FAX : 01-3254-9874
		</small>
	</font>
</body>
</html>