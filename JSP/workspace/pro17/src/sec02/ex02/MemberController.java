package sec02.ex02;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//MVC 중에서 C(Controller)역할을 하는 서블릿 
//request객체의 getPathInfo()메소드를 호출하면 
//두단계 이루어진 클라이언트가 요청한 주소 /member/addMember.do 중에서
//  /addMember.do 2단계 요청주소를 반환받을수 있다.

//모든회원정보 조회 요청주소 만들기
//http://localhost:8090/pro17/member/listMembers.do 

//회원가입 입력할 디자인 화면 요청주소 만들기
//http://localhost:8090/pro17/member/memberForm.do

//회원가입 요청 주소 만들기
//http://localhost:8090/pro17/member/addMember.do

//회원수정을 위한 입력하는 디자인 화면 요청 주소 만들기 
//http://localhost:8090/pro17/member/modMemberForm.do?id=admin2

//회원수정할 내용을 입력하고 수정요청을 받는 UPDATE요청주소 만들기
//http://localhost:8090/pro17/member/modMember.do?id=admin2

//listMember.jsp화면에서 삭제<a>클릭했을때의 
//회원삭제 요청 주소 만들기
//http://localhost:8090/pro17/member/delMember.do?id=admin2


@WebServlet("/member/*")
public class MemberController extends HttpServlet {

	MemberDAO memberDAO;
	
	
	//서블릿이 요청을 받았을때. 가장 처음에 MemberController클래스가 
	//톰캣 서버메모리에 로드되는 시점에 
	//개발자가 변수의 값을 초기화 해놓을때 사용되는 init메소드 오버라이딩
	@Override
	public void init() throws ServletException {
		//MemberDAO객체를 생성해서 memberDAO전역변수에 초기화
		memberDAO = new MemberDAO();
	}

	//클라이언트가 GET요청방식으로 요청하면 호출되어 요청을 처리하는 콜백메소드 
	@Override
	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) 
							throws ServletException, IOException {
		doHandle(request,response);
	}
	//클라이언트가 POST요청방식으로 요청하면 호출되어 요청을 처리하는 콜백메소드 
	@Override
	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) 
								  throws ServletException, IOException {
	    doHandle(request,response);	
	}

	//클라이언트가 GET 또는 POST요청시 모두 한번에 웹브라우저로 응답할 Model을 전달하는 일반 메소드 
	protected void doHandle(HttpServletRequest request, 
			  				HttpServletResponse response) 
			  					throws ServletException, IOException {
		
		//1. 문자셋 (인코딩)방식 UTF-8로 설정
		request.setCharacterEncoding("UTF-8");
		
		
		//2. request객체의 getPathInfo()메소드를 호출하여
		//   클라이언트가 요청한  전체 URL주소중에서
		//   2단계 요청주소 만 얻어 냅니다
		String action = request.getPathInfo();
		System.out.println("클라이언트가 요청단 2단계 주소: " + action);
//클라이언트가 회원 삭제 요청한 2단계 요청주소 : "/delMember.do"
		
		
//클라이언트가 회원 정보 수정을 위해 입력한 내용을 DB의 t_member테이블에 UPDATE요청하는 2단계 주소
//: "/modMember.do"
		
//클라이언트가 회원수정 입력 화면 VIEW(/test03/ModMemberForm.jsp)을 요청한 2단계 주소 
//: "/modMemberForm.do"		
				
//클라이언트가 회원가입 요청한 2단계 주소 : "/addMember.do"
		
//클라이언트가 회원 조회 요청한 2단계 주소 : "/listMembers.do" 		
		
//클라이언트가 회원가입을 위해 입력하는 VIEW(/tes02/MemberForm.jsp) 요청주소
//-> /memberForm.do 
		
		//서블릿에서 재요청할 뷰 주소 경로를 저장할 변수 선언
		String nextPage = null;
		
		//action변수에 저장된 2단계 요청한 주소값에 따라
		//if문을 분기해서 요청한 작업을 수행하는데......
		
		//만약 action변수에 저장된 값이 null이거나  
		// /listMembers.do 2단계 요청주소를 받으면(모든 회원을 조회 하는 요청주소를 받으면)
		if(action == null  || action.equals("/listMembers.do")) {
			
			List  list = memberDAO.listMembers();
			
			request.setAttribute("membersList", list);
			
			//조회한 회원정보를 보여줄  VIEW경로 를 nextPage변수에 저장
			nextPage = "/test03/listMembers.jsp";
			
		//listMembers.jsp화면에서 
		//가장 아래에 작성된 <a>회원가입하러가기</a>를 클릭하여
		//회원가입시 입력하는 화면 VIEW (/test02/MemberForm.jsp)요청을 받았을떄~~
		}else if(action.equals("/memberForm.do")) {
			
			//클라이언트의 웹브라우저로 보여줄 VIEW주소를 저장
			nextPage = "/test03/MemberForm.jsp";
		
		
		//MemberForm.jsp에서 입력한 가입할 정보를
	    //DB의 t_member테이블에 INSERT요청 주소를 받았을때~~~
		}else if(action.equals("/addMember.do")) {
			//요청한 값 얻기(입력한 가입할 새 회원정보를 request객체로 부터 얻습니다)
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			//MemberVO객체 생성후 각변수에 요청한 값들 저장
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			
			//입력한 회원정보들을 DB의 t_member테이블에 INSERT명령 하기 위해
			//MemberDAO객체의 addMember메소드 호출!
			memberDAO.addMember(memberVO);
			
			//회원가입에 성공하면 listMembers.jsp에 보여줄 가입 성공 판단값 
			//request에 바인딩
			request.setAttribute("msg", "addMember");
			
			
			//새 회원 정보를 DB의 t_member테이블에 INSERT후 
			//다시~ 모든 회원을 조회 해줘~ 라는 재요청주소를
			//nextPage변수에 저장하면
			//MemberController가 웹브라우저 주소창으로 인한 재요청을 받아
			//다시 조회 요청주소를 받아 처리할것입니다
			nextPage = "/member/listMembers.do";

//listMembers.jsp화면에서 조회된 회원한사람의 정보를 수정하기 위해
//수정 <a>를 클릭하여 수정시 입력하는 화면(VIEW-> /test03/modMemberForm.jsp)요청을 받았을때
		}else if(action.equals("/modMemberForm.do")) {
			
			//요청한 값 얻기(수정을 위해 전달받은 회원의 id얻기)
			String id = request.getParameter("id");
			
			//회원정보 수정창을 요청하면서 전송된 id를 이용해
			//수정 전 DB에 저장된 회원정보를 검색해 옵니다
			MemberVO memInfo = memberDAO.findMember(id);
			
			//request에 바인딩하여 회원정보 수정입력창에서 수정하기 전 조회된 회원정보를 전달시킵니다
			request.setAttribute("memInfo", memInfo);
			
			//회원 정보 수정 시 입력하는 페이지 재요청을 위해 주소 경로 저장
			nextPage = "/test03/modMemberForm.jsp";
		
		//회원정보 수정창(modMemberForm.jsp)에서 수정할 내용을 입력하고
		//수정하기 버튼을 클릭하여 DB의 테이블 정보를 수정하는 요청 2단계 주소를 받았을때
		}else if(action.equals("/modMember.do")) {
			
			//요청한 값얻기(수정시 입력한 회원의 정보 얻기)
			//MemberVO객체에 요청한 값들 저장
			MemberVO memberVO = new MemberVO(request.getParameter("id"),
											 request.getParameter("pwd"),
											 request.getParameter("name"),
											 request.getParameter("email")
											);
			//DB의t_member테이블에 입력한 정보를 UPDATE시키기 위해 
			//MemberDAO객체의 modMember메소드 호출시 매개변수로 MemberVO객체를 전달해서
			//UPDATE명령합니다
			memberDAO.modMember(memberVO);
			
			//수정 후 (UPDATE 후 ) 모든 회원 정보를 조회 해서 보여주는 listMembrs.jsp에
			//수정 작업 완료 메세지를 전달해 보여주기 위해 
			//request내장객체 메모리영역에 응답할 값을 바인딩
			request.setAttribute("msg", "modified");
			
			//수정 완료 후 모든 회원정보를 조회 요청을 MemberController로 재요청하기 위해
			//nextPage변수값 설정
			nextPage = "/member/listMembers.do";
		
			
	    //회원 ID를 SQL문으로 전달해 테이블의 회원레코드 정보 삭제요청 2단계 주소를 받았을때
		}else if(action.equals("/delMember.do")) {
			
			//요청한값얻기(삭제할 회원의 ID얻기)
			String id = request.getParameter("id");
			
			//ID에 해당되는 회원정보(한행)를  DB의 t_memer테이블에 삭제 DELETE명령!
			memberDAO.delMember(id);
			
			//삭제에 성공하면 listMembers.jsp에 삭제작업완료 메세지를 전달하기 위해
			//request에 삭제 성공 조건값을 바인딩 합니다
			request.setAttribute("msg", "deleted");
			
			//삭제후 listMembers.jsp화면에 모든회원 정보를 조회 해서 보여주기위해
			//MemberController에 조회할 재요청 주소를 nextPage변수에 저장
			nextPage = "/member/listMembers.do"; 
		}
		
		
		//RequestDispatcher클래스를 이용한 디스패처방식으로
		//   MVC중에서 View(listMembers.jsp)를 재요청(포워딩)하면서
		//   조회된 ArrayList배열에 바인딩된 request객체를 공유 ~~~~~
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(nextPage);
		
		//실제 재요청(포워딩)시 request, response 객체 공유
		dispatcher.forward(request, response);
			
	}
	
}











