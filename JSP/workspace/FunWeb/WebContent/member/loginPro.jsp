<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	
	// 1또는 0 또는 -1중 하나
	int check = new MemberDAO().userCheck(id, passwd);
	
	// check 변수 값이 1일때 :
	if(check == 1) {
		session.setAttribute("id", id);
		response.sendRedirect("../index.jsp");
		
	// check 변수 값이 0일때 :
	} else if(check == 0) {
%>
		<script>
			window.alert("비밀번호 틀림!");
			location.herf = "login.jsp";
			// history.back(); 또는 history.go(-1);
		</script>
<%		
	// check 변수 값이 -1일때 :
	} else {
%>
		<script>
			window.alert("아이디 틀림!");
			history.back(); // login.jsp 화면으로 이동
		</script>
<%	
	}
	
%>
    
    
    
    
    
    