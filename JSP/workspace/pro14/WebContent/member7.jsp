<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	// 회원정보를 표시하기 전에 한글 인코딩을 설정한다.
    	request.setCharacterEncoding("utf-8");
    
    	// 포워딩(재요청) 받은 member7.jsp 화면이다.
    	// 각 내장 객체 메모리 영역에 바인딩 되어 있는 데이터를 꺼내와서 변수에 저장
    	String id = (String)request.getAttribute("id"); // "hong"
    	String pwd = (String)request.getAttribute("pwd"); // "1234"
    	String name = (String)request.getAttribute("name"); // "홍길동"
    	String email = (String)request.getAttribute("email"); // "hong@test.com"
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력창</title>
</head>
<body>
	<table width="100%" border=1 align="center">
		<tr align="center" bgcolor="#99ccff">
			<td width="20%"><b>아이디</b></td>
			<td width="20%"><b>비밀번호</b></td>
			<td width="20%"><b>이름</b></td>
			<td width="20%"><b>이메일</b></td>
		</tr>
		
		<%-- 표현식으로 회원정보를 출력한다. --%>
		<tr align="center">
			<td><%=id %></td>
			<td><%=pwd %></td>
			<td><%=name %></td>
			<td><%=email %></td>
		</tr>
		
		<%-- 자바 코드 없이 각 내장 객체 영역에 접근하여 바인딩된 속성이름으로 가져온값을 EL로 출력 --%>
		<tr align="center">
			<td>${id }</td>
			<td>${pwd }</td>
			<td>${name }</td>
			<td>${email }</td>
		</tr>
		
	</table>

</body>
</html>