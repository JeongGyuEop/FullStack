package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
  클라이언트가 웹브라우저 주소창에 http://localhost:8081/pro09/set 주소를 입력하여
  SetCookieValue서블릿을 요청합니다.
  SetCookieValue서블릿(톰캣 서버가 실행하는 서버페이지)내부에서 
  Cookie클래스의 객체를 생성한후 쿠키이름을 cookieTest로 쿠키값을 저장합니다.
  그리고 setMaxAge()메소드를 사용하여 쿠키 유효 시간을 24시간으로 설정합니다.
  그런 다음 response객체의 addCookie()메소드를 이용해 생성된 쿠키를 
  웹브라우저로 전송합니다. 
*/

@WebServlet("/set")
public class SetCookieValue extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) 
								 throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		//현재 날짜 정보를 제공하는 java.util패키지의 Date클래스 객체 생성
		Date date = new Date();
		
		//1.Cookie클래스로 쿠키 객체 생성한후 
		//  cookieTest라는 이름으로 한글정보를 인코딩해서 생성한 쿠키객체 메모리에 저장
		Cookie cookie = 
		new Cookie("cookieTest" , URLEncoder.encode("JSP프로그래밍입니다.","UTF-8") );
		//2. Cookie객체 메모리 유효기간 설정
		cookie.setMaxAge(60*60*24); //하루 24시간 
		
		//유효시간을 음수로 지정하면 쿠키파일에 저장하는 것이 아니라 
		//클라이언트의 웹브라우저가 사용하는 Cookie저장소에 session쿠키를 생성할수 있다.
		//cookie.setMaxAge(-1);
		
		//3. 생성된 Cookie객체를 ~~~  요청시 사용된 클라이언트의 웹브라우저로 전달(응답)
		response.addCookie(cookie); //HttpServletResponse객체에 Cookie객체 저장 후 내보내자 
		
		//4. 클라이언트의 웹브라우저로 응답할 데이터 + Cookie객체 모두 전달
		out.println("현재 시간 : " + date);
		out.println("문자열을 Cookie객체에 저장 후  웹브라우저로 내보냈습니다.");
		
		
		
	}
	
	
	
}















