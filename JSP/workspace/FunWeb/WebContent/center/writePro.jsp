<%@page import="board.BoardDAO"%>
<%@page import="board.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	// 1. 한글처리
	request.setCharacterEncoding("utf-8");

	// 2. write.jsp에서 입력한 글정보를 request 에서 꺼내와
	//    BoardBean 객체 생성 후 인스턴스 변수에 각각 저장
%>
	<jsp:useBean id="bBean" class="board.BoardBean"  />
	<jsp:setProperty property="*" name="bBean"/>
<%	
	// 2-1. 추가로 BoardBean 객체에 ip 인스턴스 변수에 
	//      글을 현재 작성하고 있는 사람의 PC의 IP 주소를 만들어 저장
	bBean.setIp(request.getRemoteAddr());
	
	// 3. BoardDAO 객체를 생성하여 insertBoard 메소드 호출 시 매개변수로
	//    BoardBean 객체의 주소를 넘겨서 INSERT 문장을 만들어 board 테이블에 추가시킨다.
	new BoardDAO().insertBoard(bBean);
	
	// 4. 새로 글 정보가 성공적으로 board 테이블에 insert되면
	//    notice.jsp 포워딩해서 보여주자.
	response.sendRedirect("notice.jsp");
%>