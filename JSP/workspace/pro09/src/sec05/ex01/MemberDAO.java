package sec05.ex01;


import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//역할:
//오라클 DBMS의 XE데이터베이스의 t_member테이블과 연결하여 
//select(조회 해오거나), insert(추가 하거나), update(수정 하거나), delete(삭제)
//등의 작업을 할수 있게 코드를 작성하는 클래스 
public class MemberDAO {
//DB작업할 삼총사 객체를 저장할 변수 선언		
	//1총사.위 4가지 접속 정보를 이용해서 
	//오라클 DB와 연결을 맺은 접속정보를 지니고 있는 Connection객체를 저장할 참조변수 선언
	private Connection con;
	//2총사. DB와 연결 후 우리 개발자가 만든 SQL문장을  오라클DB의 테이블에 전송하여 실행할 역할을 하는
	//     PreparedStatement실행객체의 주소를 저장할 참조변수 선언
	private PreparedStatement pstmt;
	
	//3총사. SELECT SQL문장을 실행해서 조회한 검색 결과를 테이블형식으로 임시로 저장해 놓을 
	//      ResultSet객체의 주소를 저장할 참조변수 선언
	private ResultSet rs;
	
	
	//커넥션풀 객체를 담을 변수
	private DataSource dataSource;
	
	
	//기본생성자 
	//역할 : new MemberDAO(); 객체 생성시 호출되는 생성자로!!!!!
	//		생성자 내부에서 커넥션풀 역할을 하는 DataSource객체를 context.xml에 작성해 놓은 
	//		Recource태그로 얻습니다. 
	public MemberDAO() {
		try {
			//1.톰캣 서버가 실행되면 context.xml파일에 적은 <Recource>태그의 설정을 읽어와서
			//  톰캣 서버의 메모리에 <Context>태그에 대한 Context객체들을 생성하여 저장해 둡니다.
			//  이때 InitialContext객체가 하는역할은 톰캣서버 실행시 context.xml에 의해서 생성된
			//  Context객체들에 접근하는 역할을 하기떄문에 생성합니다.
			Context ctx = new InitialContext();
			
			//2.JDNI기법(key또는 name속성의 값을 이용해 Value를 얻는 기법)으로 접근하기 위해
			//기본경로(java:/comp/env)를 InitialContext객체에 지정합니다.
			//lookup("java:/comp/env"); 는 톰캣 서버의 환경설정에 관련된 Context객체들에 접근하기 위한
			//기본경로 주소를 설정하는 것입니다.
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			
			//3.그런후 다시 톰캣서버는 context.xml에 설정한 <Resource name="jdbc/oracle"/>태그의
			//  name속성값 "jdbc/oralce"(JDNI기법을 사용하기위한 key)를 이용해 
			//  커넥션풀(DataSource객체)를 얻을 수 있습니다.
			dataSource = (DataSource)envContext.lookup("jdbc/oracle");
			//커넥션풀 얻기
		
		} catch (Exception e) {
			System.out.println("DataSource커넥션풀 객체 얻기 실패  : " + e);
		}
	}//생성자 
	
	
	//오라클 DBMS 서버 내부에 XE데이터베이스 내부에 만들어 놓은 t_member테이블의 모든회원정보들을
	//한번에 조회해서 제공하는 메소드 
	public ArrayList listMembers() {//<- MemberServlet서블릿에서 
									//   모든 회원정보를 조회 하기 위해 호출한 메소드
		
		//t_member테이블에 저장된 모든회원정보들을 조회해서 가져와
		//가변길이 배열의 각 index위치에 임시로 저장할 배열 공간을 생성 한것입니다.
		ArrayList list = new ArrayList();
		
		try {
			//DB와연결을 맺기 위해 
			//위 dataSource참조변수에 저장된 DataSource커넥션풀 객체 내부에 저장해 둔
			// 미리 DB와 연결을 맺어놓은 Connection객체 50개 중에서 하나 빌려 옵니다.
			//요약 : 커넥션풀DataSource 공간에서 Connection객체 얻기
			con = dataSource.getConnection();
			
			
			//순서4.  순서5.
			//Connection객체의 prepareStatement메소드 호출시 
			//실행할 select문장일 매개변수로 전달하면  ?에 설정할 값을 제외한 select전체 문장
			//"select * from t_member where id=?" 을 PreparedStatement실행객체에 저장후 
			//PreparedStatement실행객체 자체를 반환 해서 얻습니다.
		//	stmt = con.createStatement();
			pstmt = con.prepareStatement("select * from t_member");
			
			
			//순서6. Query문(SQL문 = SELECT문장)을 DB의 t_member테이블에 전송하여 실행!(조회됨)
			//select * from t_member SQL문을 executeQuery메소드 호출시 전달 하면
			//t_member테이블에서 조회된 모든 행들을 임시로 담은 ResultSet객체 메모리 자체를 반환합니다.
			//단! 조회된 화면의 커서(화살표)위치는 가장 처음에는 조회된 테이블의 제목열 행을 가리키고 있다.
		//	rs = stmt.excuteQuery("select * from t_member where id='"+id_+"'");
			rs = pstmt.executeQuery();
			
			//순서7. 조회한 회원 레코드들이 ReusltSet객체 메모리에 표형태로 저장되어 있으면 
			//     계속 반복해서 회원 레코드(행) 단위의 조회된 열 값들을 차례로 얻어
			//		MemberVO객체를 생성하여 각 인스턴스변수에 열의 값들을 각각 저장 시킵니다.
			//		마지막으로 MemberVO객체들을 반복해서 ArrayList배열에 추가 시킵니다.
			while( rs.next() ) {
				//회원 레코드(행) 단위의 조회된 열 값들을 차례대로 얻어 변수에 저장
				String id = rs.getString("ID"); //"hong"
				String pwd = rs.getString("PWD"); //"1212"
				String name = rs.getString("NAME"); //"홍길동"
				String email = rs.getString("EMAIL"); //"hong@gmail.com"
				Date joinDate = rs.getDate("JOINDATE");//24/03/05
						
				//MemberVO객체를 생성하여 각 인스턴스변수에 조회된 열의 값들을 각각 저장 시킵니다.
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				
				//마지막으로 MemberVO객체를 ArrayList배열에 추가 시킵니다.
				list.add(vo);
				
				//현재 ArrayList배열 모습
				//[ new MemberVO(); , new MemberVO(); ,  new MemberVO(); ]
				//	     0                   1                2               index
				
			}
			
		} catch (Exception e) {
			System.out.println("listMembers메소드 내부에서 SQL문 실행 오류 : " + e);
		} finally {
			//순서9. DB작업 관련 객체들(Conneciton, Statement, ResultSet)을 모두 사용후 자원해제
			ResourceClose();
		}
	
		return list; //ArrayList배열 자체를 MemberServlet으로 반환(리턴)
		
	}//listMembers메소드 닫기 기호 

	
	/*
	 	memberForm.html에서 입력한 가입할 회원내용들을
	 	MemberVO객체의 각인스턴스 변수에 저장한 뒤~~~
	 	addMember메소드 호출시!!! 매개변수 vo로 전달 받아 INSERT문장을 만들고
	 	만든 INSERT문장을 DB의 t_member테이블에 전송해서 새 회원을 추가시키는 기능의 메소드 	 
	 */
	public int addMemember(MemberVO vo) { //<--- MemberServlet서블릿 내부에서 호출하는 메소드 
		
		int result = 0;
		
		try {
			//1. 커넥션풀(DataSource)에서 미리 DB와 연결 해 놓은 Connection객체 빌려오기 
			con = dataSource.getConnection();//DB와의 연결
			
			//2. t_member테이블에 INSERT할 값! 즉! 우리가 입력한 회원가입할 정보들은
			//	 MemberVO객체의 각 인스턴스변수에 저장되어 있으므로
			//   MemberVO객체의 getter메소드들을 호출해서 각 인스턴스변수에 저장된 입력값을 리턴받자.
			String id = vo.getId();//입력한 아이디를 MemberVO의 id변수로 부터 얻습니다.
			String pwd = vo.getPwd();//입력한 비밀번호를 MemberVO의 pwd변수로 부터 얻습니다.
			String name = vo.getName();
			String email = vo.getEmail();
			
			//3. insert SQL문장 만들기 version1.  Statement실행객체를 사용할때 만드는 코드
		//	String query = "insert into t_member(id,        pwd,      name,         email)" + 
		//				                "values('"+id+"','"+pwd+"','"+name+"','"+email+"')";
			
			//3. insert SQL문장 만들기 version2.  PreparedStatement 실행객체 사용할떄 만드는 코드
	     	String query = "insert into t_member(id, pwd, name, email)" + 
	     								 "values(?,  ?,   ?,     ?)";
	     	
			//4. query변수에 저장된 전체 insert문자열을 미리 로드한 
	     	//   OraclePreparedStatementWrapper실행 객체 얻기
	     	pstmt = con.prepareStatement(query);
	     	
	     	//4.1 OraclePreparedStatementWrapper실행객체에 위 입력된 가입할 값들을
	     	//    ? 기호 대신 설정 합니다.
	     	//요약 : ?값 설정
	     	pstmt.setString(1, id); //첫번쨰 ? 값을 id변수값으로 설정 
	     	pstmt.setString(2, pwd);//두번쨰 ? 값을 pwd변수값으로 설정 
	     	pstmt.setString(3, name);//세번쨰 ? 값을 가입시 입력한 이름이 저장된 name변수값으로 설정 
	     	pstmt.setString(4, email);//네번쨰 ? 값을 가입시 입력한 이메일이 저장된 email변수값으로 설정
	     	
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
			
			System.out.println("MemberDAO의 addMember메소드 내부에서 SQL문 오류:" + e);
			
		} finally {
			//자원해제 메소드 호출
			ResourceClose();
		}
		return result;
	}//adMember메소드 끝
	
	
	//삭제 <a>링크를 클릭했을때 .. 서블릿으로 전송해온 삭제할 회원 id를 매개변수로 전달 받아서
	//DELETE문장 완성후 ~ DB의 t_member테이블에 저장된 회원 한사람의 정보를 삭제 시키는 메소드 
	public void delMember(String id){
		
		try {
			//1. 커넥션풀(DataSource)에서 미리 DB와 연결 해 놓은 Connection객체 빌려오기 
			con = dataSource.getConnection();//DB와의 연결
			
			//2. 매개변수 String id로 전달받은 삭제할 회원의 아이디를 이용해 DELETE SQL문장 만들기
			String query = "DELETE FROM t_member WHERE id=?";
	     	
			//3. query변수에 저장된 전체 insert문자열을 미리 로드한 
	     	//   OraclePreparedStatementWrapper실행 객체 얻기
	     	pstmt = con.prepareStatement(query);
	     	
	     	//3.1 OraclePreparedStatementWrapper실행객체에 위 입력된 가입할 값들을
	     	//    ? 기호 대신 설정 합니다.
	     	//요약 : ?값 설정
	     	pstmt.setString(1, id); //첫번쨰 ? 값을 id변수값으로 설정 
	     	

	     	//4. OraclePreparedStatementWrapper실행객체에 설정된 전체 DELETE문장을
	     	//   DB의 t_member테이블에 전송해 실행!
	        pstmt.executeUpdate(); //DELETE에 성공하면 
	     						   //DELETE에 성공한 레코드 갯수 1을 반환
	     						   //DELETE에 실패하면 0을 반환   			
		} catch (Exception e) {
			
			System.out.println("MemberDAO의 delMember메소드 내부에서 SQL문 오류:" + e);
			
		} finally {
			//자원해제 메소드 호출
			ResourceClose();
		}
		
		
	}
	
	
	//LoginServlet4서블릿 페이지에서 호출하는 메소드로
	//입력한 아이디,비밀번호가 저장된 MemberVO객체의 주소를 매개변수 memberVO로 받아서
	//t_member테이블에 있는지 없는지 판단을위해 조회 작업 하는 메소드
	public boolean isExisted(MemberVO memberVO) {
		
		boolean result = false; //조회 되었는지 되지 않았는지 판단할 값 저장
								 //true또는 false가 저장됨
		
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
			
		try {
			//1. 커넥션풀(DataSource)에서 미리 DB와 연결 해 놓은 Connection객체 빌려오기 
			con = dataSource.getConnection();//DB와의 연결
			
			
			//decode( count(*)  ,  1, 'true', 'false' )함수
			//해석 입력한 아이디 비번에 해당하는 회원 레코드 행이 1개가 조회되면
			//	  'true'문자열을 반환하고 1개가 아니면 'false'를 반환하는 decode함수 
		
			//2. 입력한 아이디 비밀번호를 갖는 회원정보를 조회하는데....
			//   조회된 회원의 행의 갯수가 1이면 'true' 조회 결과가 나오고
			//	 조회된 회원의 행의 갯수가 1이 아닌 0이면  'false'문자열로 조회 결과가 출력됨
			String query = "select  decode( count(*), 1, 'true', 'false' )  as result from t_member " +
						   " where id=? and pwd=?";
			
			//3. query변수에 저장된 전체 insert문자열을 미리 로드한 
	     	//   OraclePreparedStatementWrapper실행 객체 얻기
	     	pstmt = con.prepareStatement(query);
	     	
	     	//3.1 OraclePreparedStatementWrapper실행객체에 위 입력된 가입할 값들을
	     	//    ? 기호 대신 설정 합니다.
	     	//요약 : ?값 설정
	     	pstmt.setString(1, id); //첫번쨰 ? 값을 id변수값으로 설정 
	     	pstmt.setString(2, pwd); //두번쨰 ? 값을 pwd변수값으로 설정
	     	

	     	//4. OraclePreparedStatementWrapper실행객체에 설정된 전체 SELECT문장을
	     	//   DB의 t_member테이블에 전송해 실행! 후~ 조회된 결과를 ResultSet에 담아
	     	//	 ResultSet 자체를 반환
	        rs = pstmt.executeQuery();
	        
	        rs.next(); //커서를 조회된 첫번째 레코드 행으로 내려와 가리키게 하기 
	        
	        		 //조회된 'true'문자열을 true변환해서  result변수에 저장 
	        result = Boolean.parseBoolean( rs.getString("result") ); 
	        
	        System.out.println(result);
	        
	        
		} catch (Exception e) {
			
			System.out.println("MemberDAO의 delMember메소드 내부에서 SQL문 오류:" + e);
			
		} finally {
			//자원해제 메소드 호출
			ResourceClose();
		}
		
	
		return result; //로그인 요청을 위해 입력한 아이디 비번에 해당하는 회원이 조회되면
					   //true 반환 ,  조회 안되면 false 반환 
	}
	
	
	
	//DB작업관련 삼총사 객체 메모리 자원해제 하는 기능의 메소드
		public void ResourceClose() {
			try {
				//만약에 pstmt변수에 Statement객체가 저장되어 있으면(사용하고 있으면?)
				if(pstmt != null) pstmt.close();
				//만약에 rs변수에 ResultSet객체가 저장되어 있으면 (사용하고 있으면?)
				if(rs != null) rs.close();
				//만약에 con변수에 Connection객체가 저장되어 있으면 (사용하고 있으면?)
				if(con != null) con.close();
				
			} catch (Exception e) {
				System.out.println("자원해제 실패 : " + e);
			}
		}
}













