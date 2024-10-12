package sec01.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// input.html 화면에서 이름, 비밀번호, 과목명들을 체크하여 전송 버튼을 눌렸을 때
// 요청한 값들을 얻어 처리하는 InputServlet 서버 페이지

// http://localhost:8090/pro06/input?user_id=admin&user_pw=1234&subject=Java&subject=JSP


@WebServlet("/input")
public class InputServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 클라이언트가 요청한 데이터들은 모두 매개변수 request로 전달받는
		//    HttpServletRequest 객체 메모리에 저장되어 있으느로
		//    요청한 데이터를 꺼내오기 전에 요청한 한글들에 문자처리방식을 UTF-8로 설정
		// 요약 : 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 2. 서블릿으로 요청한 데이터들을 HttpServletRequest 객체 메모리에서 얻기
		//    요약 : 요청한 데이터 얻기
		
		// 2-1. 입력한 아이디, 비밀번호 얻기
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		// 2-2. <input type="checkbox" value="값">들에 체크된 값들 얻기
		// 참고. <input> 태그들은 하나의 공통된 name="subject"로 여러 값을 한꺼번에 전송하여 받을 경우
		// HttpServletRequest 객체 메모리의 getParameterValues("<input>의 name 속성값 subject"); 메소드 호출!
		// 여러 개의 요청한 값들을 배열에 담아 얻어와야할 경우 사용!
		String[] subject = request.getParameterValues("subject");
		
		// 3. InputServlet (서버페이지)로 요청한 값을 확인하기 위해 출력
		System.out.println("요청한 아이디:" + user_id);
		System.out.println("요청한 비밀번호:" + user_pw);
		
		// 향상된 for 반복문을 이용하여 subject 배열에 저장된 체크된 모든 value 속성값을 얻어 출력
		for(String str : subject) {
			System.out.println("체크된 과목명 : " + str);
		}
	}
	
}
