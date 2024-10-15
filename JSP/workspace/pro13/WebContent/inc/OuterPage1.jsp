<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포함될 외부 JSP 파일</title>
</head>
<body>
	<h2>OuterPage1.jsp 외부파일 1</h2>
	<%
		String newVar1 = "고구려 세운 동명왕";
	%>
	<%-- page와 request 내장객체 영역에서 바인딩된 값 얻어 출력 --%>
	<ul>
		<li>page 내장 객체 영역 속성 : <%=pageContext.getAttribute("pAttr") %></li>
		<li>request 내장 객체 영역 속성 : <%=request.getAttribute("rAttr") %></li>
	</ul>
</body>
</html>