<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//1. 입력한 가입 정보중 한글이 하나라도 존재하면 한글이 깨져서 받아와 지므로
//   문자처리 방식 UTF-8방식으로 request객체 메모리에 설정
	request.setCharacterEncoding("UTF-8");
%>    

<%-- Address클래스의 객체를 생성해서 인스턴스변수 city와 zipcode에  값을 저장하자 --%>
<jsp:useBean id="addr" class="sec01.ex02.Address" />
<jsp:setProperty  name="addr"  property="city" value="서울"/>
<jsp:setProperty  name="addr"  property="zipcode" value="07654" />


<%-- 2.3. memberForm.jsp에 입력한 요청데이터들을 request에서 꺼내어
          MemberBean각변수에 저장 --%>
<jsp:useBean id="mb" class="sec01.ex02.MemberBean" />
<jsp:setProperty  name="mb" property="*"/>
<jsp:setProperty  name="mb" property="addr" value="<%=addr%>"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align="center" width="100%">
		<tr align="center" bgcolor="green">
			<td width="7%">아이디</td>
			<td width="7%">비밀번호</td>
			<td width="5%">이름</td>
			<td width="11%">이메일</td>
			<td width="11%">도시이름</td>
			<td width="11%">우편번호</td>	
		</tr>
<%--MemberBean객체의 각변수값 얻어 출력 --%>
		<tr align="center">
			<td width="7%">${pageScope.mb.id}</td>
			<td width="7%">${pageScope.mb.pwd}</td>
			<td width="5%">${pageScope.mb.name}</td>
			<td width="11%">${pageScope.mb.email}</td>

<%-- MemberBean객체의 addr인스턴스변수에 저장된 Address객체 주소 얻고, 
     Address객체의 메소드를 호출해 도시이름을 얻어 출력
--%>			
			<td width="11%"><%=mb.getAddr().getCity()%></td>
			<td width="11%"><%=mb.getAddr().getZipcode()%></td>	
		</tr>
		
		
<%--MemberBean객체의 각변수값 얻어 출력 --%>
		<tr align="center">
			<td width="7%">${mb.id}</td>
			<td width="7%">${mb.pwd}</td>
			<td width="5%">${mb.name}</td>
			<td width="11%">${mb.email}</td>

<%-- MemberBean객체의 addr인스턴스변수에 저장된 Address객체 주소 얻고, 
     Address객체의 메소드를 호출해 도시이름을 얻어 출력
--%>			
			<td width="11%">${mb.addr.city}</td>
			<td width="11%">${mb.addr.zipcode}</td>	
		</tr>		


	    <tr height="1" bgcolor="green">
	   		<td colspan="5"></td>
	    </tr>			
	</table>
</body>
</html>












