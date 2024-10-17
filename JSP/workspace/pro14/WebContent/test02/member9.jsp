<%@page import="sec01.ex02.MemberBean"%>
<%@page import="java.util.ArrayList"%>
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
<%
//자바코드를 이용해 request내장객체 영역에 접근하여  ArrayList배열을 얻고
//ArrayList배열에 저장된 MemberBean객체의 갯수만큼 반복하여 얻어 
//MemberBean의 각 인스턴스변수값들 출력

	//순서1. request에 바인딩된 ArrayList배열 얻기 
	ArrayList list = (ArrayList)request.getAttribute("list");
			//[  MemberBean,  MemberBean ]
			//       0            1      index		
			
 	//순서2. ArrayList배열에 저장된 MemberBean객체의 갯수만큼 반복하여 얻어 
 	//      각 인스턴스변수값들 출력
	for(int i=0;  i<list.size();   i++){
		
	    MemberBean mb = (MemberBean)list.get(i);

%>		
		<tr align="center">
			<td width="7%"><%=mb.getId()%></td>
			<td width="7%"><%=mb.getPwd()%></td>
			<td width="5%"><%=mb.getName()%></td>
			<td width="11%"><%=mb.getEmail()%></td>
		</tr>
<%		
	}
%>
	<%--표현 언어 EL을 이용해 request내장객체에 바인딩된 ArrayList배열 얻고 
		ArrayList배열의 0 index위치에 저장된 MemberBean객체의 주소를 얻은 후 
		MemberBean의 각 변수값 얻어 출력 
	 --%>	
		<tr align="center">
			<td width="7%">${requestScope.list[0].id} </td>
			<td width="7%">${requestScope.list[0].pwd}</td>
			<td width="5%">${requestScope.list[0].name}</td>
			<td width="11%">${requestScope.list[0].email}</td>
		</tr>
	<%--표현 언어 EL을 이용해 request내장객체에 바인딩된 ArrayList배열 얻고 
		ArrayList배열의 1 index위치에 저장된 MemberBean객체의 주소를 얻은 후 
		MemberBean의 각 변수값 얻어 출력 
	 --%>	
		<tr align="center">
			<td width="7%">${list[1].id} </td>
			<td width="7%">${list[1].pwd}</td>
			<td width="5%">${list[1].name}</td>
			<td width="11%">${list[1].email}</td>
		</tr>


	    <tr height="1" bgcolor="green">
	   		<td colspan="5"></td>
	    </tr>			
	</table>
</body>
</html>












