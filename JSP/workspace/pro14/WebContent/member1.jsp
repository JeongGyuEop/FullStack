<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//1. 입력한 가입 정보중 한글이 하나라도 존재하면 한글이 깨져서 받아와 지므로
//   문자처리 방식 UTF-8방식으로 request객체 메모리에 설정
	request.setCharacterEncoding("UTF-8");

//2. 요청한 값 얻기 (가입을 위해 입력한 값들을 request객체 메모리에서 꺼내오기)
	String id = request.getParameter("id");//입력한 아이디 얻기
	String pwd = request.getParameter("pwd");//입력한 비밀번호 얻기 
	String name = request.getParameter("name");//입력한 이름 얻기 
	String email = request.getParameter("email");//입력한 이메일 얻기 
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
<%-- 자바코드를 작성하면 request.getParameter메소드를 호출해서 가져온 정보들을 표현식으로 출력 --%>		
		<tr align="center">
			<td><%=id%></td>
			<td><%=pwd%></td>
			<td><%=name%></td>
			<td><%=email%></td>
		</tr>	
<%--
	 EL에서 재공해주는 param내장객체 명을 이용해 request내장객체 메모리에 접근하여
	 getParameter메소드를 이용하지 않고 바로 회원정보를 얻어 출력
	 
	 참고.  ${param.<input>태그의name속성값 작성} 
 --%>	
		<tr align="center">
			<td>${param.id}</td>
			<td>${param.pwd}</td>
			<td>${param.name}</td>
			<td>${param.email}</td>
		</tr>	
		
	    <tr height="1" bgcolor="green">
	   		<td colspan="5"></td>
	    </tr>			
	</table>
</body>
</html>












