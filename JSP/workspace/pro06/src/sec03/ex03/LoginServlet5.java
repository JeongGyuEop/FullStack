package sec03.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login5")
public class LoginServlet5 extends HttpServlet {
	
	protected void doService(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 1. 요청한 데이터 문자셋 처리 방식을 UTF-8로 처리하자
		request.setCharacterEncoding("UTF-8");
		
		// 2. 요청한 데이터들을 HttpServletRequest 객체 메모리에서 꺼내오기
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_address = request.getParameter("user_address");
		
		// 3. 요청한 웹브라우저에 응답할 데이터 종류(MIME-TYPE) 설정
		response.setContentType("text/html;charset=UTF-8");
		
		// 4. 요청한 웹브라우저에 출력스트림 PrintWriter 객체 생성
		PrintWriter printwriter = response.getWriter();
		
		// 5. 웹 브라우저에 응답할 데이터 생성
		/*
		 	요청한 아이디 : admin
		 	요청한 비밀번호 : 1234
		 	요청한 주소 : 서울시 성북구
		 */
		String data = "<body> 요청한 아이디 : " + user_id + "<br>"
					+ "요청한 비밀번호 : " + user_pw + "<br>"
					+ "요청한 주소 : " + user_address + "</body>";
		
		// 6. 요청한 웹브라우저로 출력스트림 PrintWriter를 통해 응답할 데이터 출력
		printwriter.print(data);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doService(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doService(request, response);
		
	}
	
}
