package sec01.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//MVC 중에서 C(Controller)역할을 하는 서블릿 

//순서1. 클라이언트가 웹브라우저 주소창에 http://localhost:8090/pro17/mem.do
//      회원정보 조회 요청주소를 입력하여 MemberController에 조회 요청합니다

//순서2. doHandle메소드 내부에서 request를 매개변수로 받아서 
//      MemberDAO객체(MVC중에서 M의 역할을 하는 DB작업하는객체)의
//      listMembers()메소드를 호출하여 모든 회원정보들을 DB에서 조회하는 명령을하게된다

//@WebServlet("/mem.do")
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
		
		//2. 요청주소 /mem.do 를 받은 MemberContrller서블릿은 
		//   모든 회원정보를 t_member테이블에서 조회 해 오기 위해
		//   MemberDAO객체의 listMembers메소드를 호출하는 조회 명령 합니다
		List list = memberDAO.listMembers();
		
		//3. /test01폴더에 만들어 놓은 listMembers.jsp로 
		//   조회한 회원정보들을 보여줘서 웹브라우저에 출력하기 위해 
		//   request내장객체 메모리 영역에 조회된 ArrayList배열 자체를 바인딩(묶어서 저장)
		request.setAttribute("membersList",list);
		
		//4. RequestDispatcher클래스를 이용한 디스패처방식으로
		//   MVC중에서 View(listMembers.jsp)를 재요청(포워딩)하면서
		//   조회된 ArrayList배열에 바인딩된 request객체를 공유 ~~~~~
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/test01/listMembers.jsp");
		
		//실제 재요청(포워딩)시 request, response 객체 공유
		dispatcher.forward(request, response);
		
	}
	
}











