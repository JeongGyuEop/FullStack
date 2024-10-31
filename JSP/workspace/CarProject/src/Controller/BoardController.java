package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
	    
	    // "/read.bo" -> list.jsp 요청화면에서 글제목 하나를 클릭했을 때
	    //               글번호를 이용해서 글 하나르 조죄해서 중앙화면에 보여줘 요청!
	    
	    // "/password.bo" -> 글 상세화면(read.jsp)에서 글 수정 또는 글 삭제를 위해
	    //					 글 비밀번호를 입력해서 DB의 Board 테이블에 저장된 비밀번호와 비교해서
	    // 					 비밀번호가 저장되어 있느냐, 저장되어 있지 않느냐? 판단  AJAX 요청!
	    
	    // "/updateBoard.do" -> 글 상세 화면(read.jsp)에서 수정할 글 입력하고
	    //					    수정 버튼을 눌러 ajax로 수정 요청! 했을 때
	    
	    // "/deleteBoard.do" -> 글 상세 화면(read.jsp)에서 글 삭제 버튼을 눌러 Ajax로 삭제 요청! 했을 때
	    
	    // "/reply.do" -> 주 글에 대한 답변글을 작성할 수 있는 화면 요청
	    
	    // "/replyPro.do" -> 주글에 대한 답변글 DB의 Board 테이블에 추가 요청
	    
	    
	    System.out.println("요청한 2단계 주소: " + action);
	    
	    ArrayList list = null;
	    BoardVo vo = null;
	    
	    switch(action) {
	    	case "/list.bo" : // 게시판의 모든 글 조회
	    		
	    		HttpSession session_ = request.getSession();
	    		String loginid = (String)session_.getAttribute("id");
	    		
	    		// 부장 호출!
	    		list = boardservice.serviceBoardList();
	    		
	    		// list.jsp 페이지의 페이징 처리 부분에서
	    		// 이전 또는 다음또는 각 페이지 번호중 하나를 클릭했을 때 요청받는 값 얻기
	    		String nowPage = request.getParameter("nowPage");
	    		String nowBlock = request.getParameter("nowBlock");
	    		
	    		request.setAttribute("list", list);
	    		request.setAttribute("center", "board/list.jsp");
	    		request.setAttribute("id", loginid);
	    		request.setAttribute("nowPage", nowPage);
	    		request.setAttribute("nowBlock", nowBlock);
	    		
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
	    		
	    		request.setAttribute("nowPage", request.getParameter("nowPage"));
	    		request.setAttribute("nowBlock", request.getParameter("nowBlock"));
	    		
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
	    		
	    	case "/read.bo" : // 글 제목을 클릭해 글번호를 이용한 글조회 요청!
	    					  // 조회한 글정보 중앙화면에 보여주자
	    		
	    		// list.jsp에서 글제목을 클릭했을 때 요청한 3개의 값 얻기
	    		String b_idx_ = request.getParameter("b_idx"); // 글번호
	    		String nowPage_ = request.getParameter("nowPage"); // 현재 페이지 번호
	    		String nowBlock_ = request.getParameter("nowBlock"); // 현재 보이고 있는 페이지 번호가 속한 블럭위치 번호
	    		
	    		// 부장 호출
	    		// 글 제목을 눌렸을 때 조회된 레코드(글)에 관한 글정보 하나 조회 요청
	    		vo = boardservice.serviceBoardRead(b_idx_);
	    	
	    		// 조회된 글 하나의 정보를 보여줄 중앙 VIEW 경로 request바인딩
	    		request.setAttribute("center", "board/read.jsp");
	    		// 조회된 글 하나의 정보(BoardVo) request에 바인딩
	    		request.setAttribute("vo", vo);
	    		// 중앙 VIEW board/read.jsp 페이지에 전달 후 사용하기 위한 
	    		//nowPage, nowBlock,b_idx 각각 바인딩
	    		request.setAttribute("nowPage", nowPage_);
	    		request.setAttribute("nowBlock", nowBlock_);
	    		request.setAttribute("b_idx", b_idx_);
	    		
	    		nextPage = "/CarMain.jsp";
	    		
	    		break;
	    		
	    //==========================================================================================
	    		
	    	case "/password.do" : // 글수정 또는 삭제를 위해 입력한 글의 비밀번호가
	    						  // DB에 존재하는지 체크 요청
	    		// 요청한 값 얻기
	    	
	    		String b_idx = request.getParameter("b_idx"); // 글번호
	    		String password = request.getParameter("pass"); // 현재 페이지 번호
	    		
	    		// 부장 호출
	    		// 글을 수정, 삭제하기 위한 수정버튼과 삭제버튼 활성화를 위해
	    		// 입력한 글의 비밀번호가 DB에 있는지 체크를 위해 호출!
	    		boolean resultPass = boardservice.servicePassCheck(b_idx, password);
	    		// true : 입력한 글의 비밀번호가 DB에 존재함
	    		// false : 입력한 글의 비밀번호가 DB에 존재하지 않음

	    		if(resultPass) { // true
	    			out.write("비밀번호 맞음");
	    			return;
	    		} else { // false
	    			out.write("비밀번호 틀림");
	    			return;
	    		}
	    		
	    		
	    //==========================================================================================
	    		
	    	case "/updateBoard.do" : // 글수정 요청
	    		
	    		// 글 수정한 값 얻기
	    		String idx_ = request.getParameter("idx"); 
	    		String email_ = request.getParameter("email"); 
	    		String title_ = request.getParameter("title"); 
	    		String content_ = request.getParameter("content"); 
	    		
	    		// 부장 호출
	    		// 수정시 입력한 위 변수 값들을 DB의 Board테이블에 있는 열의 값으로 저장되게 수정 요청
	    		int result_ = boardservice.serviceUpdateBoard(idx_, email_, title_, content_);
	    		
	    		if(result_ == 1) { // UPDATE 성공
	    			out.write("수정 성공"); // 웹브라우저 창을 거쳐서
	    									//
	    									//
	    									// data 매개변수로 "수정 성공"보낸다.
	    			return;
	    		} else { // UPDATE 실패
	    			out.write("수정 실패");
	    			return;
	    		}
    		
	    //==========================================================================================
	    		
	    	case "/deleteBoard.do" : // 글 삭제 2 단계 요청 주소일 때
	    		
	    		// 글 수정한 값 얻기
	    		String delete_idx = request.getParameter("b_idx"); 
	    		
	    		// 부장 호출
	    		// 글 삭제 요청시 삭제할 글번호를 전달해
	    		// 삭제에 성공하면 "삭제 성공" 메세지를 반환받고,
	    		// 삭제에 실패하면 "삭제 실패" 메세지를 반환받자
	    		String delete_result = boardservice.serviceDeleteBoard(delete_idx);
	    		
	    			out.write(delete_result); // AJAX 
	    			return;
	    	
	    //==========================================================================================
		    		
	    	case "/reply.do" : // 
	    		
	    		// 요청한 주 글의 글번호 얻는다.
	    		String b_idx__ = request.getParameter("b_idx");
	    		
	    		// 요청한 답변글을 작성할 사람의 아이디 얻는다
	    		String reply_id_ = request.getParameter("id");
	    		
	    		// 부장 호출
	    		// 로그인한 회원이 주 글에 대한 답변글을 작성할 수 있도록 하기 위해
	    		// 로그인한 회원 아이디를 BoardService의 메소드 호출 시 매개변수로 전달해
	    		// 아이디에 해당하는 회원정보를 조회
	    		MemberVo reply_vo = boardservice.serviceMemberOne(reply_id_);
	    		
	    		// 주(부모) 글번호와 조회한 답변글 작성자 정보를 request에 바인딩
	    		request.setAttribute("b_idx", b_idx__); // 주(부모) 글에 대한 글번호
	    		request.setAttribute("vo", reply_vo);
	    		
	    		// 중앙화면(답변글을 작성할 수 있는 중앙 VIEW) 경로를 request에 바인딩
	    		request.setAttribute("center", "board/reply.jsp");
	    		
	    		nextPage = "/CarMain.jsp";
	    		
	    		break;
	    		
	    //==========================================================================================
	    		
	    	case "/replyPro.do" : // 주글에 대한 답변글 DB의 Board 테이블에 추가 요청
	    		
	    		// 요청한 값(주 글(부모글) 글번호 + 작성한 추가할 답변글 정보) 얻기
	    		String super_b_idx = request.getParameter("super_b_idx"); // 주 글(부모글) 글번호
	    		String reply_id = request.getParameter("id"); // 답변글 작성자 아이디
	    		String reply_name = request.getParameter("writer"); // 답변글 작성자 이름
	    		String reply_email = request.getParameter("email"); // 답변글 작성자 이메일
	    		String reply_title = request.getParameter("title"); // 답변글 제목
	    		String reply_content = request.getParameter("content"); // 답변글 내용
	    		String reply_pass = request.getParameter("pass"); // 답변글 패스워드
	    		
	    		
	    		// 부장 호출
	    		// DB의 Board 테이블에 입력한 답변글 정보 추가 요청
	    		 boardservice.serviceReplyInsertBoard(super_b_idx,
	    				 							  reply_id,
	    				 							  reply_name,
	    				 							  reply_email,
	    				 							  reply_title,
	    				 							  reply_content,
	    				 							  reply_pass);
	    		 
	    		 // 답변글 추가에 성공하면 다시 전체 글목록 조회해서
	    		 // 보여주기 위한 재요청 주소를 nextPage 변수에 저장
	    		 nextPage = "/Board/list.bo";
	    		
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
