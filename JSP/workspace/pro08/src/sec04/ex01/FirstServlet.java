package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 첫번째 서블릿(서버페이지)  */
// http://localhost:8090/pro08/first 요청 -> 톰캣서버
//@WebServlet("/first")
public class FirstServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) 
							 throws ServletException, IOException {
		//1. 서블릿간의 재요청의 경우! 문자처리방식 UTF-8로 설정해야 
		//   재요청 받는 두번째 서블릿에서 요청해온 데이터를 공유받을때 
		//   한글이깨지지 않고 공유받아 웹브라우저로 출력할수 있음
		request.setCharacterEncoding("UTF-8");

		// 2. 웹브라우저 주소창으로 서블릿 요청하면 
		//	  톰캣서버는 요청하나당 HttpServletRequest 객체 메모리 생성후
		//	  doGet 메소드의 매개변수 request 로 전달하므로
		//    HttpServletRequest 객체 메모리에 address 이름과 함께
		//	  "서울시 성북구" 값을 함께 저장(바인딩)할수 있다.
		request.setAttribute("address", "서울시 성북구");
		
		// 3. 두번째 서블릿으로 리다이렉트 방법으로 포워딩
		response.sendRedirect("second");
	}
	
	
}













