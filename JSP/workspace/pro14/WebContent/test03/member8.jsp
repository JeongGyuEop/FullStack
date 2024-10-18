<%@page import="sec01.ex02.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 중에서 core라이브러리에 속한 태그를 불러와서 사용하기위해  taglib 디렉티브태그 작성 --%>        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); 

   List membersList = new ArrayList();

   membersList.add( new MemberBean("son","1234","손흥민","son@test.com") );
   membersList.add( new MemberBean("ki","4321","기성용","ki@test.com") );
   membersList.add( new MemberBean("park","1212","박지성","park@test.com") );   
%>    
<%-- 위 자바코드로 생성한  ArrayList배열을  
     JSTL의 c:forEach에서 사용하기 위해  c:set태그로 변수를 만들어서 배열을 저장   --%>
<c:set var="lists" value="<%=membersList%>" scope="page"/>    
  
<ul>
	<c:forEach  var="i" begin="0"  end="${lists.size()-1}" step="1">
		
		<c:set var="mb" value="${lists[i]}"  />
		
		<li>
			아이디 : ${mb.id},
			비밀번호 : ${mb.pwd},
			이름: ${mb.name},
			이메일:${mb.email}
		</li>	

	</c:forEach>
</ul>	











