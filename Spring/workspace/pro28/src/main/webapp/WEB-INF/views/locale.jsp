<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%--
	jsp페이지에서 다국어들을 표시 하기 위해 스프링의 <spring:message>태그를 이용합니다
	code속성에  properties파일의 키를 입력하면  키에 대한 값이 표시 되고,
	code속성에 해당하는 값이 없으면 기본값으로 표시됩니다.
 --%>  
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> <spring:message code="site.title"  text="Member Info"/>   </title>
</head>
<body>
    <%--   /pro28 --%>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/test/locale.do?locale=ko">한국어</a>
	<a href="${contextPath}/test/locale.do?locale=en">ENGLISH</a>

	<h1> <spring:message code="site.title"  text="Member Info"/> </h1>

	<p>
		<spring:message code="site.name" text="no name"/> : 
		<spring:message code="name" text="no name"/>
	</p>
	
	<p>
		<spring:message code="site.job" text="no job"/> : 
		<spring:message code="job" text="no job"/>
	</p>
	
	<input type="button" value=" <spring:message code='btn.send' /> ">
	<input type="button" value=" <spring:message code='btn.cancel' /> ">
	<input type="button" value=" <spring:message code='btn.finish' /> ">


</body>
</html>










