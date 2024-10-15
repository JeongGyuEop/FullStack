<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	include1.jsp의 상단 영역의 코드입니다.
	
	<br>
		
		
		<%-- 
			include 액션태그 코드를 톰캣서버가 만나면 
			page 속성에 작성한 서버페이지를 재요청해서 실행한 코드 내용을
			웹 브라우저로 전달해서 보여준다.
			재요청시 request 객체 메모리를 duke_image.jsp에 공유할 수 있기
			때문에 request 객체 메모리에 바인딩 후 재요청해서 공유한다.
			
			include액션태그에 의해서 duke_image.jsp 재요청시~ request 객체 
			메모리에 요청하는 값을 추가하여 바인딩 할때 사용되는 태그가 
			jsp:param 액션태그이다.
		--%>
		<jsp:include page="duke_image.jsp" flush="true">
			
			<jsp:param value="duke" name="name"/>
			<jsp:param value="duke.png" name="imgName"/>
		
		</jsp:include>
	
	<br>
	
	include1.jsp의 하단 영역의 코드입니다.
</body>
</html>