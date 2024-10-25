<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//      userid=홍길동&passwd=test

	//요청 받은 값 얻기
	String userid = request.getParameter("userid");
	String passwd = request.getParameter("passwd");
	
	//응답 
	out.print(userid + "\t" + passwd);
	
	/*
	결과에서 [파일수신]버튼을 클릭하면 4.html은 지정된 파라미터를 가지고
	Ajax통신을 이용하여 서버에 smapleText.jsp파일을 요청한다.
	jsp로 요청 했기 때문에 서버에서는 동적인 데이터를 클라이언트로 반환 하게 된다.

*/
	
%>






