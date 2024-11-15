<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



	<%--아이디와 비밀번호를 입력받아 로그인 요청 할수 있는 VIEW화면 --%>
	<form action="<%=request.getContextPath()%>/test/login.do" method="post">
	
		아이디 입력 : <input type="text" name="userID"> <br>
		비밀번호 입력 : <input type="password" name="passwd"> <br>
		<input type="submit" value="login">
		
	</form>	


</body>
</html>











