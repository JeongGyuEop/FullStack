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
	
	
	// DB의 board 테이블에 저장되어 있는 모든 글의 총 수를 조회하는 기능의 메소드
	
	
	// 검색 기준값과 입력한 검색어를 포함하고 있는 모든 글의 총갯수를 조회하는 기능의 메소드
	
	
	// 글번호(b_idx열값)을 이용해서 글수정 또는 글삭제를 위해 DB로 부터 글정보를 조회하는 기능의 메소드
	
	
	//....
}
