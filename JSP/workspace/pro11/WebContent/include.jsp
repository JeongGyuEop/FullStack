<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>안녕하세요. 쇼핑몰 상단 영역입니다.</h1>


  <%-- include디렉티브 태그를 이용해 duke_image.jsp에 작성한 코드 내용을 불러와 
             현재 행의 위치에 포함시킴 --%>
   
  <%--  <%@ include file="포함시킬 코드가 작성된 JSP의 경로" %> --%>
		<%@ include file="duke_image.jsp" %>

		<%-- 
			include 디렉티브 태그를 이용해 duke_image.jsp페이지에서 불러와 포함시킨 코드
		     <img  src="./image/duke.png"/>
		--%>

	<h1>안녕하세요. 쇼핑몰 하단 영역입니다.</h1>

</body>
</html>













