package sec01.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 	첫번째 서블릿 (서버페이지)
 	
 	순서 1. 클라이언트가 웹 브라우저 주소창에 첫번째 서블릿을 요청할 주소를 입력해서 요청한다.
 			요청 주소 -> http://localhost:8090/pro08/first
 			
 	순서 2. FirstServlet 클래스 내부의 doGet 메소드 재구현 코드를 작성하는데
 			두번째 서블릿인 SecondServlet 서블릿을 포워딩(재요청) 코드를 작성한다.
 			예) response.sendRedirect("두번째 서블릿을 재요청할 매핑 주소");
 	
 */


//@WebServlet("/first")
public class FirstServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		// 포워드 : 다른 서버 페이지를 재요청하는 기술
		// 포워드 방법 중에 리다이렉트 재요청방법
		// (HttpServeltResponse 객체의 sendRedirect() 메소드를 호출해서 사용하는 방법)
		// -> 첫번째 서블릿을 처음 요청했지만
		//    첫번째 서블릿 내부 코드에서 두번째 서블릿을
		//    웹 브라우저 주소창을 거쳐서 재요청(리다이렉트 포워드) 한다는 의미이다.
		// -> 문법
		//		response.sendRedirect("재요청할 두번째 서브릿 매핑 주소");
		
		// 1. 서블릿 간의 재요청의 경우! 문자처리방식 UTF-8로 설정해야
		//	  재요청을 받는 두번째 서블릿에서 첫번째 서블릿에서 요청받은 데이터를 공유받을 때 
		//    한글이 깨지지 않고 공유받아서 사용할 수 있다.
		request.setCharacterEncoding("UTF-8");
		
		// 2. 리다이렉트 포워드 기술을 이용해 두번째 서블릿을 재요청!
		response.sendRedirect("second");
		
	}
	
}
