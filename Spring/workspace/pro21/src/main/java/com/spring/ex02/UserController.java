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
상속받은  methodNameResolver 변수와  
        setMethodNameResolver메소드가 눈에 보이지 않지안 존재 합니다.
*/	
		
/*
	/test/loginForm.do 요청 주소를 받으면
	test폴더에 만들어 놓은 VIEW 인 ? loginForm.jsp  
	VIEW화면을 웹브라우저에 보여 주기 위한 기능의 메소드 
*/	
	public String loginForm(HttpServletRequest request,
							HttpServletResponse response) 
									throws Exception {
		
		//디스패처 서블릿으로 확장자 .jsp를 제외한
		//뷰이름(loginForm)을 디스패처 서블릿으로 반환
		return "loginForm";
		
	}
	
	//loginForm.jsp에서 입력한 아이디와 비밀번호를 request에 담아서 전달받아
	//로그인요청시 입력한 정보를 result.jsp VIEW에 보여주는 기능 처리 메소드 
	public ModelAndView login(HttpServletRequest request,
							  HttpServletResponse response) 
									throws Exception {

		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		//로그인요청시 입력한 요청한값들(아이디,비밀번호)을 request객체 메모리로부터 얻기
		String userID = request.getParameter("userID");
		String passwd = request.getParameter("passwd");
		
		//로그인을 위해 입력한 아이디와 비밀번호 정보가 제대로 입력되었는지 
	    //웹브라우저에 출력(응답)해서 보여주기 위해 요청한 값들을 웹브라우저로 응답할값으로 사용합시다.!!
		//웹브라우저에 응답할 Model을 ModelAndView객체 메모리 생성후 바인딩!
		ModelAndView mav = new ModelAndView();
					//웹브라우저로 응답할 값 바인딩
					 mav.addObject("userID", userID);
					 mav.addObject("passwd", passwd);
		
					//확장자 .jsp를 제외한 보여줄VIEW명 바인딩
					 mav.setViewName("result");
		
		//디스패처 서블릿(DispatcherServlet)으로 응답할 Model과 응답할VIEW이 바인딩된
		//ModelAndView반환 
		return mav;
	/*
		디스패처 서블릿은 action-servlet.xml설정파일에 설정된
		InternalResourceViewResolver에게  뷰이름 result를 전달하면
		prefix변수에 설정된 뷰가 저장된 경로 -> /test/ 와 
		suffix변수에 설정된 뷰의 확장자 명 ->  .jsp를 연결해 
		/test/result.jsp 뷰가 저장된 전체 주소를 만들어서 
		디스패처 서블릿으로 다시 전달하여 뷰를 찾아 웹브라우저에 응답(출력)해서 보여줍니다. 
	*/	
	}	
	
}//UserController클래스   MVC 중에 C























