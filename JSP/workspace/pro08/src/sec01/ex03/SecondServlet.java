package sec01.ex03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//두번쨰 서블릿(서버페이지)
//-> FirstServlet 첫번째 서블릿으로부터 포워드(재요청)되어 요청 받은
//   SecondServlet 두번쨰 서블랫 클래스.
//재요청 받는 주소 :  http://localhost:8090/pro08/second

//@WebServlet("/second")
public class SecondServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) 
						 throws ServletException, IOException {
		
	     //요청 한 클라이언트의 웹브라우저로 응답할 데이터종류(MIME-TYPE)를
		// HttpServletResponse객체에 설정 하고
		// 응답할 문자가 한글이 있으면 꺠지므로 인코딩 방식 UTF-8로 설정
		response.setContentType("text/html;charset=utf-8");
		
		//요청한 클라이언트의 웹브라우저로 응답할 출력스트림 PrintWriter객체 생성
		PrintWriter out = response.getWriter();
		
		//웹브라우저로 응답
		out.print("<html><body>");
		
		out.print("자바스크립트의 location 객체의 href속성의 값을 변경하는 방법으로 "
				+ "재요청 당한 SecondServlet두번쨰 서블릿이 응답한 화면");
		
		out.print("</body></html>");
		
		
		
		
	}
}


















