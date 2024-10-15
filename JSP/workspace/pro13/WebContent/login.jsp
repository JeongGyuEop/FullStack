<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	request.setCharacterEncoding("utf-8");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>아이디를 입력하지 않았습니다. 아이디를 입력해 주세요.</h1>
	
	<%-- 로그인 요청 화면에서 ID와 비밀번호를 입력한 후 <form> 태그의
		action 속성에 로그인 요청할 result.jsp 주소를 작성해서 요청 --%>
	<form action="result.jsp" method="post">
		아이디: <input type="text" name="userID"><br>
		비밀번호: <input type="password" name="userPW"><br>
		
		<%-- 전송 요청 버튼으로 만들 수 있는 경우 여러가지 --%>
		<input type="submit" value="로그인">
		<!-- <button>로그인</button>  -->
		<!-- <input type="image" src="s.png"> -->
		
		<input type="reset" value="다시입력">
		
	</form>

</body>
</html>