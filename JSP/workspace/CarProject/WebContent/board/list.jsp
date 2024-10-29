
<%@page import="Vo.BoardVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String contextPath = request.getContextPath(); 
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">

	// 아래의 검색어를 입력하지 않고 검색버튼을 눌렸을 때
	// 검색어 입력하지 않으면 검색어를 입력하세요! -> 체크하는 함수
	function fnSearch(){
		
		// 입력한 검색어 얻기
		var word = document.getElementById("word").value;
		
		// 검색어를 입력하지 않았다면
		if(word === null || word === ""){
			// 검색어 입력 메시지창 띄우기
			alert("검색어를 입력하세요.");
			// 검색어를 입력하는 <input>에 간제 포커스를 주어 검색어를 입력하게 유도함
			document.getElementById("word").focus();
			// form 태그의 action 속성에 작성된 BoardController 서버페이지 요청 차단
			return false;
		}
		else{ // 검색어를 입력했다면
			
			// form 태그를 선택해서 가져와 action 속성에 작성된 주소를 이용해
			// BoardController로 입력한 검색어에 관한 글목록 조회 요청을 한다.
			document.frmSearch.submit();
		}
	}
	
	
</script>
</head>
<body>
	
<%

	int totalRecord = 0; // 조회된 총 글의 갯수 저장

	// BoardController에서 request에 바인딩 한 ArrayList 배열을 꺼내온다.
	// 조회된 글목록 정보 얻기
	ArrayList list = (ArrayList)request.getAttribute("list");

	// 조회된 글의 총 갯수 저장
	totalRecord = list.size();

	String id = (String)session.getAttribute("id");

%>
	
<table width="97%" border="0" cellspacing="0" cellpadding="0">
	<tr height="40"> 
		<td width="46%" style="text-align: left"> 
			&nbsp;&nbsp;&nbsp; <img src="<%=contextPath%>/board/images/board02.gif" width="150" height="30">
		</td>
	</tr>
	<tr> 
		<td colspan="3">
			<div align="center">
				<img src="<%=contextPath%>/board/images/line_870.gif" width="870" height="4">
			</div>
		</td>
	</tr>
	<tr> 
		<td colspan="3" valign="top">
			<div align="center"> 
	    	<table width="95%" border="0" cellspacing="0" cellpadding="0">
	        	<tr> 
	        		<td colspan="4" style="height: 19px">&nbsp;</td> 
	        	</tr>
	        	<tr>
	        		<td colspan="4" style="height: 19px">
	        			<img src="<%=contextPath%>/board/images/ink.gif" width="875" height="100">
	        		</td>
	        	</tr>
	        	<tr> 
	        		<td colspan="4">
						<table border="0" width="100%" cellpadding="2" cellspacing="0">
							<tr align="center" bgcolor="#D0D0D0" height="120%">
								<td align="left">번호</td>
								<td align="left">제목</td>
								<td align="left">이름</td>
								<td align="left">날짜</td>
								<td align="left">조회수</td>
							</tr>
					<%
						// board 테이블에서 조회된 게시글이 없다면?
						if(list.isEmpty()) {
					%>
							<tr align="center">
								<td colspan="5"> 등록된 글이 없습니다. </td>
							</tr>			
					<%
						} else {// board 테이블에서 조회된 게시글이 있다면?
							for(int i=0; i<totalRecord; i++) {
								
								BoardVo vo = (BoardVo)list.get(i);
					%>
							<tr align="center" height="120%">
								<td align="left"><%=vo.getB_idx() %></td>
								<td align="left"><%=vo.getB_title() %></td>
								<td align="left"><%=vo.getB_name() %></td>
								<td align="left"><%=vo.getB_date() %></td>
								<td align="left"><%=vo.getB_cnt() %></td>
							</tr>
						
					<%			
							}
						}
					%>
										
						</table>
	        		</td>
	        	</tr>
	        	<tr>
	        		<td colspan="4">&nbsp;</td>
	        	</tr>
	        	<tr>
	        		<td colspan="4">&nbsp;</td>
	        	</tr>
				<tr>
					<form action="<%=contextPath%>/Board/searchlist.bo" 
							method="post" 
							name="frmSearch" onsubmit="fnSearch(); return false;">
	            	<td colspan="2">
	            		<div align="right"> 
		            		<select name="key">
		              			<option value="titleContent">제목 + 내용</option>
		              			<option value="name">작성자</option>
		              		</select>
		              	</div>
	              	</td>
		            <td width="30%">
		            	<div align="center"> &nbsp;
		            		<input type="text" name="word" id="word"/>
		            		<input type="submit" value="검색"/>
		            	</div>
		            </td>
		            </form>
	           
	           		<%-- 새 글쓰기 버튼 이미지 --%>
	           		<td width="38%" style="text-align:left">
	           		<%
	           			// 로그인을 하면 새글쓰기 <input>이 보이게 설정
	           			if(id != null){ 
	           		%>
	           				<input type="image" 
	           						id="newContent"
	           						src="<%=contextPath %>/board/images/write.gif"
	           						onclick="location.href='<%=contextPath %>/Board/write.bo'"
	           				/>	
	           		<%		
	           			}
	           		%>	
	           			
	           		</td>
	       		<tr>
	       			<td colspan="4">&nbsp;</td>
	       		</tr>
			</table>
			</div>
	 	</td>
	</tr>
	<tr align="center"> 
		<td colspan="3" align="center">Go To Page</td> 
	</tr>
</table>
</body>
</html>





















