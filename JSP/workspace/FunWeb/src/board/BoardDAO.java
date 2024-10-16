package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// DAO 역할(DB 연결후 DB 작업하는 클래스)
public class BoardDAO implements IBoardDAO {
	
	// 데이터베이스 작업관련 객체들을 저장할 변수들
	DataSource ds; // 커넥션 풀 역할을하느 DataSource 객체의 주소를 저장할 변수
		
	Connection con; // 커넥션 풀에 미리 만들어 놓고 DB와의 접속이 필요하면 
					// 빌려와 사용할 DB 접속정보를 지니고 있는 Connection 객체 주소를 저장할 변수
		
	PreparedStatement pstmt; // 생성한 SQL문을 DB의 테이블에 전송해서 실행할 역할을 하는
							 // PreparedStatement 실행 객체의 주소를 저장할 변수

	ResultSet rs; // DB의 테이블에 저장된 행동을 조회한 전체 데이터들을 임시로 얻어 저장할
				  // ResultSet 객체의 변수
	
	// 커넥션풀(DataSource)얻는 기능의 생성자
	public BoardDAO() {
		try {
			// 1. InitialContext 객체 생성
			//   생성하는 이유는 자바의 네이밍 서비스(JNDI)에서 이름과 실제 객체를 연결해주는 개념이
			//   Context 이며, InitialContext 객체는 네이밍 서비스를 이용하기 위한 시작점이다.
			Context initCtx = new InitialContext(); 
					
			// 2. "java:comp/env"라는 주소를 전달하여 Context 객체를 얻었다.
			//    "java:comp/env" 주소는 현재 웹 애플리케이션의 루트 디렉터리라고 생각하면된다.
			//    즉! 현재 웹 애플리케이션이 사용할 수 있는 모든 자원은
			//    "java:comp/env"아래에 위치한다.(<Context></Context/>이 위치를 말한다.)
			Context ctx = (Context)initCtx.lookup("java:comp/env");
					
			// 3. "java:comp/env 경로 아래에 위치한
			//    "jdbc/jspbeginner" Resource 태그의 DataSource 커넥션 풀을 얻는다.
			ds = (DataSource)ctx.lookup("jdbc/jspbeginner");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} // 생성자
	
	// DB 연결 후 작업하는 객체들 모두 사용 후 톰캣 서버 메모리에 올라가 있는 객체들 자원 해제
	public void freeResource() {
		try {
			
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
			if(rs != null) rs.close(); // 커넥션 풀로 사용한 Connection 반환
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public Vector getBoardList(String keyField, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertBoard(BoardBean boardBean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoard(BoardBean boardBean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBoard(int num, String passwd, String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replyBoard(BoardBean boardBean) {
		// TODO Auto-generated method stub
		
	}
	
}
