package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 	두 번째 서블릿(서버페이지)
 	
 	-> FirstServlet 첫번째 서블릿으로부터 포워드(재요청)되어 요청받은 
 	   SecondServlet 두번 째 서블릿 클래스.
 	   
 	재요청 받은 주소 : http://localhost:8090/pro08/second
 */

//@WebServlet("/second")
public class SecondServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		// 요청한 클라이언트의 웹브라우저로 응답할 데이터 종류(MIME-TYPE)를
		// HttpServletResponse 객체에 설정하고
		// 응답할 문자가 한글이 있으면 깨지므로 인코딩 방식 설정
		response.setContentType("text/html;charset=UTF-8");
		
		// 요청한 클라이언트의 웹브라우저로 응답할 출력스트림 PrintWriter 객체 생성
		PrintWriter out = response.getWriter();
		
		// 웹 브라우저로 응답
		out.print("<html><body>");
		
		out.print("sendRedirect 메소드 호출 방법(리다이렉트 포워드 방법) 으로"
				+ "재요청 당한 SecondServlet 두번째 서블릿이 응답한 화면");
		
		out.print("</body></html>");
		
	}
	
}
