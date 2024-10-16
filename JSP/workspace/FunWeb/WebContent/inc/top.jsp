<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	// top.jsp 가 요청당하면
	// 전체 요청 주소는 -> http://localhost:8090/FunWeb/inc/top.jsp

	// 전체 요청한 주소는 request 객체 메모리 영역에 저장되어 있기 때문에
	// 컨텍스트 주소 경로까지 얻을 수 있는 메소드를 제공한다.
	String contextPath = request.getContextPath();
	//      "/FunWeb"
	
	// session 객체 메모리 영역에 접근하여 로그인 인증할 아이디값 얻어온다.
	String id = (String)session.getAttribute("id");
	
%>

	<!-- 헤더들어가는 곳 -->
	<header>
		<div id="login">
<%
		if(id == null) { // session 객체에 저장된 로그인인증 세션값이 없으면?
				//로그아웃된 화면 보여주자
%>
			<a href="<%=contextPath %>/member/login.jsp">login</a> 
			| <a href="<%=contextPath %>/member/join.jsp">join</a>
<%
		} else { // session 객체에 로그인 인증 세션 값이 저장되어 있으면?
			// 로그인된 화면 보여주자
%>
			<span style="color:red;"><%=id %>로그인 중! 환영합니다.</span> &nbsp;&nbsp;
			<a href="<%=contextPath %>/member/logout.jsp">logout</a>
<%
		}
%>		

		</div>
		<div class="clear"></div>
		<!-- 로고들어가는 곳 -->
		<div id="logo">
			<img src="<%=contextPath %>/images/logo.gif" width="265" height="62" alt="Fun Web">
		</div>
		<!-- 로고들어가는 곳 -->
		<nav id="top_menu">
			<ul>
				<li><a href="<%=contextPath %>/index.jsp">HOME</a></li>
				<li><a href="<%=contextPath %>/company/welcome.jsp">COMPANY</a></li>
				<li><a href="#">SOLUTIONS</a></li>
				<li><a href="<%=contextPath %>/center/notice.jsp">CUSTOMER CENTER</a></li>
				<li><a href="#">CONTACT US</a></li>
			</ul>
		</nav>
	</header>
	<!-- 헤더들어가는 곳 -->