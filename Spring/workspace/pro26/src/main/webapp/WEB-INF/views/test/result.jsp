<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 
		LoginController에서 ModelAndView에 바인딩 했던 정보를 꺼내서 뷰에 보여줍니다.
	
		${key}
		${mapkey.key}
	 -->
	<%-- <h1>입력한 아이디 : ${userID}</h1> --%>
	<h1>입력한 아이디 : ${info.userID}</h1>
	
	<%-- <h1>입력한 이름 : ${userName}</h1> --%>
	<h1>입력한 이름 : ${info.userName}</h1>
	
</body>
</html>