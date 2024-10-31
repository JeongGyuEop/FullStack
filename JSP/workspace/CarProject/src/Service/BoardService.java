package Service;

import java.util.ArrayList;

import Dao.BoardDAO;
import Dao.MemberDAO;
import Vo.BoardVo;
import Vo.MemberVo;

// 단위 기능의 메소드들을 가지고 있는 클래스
// 부장
public class BoardService {
	
	BoardDAO boarddao;
	MemberDAO memberdao;
	
	// 기본 생성자
	public BoardService() {
		boarddao = new BoardDAO();
		memberdao = new MemberDAO();
	}

	//----------
	// DB의 board 테이블에 저장되어 있는 모든 글목록을 조회하는 기능의 메소드
	public ArrayList serviceBoardList() {
		
		return boarddao.boardListAll();
		
	}

	//----------
	// 검색 기준값과 입력한 검색어를 포함하고 있는 글목록을 조회하는 기능의 메소드
	public ArrayList serviceBoardKeyWord(String key, String word) {
	
		return boarddao.boardList(key, word); // BoardController(사장)에게 반환
		
	}

	//----------
	// 회원 아이디를 매개변수로 받아서 회원 한명을 조회 후 반환하는 기능의 메소드
	public MemberVo serviceMemberOne(String memberid) {

		return memberdao.memberOne(memberid);
	}

	//----------
	// 작성한 새글 정보 하나를 DB의 board 테이블에 추가(INSERT) 기능의 메소드
	public int serviceInsertBoard(BoardVo vo) {
		
		return boarddao.insertBoard(vo); // 1 또는 0을 반환받아 다시 반환(리턴)
		
	}

	//----------
	// 글 번호(b_idx)를 이용하여 조회 명령하는 기능의 메소드
	public BoardVo serviceBoardRead(String b_idx_) {
		return boarddao.boardRead(b_idx_);
	}

	//----------
	// 글을 수정, 삭제하기 위한 버튼들을 활성화해서 보여주기 위해
	// 입력한 글의 비밀번호가 DB에 있는지 체크하는 기능의 메소드
	public boolean servicePassCheck(String b_idx, String password) {
		return boarddao.passCheck(b_idx, password);
	}

	//----------
	// 글 수정 요청 UPDATE 하기 위한 메소드 호출!
	public int serviceUpdateBoard(String idx_, String email_, String title_, String content_) {
		// 수정에 성공하면 1을 반환, 실패하면 0 반환
		return boarddao.updateBoard(idx_, email_, title_, content_);
	}
	
	//----------
	// 글 삭제 요청 DELETE 하기 위한 메소드 호출!
	public String serviceDeleteBoard(String delete_idx) {
					// 삭제에 성공하면 "삭제 성공", 실패하면 "삭제 실패" 반환
		return  boarddao.deleteBoard(delete_idx);
	}

	
	// DB 의 Board 테이블에 입력한 답변글 추가하기 위해 호출!
	public void serviceReplyInsertBoard(String super_b_idx, String reply_id, 
										String reply_name, String reply_email,
										String reply_title, String reply_content, String reply_pass) {
		
		boarddao.replyInsertBoard(super_b_idx,
										 reply_id,
										 reply_name,
										 reply_email,
										 reply_title,
										 reply_content,
										 reply_pass);
		
	}
	

}
