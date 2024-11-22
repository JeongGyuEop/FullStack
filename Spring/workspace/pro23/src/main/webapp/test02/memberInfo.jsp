<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" align="center" width="100%">
	
		<tr align="center" bgcolor="lightgreen">
			<td><b>아이디</b></td>
			<td><b>비밀번호</b></td>
			<td><b>이름</b></td>
			<td><b>이메일</b></td>
			<td><b>가입일</b></td>
		</tr>
		<tr align="center">
			<td>${requestScope.member.id}</td>
			<td>${requestScope.member.pwd}</td>
			<td>${member.name}</td>
			<td>${member.email}</td>
			<td>${member.joinDate}</td>	
		</tr>
	
	
	</table>

</body>
</html>





