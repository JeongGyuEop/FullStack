package sec01.ex01;

import java.sql.*;
import java.util.ArrayList;

/*
 	오라클 DBMS의 t_member 테이블과 연결하여
 	데이터베이스 작업(SELECT, INSERT, DELETE, UPDATE등)하는
 	클래스
 	
 */

public class MemberDAO {
	
	// 순서 1. import java.sql.*;
	//   	   그리고 오라클 DBMS의 t_member 테이블과 연결할 4가지 연결정보를 변수에 저장
	
		// ojdbc6.jar 드라이버 파일 내부에 만들어져 있는
		// 드라이버 역할을 하는 OracleDriver.class 파일의 경로를 상수에 저장
		private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
		
		// 오라클 DBMS 서버의 IP, 데이터베이스명, 포트번호를 상수에 저장
		private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
		
		// 오라클 DBMS의 XE 데이터베이스에 접속할 아이디를 상수에 저장
		private static final String USER = "scott";
		
		// 접속할 비밀번호를 상수에 저장
		private static final String PWD = "tiger";
		
		// 위 4가지 연결 값을 이용해서 오라클 DB와 접속한 정보를 지니고 있는
		// T4CConnection 객체를 저장할 참조변수 선언
		private Connection con;
		
		// DB와 연결 후 개발자가 만든 SQL문장을
		// 오라클 DB의 테이블에 전송하여 실행할 역할을 하는
		// Statement 실행 객체의 주소를 저장할 참조변수 선언
		private Statement stmt;
		
		// SELECT 조회문을 실행한 조회 결과 레코드들을 테이블 형식으로
		// 그대로 가져와 임시로 저장해 놓은 ResultSet 객체 메모리의 주소를 저장할 참조변수 선언
		private ResultSet rs;
		
		
		
// -------------------------------------------------------------------------------------------------
		
	// 순서 2. 순서 3. 순서 4.
	private void connDB() {
		
		try {
			// 순서 2. OracleDriver.class 드라이버 프로그램을
			// 		   톰캣 서버에서 제공하는 JVM 메모리에 로딩한다.
			// Class.forName("OracleDriver.class 파일이 저장되어 있는 패키지명을 포함한
			// 				  경로를 문자열로 전달)을 이용하여
			// OracleDriver.class 클래스 자체를 JVM이 차지하고 있는 메모리 중에서
			// DriverManager 클래스의 정적 변수에 저장 시킨다.
			// 요약 : 드라이버 로딩
			Class.forName(DRIVER);
			
			// 순서 3. JVM에 로딩된 (DriverManager에 저장된) OracleDriver를 통하여
			// 		   DB와 접속하여 DB와 접속을 맺은 정보를 가지고 있는 T4CConnection 객체 얻기
			// 요약 : DAO와 DB와의 연결
			con = DriverManager.getConnection(URL, USER, PWD);
			
			// 순서 4. 개발자가 만든 SQL 문을 미리 로딩한 
			//			SQL 문을 오라클 DB의 테이블에 전송해서 실행할 역할을 하는
			//		 	Statement 객체 얻기
			// 요약 : Statement 실행 객체 얻기
			stmt = con.createStatement();
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	} // connDB 메소드 닫기
	
	
	// DB 작업 관련 객체 메모리들 사용이 끝난 후 자원 해제하는 기능의 메소드
	public void ResourceClose() {
		
		try {
			
			if(stmt != null) stmt.close();
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
			// 오라클 OracleDriver.class 를 DriverManager에 등록
			// 요약 : 드라이버 로드
			
			// T4CConnection 객체 얻기
			// 요약 : DAO와 DB의 연결
			
			// Statement 실행 객체 얻기
			connDB();
			
			// 순서 5. Query(SQL문) 작성하기
			// t_member 테이블에 저장된 모든 회원 레코드를 조회하는 SELECT 문 작성
			String query = "SELECT * FROM t_member";
			
			// 순서 6. Query(SQL 문)을 DB의 t_member 테이블에 전송하여 실행!(조회)
			//		   SELECT * FROM t_member SQL 문을 이용하여 
			// 		   조회 후 조회한 결과 데이터들을 ResultSet 객체 메모리에 저장 후 반환받습니다.
			//         단! 조회된 화면의 커서(화살표) 위치는 가장 처음에는 테이블의 제목열 행을
			//		   가리키고 있다.
			rs = stmt.executeQuery(query);
			
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
