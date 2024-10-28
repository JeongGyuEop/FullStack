<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<center>
	    <div class="container">
	 
	    <%--MemberController서블릿에.. 로그인 처리 요청시! 입력한 id와 패스워드 전달 --%>   
	      <form class="form-signin" action="<%=request.getContextPath()%>/member/loginPro.me" id="join">    
	        <h2 class="form-signin-heading">로그인 화면</h2>
	        	<label class="sr-only">아이디</label>
	        		<input type="text" id="id" name="id" class="form-control"  placeholder="아이디" required autofocus>
	        	<label for="inputPassword" class="sr-only">비밀번호</label>
	        		<input type="password" id="pass" name="pass" class="form-control" placeholder="패스워드" required>
		        <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>        
	      </form>
	      
	    </div> 
	</center>
</body>
</html>