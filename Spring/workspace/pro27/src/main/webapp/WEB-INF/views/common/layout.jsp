<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%--  화면 전체 구조를 정의 해 놓은 탬플릿 역할을 하는  layout.jsp 파일입니다.   --%> 
        
           
<%--  타일즈 태그들을 사용하기 위해 외부라이브러리 주소 등록 --%>        
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"  %>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
		 <%-- tiles_member.xml의 <definition>의 하위 태그인 <put-attribute>태그의 name이 title인 value의 값을 표시합니다. --%>
<title>  <tiles:insertAttribute name="title"/>  </title>


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



</head>
<body>
	<div id="container">
	
		<div id="header">			
			<%-- tiles_member.xml의 <definition>의 하위 태그인 <put-attribute>태그의 name이 header인 value의 주소값
			     /WEB-INF/views/common/header.jsp의 디자인 코드를 표시합니다. --%>
			<tiles:insertAttribute  name="header" />	
		</div>
	
		<div id="sidebar-left">
			<%-- tiles_member.xml의 <definition>의 하위 태그인 <put-attribute>태그의 name이 side인 value의 주소값
			     /WEB-INF/views/common/side.jsp의 디자인 코드를 표시합니다. --%>
			<tiles:insertAttribute name="side" />		
		</div>
		
		<div id="content">
			<tiles:insertAttribute name="body"/>   <%--본문 영역에 들어갈 jsp경로 지정 하여 표시 하는데 지정하지 않음  --%>
		</div>
	
		<div id="footer">
			<tiles:insertAttribute name="footer"/>
		</div>

	</div>

</body>
</html>








