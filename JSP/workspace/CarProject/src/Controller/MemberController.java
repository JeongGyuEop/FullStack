package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.CarDAO;
import Service.MemberService;
import Vo.CarConfirmVo;
import Vo.CarListVo;
import Vo.CarOrderVo;

// 사장 ...

// MVC 중에서 C 역할

// /member/join.me?center=members/join.jsp

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	
	// 부장
	MemberService memberservice;

	@Override
	public void init() throws ServletException {
		memberservice = new MemberService();
	}
	
	// doGet doPost 메소드 오버라이딩
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doHandle(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8"); // MIME TYPE 설정
		
		//웹브라우저로 출력할 출력 스트림 생성
	    PrintWriter out = response.getWriter();
	      
	    // 조건에 따라 포워딩 또는 보여줄 VIEW 주소 경로를 저장할 변수
	    String nextPage = null;
	    
	    // 재요청할 경로 주소를 저장할 변수
	    String center = null;
		
	    //=============================================================================================
	    // 1. Top.jsp 화면에서 회원가입 링크를 클릭했을 때 회원 가입 중앙화면 VIEW요청 주소를 받는다.
	    // 	  요청한 전체 URL 중에서 2단계 요청한 주소 얻기
	    //    /member/join.me?center=members/join.jsp
	    // 2단계 요청 주소 ->"join.me"
	    
	    // 2. join.jsp 화면에서 회원가입을 하기전 입력한 아이디가 DB의 테이블에 존재하는지
	    //	  확인하는 요청 전체 URL 중에서 2단계 요청한 주소 얻기
	    //    /member/joinIdCheck.me
	    // 2단계 요청 주소 -> "/joinTdCheck.me"
	    
	    // 3. join.jsp 화면에서 가입할 새 회원 정보를 입력하고 가입 요청을 받았을 때
	    //    전체 URL 중에서 2단계 요청한 주소 얻기
	    //    /member/joinPro.me
	    // 2단계 요청한 주소 -> "joinPro.me"
	    
	    // 4. Top.jsp 상단의 로그인 버튼을 클릭하여 아이디, 비밀번호를 입력받을 수 있는 중앙
	    //    VIEW 요청을 받았을 때 전체 URL중에서 2단계 요청한 주소 넣기
	    //    /member/login.me
	    // 2단계 요청한 주소 -> "login.me"
	    
	    // 5. login.jsp 화면에서 아이디, 비밀번호를 입력 후 로그인 요청
	    //    전체 URL 중에서 2단계 요청한 주소 얻기
	    //    /member/loginPro.me
	    // 2단계 요청한 주소 -> "loginPro.me"
	    
	    // 6. Top.jsp 화면 상단의 로그아웃 링크를 클릭해서 로그아웃 요청했을 때 
	    //    전체 URL 중에서 2단계 요청한 주소 얻기
	    //    /member/logout.me
	    // 2단계 요청한 주소 -> "/logout.me"
	    //=========================================================================================
	    
	    String action = request.getPathInfo(); // 2단계 요청주소
	    
	    
	    System.out.println("요청한 2단계 주소: " + action);
	    
	    switch(action) {
	    	case "/join.me": //회원 가입 중앙 VIEW 요청
	    		
	    		// 부장 ---
	    		center = memberservice.serviceJoinName(request);
	    		//"members/join.jsp
	    		
	    		// request 객체에 "members/join.jsp" 중앙화면 뷰 주소 바인딩
	    		request.setAttribute("center", center);
	    		
	    		nextPage = "/CarMain.jsp";
	    		
	    		break;
	   //===========================================================================================
	    	case "/joinIdCheck.me": // 아이디 중복 체크 요청!
	    		
	    		// 부장 --
	    		// 입력한 아이디가 DB에 저장되어 있는지 확인하는 작업
	    		// true -> 중복, false -> 중복 아님 둘 둥 하나를 반환 받음 
	    		boolean result = memberservice.serviceOverLappedId(request);
	    		
	    		// 아이디 중복결과를 다시 한번 확인하여 
	    		// join.jsp 파일과 연결된 join.js 파일에 작성해 놓은
	    		// $.ajax 메소드 내부의
	    		// success:function의 data 매개변수로 웹브라우저를 거쳐 보낸다!!
	    		if(result == true) {
	    			out.write("not_usable");
	    			return;
	    		} else if(result == false) {
	    			out.write("usable");
	    			return;
	    		}
	    		break;
	    	
	   //===========================================================================================
	    	case "/joinPro.me": // 회원가입 2단계 요청주소와 같다면
	    		
	    		// 부장에게 시킴
	    		int result1 = memberservice.serviceInsertMember(request);
	    	
	    		nextPage = "/CarMain.jsp";
	    		
	    		break;
	    		
	    //=========================================================================================
	    	case "/login.me":
	    		
	    		// 부장에게 시킴
	    		center = memberservice.serviceLoginMember();
	    		// "members/login.jsp"
	    		
	    		// 중앙 화면 VIEW 주소 바인딩
	    		request.setAttribute("center", center);
	    		
	    		// 재요청할 전체 메인화면 주소를 저장
	    		nextPage = "/CarMain.jsp";
	    		
	    		break;
	    		
	    //=========================================================================================
	    	case "/loginPro.me": // 로그인 요청을 한 2단계 요청주소 일 때
	    		
	    		// 부장에게 시킴
	    		// check 변수의 값이 1 이면 아이디, 비밀번호가 DB에 존재한다.
	    		//                   0 이면 아이디만 DB에 존재한다.
	    		//                  -1 이면 아이디 DB에 존재하지 않음
	    		int check = memberservice.serviceUserCheck(request);
	    		
	    		if(check == 0) { // 아이디 맞음, 비밀번호 틀림
	    			out.println("<script>");
	    				out.println("window.alert('비밀번호 틀림');");
	    				out.println("history.go(-1);");
	    			out.println("</script>");
	    			
	    			return; // doHandle 메소드를 빠져나가기 위함
	    			
	    		} else if(check == -1) { // 아이디 틀림
	    			out.println("<script>");
    					out.println("window.alert('아이디 틀림');");
    					out.println("history.go(-1);");
    				out.println("</script>");
	    		
    				return; // doHandle 메소드를 빠져나가기 위함
	    		}
	    		
	    		// 재요청할 전체 메인화면 주소를 저장
	    		nextPage = "/CarMain.jsp";
	    		
	    		break;
	    		
	    //=========================================================================================
	    	case "/logout.me": // 로그아웃 요청을 한 2단계 요청주소 일 때
	    		
	    		// 부장 시키자
	    		// - 로그아웃 화면을 보여주기 위해 session 영역에 저장된 아이디를 제거
	    		memberservice.serviceLogOut(request);

	    		// 메인화면을 재요청해서 로그아웃된 상단을 보여주기 위해 주소 저장
	    		// 중앙 페이지는 "Center.jsp"가 출력된다.
	    		nextPage = "/CarMain.jsp";
	    		
	    		break;
	    		
	    //==========================================================================================
	    	default:
	    		break;
	    }
		
		// 디스패처 방식 포워딩(재요청)
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
		
	} // doHandle 메소드
}
