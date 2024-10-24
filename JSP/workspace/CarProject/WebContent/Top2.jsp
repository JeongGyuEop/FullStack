 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">


	<style>
/* 		전체 폰트 크기 반응형으로  설정1  */
		html {
    			font-size: 62.5%
		}
		
		
		#login{
			float: right;
			margin: 20px 64px 0 0;
			font-family: Arial,Helvetico,sans-serif;
			font-size: 20px;
		}
		
		/* a태그의 하이퍼링크 없애기, 글자색 설정 */
		#login a{
			text-decoration: none;
			color: #333;
		}
		
		/* a태그에 마우스를 올리는 이벤트가 발생하면 글자색 설정 */
		
		#login a:hover{
			color: #f90;
		}
		
		/* 메인 로고 이미지 div영역 스타일 주기 */
		#logo{
			float: left;
			width: 265px;
			margin: 60px 0 0 40px;
		}
	
	</style>

</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String contextPath = request.getContextPath();
%>
	
	<%-- login | join --%>
	<table width="100%" height="5">
		<tr>
			<td>
				<div id="logo">
				<%-- 메인 로고 이미지  --%>
					<a href="<%=contextPath %>/Car/Main"><img src="<%=contextPath %>/img/RENT.jpg" width="300" height="80"/></a>
				</div>
			</td>
			<td>
			
			</td>
			
			<td align="right" colspan="5">
				<%
					//Session내장객체 메모리 영역에 session값 얻기
					String id = (String)session.getAttribute("id");
					//Session에 값이 저장되어 있지 않으면?
					if(id == null){
				%>		
						<div id="login" align="center">
						
				          <button type="button" class="btn btn-warning" onclick="location.href='<%=contextPath%>/member/login.me'">로그인</button>
				          <button type="button" class="btn btn-warning" onclick="location.href='<%=contextPath%>/member/join.me?center=members/join.jsp'">회원가입</button> 
						  <button id="joinBtn" class="btn btn-info" onclick="location.href='<%=contextPath%>/Guestbook/GuestbookListAction.gb'">방명록</button>
							<nav class="navbar navbar-light">
									<div class="container-fluid">
									<form class="d-flex" action="<%=contextPath%>/Car/NaverSearchAPI.do">
										<input class="form-control me-2" type="search"
											id="keyword" name="keyword" placeholder="Search" aria-label="Search">
										<input type="hidden" id="startNum" name="startNum" value="1">	
										<button id="searchBtn" class="btn btn-outline-success" type="submit">search</button>
									</form>
								</div>
							</nav>
						</div>
				<%
					}else{
				%>		
						<div id="login">
							<%=id%>&nbsp;&nbsp;
							<button type="button" class="btn btn-warning" onclick="location.href='<%=contextPath%>/member/logout.me'">정보수정</button>&nbsp;&nbsp;
							<button type="button" class="btn btn-warning" onclick="location.href='<%=contextPath%>/member/logout.me'">로그아웃</button> 
							<button id="joinBtn" class="btn btn-info" onclick="location.href='<%=contextPath%>/Guestbook/GuestbookListAction.gb'">방명록</button>
							<nav class="navbar navbar-light">
									<div class="container-fluid">
									<form class="d-flex" action="<%=contextPath%>/Car/NaverSearchAPI.do">
										<input class="form-control me-2" type="search"
											id="keyword" name="keyword" placeholder="Search" aria-label="Search">
										<input type="hidden" id="startNum" name="startNum" value="1">	
										<button id="searchBtn" class="btn btn-outline-success" type="submit">search</button>
									</form>
								</div>
							</nav>
						</div>
				<%				
					}
				%>
				
			</td>
			
		</tr>
	</table>
	
	
	<%-- 메뉴 만들기 --%>
	<table width="100%" background="<%=contextPath %>/img/aa.jpg" height="5">
		<tr>
			<td align="center" bgcolor="red" width="20%">
				<a href="<%=contextPath %>/Car/bb?center=CarReservation.jsp">
					 <div style="font-size: 2.5rem; color: white;">예약하기</div>
				</a> <%--예약하기 --%>
			</td>
			<td align="center" bgcolor="red" width="20%">
				<a href="<%=contextPath %>/Car/cc?center=CarReserveConfirm.jsp">
					<div style="font-size: 2.5rem; color: white;">예약확인</div>
				</a> <%--예약확인 --%>
			</td>
			<td align="center" bgcolor="red" width="20%">
				<a href="<%=contextPath %>/board1/list.bo">
					<div style="font-size: 2.5rem; color: white;">자유게시판</div>
				</a> <%--자유게시판 --%>
			</td>
			<td align="center" bgcolor="red" width="20%">
				<a href="<%=contextPath %>/EventBoard/list.bo">
					<div style="font-size: 2.5rem; color: white;">이벤트정보</div>
				</a> <%--이벤트정보 --%>
			</td>
			<td align="center" bgcolor="red" width="20%">
				<a href="<%=contextPath %>/FileBoard/list.bo?nowBlock=0&nowPage=0">
					<div style="font-size: 2.5rem; color: white;">공지사항</div>
				</a> <%--공지사항게시판 --%>
			</td>
			
		</tr>
	</table>
	<!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
    -->
</body>
</html>






