
<%@page import="java.util.List"%>
<%@page import="sec01.ex01.MemberDAO"%>
<%@page import="sec01.ex01.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
/*
member.jsp설명
- memberForm.html(회원가입을 위해 입력한 정보를 요청하는 화면)에서 
    입력한 가입할 정보들을  request객체 메모리에서 가져온후 ...
  MemberBean클래스의 객체 생성후 각 인스턴스변수에 저장 시킵니다.
  그런 다음 MemberDAO객체를 생성해서 addMember()메소드 호출시~~ 
  이메소드의 매개변수로 MemberBean객체의 주소를 전달합니다
  addMember메소드 내부에서  회원가입정보를 DB의 t_member테이블에 INSERT시킨후~
  INSERT에 성공하면 다시 ~ MemberDAO객체의 listMembers()메소드를 호출해!!
  DB의 t_member테이블에 저장된 가입된 회원정보들을 조회해와서 
    현재 member.jsp에 목록으로 출력(응답)합니다.
*/
//1. 요청한 데이터 request에 UTF-8설정
request.setCharacterEncoding("UTF-8");

//2. 요청한 데이터 얻기(가입을 위해 입력한값들을 request객체 메모리에서 얻기)
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
String name = request.getParameter("name");
String email = request.getParameter("email");
%>

<%--
	<jsp:useBean>액션태그는 자바코드 객체 생성하는 구문을 대체해서 작성할수 있는 태그입니다
	
	id속성에는 생성한 객체의 참조변수명을 지정해서 객체를 식별할 유일한 고유ID값을 지정합니다
	class속성에는 객체를 생성할때 사용하는 설계도인 클래스경로를 패키지명을 포함해서 지정합니다
	scope속성에는 자바빈역할을 하는 VO또는 DTO객체를 생성후 저장될 내장객체 메모리영역 종류명 하나 지정
	
	문법
		<jsp:useBean  id="생성한 객체를 식별할 고유ID값(참조변수명 설정)"    
					  class="객체 생성시 사용될 클래스파일이 저장된 경로" 
					  scope="생성한 객체는 어떤 내장객체 메모리영역에 저장할지 종류작성"
							 page 또는 request 또는 session 또는 application
		/>
 --%>
<%
//3. 요청한 데이터를 통해~ 비즈니스로직처리(insert작업)한 웹브라우저로 응답할 값을 마련

//3.1.MemberBean클래스의 객체 하나를 생성해서 각 변수에 요청한 데이터들을 각각 저장
//MemberBean mb = new MemberBean(id,pwd,name,email); 
%>
<%--기본생성자를 호출해 MemberBean클래스의 객체를 생성합니다. --%>
 <jsp:useBean id="mb" class="sec01.ex01.MemberBean" scope="page"/>	
<%
   //useBean액션태그로 생성한 MemberBean객체 내부에 만들어져 있는 
   //setter인스턴스메소드들을 호출해서 인스턴스변수값들을 각각 초기화 
   mb.setId(id);
   mb.setPwd(pwd);
   mb.setName(name);
   mb.setEmail(email);


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














