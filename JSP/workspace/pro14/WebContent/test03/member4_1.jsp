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
	<%-- 변수 선언  :  정수 100(number변수)과 
	                             문자열 "JSP"(string변수)를 저장시킬 변수를 선언합니다. --%>
	<c:set var="number" value="${100}" scope="page" />
	<c:set var="string" value="JSP" scope="page"  />

<h4>JSTL의 if태그로 짝수/홀수 판단하기</h4>
	
<%-- c:if태그로 number변수를 2로 나눈 나머지가 0인지 판단하는 
     (즉! 짝수인지 판단하여) 결과를  result변수에 저장  --%>
	<c:if test="${ number mod  2 eq 0 }" var="result" 
										 scope="page" >
		${number} 는 짝수 입니다.<br>
	
	</c:if>

	result : ${pageScope.result} <br>
	
	
	
<h4>문자열 비교와 else 구문 흉내내기</h4>

	<%-- string변수에 저장된 문자열과  'Java'문자열을 비교해 같으면  
	          결과값을  result2변수에 저장합니다. --%>
	<c:if test="${ string eq 'Java'}" var="result2" 
									  scope="page">
	
		문자열은 Java입니다. <br>
	
	</c:if>
	
	result2 : ${pageScope.result2} <br>

	<%-- result2변수에 저장된값이 false이면 
	    (string변수에 저장된 문자열이 'Java'문자열과 다르다면?) --%>
    <c:if test="${ not result2}">
    	
    	문자열은 Java가 아닙니다. <br>
    
    </c:if>


<h4>조건식 주의 사항</h4>
	<%-- 주의사항1 :  EL이 아닌 일반값이 오면 무조건 false를 반환합니다. 
	                               하지만 일반값으로 true가 사용되는 것은 예외입니다. --%>		
	<c:if test="100" var="result3" scope="page">
		EL이 아닌 정수를 조건식에 지정하면 false를 반환 <br>
	</c:if>
	
	result3 : ${result3}<br>
	
	
	<%-- 주의사항2 : 문자열 'tRuE'는 대소문자에 상관없이 
	                             항상 true를 반환합니다.  --%>
	<c:if test="tRuE" var="result4">
		
		대소 문자 구분없이 "tRuE"문자열을 test속성에 조건식 대신 넣으면
		true를 반환합니다. <br>
	
	</c:if>
	
	result4 : ${result4}<br>
	
<%-- 주의사항3 : test속성에 EL을 통해 조건식을 쓸때 공백이 삽입되면 
                             무조건 false를 반환합니다. --%>
	<c:if test=" ${true} " var="result5" scope="page" > 

<%--<c:if test="${ true }" var="result5" scope="page" >   
         정상 적으로 조건식의 반환값은 true이다 . --%>	

		EL 양쪽에 빈공백이 있는 경우 test속성에 적은 조건식의 결과는 무조건  
		false를 반환합니다. <br>
	
	</c:if>
	
	result5 : ${result5} <br> <%-- false --%>




</body>
</html>

















