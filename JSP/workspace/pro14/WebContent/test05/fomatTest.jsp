<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>fmt 의 foramtnumber태그를 이용한 숫자 포맷팅 예제</h2>
	<c:set var="price" value="100000000" />
	<fmt:formatNumber value="${price }" type="number" var="priceNumber" />
	
	<br>
	
	일반 숫자로 표시 : ${ priceNumber } <br>
	
	통화 기호로 표시 : 
	<fmt:formatNumber value="${price }" type="currency" currencySymbol="$" groupingUsed="true"/>
	
	<br>
	
	퍼센트로 표시 :
	<fmt:formatNumber value="${price }" type="percent" />
</body>
</html>