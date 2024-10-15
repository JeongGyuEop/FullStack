<%@page import="java.util.List"%>
<%@page import="sec01.ex01.MemberDAO"%>
<%@page import="sec01.ex01.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	//1. 요청한 데이터 request 한글설정
    	request.setCharacterEncoding("utf-8");
    
    	// 2. 요청한 데이터 얻기(가입을 위해 입력한 값들을 request 객체 메모리에서 얻기)
    	String id = request.getParameter("id");
    	String pwd = request.getParameter("pwd");
    	String name = request.getParameter("name");
    	String email = request.getParameter("email");

    	// 3. 요청한 데이터를 통해~ 비즈니스로직처리(insert 작업)한 웹 브라우저로 응답할 값을 마련
    	//    MemberBean 클래스의 객체 하나를 생성해서 각 변수에 요청한 데이터들을 각각 저장
    	MemberBean mb = new MemberBean(id, pwd, name, email);
    	
    	// 3.2 MemberDAO 객체를 생성해서 addMember 메소드 호출시
    	//     매개변수로 DB에 insert할 정보가 저장된 MemberBean 객체의 주소를 전달
    	MemberDAO dao = new MemberDAO(); 
    	
    	dao.addMember(mb); // t_member테이블에 회원가입시 입력한 레코드 하나 insert진행
    	
    	// 3.3 회원가입에 성공한 회원 레코드를 포함해서 t_member 테이블에 저장된 모든 회원조회
    	//     방법 : MemberDAO 객체의 listMembers 메소드 호출하여 ArrayList 배열 반환받자
    	List membersList = dao.listMembers();
    	
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<table align="center" width="100%">
		<tr align="center" bgcolor="#99ccff">
			<td width="7%">아이디</td>
			<td width="7%">비밀번호</td>
			<td width="5%">이름</td>
			<td width="11%">이메일</td>
			<td width="5%">가입일</td>
		</tr>
		<%
		if(membersList.size()==0) { // DB의 테이블에 조회한 회원이 없으면
		%>
			<tr>
				<td colspan="5">
					<p align="center"><b><span style="font-size:9pt;">등록된 회원이 없습니다.</span></b></p>
				</td>
			</tr>
		<%
		} else { // DB 테이블에 조회한 회원이 있으면?
			for(int i=0; i<membersList.size(); i++) {
				MemberBean bean = (MemberBean) membersList.get(i);	
		%>
				<tr align="center" >
					<td width="7%"><%=bean.getId() %></td>
					<td width="7%"><%=bean.getPwd() %></td>
					<td width="5%"><%=bean.getName() %></td>
					<td width="11%"><%=bean.getEmail() %></td>
					<td width="5%"><%=bean.getJoinDate() %></td>
				</tr>
		
		<%
			}
	
		}
		%>
		<tr height="1" bgcolor="#99ccff">
			<td colspan="5"></td>
		</tr>
	</table>

</body>
</html>