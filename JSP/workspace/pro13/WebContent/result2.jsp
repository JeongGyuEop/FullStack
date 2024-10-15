<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
		//login2.jsp화면에서 아이디를 입력받지 않고
		//로그인 버튼을 누르면 재요청에 의해 login2.jsp에 보여질 경고메세지 변수에 저장
		String msg = "아이디를 입력하지 않았습니다. 아이디를 입력해 주세요";
	%>


	<%
		//1.한글처리(request객체 메모리영역에 요청한 데이터중에서
				// 한글문자가 하나라도 존재하면 꺼내올때 깨진채로 꺼내와지므로
				// request객체 메모리에 인코딩방식 UTF-8로 설정)
		request.setCharacterEncoding("UTF-8");
	
		//2. 요청한 값 얻기 (입력받은 아이디 얻기)
		String userID = request.getParameter("userID");
		
		//3. 요청한 값을 조건식에 작성하여 판단후 웹브라우저로 응답
		//조건 : 아이디를 입력하지 않았다면?
		if(userID.length() == 0){
	%>		
			<%-- 아이디 입력하지 않았으면 다시 login2.jsp 재요청(포워딩)
			     forward액션태그 사용함 --%>	
			<jsp:forward page="login2.jsp">
				
				<jsp:param  name="msg" value="<%=msg%>" />
			
			</jsp:forward>
	<%		
		}
	%>
	
	
	<h1>환영합니다.<%=userID%>님!!</h1>
	
</body>
</html>







