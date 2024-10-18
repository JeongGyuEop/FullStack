<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 중에서 core라이브러리에 속한 태그를 불러와서 사용하기위해  taglib 디렉티브태그 작성 --%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"    %>   
  
<%  request.setCharacterEncoding("UTF-8"); %>      
      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>일반 for문 형태의 forEach 태그 사용</h4>
	
    <%-- 일반 for문 형태로  i변수의 값이 1부터 3이될때까지  반복하며  i변수의 값을 1씩 증가 시킵니다. 
                  이떄 증가 하는 값은 i변수에 저장됩니다. --%>	
	<c:forEach  var="i" begin="1"  end="3" step="1" > 
	
		<p>반복 ${i} 입니다.
	
	</c:forEach>
	
	
	<h4>varStatus 속성 살펴보기</h4>
	
	<table border="1">
												<%-- varStatus속성은 반복하고 있는 상태의 정보를 알려줄 변수명 loop를 지정합니다.
													 loop변수를 통해 varStatus속성이 제공하는 모든 하위 속성의 값을 얻어 출력!
													 일반 for문에서는 index, current 속성을 호출하면 값이 동일하게 나옵니다.
												 --%>
		<c:forEach var="i"  begin="3"  end="5"  varStatus="loop" >
		
			<tr>
				<td>count : ${loop.count}</td> <!-- 현재 몇번 반복하고 있는상태 인지 int로 반환 -->
				<td>index : ${loop.index}</td> <!-- var i변수에 현재 반복하고 있는 상태에서  저장된 값을 반환 -->
				<td>current : ${loop.current}</td>
				<td>first : ${loop.first}</td> <!-- 처음 반복하고 있는 상태 일때만 true를 반환 -->
				<td>last : ${loop.last}</td> <!-- 마지막 반복하고 있는 상테 일때만 true를 반환 -->
			</tr>
		
		
		</c:forEach>
	
	</table>
	
	
	<h4>1에서 100까지 정수 중 홀수의 합</h4>
	
	<c:forEach var="j" begin="1" end="100" step="1" varStatus="loop">
	
		<!-- j변수의 값이 홀수 일때만  sum변수를 만들어서 누적! -->
		<c:if test="${ j % 2 != 0 }">
			
			\${loop.index} :  ${loop.index} 
			<c:set var="sum"  value="${ sum + j }" scope="page" />
			
		</c:if>
	
	</c:forEach>
	
	1 ~ 100 사이의 정수 중 홀수의 총합은? ${pageScope.sum}





</body>
</html>













