<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		// 1. 한글처리 (request 객체 메모리 영역에 요청한 데이터 중에서)
		//    한글문자가 하나라도 있으면 꺼내올 대 깨진채로 꺼내와 지므로
		//    request 객체 메모리에 인코딩 방식 설정)
		request.setCharacterEncoding("utf-8");
		
		// 2. 요청한 데이터 얻기
		String userID = request.getParameter("userID");
		
		// 3. 요청한 값을 조건식에 작성하여 판단 후 웹 브라우저로 응답
		//     조건 : 아이디를 입력하지 않았다면?
		if(userID.length()==0) {
			/*
				RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
				dispatch.forward(request, response);
			*/
	%>
		<%-- 아이디 입력하지 않았으면 다시 login.jsp 재요청(포워딩)
			forward 액션태그 사용한다. --%>
		<jsp:forward page="login.jsp"/>
	<%
		}
	
	%>
	<h1>환영합니다 <%=userID %>님!!</h1>
</body>
</html>