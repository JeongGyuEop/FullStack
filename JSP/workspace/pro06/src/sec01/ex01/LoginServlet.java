package sec01.ex01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
login.html(아이디와 비밀번호를 입력해서 로그인버튼을 눌러 서블릿을 요청하는 화면)에서 
로그인 버튼을 눌러  http://localhost:8090/pro06/login 
요청주소를 톰캣 서버에 전달하여 요청하면
요청을 받는 LoginServlet클래스 입니다.

순서1. 클라이언트가 웹브라우저 주소창에 
      http://localhost:8090/pro06/login.html을 입력하여 
           정적인 페이지인 login.html 디자인 화면을 요청합니다.

순서2. 웹서버 (아파치)는  login.html파일을 찾아 실행한 HTML코드 디자인을 
           클라이언트의 웹브라우저 화면에 표시 해 줍니다.

순서3. login.html화면을 본~ 클라이언트는 아이디와 비밀번호를 입력하고  
          로그인(submit)버튼을 눌러
      <form>태그의 action속성에 설정된 login 요청주소로 
      LoginServlet서블릿(서버페이지)를 요청하게 됩니다.

순서4. LoginServlet서블릿은 웹브라우저를 통해 전송한 정보(입력한 아이디,비밀번호)를  
  	   톰캣 서버가  HttpServletRequest객체메모리를 생성한 후 담아서    
  	  doGet메소드의 매개변수로 전달합니다.
  	  
순서5. 우리 백엔드 개발자가 doGet메소드에서 요청한 데이터를 받아서 
           이클립스의 console탭에 요청한 값들을 얻어서 확인차 출력시킵니다.      

*/

//http://localhost:8090/pro06/login
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, 
			             HttpServletResponse response) 
			            		 throws ServletException, IOException {
		//1.클라이언트가 요청한 데이터들은 모두 매개변수로 전달받는 HttpServletRequest객체
		//메모리에 저장되어 있으므로 데이터들 중에 한글이 하나라도 존재할 경우
		//문자를 처리할수 있는 방식을 UTF-8방식으로 인코딩해서 한글이 깨지는 것을 방지 하자
		//요약 : 요청한 값들을 HttpServletRequest객체 메모리에서 꺼내오기 전에 한글처리
		request.setCharacterEncoding("UTF-8");
		
		//2. login.html화면에서 입력한 요청한 값을
		//   HttpServletRequest객체 메모리에서 얻기
		//요약 :  클라이언트가 요청한 데이터 얻기 
		
		//입력한 아이디 얻기
										//입력한 <input>의 name속성값 넣기 
		String user_id = request.getParameter("user_id"); 
		
		//입력한 비밀번호 얻기                            //입력한 <input>의 name속성값 넣기 
		String user_pw = request.getParameter("user_pw");
		
		
		//3. 요청한 값을 서블릿(서버페이지)에서 얻는지 확인 하기 위해 이클립스의 console창에 출력
		System.out.println("입력하여 요청한 아이디:" + user_id);
		System.out.println("입력하여 요청한 비밀번호:" + user_pw);
		
		
		
	}
	


}









