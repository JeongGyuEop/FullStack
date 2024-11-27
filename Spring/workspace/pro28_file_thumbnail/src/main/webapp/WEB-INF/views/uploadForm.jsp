<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%> 

<!DOCTYPE html >
<html>
<head>
<meta "charset=utf-8">
<title>파일업로드 하기</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	//파일 업로드 name 값을 다르게 하는 변수입닌다.
	var cnt=1;
	
	//파일 추가를 클릭하면 동적으로 파일 업로드를 추가합니다. name 속성의 값으로 'file'+cnt를 설정함으로써 값을 다르게 해줍니다.
	function fn_addFile(){
		
		//id속성값이 d_file인 <div></div>요소 영역을 선택해서 가져와
		//<div></div>내부에 동적으로 만든 html태그 추가!
		$("#d_file").append("<br>"+"<input  type='file' name='file"+cnt+"' />");
		
		cnt++;
	}
</script>
</head>
<body>
<h1>파일 업로드 하기</h1>							   <!--  파일 업로드 요청시 encType속성은 반드시 multipart/form-data로 설정해야 합니다. -->
<form method="post" action="${contextPath}/upload" enctype="multipart/form-data">

	<label>아이디:</label>
    <input type="text" name="id"><br>	<!-- 텍스트 박스에 ID를 입력받아 전송합니다. -->
    
	<label>이름:</label>
    <input type="text" name="name"><br>	<!-- 텍스트 박스에 이름을 입력받아 전송합니다. -->
    
    <!-- 파일추가를 클릭하면 동적으로 파일 업로드를 추가합니다. -->
	<input type="button"  value="파일추가" onClick="fn_addFile()"/><br> 
	
	<!-- 파일 추가를 클릭하면 자바스크립트 fn_addFile()함수를 이용해 <div> 태그 안에 동적으로 만든 <input type='file' ../>추가할 영역입니다. -->
	<div id="d_file"> </div>
	
	
	<input type="submit"  value="업로드"/>
</form>
</body>
</html>

















