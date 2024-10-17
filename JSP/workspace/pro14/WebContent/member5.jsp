<%@page import="sec01.ex01.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//1. 입력한 가입 정보중 한글이 하나라도 존재하면 한글이 깨져서 받아와 지므로
//   문자처리 방식 UTF-8방식으로 request객체 메모리에 설정
	request.setCharacterEncoding("UTF-8");
%>    

<%-- HashMap클래스의 객체 생성 --%>
<jsp:useBean id="membersMap"  class="java.util.HashMap"   />

<%
	//HashMap객체에 key,value 한쌍형태의 데이터 저장
	membersMap.put("id", "park2");
	membersMap.put("pwd", "4321");
	membersMap.put("name", "박지성");
	membersMap.put("email", "park2@test.com");
/*
	-----------------------------------HashMap----------------------------------
    key들   value들 
	("id", "park2")
	("pwd", "4321")
	("name", "박지성")
	("email", "park2@test.com")
	-----------------------------------------------------------
*/
%>
<%-- ArrayList클래스의 객체 생성 --%>
<jsp:useBean id="membersList" class="java.util.ArrayList"/>

<%-- 2.3. memberForm.jsp에 입력한 요청데이터들을 
     request에서 꺼내어 MemberBean각변수에 저장 --%>
<jsp:useBean  id="mb" class="sec01.ex01.MemberBean"  />
<jsp:setProperty  name="mb" property="*"/>

<%
//자바코드로 새로운 회원정보가 저장된 MemberBean객체 생성
MemberBean mb2 = new MemberBean("son","1234","손흥민","son@test.com");

//위 ArrayList배열에 두개의 MemberBean객체 추가	
membersList.add(mb);   membersList.add(mb2);

//추가로~~~~~~~~~~~~~~~~~~HashMap내부에 ArrayList배열 추가 !
membersMap.put("membersList", membersList);

/*

-----------------------------------HashMap----------------------------------
     key들   value들 
	("id", "park2")
	("pwd", "4321")
	("name", "박지성")
	("email", "park2@test.com")
	("membersList", ArrayList배열)
						|              입력한정보          손흥민
						 ->  [  new MemberBean(), new MemberBean()  ] 
								 	 0                 1
------------------------------------------------------------------------------


					${membersMap.membersList[0].변수명}
					${membersMap.membersList[1].변수명}

*/


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
<%-- HashMap에 저장된   
		("id", "park2")
		("pwd", "1234")
		("name", "박지성")
		("email", "park2@test.com")
		                                 value들 얻어 EL로 출력
 --%>	
		<tr align="center" bgcolor="green">
			<td width="7%">${membersMap.id}</td>
			<td width="7%">${membersMap.pwd}</td>
			<td width="5%">${membersMap.name}</td>
			<td width="11%">${membersMap.email}</td>	
		</tr>
<%-- HashMap에 저장된 ArrayList배열얻고 
         이배열에 0 index위치에 저장되어 있는 MemberBean객체의 
         각변수값 얻어 EL로 출력 --%>
		<tr align="center">
			<td width="7%">${membersMap.membersList[0].id}</td>			
			<td width="7%">${membersMap.membersList[0].pwd}</td>
			<td width="5%">${membersMap.membersList[0].name}</td>
			<td width="11%">${membersMap.membersList[0].email}</td>	
		</tr>
<%-- HashMap에 저장된 ArrayList배열얻고 
         이배열에 1 index위치에 저장되어 있는 MemberBean객체의 
         각변수값 얻어 EL로 출력 --%>
		<tr align="center">
			<td width="7%">${membersMap.membersList[1].id}</td>			
			<td width="7%">${membersMap.membersList[1].pwd}</td>
			<td width="5%">${membersMap.membersList[1].name}</td>
			<td width="11%">${membersMap.membersList[1].email}</td>	
		</tr>


<%--
-----------------------------------HashMap----------------------------------
     key들   value들 
	("id", "park2")
	("pwd", "4321")
	("name", "박지성")
	("email", "park2@test.com")
	("membersList", ArrayList배열)
						|              입력한정보          손흥민
						 ->  [  new MemberBean(), new MemberBean()  ] 
								 	 0                 1
------------------------------------------------------------------------------


					${membersMap.membersList[0].변수명}
					${membersMap.membersList[1].변수명}

 --%>

	</table>
</body>
</html>












