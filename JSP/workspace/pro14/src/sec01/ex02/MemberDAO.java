package sec01.ex02;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/*
  오라클 DBMS의 t_member테이블과 연결하여 
 데이터베이스 작업(SELECT, INSERT, DELETE, UPDATE등)
 하는 클래스 
*/
public class MemberDAO {

	//위 4가지 연결 값을 이용해서 오라클 DB와 접속한 정보를 지니고 있는
	//T4CConnection객체를 저장할 참조변수 선언
	private Connection con;
	
	//DB와 연결 후 우리 개발자가 만든 SQL문장을
	//오라클 DB의 테이블에 전송하여 실행할 역할을 하는 
	//OraclePreparedStatementWrapper실행객체의 주소를 저장할 참조변수 선언

	//	private Statement stmt;
	private PreparedStatement pstmt;
	
		
	//SELECT 조회문을 실행한 조회 결과 레코드들을 테이블형식으로
	//그대로 가져와 임시로 저장해 놓은 OracleResultSetImpl객체 메모리의 주소를 저장할 참조변수 선언
	private ResultSet rs;
	
	//DataSource커넥션 풀 객체 주소를 저장할 변수 
	private DataSource dataSource;
	
	
	//MemberDAO클래스의 기본생성자 
	//역할 : new MemberDAO(); 객체 생성시 호출되는 생성자로!
	//     생성자 내부에서 커넥션풀 역할을 하는 DataSource객체를 얻는 작업을 하게 됩니다.
	public MemberDAO() {
	 
		try {
			//1. JNDI 네이밍 서비스를 시작하고, 톰캣 서버에 등록된 Resource자원에 
			//   접근 할수 있는 환경을 설정하는 객체 생성 (DataSource자원을 얻을 환경 준비)
			// InitialContext객체를 생성하면, java애플리케이션은 해당 객체를 통해
			// 톰캣서버내부에 등록된 리소스를 탐색할수 있습니다.
			Context ctx = new InitialContext();
			    
			     
			//2.lookup메소드는 특정 이름에 대응하는 객체를 찾는 역할을 합니다.
			//  즉, ctx.lookup("java:/comp/env")는 
			//   "java:/comp/env"경로는 JNDI에서 표준적으로 사용되는 경로로
			//   주로 애플리케이션 환경 설정(환경변수, 데이터베이스 연결정보 등)에 접근하는 기본경로 입니다
			//   이 기본경로 아래에 커넥션풀(DataSource) 등의 리소스 이름이 설정됩니다. 
			Context envContext  =  (Context)ctx.lookup("java:/comp/env");
			
			//3.그런후 다시 톰캣서버는 context.xml에 설정한
			//  <Resource name="jdbc/oracle" ...../> 태그의 
			//   name속성값 "jdbc/oracle"(JNDI 기법을 사용하기위한 key)를 이용해
			//   톰캣 서버가 DB와 미리 연결을 맺은 Connection객체들의 보관 하고 있는
			//   DataSource커넥션풀 객체를 찾아 반환해 줍니다.
			dataSource = (DataSource)envContext.lookup("jdbc/oracle");
			
		}catch(Exception e) {
			System.out.println("DataSource커넥션풀 객체 얻기 실패 : " + e);
		}	
	}
	
	
	
	//DB작업 관련 객체 메모리들 사용이 끝난 후  자원 해제하는 기능의 메소드
	public void ResourceClose() {
		try {
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();
			if(con != null) con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// 오라클 DBMS서버 내부의 XE데이터베이스 내부에 만들어 놓은
	// t_member테이블의 모든 회원정보들을 한번에 조회 해서 제공하는 메소드 
	public ArrayList listMembers() {
		
		//t_member테이블에 저장된 모든 회원 레코드정보들을 조회해서 가져와서
		//가변길이 배열의 각 index위치에 임시로 저장할 배열공간인? 
		//ArrayList배열 생성
		ArrayList list = new ArrayList();
		
		try {
            //DataSource(커넥션풀)에서
			//미리 DB와 연결된 Connection객체 빌려오기 
			//요약 : DB와 DAO의 연결
			con = dataSource.getConnection();
			
			//순서5. Query(SQL문) 작성하기
			//t_member테이블에 저장된 모든 회원 레코드를 조회하는 SELECT문 작성
			String query = "SELECT * FROM T_MEMBER";
			
			//커넥션 객체의 메소드 호출시 미리 준비된 SELECT 전체 문장을 인자로 전달하면
			//전달한  전체 SELECT문장을 로딩한 PreparedStatement실행 객체 반환
			pstmt = con.prepareStatement(query);
					
			//순서6. Query(SQL문)을 DB의 t_member테이블에 전송하여 실행!(조회)
			//select * from t_member SQL문을 이용하여 
			//조회 후  조회한 결과 데이터들을 OracleResultSetImpl객체 메모리에 저장후 반환받습니다
			//단! 조회된 화면의 커서(화살표)위치는 
			//가장 처음에는 조회된 테이블의 제목열 행을 가리키고 있다.
			rs = pstmt.executeQuery();//<- OracleResultSetImpl객체 반환
			
			//순서7. 조회된 회원 레코드들이 ResultSet임시 객체 메모리에 표형태로
			//저장되어 있으면 계속 반복해서 
			//회원 레코드(행)단위의 조회된 열 값들을 차례로 얻어 
			//MemberVO객체를 생성하여 각 인스턴스변수에 저장시킵니다.
			//마지막으로 MemberVO객체들을 ArrayList가변길이배열에 반복해서 추가해서 저장시킴
			while( rs.next() ) {
				//회원 레코드(행)단위의 조회된 열 값들을 차례로 얻어 변수에 저장
				String id = rs.getString("ID");//"hong"
				String pwd = rs.getString("PWD");//"1212"
				String name = rs.getString("NAME");//"홍길동"
				String email = rs.getString("EMAIL");//"hong@gmail.com"
				Date joinDate = rs.getDate("JOINDATE");//24/10/10
				
				//MemberVO객체를 생성 하여 각 인스턴스변수에 저장시킵니다.
				MemberBean vo = new MemberBean();
						   vo.setId(id);
						   vo.setPwd(pwd);
						   vo.setName(name);
						   vo.setEmail(email);
						   vo.setJoinDate(joinDate);
				
				//MemberBean객체들을 ArrayList배열에 반복해서 추가 시킴
				list.add(vo);		 	
			}//while반복문 끝
			
		//ArrayList가변길이 배열 모습
		//[ MemberBean,  MemberBean,  MemberBean ]
		//    0          1            2       index
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//순서 9. DB작업 관련 객체 메모리를 자원해제
			ResourceClose();
		}
		
		return list; //ArrayList배열 자체를 member.jsp으로 반환(리턴)
		
	}//listMembers메소드 닫기 
	
	
	
	/*
	  memberForm.html에서 입력한 가입할 회원내용들을
	  MemberBean객체를 생성해서 각 인스턴스변수에 저장한뒤~~~~~~~~
	  addMember메소드 호출시~~ 매개변수 vo로 전달받아 INSERT문장을 만들고
	   만든 INSERT문장을 DB의 t_member테이블에 전송해서 새회원을 추가시키는 기능의 메소드
	*/
	public int addMember(MemberBean vo) {
		
		//회원 추가에 성공하면 1을 저장하고 실패하면 0을 저장할 result변수 선언
		int result = 0;
		
		try {
			//순서1. 커넥션풀(DataSource)에서 
			//      미리 DB와 연결해 놓은 Connection객체 빌려오기
			con = dataSource.getConnection(); //DB와 연결
			
			//2. t_member테이블에 INSERT할 값! 즉! 우리가 입력한 회원가입할 정보들은
			//	 MemberVO객체의 각 인스턴스변수에 저장되어 있으므로
			//   MemberVO객체의 getter메소드들을 호출해서 각 인스턴스변수에 저장된 입력값을 리턴받자.
			String id = vo.getId();//입력한아이디
			String pwd = vo.getPwd();//입력한 비밀번호를 MemberVO의 pwd변수로부터 얻습니다
			String name = vo.getName();
			String email = vo.getEmail();
			
			//3-1. insert SQL문장 만들기 
			//     version1. Statement실행객체를 사용할떄 만드는 SQL
		//	String query = "insert into t_member(id, pwd, name, email)" +
		//			       "values( '"+id+"','"+pwd+"','"+name+"','"+email+"')";
			
			//3-2. insert SQL문장 만들기
			//     version2. PreparedStatement실행객체 사용할때 만드는 SQL
			String query = "insert into t_member(id, pwd, name, email)" +
						   				 "values(?,    ?,    ?,    ? )";
			
			//4. query변수에 저장된 ? 들을 포함한 전체 insert문장을 미리 로드한
			//   OraclePreparedStatementWrapper실행객체 얻기
			pstmt = con.prepareStatement(query);
			
			//4.1 OraclePreparedStatementWrapper실행객체에 위 입력된 가입할 값들을
	     	//    ? 기호 대신 설정 합니다.
	     	//요약 : ?값 설정
			pstmt.setString(1, id);//첫번째 ? 값을 id변수값으로 설정
			pstmt.setString(2, pwd);//두번쨰? 값을 pwd변수값으로 설정
			pstmt.setString(3, name);//세번쨰?대신 들어갈 값을 name변수값으로 설정
			pstmt.setString(4, email);//네번째? 대신 들어갈 값을 email변수값으로 설정			
	     	
	     	/*
	     	참고. 회원가입(회원추가)날짜 정보가 저장되는 joinDate열의 값은 t_member테이블 만들때
	     	     default설정을 통해 sysdate로 값을 적어 놓았기때문에
	     	     따로 insert구문에 작성하지 않으면 현재 컴퓨터의 날짜정보가 sysdate예약어에 의해
	     	     자동으로 같이 insert됩니다.
	     	*/
			//5. OraclePreparedStatementWrapper실행객체에 설정된 전체 insert문장을
	     	//   DB의 t_member테이블에 전송해 실행!
	     	result = pstmt.executeUpdate(); //insert에 성공하면 
				     						//insert에 성공한 레코드 갯수 1을 반환
				     						//insert에 실패하면
				     						//0을 반환 
						
		} catch (Exception e) {
			
			System.out.println("MemberDAO의 addMember메소드 내부에서 "
								+ "insert문 실행 오류:" + e);		
		} finally {
			//6. 자원해제 
			ResourceClose();
		}
				
		//addMember메소드를 호출한 곳(member.jsp)으로  1 또는 0을 반환
		return result;
		
	}//addMember메소드 끝
	
	//삭제 <a>링크를 클릭했을때.. 서블릿으로 전송한 삭제할 회원 아이디를 
	//다시~ delMember메소드의 매개변수로 전달받아서
	//DELETE문장을 완성후 ~ DB의 t_member테이블에 저장된 회원 한사람의 정보를 
	//삭제시키는 기능의 메소드 
	public void delMember(String id) {
		try {
			//순서1.커넥션풀(DataSource)에서 
			//     미리 DB와 연결을 맺은 클라이언트의 접속정보를 가지고 있는
			//     Connection객체 하나 빌려와 DB와 MemberDAO와 연결
			con = dataSource.getConnection();
			
			//순서2. 위 String id 매개변수로 전달받은
			//삭제할 회원 아이디와 일치하는 회원레코드(행) 삭제하는 DELETE문 작성
			String query = "delete from t_member where id=?";
			
			//순서3.query변수에 저장된 전체 DELETE문자열을 미리 로드한
			//     OraclePreparedStatementWrapper실행 객체 얻기
			pstmt = con.prepareStatement(query);
			
			//순서3-1. OraclePreparedStatementWrapper실행객체에 
			// 미리 로드된 delete문장 중에 ? 기호 대신 매개변수로 전달받은
			// 삭제할 회원 아이디로 설정
			//요약  : ?  설정
			pstmt.setString(1, id);
			
			//순서4. OraclePreparedStatementWrapper실행 객체에 설정된
			// delete from t_member where id=hong 전체 문장을
			// DB의 t_member테이블에 전송해 실행!
			pstmt.executeUpdate(); //DELETE에 성공하면 성공한 레코드(행)갯수 1반환
								   //DELETE에 실패하면 0을 반환 
			
		}catch(Exception e) {
			System.out.println("MemberDAO의 delMember메소드 내부에서 "
							  + "DELTE SQL문 실행 오류 : " + e);
		}finally {
			//ResultSet, PreparedStatement, Connection  자원해제 
			ResourceClose();
		}
	}
	
	
		
}//MemberDAO









