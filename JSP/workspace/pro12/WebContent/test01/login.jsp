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
		login.jsp화면에서 아이디,비밀번호를 입력한후 서버페이지인?
		login.jsp로 로그인 요청시 입력한 아이디,비밀번호를 request객체에 담아 전달
	 -->
	<form action="login.jsp" method="post">
		ID : <input type="text" name="user_id"> <br>
		PW : <input type="password" name="user_pw"> <br>
		<input type="submit" value="로그인">
		<input type="reset" value="취소">
	</form>
	
	<%
		//<form>태그의 요청전송방식이 "post"이면? 조건
		if(request.getMethod().equals("POST")   ){
		   //	        "POST".equals("POST")
			
		   //1. 한글처리
		   request.setCharacterEncoding("UTF-8");
		   
		   //2. 로그인 요청시 입력한 요청한 아이디,비밀번호 얻기
		   String id = request.getParameter("user_id");
		   String pw = request.getParameter("user_pw");
		   
		   //참고. DB에 테이블에 저장되어 있는 아이디 : "master"와 
		   //                          비밀번호 : "1111" 이 저장되어있다고
		   //                          가정 하고 실습하자.
		   
		   //3. 요청한 데이터를 이용해 DB에서 조회한 아이디,비밀번호와 비교해서
		   //   모두 같으면? 로그인 처리 
		   //   하나라도 틀리면 미로그인 처리 
		   //요약 : 요청한 데이터를 이용해서 웹브라우저에 응답할값 마련후 응답
		   
		   //DB에 저장되어 있는 조회한 "master"아이디와
		   //login.jsp화면에서 로그인 요청시 입력한 아이디가 같은지 비교
		   if("master".equals(id)){ //아이디 같고
			   
			   if("1111".equals(pw)){ //아이디,비밀번호 모두 같으면? 
				   
				   //로그인 된 화면을 보여 주기 위해 
				   //session내장객체 메모리에 입력한 아이디 저장
				   session.setAttribute("id", id);
			   
			   	   //메인 홈페이지 화면(index.jsp)을 재요청(포워딩)
			   	   //참고. 리다이렉트 방법으로 포워딩
			   	   response.sendRedirect("index.jsp");
				   
				   
			   }else{//아이디는 같으나 비밀번호가 다르면?				   
	  %>			   
					<script type="text/javascript">
						window.alert("비밀번호 틀립니다.");
					</script>
	  <%				   
			   }
		   }else{//DB "master"아이디와 입력한 아이디가 다르면
			   
			   //DB "1111"비밀번호와 입력한 비밀번호가 같으면?
			   if("1111".equals(pw)){
	   %>
	   				<script type="text/javascript">
	   					window.alert("아이디만  틀립니다.")
	   					//location.href = "login.jsp";
	   				</script>
	   <%			   		   
			   }else{//아이디,비밀번호 모두 다르면?
	   %>				   
					<script type="text/javascript">
						window.alert("아이디,비밀번호 둘다 틀립니다.");
					</script>			   
	   <%			   
			   }//안쪽 else
		   }//바깥 else	   
		   
		}//가장 바깥쪾 if
	%>





</body>
</html>





