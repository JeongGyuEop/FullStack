package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/second")
public class SecondServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
						  throws ServletException, IOException {

		//1 한글처리
		request.setCharacterEncoding("UTF-8");
		
		//1.1 응답할 데이터 종류 설정
		response.setContentType("text/html;charset=utf-8");
		
		//1.2 출력 스트림 통로 생성 
		PrintWriter out = response.getWriter();
		
				
		//2. 요청한 값들을 HttpservletRequest객체 메모리에서 꺼내와 얻자
		String user_id = request.getParameter("user_id"); //입력한 이름 얻기 
		String user_pw = request.getParameter("user_pw"); //입력한 비밀번호 얻기
		String user_address = request.getParameter("user_address"); 
		
		//3. 첫번쨰 서블릿에서 전달한  입력한 아이디 정보를 이용해 로그인 상태를 유지 시키는 모습을 보여줍니다
		// 입력한 아이디가 존재하면?
		if(user_id != null && user_id.length() != 0) {
			
			out.println("이미 로그인된 상태 입니다. <br><br>");
			out.println("첫 번째 서블릿으로 부터 넘겨받은 아이디 : " +  user_id + "<br>");
			out.println("첫 번쨰 서블릿으로 부터 넘겨받은 비밀번호 : " + user_pw + "<br>");
			out.println("첫 번째 서블릿으로 부터 넘겨받은 주소 : " + user_address + "<br>");
			
		}else {//첫번쨰 서블릿에서 전달한 아이디를 받을수 없으면?
			
			out.println("로그인 하지 않으셨습니다.<br><br>");
			out.println("다시 로그인 하고 오세요.<br>");
			out.println("<a href='/pro09/login.html'>로그인 요청 화면으로 다시 이동</a>");
			
		}

	}
	
	
}










