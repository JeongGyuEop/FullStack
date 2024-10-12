package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.SessionIdGenerator;
import org.apache.catalina.connector.Response;

//두번쨰 서블릿(로그인 한 회원의 회원정보를 보여주면서 로그인 된 화면을 보여주자) 
/*
 먼저 로그인 된 상태를 나타내기 위해  기존 LoginServlet내부에서 만들었었던 HttpSession메모리를 얻은다음
 내부에 바인딩된  isLogOn key에 해당하는 true값을 가져와 로그인 상태의 화면을 보여줍니다.
 그리고 isLogon의 값이 true이면 같이 저장된 회원정보(입력한 아이디, 비번)을 불러와 같이 화면에 출력합니다
 마지막으로 HttpSession객체 메모리가 존재하지 않거나  isLogOn key에 해당하는 값이 false이면 
 다시 로그인 요청할수 있도록 포워딩(재요청)해서 이동한 login4.html화면을 보여 줍니다.
 
 */
@WebServlet("/show")
public class ShowMember extends HttpServlet {

	//이 메소드는 HTTP GET 요청을 처리 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	//이 메소드는 HTTP POST 요청을 처리
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	//GET 방식 또는 POST 요청을 모두 처리하는 일반 메소드 
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");//한글처리
		
		//웹브라우저로 응답할 데이터 유형 설정 및 한글처리
		response.setContentType("text/html;charset=utf-8");
		
		//클라이언트의 웹브라우저와 연결된 출력 스트림 통로 역할 PrintWriter객체 
		PrintWriter out = response.getWriter();
		
		String id = "", pwd = "";
		
		Boolean isLogon = false;
		
		//1.이미 LoginServlet에서 HttpSession을 생성 해 놓고 바인딩 했으므로
		//		기존에 만들어 놓았던 HttpSession객체 메모리의 주소를 얻는다
		HttpSession session = request.getSession(false);
		/*
		session.setAttribute("isLogon", true); //로그인 인증값  저장 
		session.setAttribute("login.id", user_id); //입력했던 아이디 저장
		session.setAttribute("login.pw", user_pw); //입력했던 비밀번호 저장
		*/
		if(session  != null) {// 먼저 기존 HttpSession객체메모리가 유지되고 있고~
			
			   //로그인 되었다라는 판단할값 true를 받아와서 저장 
			   isLogon = (Boolean)session.getAttribute("isLogon");
			   
			   if(isLogon) {//isLogon변수의 값이 true이면 로그인 된 상태이므로 회원정보를 브라우저에 표시
				   
				   id = (String)session.getAttribute("login.id");
				   pwd = (String)session.getAttribute("login.pw");
				   
					out.print("이 ShowMember서버페이지는 서브화면을 웹브라우저로 응답한 내용입니다.<hr>");
					out.print("안녕하세요 " + id + "님!! 로그인되셨어요");
				   
					out.print("<hr><hr>");
				   
				   out.print("아이디 : " + id + "<br>");
				   out.print("비밀번호 : " + pwd + "<br>");
				   
			   }else {//isLogon변수의 값이 false이면 미로그인 이라는 의미 이므로 
				   
				    //포워딩(재요청) - login4.html
					response.sendRedirect("login4.html");	   
			   }		
		}else {
			
			//포워딩(재요청) - login4.html
			response.sendRedirect("login4.html");
		}	
	}//doHandle	
	
}//서블릿 닫는 기호 










