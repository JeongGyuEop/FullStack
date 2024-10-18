<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
    
    
<% request.setCharacterEncoding("UTF-8"); %>    
    
<%-- urlTest.jsp를 요청했을때의 전체 요청 주소 중에서   
          켄텍스트 주소 얻어 변수에 저장 --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>    

<%-- 재요청할 주소를 value속성에 작성해서 url1변수에 저장 후 사용하게 됩니다. --%>
<c:url var="url1" value="/test01/member1.jsp">
	<c:param name="id" value="hong"/>
	<c:param name="pwd" value="1234"/>
	<c:param name="name" value="홍길동"/>
	<c:param name="email" value="hong@test.com"/>
</c:url>    

<a href="${contextPath}/test01/member1.jsp?id=hong&pwd=1234&name=홍길동&email=hong@test.com">회원정보 출력</a>
<hr>
<a href="${url1}">회원정보 출력</a>








