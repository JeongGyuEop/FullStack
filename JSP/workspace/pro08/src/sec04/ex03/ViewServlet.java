package sec04.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/viewMembers")
public class ViewServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//첫번쨰 서블릿 MemberServlet에서 HttpServletRequest객체 메모리에 바인딩한
		//조회된 MemberVO객체들이 저장된 ArrayList배열 꺼내오기 
		List list = (List)request.getAttribute("membersList");
		
		
		//ArrayList가변길이 배열 모습
		//[ MemberVO,  MemberVO,  MemberVO ]
		//    0          1            2       index
		
		//3.2 클라이언트의 웹브라우저로 응답할 데이터 종류(MIME-TYPE) 설정 및
		//   응답할 데이터 인코딩(문자를 처리할수 있는 방식)을 UTF-8로 설정
		response.setContentType("text/html;charset=utf-8");
		
		//3.3 클라이언트의 웹브라우저로 응답(출력)할 출력스트림 통로 (PrintWriter)얻기
		PrintWriter out = response.getWriter();
		
		//3.4 (순서 8) 웹브라우저로 출력(결괏값 출력)
		//t_member테이블에 저장된 조회된 정보를 얻어 출력
		out.print("<html>");
			out.print("<body>");
				out.print("<table border='1'>");
					out.print("<tr align='center' bgcolor='lightgreen'>");
						out.print("<th>아이디</th>");
						out.print("<th>비밀번호</th>");
						out.print("<th>이름</th>");
						out.print("<th>이메일</th>");
						out.print("<th>가입일</th>");
						out.print("<th>삭제</th>"); //<-----추가 
					out.print("</tr>");
					
					//ArrayList가변길이 배열 모습
					//[ MemberVO,  MemberVO,  MemberVO ]
					//    0          1            2       index
					
				//t_member테이서 조회된 MemberVO객체들이 저장된
				//ArrayList배열의 MemberVO객체의 갯수만큼 반복해서 얻어 출력
				for(int i=0;  i<list.size();  i++) {
					
					//ArrayList배열에 저장되어 있는 MemberVO객체를 하나씩 반복해서 얻기
					Object obj	= list.get(i);
					MemberVO memberVO = (MemberVO)obj;//다운캐스팅
					
				out.print("<tr align='center'>");
					out.print("<td>"+ memberVO.getId() +"</td>");
					out.print("<td>"+ memberVO.getPwd() +"</td>");
					out.print("<td>"+ memberVO.getName() +"</td>");
					out.print("<td>"+ memberVO.getEmail() + "</td>");
					out.print("<td>"+ memberVO.getJoinDate() +"</td>");
					out.print("<td><a href='/pro08/member4?command=delMember&id="+memberVO.getId() +"'>삭제</a></td>");
				out.print("</tr>");
								
				}//for
					
				out.print("</table>");
				
				out.print("<a href='/pro08/memberForm.html'>회원가입</a>");
				
				
			out.print("</body>");
		out.print("</html>");
	}

	
	
}





