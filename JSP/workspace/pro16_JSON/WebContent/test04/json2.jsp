<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<%-- JQuery를 사용하기 위해 제이쿼리 홈페이지에서 jquery-latest.min.js 불러온다. --%>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<script type="text/javascript">
		
		$(function(){
			
			$("#checkJson").on("click", function(){
				
				// 문자열로 JSONObject 만들기
				let jsonStr = '{"name":"박지성", "age":25, "gender":"남자", "nickname":"날센돌이"}';
				
				// JSON 클래스의 parse() 함수를 호출해서 
				// jsonStr 변수에 저장된 문자열을 JSONObject로 변환해서 저장
				let jsonObj = JSON.parse(jsonStr);
			 // {"name":"박지성", "age":25, "gender":"남자", "nickname":"날센돌이"}
				
			 	let output = "회원정보<br>";
			 		output = "=============<br>";
			 		output += "이름 : " + jsonObj.name + "<br>";
			 		output += "나이 : " + jsonObj.age + "<br>";
			 		output += "성별 : " + jsonObj.gender + "<br>";
			 		output += "닉네임 : " + jsonObj.nickname + "<br>";
			
				$("#output").html(output);
			
			});
			
		});
	
	
	</script>


</head>
<body>
	<a id="checkJson" style="cursor:pointer;">출력</a>  <br><br>
	<div id="output"></div>





</body>
</html>


















