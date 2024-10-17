<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  //한글처리 
  request.setCharacterEncoding("UTF-8");

  //회원가입요청창(memberForm.jsp)에서 
  //입력한 요청정보들이 저장된 request객체를 공유받아
  //추가로~~~~ 수동으로~~ 특정 데이터를 바인딩 해 놓자
  request.setAttribute("address", "서울시 강남구");
%>

<%-- member2.jsp로 디스패처 방식으로 포워딩 합니다 --%>
<jsp:forward page="member2.jsp" />
















