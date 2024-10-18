<%@page import="sec01.ex02.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 중에서 core라이브러리에 속한 태그를 불러와서 사용하기위해  taglib 디렉티브태그 작성 --%>        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %>    
    
<%--HashMap 배열 생성   (key,value)를 한쌍의 형태로 저장시키는 배열 메모리   --%>
<jsp:useBean id="membersMap" class="java.util.HashMap" />    
    
<%-- ArrayList배열 생성 --%>
<jsp:useBean  id="membersList"  class="java.util.ArrayList"/>

<%
membersList.add(new MemberBean("ki","4321","기성용","ki@test.com"));
membersList.add(new MemberBean("son","1234","손흥민","son@test.com"));

//HashMap객체에 key, value를 한쌍의 형태로  박지성에 대한 정보를 저장
membersMap.put("id", "park2");
membersMap.put("pwd","4321");
membersMap.put("name","박지성");
membersMap.put("email","park2@test.com");

//HashMap객체에 key, value를 한쌍의 형태로  
//위 MemberBean객체 2개 저장하고 있는 ArrayList배열 자체를 저장 
membersMap.put("List", membersList);
%>
<%--  c:set태그를 이용해 HashMap에저장된 ArrayList배열을 꺼내어서  
      memberslist변수를 만들어 저장 시키면  
      memberslist변수명으로 ArrayList배열을 사용할수 있다. --%>
<c:set var="memberslist" value="${pageScope.membersMap.List}"   />
    
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
<%-- HashMap에 저장된 박지성에 대한 정보를 얻어 출력 
	 방법 : 먼저 membersMap을 작성하여  HashMap에 접근합니다.
	 	    그리고  membersMap.key 형태로 작성하여  
	 	  key와 함께 한쌍으로 저장되었던 value를 얻어옵니다.
--%>	<tr align="center">
			<td>${pageScope.membersMap.id}</td> <%-- "park2" --%>
			<td>${pageScope.membersMap.pwd}</td> <%-- "4321" --%>
			<td>${pageScope.membersMap.name}</td> <%-- "박지성" --%>
			<td>${pageScope.membersMap.email}</td> <%-- "park2@test.com" --%>
		</tr>	
<%--  membersMap.key -> membersMap.List 를 작성하면  key와함께 저장된  HashMap에 저장된 ArrayList배열을 꺼내옵니다. --%>	
<%-- ArrayList배열의 0 index위치에 저장된 첫번째 MemberBean객체를 얻어  첫번째 MemberBean객체의 각 변수를 얻어 출력 --%>
<%-- ${  HashMap참조변수.ArrayList배열을 꺼내올 key[index].꺼내온MemberBean객체의 변수명    } --%>
		<tr align="center">
			<td>${memberslist[0].id}</td>
			<td>${memberslist[0].pwd}</td>
			<td>${memberslist[0].name}</td>
			<td>${memberslist[0].email}</td>
		</tr>
<%-- ArrayList배열의 1 index위치에 저장된 두번째 MemberBean객체를 얻어 두번째 MemberBean객체의 각변수에저장된 값을 얻어 EL로 출력 --%>
		<tr align="center">
			<td>${memberslist[1].id}</td>
			<td>${memberslist[1].pwd}</td>
			<td>${memberslist[1].name}</td>
			<td>${memberslist[1].email}</td>		
		</tr>		
		
	</table>		
		
		
</body>
</html>












