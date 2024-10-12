package sec01.ex01;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 	어노테이션 기호란?
 		- web.xml 파일에서 서블릿을 요청할 매핑주소를 설정할 경우
 		  요청할 주소들이 복잡해 코드가 길어진다는 단점이 있다.
 		  따라서 각 서블릿 클래명위에 @기호를 붙여서 서블릿을 요청할 매핑주소를 설정하면
 		  코드의 가독성이 좋아진다.
 		  
 		  어노테이션 기호는 종류가 여러 개 만들어져 있어 제공된다.
 		  그 중에서 우리가 사용할 어노테이션 기호는 @WebServlet 어노테이션 기호이다.
 	
 	@WebServlet 어노테이션 기호의 역할
 		- Java Servlet 3.0 버전 이상에서 사용 가능한 어노테이션 기호이다.
 		- 서블릿(서버페이지)를 요청할 매핑 주소를 설정할 용도의 어노테이션 기호이다.
 		예) http://localhost:8090/Pro_05/third 주소를 입력해서 아래의
 		 	ThirdServlet 서블릿 서버페이지를 요청하기 위해 매핑 주소 /third를 설정 
 */

@WebServlet("/third")
public class ThirdServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
	}
	
	// 2
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("클라이언트가 웹 브라우저 주소창에 요청할 주소를 입력하여 서블릿을"
				+ " GET 전송방식으로 요청했을 때 호출되는 콜백 메소드로 요청한 정보에 의한 응답할"
				+ " 데이터를 재구현하는 doGet 메소드 영역이다.");
		
	}
	
	// 2-1
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("클라이언트가 form 태그 내부의 input 태그에 내용을 입력하여"
				+ " 서블릿을 POST 전송방식으로 요청했을 때 호출되는 콜백 메소드로"
				+ " 요청한 정보에 의한 응답할 데이터를 재구현하는 doPost 메소드 영역이다.");
		
	}
	
	// 3
	@Override
	public void destroy() {
		
		System.out.println("톰캣 서버가 중지되는 시점(웹 애플리케이션이 중지)에 "
				+ "호출되는 destroy 콜백 메소드");
		
	}

	// 1
	@Override
	public void init(ServletConfig config) throws ServletException {

		System.out.println("클라이언트가 ThirdServlet을 처음 요청했을 때.."
				+ "ThirdServlet.class가 톰캣 서버메모리에 처음 로드되는 시점에 "
				+ "호출되는 init 메소드");
		
	}
	
	

}
