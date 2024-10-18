<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
 
<%--
	 c:set태그를 이용해 변수를 만들고  컨텍스트 주소를 저장
	 
	 pageContext내장객체의 request변수를 호출하면   
	 HttpServletRequest 객체를 얻을수 있다.
	 얻은 HttpServletRequest객체의 contextPath변수를 호출하면  
	 클라이언트가 요청한 전체 URL중에서 컨텍스트 주소를 문자열로 반환하니 얻을수 있다
 
 	      /pro14
 --%>      
<c:set var="contextPath"  
       value="${pageContext.request.contextPath}" 
       scope="page"/>    

<%
	String contextPath2 = request.getContextPath();
%>

<a href="${contextPath}/test01/memberForm.jsp">회원가입하러가기</a>
<br>

	<%-- 로그인 요청 화면에서 ID와 비밀번호를 입력한 후  <form>태그의 action속성에 로그인요청할 result.jsp주소를 작성해서 요청 --%>
	<form action="result.jsp" method="post" >
		아이디 : <input type="text" name="userID"><br>
		비밀번호 : <input type="password" name="userPw"><br>
		
		<%--전송 버튼으로 만들수 있는 경우 --%>
		<input type="submit" value="로그인">
		<input type="reset" value="다시 입력">
	</form>
	<br><br>

	








