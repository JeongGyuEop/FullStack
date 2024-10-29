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
		
			sql = "SELECT * FROM board ORDER BY b_idx DESC";
			
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
						+ "ORDER BY b_idx desc";
				
			} else { // 검색 기준 열의 값 '작성자'선택했다면?
				
				sql = "SELECT * FROM board "
						+ "where b_name like '%"+ word + "%' "
						+ "ORDER BY b_idx desc";
				
			}
			
		} else { // 검색어를 입력하지 않았다면?
			
			sql = "SELECT * FROM board ORDER BY b_idx desc";
			
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
	
} // BoardDAO 클래스

















