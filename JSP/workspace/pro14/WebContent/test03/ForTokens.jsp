<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 중에서 core라이브러리에 속한 태그를 불러와서 사용하기위해  taglib 디렉티브태그 작성 --%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"    %>   
  
<%  request.setCharacterEncoding("UTF-8"); %>     

<%--

	 c:forTokens 태그
	 
	 -  마치 자바의 StringTokenizer클래스 처럼 구분자를 기준으로 
	        문자열을 나눠  토큰의 갯수만큼 반복해 줍니다.
	 
	 -  문법
	 		<c:forTokens  var="변수명"  items="나눌전체문자열"  delims="문자열 구분자"  />

	  참고. 토큰이란?
	  	  일반적으로 토큰은 '문법적으로 의미있는 최소 단위'를 말합니다.
	  	 여기서는 문자열을 구분할 구분자로 분리외는 문자열의 구성요소를 말합니다.
	  	 전화번호를 예로 든다면 "010-1234-5678"을   구분자 -(하이픈)으로 분리하면   "010" , "1234",  "5678"이 각각 토큰이 됩니다.

 --%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<%
			String rgba = "Red,Green,Blue,Black"; //콤마로 구분된 문자열을 준비합니다. 구분자는 ,콤마 입니다.
		%>
		
		<h4>JSTL의 forTokens 태그 사용</h4>
		
		<%-- 구분자 쉼표,로  구분된 토큰의 갯수만큼 반복 --%>
		<%-- 이태그는  items속성에는 문자열만 사용할수 있습니다. 
		          배열이나 컬렉션 가변길이 배열은 사용할수 없습니다. 
		      delims속성에는  토큰을 구분해서 분리할  구분자기호를 문자열로 설정합니다.
		      var속성에는  구분자 ,콤마를 기준으로 분리된 토큰을 반복해서 저장할 변수color를 설정합니다.    
		          --%>
		<c:forTokens items="<%=rgba%>" delims="," var="color">
			
			 <span style="color:${color}">${color}</span> <br>
		
		</c:forTokens>
		
		
		
		
</body>
</html>








