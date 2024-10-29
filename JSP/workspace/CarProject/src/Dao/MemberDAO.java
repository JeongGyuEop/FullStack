package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import Vo.CarConfirmVo;
import Vo.CarListVo;
import Vo.CarOrderVo;
import Vo.MemberVo;

// MVC 중에서 M을 얻기 위한 클래스

// 데이터 베이스와 연결하여 비즈니스 로직 처리하는 클래스
public class MemberDAO {

	Connection con;
	
	PreparedStatement pstmt;
	
	ResultSet rs;
	
	DataSource ds;
	
	// 커넥션 풀 얻는 생성자
	public MemberDAO() {
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
	
	// 아이딩 중복 체크
	public boolean overlappedId(String id) {
		
		boolean result = false;
		
		try {
			con = ds.getConnection(); // DB 연결
			
			// oracle의 decode 함수 사용하여 
			// 입력한 ID에 해당하는 데이터를 검색하는데 조회된 레코드가 있으면 true 리턴
			// 없으면 false 리턴하는데,
			// 검색한 갯수가 1(검색한 레코드가 존재하면)이면 'true'를 반환
			// 검색이 안되면 'false'를 반환
			String sql = "select decode( count(*), 1, 'true', 'false' ) as result "
					+ "from member "
					+ "where id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			// select 전체 문장을 DB에 전송하여 실행한 조회된 결과를 ResultSet에 담아 반환
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 조회된 제목 행의 커서 (화살표)가 조죄된 행 줄로 내려왔을 때 있으면?
				String value = rs.getString("result"); // "false" 또는 "true"
				result = Boolean.parseBoolean(value); // "false" -> false 변환해서 저장
													  // "true" -> true 변환해서 저장
			}		
					
		} catch (Exception e) {
			System.out.println("MemberDAO의 overlappedId 메소드 내부에서 오류: " + e);
			e.printStackTrace();
		} finally {
			// 자원해제
			closeResource();
		}
		
		return result; // "true" 또는 "false" 부장(MemberService)에게 반환
	}

	//------
	// 새 회원 추가
	public int insertMember(MemberVo vo) {
		int result = 0;

		try {
			con = ds.getConnection(); // DB 연결
			
			// 매개변수로 전달 받은 MemberVO 객체의 각 변수의 값을 얻어
			// INSERT 문장을 완성시킨다.
			String sql = "INSERT INTO member(id, pass, name, reg_date, age, gender, address, email, tel, hp)"
					+ " VALUES (?, ?, ?, sysdate, ?, ?, ?, ?, ?, ?)";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getName());
			pstmt.setInt(4, vo.getAge());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getAddress());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getTel());
			pstmt.setString(9, vo.getHp());
			
			result = pstmt.executeUpdate();
					
		} catch (Exception e) {
			System.out.println("MemberDAO의 insertMember 메소드 내부에서 오류: " + e);
			e.printStackTrace();
		} finally {
			// 자원해제
			closeResource();
		}

		return result;
		
	}

	//-------
	// 로그인 요청 시 입력한 아이디, 비밀번호가 DB의 member테이블에 있는지 확인
	public int userCheck(String login_id, String login_pass) {
		
		int check = -1;
		
		try {
			con = ds.getConnection(); // DB 연결
			
			// member 테이블의 id 열값이 입력한 아이디에 해당하면
			// 해당 행의 모든 열 값 조회
			String sql = "SELECT * FROM member WHERE id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, login_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 입력한 아이디로 조회한 행이 있으면?(아이디가 있으면?)
				
				// 입력한 비밀번호와 조회된 비밀번호를 비교해서 비밀번호가 일치하면?
				if(login_pass.equals(rs.getString("pass"))) {
					check = 1;
				} else { // 아이디는 있으나 비밀번호가 일치하지 않으면?
					check = 0;
				}
			} else { // 입력한 아이디가 DB의 테이블레 없다면
				check = -1;
			}
			
			
		} catch (Exception e) {
			System.out.println("MemberDAO의 userCheck 메소드 내부에서 오류: " + e);
			e.printStackTrace();
		} finally {
			// 자원해제
			closeResource();
		}
		
		return check; // MemberService(부장)에게 결과 반환
	}

	// 로그인한 회원 아이디를 이용하여 회원정보 조회
	// 이유 : 글 작성 화면에 조회한 회원정보를 보여주기 위해
	public MemberVo memberOne(String memberid) {

		MemberVo vo = null;
		
		try {
			con = ds.getConnection(); // DB 연결
			
			
			String sql = "SELECT email, name, id FROM member WHERE id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberid);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new MemberVo();
				vo.setEmail(rs.getString("email"));
				vo.setName(rs.getString("name"));
				vo.setId(rs.getString("id"));
			}
			
			
		} catch (Exception e) {
			System.out.println("MemberDAO의 memberOne 메소드 내부에서 오류: " + e);
			e.printStackTrace();
		} finally {
			// 자원해제
			closeResource();
		}
			
		return vo;
	}
	
	
	
} // MemberDAO 클래스

















