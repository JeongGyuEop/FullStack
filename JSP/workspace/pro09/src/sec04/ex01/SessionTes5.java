package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;

//서블릿

/*
 순서1. login2.html에서 아이디, 비밀번호 입력후 로그인 버튼을 클릭해 아래의 서블릿으로 로그인 요청합니다.
 
 순서2. doPost메소드가 호출되면서 매개변수로 request를 받습니다. 
       request에는 입력한 아이디, 비번이 저장되어 있음
       doPost메소드에서 doHandle메소드를 호출시 request를 매개변수로 전달해서 사용합니다.
       
 순서3. doHandle메소드 내부에서 로직을 작성 합니다.
 
*/
@WebServlet("/login3")
public class SessionTes5 extends HttpServlet {

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
		
		//요청한 클라이언트의 웹브라우저와 연결되는 HttpSession객체 메모리를 새로 생성합니다.
		HttpSession session = request.getSession(); //getSession(true);
		
		//로그인 요청시 입력한 아이디 , 비밀번호를 HttpServletRequest객체 메모리에서 꺼내오기
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		//HttpSession객체 메모리가 새로 생성되었을 경우(최초로 로그인 요청 했을 경우)
		if(session.isNew()) {
			
			//만약 로그인 시도시 아이디를 입력 했다면?
			//HttpSession객체 메모리에 user_id변수의 값을 저장하고 로그인 상태를 확인하는 <a>링크를 표시
			if (user_id != null && !user_id.trim().isEmpty()) { // 아이디가 null이 아니고 빈 문자열이 아닌 경우			
				
				session.setAttribute("user_id", user_id);	
				
				//변수 url에 encodeURL메소드를 이용해 웹브라우저로 응답시 미리 jsessionId를 저장 합니다.
				String url = response.encodeURL("login3");
				
				System.out.println(url);
				
				//로그인 상태 확인 클릭 시 jsessionId를 서블릿으로 다시 전송합니다.
				out.println("<a href=" +  url  + ">로그인 상태 확인 요청</a>");	
				
			}else {//서블릿으로 로그인 요청시 아이디 입력 하지 않고 요청했을때 			
				out.print("<a href='login2.html'>다시 로그인 하세요!!</a>");			
				session.invalidate();
			}
						
		}else {
			
			//HttpSession이 이미 존재 하는 경우 (사용자가 이미 로그을 했다거나 "login2" 페이지를 방문한 경우)
			//HttpSession에 저장된 user_id 속성을 가져와 사용자가 로그인 되었는지 확인합니다.
			user_id = (String)session.getAttribute("user_id");
			
			if(user_id != null && user_id.length() != 0) {
				out.print("안녕하세요 " + user_id + "님!! 로그인중...");
			}else {
				out.print("<a href='login2.html'>다시 로그인 하세요!!</a>");
				session.invalidate();
			}	
		}		
	}	
	
	
	
}










