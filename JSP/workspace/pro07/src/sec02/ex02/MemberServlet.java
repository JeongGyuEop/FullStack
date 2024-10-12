package sec02.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sec02.ex02.MemberVO;

/*
memberForm.html(회원가입을 위해 가입할 정보를 입력후 DB의 테이블에 INSERT 요청 하는 화면)
에서 가입할 정보를 입력하고 가입요청버튼을 클릭하면 입력된 정보들을 
모두 HttpServletRequest객체 메모리에  저장된후 공유받아 사용하는 서블릿으로 
회원 추가후 추가된 정보를 확인 하기 위해
다시~~~ 모든 회원정보들을 조회해서 클라이언트의 웹브라우저로 출력(응답) 해서 보여줍니다.

순서1. input type="hidden" name="command" value="addMember" 의 
      addMember값을  HttpServletRequest객체 메모리에서 얻습니다.
	  
	  얻는 코드 :   request.getParameter("command"); -> "addMember"
	  
	  얻는 이유 : 어떤 요청을 했는지 서블릿은 판단하기 위해서 입니다. 
	  			여기서는 addMember 값 자체가 회원가입 요청임을 판단하는 값이 됩니다. 
	  			
순서2. addMember <- 요청한 값은 t_member테이블에 회원추가 요청임을 판단해
	  가입시 입력한 회원정보들을 HttpServletRquest객체 메모리로부터 얻어서
	  MemeberVO객체 생성후 각인스턴스변수에 저장 

순서3. 실제 t_member테이블에 insert하기 위해 MemberDAO의 addMember메소드 호출시~
  	  매개변수로 MemberVO객체의 주소를 전달하여 MemberDAO의 addMember메소드 내부에서
  	  insert문장을 만들고 insert문을 실행할수 있도록 합니다.
  	  
순서4. insert에 성공하면 다시~모든 회원정보 조회 요청을 하기위해
  	  MemberDAO객체의 listMembers메소드를 호출하여 ArrayList배열을 받고
  	  웹브라우저 화면에 조회된 정보들을 응답합니다.
  	  
	  			
*/
@WebServlet("/member4")
public class MemberServlet extends HttpServlet {

	protected void doHandle(HttpServletRequest request, 
			 			    HttpServletResponse response) 
			 					 throws ServletException, IOException {
		//DB작업 담당하는 객체 생성
		MemberDAO dao = new MemberDAO();
		
		//1.요청한 값들 중에서 한글이 하나라도 존재하면
		//  HttpServletRequest객체 메모리에서 꺼내올떄 
		//  한글이 깨져서 꺼내와 지므로 무조건 문자를 처리할수 있는 방식(인코딩 방식)을
		//  UTF-8로 설정
		//요약 : 한글처리
		request.setCharacterEncoding("UTF-8");
		
		//2.memberForm.html에서 가입시 입력한 요청한 값 얻기 
		//2.1. 어떤 요청을 했는지(조회?,추가?,삭제?,수정? 중 추가) 판단하기 위해
		//    "addMember" 얻기
		// 2.1.1 <a href='/pro07/member4?command=delMember&id=" + memberVO.getId() + "'>삭제</a>
		//		 를 클릭하여 삭제 요청을 판단하는 값 "delMember" 얻기
		String command = request.getParameter("command");
		
		//DB의 t_member테이블에 회원 추가 요청을 했다면?
		if(command != null && command.equals("addMember")) {
			
			//2.2 요청한 값들 얻기 (회원가입을 위해 입력한 값들 얻기 )
			String _id = request.getParameter("id");
			String _pwd = request.getParameter("pwd");
			String _name = request.getParameter("name");
			String _email = request.getParameter("email");
			
			//2.3 회원가입창에서 입력한 정보들을 MemberVO객체생성후 인스턴스변수에 각각 저장
			MemberVO vo = new MemberVO(_id, _pwd, _name, _email);
			
			//2.4 회원가입(t_member테이블에 MemberVO의 정보를 추가)을 위해
			//MemberDAO에 만들어 놓은 adMember메소드 호출시 매개변수로 
			//-----2.3에서 만든 MemberVO객체의 주소를 전달 해서 insert작업 시키자
			int result = dao.addMember(vo);
			
			System.out.println("회원가입 성공 1 실패 0 = " + result);		
		}//----- if
		// 위 command변수에 저장된 값이 delMember라면?
		// (회원 한 사람의 정보 삭제 요청을 받았다면?)
		else if(command != null && command.contentEquals("delMember")) {
			// http://localhost:8090/pro07/member4?command=delMember&id=memberVO.getId()
			// 요청한 삭제할 회원레코드 id 값 얻기
				// 삭제할 회원 아이디를 HttpServletRequest 객체 메모리에서 얻기
				// 얻는 이유 : DELETE 구문 작성 시 어떤 아이디를 가진 회원을 삭제할 것인지 판단하여
				//            예) DELETE FROM t_member WHRER id=hong;
			String id = request.getParameter("id");
			
			// 삭제할 회원 아이디를 이용해서 DB의 t_member 테이블에 저장된
			// 하나의 회원 레코드를 삭제하기 위해 
			// MemberDAO에 만들어 놓은 delMember 메소드 호출 시~
			// 매개변수로 삭제할 회원의 아이디를 전달해서 DELETE 구문 완성 후 삭제를 실행해라
			// 명령한다.
			dao.delMemebr(id);
		}
		 
		
		//3.1 t_member테이블의 모든 회원을 조회 하기 위해
		//    MemberDAO객체의 listMembers메소드 호출!
		//listMember메소드는  DB의 t_member테이블의 모든 회원정보를 행단위로 조회후
		//ArrayList배열에 담아 반환해 주는 역할을 합니다.
		List list = dao.listMembers();//클라이언트의 웹브라우저로 응답할
									  //조회된 회원데이터 전체(ArrayList배열)
		
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
						out.print("<th>삭제</th>");
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
					out.print("<td><a href='/pro07/member4?command=delMember&id=" + memberVO.getId() + "'>삭제</a></td>");
				out.print("</tr>");
								
				}//for
					
				out.print("</table>");
				
				out.print("<a href='/pro07/memberForm.html'>회원가입</a>");
			out.print("</body>");
		out.print("</html>");
		
		
		
	}// doHandle메소드 
	
	@Override
	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) 
								 throws ServletException, IOException {
		doHandle(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) 
								  throws ServletException, IOException {
		doHandle(request, response);
	}

}






