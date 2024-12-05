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
			//아래의 새글 쓰기 <input>을 선택해서 가져와  클릭이벤트 등록 !
		  	//$("#checkJson1").click(function(){
			//글 수정 하기 <input>을 선택해서 가져와 클릭이벤트 등록 !  
			$("#checkJson2").click(function(){	
				//DB에 추가할  새글 정보를 JSONObject로 생성 합니다.
				//또는
				//DB에 수정할  글 정보를 JSONObject로 생성 합니다.
				var article = {articleNO:"114", writer:"박지성", title:"안녕하세요", content:"상품 소개 글입니다."};
				
				$.ajax({
					/*
						type:"POST",
						url: "${contextPath}/boards", //새글 정보를 추가 해줘~ 요청! 
					*/	
						 type:"PUT",
						url: "${contextPath}/boards/114",  // 114번 글의 정보를 수정 요청! 
						
						contentType:"application/json",
						data:JSON.stringify(article),
						success:function(data){
							console.log(data);
						},
						error:function(data,textStatus){
							alert("에러가 발생했습니다." + textStatus);
						}				
				});		
				
			});
		});
	</script>

</head>
<body>
	<%-- 
		 새글 등록,  수정,  삭제에 사용할 화면 입니다.
		 Ajax요청시 type속성에는 메소드의 속성을 지정하고,
		          url속성에는 REST에서 지정한 URl형식으로 요청하도록 지정합니다.
	 --%>
	 
	 <input type="button" id="checkJson1" value="새글 쓰기"> <br><br>
	 <input type="button" id="checkJson2" value="글 수정하기"> <br><br>
	 
	 <div id="output"></div>
	 

</body>
</html>







