package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Vo.FileBoardVo;



//MVC중에서 M을 얻기 위한 클래스 

//DB와 연결하여 비즈니스로직 처리하는 클래스 
//사원 
public class FileBoardDAO {
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	//컨넥션풀 얻는 생성자
	public FileBoardDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:/comp/env/jdbc/oracle");
			
		}catch(Exception e) {
			System.out.println("커넥션풀 얻기 실패 : " + e.toString());
		}
	}
	//자원해제(Connection, PreparedStatment, ResultSet)기능의 메소드 
	private void closeResource() {
		try {
				if(con != null) { con.close(); }
				if(pstmt != null) { pstmt.close(); }
				if(rs != null) { rs.close(); }
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//현재 DB의 Fileboard테이블에 저장된 모든 게시글들 조회
	public ArrayList boardListAll() {
		
		String sql = null;
		
		ArrayList list = new ArrayList();
		
		try {
			con = ds.getConnection();//DB연결
			sql = "select * from Fileboard order by b_group asc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//조회된 ResultSet의 정보를 레코드 단위로 얻어서 
			//BoardVo객체에 레코드 정보를 반복해서 저장하고 
			//BoardVo객체들을 ArrayList배열에 반복해서  추가
			while(rs.next()) {
				FileBoardVo vo = new FileBoardVo(rs.getInt("b_idx"), 
												 rs.getInt("b_group"), 
												 rs.getInt("b_level"), 
												 rs.getInt("b_cnt"), 
												 rs.getString("b_id"), 
												 rs.getString("b_pw"), 
												 rs.getString("b_name"), 
												 rs.getString("b_email"), 
												 rs.getString("b_title"), 
												 rs.getString("b_content"), 
												 rs.getDate("b_date"),
												 rs.getString("ofile"),
												 rs.getString("sfile"),
												 rs.getInt("downcount")
						
												 );
				list.add(vo);			
			}			
		} catch (Exception e) {
			System.out.println("FileBoardDAO의 boardListAll메소드에서 오류 ");
			e.printStackTrace();
		} finally {
			closeResource();
		}	
		return list;
	}

	//----------------
	//선택한 option의 value값(검색기준열의값)과 입력한 검색어 단어가 포함된 글목록 조회
	public ArrayList boardList(String key, String word) {
		
		String sql = null;
		
		ArrayList list = new ArrayList();
		
		//검색어를 입력했다면?
		if(!word.equals("")) {
			
			//검색기준열의 값  제목+내용 선택했다면?
			if(key.equals("titleContent")) {
				
				sql = "select * from fileboard "
					+ "where b_title like  '%"+word+"%' "
					+ "OR b_content like '%"+word+"%' "
					+ "order by b_group asc";
				
			}else {//검색기준열의 값 작성자 선택했다면?
				
				sql = "select * from fileboard "
				    + " where b_name like '%"+word+"%' "		
				    + "order by b_group asc";
			} 
					
		}else {//검색어를 입력하지 않았다면?
			//모든 글의 열목록 조회
			sql = "select * from fileboard "
				+ "order by b_group asc";
			
		}
		
		try {
			con = ds.getConnection();//DB연결
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//조회된 ResultSet의 정보를 레코드 단위로 얻어서 
			//BoardVo객체에 레코드 정보를 반복해서 저장하고 
			//BoardVo객체들을 ArrayList배열에 반복해서  추가
			while(rs.next()) {
				FileBoardVo vo = new FileBoardVo(rs.getInt("b_idx"), 
						 						 rs.getInt("b_group"), 
						 						 rs.getInt("b_level"), 
												 rs.getInt("b_cnt"), 
												 rs.getString("b_id"), 
												 rs.getString("b_pw"), 
												 rs.getString("b_name"), 
												 rs.getString("b_email"), 
												 rs.getString("b_title"), 
												 rs.getString("b_content"), 
												 rs.getDate("b_date"),
												 rs.getString("ofile"),
												 rs.getString("sfile"),
												 rs.getInt("downcount")

						 						);
						
						
				list.add(vo);			
			}			
			
			
		} catch (Exception e) {
			System.out.println("FileBoardDAO의 boardList메소드 ");
			e.printStackTrace();
		} finally {
			closeResource();
		}
		
		return list; // FileBoardService부장에게 리턴(보고)	
	}

	//----------------
	// fileboard테이블에 저장된 최신 글번호 조회 후 새글의 글번호를 만들어서 반환하는 메소드
	private int getNewArticleNO() {
		
		String sql = null;
		
		try {
			con = ds.getConnection();//DB연결
			sql = "select max(b_idx) from fileBoard";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return (rs.getInt(1) + 1); // insert 할 새 글번호 만들어서 반환
			}
					
		} catch (Exception e) {
			System.out.println("FileBoardDAO의 boardListAll메소드에서 오류 ");
			e.printStackTrace();
		} finally {
			closeResource();
		}	
		
		return 0;
	}
	
	//----------------
	//입력한 새글 정보(첨부파일명 포함)를 DB의 fileboard테이블에 추가insert 시키는 기능의 메소드
	public int insertBoard(FileBoardVo vo) {
		
		int articleNO = getNewArticleNO(); // FileBoard 테이블에 저장된 
										   // 가장 큰 글번호 하나 조회해서 얻기
		
		String sql = null;
		
		try {
			  con = ds.getConnection(); //DB연결
			  
			  //주글 insert 규칙2. 두번째 글부터 추가되는 글들의 pos(b_group)를 1증가 시킨다.
			  sql = "update fileboard set b_group = b_group + 1";
			  pstmt = con.prepareStatement(sql);
			  pstmt.executeUpdate();
			  
			  //주글 insert 규칙3. 처음 입력되는 주글의 pos(b_group)과 , depth(b_level)은  0 0  으로  insert시킨다
			  sql = "insert into fileboard(b_idx, b_id, b_pw, b_name,"
			  	  + " b_email, b_title, b_content, b_group, b_level, b_date, b_cnt, sfile, downcount )"
				  + " values(?,?,?,?,?,?,?,0,0,sysdate,0,?,0)";	 
			  
			  pstmt = con.prepareStatement(sql);
			  pstmt.setInt(1, articleNO);
			  pstmt.setString(2, vo.getB_id());
			  pstmt.setString(3, vo.getB_pw());
			  pstmt.setString(4, vo.getB_name());
			  pstmt.setString(5, vo.getB_email());
			  pstmt.setString(6, vo.getB_title());
			  pstmt.setString(7, vo.getB_content());
			  pstmt.setString(8, vo.getSfile());
			  
			  pstmt.executeUpdate();//insert 성공하면 1 반환 실패하면 0반환
			  
		}catch(Exception e) {
			System.out.println("FileBoardDAO의 insertBoard메소드");
			e.printStackTrace();
		}finally {
			closeResource();
		}
		
		return articleNO; // 글번호 반환(리턴) BoardService에게 
	}

	//----------------
	// 글제목을 눌렸을 때~
	// 글 조회 수를 1 증가시키고
	// 글번호를 이용해 글하나 조회해서 제공하자
	public FileBoardVo boardRead(String b_idx) {
		
		FileBoardVo vo = null;
		String sql = null;
		
		try {
			con = ds.getConnection();
			// 글 제목 눌렸을 때 글 조회수 1 증가 시키기
			sql = "update fileboard set b_cnt = b_cnt + 1 where b_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  Integer.parseInt(b_idx));
			pstmt.executeUpdate();
			
			// 글제목을 클릭한 글의 글번호를 매개변수로 받아 글 하나 조회
			sql = "select * from fileboard where b_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  Integer.parseInt(b_idx));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				//ResultSet에서 조회된 레코드의 모든 열값을 얻어
				//BoardVo객체 생성후 각변수에 저장
				vo = new FileBoardVo(rs.getInt("b_idx"), 
								 rs.getInt("b_group"), 
								 rs.getInt("b_level"), 
								 rs.getInt("b_cnt"), 
								 rs.getString("b_id"), 
								 rs.getString("b_pw"), 
								 rs.getString("b_name"), 
								 rs.getString("b_email"), 
								 rs.getString("b_title"), 
								 rs.getString("b_content"), 
								 rs.getDate("b_date"),
								 rs.getString("ofile"),
								 rs.getString("sfile"),
								 rs.getInt("downcount"));
				
			}
			
		} catch (Exception e) {
			System.out.println("FileBoardDAO의 boardRead메소드");
			e.printStackTrace();
		} finally {
			closeResource();
		}
		
		return vo; // FileBoardVo 객체 반환
		
	}

	//----------------
	//글을 수정,삭제하기위한 버튼들을 활성화해서 보여주기 위해
	//입력한 글의 비밀번호가 DB에 있는지 체크하는 기능의 메소드 
	public boolean passCheck(String b_idx_,  String password) {
		
		boolean result = false;
		
		try {
			 con = ds.getConnection();//DB연결
			 
			 //board테이블에 b_idx글번호열값이 매개변수b_idx_로 전달받은 값이고
			 //           b_pw글 비밀번호열값이 매개변수 password로 전달받은 값인
			 //행의 모든 열값을 조회
			 String sql = "select * from fileboard "
					    +" where b_idx=? and b_pw=? "
					    +" order by b_idx desc";
			 pstmt = con.prepareStatement(sql);
			 //?설정
			 pstmt.setString(1, b_idx_);//전달받은 글 번호 
			 pstmt.setString(2, password);//입력해서 전달받은 글의 비밀번호
			 
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {//입력한 비밀번호로 한줄이 조회되니 비밀번호가 저장되어 있음
				 result = true;
			 }else {
				 result = false;
			 } 
		} catch (Exception e) {
			System.out.println("FileBoardDAO의 passCheck");
			e.printStackTrace();
		} finally {
			closeResource();
		}
		return result;
		
	}

	//----------------
	//수정시 입력한 글 정보를 DB에 UPDATE(열의 값을 수정)하는 메소드 
	public int updateBoard(String idx_, 
						   String email_, 
						   String title_, 
						   String content_) {
		int result = 0;
		
		try {
			//DB연결
			con = ds.getConnection();
			//Board테이블의 b_idx열의 값이 매개변수로 전달받은 수정할 글번호이면
			//글번호가 저장된 레코드의 b_email열의 값, b_title열의 값, b_content열의 값을
			//우리가 수정시 입력한 값들로 변경해라(UPDATE)
			String sql = "update fileboard set "
					   + "b_email=?, b_title=?, b_content=? "
					   + "where b_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email_);
			pstmt.setString(2, title_);
			pstmt.setString(3, content_);
			pstmt.setInt(4, Integer.parseInt(idx_));
				
			result = pstmt.executeUpdate();//UPDATE성공하면 성공한 레코드 갯수 1반환
								 			//UPDAte실패하면 실패했으므로 레코드 갯수 0 반환

			
		} catch (Exception e) {
			System.out.println("FileBoardDAO의 updateBoard메소드");
			e.printStackTrace();
		} finally {
			//커넥션 객체 커넥션풀로 반납 
			//Resultset객체 사용후 제거
			//PreparedStatement객체 사용후 제거 
			closeResource();
		}
		
		return result; //1또는 0 반환 
	}

	//----------------
	//삭제할 글번호를 매개변수로 받아 글(레코드)삭제후 
	//삭제에 성공하면 "삭제성공" 반환 실패하면 "삭제실패" 반환 하는 메소드
	public String deleteBoard(String delete_idx) {
		
		//"삭제성공" 또는 "삭제실패" 메세지 저장할 변수
		String result = null;
		
		try {
			con = ds.getConnection();
			//Board테이블의 b_idx열(글번호가 저장되는 열)의 값이 매개변수로 전달받은 글번호와 같으면?
			//글번호가 저장된 행(글 하나의 정보 레코드)삭제 
			String sql = "DELETE FROM fileboard";
				   sql += " WHERE B_IDX=?";
							/*
							DELETE FROM 테이블 명
							WHERE 조건
							*/
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(delete_idx));
			
			int val = pstmt.executeUpdate();
			
			if(val == 1)  result = "삭제 성공";
			else result = "삭제 실패";
			
		} catch (Exception e) {
			System.out.println("FileBoardDAO의 deleteBoard메소드");
			e.printStackTrace();
		} finally {
			closeResource();
		}
		return result;
	}

	//----------------
	//작성한 답변들을 DB의 Board테이블에 INSERT
	//규칙1. 답변글을 추가하는 주글(부모글)의
	//      b_group열의 값보다 큰 값이 저장된 다른주글의 b_grop열의 값을 1증가시킨다
	//규칙2. 추가(INSERT)하는 답변글은 주글(부모들)의 b_grop열값을 가져와 +1을 해서 insert한다
	//규칙3. 추가(INSERT)하는 답변글은 주글(부모들)의 b_level열값을 가져와 +1을 해서 insert한다
	public void replyInsertBoard(String super_b_idx, 
								 String reply_id, 
								 String reply_name, String reply_email,
								 String reply_title, String reply_content, 
								 String reply_pass) {
		String sql = null;
		
		try {
			con = ds.getConnection();//커넥션풀 공간에서 DB와 미리연결을 맺은 Connection얻기
			
			//1. 부모글의 글번호를 이용해 답변을 다는 부모글의 b_group열값과 b_level열값 조회
			sql = "SELECT b_group, b_level FROM fileboard "
				+ "WHERE b_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(super_b_idx));
			rs = pstmt.executeQuery();
			rs.next();
			String b_group = rs.getString("b_group");//추가할 답변글의 부모글의 조회된 b_group
			String b_level = rs.getString("b_level");//추가할 답변들의 부모글의 조회된 b_level
			//2.		
			//규칙1. 답변글을 추가하는 주글(부모글)의
			//	      b_group열의 값보다 큰 값이 저장된 다른주글의 b_grop열의 값을 1증가시킨다	
			
			sql = "UPDATE fileboard SET b_group=b_group+1 "
			    + "WHERE b_group > '"+b_group+"'";
			
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			//3.	
			//답변글 DB에  INSERT			
			//규칙2. 추가(INSERT)하는 답변글은 주글(부모들)의 b_grop열값을 가져와 +1을 해서 insert한다
			//규칙3. 추가(INSERT)하는 답변글은 주글(부모들)의 b_level열값을 가져와 +1을 해서 insert한다			
			  sql = "insert into fileboard(b_idx, b_id, b_pw, b_name,"
							  	  + " b_email, b_title, b_content, b_group, b_level, b_date, b_cnt )"
								  + " values(border_b_idx.nextVal, ?,?,?,?,?,?,?,?,sysdate,0)";	
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reply_id);//답변글 작성자 로그인한 아이디
			pstmt.setString(2, reply_pass);//작성한 답변글 비밀번호
			pstmt.setString(3, reply_name);//답변글 작성자 명
			pstmt.setString(4, reply_email);//답변글 작성자 이메일 주소 
			pstmt.setString(5, reply_title);//작성한 답변글 제목
			pstmt.setString(6, reply_content);//작성한 답변글 내용
			//규칙2.
			//답변글의 b_group열의 값은 주글(부모글) b_group열값에 + 1 한 값을 INSERT
			pstmt.setInt(7, Integer.parseInt(b_group) + 1); 
			//규칙3.
			//답변글의 b_level열의 값은 주글(부모글) b_level열값에 + 1 한 값을 INSERT
			pstmt.setInt(8, Integer.parseInt(b_level) + 1);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("FileBoardDAO의 replyInsertBoard메소드");
			e.printStackTrace();
		} finally {
			closeResource();
		}
	}
	
	
	// 다운로드 시킨 글번호를 매개변수로 받아 글번호에 해당되는 글정보와 같이
	// 추가된 다운로드 횟수 1 증가
	public void downUpdateCount(String idx) {
		
		try {
			con = ds.getConnection();//커넥션풀 공간에서 DB와 미리연결을 맺은 Connection얻기
			
			String sql = "UPDATE fileboard "
					   + "SET downcount= downcount + 1 "
					   + "WHERE b_idx=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(idx));
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("FileBoardDAO의 downUpdateCount메소드");
			e.printStackTrace();
		} finally {
			closeResource();
		}
		
	}

}//BoardDAO클래스 

















