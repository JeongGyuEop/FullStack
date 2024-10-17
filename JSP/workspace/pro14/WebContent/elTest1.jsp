<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>표현언어로 여러가지 자료형 출력하기</h1>

	<h1>
		 \${100}:    ${100} <br> 
	
		 \${"안녕하세요"}:  ${"안녕하세요"} <br>
		 
		 \${10+1} : ${10+1} <br> 
	
		 <%--숫자형 문자열과 실제숫자를 더하는 수식을 작성하면
		 	 문자열을 자동으로 숫자로 형변환한 후 더합니다.--%>
		 \${"10"+1} : ${"10"+1} <br> 
		 
		 <%--
		 	null데이터와 10을 더하는 연산식을 EL표현언어에 작성하여 출력해보자
		 	null데이터와 10을 더하는 연산을하면 10이 출력됩니다.
		  --%>
		 \${null + 10} : ${null + 10} <br>
		 
		 
		 <%--문자형태의 문자열과 숫자는 더하여 EL표현언어로 출력할수 없다. --%>
		 <%-- \${"안녕" + 11} : ${"안녕" + 11} <br> --%>
		 
		 
		 <%--문자형태의 문자열끼니 하나의 문자열로 결합하는식을 EL표현언어에 작성할수 없다 --%>
		 <%-- \${"hello"+"world"} : ${"hello" + "world"} <br> --%>
		
		
	</h1>




</body>
</html>





