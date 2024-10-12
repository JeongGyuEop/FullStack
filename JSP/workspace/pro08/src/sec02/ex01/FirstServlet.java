package sec02.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//첫번째 서블릿 (서버페이지)

//순서1.  클라이언트의 웹브라우저 주소창에 첫번째 서블릿을 요청할 주소를 입력해서 요청한다
//       요청주소 -> http://localhost:8081/pro08/first

//순서2.  FirstServlet 클래스 내부에 두번쨰 서블릿인 SecondServet서블릿을 재요청할 코드 작성 
//		 response.sendRedriect("second");  <- 코드 

//주제 : response객체의 sendRedirect()메소드를 호출하는 재요청방법은 
//      첫번쨰 서블릿에서 두번쨰 서블릿 재요청시 첫번째 서블릿이 지니고 있는 데이터를 공유할수 있다.

//예제 : 첫번쨰 서블릿에서 만든 데이터를 두번쨰 서블릿으로 재요청시(GET전송방식으로) 데이터를 전달해서 사용하는 예제

//@WebServlet("/first")
public class FirstServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
						throws ServletException, IOException {
		
		//1. 서블릿간의 재요청의 경우! 문자처리방식 UTF-8로 설정해야 
		//   재요청 받는 두번째 서블릿에서 요청해온 데이터를 공유받을때 
		//   한글이깨지지 않고 공유받아 웹브라우저로 출력할수 있음
		request.setCharacterEncoding("UTF-8");
		
		//2. 두번째 SecondServlet클래스로 재요청(포워딩)
		//  GET전송방식을 이용해 재요청하는 주소뒤에 ? 를 작성하고 그뒤에 이름=값 을 한쌍에 형태로
		//	request에 담아서 두번쨰 서블릿으로 재요청시 전달 합니다. 
		response.sendRedirect("second?name=lee");
		
		
	}
	
	
}










