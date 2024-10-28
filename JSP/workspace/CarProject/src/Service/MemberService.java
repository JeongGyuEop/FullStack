package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Dao.MemberDAO;
import Vo.MemberVo;

// 부장
// - 단위 기능 별로 메소드를 만들어서 그 기능을 처리하는 클래스
public class MemberService {
	
	// MemberDAO 객체의 주소를 저장할 참조변수
	MemberDAO memberDao;
	
	// 기본 생성자 - 위 memberDao변수에 new MemberDAO() 객체를 만들어서 저장하는 역할
	public MemberService() {
		memberDao = new MemberDAO();
	}
	
	//-------
	// 회원가입 중앙화면 VIEW 요청
	public String serviceJoinName(HttpServletRequest request) {
		// members/join.jsp 중앙화면 뷰 주소를 얻어 MemberController로 반환
		return request.getParameter("center");
	}

	//-------
	// 아이디 중복 체크 요청
	public boolean serviceOverLappedId(HttpServletRequest request) {

		// 요청한 아이디 얻기
		String id = request.getParameter("id");
		
		// 입력한 아이디가 DB에 저장되어 있는지 확인하는 작업을 MemberDAO의 메소드를 호출해서 명령
		return memberDao.overlappedId(id);
				// true 또는 false를 반환받아 다시 MemberController에 반환
	}

	//-------
	// 회원등록(가입) 요청
	public int serviceInsertMember(HttpServletRequest request) {

		// 요청한 값 얻기
		String user_id = request.getParameter("id");
		String user_pass = request.getParameter("pass");
		String user_name = request.getParameter("name");
		int user_age = Integer.parseInt(request.getParameter("age"));
		String user_gender = request.getParameter("gender");
		
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String address3 = request.getParameter("address3");
		String address4 = request.getParameter("address4");
		String address5 = request.getParameter("address5");
		String user_address = address1 + address2 + address3 + address4 + address5;
		
		String user_email = request.getParameter("email");
		String user_tel = request.getParameter("tel");
		String user_hp = request.getParameter("hp");
		
		MemberVo vo = new MemberVo(user_id, user_pass, user_name,
								user_age, user_gender, user_address, user_email, user_tel, user_hp);
		
		return memberDao.insertMember(vo);
		
	}

	
	//-------
	// 로그인을 하기 위해 아이디 비밀번호를 입력할 수 있는 중앙화면 VIEW 요청
	public String serviceLoginMember() {
		
		return "members/login.jsp";
		
	}

	//-------
	// 로그인 요청
	public int serviceUserCheck(HttpServletRequest request) {

		// 요청한 값 얻기
		String login_id = request.getParameter("id");
		String login_pass = request.getParameter("pass");
		
		// check 변수값이 1이면 입력한 아이디, 비밀번호가 DB에 존재함
		
		// HttpSession 메모리 얻기
		HttpSession session = request.getSession();
		// HttpSession 메모리에 입력한 아이디 바인딩
		session.setAttribute("id", login_id);
		
		return memberDao.userCheck(login_id, login_pass);

	}

	//-------
	// 로그아웃 요청
	public void serviceLogOut(HttpServletRequest request) {

		// 기존에 생성했던 HttpSession 객체 메모리 얻기
		HttpSession session = request.getSession();
		session.removeAttribute("id");
		
	}
	

}
