package com.spring.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	http://localhost:8081/pro23/mem.do 주소를 입력하여  
	DB에 저장된 모든 회원을 조회 하는 요청 주소를 받으면 호출되는 컨트롤러 클래스 
*/

@WebServlet("/mem.do")
public class MemberServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
						throws ServletException, IOException {
		doHandle(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
						 throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) 
			 			 throws ServletException, IOException {
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		//DB에 저장된 모든 회원을 조회 하기 위해 MemberDAO객체 생성후 selectAllmemberList()메소드를 호출하여 
		//조회된 정보가 저장된 List를 리턴받는다.
		List<MemberVO> membersList = new MemberDAO().selectAllMemberList();
		
		//조회한 회원정보들(Model)을  View(test01폴더 내부에 만들어 놓은 listMembers.jsp)에 보여주기 위해 
		//request객체에 바인딩
		request.setAttribute("membersList", membersList);
		
		
		//디스패처 방식으로 View페이지 포워딩(재요청)시~~  request내장객체메모리를 공유 합니다.
		RequestDispatcher dispatcher = request.getRequestDispatcher("test01/listMembers.jsp");
						  dispatcher.forward(request, response);
		
	}	
	
	
	
}




