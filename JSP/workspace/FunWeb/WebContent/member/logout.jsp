<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	// session 객체 메모리에 접근해서 로그인인증 세션 아이디값 제거
    	session.removeAttribute("id");
    %>
    
    <script>
    	alert("로그아웃 처리 되었습니다.");
    	location.href="../index.jsp";
    </script>
    