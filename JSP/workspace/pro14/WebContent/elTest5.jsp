<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- useBean액션태그를 이용해 MemberBean클래스의 객체(빈) 생성 --%>   
<jsp:useBean  id="m1"  class="sec01.ex01.MemberBean" scope="page"/>
  
<%-- setProperty액션태그를 이용해
	 MemberBean객체의 setName메소드 호출시 매개변수로  "이순신" 문자열을 전달해
	 name인스턴스변수에 저장 --%>  
<jsp:setProperty  name="m1"  property="name" value="이순신"/> 


<%-- useBean 액션태그를 이용해 java.util패키지에서 제공해주는 
     ArrayList클래스에 대한 배열객체 생성 --%>
<jsp:useBean  id="m2"  class="java.util.ArrayList" scope="page"/>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	empty 연산자 이용하여 EL표현언어 영역에 출력 <br>
	
	<h2>
		<%--
			MemberBean객체의 모든 인스턴스변수에 값이 저장되어 있지 안느냐?라고 물어봅니다
			값이 저장되어 있지않으면 true
			하나라고 값이 저장되어 있으면 false를 반환
			
			참고. m1참조변수가 가리키는 MemberBean객체 내부의 
			     name인스턴스변수에는 "이순신"값이 저장되어 있으므로 false를 반환
		 --%>
	
		 ${empty m1} <br> <%-- false --%>
		 
<%-- MemberBean객체의 모든 변수에 값이 하나라도 저장되어 있느냐? 라고 물어볼수도 있다 --%>
		 ${not empty m1} <br> <%-- true --%>

	
<%-- 위 ArrayList배열에  값이 저장되어 있지 않느냐?(비어 있느냐?)라고 물어볼때 
     empty ArrayList  --%>	
		  ${empty m2} <br> <%--값이 저장되어 있지 않기 떄문에 empty연산자는 true를 반환함 --%>
	
<%-- 위 ArrayList배열에 값이 하나라도 저장되어 있느냐? 
     not empty ArrayList --%>
		  ${not empty m2} <br><%--값이 하나라도 저장되어 있지 않기 때문에 false를 반환합니다. --%>
		  
	 
	 <br>
	 ${empty "hello"}<%-- 뒤 문자열 값이 없냐?라고 물어보기 떄문에 있기 때문에 false를 반환 --%>
	 <br>
	
	 ${empty null}	<%-- empty연산자 뒤에 null을 넣으면 무조건 true를 반환 --%>
	 <br>
	 
	 ${empty ""}  <%-- empty연산자 뒤에 빈공백문자열""을 넣으면 무조건 true를 반환  --%>
	 
	
	
	</h2>



</body>
</html>










