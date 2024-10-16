<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/subpage.css" rel="stylesheet" type="text/css">

</head>
<body>
	<div id="wrap">
	
		<!-- 헤더가 들어가는 곳 -->
		<jsp:include page="../inc/top.jsp" />  
		<!-- 헤더가 들어가는 곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 메인이미지 -->
		<div id="sub_img_center"></div>
		<!-- 메인이미지 -->

		<!-- 왼쪽메뉴 -->
		<nav id="sub_menu">
			<ul>
				<li><a href="#">Notice</a></li>
				<li><a href="#">Public News</a></li>
				<li><a href="#">Driver Download</a></li>
				<li><a href="#">Service Policy</a></li>
			</ul>
		</nav>
		<!-- 왼쪽메뉴 -->


<%
	// 글쓰기 화면에 글 작성자명을 <input>에 출력해주기 위해
	// session 내장 객체 영역에 저장된 로그인된 아이디를 얻자
	String id = (String)session.getAttribute("id");
	
	// session 아이디 값이 없으면?
	if(id == null) {
		response.sendRedirect("../member/login/jsp");
	} // 안적어도 상관은 없으..

%>
		<!-- 게시판 -->
		<article>
			<h1>Notice Write</h1>
			<form action="writePro.jsp" method="post">
				<table id="notice">
					<tr>
						<td>글 작성자</td>
						<td><input type="text" name="name" value="<%=id %>" readonly></td>
					</tr>
					<tr>
						<td>글 비밀번호</td>
						<td><input type="password" name="passwd"></td>
					</tr>
					<tr>
						<td>글 제목</td>
						<td><input type="text" name="subject"></td>
					</tr>
					<tr>
						<td>글 내용</td>
						<td><textarea name="content"></textarea></td>
					</tr>
				</table>		
				
				<div id="table_search">
					<input type="submit" value="글쓰기" class="btn">
					<input type="reset" value="다시입력" class="btn">
					<input type="button" value="글목록" class="btn" onclick="location.href='notice.jsp'">
				</div>
				
			</form>
			

			<div class="clear"></div>
			<div id="page_control"></div>
		</article>
		<!-- 게시판 -->
		<!-- 본문들어가는 곳 -->
		<div class="clear"></div>
		
		<!-- 푸터들어가는 곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터들어가는 곳 -->
	</div>
</body>
</html>