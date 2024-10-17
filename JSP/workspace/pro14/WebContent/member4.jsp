<%@page import="sec01.ex01.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//1. 입력한 가입 정보중 한글이 하나라도 존재하면 한글이 깨져서 받아와 지므로
//   문자처리 방식 UTF-8방식으로 request객체 메모리에 설정
	request.setCharacterEncoding("UTF-8");
%>    

<%-- 2.3. memberForm.jsp에 입력한 요청데이터들을 request에서 꺼내어
          MemberBean각변수에 저장 --%>
<jsp:useBean id="m1" class="sec01.ex01.MemberBean"  />
<jsp:setProperty  name="m1" property="*"/>


<jsp:useBean id="membersList" class="java.util.ArrayList" />

<%
	//2번쨰 MemberBean객체 생성
	MemberBean m2 = 
	new MemberBean("son","1234","손흥민","son@test.com");

	//위 두개의 MemberBean객체들을 ArrayList배열에 추가
	membersList.add(m1);
	membersList.add(m2);
	
	//ArrayList배열 모습
	//[ MemberBean객체         , MemberBean객체  ]
	//       0                   1  

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
<%--		
ArrayList배열 모습
[ MemberBean객체         , MemberBean객체  ]
       0                   1  		
 --%>
 		
<%-- 위 ArrayList배열에 0 index위치에 저장되어 있는
      MemberBean객체의 각변수값 얻어 EL로 출력 --%>	
		<tr align="center">
			<td width="7%">${membersList[0].id}</td>
			<td width="7%">${membersList[0].pwd}</td>
			<td width="5%">${membersList[0].name}</td>
			<td width="11%">${membersList[0].email}</td>		
		</tr>
<%-- 위 ArrayList배열에 1 index위치에 저장되어 있는
      MemberBean객체의 각변수값 얻어 EL로 출력 --%>	
		<tr align="center">
			<td width="7%">${membersList[1].id}</td>
			<td width="7%">${membersList[1].pwd}</td>
			<td width="5%">${membersList[1].name}</td>
			<td width="11%">${membersList[1].email}</td>		
		</tr>

	    <tr height="1" bgcolor="green">
	   		<td colspan="5"></td>
	    </tr>			
	</table>
</body>
</html>












