<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    
    	// include1.jsp 페이지에서 작성한
    	// <jsp:include>액션태그에 의해 재요청당한 duke_image.jsp파일입니다.
    	// 재요청하여 공유받은 request 객체 메모리에 접근해서 재요청할때의 
    	// 요청한 값을 꺼내서 사용한다.
    	
    	//1. 요청한 데이터를 request 에서 꺼내오기 전에 인코딩 방식 설정
    	request.setCharacterEncoding("utf-8");
    	
    	// 2. 재요청시 요청한 데이터 얻기
    	// (jspLparam액션태그를 이용해 duke_image.jsp 재요청시
    	// 요청한 값은 request 내장 객체 메모리의 getParameter 메소드를 이용해서 얻는다.
    	
    	// <jsp:param value="duke" name="name"/> 액션태그에 작성한 name 속성값 "name" 작성
    	String name = request.getParameter("name");
    	// <jsp:param value="duke.png" name="imgName"/>액션태그에 작성한 name 속성값 "imgName" 작성
    	String imgName = request.getParameter("imgName");
    
    %>
    
    <br><br>
    <h1>이름은 <%=name %>입니다.</h1>
    <br><br>
    <img src="./image/<%=imgName %>" />