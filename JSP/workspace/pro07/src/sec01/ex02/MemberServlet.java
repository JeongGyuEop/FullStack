package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.net.aso.e;

// 웹 브라우저(클라이언트)로 부터
// t_member 테이블에 저장된 모든 회원 정보르 조회하여 보여줘~ 라고
// http://localhost:8090/pro07/member 조회 주소요청을 받아 
// 응답하는 서블릿

//@WebServlet("/member2")
public class MemberServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 1. 요청한 데이터 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 2. 요청한 값 얻기
		//    (요청한 주소 http://localhost:8090/pro07/member를 얻었기 때문에 따로 작성 X)
		
		// 3. 요청한 주소에 관한 DB의 t_member 테이블의 회원 정보 조회를 위해
		//    DB 작업을 하는 MemberDAO 클래스의 객체 생성
		MemberDAO dao = new MemberDAO();
		
		// 3.1 t_member 테이블의 모든 회원을 조회하기 위해서
		//	   MemberDAO 객체의 listMembers 메소드 호출!
		// 	   listMembers 메소드는 DB의 t_member 테이블의 모든 회원 정보를 행단위로 조회 후
		//     ArrayLsit 배열에 담아 반환해 주는 역할을 한다.
		List<e> list = dao.listMembers(); // 클라이언트의 웹브라우저로 응답할
									   // 조회된 회원 데이터 전체(ArrayList 배열)
		// ArrayLsit 가변길이 배열 모습
		// [ MemberVO, MemverVo, MemberVO ]
		//      0          1         2         index
		
		// 3.2 클라이언트의 웹브라우저로 응답할 데이터 종류 (MIME-TYPE) 설정 및
		//     응답할 데이터 인코딩 (문자를 처리할 수 있는 방식)을 UTF-8로 설정
		response.setContentType("text/html;charset=UTF-8");
		
		// 3.3 클라이언트의 웹브라우저로 응답(출력)할 출력스트림 통로(PrintWriter) 얻기
		PrintWriter out = response.getWriter();
		
		// 3.4 (순서 8) 웹 브라우저로 출력(결과값 출력)
		//     t_member 테이블에 저장된 조회된 정보르 얻어 출력
		out.print("<html>");
			out.print("<body>");
				out.print("<table border='1'>");
					out.print("<tr align='center' bgcolor='lightgreen'>");
					out.print("<th>아이디</th>");
					out.print("<th>비밀번호</th>");
					out.print("<th>이름</th>");
					out.print("<th>이메일</th>");
					out.print("<th>가입일</th>");
					out.print("</tr>");					
					
					// t_member 테이블에서 조회된 MemberVO 객체들이 저장된
					// ArrayLsit 배열으 MemberVO 객체의 갯수만큼 반복해서 얻어 출력
					for(int i=0; i<list.size(); i++) {
						Object obj = list.get(i);
						MemberVO memberVO = (MemberVO)obj; // 다운캐스팅
						
						out.print("<tr>");
						out.print("<td>" + memberVO.getId() + "</td>");
						out.print("<td>" + memberVO.getPwd()+ "</td>");
						out.print("<td>" + memberVO.getName() + "</td>");
						out.print("<td>" + memberVO.getEmail() + "</td>");
						out.print("<td>" + memberVO.getJoinDate() + "</td>");
						out.print("</tr>");	
					}
				out.print("</table>");
			out.print("</body>");
		out.print("</html>");
		
		
		
	}
	
}
