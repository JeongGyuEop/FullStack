<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 중에서 core라이브러리에 속한 태그를 불러와서 사용하기위해  taglib 디렉티브태그 작성 --%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"    %>   
  
<%  request.setCharacterEncoding("UTF-8"); %>      
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL - choose/when/otherwise 태그 </title>
</head>
<body>
	<%-- number변수를 생성하고  문자열100 저장 !  number변수는 page내장객체에 저장  --%>
	<c:set  var="number" value="100" scope="page" />

	<h4>choose태그로 홀짝 판단하기</h4>
	
	<%-- number변수에 저장되어 있는 값이 짝수인지 물어보는 조건식을 작성하여   조건식에 만족하면 
	 	 "100"은 짝수 입니다 <- 출력
	 	
	         만약 조건식의 결과가 false반환 한다면 <c:otherwise>부분에    
	     "100"은 홀수 입니다. 출력되게 작성 --%>
	 <c:choose>
	 	<c:when test="${number % 2 == 0}"> 
	 		${number}은 짝수 입니다   
	 	</c:when>
	 	<c:otherwise>
	 		${number}은 홀수 입니다.
	 	</c:otherwise>
	 </c:choose>    
	     
	     
	<h4>국어, 영어, 수학점수를 입력하면 평균을 내어 학점 출력</h4>
	
	<form> <%-- 먼저 폼값을 전송하기 위해 <form>태그를 사용합니다. 
				전송방식과 URL을 입력하지 않았으므로 
				폼값은 현재 페이지로 쿼리 스트링을 통해 전송합니다. --%>
		국어 : <input type="text" name="kor"> <br>
		영어 : <input type="text" name="eng"> <br>
		수학 : <input type="text" name="math"> <br>
		<input type="submit" value="학점 구하기">
	
	</form>
	
	<%-- 현재 페이지에 첫 진입시에는 전송된 폼값이 없습니다. 
	          그래서 request에 입력한 값이 없다면 결과가 출력되지 않게 하기 위해 
	      c:if를 사용합니다.
	           국,영,수 점수 중 하나라도 입력하지 않았다면 결과가 출력되지 않게 하기      
		 
		 모든 과목의 점수를 입력되었는지 확인 
	--%>
	
	<c:if test="${ not (empty param.kor or  empty param.eng  or  empty param.math )  }">
		
		<!-- 평균 계산 -->
		<c:set var="avg"  value="${ (param.kor + param.eng + param.math) / 3 }"  />
		평균 점수는  ${avg} 으로
		
		<!-- 학점 출력 -->
		<c:choose>
			<c:when test="${ avg >= 90 }">A 학점</c:when>
			<c:when test="${ avg >= 80 }">B 학점</c:when>
			<c:when test="${ avg ge 70 }">C 학점</c:when>
			<c:when test="${ avg ge 60 }">D 학점</c:when>
			<c:otherwise>F 학점</c:otherwise>
		</c:choose>
		입니다.
	
	</c:if>
	
<%
/* 
int kor = 0;
int eng = 0;
int math = 0;
int avg = 0;

	if(request.getParameter("kor") != null && 
	   request.getParameter("eng") != null &&
	   request.getParameter("eng") != null){
	   
		kor = Integer.parseInt(request.getParameter("kor"));
    	eng = Integer.parseInt(request.getParameter("eng"));
    	math = Integer.parseInt(request.getParameter("math"));
	
		avg = (kor+eng+math) / 3; //평균
		
		out.print("평균 점수는  " + avg + "으로");
		
		if(avg >= 90){
			out.print("A 학점");
		}else if(avg >= 80){
			out.print("B 학점");
		}else if(avg >= 70){
			out.print("C 학점");
		}else if(avg >= 60){
			out.print("D 학점");
		}else{
			out.print("F 학점");
		}
		out.print("  입니다.");
	}
 */

%>	
</body>
</html>













