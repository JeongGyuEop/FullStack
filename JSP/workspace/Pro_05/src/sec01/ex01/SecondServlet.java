package sec01.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondServlet extends HttpServlet {

	// 호출순서 1위
	// 기능 : SecondServlet 클래스를 톰캣 서버 메모리에 로드하고 객체가 생성되는 시점에
	//		  개발자가 다른 작성을 해야할 때 호출되는 콜백 메소드
	// 		  변수값 초기화 등을 재구현 해 놓는다.
	@Override
	public void init() throws ServletException {

		System.out.println("init 메소드 호출 >>>> ");
	}
	
	// 호출 순서 3위
	// 기능 : 클라이언트의 요청 데이터를 받고 클라이언트의 웹 브라우저로 응답할 데이터를 마련해서 응답
	// 호출되는 시점 : 클라이언트가 웹 브라우저를 이용해서 AET 전송 방식으로 요청이 들어 왔을 때
	//				   호출되는 콜백메소드
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("doGet 메소드 호출 >>>> ");
		
	}
	
	// 호출되는 순위 마지막
	// 기능 : 서블릿의 모든 작업을 마친뒤 톰켓 서버가 중지되는 시점에 
	//		  개발자가 다른 작업을 구현해 놓는 콜백 메소드
	//		  단 한 번만 호출되는 메소드
	@Override
	public void destroy() {

		System.out.println("destroy 메소드 호출");
		
	}

}
