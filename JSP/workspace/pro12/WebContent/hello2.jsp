<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 
	스크립틀릿 태그 <% %>
		- 동적인 데이터를 자바코드로 넣는 영역을 나타내는 태그
		- 문법
			<% 자바코드 작성; %>

 --%>
 
 <%-- 선언문 태그 영역에 변수선언 및 메소드를 선언해 놓자 --%>
 <%!
 	// 전역변수 선언
 	String name = "이순신";
 
 	// 메소드 선언
 	public String getName() {
 		return name;
 	}
 
 %>
 
 <%-- 스크립틀릿 태그 영역에 클라이언트가 hello2.jsp 요청시
 	  전달한 요청한 값을 HttpServletRequest 객체 메모리에서 얻기 --%>
 <%
 	
 	// 순서 1. 클라이언트의 웹브라우저 주소창에 
 	//		   http://localhost:8090/pro12/hello2.jsp?age=22 요청주소를 입력하여
 	//		   hello2.jsp 서버페이지를 톰캣 서버에게 요청한다.
 	// 그러면 톰캣 서버는 요청 주소 하나당 ~ request 객체 메모리를 생성 후
 	// ? 뒤에 적은 요청한 값 age=22를 request 객체 메모리에 담아 request 객체 메모리를
 	// 공유해 준다.
 	
 	// 순서 2. 클라이언트가 요청한 값! request 객체 메모리로 부터 얻기
 	String age = request.getParameter("age");
 	// 참고. 스크립틀릿 태그 영역 안에 작성한 age 변수는 _jspService 메소드 내부에
 	//       지역변수로 만들어지기 때문에 String age 변수 선언 위치의 아래 줄에서
 	//		 age 변수에 저장도니 값을 불러와서 사용할 수 있다.
 	
 	/*
 		스크립틀릿 태그 영역 안에는 메소드 선언이 불가능하다!
 		메소드 선언은 선언문 태그 영역안에 선언해야 한다.

 		public void test() {
 			System.out.println("test");
 		}
 	*/
 
 %>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 표현식 태그를 이용해서 name 전역변수와 age 지역변수에 저장된 값을 불러와 응답(출력) --%>
	<h1>안녕하세요. <%= getName() %>님!!</h1>
	<h1>나이는 <%= age %>세 입니다!</h1>
</body>
</html>