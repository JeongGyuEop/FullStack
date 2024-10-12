package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//두번쨰 서블릿(서버페이지)
//-> FirstServlet 첫번째 서블릿으로부터 포워드(재요청)되어 요청 받은
//   SecondServlet 두번째 서블랫 클래스.
//재요청 받는 주소 :  http://localhost:8090/pro08/second

//@WebServlet("/second")
public class SecondServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) 
						 throws ServletException, IOException {
		
		// 1. 한글처리 UTF-8 설정
		request.setCharacterEncoding("UTF-8");
		
		// 2. 첫번째 서블릿 FirstServlet 클래스 내부에서
		//     작성한  request.setAttribute("address", "서울시 성북구");
		//    에 의해 바인딩된 정보를 한번 꺼내와서 사용해보자
		String address = (String)request.getAttribute("address");
		
		// 3. 
		
	}
}


















