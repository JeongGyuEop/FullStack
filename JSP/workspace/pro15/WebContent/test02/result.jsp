<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
    
<%
	request.setCharacterEncoding("UTF-8");

    //result.jsp요청한  전체 URL주소 중에서 컨텍스트 주소 경로 얻기
    String contextPath = request.getContextPath(); //   "/pro15"

    //다운로드할 파일 이름을 request에서 얻기     //"duke.png"
    String file1 = URLEncoder.encode( request.getParameter("param1") , "UTF-8");
    								  //"duke2.png"
    String file2 = URLEncoder.encode( request.getParameter("param2") , "UTF-8");
   
%>    
    
    파일 내려받기1 : <br>
  <a href="<%=contextPath%>/download.do?fileName=<%=file1%>">파일다운로드요청</a><br>
  
   파일 내려받기2 : <br>
  <a href="<%=contextPath%>/download.do?fileName=<%=file2%>">파일다운로드요청</a><br>

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    