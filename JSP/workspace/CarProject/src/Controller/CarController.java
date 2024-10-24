package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.CarDAO;
import Vo.CarConfirmVo;
import Vo.CarListVo;
import Vo.CarOrderVo;

// MVC 중에서 C 역할

// index.jsp를 요청하면 location.href="/Car/Main";에 의해 CarController로
// CarMain.jsp (VIEW) 화면 재요청

// /Car/CarList.do

// /Car/CarInfo.do?carno=${vo.carno }

// /Car/cc?center=CarReserveConfirm.jsp

@WebServlet("/Car/*")
public class CarController extends HttpServlet {

	private CarDAO cardao;
	
	// private MemberDAO memberdao;
	
	@Override
	public void init() throws ServletException {
		cardao = new CarDAO();
		
		// memberdao = new MemberDAO();
	}
	
	// doGet doPost 메소드 오버라이딩
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doHandle(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8"); // MIME TYPE 설정
		
		//웹브라우저로 출력할 출력 스트림 생성
	      PrintWriter pw = response.getWriter();
		
		String action = request.getPathInfo(); // 클라이언트가 요청안 2단계 주소
		
		System.out.println("요청한 2단계 주소 : " + action);
		
		// /Main <- CarMain.jsp(VIEW) 메인 화면 요청
		// /bb <- 예약하기 메뉴를 클릭해서 전체검색 또는 카테고리별 검색 화면 요청
		// /CarList.do <- 전체 차량 조회 요청
		// /carcategory.do <- 차량 유형별(소형, 중형, 대형 중) 조회 요청
		// /CarInfo.do <- carno번호에 해당하는 차량 한 대의 정보 조회 요청
		// /CarOption.do <- 렌트를 위해 옵션을 추가로 선택하는 화면 요청
		// /CarOptionResult.do <- 추가한 옵션 금액 + 기본 금액을 계산 요청
		// /CarOrder.do <- 비회원으로 예약 요청
		// /cc <- 예약확인 하기 위해 예약 당시 입력했던 비회원 핸드폰번호, 비밀번호를
		//		  입력하여 예약확인을 요청하는 디자인 VIEW 를 요청!
		// /CarReserveConfirm.do <- 입력한 휴대폰 번호와 비밀번호로 정보 조회 요청!
		// /Car/update.do <- 예약한 정보 수정하기 위해 예약한 아이디로 예약 정보 조회요청!
		// /Car/updatePro.do <- 입력한 정보 수정(UPDATE) 요청
		// /Car/delete.do <- 예약 취소를 위해서 비밀번호를 입력해서 예약취소 요청하는 VIEW 보여줘 요청
		// /Car/deletePro.do <- 예약한 내용을 취소하는 요청
		
		// 재요청할 경로 주소를 저장할 변수
		String nextPage = null;
		
		if(action.equals("/Main")) { // CarMain.jsp 메인화면 2단계 요청 주소를 받으면
			nextPage = "/CarMain.jsp";
			
		} else if(action.equals("/bb")) { // /Car/bb?center=CarReservation.jsp
			
			// 중앙화면 요청한 파라미터 주소 얻기
			String center = request.getParameter("center");
											// "CarReservation.jsp"
			
			// request 내장 객체에 CarReservation.jsp 저장(바인딩)
			request.setAttribute("center", center);
			
			// 메인 화면 재요청을 위해 경로 저장
			nextPage = "/CarMain.jsp";
			
		} else if(action.equals("/CarList.do")) { // 전체 차량 조회 2단계 요청 주소를 받았을 때
			
			// 모든 차량 조회 명령을 위해 CarDAO 객체의 getAllCarList() 메소드 호출!
			// 참고. 조회된 모든 차량 정보가 Model 역할을 한다.
			Vector<CarListVo> vector = cardao.getAllCarList();
			
			// View 중앙 화면에 조회된 vector의 정보를 보여주기 위해
			// vector 내장 객체 바인딩
			request.setAttribute("v", vector);
			
			// View 중앙화면에 보일 파일명을 request 내장 객체에 바인딩
			request.setAttribute("center", "CarList.jsp");
			
			nextPage = "/CarMain.jsp";
			
		// 소형, 중형, 대형 중 선택한 유형의 차량 조회 요청주소를 받았을 때
		} else if(action.equals("/carcategory.do")) {
			// 소형(Small), 중형(Mid), 대형(Big) 중 선택한 option의 value 속성값 얻기
			String category = request.getParameter("carcategory");
			
			// 선택한 유형의 차량을 조회하기 위해서 명령
			Vector vector = cardao.getCategoryList(category);
			
			// View 중앙 화면에 조회된 vector의 정보를 보여주기 위해
			// vector 내장 객체 바인딩
			request.setAttribute("v", vector);
						
			// View 중앙화면에 보일 파일명을 request 내장 객체에 바인딩
			request.setAttribute("center", "CarList.jsp");
						
			nextPage = "/CarMain.jsp";
			
		// CarList.jsp 조회된 화면에서 렌트 예약을 위해
		// 차량 한 대의 정보를 감싸고 있는 <a>링크를 클릭하여
		// 조회 요청을 받았을 때
		} else if(action.equals("/CarInfo.do")) {
			// /Car/CarInfo.do?carno=${vo.carno }
			
			// 조회 시 사용할 차량 번호 얻기
			int carno = Integer.parseInt(request.getParameter("carno"));
			
			// 요청받은 carno 차 번호에 해당하는 차량 정보를 조회하기 위해 
			// CarDAO 객체의 getOneCar 메소드 호출 시 인자로 차량 번호를 전달해서 조회해 온다.
			CarListVo vo = cardao.getOneCar(carno);
			
			// View 중앙 화면에 조회된 vector의 정보를 보여주기 위해
			// vector 내장 객체 바인딩
			request.setAttribute("vo", vo);
						
			// View 중앙화면에 보일 파일명을 request 내장 객체에 바인딩
			request.setAttribute("center", "CarInfo.jsp");
						
			nextPage = "/CarMain.jsp";
			
		// 
		} else if(action.equals("/CarOption.do")) {
			// /Car/CarOption.do
			
			// 중앙 옵션 추가 요청 화면 VIEW 경로 request 바인딩
			request.setAttribute("center", "CarOption.jsp");
			
			
			nextPage = "/CarMain.jsp";
		} else if(action.equals("/CarOptionResult.do")) {
			// /Car/CarOptionResult.do
			
			// 요청 값 얻기
			int carno = Integer.parseInt(request.getParameter("carno"));
			String carbegindate = request.getParameter("carbegindate");
			int carqty = Integer.parseInt(request.getParameter("carqty"));
			int carprice = Integer.parseInt(request.getParameter("carprice"));
			int carreserveday = Integer.parseInt(request.getParameter("carreserveday"));
			int carins = Integer.parseInt(request.getParameter("carins"));
			int carwifi = Integer.parseInt(request.getParameter("carwifi"));
			int carnave = Integer.parseInt(request.getParameter("carnave"));
			int carbabyseat = Integer.parseInt(request.getParameter("carbabyseat"));
			
			// 응답할 값 마련(렌트할 금액 최종 계산, 비즈니스 로직 처리)
			// 차량 기본 금액 계산 = 대여수량 * 대여금액 * 대여기간
			int totalreserve = carqty * carprice * carreserveday;
			
			// 추가 옵션 금액 계산
			int totaloption = (carins + carwifi + carbabyseat) * carreserveday * 10000 * carqty;
			
			// 웹 브라우저로 응답할 VIEW(CarOrder.jsp)중앙화면에 보여주기 위해
			// CarOrderVo 객체를 생성하여 각 인스턴스 변수에 저장시킨다.
			CarOrderVo vo = new CarOrderVo();
			vo.setCarno(carno);
			vo.setCarqty(carqty);
			vo.setCarreserveday(carreserveday);
			vo.setCarbegindate(carbegindate);
			vo.setCarins(carins);
			vo.setCarwifi(carwifi);
			vo.setCarnave(carnave);
			vo.setCarbabyseat(carbabyseat);
			
			request.setAttribute("vo", vo);
			request.setAttribute("totalreserve", totalreserve);
			request.setAttribute("totaloption", totaloption);
			
			// 세션 메모리 영역 얻기
			HttpSession session = request.getSession();
			
			String id = (String)session.getAttribute("id");
			
			if(id == null) {// 로그인이 안된 비회원의 총결제 금액을 보여주자

				request.setAttribute("center", "CarOrder.jsp");
			} else { // 로그인 후 회원으로 총 결제 금액을 보여주자
				
				request.setAttribute("center", "LoginCarOrder.jsp");
			}
			
			
			nextPage = "/CarMain.jsp";
		} else if(action.equals("/CarOrder.do")) {//예약요청을 받았을때
			
			//렌트 예약을 위해 선택했던 값들 얻기
			//요청한 값 얻기
			int carno = Integer.parseInt(request.getParameter("carno"));
			String carbegindate = request.getParameter("carbegindate");
			int carqty = Integer.parseInt(request.getParameter("carqty"));
//			int carprice = Integer.parseInt(request.getParameter("carprice"));
			int carreserveday = Integer.parseInt(request.getParameter("carreserveday"));
			int carins = Integer.parseInt(request.getParameter("carins"));
			int carwifi = Integer.parseInt(request.getParameter("carwifi"));
			int carnave = Integer.parseInt(request.getParameter("carnave"));
			int carbabyseat = Integer.parseInt(request.getParameter("carbabyseat"));
			
			CarOrderVo vo = new CarOrderVo();
			vo.setCarno(carno);
			vo.setCarqty(carqty);
			vo.setCarreserveday(carreserveday);
			vo.setCarbegindate(carbegindate);
			vo.setCarins(carins);
			vo.setCarwifi(carwifi);
			vo.setCarnave(carnave);
			vo.setCarbabyseat(carbabyseat);
			
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			
			if(id == null) {//비회원으로 예약할때~
				//비회원 으로 예약시 입력했던 핸드폰번호와 비밀번호 얻기 
				String memberphone = request.getParameter("memberphone");
				String memberpass = request.getParameter("memberpass");
				
				vo.setMemberphone(memberphone);
				vo.setMemberpass(memberpass);
				
			}else {//회원으로 예약할떄~
				//아이디 비밀번호 얻기 
				String memberid = request.getParameter("memberid");
				String memberpass= request.getParameter("memberpass");
				
				vo.setId(memberid);
				vo.setMemberpass(memberpass);
				
			}
			//CarDAO객체의 insertCarOrder메소드 호출시~
			//매개변수로 CarOrderVo객체와  HttpSession내장객체 를 전달해서
			//INSERT작업 명령 
			cardao.insertCarOrder(vo,session);
			
			//출력 스트림 PrintWriter객체 생성
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print(" alert('예약되었습니다.'); ");
			out.print(" location.href='"+request.getContextPath()
										+"/Car/CarList.do'");
			out.print("</script>");
			
			return;

		
			// /cc <- 예약확인 하기 위해 예약 당시 입력했던 비회원 핸드폰번호, 비밀번호를
		//		  입력하여 예약확인을 요청하는 디자인 VIEW 를 요청!
		} else if(action.equals("/cc")) {
			// /Car/cc?center=CarReserveConfirm.jsp
			// 중앙 VIEW 화면에 보여줄 요청할 값 얻기
			String center = request.getParameter("center"); // "CarReserveConfirm.jsp"
			
			// request에 "CarReserveConfirm.jsp" 중앙 VIEW 화면 주소를 바인딩(저장)
			request.setAttribute("center", center);
			
			nextPage="/CarMain.jsp";
			
		// 입력한 휴대폰번호와 비밀번호로 예약한 렌트 정보 조회 요청!
		} else if(action.equals("/CarReserveConfirm.do")) {
			
			// CarReserveConfirm.do
			
			// (입력한 휴대폰 번호와 비밀번호 얻기)
			String memberphone = request.getParameter("memberphone");
			String memberpass = request.getParameter("memberpass");
			
			//예약한 정보를 조회 하기 위해 CarDAO객체의 getAllCarOrder 메소드 호출
			//매개변수로 입력한 휴대번호와 비밀번호를 전달 하여 SELECT문장을 만든 뒤 
			//조회한 정보들을 각각 CarConfirmVo객체에 담아 Vector배열에 최종 저장 후 반환 받자
			Vector<CarConfirmVo> vector = cardao.getAllCarOrder(memberphone, memberpass);
			
			// 조회한 예약정보들을 중앙 VIEW(CarReserveResult.jsp)에 보여주기 위해 
			// 먼저 ~ request 내장 객체에 바인딩(저장)
			request.setAttribute("v", vector);
			
			// 중앙 VIEW (예약한 정보를 보여줄 CarReserveResult.jsp)주소를
			// request 내장 객체에 바인딩(저장)
			request.setAttribute("center", "/CarReserveResult.jsp");
			
			request.setAttribute("memberphone", memberphone);
			request.setAttribute("memberpass", memberpass);

			nextPage="/CarMain.jsp";
		
		// 예약한 정보 수정하기 위해 예약한 아이디로 예약정보 조회요청!
		} else if(action.equals("/update.do")) {
			/*
			 * <a href="${contextPath }/Car/update.do?orderid=${vo.orderid}&
			 * 										  carimg=${vo.carimg}&
			 * 									      memberpass=${requestScope.memberpass}&
			 * 										  memberphone=${requestScope.memberphone}"> 
			 * 		예약수정 
			 * </a>
			 */
			
			// 요청한 값 얻기
			int orderid = Integer.parseInt(request.getParameter("orderid"));
			String carimg = request.getParameter("carimg");
			String memberphone = request.getParameter("memberphone");
			String memberpass = request.getParameter("memberpass");
			
			//예약 아이디를 이용해 예약한 정보를 DB에서 조회하기 위해
			//CarDAO객체의 getOneOrder메소드 호출할때 
			//매개변수로 orderid를 전달 하여 조회 해 오자
			CarConfirmVo vo = cardao.getOneOrder(orderid);
						 vo.setCarimg(carimg);
						 
			// 중앙화면 VIEW("CarConfirmUpdate.jsp")에 조회된 예약정보를 보여주기 위해
			// 일단~~~ request 내장 객체 영역에 CarConfirmVo 객체 바인딩
			request.setAttribute("vo", vo);
			request.setAttribute("memberphone", memberphone);
			request.setAttribute("memberpass", memberpass);
			
			request.setAttribute("center", "CarConfirmUpdate.jsp"); // 중앙 VIEW
			
			nextPage = "/CarMain.jsp";
			
		// 입력한 정보를 DB에 UPDATE 수정 해주세요!
		} else if(action.equals("/updatePro.do")) {
			
			// 수정을 위해 입력한 정보들은 request 내장 객체 메모리에 저장되어 있으므로
			// DB에 UPDATE시키기 위해 CarDAO객체의 carOrderUpdate 메소드 호출할 때 
			// 매개변수로 request 객체 메모리의 주소를 전달해 update 시키자!
			int result = cardao.carOrderUpdate(request);
			int orderid = Integer.parseInt(request.getParameter("orderid"));
			String memberphone = request.getParameter("memberphone");
			String carimg = request.getParameter("carimg");
			
			if(result == 1) {
				pw.print("<script>");
				pw.print("	alert('예약 정보가 수정 되었습니다.'); ");
				pw.print(" location.href='"+request.getContextPath()
											+"/Car/update.do?orderid=" + orderid 
														  + "&carimg=" + carimg 
														  + "&memberphone=" + memberphone + "' ");
				pw.print("</script>");
				
				return;
				
			} else {
				pw.print("<script>");
				pw.print("	alert('예약 정보 수정 실패'); ");
				pw.print(" history.back(); ");
				pw.print("</script>");
				
				return;
			}
			
			
		// 예약 삭제를 위해 비밀번호를 입력하는 화면 요청
		} else if(action.equals("/delete.do")) {
// http://localhost:8090/CarProject/Car/delete.do?orderid=6&memberphone=01012123434&center=Delete.jsp
			// 요청한 값 얻기(비밀번호 입력하는 화면 VIEW delete.jsp 얻기
			String center = request.getParameter("center");
			
			// request 내장 객체에 바인딩
			request.setAttribute("center", center); // "Delete.jsp"
			
			nextPage="/CarMain.jsp";
		
		// 예약 (취소)삭제 요청을 받았다면
		} else if(action.equals("/deletePro.do")) {
			
			//요청한 값 얻기
			// 삭제할 아이디, 삭제를 위해 입려한 비밀번호, 삭제 시 사용할 예약자의 핸드폰번호
			int orderid = Integer.parseInt(request.getParameter("orderid"));
			String memberpass = request.getParameter("memberpass");
			String memberphone = request.getParameter("memberphone");
			
			//응답할 값 마련 
			//예약정보를 삭제(취소)하기 위해 CarDAO객체의 OrderDelete메소드 호출할때
			//매개변수로 삭제할 예약아이디와 입력한 비밀번호 전달하여 DB에서 DELETE시키자
			//삭제에 성공하면 OrderDelete 메소드의 반환값은 
			//삭제에 성공한 레코드 개수 1을 반환받고
			//실패하면 0을 반환 받습니다.	
			int result = cardao.OrderDelete(orderid, memberpass);
			
			if(result == 1) {
				pw.print("<script>");
				pw.print("	alert('예약 정보가 취소 되었습니다.'); ");
				pw.print(" location.href='"+request.getContextPath()
											+"/Car/CarReserveConfirm.do?memberpass=" + memberpass 
														  + "&memberphone=" + memberphone + "' ");
				pw.print("</script>");
				
				return;
				
			} else {
				pw.print("<script>");
				pw.print("	alert('예약 정보 취소 실패'); ");
				pw.print(" history.back(); ");
				pw.print("</script>");
				
				return;
			}
			
			
		}
		
		
		// 디스패처 방식 포워딩(재요청)
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
		
	} // doHandle 메소드
}
