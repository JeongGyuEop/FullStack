package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import Vo.BoardVo;
import Vo.CarConfirmVo;
import Vo.CarListVo;
import Vo.CarOrderVo;
import Vo.MemberVo;

// MVC 중에서 M을 얻기 위한 클래스

// 데이터 베이스와 연결하여 비즈니스 로직 처리하는 클래스
// 사원
public class BoardDAO {

	Connection con;
	
	PreparedStatement pstmt;
	
	ResultSet rs;
	
	DataSource ds;
	
	// 커넥션 풀 얻는 생성자
	public BoardDAO() {
		try {
			
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:/comp/env/jdbc/oracle");
			
		} catch(Exception e) {
			System.out.println("커넥션풀 얻기 실패 오류 : " + e.toString());
		}
	}
	
	// 자원해제(Connection, PreparedStatement, ResultSet) 기능의 메소드
	private void closeResource() {
		try {
			if(con != null) {con.close();}
			if(pstmt != null) {pstmt.close();}
			if(rs != null) {rs.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//------------
	// 현재 DB의 board 테이블에 저장된 모든 게시글 조회
	public ArrayList boardListAll() {

		String sql = null;
		
		ArrayList list = new ArrayList();
		
		try {
			
			con = ds.getConnection(); // DB 연결
		
			sql = "SELECT * FROM board ORDER BY b_group ASC";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			// 조회된 ResultSet 정보를 레코드 단위로 얻어서
			// BoardVo객체에 레코드 정보를 반복해서 저장하고
			// BoardVo 객체들을 ArrayList 배열에 반복해서 추가
			while(rs.next()) {
				
				BoardVo vo = new BoardVo(rs.getInt("b_idx"),
										 rs.getInt("b_group"),
										 rs.getInt("b_level"),
										 rs.getInt("b_cnt"),
										 rs.getString("b_id"),
					   					 rs.getString("b_pw"),
										 rs.getString("b_name"),
										 rs.getString("b_email"),
										 rs.getString("b_title"),
										 rs.getString("b_content"),
										 rs.getDate("b_date"));
				list.add(vo);
				
			}
			
		} catch(Exception e) {
			System.out.println("BoardDAO의 boardListAll 메소드에서 오류: " + e);
			e.printStackTrace();
		} finally {
			// 자원해제
			closeResource();
		}
		
		return list;
	}

	//----------
	// 선택한 option의 value 값(검색기준열의 값)과 입력한 검색어 단어가 포함된 글목록 조회
	public ArrayList boardList(String key, String word) {

		String sql = null;
		
		ArrayList list = new ArrayList();
		
		// 검색어를 입력했다면?
		if(!word.equals("")) {
			
			// 검색 기준 열의 값 '제목 + 내용' 선택했다면?
			if(key.equals("titleContent")) {
				
				sql = "SELECT * FROM board "
						+ "where b_title like '%"+ word + "%' "
						+ "OR b_content like '%"+ word + "%' "
						+ "ORDER BY b_group asc";
				
			} else { // 검색 기준 열의 값 '작성자'선택했다면?
				
				sql = "SELECT * FROM board "
						+ "where b_name like '%"+ word + "%' "
						+ "ORDER BY b_group asc";
				
			}
			
		} else { // 검색어를 입력하지 않았다면?
			
			sql = "SELECT * FROM board ORDER BY b_group asc";
			
		}
		
		try {
			
			con = ds.getConnection(); // DB 연결
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			// 조회된 ResultSet 정보를 레코드 단위로 얻어서
			// BoardVo객체에 레코드 정보를 반복해서 저장하고
			// BoardVo 객체들을 ArrayList 배열에 반복해서 추가
			while(rs.next()) {
				
				BoardVo vo = new BoardVo(rs.getInt("b_idx"),
										 rs.getInt("b_group"),
										 rs.getInt("b_level"),
										 rs.getInt("b_cnt"),
										 rs.getString("b_id"),
					   					 rs.getString("b_pw"),
										 rs.getString("b_name"),
										 rs.getString("b_email"),
										 rs.getString("b_title"),
										 rs.getString("b_content"),
										 rs.getDate("b_date"));
				list.add(vo);
				
			}
			
		} catch(Exception e) {
			System.out.println("BoardDAO의 boardList 메소드에서 오류: " + e);
			e.printStackTrace();
		} finally {
			// 자원해제
			closeResource();
		}
		
		
		return list; // BoardService (부장)에게 반환
	}

	//----------
	// 입력한 새글 정보를 DB의 board 테이블에 추가 insert 시키는 기능의 메소드
	public int insertBoard(BoardVo vo) {
		
		int result = 0;
		String sql = null;
		
		try {
			
			con = ds.getConnection(); // DB 연결
			
			// 주글 insert 규칙2. 두번째 글부터 추가되는 글들의 pos를(b_group) 1 증가 시킨다.
			sql = "UPDATE board SET b_group = b_group + 1";
			
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			// 주글 insert 규칙 3. 처음 입력되는 주글의 pos(b_group)과, depth(b_level)은 0 0 으로 insert 시킨다.
			sql = "insert into board(b_idx, b_id, b_pw, b_name,"
				  	  + " b_email, b_title, b_content, b_group, b_level, b_date, b_cnt )"
					  + " values(border_b_idx.nextVal, ?,?,?,?,?,?,0,0,sysdate,0)";	
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getB_id());
			pstmt.setString(2, vo.getB_pw());
			pstmt.setString(3, vo.getB_name());
			pstmt.setString(4, vo.getB_email());
			pstmt.setString(5, vo.getB_title());
			pstmt.setString(6, vo.getB_content());
			
			result = pstmt.executeUpdate(); // insert 성공하면 1 반환, 실패하면 0반환
		
		} catch(Exception e) {
			System.out.println("BoardDAO의 insertBoard 메소드에서 오류: " + e);
			e.printStackTrace();
		} finally {
			// 자원해제
			closeResource();
		}
		
		return result; // 1 또는 0 반환 BoardService 에게
	}

	//----------
	// 글번호를 이용해 글 하나 조회
	public BoardVo boardRead(String b_idx_) {
		
		BoardVo vo = null;
		
		String sql = null;
		
		try {
			
			con = ds.getConnection(); // DB 연결
			
			sql = "SELECT * FROM board WHERE b_idx=?";	
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, b_idx_);
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) {
				// ResultSet 에서 조회된 레코드의 모든 열 값을 얻어
				// BoardVo 객체 생성후 각 변수에 저장
				vo = new BoardVo(rs.getInt("b_idx"),
								rs.getInt("b_group"),
								rs.getInt("b_level"),
								rs.getInt("b_cnt"),
								rs.getString("b_id"),
					   		 	rs.getString("b_pw"),
					   		 	rs.getString("b_name"),
					   		 	rs.getString("b_email"),
					   		 	rs.getString("b_title"),
					   		 	rs.getString("b_content"),
					   		 	rs.getDate("b_date"));
			}
		
		} catch(Exception e) {
			System.out.println("BoardDAO의 boardRead 메소드에서 오류: " + e);
			e.printStackTrace();
		} finally {
			// 자원해제
			closeResource();
		}
		
		return vo;
	}

	//----------
	// 글을 수정, 삭제하기 위한 버튼들을 활성화해서 보여주기 위해
	// 입력한 글의 비밀번호가 DB에 있는지 체크하는 기능의 메소드
	public boolean passCheck(String b_idx, String password) {
		
		boolean result = false;
		
		String sql = null;
		
		try {
			
			con = ds.getConnection(); // DB 연결
			
			// board 테이블에 b_idx글번호 열값이 매개변수 b_id로 전달받은 값이고,
			//    			  b_pw글 비밀번호 열값이 매개변수 password 로 전달받은 값인
			// 행의 모든 열값을 조회
			sql = "SELECT * FROM board WHERE b_idx=? AND b_pw=? "
					+ "ORDER BY b_idx desc";	
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, b_idx);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) { // 입력한 비밀번호로 한줄이 조회되니 비밀번호가 저장되어 있음
				result = true; 
			}
			
		
		} catch(Exception e) {
			System.out.println("BoardDAO의 passCheck 메소드에서 오류: " + e);
			e.printStackTrace();
		} finally {
			// 자원해제
			closeResource();
		}
		return result;
	}

	//----------
	// 수정시 입력한 글 정보를 DB에 UPDATE(열의 값을 수정)하는 메소드
	public int updateBoard(String idx_, String email_, String title_, String content_) {

		int result = 0;
		
		String sql = null;
		
		try {
			
			con = ds.getConnection(); // DB 연결
			
			// board 테이블에 b_idx글번호 열값이 매개변수로 전달받은 수정할 글번호이면
			// 글번호가 저장된 레코드의 b_email, b_title, b_content 열의 값을
			// 우리가 수정시 입력한 값들로 변경(UPDATE)해라
			sql = "UPDATE board SET b_email=?, b_title=?, b_content=? WHERE b_idx=?";	
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, email_);
			pstmt.setString(2, title_);
			pstmt.setString(3, content_);
			pstmt.setString(4, idx_);
			
			result = pstmt.executeUpdate(); 
			
		} catch(Exception e) {
			System.out.println("BoardDAO의 updateBoard 메소드에서 오류: " + e);
			e.printStackTrace();
		} finally {
			// 자원해제
			closeResource();
		}
		
		return result;
	}

	//----------
	// 삭제할 글번호를 매개변수로 받아 글(레코드)삭제 후
	// 삭제에 성공하면 "삭제 성공" 반환, 실패하면 "삭제 실패" 반환하는 메소드
	public String deleteBoard(String delete_idx) {
		
		// "삭제 성공" 또는 "삭제 실패" 메세지 저장할 변수
		String result = null;
		
		String sql = null;
		
		try {
			
			con = ds.getConnection(); // DB 연결
			
			// board 테이블의 b_idx 열(글번호가 저장되는 열)의 값이 매개변수로 전달받은 글번호와 같으면?
			// 글번호가 저장된 행(글 하나의 정보 레코드) 삭제
			sql = "DELETE FROM board WHERE b_idx=?";	
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(delete_idx));
			
			int queryResult = pstmt.executeUpdate(); 
			
			if(queryResult == 1) {
				result = "삭제 성공";
			} else {
				result = "삭제 실패";
			}
			
		} catch(Exception e) {
			System.out.println("BoardDAO의 deleteBoard 메소드에서 오류: " + e);
			e.printStackTrace();
		} finally {
			// 자원해제
			closeResource();
		}
			
		return result;
	}

	// 작성한 답변들을 DB의 Board 테이블에 INSERT
	// 규칙1. 답변글을 추가하는 주 글(부모글)의 b_group 열의 값보다 큰값이 저장된
	//		  다른 주글의 b_group열의 값을 1증가시킨다.
	// 규칙2. 추가(INSERT)하는 답변글은 주 글(부모글)의  b_group열 값을 가져와 +1을 해서 insert 한다.
	// 규칙3. 추가(INSERT)하는 답변글은 주 글(부모글)의  b_level열 값을 가져와 +1을 해서 insert 한다.
	public void replyInsertBoard(String super_b_idx, String reply_id, String reply_name,
								 String reply_email, String reply_title, 
								 String reply_content, String reply_pass) {
		
		String sql = null;
		
		try {
			
			con = ds.getConnection(); // 커넥션풀 공간에서 DB와 미리 연결을 맺은 connection 얻기
			
			// 1. 부모 글의 글번호를 이용해 답변을 다는 부모글의 b_group열값과 b_level열값 조회
			sql = "SELECT b_group, b_level FROM board WHERE b_idx=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(super_b_idx));
			rs = pstmt.executeQuery();
			
			int b_group = 0;
			int b_level = 0;
			
			if(rs.next()) {
				b_group = rs.getInt("b_group"); // 추가할 답변글의 부모글의 조회된 b_group값
				b_level = rs.getInt("b_level"); // 추가할 답변들의 부모글의 조회된 b_level값
			}
			
			// 규칙1. 답변글을 추가하는 주 글(부모글)의 b_group 열의 값보다 큰값이 저장된
			//		  다른 주글의 b_group열의 값을 1증가시킨다.
			sql = "UPDATE board SET b_group = b_group + 1 WHERE b_group > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_group);
			pstmt.executeUpdate();
			
			sql = "insert into board(b_idx, b_id, b_pw, b_name,"
				  	  + " b_email, b_title, b_content, b_group, b_level, b_date, b_cnt )"
					  + " values(border_b_idx.nextVal, ?,?,?,?,?,?,?,?,sysdate,0)";	
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reply_id); // 답변글 작성자 로그인한 아이디
			pstmt.setString(2, reply_pass); // 작성한 답변글 비밀번호
			pstmt.setString(3, reply_name); // 답변글 작성자 명
			pstmt.setString(4, reply_email); // 답변글 작성자 이메일 주소
			pstmt.setString(5, reply_title); // 작성한 답변글 제목
			pstmt.setString(6, reply_content); // 작성한 답변글 내용

			// 규칙2. 추가(INSERT)하는 답변글은 주 글(부모글)의  b_group열 값을 가져와 +1을 해서 insert 한다.
			pstmt.setInt(7, b_group + 1);
			
			// 규칙3. 추가(INSERT)하는 답변글은 주 글(부모글)의  b_level열 값을 가져와 +1을 해서 insert 한다.
			pstmt.setInt(8, b_level + 1);
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("BoardDAO의 replyInsertBoard 메소드에서 오류: " + e);
			e.printStackTrace();
		} finally {
			// 자원해제
			closeResource();
		}
		
	}
	
} // BoardDAO 클래스

















