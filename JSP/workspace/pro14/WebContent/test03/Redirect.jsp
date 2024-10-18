<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
    
<% request.setCharacterEncoding("UTF-8"); %>

<%--
	 <c:redirect>태그
	 - 이태그는 response내장객체의 sendRedirect()메소드를 호출한 
	      포워딩을 통한 페이지 이동을 처리하는 태그입니다.
	      
	 - 문법
	 	<c:redirect url="재요청할 주소 경로"    />
	 
	 	또는
	 	
	 	<c:redirect url="재요청할 서버페이지 주소">
	 	
	 		<c:param name="전달할값을식별할속성명" value="전달할값" >
	 		<c:param name="전달할값을식별할속성명" value="전달할값" >
	 		<c:param name="전달할값을식별할속성명" value="전달할값" >
	 		<c:param name="전달할값을식별할속성명" value="전달할값" >
	 	
	 	</c:redirect>
	 
	 - 재요청시  다른 서버페이지로  전달할 값이 있다면  
	   <c:param>태그를 내부에 사용하면 됩니다.
	 
 --%>    
    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- requestVar변수를 만들고 "홍길동" 저장 !  
          그리고 request내장객체 영역에 requestVar변수 저장 --%>
   <c:set var="requestVar"  value="홍길동" scope="request"/>

<%-- OtherPage.jsp를 재요청(포워딩) 합니다. --%>	
   <c:redirect url="/test03/OtherPage.jsp">
   		<c:param name="user_param1" value="출판사"/>
   		<c:param name="user_param2" value="한빛출판사" />
   </c:redirect>	


</body>
</html>











