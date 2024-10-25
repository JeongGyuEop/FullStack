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
				
				// JSON 데이터를 문자열로 만들어서 저장
				let jsonStr = '{"name":["홍길동", "이순신", "임꺽정"]}';
				//				 "키"  :           배열 값
				
				// JSON 형태의 문자열을 JSONObject 변환해서 저장
				// 참고. JSON.parse() 내장 함수 이용
				let jsonInfo = JSON.parse(jsonStr);
				// alert(jsonInfo);
				// console.log(jsonInfo);
			
				// JSONObject 를 JSON 형태의 문자열로 변환해서 저장
				let s = JSON.stringify(jsonInfo);
				alert(s); // {"name":["홍길동","이순신","임꺽정"]} <- String
				// console.log(s);
				
				let output = "회원이름<br>";
					output += "==================<br>";
				
				for( let index in jsonInfo.name){
								// ["홍길동", "이순신", "임꺽정"]
								//      0         1         2
					output += jsonInfo.name[index] + "<br>";
					// {"name":["홍길동","이순신","임꺽정"]}
				
				}	
				
				$("#output").html(output)
			});
			
		});
	
	
	</script>


</head>
<body>
	<a id="checkJson" style="cursor:pointer;">출력</a>  <br><br>
	<div id="output"></div>

</body>
</html>


















