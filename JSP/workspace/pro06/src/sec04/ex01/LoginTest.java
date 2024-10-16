package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginTest")
public class LoginTest extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		
		System.out.println("init 메소드 호출");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		response.setContentType("text/html;charset=UTF-8");
		
		System.out.println("아이디 : " + user_id);
		System.out.println("패스워드 : " + user_pw);
		
		PrintWriter printwrite = response.getWriter();
		
		String loginSucess = "<html><body>"
				+ user_id + "님이 로그인하셨습니다."
				+ "</body></html>";
		
		String loginFaild = "<html><body>"
				+ "아이디를 입력하세요!!! <br>" 
				+ "<a href='http://localhost:8090/pro06/test01/login.html'>로그인창으로 이동</a>"
				+ "</body></html>";
		
		if(user_id != null && (user_id.length() != 0)) {
			printwrite.print(loginSucess);
		} else {
			printwrite.print(loginFaild);
		}
		
	}

	public void destroy() {
		
		System.out.println("destroy 메소드 호출");
		
	}

}