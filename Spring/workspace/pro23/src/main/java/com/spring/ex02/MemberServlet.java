package com.spring.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	http://localhost:8081/pro23/mem2.do 주소를 입력하여  
	DB에 저장된 회원 id가 hong인 사람의 이름 조회 요청!
	그리고~
	DB에 저장된 회원 id가 hong인 사람의 비밀번호 조회 요청!
*/

@WebServlet("/mem2.do")      
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
	//요청한 데이터 한글처리
		request.setCharacterEncoding("UTF-8");
		
		MemberDAO dao = new MemberDAO();
		
	//DB에 저장된 id열 값이 hong인 회원의 이름을 조회하기 위해 DAO의 메소드호출!	
		//String name = dao.selectName();
		
	//DB에 저장된 id열 값이 hong인 회원의 비밀번호를 조회하기 위해 DAO의 메소드호출!	
		int pwd = dao.selectPwd();	
		
		
	//웹브라우저로 응답할 MIME-TYPE설정 및 응답할 이름 한글처리
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();// 출력 스트림 통로 
		out.write("<script>");
//			out.write(" alert('이름:"+  name   + "')");
			out.write(" alert('비밀번호:"+  pwd   + "')");
		out.write("</script>");
			
	}	
		
}







