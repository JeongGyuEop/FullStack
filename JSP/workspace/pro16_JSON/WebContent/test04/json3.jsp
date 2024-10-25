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
				
				// JSON을 문자열 형태로 만들자
				let jsonStr = '{ "members":['	
					+ '{"name":"박지성", "age":"25", "gender":"남자", "nickname":"날센돌이"},'
					+ '{"name":"손흥민", "age":"30", "gender":"남자", "nickname":"탱크"}'
					+ ']}';
				
				// JSON 문자열을 JSONObject로 변환해서 저장
				let jsonInfo = JSON.parse(jsonStr);
			//	{ "members":[
			//		{"name":"박지성", "age":"25", "gender":"남자", "nickname":"날센돌이"},'
			//		{"name":"손흥민", "age":"30", "gender":"남자", "nickname":"탱크"}'
			//	]}
			
				let output = "회원정보<br>";
				output += "==================<br>";
				
				for(let index in jsonInfo.members) {
					output += "이름 : " + jsonInfo.members[index].name + "<br>"; 
					output += "나이 : " + jsonInfo.members[index].age + "<br>"; 
					output += "성별 : " + jsonInfo.members[index].gender + "<br>"; 
					output += "별명 : " + jsonInfo.members[index].nickname + "<br>"; 
					output += "<br><br>"
				}
				
				
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


















