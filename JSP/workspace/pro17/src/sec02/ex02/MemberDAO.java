package sec02.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//MVC중에서  Model(조회한 응답할 데이터)를 만드는 역할
//-> 오라클 DBMS의 XE데이터베이스 내부에 만들어 놓은
//   t_member테이블과 연결하여 DB작업(SELECT,INSERT....)할 클래스
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
	
	//오라클 DBMS서버 내부의 XE데이터베이스 내부에 만들어 놓은 t_member테이블의 
	//모든 회원레코드를 한번에 조회 하는 기능의 메소드 
	public ArrayList listMembers() {//MemberController서블릿이 호출한 메소드 
		
		//t_member테이블에 저장된 모든 회원 정보들을 조회해서 가져와
		//저장할 임시 배열 공간 
		ArrayList list = new ArrayList();
		
		try {
			//1.MemberDAO와 DB의 t_member테이블과 연결을 맺은 접속정보를 지닌 Connection객체 얻기
			//요약 : DB연결
			con = dataSource.getConnection();
			//2. t_member테이블에 저장된 모든 회원레코드 조회하는 SELECT문장 만들어
			//   String query변수에 저장
			String query = "select * from t_member";
			
			//3. PreparedStatement객체  얻기
			pstmt = con.prepareStatement(query);
			
			//4. PreparedStatement객체의 executeQuery()메소드를 호출하여
			//   설정된 SELECT문장을 t_member에 전달해 실행 합니다
			//select * from t_member SQL문을 이용하여 조회후 
			//조회한 결과 데이터들을 ResultSet객체 메모리에 저장후 반환 받습니다.
			//단! 조회된 화면의  커서(화살표)위치는  
			//가장 처음에는  조회된 테이블의 제목열 행을 가리키고 있다.
			rs = pstmt.executeQuery();
			
			//5. 조회한 회원레코드들이 Resultset객체 메모리에 표형태로 저장되어 있으면
			//   계속 반복해서 회원레코드(행)단위의 조회된 열의 값들을 차례로 얻어
			//   MemberVO객체를 생성하여 각 인스턴스변수에 저장시킵니다.
			//   그런 후 MemberVO객체 하나하나를 ArrayList가변길이  배열에 반복해서 넣는다
			while(rs.next()) {
				//회원 레코드(행)단위의 조회된 열의 값들을 차례로 얻어 변수에 저장
				String id = rs.getString("ID");
				String pwd = rs.getString("PWD");
				String name = rs.getString("NAME");
				String email = rs.getString("EMAIL");
				Date joinDate = rs.getDate("JOINDATE");
				
				//MemberVO객체를 생성해서 위 조회된 회원 레코드의 한행 정보를 변수에 저장
				MemberVO vo = new MemberVO(id, pwd, name, email, joinDate);
				
				//MemberVO객체(조회된 회원 레코드 정보)를  ArrayList배열에 반복해서 추가
				list.add(vo);
				
			}
				
		}catch(Exception e) {
			System.out.println("listMembers메소드 내부에서 SQL문 실행 오류:" + e);
		}finally {
			//DB작업 관련 객체 메모리들 톰캣 서버 메모리에서 사용후 제거 
			ResourceClose();
		}
		return list; //MemberController서블릿(Controller)로 
					 //클라이언트의 웹브라우저로 응답할 Model(ArrayList)를 반환
	
	}//listMembers메소드 닫는 }
	
	
	//memberForm.jsp에서 입력한 가입할 회원 내용들을
	//MemberVO객체의 각변수에 저장시켰고
	//MemberController에서 이메소드 호출시 매개변수로 MemberVO객체를 전달받아
	//DB의 t_member테이블에 INSERT 시키는(새 회원 추가) 기능의 메소드 
	public void addMember(MemberVO memberVO) {
		
		try {
			//1. 커넥션풀(DataSouce)에서 미리 DB와 연결을 맺은 Connection객체 얻기 
			//요약 : DB연결
			con = dataSource.getConnection();
			
			//2. t_member테이블에 INSERT할 값! 즉! 우리가 입력해서 가입할 회원정보들을
			//   매개변수로 전달받은 MemberVO객체의 변수값으로 얻기
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			
			//3. INSERT SQL문 만들기 
			String query = "INSERT INTO T_MEMBER(id, pwd, name, email)"
					     				+ "VALUES(?,   ?,    ?,   ?)";
			
			//4.query변수에 저장된 전체 insert문자열을 미리 로드한
			//  OraclePreparedStatementWrapper실행 객체 얻기
			pstmt = con.prepareStatement(query);
			
			//4.1 OraclePreparedStatementWrapper실행객체에 
			//    위 변수에 저장된 값들을 ?기호에 대응되게 설정
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			
			
			//5. OralcePreparedStatementWrapper실행객체에  설정된 
			///  전체 insert문장을 DB의 테이블에 전송해서 실행!
			pstmt.executeUpdate();//insert,update,delete
			
		}catch(Exception e) {
			System.out.println("addMember메소드 내부에서 SQL실행 오류 : " + e);
		}finally {
			//자원해제
			ResourceClose();
		}
	}//addMember메소드 } 닫기 
	
	//매개변수로 전달받은  회원ID를 이용해 수정할 회원한사람의 정보를 조회해서
	//MemberVO객체에 저장후 반환하는 기능의 메소드 
	public MemberVO findMember(String id) {
	
		MemberVO memInfo = null;
		
		try {
			//DB와의 연결
			con = dataSource.getConnection();
			
			//매개변수 String id로 전달받은 회원 아이디열의 값에 해당하는
			//회원 레코드의 정보를 조회 하는 SELECT 문
			String query = "select * from t_member where id=?";
			
			pstmt  = con.prepareStatement(query);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			rs.next(); //커서의 위치를 조회된 행으로 내려 준다
			
			//조회한 한사람(행의정보)를 ResultSet객체에서 꺼내어
			//MemberVO객체를 생성해서 저장
			memInfo = new MemberVO(rs.getString("id"), 
					               rs.getString("pwd"), 
					               rs.getString("name"), 
					               rs.getString("email"), 
					               rs.getDate("joinDate"));								
		}catch(Exception e) {
			System.out.println("findMember메소드 내부에서 select실행 오류 :" + e);
		}finally {
			ResourceClose();//자원해제
		}
		return memInfo; //MemberController로 MemberVO객체 반환
	}
	
	//MemberController에서 호출한 메소드로
	//매개변수로 전달받은 MemberVO객체의 정보를 
	//DB의 t_member테이블의 각열값을 UPDATE하는 기능의 메소드 
	public void modMember(MemberVO memberVO) {
		try {
			//커넥션풀에서 커넥션 하나 얻기
			//DB와의 연결
			con = dataSource.getConnection();
			
			//매개변수로 전달받은 MemberVO객체의 id변수값에 해당되는
			//입력한 수정정보들을 t_member테이블에 UPDATE시키는 SQL문
			String query = "update t_member set pwd=?, name=?, email=? "
					     + "where id=?";
			
			pstmt = con.prepareStatement(query);
			//? 값 설정
			pstmt.setString(1, memberVO.getPwd());
			pstmt.setString(2, memberVO.getName());
			pstmt.setString(3, memberVO.getEmail());
			pstmt.setString(4, memberVO.getId());
			
			//UPDATE문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("modMember메소드 내부에서 SQL문 실행오류:"+e);
		}finally {
			ResourceClose();
		}
		
	}


	//삭제 <a>링크를 클릭했을때.. 서블릿으로 전송해온  삭제할 회원의 id를 매개변수로 전달 받아서
	//DELETE문장 완성후 ~  DB의 회원한사람의 정보를 삭제시키는 기능의 메소드 
	//참고.  DELETE FROM 삭제할테이블명  where 삭제할조건열=삭제할조건값;
	public void delMember(String id) {
	
		try {
			//1.커넥션풀(DataSouce)객체 내부에 있는 Connection객체 얻기(DB연결)
			con = dataSource.getConnection();
			
			//2.매개변수로 전달 받은 삭제할 회원 아이디를 이용해 DELETE SQL문장 만들기
			String query = "delete from t_member where id=?";
						
			//3. delete문장 전체를 미리 로드한 OraclcePreparedStatementWrapper실행 객체 얻기
			pstmt = con.prepareStatement(query);
						
			//3.1 ? 설정
			pstmt.setString(1, id);
			
			//4. 완성된 delete전체 문장을  DB에 전송해서 실행!
			pstmt.executeUpdate(); //삭제에 성공하면 1을 반환 , 
								   //삭제에 실패하면 0을 반환 			
			
			
		}catch (Exception e) {
			System.out.println("MemberDAO의 delMember메소드 내부에서 SQL문 실행 오류 : " + e);
		}finally {
			ResourceClose();
		}

	}
	
	
}



















