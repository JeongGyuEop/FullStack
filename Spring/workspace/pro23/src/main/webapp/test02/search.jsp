<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 검색창</title>
</head>
<body>
	<!-- 검색창에 입력한 값과 셀렉트 박스의 검색조건을 선택해 
		 서블릿으로 검색 요청을 하는 VIEW
	 -->
	 <form action="${pageContext.request.contextPath}/mem4.do">
	 	
	 	입력 : <input type="text" name="value"> <!-- 검색할 값 입력 -->
	 	
	 	<!-- 셀렉트 박스의 검색조건을 선택합니다. -->
	 	<select name="action">
	 		<option value="listMembers">전체</option>	
	 		<option value="selectMemberById">아이디</option>
	 		<option value="selectMemberByPwd">비밀번호</option>
	 	</select>
	 	<br>
	 	
	 	<input type="submit" value="검색">
	 	
	 </form>
	 
	 

</body>
</html>





