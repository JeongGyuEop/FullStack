package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// jspbeginner 데이터베이스 내부의 member 테이블과 연결해서 DB 작업할 클래스

public class MemberDAO implements IMemberDAO {
	
	// 데이터베이스 작업관련 객체들을 저장할 변수들
	DataSource ds; // 커넥션 풀 역할을하느 DataSource 객체의 주소를 저장할 변수
	
	Connection con; // 커넥션 풀에 미리 만들어 놓고 DB와의 접속이 필요하면 
					// 빌려와 사용할 DB 접속정보를 지니고 있는 Connection 객체 주소를 저장할 변수
	
	PreparedStatement pstmt; // 생성한 SQL문을 DB의 테이블에 전송해서 실행할 역할을 하는
							 // PreparedStatement 실행 객체의 주소를 저장할 변수

	ResultSet rs; // DB의 테이블에 저장된 행동을 조회한 전체 데이터들을 임시로 얻어 저장할
				  // ResultSet 객체의 변수
	
	// 커넥션풀(DataSource) 및 커넥션(Connection)객체를 얻어
	// 커넥션(Connection) 객체 자체를 반환하는 메소드
	private Connection getConnection() throws Exception {
		
		// 1. InitialContext 객체 생성
		//   생성하는 이유는 자바의 네이밍 서비스(JNDI)에서 이름과 실제 객체를 연결해주는 개념이
		//   Context 이며, InitialContext 객체는 네이밍 서비스를 이용하기 위한 시작점이다.
		Context initCtx = new InitialContext(); 
		
		// 2. "java:comp/env"라는 주소를 전달하여 Context 객체를 얻었다.
		//    "java:comp/env" 주소는 현재 웹 애플리케이션의 루트 디렉터리라고 생각하면된다.
		//    즉! 현재 웹 애플리케이션이 사용할 수 이쓴ㄴ 모든 자원은
		//    "java:comp/env"아래에 위치한다.(<Context></Context/>이 위치를 말한다.)
		Context ctx = (Context)initCtx.lookup("java:comp/env");
		
		// 3. "java:comp/env 경로 아래에 위치한
		//    "jdbc/jspbeginner" Resource 태그의 DataSource 커넥션 풀을 얻는다.
		ds = (DataSource)ctx.lookup("jdbc/jspbeginner");
		
		// 4. 마지막으로 커넥션풀(DataSource) 객체 메모리에 저장된
		//     Connection 객체를 반환받아 사용
		con = ds.getConnection();
		
		return con;
	}
	

	// 새 회원 한 사람의 정보를 member 테이블에 insert 추가시키는 메소드
	@Override
	public int insertMember(MemberBean memberbean) {
		int result = 0;
		try {
			// 1. DB의 테이블과의 연결
			//    커넥션 풀에서 커넥션 가져오기
			con = getConnection();
				
			// 2. SQL(INSERT) 만들기
			//    -> 가입하기 위해 입력한 값을 테이블에 추가할 INSERT 문장
			// 3. PreparedStatement 실행 객체 얻기
			pstmt = con.prepareStatement("INSERT INTO member(id, passwd, name, reg_date, email, address, tel, mtel)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			// 3.1. ? 대신 들어갈 값 설정
			pstmt.setString(1, memberbean.getId());
			pstmt.setString(2, memberbean.getPasswd());
			pstmt.setString(3, memberbean.getName());
			pstmt.setTimestamp(4, memberbean.getReg_date());
			pstmt.setString(5, memberbean.getEmail());
			pstmt.setString(6, memberbean.getAddress());
			pstmt.setString(7, memberbean.getTel());
			pstmt.setString(8, memberbean.getMtel());
			
			// 5. insert 문장 DB에 테이블에 전송해서 실행한다.
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("MemberDAO의 insertMember 메소드 내부에서 SQL문 실행 오류: " + e );
		} finally {
			// 자원 해제
			if(rs != null) try { rs.close(); } catch(SQLException e) { e.printStackTrace(); }
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) { e.printStackTrace(); }
			if(con != null) try { con.close(); } catch(SQLException e) { e.printStackTrace(); }
		}
		
		return result;
		
	}

	
	// 회원가입을 위해 입력한 아이디를 매개변수로 받아
	// DB에 member 테이블에 저장된 아이디와 비교해서
	// 있으면 1을 반환
	// 없으면 0을 반환
	@Override
	public int idCheck(String id) {
		int check = 0;

		String sql = "";
				
		try {
			// 1. DB의 테이블과의 연결
			//    커넥션 풀에서 커넥션 가져오기
			con = getConnection();
			
			// 2. SQL(SELECT) 만들기
			//    -> 입력한 아이디에 해당되는 레코드 조회
			sql = "SELECT * FROM member WHERE id=?";
			
			// 3. PreparedStatement 실행 객체 얻기
			pstmt = con.prepareStatement(sql);
			// 3.1. ? 대신 들어갈 값 설정
			pstmt.setString(1, id);
			
			// 4. 조회한 결과 하나의 행을 표형식으로 ResultSet에 담아 얻기 
			rs = pstmt.executeQuery();
			
			// 5. ResultSet에 조회된 행이 저장되어 있으면? check=1로 저장
			if(rs.next()) {
				check = 1; // 아이디 중복
				
			// ResultSet에 조회된 행이 저장되어 있지 않으면? check=0로 저장
			} else {
				check = 0; // 아이디 중복 아님
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원 해제
			if(rs != null) try { rs.close(); } catch(SQLException e) { e.printStackTrace(); }
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) { e.printStackTrace(); }
			if(con != null) try { con.close(); } catch(SQLException e) { e.printStackTrace(); }
			
		}
		
		return check;
	}

	// 로그인 처리 시 사용하는 메소드
	
	@Override
	public int userCheck(String id, String passwd) {
		int check = -1;
		
		String sql = "";
		
		try {
			
			// 1. DB와 연결
			con = getConnection();
			
			// 2. 입력한 아이디에 관한 회원 한 사람의 레코드 정보를 모두 조회
			sql = "SELECT * FROM member WHERE id=?";
			
			// 3. PreparedStatement 실행 객체 얻기
			pstmt = con.prepareStatement(sql);
			
			// 3.1 ? 설정
			pstmt.setString(1, id);
			
			// 4. 
			rs = pstmt.executeQuery();
			
			// 5.
			if(rs.next()) {
				if(passwd.equals(rs.getString("passwd"))) {
					check = 1;
				} else  {
					check = 0;
				}
			} else {
				check = -1;
			}
			
		} catch(Exception e) {
			System.out.println("MemberDAO의 userCheck 메소드 내부에서 SQL문 실행 오류: " + e );
		} finally {
			// 자원 해제
			if(rs != null) try { rs.close(); } catch(SQLException e) { e.printStackTrace(); }
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) { e.printStackTrace(); }
			if(con != null) try { con.close(); } catch(SQLException e) { e.printStackTrace(); }
		}
		
		return check;
	}
	
}
