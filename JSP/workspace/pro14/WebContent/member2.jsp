<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//1. 입력한 가입 정보중 한글이 하나라도 존재하면 한글이 깨져서 받아와 지므로
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
			<td width="20%">주소</td>		
		</tr>
		
		<tr align="center">
<%-- memberForm.jsp 내부의 <input>에 입력한 값을 request내장객체에서 얻을때
     param내장객체를 이용하여 request내장객체 메모리에 접근하여 얻는다.  --%>		
			<td>${param.id}</td>
			<td>${param.pwd}</td>
			<td>${param.name}</td>
			<td>${param.email}</td>
<%-- forward.jsp에서 request.setAttribute("address","서울시 강남구"); 
          수동으로 바인딩 해 놓은 request내장객체 메모리에 접근할떄  
     EL에서 제공해주는 requestScope내장객체 이름으로 접근하여 얻는다.
   --%>						
			<td>${requestScope.address}</td>
<%-- 			
			<td>
				<% 
					Object obj = request.getAttribute("address");
					String data = (String)obj;
					out.print(data);				
				%>
			</td> 
--%>
			
			
		</tr>	
		
	    <tr height="1" bgcolor="green">
	   		<td colspan="5"></td>
	    </tr>			
	</table>
</body>
</html>












