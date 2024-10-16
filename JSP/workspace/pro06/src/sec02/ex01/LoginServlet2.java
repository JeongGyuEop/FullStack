package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 1. 클라이언트가 요청한 데이터들을 HttpServeltRequest객체 메모리 내부에서 
		//	  꺼내와 얻기 전에 한글 데이터 문자 처리 방식 UTF-8 방식으로 설정
		// 요약 : 요청한 데이터 한글처리
		request.setCharacterEncoding("UTF-8");
		
		
		// 2. 요청한 데이터 HttpServletRequest 객체 메모리 내부에서 꺼내서 얻기
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		
		// 3.1 요청한 클라이언트의 웹 브라우저로 응답할 데이터 종류(MIME-TYPE)을
		//		HttpServletResponse 객체에 설정
		response.setContentType("text/html;charset=UTF-8");
		
		// 3.2 요청한 클라이언트의 HttpServletResponse 객체의 getWriter() 메소드를 호출하면
		//     반환값으로 클라이언트의 웹브라우저와 연결되어 데이터를 출력할
		//		출력스트림 통로 역할을 하는 PrintWriter 객체를 반환받을 수 있다.
		// 요약 : 웹 브라우저와 연결된 출력스트림 PrintWriter객체 얻기
		PrintWriter out = response.getWriter();
		
		// 3.3 요청한 데이터를 이용해서 클라이언트의 웹 브라우저로 응답할 데이터를 마련
		String data = "<html>";
			   		data += "<body>";
			   			data += "입력한 아이디 : " + user_id;
			   			data += "<br>";
			   			data += "입력한 패스워드 : " + user_pw;
			   			data += "</body>";
			   data += "</html>";
			   
		// 4. 클라이언트의 웹 브라우저로 응답할 데이터 응답
		//    PrinterWriter 객체의 print 또는 println 메소드를 이용하여
		//    클라이언트의 웹브라우저로 응답(출력)
		out.print(data);
		
		
		System.out.println();
		
	}
	
	
}