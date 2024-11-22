<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 
		http://localhost:8090/pro26/test/loginForm.do 주소를 입력하여
		요청하면 아래의 디자인 화면이 웹 브라우저 화면에 나타난다.
		
		아래의 디자인화면에서 아디ㅣ와 이름을 입력받고 로그인 버튼을
		누르면 /test/login.do 주소로 디스패처 서블릿에서 요청 후
		LoginController 컨트롤러의 메소드가 호출되어 실행된다.
	 --%>
	 
	 <%-- <form action="${pageContext.request.contextPath }/test/login.do" method="post"> --%> <%-- 기본 로그인 요청--%>
	 <%-- <form action="${pageContext.request.contextPath }/test/login2.do" method="post"> --%> <%-- @RequestParam 사용 --%>
	 <%-- <form action="${pageContext.request.contextPath }/test/login3.do" method="post"> --%> <%-- @RequestParam에 Map<> 사용 --%>
	 <form action="${pageContext.request.contextPath }/test/login4.do" method="post"> <%-- @ModelAttribute이용해서 VO 매개변수 사용 --%>
	 	
	 	<!-- <input type="hidden" name="email" value="hong@test.com"> -->
	 
	 	<table width="400">
	 		<tr>
	 			<td>아이디<input size="10" name="userID"></td>
	 		</tr>
	 		<tr>
	 			<td>이름<input size="10" name="userName"></td>
	 		</tr>
	 		<tr>
	 			<td colspan="2">
	 				<input type="submit" value="로그인" >
	 				<input type="reset" value="다시입력" >	 				
	 			</td>
	 		</tr>
	 	</table>
	 </form>

</body>
</html>