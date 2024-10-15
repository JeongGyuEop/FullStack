<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<%
		// 순서 1. 요청한 데이터들을 request 메모리에서 꺼내오기 전에
		// 		   문자 처리 방식을 UTF-8로 설정
		request.setCharacterEncoding("UTF-8");
	
		// 순서 2. 요청한 데이터들 request 객체 메모리에서 꺼내오기(입력한 아이디, 비밀번호)
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
	
		// 순서 3. 아이디를 입력하지 않고 result2.jsp를 요청한 경우
		if(user_id == null || user_id.length() == 0 ){
	%>
			아이디를 입력하세요.<br>
			<a href="/pro12/login.html">로그인 요청 화면으로 다시 이동</a>
	<%
		} else { // 아이디를 입력하고 ressult2.jsp 요청한 경우
			// 입력한 아이디가 관리자 아이디인? admin을 입력한 경우
			if(user_id.equals("admin")) {
	%>			
				<h1>관리자로 로그인 했습니다.</h1>
				<form>
					<input type="button" value="회원정보수정">
					<input type="button" value="회원정보삭제">
				</form>
	<%
			// 입력한 아이디가 관리자 아이디 admin이 아닌 일반 다른 아이디를 입력한 경우
			} else {
	%>
				<h1>환영합니다.<%=user_id %>님!!!</h1>
	<%			
			}
		}
		
	%>
    
    
    