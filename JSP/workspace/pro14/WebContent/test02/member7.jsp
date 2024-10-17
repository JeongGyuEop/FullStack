<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//   입력한 가입 정보중 한글이 하나라도 존재하면 한글이 깨져서 받아와 지므로
//   문자처리 방식 UTF-8방식으로 request객체 메모리에 설정
	request.setCharacterEncoding("UTF-8");

//순서4. 포워딩(재요청) 받은 member7.jsp화면 입니다.
//		각 내장객체 메모리 영역에 바인딩되어 있는 데이터를 꺼내와서 변수에 저장	
	String id = (String)request.getAttribute("id"); //"hong"
	String pwd = (String)request.getAttribute("pwd"); //"1234"
	
	String name = (String)session.getAttribute("name");//"홍길동"
	
	String email = (String)application.getAttribute("email");//"hong@test.com"
%>    



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align="center" width="100%">
		<tr align="center" bgcolor="green">
			<td width="7%">아이디</td>
			<td width="7%">비밀번호</td>
			<td width="5%">이름</td>
			<td width="11%">이메일</td>
		</tr>
<%-- 회원정보를 표현식 태그 영역으로 출력 --%>
		<tr>
			<td><%=id%></td>
			<td><%=pwd%></td>
			<td><%=name%></td>
			<td><%=email%></td>		
		</tr>
<%-- 자바코드 없이 각 내장객체 영역에 접근하여 바인딩된 속성이름으로 가져온값을 EL로 출력 

request.setAttribute("id","hong"); 

<- 이렇게 바인딩한 정보 "hong"을 얻기 위해서는
   EL문법에서 제공해주는 requestScope내장객체를 통해 request영역에 접근하고
      바인딩시 "hong"이라는 값과 함께 바인딩된 "id"속성명을 작성해주면
   "id"속성명과 같이 저장된  "hong"값을 불러와서 바로~ EL ${  }로 출력가능함 

session.setAttribute("name", "홍길동");


application.setAttribute("email","hong@test.com");   
	
				${applcaitonScope.email} -> ${"hong@test.com"}출력

--%>
		<tr>
			<td>${requestScope.id}</td>
			<td>${requestScope.pwd}</td>
			<td>${sessionScope.name}</td>
			<td>${applicationScope.email}</td>		
		</tr>
		


	    <tr height="1" bgcolor="green">
	   		<td colspan="5"></td>
	    </tr>			
	</table>
</body>
</html>












