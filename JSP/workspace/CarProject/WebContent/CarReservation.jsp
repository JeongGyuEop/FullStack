<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<% request.setCharacterEncoding("UTF-8"); %>
<%
	// 컨텍스트 주소 얻기
	String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 자주 렌트 되는 차량 이미지 3개를 미리 보여주자 --%>
	<center>
		<table width="1000" >
			<tr>
				<td align="center" width="333">
					<img src="<%=contextPath %>/img/lfsonata.jpg" width="288">
				</td>
				<td align="center" width="333">
					<img src="<%=contextPath %>/img/k5.jpg" width="288">
				</td>
				<td align="center" width="333">
					<img src="<%=contextPath %>/img/avante.jpg" width="288">
				</td>
			</tr>
		</table>
		
		<%-- <차량 종류 보기> 이미지 --%>
		<h1><img src="<%=contextPath%>/img/ccs.jpg" height="50"></h1>
		
		<%-- 소형, 중형, 대형 중 기준을 선택해서 차량 정보 조회 요청하는 디자인 --%>
		<form action="<%=contextPath %>/Car/carcategory.do" method="post">
			<table width="400" align="center">
				<tr>
					<td width="100">차량 유형</td>
					<td width="100" height="50">
						<select name="carcategory">
							<option value="Small">소형</option>
							<option value="Mid">중형</option>
							<option value="Big">대형</option>
						</select>
					</td>
					<td align="center">
						<input type="submit" value="검색">
					</td>
					<td align="center">
						<input type="button" value="전체검색" 
								onclick="location.href='<%=contextPath %>/Car/CarList.do'">
					</td>
				</tr>
			</table>
		</form>
	</center>
	
</body>
</html>