<%@page import="Vo.CarListVo"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
request.setCharacterEncoding("UTF-8");
String contextPath = request.getContextPath();
request.setAttribute("contextPath", contextPath);

Vector<CarListVo> carList = (Vector<CarListVo>) request.getAttribute("v");

int j = 0;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CarList.jsp 조회된 차량 정보를 보여주는 VIEW</title>
</head>
<body>
    <center>
        <img src="<%= contextPath %>/img/cis.jpg">
        
        <form action="<%= contextPath %>/Car/carcategory.do">
            <table width="1000" height="470">
                
<% for (CarListVo vo : carList) { %>
		    <% if (j % 4 == 0) { %>
		        <tr align="center">
		    <% } %>
		    <td>
		        <a href="<%= contextPath %>/Car/CarInfo.do?carno=<%= vo.getCarno() %>">
		            <img src="<%= contextPath %>/img/<%= vo.getCarimg() %>" width="220" height="180"><br>
		                차량명 : <%= vo.getCarname() %><br>
		                한대당 금액 : <%= vo.getCarprice() %>
		        </a>
    		</td>
    <%
        j++;
    }
%>
</tr>
                
                <tr height="70">
                    <td colspan="4" align="center">
                        차량검색 :
                        <select name="carcategory">
                            <option value="Small">소형</option>
                            <option value="Mid">중형</option>
                            <option value="Big">대형</option>
                        </select>
                        &nbsp;&nbsp;&nbsp;
                        <input type="submit" value="차량검색">
                    </td>
                </tr>      
            </table>
        </form> 
    </center>

</body>
</html>
