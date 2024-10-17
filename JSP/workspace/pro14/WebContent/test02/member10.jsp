<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

//forward4.jsp페이지에서 request내장객체에 
//address속성이름과 함께 "서울시 강남구"를 바인딩했었습니다

//현재 member4.jsp페이지에서 session내장객체에 
//똑같은 address속성이름으로 다른값 "수원시 팔달구"를 바인딩 합니다.

//결과 아래의 EL태그로  address속성명을 이용해  값을 얻어 출력하면 어떤 데이터가 출력될까요? 
//단! requestScope. 또는 sessionSope.을 생략했을때 어떻게 출력되는지 알아보자
session.setAttribute("address", "수원시 팔달구");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" align="center">
		<tr align="center" bgcolor="#99ccff">
			<td width="20%"><b>아이디</b></td>
			<td width="20%"><b>비밀번호</b></td>
			<td width="20%"><b>이름</b></td>
			<td width="20%"><b>이메일</b></td>
			<td width="20%"><b>주소</b></td>
		</tr>
	    <tr align="center">
			<td>${id}</td>   <%-- requestScope.id 중에 requestScope. 생략    --%>
			<td>${pwd}</td>	 <%-- requestScope.pwd 중에 requestScope. 생략 --%>
			<td>${name}</td> <%-- sessionScope.name 중에 sessionScope. 생략 --%>
			<td>${email}</td> <%-- applicationScope.email 중에   applicationScope. 생략 --%>
			<td>${address}</td>   <%--  requestScope.address 중에 requestScope. 생략 한것임      --%>
		</tr>
	</table>
	<%--  위 표현 언어로 address속성이름과 같은 이름으로 각 내장객체에 저장된  값을 얻어 출력시!  
	            앞에 내장객체영역에 접근하는  XXXScpoe.을 생략하면
	    	먼저 1. page 내장객체 영역에 만약 address속성명과 같이 저장된 값이 있으면 그값을 얻는다.
		    2. request 내장객체 영역에  만약 address속성명과 같이 저장된 값이 있으면 그값을 얻는다.
		    3. session 내장객체 영역에 만약 address속성명과 같은 이름으로 저장된 값이 있으면 그값을 얻는다.
		    4. application 내장객체 영역에 만약 address속성명과 같은 이름으로 저장된 값이 있으면 그값을 얻는다.
	
			page -> request -> session -> application 
			
		결론:  위 ${address}는   requestScope.이 생략되어   
		       request내장객체 영역에 바인딩된 address속성이름의 값을 얻어 출력한것임
		
	   --%>



</body>
</html>






