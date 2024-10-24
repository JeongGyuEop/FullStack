<%@page import="Vo.CarConfirmVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    // 인코딩 설정
    request.setCharacterEncoding("utf-8");

    // contextPath 설정
    String contextPath = request.getContextPath();

    // 차량 예약 목록을 가져옴 (DB에서 가져온 데이터라고 가정)
    List<CarConfirmVo> carList = (List<CarConfirmVo>) request.getAttribute("v");
    String memberPhone = (String) request.getAttribute("memberphone");
    String memberPass = (String) request.getAttribute("memberpass");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <center>
        <img src="<%= contextPath %>/img/naeyeok.jpg">
        <br><br>
        
        <table width="1000" border="1" align="center">
            <tr align="center">
                <td align="center" width="150">차량이미지</td>
                <td align="center" width="100">차량명</td>
                <td align="center" width="100">대여일</td>
                <td align="center" width="50">대여기간</td>
                <td align="center" width="100">한대 가격</td>
                <td align="center" width="70">보험</td>
                <td align="center" width="70">와이파이</td>
                <td align="center" width="70">네비게이션</td>
                <td align="center" width="70">베이비시트</td>
                <td align="center" width="100">예약수정</td>
                <td align="center" width="100">예약취소</td>
            </tr>
            
            <%
                if (carList == null || carList.isEmpty()) {
                    // 예약한 정보가 없을 경우
            %>
                <tr align="center">
                    <td colspan="11">예약한 정보가 없습니다.</td>
                </tr>
            <%
                } else {
                    // 예약 정보가 있을 경우
                    for (CarConfirmVo vo : carList) {
            %>
                <tr align="center">
                    <td align="center" width="150">
                        <img src="<%= contextPath %>/img/<%= vo.getCarimg() %>" width="140" height="90">
                    </td>
                    <td align="center" width="100"><%= vo.getCarname() %></td>
                    <td align="center" width="100"><%= vo.getCarbegindate() %></td>
                    <td align="center" width="50"><%= vo.getCarreserveday() %>일</td>
                    <td align="center" width="100"><%= vo.getCarprice() %>원</td>
                    <td align="center" width="70">
                        <%= (vo.getCarins() == 1) ? "적용" : "미적용" %>
                    </td>
                    <td align="center" width="70">
                        <%= (vo.getCarwifi() == 1) ? "적용" : "미적용" %>
                    </td>
                    <td align="center" width="70">
                        <%= (vo.getCarnave() == 1) ? "적용" : "미적용" %>
                    </td>
                    <td align="center" width="70">
                        <%= (vo.getCarbabyseat() == 1) ? "적용" : "미적용" %>
                    </td>
                    <td align="center" width="100">
                        <a href="<%= contextPath %>/Car/update.do?orderid=<%= vo.getOrderid() %>&carimg=<%= vo.getCarimg() %>&memberpass=<%= memberPass %>&memberphone=<%= memberPhone %>">예약수정</a>
                    </td>
                    <td align="center" width="100">
                        <a href="<%= contextPath %>/Car/delete.do?orderid=<%= vo.getOrderid() %>&center=Delete.jsp&memberphone=<%= memberPhone %>">예약취소</a>
                    </td>
                </tr>
            <%
                    }
                }
            %>
        </table>
    </center>
</body>
</html>
