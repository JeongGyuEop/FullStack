package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import Vo.CarListVo;
import Vo.CarOrderVo;

// MVC 중에서 M을 얻기 위한 클래스

// 데이터 베이스와 연결하여 비즈니스 로직 처리하는 클래스
public class CarDAO {

	Connection con;
	
	PreparedStatement pstmt;
	
	ResultSet rs;
	
	DataSource ds;
	
	// 커넥션 풀 얻는 생성자
	public CarDAO() {
		try {
			
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:/comp/env/jdbc/oracle");
			
		} catch(Exception e) {
			System.out.println("커넥션풀 얻기 실패 오류 : " + e.toString());
		}
	}
	
	// 자원해제(Connection, PreparedStatement, ResultSet) 기능의 메소드
	private void closeResource() throws Exception {
		if(con != null) {con.close();}
		if(pstmt != null) {pstmt.close();}
		if(rs != null) {rs.close();}
	}
	
	// CarList 테이블에 저장된 모든 차량 정보 조회
	public Vector<CarListVo> getAllCarList() {
		
		Vector<CarListVo> vector = new Vector<CarListVo>();
		
		// 조회된 차량 하나의 정보(하나의 행 정보)를 저장할 CarListVo 객체의 주소를 담을 변수
		CarListVo vo = null;
		
		try {
			
			con = ds.getConnection(); // DB 연결
			
			String sql = "SELECT * FROM carlist"; // 모든 차량 조회
			
			pstmt = con.prepareStatement(sql); // select 실행 객체 얻기
			
			// select 문장을 실행해서 조회한 결과 데이터를 ResultSet에 담아 얻기
			rs = pstmt.executeQuery();
			
			// 반복문을 활용하여 ResultSet 객체에 조회된 한줄 정보씩 얻어와서
			// CarListVo 객체의 각 변수에 저장 후
			// CarListVo 객체를 Vector 배열에 추가하여 담는다.
			while(rs.next()) {
				vo = new CarListVo(rs.getInt("carno"),
								   rs.getString("carname"), 
								   rs.getString("carcompany"), 
								   rs.getInt("carprice"), 
								   rs.getInt("carusepeople"), 
								   rs.getString("carinfo"),
								   rs.getString("carimg"),
								   rs.getString("carcategory"));
				
				vector.add(vo);
				
			}
			
		} catch(Exception e) {
			System.out.println("CarDAO클래스의 getAllCarList 메소드 내부에서 오류 발생" + e);
		} finally {
			try {
				closeResource();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return vector; // 조회된 차량정보들이 저장된 Vector 배열 반환
	
	} // getAllCarList 메소드 닫는 부분

	// CarReservation.jsp 또는 CarList.jsp 화면의 디자인 중
	// <select>, <option> 의 선택한 유형의 차량 조회
	public Vector<CarListVo> getCategoryList(String category) {
									// Small, Mid, Big
		
		Vector<CarListVo> vector = new Vector<CarListVo>();
		
		CarListVo vo = null;
		
		try {
		
			con = ds.getConnection(); // DB 연결
			
			String sql = "SELECT * FROM carlist WHERE carcategory=?";
			
			pstmt = con.prepareStatement(sql); // select 실행 객체 얻기
			
			pstmt.setString(1, category);
			
			// select 문장을 실행해서 조회한 결과 데이터를 ResultSet에 담아 얻기
			rs = pstmt.executeQuery();
			
			// 반복문을 활용하여 ResultSet 객체에 조회된 한줄 정보씩 얻어와서
			// CarListVo 객체의 각 변수에 저장 후
			// CarListVo 객체를 Vector 배열에 추가하여 담는다.
			while(rs.next()) {
				vo = new CarListVo(rs.getInt("carno"),
								   rs.getString("carname"), 
								   rs.getString("carcompany"), 
								   rs.getInt("carprice"), 
								   rs.getInt("carusepeople"), 
								   rs.getString("carinfo"),
								   rs.getString("carimg"),
								   rs.getString("carcategory"));
				
				vector.add(vo);

			}
		} catch(Exception e) {
			System.out.println("CarDAO클래스의 getCategoryList 메소드 내부에서 오류 발생" + e);
		} finally {
			try {
				closeResource();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return vector;
	}

	// 조회 시 사용할 차량 번호를 매개변수로 전달 받아서 
	// 전달받은 차 번호에 해당하는 차량 정보 조회
	public CarListVo getOneCar(int carno) {
		CarListVo vo = null;
		
		try {
			
			con = ds.getConnection(); // DB 연결
			
			String sql = "SELECT * FROM carlist WHERE carno=?";
			
			pstmt = con.prepareStatement(sql); // select 실행 객체 얻기
			
			pstmt.setInt(1, carno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new CarListVo(rs.getInt("carno"),
						   rs.getString("carname"), 
						   rs.getString("carcompany"), 
						   rs.getInt("carprice"), 
						   rs.getInt("carusepeople"), 
						   rs.getString("carinfo"),
						   rs.getString("carimg"),
						   rs.getString("carcategory"));
			}
			
			
		} catch(Exception e) {
			System.out.println("CarDAO클래스의 getOneCar 메소드 내부에서 오류 발생" + e);
		} finally {
			try {
				closeResource();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return vo;
	}
	
	// 매개변수로 예약할 정보가 저장된 CarOrderVo 객체를 전달 받아
	//DB의 non_carorder 테이블 또는 carorder 테이블에 INSERT 하는 기능의 메소드
	public void insertCarOrder(CarOrderVo vo, HttpSession session) {
		String id = (String)session.getAttribute("id");
		
		String sql = null;
		
		try {
			
			con = ds.getConnection(); // DB 연결
			
			// session 영역에 아이디가 저장되어 있지 않으면?(비회원으로 예약)
			if(id == null) {
				sql = "INSERT INTO non_carorder(non_orderid,"
											 + "carno,"
											 + "carqty,"
											 + "carreserveday,"
											 + "carbegindate,"
											 + "carins,"
											 + "carwifi,"
											 + "carnave,"
											 + "carbabyseat,"
											 + "memberphone,"
											 + "memberpass) "
					+ "VALUES(non_carorder_non_orderid.nextval, ?,?,?,?,?,?,?,?,?,?)";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, vo.getCarno());
				pstmt.setInt(2, vo.getCarqty());
				pstmt.setInt(3, vo.getCarreserveday());
				pstmt.setString(4, vo.getCarbegindate());
				pstmt.setInt(5, vo.getCarins());
				pstmt.setInt(6, vo.getCarwifi());
				pstmt.setInt(7, vo.getCarnave());
				pstmt.setInt(8, vo.getCarbabyseat());
				pstmt.setString(9, vo.getMemberphone());
				pstmt.setString(10, vo.getMemberpass());
				
				
			} else { // session 영역에 아이디가 저장되어 있으면?(회원으로 예약)
				
			}
			
			// insert 문장 DB에 전송해서 실행
			pstmt.executeUpdate();
			
			
		} catch(Exception e) {
			System.out.println("CarDAO의 insertCarOrder 메소드 내의 오류 : " + e);
			e.printStackTrace();
		} finally {
			try {
				closeResource();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
} // CarDAO 클래스

















