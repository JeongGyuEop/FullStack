<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// 순서 1. 요청한 값 문자처리 방식 utf-8로 설정
	// 요약 : 요청한 값 얻기
	request.setCharacterEncoding("utf-8");

	// 순서 2. 입력하여 요청한 시험 점수를 request객체 메모리에서 얻기
	// 요약 : 요청한 값 얻기
	int score = Integer.parseInt(request.getParameter("score"));
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>점수 출력창</title>
</head>
<body>
<%-- 순서 3. 요청한 점수를 이용해서 조건문에 따라 응답할 값 마련 후 클라이언트의 웹 브라우저로 응답! --%>
	<h1>시험점수 <%=score %>점</h1><br>
	<% if(score>=90) { %>
	
			<h1>A학점입니다.</h1>
			
	<% } else if(score>=80 && score<90) { %>
	
			<h1>B학점입니다.</h1>
			
	<% } else if(score>=70 && score<80) { %>
	
			<h1>C학점입니다.</h1>
			
	<% } else if(score>=60 && score<70) { %>
	
			<h1>D학점입니다.</h1>
			
	<% } else { %>
	
			<h1>F학점입니다.</h1>
			
	<% } %>
	  
	<br>
	<a href="scoreTest.html">시험점수입력</a>
</body>
</html>