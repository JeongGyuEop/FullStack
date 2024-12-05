<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
    
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	
	<!-- JQUERY 항상 최신 CDN -->
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript">
		$(function(){	
			$("#checkJson").click(function(){
		
			//회원 정보를 JSONObject { } 객체로 생성합니다
				var member = { id:"park", name:"박지성", pwd:"1234", email:"park@test.com"};
				
				$.ajax({
						 type:"post",
						 url:"${contextPath}/test/info",  
						 contentType: "application/json",
						 data:JSON.stringify(member), 
						 // "{ id:'park', name:'박지성', pwd:'1234', email:'park@test.com'}"			 
						//참고
						//JSONObject { } 객체를   문자열 "{ }"형태로 변환시   JSON클래스의 strtingify( {}객체전달 ) 메소드 호출!하면 
						// 문자열 "{ }"형태로 반환 해 줍니다.
						success:function(data,textStatus){
							console.log(data);
						},
						error:function(data,textStatus){
							alert("통신 에러 발생!" + textStatus);
						}
				});
				
				
			});
			
		});
	</script>
</head>
<body>
	<%--
		회원 정보 보내기 버튼을 클릭하면 Ajax를 이용해 회원정보를 JSON으로 만들어서  컨트롤러로 전송합니다.
	 --%>
	<input type="button" id="checkJson" value="회원 정보 보내기"/> <br><br>
	<div id="output"></div>


</body>
</html>