<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//   입력한 가입 정보중 한글이 하나라도 존재하면 한글이 깨져서 받아와 지므로
//   문자처리 방식 UTF-8방식으로 request객체 메모리에 설정
	request.setCharacterEncoding("UTF-8");
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
<%-- request내장객체 메모리에 requestScope로 접근하여 MemberBean객체를 얻고
	 MemberBean객체의 각 인스턴스변수에 저장된값 불러와 EL로 출력
 --%>
		<tr>
			<td>${requestScope.member.id}</td>
			<td>${requestScope.member.pwd}</td>
			<td>${requestScope.member.name}</td>
			<td>${requestScope.member.email}</td>
		</tr>

	    <tr height="1" bgcolor="green">
	   		<td colspan="5"></td>
	    </tr>			
	</table>
</body>
</html>












