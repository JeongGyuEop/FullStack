<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//      userid=홍길동&passwd=test

	//요청 받은 값 얻기
	String userid = request.getParameter("userid");
	String passwd = request.getParameter("passwd");
	
	//응답할 데이터를 문자열 형태의 JSON데이터로 만들어서 저장
	String jsonData = "{'userid':'"+userid+"','passwd':'"+passwd+"'}";
//	String jsonData = "{\"userid\":\"" + userid + "\", \"passwd\":\"" + passwd + "\"}";
 
	//Java에서 문자열 내에 큰따옴표(")를 그대로 사용할 수 없기 때문에, 
	//큰따옴표를 문자열 안에 포함하려면 이스케이프 문자 \를 붙여줘야 합니다.
	
	//JSON 문자열에서 큰따옴표를 추가하려면 \"로 작성하여 Java 컴파일러가 큰따옴표를 문자열의 일부로 인식하게 해야 합니다.
	
  

	//응답 
%>
	<%=jsonData%>














