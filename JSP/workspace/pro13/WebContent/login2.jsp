<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//상황1. 최초로 login2.jsp를 요청하면  request영역에는  null이 저장되어 있으므로 
//      판단해서   경고메세지를 보여주지 말자

//상황2. login2.jsp화면에서 아이디를 입력하지 않고 로그인버튼을 눌러  
//      result2.jsp로 요청해서 갔다가 다시 login2.jsp를 재요청해서 오는상황
//      이상황에서는 <jsp:param name="msg" value="경고메세지변수명msg" /> 
//      액션태그에 설정한 경고메세값을 request에 꺼내서 사용		

		String msg = request.getParameter("msg");
              //"아이디를 입력하지 않았습니다. 아이디를 입력해 주세요" <- 상황2.
              //또는
              //null  <- 상황1.
        
        if(msg != null){//경고메세지가 저장되어 있을때만? 경고메세지를 보여주자
        	out.print("<h1>" + msg + "</h1>");
        }
%>
	
	<%-- 로그인 요청 화면에서 ID와 비밀번호를 입력한후 
	     <form>태그의 action속성에 로그인요청할 result2.jsp주소를 작성해서 요청 --%>
	<form action="result2.jsp" method="post">
		아이디 : <input type="text" name="userID"><br>
		비밀번호: <input type="password" name="userPW"><br>
		
		<%-- 전송 요청 버튼으로 만들수 있는 경우 여러가지 --%>
		<input type="submit" value="로그인">		
		<!--   <button>로그인</button> -->
		<!--   <input type="image" src="s.png"> -->		
		
		<input type="reset" value="다시입력">
	</form>






</body>
</html>








