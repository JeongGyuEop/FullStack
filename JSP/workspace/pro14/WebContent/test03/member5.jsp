<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 중에서 core라이브러리에 속한 태그를 불러와서 사용하기위해  taglib 디렉티브태그 작성 --%>        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %>    
     
    
<%-- id변수선언 후 hong문자열저장     
     id변수는 page내장객체 영역에 저장후 현재 페이지에서 공유해서 사용함
--%>    
<c:set var="id" value="hong" scope="page"/>   
<c:set var="pwd" value="1234" scope="page"/> 
<%-- <c:set var="name" value="${'홍길동'}" scope="page"/>  --%>  
<c:set var="age"  value="${22}" scope="page"/>
<c:set var="height" value="${177}" scope="page"/>    
 
<%--
	  <c:choose> , <c:when>,  <c:otherwise> 태그 
	  
	  - <c:choose>태그는 다중 조건식을 통해 판단해야 할때 사용하는 태그입니다.
	        하위 태그로  <c:when>태그, <c:otherwise>태그를 함께 사용합니다.
	  
	  - 문법
	  		<c:choose>
	  			<c:when test="조건식1"> 조건식1을 만족하는 경우 실행될 코드  </c:when>    if(조건식1) { ...
	  			<c:when test="조건식2"> 조건식2을 만족하는 경우 실행될 코드  </c:when>    } else if(조건식2){ ...
	  			<c:otherwise>모든 조건식에 만족하지 않을경우 실행될 코드 </c:otherwise>	  } else{ ...  }
	  		</c:choose>
	  		  
            설명 : 얼핏보면 switch case default 비슷하지만  
                      각각의 <c:when>에서 조건식을 비교한다는 점은
            if  else if  else와 같은 구조입니다.
 --%>  
    
 
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
<c:choose>
	<%--page내장객체 영역에 name변수가 저장되어 있지 않느냐? --%>
	<c:when test="${empty pageScope.name}">
	    <tr align="center">
	    	<td colspan="5">이름을 입력하세요!!</td>
	    </tr>
	</c:when>
	<c:otherwise>
			<%-- 표현언어 EL로 변수에 바로 접근하여 값을 얻어 출력 --%>
		<tr align="center">
			<td>${pageScope.id}</td>
			<td>${pwd}</td>
			<td>${name}</td>
			<td>${age}</td>
			<td>${height}</td>
		</tr>
	</c:otherwise>
</c:choose>		
	</table>
 	
		
		
</body>
</html>












