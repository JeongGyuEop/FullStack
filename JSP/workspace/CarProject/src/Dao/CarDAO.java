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

	// 예약 확인을 위해 예약 당시 입력했던 휴대폰 번호와 비밀번호를 매개변수로 전달 받아
	// 휴대폰 번호와 비밀번호가 일치하는 예약정보들을 조회
	public Vector<CarConfirmVo> getAllCarOrder(String memberphone, String memberpass) {
		
		// 조회된 CarConfirmVo 객체들을 저장할 Vector 배열 생성
		Vector<CarConfirmVo> v = new Vector<CarConfirmVo>();
		
		// 조회된 한 행의 정보(하나의 예약정보)를 저장할 객체의 참조변수 선언
		CarConfirmVo vo = null;
		
		try {
			con = ds.getConnection();
			
			//자연 조인(NATURAL JOIN)은 두 개의 테이블을 합칠 때, 
			//**두 테이블에 똑같은 이름을 가진 열(컬럼)**이 있으면 그 열을 기준으로 자동으로 연결하는 방법입니다. 
			//이를 통해 서로 관련된 데이터를 한 번에 볼 수 있게 됩니다.

			//쉽게 말하면:
			//두 테이블이 있을 때, 두 테이블에서 이름이 같은 열을 찾아냅니다.
			//그 같은 이름을 가진 열을 기준으로 두 테이블을 합쳐서 새로운 결과를 만듭니다.			  
				  
					//SELECT문 설명
					//이 쿼리는 non_carorder와 carlist 테이블에서 
				    //현재 날짜보다 시작 날짜(carbegindate)가 이후인 주문 정보를 조회하며, 
					//사용자의 전화번호와 비밀번호가 일치하는 데이터를 반환합니다.	
			String sql = "SELECT * FROM non_carorder NATURAL JOIN carlist "
						 + "WHERE sysdate < TO_DATE(carbegindate, 'YYYY-MM-DD') AND "
						 + "memberphone=? AND memberpass=?";
			
			//부분 해석1. SELECT * FROM carorder NATURAL JOIN carlist
			//carorder 테이블과 carlist 테이블의 공통된 컬럼을 기준으로 자연 조인을 수행한 후,
		    //두 테이블의 모든 열(*)을 선택(select)합니다. 
		    
			//부분 해석2. WHERE sysdate < TO_DATE(carbegindate, 'YYYY-MM-DD')
			//현재 날짜(sysdate)가 carbegindate보다 이전인 레코드들만 조회합니다. 
			//여기서 TO_DATE(carbegindate, 'YYYY-MM-DD')는 carbegindate를 'YYYY-MM-DD' 형식의 날짜로 변환하여 비교합니다.		
				
			//부분 해석3. AND memberphone=? AND memberpass=?
			//memberphone과 memberpass가 주어진 값(?에 바인딩되는 값)과 일치하는 레코드들만 조회합니다. 
			//이는 사용자가 입력한 전화번호와 비밀번호를 기준으로 필터링하는 조건입니다.	
		
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, memberphone);
			pstmt.setString(2, memberpass);
			
			rs = pstmt.executeQuery(); // SELECT 전체 문장 실행 후 조회한 결과데이터들을 
									   // ResultSet에 담아 얻는다.
			
			while(rs.next()) {
				vo = new CarConfirmVo();
				vo.setOrderid(rs.getInt("NON_ORDERID")); //주문 ID
				vo.setCarqty(rs.getInt("CARQTY")); //대여차량 갯수
				vo.setCarreserveday(rs.getInt(4)); //대여일수
				vo.setCarbegindate(rs.getString(5)); //대여시작날짜 
				vo.setCarins(rs.getInt("CARINS")); //보험 옵션 적용 여부
				vo.setCarwifi(rs.getInt("CARWIFI")); //와이파이 옵션 적용 여부
				vo.setCarnave(rs.getInt(8)); //네비 옵션 적용 여부
				vo.setCarbabyseat(rs.getInt(9)); //베이비시트 옵션 적용 여부
				vo.setCarname(rs.getString(12)); //차량명
				vo.setCarprice(rs.getInt(14)); //하루 대여 금액
				vo.setCarimg(rs.getString(17)); //차량이미지명
				
				//백터 배열에 VO추가
				v.add(vo); 
			}
						
		} catch(Exception e) {
			System.out.println("CarDAO클래스의 getAllCarOrder 메소드 내부에서 오류 발생" + e);
		} finally {
			try {
				closeResource();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return v;
	}

	
	// 예약한 아이디를 매개변수로 받아 예약한 정보 조회
	public CarConfirmVo getOneOrder(int orderid) {
		
		CarConfirmVo vo = null;
		
		try {
			// 1. DB 연결
			con = ds.getConnection();
			
			// 2. 매개변수 int orderid 로 전달받은 예약 아이디에 관한
			//    예약 정보들(모든 열)의 값을 조회하는 SELECT
			// 3. 2.의 SELECT 전체 구문을 미리 로드한 PreparedStatement 객체 얻기
			pstmt = con.prepareStatement("select * from non_carorder where non_orderid=?");
			
			// 4. ? 값 설정
			pstmt.setInt(1, orderid);
			
			// 5. PreparedStatement 실행 객체에 ? 값까지 완성된 전체 SELECT 문 실행  
			rs = pstmt.executeQuery();

			// 6. 조회된 행의 정보를 ResultSet에서 얻어
			//    CarConfirmVo 객체 하나 생성 후 변수에 저장
			if(rs.next()) {
				vo = new CarConfirmVo();
				vo.setOrderid(rs.getInt("non_orderid"));
				vo.setCarbegindate(rs.getString("carbegindate"));
				vo.setCarreserveday(rs.getInt("carreserveday"));
				vo.setCarins(rs.getInt("carins"));
				vo.setCarwifi(rs.getInt("carwifi"));
				vo.setCarnave(rs.getInt("carnave"));
				vo.setCarbabyseat(rs.getInt("carbabyseat"));
				vo.setCarqty(rs.getInt("carqty"));
			}
						
		} catch(Exception e) {
			System.out.println("CarDAO클래스의 getOneOrder 메소드 내부에서 오류 발생" + e);
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

	
	// 입력한 예약 정보를 수정 UPDATE 시키는 메소드
	public int carOrderUpdate(HttpServletRequest request) {
		
		int result = 0; // 수정 성공 시 1이 저장되고, 수정 실패하면 0이 저장될 변수
		
		try {

			con = ds.getConnection();
			
			// 예약한 아이디와 예약 당시 입력했던 비밀번호와 일치하는 하나의 예약정보를 수정
			String sql = "UPDATE non_carorder SET"
					+ " carbegindate=?, carreserveday=?, carins=?, "
					+ "carwifi=?, carnave=?, carbabyseat=?, carqty=? "
					+ "WHERE non_orderid=? AND memberpass=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("carbegindate"));
			pstmt.setInt(2, Integer.parseInt(request.getParameter("carreserveday")));
			pstmt.setInt(3, Integer.parseInt(request.getParameter("carins")));
			pstmt.setInt(4, Integer.parseInt(request.getParameter("carwifi")));
			pstmt.setInt(5, Integer.parseInt(request.getParameter("carnave")));
			pstmt.setInt(6, Integer.parseInt(request.getParameter("carbabyseat")));
			pstmt.setInt(7, Integer.parseInt(request.getParameter("carqty")));
			pstmt.setInt(8, Integer.parseInt(request.getParameter("orderid")));
			pstmt.setString(9, request.getParameter("memberpass"));
			
			// UPDATE 실행 후 성공하면 1, 실패하면 0을 반환	
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("CarDAO클래스의 carOrderUpdate 메소드 내부에서 오류 발생" + e);
		} finally {
			try {
				closeResource();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	
	// 삭제 성공 하면 1저장, 삭제 실패하면 0저장
	public int OrderDelete(int orderid, String memberpass) {
		int result = 0;
		
		try {
			//커넥션 풀에서 DB와 미리 연결을 맺어 만들어져 있는 
			//Connection객체 빌려오기(DB연결)
			con = ds.getConnection();
			
			//DELETE구문
			//->예약한 아이디와 
			//  예약 취소를 위해 입력한 비밀번호가 
			//  예약당시 입력했던 비밀번호(memberpass열에 저장된 비밀번호)와 
			//  일치(같은)하는 하나의 예약정보(레코드)를 삭제	
			String sql = "DELETE FROM non_carorder WHERE non_orderid=? AND memberpass=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderid);
			pstmt.setString(2, memberpass);
			
			//삭제에 성공하면 1을 반환 , 실패하면 0반환
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("CarDAO클래스의 OrderDelete 메소드 내부에서 오류 발생" + e);
		} finally {
			try {
				closeResource();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
} // CarDAO 클래스

















