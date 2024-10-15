<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome to Shop</h1>
<%
//index.jsp 미로그인된 메인화면에서
//<a href="shop.jsp">쇼핑몰</a>를 클릭하거나
//또는
//index.jsp 로그인된 메인화면에서
//<a href="shop.jsp">쇼핑몰</a>를 클릭하여 요청받는 서브 페이지

//1. session객체 메모리 영역에 저장된 데이터 꺼내오기
String new_id = (String)session.getAttribute("id");

//2. session객체 메모리 영역에 로그인시 요청한 "master"아이디가 저장되어 있으면?
//   로그인된 쇼핑몰 화면 디자인을 보여주고
if(new_id != null){
%>
	<%=new_id%> 로그인중... &nbsp;&nbsp;
	<a href="logout.jsp">logout</a>	
	<a href="index.jsp">main</a>
<%	
}else{
//   그렇지 않으면  로그인이 안된 상태이니 로그인 하고 오세요 경고메세지창과 함께
//   login.jsp를 재요청해서 보여줍니다.
%>	
	 <script type="text/javascript">
	 	alert("로그인이 안된 상태이니 로그인하고 오세요~");
	 	alert("로그인 요청 화면으로 이동됩니다.....");
	 	location.href="login.jsp";
	 </script>
<%
}
%>
	


</body>
</html>














