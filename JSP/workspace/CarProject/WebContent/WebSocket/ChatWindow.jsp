<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
/*
### WebSocket 클래스의 역할

JavaScript의 WebSocket 클래스는 웹소켓 통신을 위한 API를 제공하며, 
주요 역할과 기능은 다음과 같습니다.

1. **서버와의 양방향 통신 채널 생성**:
   - WebSocket 객체는 브라우저와 서버 간에 양방향 통신 채널을 만들어, **실시간 통신**을 가능하게 합니다.
   - HTTP와 달리 웹소켓은 한 번 연결되면, 클라이언트와 서버 간에 메시지를 주고받는 데 추가 요청이 필요 없습니다.

2. **이벤트 핸들러**:
   - 웹소켓은 통신 상태에 따라 여러 이벤트 핸들러를 제공합니다. 
   - onopen, onmessage, onclose, onerror 등 이벤트가 있어, 
         서버 연결 시 발생할 동작을 코드로 정의할 수 있습니다.
     - onopen : 웹소켓 연결이 열릴 때 발생하여 초기 설정 등을 처리할 수 있습니다.
     - onmessage: 서버에서 메시지를 받을 때 발생하여, 메시지 데이터를 처리할 수 있습니다.
     - onclose: 연결이 닫혔을 때 발생하여, 연결 종료 후 동작을 처리할 수 있습니다.
     - onerror: 통신 중 오류가 발생할 때 호출되어 오류 처리를 할 수 있습니다.

3. **메시지 전송 및 수신**:
   - send() 메서드를 통해 클라이언트가 서버로 메시지를 보낼 수 있으며, 
          서버에서 수신한 메시지를 `onmessage` 이벤트 핸들러에서 처리할 수 있습니다.
   - 이 양방향 메시지 기능을 통해 채팅, 알림, 게임 데이터 등의 실시간 통신을 지원합니다.

4. **상태 유지**:
   - 웹소켓은 브라우저와 서버 간의 연결 상태를 readyState 속성으로 유지하여, 
          현재 연결 상태를 확인하고 조작할 수 있습니다.
   - 연결 상태는 CONNECTING, OPEN, CLOSING, CLOSED의 네 가지 상태로 표현됩니다.

이와 같이 WebSocket 객체는 실시간 웹 애플리케이션에서 서버와 브라우저 간에 효율적인 양방향 통신을 관리하고, 
대화형 기능을 구현하는 데 중요한 역할을 합니다.
*/




/*	
	WebSocket 객체 생성
	- new WebSocket(주소)를 통해 웹소켓 객체를 생성하면,
	    지정된 서버 요청주소로 즉시 연결을 시도 합니다.
	- 이 연결은 서버페이지와의  양방향 통신 채널을 형성합니다

*/
	//웹 소켓 객체 생성 : JSP의 application내장객체를 통해 요청할 
	//					  채팅 서버페이지 주소로 웹소켓을 만들어 연결 
	var webSocket = new WebSocket("<%=application.getInitParameter("CHAT_ADDR")%>/ChatingServer");
										//ws://localhost:8090/CarProject/채팅서버파일 요청 매칭 주소

	//웹 소켓 채팅에 사용할 HTML(DOM)요소들( 대화창, 메세지 입력창, 대화명)	을 저장할 변수들								
	var chatWindow, chatMessage, chatId;									
										
    //웹브라우저가 ChatWindow.jsp파일의 모든 HTML태그들(DOM)을 로딩했을때
    window.onload = function(){						
    		//대화 내용이 표시될  대화창 영역 얻기 
			chatWindow = document.getElementById("chatWindow");								
			
    		//메세지 입력창 요소 얻기 
    		chatMessage = document.getElementById("chatMessage");
    		
    		//채팅 하는 사용자의 대화명 요소에서 대화명값 얻기 
    		chatId = document.getElementById("chatId").value;	
	}
	
    //메세지 전송 함수  : 채팅 사용자가 메세지 전송 버튼을 클릭하거나 엔터 키를 눌렀을때 호출됨
    function sendMessage(){
    	//사용자가 입력한 메세지를 대화창에서 얻어 오른쪾 정렬로 디자인 추가
    	chatWindow.innerHTML += "<div class='myMsg'>" + chatMessage.value  + "</div>";
    	
    	//웹 소켓 통로를 통해 메세지를 서버페이지로 전송,
    	//'대화명 | 메세지' 로  구분 (대화명과 입력한 채팅메세지를 | 기호로 구분하여 전송)
    	webSocket.send(chatId + '|' +  chatMessage.value);
    	
    	//메세지 입력 창 내용 초기화 (입력창을 비워줌)
    	chatMessage.value = "";
    	
    	//대화창 스크롤 막대바를 맨 아래로 이동하여 새로운 메세지가 나타나면 보이게 설정
    	chatWindow.scrollTop = chatWindow.scrillHeight;
    	
    }
	
	//서버와 웹 소켓 통로 연결을 종료하는 함수 :  사용자가 '채팅종료' 버튼을 클릭했을때 호출됨
	function disconnect(){
		webSocket.close(); // 웹 소켓 통로연결 끊기 (  웹브라우저, 서버페이지 연결 끊김 )
	}

    //메세지 입력창(<input>)에서 Enter키를 누르고 뗏을때 
    //자동으로 sendMessage함수를 호출하도록처리
	function enterKey(){
		//참고. Enter 키의 키코드값은 13으로 정해져 있다
		if(window.event.keyCode == 13){//Enter 키를 눌렀다면
			sendMessage();
		}
		
	}
    
    
    
//WebSocket 웹소켓 통로에 여러 이벤트가 발생했을때  자동으로 처리하는 이벤트핸들러 함수 설정
    
//1. 서버에 웹소켓 통로 연결이 성공적으로 이루어진 이벤트가
//   발생했을때 호출되는 이벤트 핸들러 함수 설정
webSocket.onopen = function(event){
	//대화창<div id="chatWindow"></div>에 연결 성공 메세지를 보여주기 위해 출력
	chatWindow.innerHTML += "웹소켓통로를 통한 서버페이지에 연결되었습니다.<br/>";
	
};   
//2. 웹소켓 통로와 연결된 서버페이지와의 연결이 종료될때의 이벤트가
//   발생하면 호출되는 이벤트 핸들러 함수설정
webSocket.onclose = function(event){
	//대화창에 연결 종료 메세지를 출력
	chatWindow.innerHTML += "웹 소켓통로를 통한 서버페이지와의 연결 종료되었습니다.<br/>";
};
//3. 웹소켓 통로와 통신중 오류가 발생하는 이벤트가
//   일어나면 호출되는 이벤트 핸들러 함수 설정
webSocket.onerror = function(event){
	alert(event.data); //오류 발생시 알림창에 오류메시지 표시 
	//대화창에 에러 메세지 출력
	chatWindow.innerHTML += "채팅 중에 에러가 발생하였습니다.<br/>";
};
//4. 서버페이지에서 웹소켓 통로를 통해 클라이언트가 보낸
//   메세지를 보내어서 여기로 수신했을떄(메아리)
webSocket.onmessage = function(event){
	//수신된 데이터 '대화명 | 메세지'를    '|'기준으로 분리하여 배열로 저장
	var message = event.data.split("|");
	//대화명을 sender 변수에저장
	var sender = message[0];
	//메세지 내용을 content 변수에 저장
	var content = message[1];
	
	if(content != ""){
		//수신된 메세지가 귓속말인지 확인(귓속말 메세지에 '/' 문자가 포함되어 있음)
		if(content.match("/")){
			//귓속말 사용자를 대상으로 한것인지 확인 (예:  '/사용자ID' 형식)
			if(content.match( ("/" + chatId) )){
				//대상이 맞으면 귓속말임을 표시하고 메세지를 대화창에 출력
				var temp = content.replace( ("/" + chatId)  , "[귓속말] : ");
				chatWindow.innerHTML += "<div>" + sender + "" + temp + "</div>";
			}
		}else{//수신된 메세지가 귓속말이 아니면
			//일반 메세지인 경우 대화창에 '대화명 : 메세지' 형식으로 표시
			chatWindow.innerHTML += "<div>" + sender + " : " + content + "</div>";			
		}	
	}
	//새메세지가 대화창에 추가되면 대화창의 스크롤막대바를 가장 아래로 이동시켜 
	//사용자가 새 메세지를 볼수 있도록 설정
    chatWindow.scrollTop = chatWindow.scrollHeight; 
	
	
};
    
    
    
										
</script>



<style>
/* 채팅 UI 요소 스타일 지정 */
#chatWindow { 
    border: 1px solid black;    /* 대화창 테두리를 검은색 실선으로 설정 */
    width: 270px;               /* 대화창 너비 설정 */
    height: 310px;              /* 대화창 높이 설정 */
    overflow: scroll;           /* 내용이 넘칠 때 스크롤바 표시 */
    padding: 5px;               /* 내부 여백 설정 */
}
#chatMessage { 
    width: 236px;               /* 메시지 입력창 너비 설정 */
    height: 30px;               /* 메시지 입력창 높이 설정 */
}
#sendBtn { 
    height: 30px;               /* 전송 버튼 높이 설정 */
    position: relative;         /* 전송 버튼 위치 조정을 위해 relative 포지션 설정 */
    top: 2px;                   /* 버튼을 약간 아래로 이동 */
    left: -2px;                 /* 버튼을 약간 왼쪽으로 이동 */
}
#closeBtn { 
    margin-bottom: 3px;         /* 종료 버튼의 하단 여백 설정 */
    position: relative;         /* 종료 버튼 위치 조정을 위해 relative 포지션 설정 */
    top: 2px;                   /* 종료 버튼을 약간 아래로 이동 */
    left: -2px;                 /* 종료 버튼을 약간 왼쪽으로 이동 */
}
#chatId { 
    width: 158px;               /* 대화명 입력창 너비 설정 */
    height: 24px;               /* 대화명 입력창 높이 설정 */
    border: 1px solid #AAAAAA;  /* 대화명 입력창 테두리 설정 */
    background-color: #EEEEEE;  /* 대화명 입력창 배경색 설정 */
}
.myMsg { 
    text-align: right;          /* 내 메시지를 오른쪽 정렬로 설정 */
}
</style>
</head>
<body>

<!-- 현재 채팅하는 사람의 대화명을 input에 표시 , 읽기전용으로 설정하여 수정 불가  -->
대화명 : <input type="text" id="chatId" value="${param.chatId}" readonly>

<!-- 채팅 종료 버튼 클릭시 disconnect()함수 호출 -->
<button id="closeBtn" onclick="disconnect();">채팅 종료</button>

<!-- 대화창, 수신된 메세지와 전송한 메세지가 표시 되는 영역 -->
<div id="chatWindow"></div>

<div>
	<!-- 메세지 입력창,  키보드 이벤트 발생시 enterKey() 함수 호출 -->
	<input type="text" id="chatMessage" onkeyup="enterKey();">
	
	<!-- 메세지 전송 버튼 , 클릭시 sendMessage() 함수 호출 -->
	<button id="sendBtn" onclick="sendMessage();">전송</button>
	
</div>



</body>
</html>











