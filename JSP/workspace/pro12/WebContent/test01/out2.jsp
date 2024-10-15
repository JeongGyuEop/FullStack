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
		//1. 요청한 한글데이터 문자처리 방식 UTF-8로 설정
		request.setCharacterEncoding("UTF-8");
	
		//2. 요청한 데이터 request객체 메모리로 부터 얻기
		String name = request.getParameter("name");
		String age = request.getParameter("age");
	
		//3. 조건에 따라 웹브라우저에 응답할 디자인 출력
		
		if(name!= null || name.length() != 0){//이름을 입력하고 요청했다면?
	%>		
			<h1><%=name%>, <%=age%></h1>
	<%			
		}else{//이름을 입력하지 않고 요청 받았다면?
	%>		
			<h1>이름을 입력하세요</h1>		
	<%	
		}
	%>
	
	<%-- ---------------------------------------------------- --%>

	<%
		if(name != null || name.length() != 0){
			
			out.println("<h1>"+name+","+age +"</h1>");
		
		}else{
			
			out.println("<h1>이름을 입력하세요</h1>");
		}
	%>



</body>
</html>





