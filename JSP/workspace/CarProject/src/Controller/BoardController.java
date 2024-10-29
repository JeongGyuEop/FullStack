package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.BoardService;
import Vo.BoardVo;
import Vo.MemberVo;


// 사장 ...

// MVC 중에서 C 역할

// 게시판 관련 기능 요청이 들어옹면 호출되는 사장님(컨트롤러)
@WebServlet("/Board/*")
public class BoardController extends HttpServlet {
	
	// 부장
	BoardService boardservice;

	@Override
	public void init() throws ServletException {
		boardservice = new BoardService();
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
		
		// 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		// MIME TYPE 설정
		response.setContentType("text/html;charset=utf-8"); 
		
		//웹브라우저로 출력할 출력 스트림 생성
	    PrintWriter out = response.getWriter();
	      
	    // 조건에 따라 포워딩 또는 보여줄 VIEW 주소 경로를 저장할 변수
	    String nextPage = null;
	    
	    // 재요청할 경로 주소를 저장할 변수
	    String center = null;
		
	    // 클라이언트가 BoardController로 요청한 전체 주소 중에서
	    // 2단계 요청한 주소 얻어서 action 변수에 저장
	    String action = request.getPathInfo(); // 2단계 요청주소
	    // "/list.bo" -> DB의 Board 데이블에 저장된 모든 글목록 조회 요청!
	    // "/searchlist.bo" -> DB의 Board 테이블에 저장된 글 목록을 조회하되 입력한 검색기준 열값과
	    //					   검색어를 포함하는 글 목록 조회 요청!
	    // "/write.bo" -> 새글 작성화면VIEW 요청
	    // "/writePro.bo" -> 입력한 새 글 정보를 DB의 Board 테이블에 추가 요청!
	    
	    System.out.println("요청한 2단계 주소: " + action);
	    
	    ArrayList list = null;
	    BoardVo vo = null;
	    
	    switch(action) {
	    	case "/list.bo" : // 게시판의 모든 글 조회
	    		
	    		// 부장 호출!
	    		list = boardservice.serviceBoardList();
	    		
	    		request.setAttribute("list", list);
	    		request.setAttribute("center", "board/list.jsp");
	    		
	    		nextPage = "/CarMain.jsp";
	    		
	    		break;
	    		
	    //==========================================================================================
	    		
	    	case "/searchlist.bo" : // 검색 키워드로 글 조회
	    		
	    		// 요청한 값 얻기(조회를 위해 선택한 Option 값, 입력한 검색어)
	    		String key = request.getParameter("key");
	    		String word = request.getParameter("word");
	    		
	    		
	    		// 부장 호출!
	    		// 검색 기준 열의 값과 입력한 검색어 단어를 포함하고 있는 글 목록 조회 명령!
	    		list = boardservice.serviceBoardKeyWord(key, word);
	    		
	    		// VIEW 중앙화면에 조회된 글목록을 보여주기 위해
	    		// request 내장 객체에 조회된 정보 바인딩
	    		request.setAttribute("list", list);
	    		request.setAttribute("center", "board/list.jsp");
	    		
	    		nextPage = "/CarMain.jsp";
	    		
	    		break;
	    		
	    //==========================================================================================
	    		
	    	case "/write.bo" : // 새글을 입력할 수 있는 화면 요청
	    		
	    		HttpSession session = request.getSession();
	    		
	    		String memberid = (String)session.getAttribute("id");
	    		
	    		// 부장 호출
	    		// 새글을 입력할 수 있는 화면에 로그인한 사람(글쓰는 사람)의 정보를 보여주기 위해
	    		// 조회하자
	    		MemberVo membervo = boardservice.serviceMemberOne(memberid);
	    		
	    		// 글쓰기 중앙화면(VIEW)에 조회된 회원의 이름, 이메일, 아이디 등을 보여주기 위해
	    		// MemberVo 객체를 request 내장 객체에 바인딩
	    		request.setAttribute("membervo", membervo);
	    		
	    		// 글쓰기 중앙화면 (VIEW) 경로를 request 내장 객체에 바인딩
	    		request.setAttribute("center", "board/write.jsp");
	    		
	    		nextPage = "/CarMain.jsp";
	    		
	    		break;
	    		
	    //==========================================================================================
	    		
	    	case "/writePro.bo" : // 새글을 입력할 수 있는 화면 요청
	    		
	    		// 요청한 값 얻기
	    		String writer = request.getParameter("w");
	    		String email = request.getParameter("e");
	    		String title = request.getParameter("t");
	    		String content = request.getParameter("c");
	    		String pass = request.getParameter("p");
	    		String id = request.getParameter("i");
	    		
	    		// 요청한 값들을 Vo에 저장
	    		vo = new BoardVo();
	    		vo.setB_name(writer);
	    		vo.setB_email(email);
	    		vo.setB_title(title);
	    		vo.setB_content(content);
	    		vo.setB_pw(pass);
	    		vo.setB_id(id);
	    		
	    		// 부장 호출
	    		// 웹브라우저에 응답할 값 마련(DB에 새 글 정보 추가에 성공 또는 실패 관련데이터)
	    		// result == 1 -> insert 성공
	    		// result == 0 -> insert 실패
	    		int result = boardservice.serviceInsertBoard(vo);
	    		
	    		// 1 -> "1"로 변환하거나 또는 0 -> "0" 문자열로 변환해서 저장
	    		String go = String.valueOf(result);
	    		
	    		if(go.equals("1")) { // insert 성공했다면?
	    			out.print(go); // "1" 전달 writer.jsp 요청한 페이지로 응답
	    		} else { // "0" insert 실패했다면?
	    			out.print(go); // "0" 전달 writer.jsp 요청한 페이지로 응답
	    		}
	    		
	    		return; // doHandle 메소드 빠져나가자.
	    		
	    //==========================================================================================
	    	default:
	    		break;
	    }
		
		// 디스패처 방식 포워딩(재요청)
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
		
	} // doHandle 메소드
}
