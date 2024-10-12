package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
						  throws ServletException, IOException {

		//1 한글처리
		request.setCharacterEncoding("UTF-8");
		
		//2. 요청한 값들을 HttpservletRequest객체 메모리에서 꺼내와 얻자
		String user_id = request.getParameter("user_id"); //입력한 이름 얻기 
		String user_pw = request.getParameter("user_pw"); //입력한 비밀번호 얻기
		//hidden태그의 value속성에 설정된 주소 얻기 
		String user_address = request.getParameter("user_address"); 
		String user_email = request.getParameter("user_email");
		String user_hp = request.getParameter("user_hp");
		
		//3.요청한 값들을 이용하여 요청한 클라이언트의 웹브라우저로 응답(출력)할 값 생성
		String data = "안녕하세요!<br> 로그인하셨습니다.<br><br>";
			   data += "<html><body>";
			   data += "입력한 아이디 : " + user_id + "<br>";
			   data += "입력한 비밀번호 : " + user_pw + "<br>";
			   data += "주소 : " + user_address + "<br>";
			   data += "이메일 : " + user_email + "<br>";
			   data += "휴대전화 : " + user_hp + "<br>";
			   data += "</body></html>";
		
		//4. 응답할 data변수의 값을 클라이언트의 웹브라우저로 응답(출력)
		//설정1. 웹브라우저로 응답할 데이터 종류 설정 및 한글처리
		response.setContentType("text/html;charset=utf-8");
		//설정2. 출력스트림 통로 얻기 
		PrintWriter out = response.getWriter();
		//응답(출력)
		out.print(data);
			   
		
		
	}

	
	
	
	
}








