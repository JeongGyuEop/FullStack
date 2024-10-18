<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>향상 for문 형태의 forEach태그 사용해보기</h4>
	<%
		//문자열 객체들을 저장할 일반 고정길이 배열 생성
		String[] rgba = {"Red", "Green", "Blue", "Black"};
		//				   0       1        2       3
	%>
	<%--
		 c:forEach태그에서 items속성에는 반복할 배열을 지정하고,
		 변수이름은 c로 생성했습니다.
		 items속성에 지정한 배열의 크기만큼  반복해서 
		 0 index 위치의 문자열"Red"부터  3 index위치의 문자열 "Black"까지 
		 차례로 반복해서 얻어서 출력합니다.  
		 4 index위치는 없으므로 꺼내오려고 할때 
		 c:forEach문을 빠져 나가 종료 됩니다.
	 --%>	
	 	<c:forEach var="c"  items="<%=rgba%>" >
	 	
	 		<span style="color:${c}">
	 			${c}
	 		</span>
	 	
	 	
	 	</c:forEach>
	 
	  <h4>varStatus속성 살펴보기</h4>
<!-- 
	문자열 객체들을 저장할 일반 고정길이 배열 생성
	String[] rgba = {"Red", "Green",  "Blue",  "Black"};
		              0       1        2         3       index
-->	  
	<table border="1">	
		<c:forEach var="c"  items="<%=rgba%>"  varStatus="loop"  >
			<tr>
				<td>count : ${loop.count}</td> <!-- 현재 몇번 반복하고 있는상태 인지 int로 반환 -->
				<td>index : ${loop.index}</td> <!-- var c변수에 현재 반복하고 있는 상태에서  저장된 값의 배열의 index위치 반환 -->
				<td>current : ${loop.current}</td> <!--  var c변수에 저장되는 index의 문자열값을 반환 -->
				<td>first : ${loop.first}</td> <!-- 처음 반복하고 있는 상태 일때만 true를 반환 -->
				<td>last : ${loop.last}</td> <!-- 마지막 반복하고 있는 상테 일때만 true를 반환 -->
			</tr>
		
		</c:forEach>
	</table>	 
	
	
	
	
	


</body>
</html>












