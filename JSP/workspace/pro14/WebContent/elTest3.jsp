<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>표현언어 EL안에서 비교 연산자 사용하여  식 작성하는 방법</h2>
<%--
표현언어에서 비교 연산자 사용 방법
	
1. 값이 같은지 비교할때는  ==  또는 eq  연산자를 사용합니다.
2. 값이 같지 않은지 비교할때는  != 또는 ne 연산자를 사용합니다.
3. 대소 비교시  > 와  <  또는  gt 와 lt도 각각 연산자로 사용할수 있습니다.
4. 대소 및 동등 비교를 동시에 할때는  >=   와   <= 연산자   또는   ge와    le도 각각 연산자로 사용할수 있습니다.
 --%>	
	\${10 == 10} : ${10 == 10} <br>
	\${10 eq 10} : ${10 eq 10} <br>

	\${"hello" ==  "hello"} : ${"hello" == "hello"} <br>
	\${"hello" eq  "hello"} : ${"hello" eq "hello"} <br>
	
	
	\${"hello" != "apple"}	: ${"hello" != "apple"} <br>
	\${"hello" ne "apple"}	: ${"hello" ne "apple"} <br>
	

	\${10 < 10} : ${10 < 10} <br>
	\${10 lt 10} : ${10 lt 10} <br>
	
	\${100 > 10} : ${100 > 10} <br>
	\${100 gt 10} : ${100 gt 10} <br>
	
	
	
	\${100 >= 10} : ${100 >= 10} <br>
	\${100 ge 10} : ${100 ge 10} <br>
	
</body>
</html>








