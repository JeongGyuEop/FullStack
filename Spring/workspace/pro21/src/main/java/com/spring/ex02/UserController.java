package com.spring.ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

//MVC중에서 C의 역할을 하는 클래스 

//왜 MultiActionController클래스를 상속받아  UserController를 만들어야 하는지?
//-> action-servlet.xml설정파일의 
//   userMethodNameResolver객체의 변수(속성,프로퍼티)를 사용하기 위해 ~~
public class UserController extends MultiActionController {
	/*
	 * 상속받은 methodNameResolver 변수와 setMethodNameResolver메소드가 눈에 보이지 않지안 존재 합니다.
	 */

	/*
	 * /test/loginForm.do 요청 주소를 받으면 test폴더에 만들어 놓은 VIEW 인 ? loginForm.jsp VIEW화면을
	 * 웹브라우저에 보여 주기 위한 기능의 메소드
	 */
	public String loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 클라이언트가 요청한 전체 URL /test/loginForm.do 주에서
		// 확장자 .do를 제외하고 /test/를 제외한 loginForm VIEW 명을 만들어서 반환
		String viewName = getViewName(request);
		      // "loginForm"
		
		System.out.println("viewName : " + viewName);
		
		// 디스패처 서블릿으로 확장자 .jsp를 제외한
		// 뷰이름(loginForm)을 디스패처 서블릿으로 반환
		return viewName;

	}

	// loginForm.jsp에서 입력한 아이디와 비밀번호를 request에 담아서 전달받아
	// 로그인요청시 입력한 정보를 result.jsp VIEW에 보여주는 기능 처리 메소드
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 한글처리
		request.setCharacterEncoding("UTF-8");

		String viewname = getViewName(request);
		// 로그인요청시 입력한 요청한값들(아이디,비밀번호)을 request객체 메모리로부터 얻기
		String userID = request.getParameter("userID");
		String passwd = request.getParameter("passwd");

		// 로그인을 위해 입력한 아이디와 비밀번호 정보가 제대로 입력되었는지
		// 웹브라우저에 출력(응답)해서 보여주기 위해 요청한 값들을 웹브라우저로 응답할값으로 사용합시다.!!
		// 웹브라우저에 응답할 Model을 ModelAndView객체 메모리 생성후 바인딩!
		ModelAndView mav = new ModelAndView();
		// 웹브라우저로 응답할 값 바인딩
		mav.addObject("userID", userID);
		mav.addObject("passwd", passwd);

		// 확장자 .jsp를 제외한 보여줄VIEW명 바인딩
		mav.setViewName(viewname);

		// 디스패처 서블릿(DispatcherServlet)으로 응답할 Model과 응답할VIEW이 바인딩된
		// ModelAndView반환
		return mav;
		/*
		 * 디스패처 서블릿은 action-servlet.xml설정파일에 설정된 InternalResourceViewResolver에게 뷰이름
		 * result를 전달하면 prefix변수에 설정된 뷰가 저장된 경로 -> /test/ 와 suffix변수에 설정된 뷰의 확장자 명 ->
		 * .jsp를 연결해 /test/result.jsp 뷰가 저장된 전체 주소를 만들어서 디스패처 서블릿으로 다시 전달하여 뷰를 찾아 웹브라우저에
		 * 응답(출력)해서 보여줍니다.
		 */
	}

	//
	public String memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 디스패처 서블릿으로 확장자 .jsp를 제외한
		// 뷰이름(memberForm)을 디스패처 서블릿으로 반환
		return getViewName(request);

	}

	public ModelAndView memberInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 한글처리
		request.setCharacterEncoding("UTF-8");

		// 로그인요청시 입력한 요청한값들(아이디,비밀번호)을 request객체 메모리로부터 얻기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		// 로그인을 위해 입력한 아이디와 비밀번호 정보가 제대로 입력되었는지
		// 웹브라우저에 출력(응답)해서 보여주기 위해 요청한 값들을 웹브라우저로 응답할값으로 사용합시다.!!
		// 웹브라우저에 응답할 Model을 ModelAndView객체 메모리 생성후 바인딩!
		// 모델 데이터와 뷰 정보를 함께 반환할 때 사용하는 클래스
		ModelAndView mav = new ModelAndView();
		// 웹브라우저로 응답할 값 바인딩
		mav.addObject("id", id);
		mav.addObject("pwd", pwd);
		mav.addObject("name", name);
		mav.addObject("email", email);

		// 확장자 .jsp를 제외한 보여줄VIEW명 바인딩
		mav.setViewName("memberInfo");

		// 디스패처 서블릿(DispatcherServlet)으로 응답할 Model과 응답할VIEW이 바인딩된
		// ModelAndView반환
		return mav;
		/*
		 * 디스패처 서블릿은 action-servlet.xml설정파일에 설정된 InternalResourceViewResolver에게 뷰이름
		 * result를 전달하면 prefix변수에 설정된 뷰가 저장된 경로 -> /test/ 와 suffix변수에 설정된 뷰의 확장자 명 ->
		 * .jsp를 연결해 /test/result.jsp 뷰가 저장된 전체 주소를 만들어서 디스패처 서블릿으로 다시 전달하여 뷰를 찾아 웹브라우저에
		 * 응답(출력)해서 보여줍니다.
		 */
	}

	// UserController에 getViewName메소드 복사해서 추가 하세요!
	// request 객체에서 URL 요청명을 가져와 .do를 제외한 요청명을 구하는 메소드
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String)request.getAttribute("javax.servlet.include.request_uri");

		if(uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		// http://localhost:8090/pro21/test/loginForm.do로 요청시
		int begin = 0; //
	
		if(!((contextPath==null)||("".equals(contextPath)))){
			begin = contextPath.length(); // 전체 요청명 의 길이를 구함
		}

		int end;
		
		if(uri.indexOf(";")!=-1){
			end=uri.indexOf(";"); //요청 uri에 ';'가 있을 경우 ';'문자 위치를 구함
		}else if(uri.indexOf("?")!=-1){
			end=uri.indexOf("?"); //요청 uri에 '?'가 있을 경우 '?' 문자 위치를 구함
		}else{
			end=uri.length();
		}
		
		//http://localhost:8090/pro21/test/loginForm.do로 요청시
		// 먼저 '.do'를 제거한 http://localhost:8090/pro21/test/loginForm를 구한 후,
		//다시 http://localhost:8090/pro21/test/loginForm에서 역순으로 첫번째 '/' 위치를 구한 후,
		//그 뒤의 listMember를 구한다.
		String fileName=uri.substring(begin,end);
	
		if(fileName.indexOf(".")!=-1){
	
			fileName=fileName.substring(0,fileName.lastIndexOf(".")); //요청명에서 역순으로 최초 '.'의 위치를 구한후, '.do' 앞에까지의 문자열을 구함
	
		}
	
		if(fileName.lastIndexOf("/")!=-1){
	
			fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length()); //요청명에서 역순으로 최초 '/'의 위치를 구한후, '/' 다음부터의 문자열을 구함 
	
		}
		System.out.println("viewName : " + fileName);
	
		return fileName;
	
	} // getViewName 메소드 닫기

}// UserController클래스 MVC 중에 C
