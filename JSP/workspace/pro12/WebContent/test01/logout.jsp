<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//로그 아웃된 화면을 보여주기 위해
	//공유 받은 session내장객체 메모리 내부에 바인딩된 모든 데이터 제거
	session.invalidate();

    //참고.
	//session.removeAttribute("id");  //("id","master") 만 제거 
	
	//session내장객체 메모리의 모든데이터 제거후
	//index.jsp(메인화면) 포워딩(재요청)
//	response.sendRedirect("index.jsp");
%>
<script>
	window.alert("로그아웃처리 되었습니다.");
	window.alert("메인페이지로 이동합니다.");
	
	location.href = "index.jsp";//index.jsp 재요청

</script>











