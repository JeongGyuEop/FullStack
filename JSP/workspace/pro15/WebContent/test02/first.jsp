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
	<%--
		first.jsp페이지에서는 다운로드할 이미지 파일 이름을  hidden태그에 넣어
		result.jsp로 전송합니다.
	 --%>
	<form action="result.jsp" method="post">
		<input type="hidden" name="param1" value="duke.png"> <br>
		<input type="hidden" name="param2" value="duke2.png"> <br> 
		<input type="submit" value="이미지 다운로드">
	</form>


</body>
</html>












