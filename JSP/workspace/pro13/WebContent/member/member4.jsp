
<%@page import="java.util.List"%>
<%@page import="sec01.ex01.MemberDAO"%>
<%@page import="sec01.ex01.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//1. 요청한 데이터 request에 UTF-8설정
request.setCharacterEncoding("UTF-8");
%>
<%--
  2. 요청한 값들을 얻기 (회원가입을 위해 입력한 값들을 request객체 얻기)
  3. MemberBean객체를 생성해서 각 인스턴스변수에 요청한 값들을 저장
  
  회원가입 창(memberForm.html)에서 전달된 값들을 rquest객체 에서 자동으로 얻어
 setProperty액션태그에 의해 MemberBean의 각변수에 저장하기 위해서
 setProperty액션태그에 value속성 대신 param속성을 사용 합니다.
 param속성에는 입력했던 input태그의 name속성값을 넣습니다.
 
 param속성을 작성하는 조건-> 
 MemberBean클래스의 인스턴스변수명과 input태그의 name속성값이 일치해야 함
 --%>
 <jsp:useBean id="mb" class="sec01.ex01.MemberBean" scope="page"/>	
 
 <jsp:setProperty name="mb" property="id" param="id" />
 <jsp:setProperty name="mb" property="pwd" param="pwd" />
 <jsp:setProperty name="mb" property="name" param="name" />
 <jsp:setProperty name="mb" property="email" param="email"/>
<%
//3.2.MemberDAO객체를 생성해서 addMember메소드 호출시
//    매개변수로 DB에 insert할 정보가 저장된 MemberBean객체의 주소 전달
MemberDAO dao = new MemberDAO();

		  dao.addMember(mb);//t_member테이블에 회원가입시 입력한 레코드 하나 insert진행

//3.3 회원가입에 성공한 회원레코드를 포함해서 t_member테이블에 저장된 모든 회원조회
//방법 : MemberDAO객체의 listMembers메소드 호출하여 ArrayList배열 반환받자
List membersList =  dao.listMembers();		  
%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- t_member테이블에서 조회된 모든 회원정보를 표의 목록형태로 출력 --%>
	<table align="center" width="100%">
		<tr align="center" bgcolor="#99ccff">
			<td width="7%">아이디</td>
			<td width="7%">비밀번호</td>
			<td width="5%">이   름</td>
			<td width="11%">이메일</td>
			<td width="5%">가입일</td>
		</tr>
<%
   //t_member테이블에 조회한 회원정보가 없으면?(t_member테이블에 저장된 회원이 없으면?)
   if(membersList.size() == 0){
%>	   
	   <tr>
	   	   <td colspan="5">등록된 회원이 없습니다.</td>
	   </tr>
<% 
   }else{//t_member테이블에 조회한 회원정보가 있으면?
	     //(ArrayList배열에 조회된 MemberBean객체들이 저장되어 있다면?)
	   
	     //for반복문을 이용해 ArrayList배열에 저장된 MemberBean객체의 갯수만큼반복해서
	     //MemberBean객체를 ArrayList배열에서 각각 얻은후 
	     //얻은 MemberBean객체의 각 변수의 회원데이터들을 getter메소드들을 호출해 얻어
	     //한사람의 정보씩 한행에 출력합니다.
	     for(int i=0; i<membersList.size(); i++){
	    	 
	    	 MemberBean memberbean = (MemberBean)membersList.get(i);
%>	    	 
	    	 <tr align="center">
				<td width="7%"><%=memberbean.getId()%></td>
				<td width="7%"><%=memberbean.getPwd()%></td>
				<td width="5%"><%=memberbean.getName()%></td>
				<td width="11%"><%=memberbean.getEmail()%></td>
				<td width="5%"><%=memberbean.getJoinDate()%></td>
			</tr>
<%	    	 
	     }//for닫기
   }//else닫기
%>	
		<tr height="1" bgcolor="#99ccff">
			<td colspan="5"></td>
		</tr>
	</table>


</body>
</html>














