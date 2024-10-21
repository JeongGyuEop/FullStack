<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 중에서 core태그들을 사용하기 위해 주소를 import --%>        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<%-- JSTL 중에서 fomatting태그들을 사용하기 위해 주소를 import --%>        
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
    
<% request.setCharacterEncoding("UTF-8"); %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h2>fmt의 formatNumber태그를 이용한 숫자 포맷팅 예제</h2>
		
		<c:set var="price" value="100000000" />
		
		<fmt:formatNumber  value="${price}" type="number" var="priceNumber"/>
		<br>			
					
		일반 숫자로 표시 : ${priceNumber} <br>	
		
<%-- currencySymbol속성에는   type속성에 지정한 currency대신   
         개발자가 원하는 기호표시로 설정할수 있다. --%>	
		통화기호로 표시  : 
		<fmt:formatNumber  value="${price}" 
						   type="currency" 
						   currencySymbol="$"   groupingUsed="true"/>
		
	   <br>
	   
	     퍼센트로 표시 : 
	   <fmt:formatNumber   value="${price}" 
	                       type="percent"  groupingUsed="false"    />
	   	
				
		
		
		
		
		
</body>
</html>













