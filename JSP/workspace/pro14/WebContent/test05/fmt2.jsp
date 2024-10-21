<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
    
<% request.setCharacterEncoding("UTF-8"); %>    
<%-- 
	 날짜 포맷 및 타임존
	 
	 <fmt:formatDate /> 태그    (날짜 포맷)
	 
	 	- 이태그는 날짜와 시간 포맷을 지정하는 태그입니다.
	 	
	 	- 문법
	 		 <fmt:formatDate   
	 		 	  
	 		 	  value="변환하여출력할날짜"
	 		 	  
	 		 	  type="변환하여 출력할 양식 세가지중 하나   (출력양식 종류 : 날짜 date,  시간  time,  날짜 및 시간모두  both  )"
	 		 	  
	 		 	  var="변환하여 출력할 날짜 또는 시간을 저장할 변수"
	 		 	
	 		 	  dataStyle="날짜 스타일 종류 지정  (default, short,  medium, long, full 중 하나 )"
	 		 	  
	 		 	  timeStyle="시간 스타일 종류 지정 (default, short,  medium, long, full 중 하나 ) "
	 		 	
	 		 	  pattern = "출혁할 날짜 및 시간의 양식을 패턴으로 직접 지정합니다."
	 		 	
	 		 	  scope = "변환한 날짜가 저장된 var의 변수를 저장할 내장객체 영역중 하나"

	 		 />
	 	
	 
	  타임존
	 	<fmt:timeZone> </fmt:timeZone> 태그
	 	- 출력할 시간의 시간대를 설정할수 있는 태그 
	 	- 위 <fmt:formatDate>태그를 <fmt:timeZone>여는 부분과 닫는 부분 사이에 작성하면 ,
	 	  설정한 시간대에 따라 다른 시간을 출력할수 있습니다.
	 	  
	 	  <fmt:timeZone  value="America/Chicago" >
	 	  	
	 	  		<fmt:formatDate value="날짜및시간" ....  />
	 	  
	 	  </fmt:timeZone>


--%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- java.util패키지에서 제공해주는 Date클래스의 객체를 생성해서 변수에 저장합니다. 
		 Date클래스의 객체를 생성하면 오늘날짜와 시간값을 가지는 Date객체가 만들어집니다.
	 --%>
	<c:set var="today"  value="<%=new java.util.Date()%>"  />

  	<%-- Mon Oct 21 11:54:16 KST 2024 --%>
	<c:out value="${today}" /> <br>


	<h4>날짜 포맷</h4>
	<%--날짜를 포맷(변환) 하기 위해서는  type="date"로 설정합니다. 
	       날짜스타일은 dateStyle속성에 각각 지정합니다. --%>
   
     <%-- full : 2024년 10월 21일 월요일 --%>
     full : <fmt:formatDate  value="${today}" type="date" dateStyle="full"   /> <br>
	
	 <%-- short : 24. 10. 21. --%>
	 short : <fmt:formatDate  value="${today}" type="date" dateStyle="short" /> <br>
	 
	 <%-- long : 2024년 10월 21일 --%>
     long : <fmt:formatDate  value="${today}" type="date" dateStyle="long" /> <br>

	 <%-- default : 2024. 10. 21. --%>
     default : <fmt:formatDate  value="${today}" type="date" dateStyle="default" /> <br>
     
     
     <h4>시간포맷</h4>
     <%--시간 포맷(변환) 하기 위해서는  type="time"로 설정합니다. 
                 시간스타일은 timeStyle속성에 각각 지정합니다. --%>
     
     <%-- full : 오후 12시 1분 41초 대한민국 표준시 --%>
     <fmt:formatDate value="${today}" type="time" timeStyle="full"/> <br>
     
     <%-- short : 오후 12:02 --%>
     <fmt:formatDate value="${today}" type="time" timeStyle="short"/> <br>
     
     <%-- long : 오후 12시 3분 41초 KST --%>
     <fmt:formatDate value="${today}" type="time" timeStyle="long"/> <br>
     
     <%-- default : 오후 12:04:54 --%>
     <fmt:formatDate value="${today}" type="time" timeStyle="default"/> <br>
     
     
     <h4>날짜/시간 포맷</h4>
      <%-- 날짜와 시간을 포맷해서 동시에 출력하기위해서는 type="both"로 설정합니다.  --%>
      
      <%--2024년 10월 21일 월요일 오후 12시 8분 3초 대한민국 표준시 --%>
      <fmt:formatDate value="${today}" type="both" 
      								   dateStyle="full" 
      								   timeStyle="full"  /> <br>
     <%-- dataStyle속성과  timeStyle속성대신  
          pattern속성을 설정하여 직접  날짜나 시간의 포맷형식을 설정해서 변경할수 있다. --%>
      <%-- 2024/10/21 12:11:08 --%>    
      <fmt:formatDate value="${today}" type="both"
      								   pattern="yyyy/MM/dd hh:mm:ss"/> <br>
      								   
	  <h4>타임존 설정</h4>
  	  <%-- 시간대를 세계 협정시(GMT, 대한민국 시간보다 9시간 빠름)로 변경 --%>
	    세계 협정 시간 대 :
	  <fmt:timeZone value="GMT">
 		
 		      <fmt:formatDate value="${today}" type="both" 
    								   dateStyle="full" 
    								   timeStyle="full"  />
	  </fmt:timeZone>
	  
	  <br>
	  
	  시카고 지역 시간 대 :
	  <fmt:timeZone value="America/Chicago">
 		
 		      <fmt:formatDate value="${today}" type="both" 
    								   dateStyle="full" 
    								   timeStyle="full"  />
	  </fmt:timeZone>	  
	  
	 <br>
	 
	  유럽 런던 시간 대 :
	  <fmt:timeZone value="Europe/London">
 		
 		      <fmt:formatDate value="${today}" type="both" 
    								   dateStyle="full" 
    								   timeStyle="full"  />
	  </fmt:timeZone>	 
	  
	  

</body>
</html>













