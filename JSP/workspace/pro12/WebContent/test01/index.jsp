<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome to myhompage Main</h1>
	<%
		
		//1. loing.jsp페이지에서 넘겨 받은 session내장객체 메모리에 접근하여
		//   바인딩된 id값을 얻습니다.
		String new_id	= (String)session.getAttribute("id");
	
		//2. session내장객체 메모리영역에 입력한 아이디가 저장되어 있으면?
		//   로그인 된 디자인 화면을 보여주고
		//   session내장객체 메모리 영역에 입력한 아이디가 저장되어 있지않으면?
		//   미로그인된(로그아웃)된 디자인 화면을 보여주가
		
		//2.1 session내장객체 메모리영역에 입력한 아이디가 저장되어 있으면?
		if(new_id != null){
	%>		
			<%=new_id%> 로그인중... &nbsp;&nbsp;
			<a href="logout.jsp">logout</a>	
			<a href="shop.jsp">쇼핑몰</a><!-- 로그인된 상태에서 서브페이지 요청 -->
	<%		
		}else{//2.2 session내장객체 메모리영역에 입력한 아이디가 저장되어 있지 않으면?
	%>			
			<a href="login.jsp">login</a> <!-- 로그인 요청을 위한 아이디,비밀번호를 입력하는 화면요청-->		
			<a href="shop.jsp">쇼핑몰</a><!-- 미로그인된 상태에서 서브페이지 요청 -->
	<%		
		}
	%>







</body>
</html>



