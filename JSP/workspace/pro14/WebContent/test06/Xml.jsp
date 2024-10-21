<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 중에서 core태그들을 사용하기 위해 주소를 import --%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>    
    
<%-- JSTL 중에서 xml태그들을 사용하기 위해 주소를 import --%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"  prefix="x" %>       
    
<% request.setCharacterEncoding("UTF-8"); %>         
 
 
 <%--
 	  JSTL의 XML태그들
 	  - XML문서파일의 내용을 처리 하기 위한 것으로, 
 	     xml데이터를 파씽 및 출력, 흐름제어등의 기능을 사용할수 있는 태그들을 제공합니다.
 	     
 	  -  xml태그들 종류
 	  
 	  	 태그명			기능
 	  	 out			select속성에 지정한 XPath표현식의 결과를 출력하는 태그
 	  	 parse			XML을 파싱할때 사용합니다.
 	  	 forEach		select속성에 지정한 반복되는 노드를 파싱할때 사용하는 태그
 	  	 if				select속성에 지정한 XPath표현식의 값을 하나의 조건으로 결정하는 태그
 	  	 choose			select속성에 지정한 XPath표현식의 값을 다중 조건으로 결정하는 태그로  하위태그로 when, otherwise태그를 사용함
 	  	 	  	 
 	  	 
 	  파싱이란?
 	  	데이터를 분석하여 원하는 데이터를 특정 패턴이나 순서로 추출해 가공하는 것을 말합니다.	 
 	        쉽게 말하면  XML데이터나 HTML등으로 구성된 데이터를 분석해서 내가원하는 부분만 추출 하는것을 말합니다.

  --%>   
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- test06폴더에 만들어 놓은 BookList.xml파일의 데이터들을 현재 페이지에서 사용하기 위해 경로를 import해 놓습니다. --%>
	<c:import url="BookList.xml" var="booklist"  charEncoding="UTF-8" />
	
  <%--
   아래와 같이  변수 booklist를 만들어  전체를 문자열로 저장해 놓고 사용할수도 있다.	
	<c:set var="booklist">
		<booklist>
			<book>
				<name>사피엔스</name>
				<author>유발 하라리</author>
				<price>19800</price>
			</book>
			<book>
				<name>총,균,세</name>
				<author>제러드 다이아몬드</author>
				<price>25200</price>
			</book>
		</booklist>
	</c:set>
 --%>
 
 	<%-- 위 선언한 booklist변수에 저장된 전체 XML데이터를 파씽해 줄 <x:parse>태그를 만듭니다. --%>
	<x:parse  xml="${booklist}" var="blist"  />
	
	<h4>파싱 1</h4>
	제목 : <x:out select="$blist/booklist/book[1]/name" /> <br>
	저자 : <x:out select="$blist/booklist/book[1]/author" /> <br>
        가격 : <x:out select="$blist/booklist/book[1]/price"/> <br>
	

	<h4>파싱 2</h4>
	<table border="1">
		<tr>
			<th>제목</th>
			<th>저자</th>
			<th>가격</th>
		</tr>
	<x:forEach select="$blist/booklist/book" var="item">
		<tr>
			<td><x:out select="$item/name" /> </td>
			<td><x:out select="$item/author"/> </td>
			<td>
				<x:choose>
					<x:when select="$item/price >= 20000"> 2만원 이상<br> </x:when>
					<x:otherwise>2만원 미만 <br> </x:otherwise>
				</x:choose>
			</td>
		</tr>
	</x:forEach>
	</table>
	
	<h4>파싱 3</h4>
	<table border="1">

	<x:forEach select="$blist/booklist/book" var="item">
		<tr>
			<td><x:out select="$item/name" /> </td>
			<td><x:out select="$item/author"/> </td>
			<td><x:out select="$item/price"/> </td>
			<td>
				<%-- 책제목이 총,균,세 와 같으면?  구매함 출력!--%>
				<x:if select="$item/name = '총,균,세'"> 구매함 </x:if>
			</td>	
		</tr>
	</x:forEach>
	</table>



</body>
</html>















