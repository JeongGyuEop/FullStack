<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//1. 입력한 가입 정보중 한글이 하나라도 존재하면 한글이 깨져서 받아와 지므로
//   문자처리 방식 UTF-8방식으로 request객체 메모리에 설정
	request.setCharacterEncoding("UTF-8");
%>    

<%-- 2.3. memberForm.jsp에 입력한 요청데이터들을 request에서 꺼내어
          MemberBean각변수에 저장 --%>
<jsp:useBean id="mb" class="sec01.ex01.MemberBean"  />
<jsp:setProperty  name="mb" property="*"/>


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
<%-- EL로 MemberBean객체의 각변수에 저장된 값을 얻어 출력 
		 
		 	${MemberBean객체.가져와 출력할 값이 저장된_변수명}
--%>	
		<tr align="center">
			<td>${mb.id}</td>
			<td>${mb.pwd}</td>
			<td>${mb.name}</td>
			<td>${mb.email}</td>	
		</tr>	
<%-- 
		<tr align="center">
			<td><%=mb.getId()%></td>
			<td><%=mb.getPwd()%></td>
			<td><%=mb.getName()%></td>
			<td><%=mb.getEmail()%></td>	
		</tr>	
 --%>
		
	    <tr height="1" bgcolor="green">
	   		<td colspan="5"></td>
	    </tr>			
	</table>
</body>
</html>












