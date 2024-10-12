package sec03.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login4")
public class LoginServlet4 extends HttpServlet {

	// 클라이언트가 GET 전송방식(웹브라우저 주소창에 주소 입력 요청 
	// 또는 <form> 태그의 method 속성을 get으러 설정해 놓고 요청)
	// 으로 요청하면 호출되는 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		System.out.println("doGET메소드 호출당함.");
		doHandle(request, response); // GET 방식으로 요청 시 다시 doHandle()을 호출한다.
		
	}

	// 클라이언트가 POST 전송방식
	// (<form> 태그의 method 속성을 post로 설정해 놓고(서버단에서) 요청)
	// 으로 요청하면 호출되는 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("doPost 메소드 호출당함.");
		doHandle(request, response); // POST 방식으로 요청 시 다시 doHandle()을 호출한다.
		
	}
	
	// 클라이언트가 GET 이든 POST이든 모든 호출 방식에 대해 응답처리 할 메소드
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		System.out.println("doHandle 메소드 호출당함.");
		
		// 1. 요청한 데이터 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 2. 요청한 데이터 얻기
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		// 3.1 요청한 데이터를 이용해 응답할 값 마련을 위해 응답할 데이터 유형 설정
		//	   설정할 내용 : text 기반의 html 태그 형식으로 설정, 응답할 데이터 한글이
		//   			  	 깨지지 않도록 UTF-8 설정
		response.setContentType("text/html;charset=UTF-8");
		
		// 3.2 요청한 클라이언트의 웹브라우저와 연결된 출력스트림 통로 생성
		PrintWriter printWriter = response.getWriter();
		
		// 3.3 요청한 클라이언트의 웹 브라우저로 응답할 데이터 마련(생성)
		String data = "<body> 아이디 : " + user_id + "<br> 비밀번호 : " + user_pw + "</body>";  
		
		// 4. 요청한 클라이언트의 웹 브라우저로 응답
		printWriter.print(data);
	}

}
