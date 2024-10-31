<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹소켓 채팅</title>
</head>
<body>
	<script type="text/javascript">
	
		function chatWinOpen(){
			//chatId라는 id속성값을 가진 <input>선택해서 변수에 저장
			//:대화명 입력하는 <input>선택
			var id = document.getElementById("chatId");
			
			//입력 필드가 비어 있을 경우, 대화명 입력 요청 알림
			if(id.value == ""){
				alert("대화명을 입력 후 채팅창을 열어 주세요");
				id.focus();
				return;//함수 실행 중단
			}
			
			//입력한 대화명을 파라미터로전달한 ChatWindow.jsp를 새롭게 팝업창에 보여줌
			window.open("ChatWindow.jsp?chatId=" + id.value,
					     "",
					     "width=320,height=400");
			
			//팝업 새채팅 창이 열리면  대화명 입력 <input>에 빈공백을 주어 초기화
			id.value = "";
			
		}	
	</script>

	<h2>웹소켓 채팅 - 대화명 적용해서 채팅창 띄워주기</h2>

	대화명 : <input type="text" id="chatId">

	<button onclick="chatWinOpen();">채팅 참여</button>




</body>
</html>








