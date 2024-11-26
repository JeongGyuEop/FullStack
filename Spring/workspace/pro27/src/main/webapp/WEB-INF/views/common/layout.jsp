<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"
 %>

<!-- 
	 전체 화면 구조를 정의 하는 layout.jsp파일 입니다.
	 tiles_member.xml파일의 설정 코드에 따라서 각각의 위치에 JSP파일을 표시 합니다. 
 --> 
 
 
<!-- 타일즈를 사용하기 위해 접두사를 tiles로 정하여 태그들을 불러올수 있도록 설정 --> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <style>
      #container {
        width: 100%;
        margin: 0px auto;
        text-align:center;
        border: 0px solid #bcbcbc;
      }
      #header {
        padding: 5px;
        margin-bottom: 5px;
        border: 0px solid #bcbcbc;
        background-color: lightgreen;
      }
      #sidebar-left {
        width: 15%;
        height:700px;
        padding: 5px;
        margin-right: 5px;
        margin-bottom: 5px;
        float: left;
         background-color: yellow;
        border: 0px solid #bcbcbc;
        font-size:10px;
      }
      #content {
        width: 75%;
        padding: 5px;
        margin-right: 5px;
        float: left;
        border: 0px solid #bcbcbc;
      }
      #footer {
        clear: both;
        padding: 5px;
        border: 0px solid #bcbcbc;
         background-color: lightblue;
      }
      
    </style>
    
    <!-- tiles_member.xml의 <definition>태그 하위 태그인  <put-attribute>태그의 
         name속성값이 title인 value속성값을 표시 합니다. -->
    <title><tiles:insertAttribute name="title" /></title>
  </head>
    <body>
    <div id="container">
             
      <!-- tiles_member.xml의 <definition>태그 하위 태그인  <put-attribute>태그의 
       name속성값이 header인 value속성값 /WEB-INF/views/common/header.jsp 을 표시 합니다. -->
      <div id="header">
         <tiles:insertAttribute name="header"/>
      </div>
      
        <!-- tiles_member.xml의 <definition>태그 하위 태그인  <put-attribute>태그의 
         name속성값이 side인 value속성값 /WEB-INF/views/common/side.jsp 을 표시 합니다. -->
      <div id="sidebar-left">
          <tiles:insertAttribute name="side"/> 
      </div>
      
      
      <div id="content">
          <tiles:insertAttribute name="body"/>
      </div>
      
        <!-- tiles_member.xml의 <definition>태그 하위 태그인  <put-attribute>태그의 
         name속성값이 footer인 value속성값 /WEB-INF/views/common/footer.jsp 을 표시 합니다. -->
      <div id="footer">
          <tiles:insertAttribute name="footer"/>
      </div>
    </div>
  </body>
</html>




