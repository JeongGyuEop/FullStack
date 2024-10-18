<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"  %>    
    
 <% request.setCharacterEncoding("UTF-8");  %>       
    
<%--
	 <c:catch>태그
	 
	 - 이 태그는 발생한 예외를 잡아 처리하는 역할을 하는 태그 입니다.
	      예외가 발생하면 지정한 변수에 에러 메세지가 저장되어 전달됩니다.

	- 문법
			<c:catch  var="변수명">
				실행코드 
			</c:catch>
 --%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h4>자바 코드에서의 예외</h4>
		<%
			int num1 = 100;	
		%>
		
		<%--만약 예외가 발생하면  c:catch태그가 예외를 잡아 eMessage변수에 저장합니다. --%>
		<c:catch var="eMessage">
			<%
			   int result = num1 / 0;	// 숫자를 0으로 나눌수 없으므로 수학연산관련 예외가 발생합니다.	
			%>
		</c:catch>
		
		예외 내용 : ${eMessage} 
		
		
		<h4>EL에서의 예외</h4>
		
		<c:set  var="num2" value="200"  />
		
		<%-- c:catch로 감싼  코드에서 예외가 발생하면  
		         예외 종류 메세지를 eMessage변수에 저장시킵니다. --%>
		<c:catch var="eMessage" >
			${"일" + num2} <%-- EL에서는 +연산자를 덧셈에만 사용할 수 있습니다. --%>
		</c:catch>
		
		예외 내용 : ${eMessage}
		
		
		<%--
		결론 :   
	    EL은 null이나 예외에 관대한 편이므로 
	    <c:catch>태그는 EL보다는 JSP에서 발생되는 예외를 처리할때 주로 사용됩니다.
		
		 --%>
		
		
		
		
</body>
</html>










