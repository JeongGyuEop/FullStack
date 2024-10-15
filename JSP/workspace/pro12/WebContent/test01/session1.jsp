<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//SessionTest라는 이름의 서블릿에서 <a>태그를 클릭하여
//요청받은 서버페이지 session1.jsp입니다.
//이때~ 같은 웹브라우저를 사용하므로 HttpSession내장객체 메모리를 공유받아 사용할수 있다
	
//확장자가 .jsp인 서버페이지에서 HttpSession내장객체 메모리에 접근할떄는
//서블릿 페이지에서는 request.getSession()메소드를 호출하여 얻었지만
//jsp페이지에서는 참조변수 session라는 이름으로  
//HttpSession객체 메모리에 바로접근해서 사용합니다

   //공유받은 session내장객체 메모리에 접근해서 
   //이름이 name으로 저장된 "이순신"값을 꺼내오자 
   String name = (String)session.getAttribute("name");//"이순신" 얻는다.

   //추가로 기존 공유받은 session내장객체 메모리에 
   //address 이름과 함꼐 "서울시 강남구" 한쌍을 바인딩 하자 
   session.setAttribute("address", "서울시 강남구");
   
%>    
    
    이름은 <%=name%> 입니다. <br>
  <a href="session2.jsp">두번째 session2.jsp요청하여 보여줘~</a>
  
  
  
  
  
  
  
  
  
  
  
  
    
    
    
    

    
    
    
    
    
    