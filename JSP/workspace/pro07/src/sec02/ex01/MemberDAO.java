package sec02.ex01;

import java.sql.*;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/*
 	오라클 DBMS의 t_member 테이블과 연결하여
 	데이터베이스 작업(SELECT, INSERT, DELETE, UPDATE등)하는
 	클래스
 	
 */

public class MemberDAO {
	
		// 위 4가지 연결 값을 이용해서 오라클 DB와 접속한 정보를 지니고 있는
		// T4CConnection 객체를 저장할 참조변수 선언
		private Connection con;
		
		// DB와 연결 후 개발자가 만든 SQL문장을
		// 오라클 DB의 테이블에 전송하여 실행할 역할을 하는
		// OraclePreparedStatementWrapper 실행 객체의 주소를 저장할 참조변수 선언
		private PreparedStatement pstmt;
		
		// SELECT 조회문을 실행한 조회 결과 레코드들을 테이블 형식으로
		// 그대로 가져와 임시로 저장해 놓은 OracleResultSetImpl 객체 메모리의 주소를 저장할 참조변수 선언
		private ResultSet rs;
		
		// DataSource 커넥션 풀 객체 주소를 저장할 변수
		private DataSource dataSource;
		
		
		// MemberDAO 클래스의 기본생성자
		// 역할 : new MemberDAT(); 객체 생성 시 호출되는 생성자로!
		//		  생성자 내부에서 커넥션풀 역할을 하는 DataSource 객체를 얻는 작업을 하게 된다.
		public MemberDAO() {
			
			try {
				
				// 1. JNDI 네이밍 서비스를 시작하고, 톰캣 서버에 등록된 Resource 자원에 접근할
				//    수 있는 환경을 설정하는 객체 생성(DataSoutce 자원을 얻을 환경 준비)
				// 	  InitialContext 객체를 생성하면, java 애플리케이션은 해당 객체를 통해
				//    톰캣서버내부에 등록된 리소스를 탐색할 수 있다.
				Context ctx = new InitialContext();
				
				// 2. lookup 메소드는 특정 이름에 대응하는 객체를 찾는 역할을 한다.
				//    즉, ctx.lookup("java:/comp/env")는
				//    "java:/comp/env" 경로는 JNDI 에서 표준적으로 사용되는 경로로
				//    주로 애플리케이션 환경 설정(환경변수, 데이터베이스 연결정보 등)에 접근하는 기본경로 이다.
				//    이 기본 경로 아래에 커넥션풀(DataSource) 등의 리소스 이름이 설정된다.
				Context envContext = (Context)ctx.lookup("java:/comp/env");
			
				// 3. 그런 후 다시 톰캣서버는 context.xml 에 설정한
				//    <Resource name="jdbc/oracle" ...../> 태그의
				//    name 속성값 "jdbc/oracle"(JNDI 기법을 사용하기 위한 key)를 이용해
				//    톰캣 서버가 DB와 미리 연결을 맺은 Connection 객체들의 보관하고 있는
				//    DataSource 커넥션 풀 객체를 만든다ㅣ.
				dataSource = (DataSource)envContext.lookup("jdbc/oracle");
				
				
			} catch(Exception e) {
				System.out.println("DataSource 커넥션풀 객체 얻기 실패 : " + e);
			}
			
		}
		
// -------------------------------------------------------------------------------------------------
		
	
	// DB 작업 관련 객체 메모리들 사용이 끝난 후 자원 해제하는 기능의 메소드
	public void ResourceClose() {
		
		try {
			
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();
			if(con != null) con.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
		 
	// 오라클 DBMS 서버 내부의 XE 데이터베이스 내부에 만들어 놓은
	// t_member 테이블의 모든 회원 정보들을 한 번에 조회해서 제공하는 메소드
	public ArrayList listMembers() {
		// t_member 테이블에 저장된 모든 회원 레코드 정보들을 조회해서 가져와서 
		// 가변길이 배열의 각 index 위치에 임시로 저장할 배열공간인 ArrayList 배열 생성 
		ArrayList list = new ArrayList();
		
		try {
			
			// DataSource(커넥션 풀)에서 
			// 미리 DB와 연결된 Connection 객체 빌려오기
			// 요약 : DB와 DAO의 연결
			con = dataSource.getConnection();
			
			// 순서 5. Query(SQL문) 작성하기
			// t_member 테이블에 저장된 모든 회원 레코드를 조회하는 SELECT 문 작성
			String query = "SELECT * FROM t_member";
			
			// Connection 객체의 prepareStatement 메소드 호출 시 미리 준비된 SELECT 전체
			// 문장을 인자로 전달하면 전달한 전체 SELECT 문장을 로딩한 PreparedStatement
			// 실행 객체를 반환한다.
			pstmt = con.prepareStatement(query);
			
			// 순서 6. Query(SQL 문)을 DB의 t_member 테이블에 전송하여 실행!(조회)
			//		   SELECT * FROM t_member SQL 문을 이용하여 
			// 		   조회 후 조회한 결과 데이터들을 ResultSet 객체 메모리에 저장 후 반환받습니다.
			//         단! 조회된 화면의 커서(화살표) 위치는 가장 처음에는 테이블의 제목열 행을
			//		   가리키고 있다.
			rs = pstmt.executeQuery(query);
			
			// 순서 7. 조회된 회원 레코드들이 ResultSet 임시 객체 메모리에 표 형태로
			//         저장되어 있으면 계속 반복해서
			//		   회원 레코드(행) 단위의 조회된 열 값들을 차례로 얻어
			//    	   MemberVO 객체를 생성하여 각 인스턴스 변수에 저장시킨다.
			// 		   마지막으로 MemberVO 객체들을 ArrayLsit 가변길이 배열에 반복해서
			//		   추가해서 저장시킨다.
			while(rs.next()) {
				// 회원 레코드(행) 단위의 조회된 열 값들을 차례로 얻어 
				String id = rs.getString("ID"); // "hong"
				String pwd = rs.getString("PWD"); // "1212"
				String name = rs.getString("NAME"); // "홍길동"
				String email = rs.getString("EMAIL"); // "hong@gamil.com"
				Date joinDate = rs.getDate(5); // "20/10/10"
				
				// MemberVO 객체를 생성하여 각 인스턴스 변수에 저장시킨다.
				MemberVO vo = new MemberVO();
						 vo.setId(id);
						 vo.setPwd(pwd);
						 vo.setName(name);
						 vo.setEmail(email);
						 vo.setJoinDate(joinDate);
						 
				// MemberVo 객체들을 ArrayLsit 배열에 반복해서 추가 시킨다.
				list.add(vo);
				
			}
			
			// ArrayLsit 가변길이 배열 모습
			// [ MemberVO, MemverVo, MemberVO ]
			//      0          1         2         index
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// 순서 9. DB 작업 관련 객체 메모리를 자원 해제
			ResourceClose();
		}
		
		return list; // MemberVO 객체들이 저장되어 있는 ArrayList 공간을 반환한다. 
		
	}
	
}
