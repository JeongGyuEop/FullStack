<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		// 1. 한글처리
		request.setCharacterEncoding("utf-8");
	
		// 2. join.jsp에서 function winopen() 함수에 의해서 전달받은
		//    입력한 아이디 얻기
		// 3. 밑의 아이디 중복확인 버튼을 클릭하면 <form> 태그에 의해서
		//    join_IDcheck.jsp 요청 시 전달받는 입력된 아이디 얻기
		String id = request.getParameter("userid");

		// 4. MemberDAO 클래스의 객체를 생성해서
		//    idCheck 메소드를 호출 시 매개변수로 입력한 아이디를 전달해
		//    데이터베이스에 있는지 없는지 유무를 확인
		MemberDAO mdao = new MemberDAO();
		
		// 아이디 중복 체크 유무값 가져오기
		// check==1 이면 "사용중인 ID 입니다." // 아이디가 데이터베이스에 저장되어 있음
		// check==0 이면 "사용가능한 ID 입니다." // 아이디가 데이터베이스에 저장되어 있지 않음		
		int check = mdao.idCheck(id);
		
		// 5. check 변수가 1이면 "아이디 중복" 웹브라우저 응답
		//	  check 변수가 0이면 "사용가능함" 웹 브라우저에응답		
		if( check == 1 ) {
			out.println("아이디 중복");
		} else {
			out.println("사용가능함");
		}	
	%>
	
	<%-- 6. 사용가능한 아이디이면 사용함 버튼을 눌러서 부모 창(join.jsp)에 사용가능한 아이디 보여주기 --%>
	<input type="button" value="사용함" onclick="result();">
	
	<form action="join_IDcheck.jsp" method="post" name="nfr">
		아이디 : <input type="text" name="userid" value="<%=id %>">
		<input type="submit" value="아이디중복확인"> 	
	</form>
	
	<script type="text/javascript">
		// 7. '사용가능함' 버튼을 클릭했을 때 호출되는 함수로
		//    바로 위 input에 입력한 사용가능한 아이디를 얻어
		//    부모 창(join.jsp)페이지에 적힌 아이디 입력 input의
		//    value 속성의 값으로 설정해서 보여주자
		function result() {
			// join.jsp 부모 창의 아이디를 입력하는 <input value=""> = join_IDcheck.jsp 자식팝업창의 
			// input에 입력된 value 값 얻자
			opener.document.fr.id.value = document.nfr.userid.value;
		
			// 팝업창 닫기
			window.close();
		}
	</script>
	
</body>
</html>