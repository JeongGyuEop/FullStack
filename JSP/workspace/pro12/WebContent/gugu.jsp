<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	// 1. 요청한 값 문자처리방식 utf-8 설정
    	request.setCharacterEncoding("utf-8");
    
    	// 2. 입력한 단수 얻기(요청한 값 얻기)
		//    입력한 단수 값 int dan 변수에 저장
		int dan = Integer.parseInt(request.getParameter("dan"));
    	
    	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" width="800">
		<tr bgcolor="yellow" align="center">
			<td colspan="2"><%= dan %>단 출력</td>
		</tr>
		
<%
		// for 반복문을 사용하여 각 구구단의 곱하는 수 만큼 9번 반복해서 구구단 출력
		for(int i=1; i<10; i++) {
			if(i%2 == 1) { // 곱하는 수가 홀수이면?
%>
				<tr align="center" bgcolor="green">
					<td width="400"><%= dan %>*<%= i %></td>
					<td width="400"><%= dan*i %></td>
				</tr>
<%
			} else { // 곱하는 수가 짝수이면?
%>
				<tr align="center" bgcolor="pink">
					<td width="400"><%= dan %>*<%= i %></td>
					<td width="400"><%= dan*i %></td>
				</tr>
<%
			}
		}
%>		
	
	
	
	
	</table>
</body>
</html>