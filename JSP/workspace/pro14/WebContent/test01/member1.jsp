<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 중에서 core라이브러리에 속한 태그를 불러와서 사용하기위해  taglib 디렉티브태그 작성 --%>        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %>    
    
<%-- 
	<c:set>태그를 이용하면 변수를 선언할수 있습니다.
	 value속성에는  표현언언어를 사용해서 변수에 저장되는 값을 초기화할수 있습니다.
	 
	 문법
	 	<c:set  var="선언할변수명" value="저장할값"  scope="선언한변수를 저장할 내장객체종류명중에 하나"    />
	 
	 	또는
	 	
	 	<c:set var="선언할변수명" scope="선언한변수를 저장할 내장객체 종류중 하나">
	 	
	 		value속성에서 적었던 저장할값 
	 	
	 	</c:set>
--%>    
    
<%-- id변수선언 후 hong문자열저장     
     id변수는 page내장객체 영역에 저장후 현재 페이지에서 공유해서 사용함
--%>    
<c:set var="id" value="hong" scope="page"/>   
<c:set var="pwd" value="1234" scope="page"/> 
<c:set var="name" value="${'홍길동'}" scope="page"/>   
<c:set var="age"  value="${22}" scope="page"/>
<c:set var="height" value="${177}" scope="page"/>    
    
    
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
			<td width="11%">나이</td>
			<td width="20%">키</td>		
		</tr>
		<%-- 표현언어 EL로 변수에 바로 접근하여 값을 얻어 출력 --%>
		<tr align="center">
			<td>${pageScope.id}</td>
			<td>${pwd}</td>
			<td>${name}</td>
			<td>${age}</td>
			<td>${height}</td>
		</tr>
	</table>		
		
		
</body>
</html>












