package sec01.ex03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
  첫번째 서블릿(서버페이지)
  
  순서1. 클라이언트가 웹브라우저 주소창에 첫번째 서블릿을 요청할 주소를 입력해서 요청한다
              요청주소 -> http://localhost:8090/pro08/first

  순서2. FirstServlet클래스 내부의 doGet메소드 재구현 코드를 작성하는데
             두번째 서블릿인 SecondServlet서블릿을 포워딩(재요청)할 코드를 작성함
             
         예) location.href = "재요청할 두번째 서블릿 매핑주소 또는 재요청할 JSP 페이지의 주소";

*/

//@WebServlet("/first")
public class FirstServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) 
							 throws ServletException, IOException {
		

		//-> 문법
        //     예) location.href = "재요청할 두번째 서블릿 매핑주소 또는 재요청할 JSP 페이지의 주소";

		
		// 1. 서블릿간의 재요청의 경우! 문자처리방식 UTF-8로 설정해야 
		//   재요청 받는 두번째 서블릿에서 요청해온 데이터를 공유받을때 
		//   한글이깨지지 않고 공유받아 웹브라우저로 출력할수 있음
		request.setCharacterEncoding("UTF-8");
		
		// 2. 요청한 클라이언트의 웹브라우저로 응답할 데이터 종류(MIME-TYPE)설정 및 
		// 문자 처리방식(인코딩방식)을 UTF-8로 설정
		response.setContentType("text/html;charset=UTF-8");
		
		// 3. 출력 스트림 통로 얻기
		PrintWriter out = response.getWriter();
		
		// 4. 재요청
		out.print("<script type='text/javascript'>");
		out.print("location.href = 'second';");
		out.print("</script>");
		
		
		
	
	}
	
	
}













