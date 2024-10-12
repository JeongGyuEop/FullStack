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

import org.apache.catalina.connector.Response;

//서블릿
/*
 	login4.html(로그인 요청하는 화면)에서 전송된 ID와 비밀번호를 가져와
 	MemberVO객체를 생성한 후 변수에 ID와 비밀번호를 저장시킵니다.
 	
 	그런다음 MemberDAO객체의 isExisted()메소드를 호출하면서 
 	MemberVO객체의 주소를~ 매개변수로 전달합니다.
 	
 	입력한 아이디와 비밀번호에 해당되는 회원정보가 조회가 되면 true를 반환받아
 	isLogOn속성을 true로 HttpSession메모리에 저장하고, ID와 비밀번호도 같이 저장합니다.
 
 
 */
@WebServlet("/login4")
public class LoginServlet extends HttpServlet {

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
		
		//요청한 값 얻기(로그인창에서 입력한 아이디 비번 얻기)
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		//MemberVO객체를 생성하고 각변수에 로그인 요청시 입력한 아이디 , 비밀번호 저장 
		MemberVO memberVO = new MemberVO();
		memberVO.setId(user_id);
		memberVO.setPwd(user_pw);
		
		MemberDAO dao = new MemberDAO();
		
//입력한 아이디와 비밀번호에 해당되는 회원정보를 조회 해서 있으면 true반환 없으면 false반환
		boolean result	= dao.isExisted(memberVO);
		
		if(result) {//조회가 되면 입력한 아이디와 비밀번호가 DB의 테이블에 저장되어 있다는 의미 
			
			HttpSession session = request.getSession();
			
			session.setAttribute("isLogon", true); //로그인 인증값  저장 
			session.setAttribute("login.id", user_id); //입력했던 아이디 저장
			session.setAttribute("login.pw", user_pw); //입력했던 비밀번호 저장
			
			out.print("<html><body>");
			
			out.print("이 LoginServlet서버페이지는 메인화면을 웹브라우저로 응답한 내용입니다.<hr>");
			out.print("안녕하세요 " + user_id + "님!! 로그인되셨어요");
			
			out.print("<a href='show'>회원 정보 보기</a>");
			
			out.print("</body></html>");
		}else {
			out.print("<html><body><center>회원 아이디 또는 비밀번호가 틀립니다.</center>");
				
			out.print("<a href='login4.html'>다시 로그인하러가기</a>");
			
			out.print("</body></html>");		
		}
		
	}//doHandle	
	
}//서블릿 닫는 기호 










