<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
    
<% request.setCharacterEncoding("UTF-8"); %>    
  
<%
	//자바코드로 ArrayList배열 생성 후 문자열 저장
	List dataList = new ArrayList();
		 dataList.add("hello");
		 dataList.add("world");
		 dataList.add("안녕하세요!!");		 
%>  
<%-- JSTL c:forEach에서 EL ${ }을  사용할수 있도록   
     list변수에  위 dataList참조변수에 저장된 ArrayList배열의 주소를 저장  --%> 
<c:set var="list" value="<%=dataList%>" />
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 변수i 1부터  10이 될때까지 1씩 증가 시키면서 10번 반복해서 i변수를 출력.  
          그리고 현재 몇번 반복하고 있는지 상태값도 출력 --%>
		<c:forEach var="i" begin="1" end="10" step="1" varStatus="loop">
			
			현재  ${loop.count} 반복한 상태의     i변수에 저장된 값은 ${i} 입니다.  <br>
		
		</c:forEach>
	
<%--
	varStatus="loop"에서 loop는 c:forEach 태그의 반복 상태를 저장하는 변수입니다. 
	varStatus 속성을 사용하면 반복에 대한 다양한 정보를 얻을 수 있는데, 
	그 중 loop는 이 정보를 담고 있는 객체가 됩니다. 이 loop 객체를 통해 반복의 상태 정보를 참조할 수 있습니다.

	loop 객체가 제공하는 주요 속성들은 다음과 같습니다:	
		loop.index: 0부터 시작하는 현재 반복의 인덱스.
		loop.count: 1부터 시작하는 현재 반복 횟수.
		loop.first: 첫 번째 반복인지 여부 (Boolean).
		loop.last: 마지막 반복인지 여부 (Boolean).
		loop.even: 현재 반복이 짝수인지 여부.
		loop.odd: 현재 반복이 홀수인지 여부.
		
	위코드를 예를 들면, ${loop.count}는 현재 몇 번째 반복인지 나타내고, 
	               ${i}는 var="i"에 정의된 반복 변수 값을 참조합니다.
--%>

	<hr>
	<%--
		 일반 for문으로  초기변수i를 선언하고 1을 초기화
		 			초기변수i변수값이 10보다 작을때까지
		 			증감식에서 2씩 증가시키면서 
		 			
		 			5단을 반복해서 출력 
		 			5 * 1 = 5 <br>
		 			5 * 3 = 15 <br>
		 			5 * 5 = 25 <br>
		 			5 * 7 = 35 <br>
		 			5 * 9 = 45 <br>	
		 			
	 --%>
	 <c:forEach var="i" begin="1" end="10" step="2">
	 	
	 		5 * ${i} = ${5 * i} <br>
	 
	 </c:forEach>
	 
<hr>
	<%-- ArrayList배열에 저장된 문자열객체의 갯수만큼 반복해서 
	     data변수에 저장하고 반복해서 출력! 
	     							ArrayList배열
	     				   ["hello","world","안녕하세요!!"]
	     				      0        1        2         index
	     --%>
								  
	<c:forEach  var="data" items="${list}" varStatus="loop">
	
		${loop.count}번쨰 반복하고 있는 상태에서 얻는 문자열객체는?
		${data} 문자열이다~~~~ <br>
	
	
	</c:forEach>
	
<hr>	
	<%-- ArrayList배열에 저장된 문자열객체의 갯수만큼 반복해서 
	     data변수에 저장하고 반복해서 출력! 
	     							ArrayList배열
	     				   ["hello","world","안녕하세요!!"]
	     				      0        1        2         index
	     --%> 
	<c:forEach var="data"  begin="0"   
						   end="${list.size()-1}" 
						   step="1"  
						   items="${list}">
		 ${data}문자열이다~ <br>
	
	
	</c:forEach>     
	     
	     
  <hr>
  
  		  
	  
	  
	     
	     

</body>
</html>










