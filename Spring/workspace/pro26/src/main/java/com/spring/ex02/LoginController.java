package com.spring.ex02;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// <bean id="logincontroller" class="com.spring.ex02.loginController" /> 빈을 자동생성해서 
// 톰캣 컨테이너 메모리에 보관해 둔다.
@Controller("loginController")
public class LoginController {

	// http://localhost:8090/pro26/test/loginForm.do 주소를 입력하여 요청하면
	// 아이디 비밀번호를 입력하는 VIEW 화면 요청
	// 이때 method={RequestMethod.GET}을 설정함녀 GET 방식으로 요청하면 메소드가 호출된다.
	@RequestMapping(value = {"/test/loginForm.do", "/test/loginForm2.do"}, 
					method = { RequestMethod.GET })
	public ModelAndView loginForm(HttpServletRequest request, 
								  HttpServletRequest response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		// 요청한 클라이언트의 웹브라우저에 보여줄 VIEW 명을 ModelAndView객체를 생성해서 저장하자
		// 그리고 ModelAndView 객체를 디스패처 서블릿으로 반환해서
		// InternalResourceViewResource에게 loginForm을 전달해서 뷰의 전체 경로를 만들어서 찾아간다.
		return new ModelAndView("loginForm");
	}
	
	
	// loginForm.jsp 화면에서 아이디와 이름을 입력받고 로그인 버튼을 눌렸을때
	// <form>의 action 속성에 적힌 요청주소 /test/login.do를 POST 방식으로 요청받으면 호출되는 login 메소드
	@RequestMapping(value = {"/test/login.do"}, 
			method = { RequestMethod.POST})
	public ModelAndView login(HttpServletRequest request, 
							  HttpServletRequest response) throws Exception {
	
		request.setCharacterEncoding("UTF-8");
		
		// 클라이언트가 입력하여 요청한 값 2개를 request 메모리에서 얻기
		String userID = request.getParameter("userID"); // 입력한 아이디 얻기
		String userName = request.getParameter("userName"); // 입력한 이름 얻기
		
		// 클라이언트가 요청한 우베 브라우저로 Model 데이터 그리고 응답할 VIEW(JSP) 명을 저장할 
		// 용도의 객체 생성
		ModelAndView mav = new ModelAndView();
		
		// 응답할 Model 데이터 바인딩(키-값 묶어서 저장)
		mav.addObject("userID", userID); // 입력한 아이디 저장
		mav.addObject("userName", userName); // 입력한 이름 저장
		
		mav.setViewName("result");
		
		return mav;
	}
	
	
	// loginForm.jsp 화면에서 아이디와 이름을 입력받고 로그인 버튼을 눌렸을때
	// <form>의 action 속성에 적힌 요청주소 /test/login2.do를 POST 방식으로 요청받으면 호출되는 login2 메소드
	@RequestMapping(value = {"/test/login2.do"}, 
			method = { RequestMethod.POST})
	public ModelAndView login2(@RequestParam(value="userID", required = true) String userID, 
							   @RequestParam(value="userName", required = true) String userName,
							   @RequestParam(value="email", required = false ) String email,				   
							   HttpServletRequest request, HttpServletRequest response) throws Exception {
	
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("userId : " + userID);
		System.out.println("userName : " + userName);
		System.out.println("email : " + email);
		// 클라이언트가 요청한 우베 브라우저로 Model 데이터 그리고 응답할 VIEW(JSP) 명을 저장할 
		// 용도의 객체 생성
		ModelAndView mav = new ModelAndView();
		
		// 응답할 Model 데이터 바인딩(키-값 묶어서 저장)
		mav.addObject("userID", userID); // 입력한 아이디 저장
		mav.addObject("userName", userName); // 입력한 이름 저장
		
		mav.setViewName("result");
		
		return mav;
	}

	// loginForm.jsp 화면에서 아이디와 이름을 입력받고 로그인 버튼을 눌렸을때
	// <form>의 action 속성에 적힌 요청주소 /test/login3.do를 POST 방식으로 요청받으면 호출되는 login3 메소드
	@RequestMapping(value = {"/test/login3.do"}, 
				    method = { RequestMethod.POST})
	public ModelAndView login3(@RequestParam Map<String, String> info,
							   HttpServletRequest request, HttpServletRequest response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
	
		// 입력한 아이디, 이름 확인
		String userID = info.get("userID"); // <input> 태그의 name값이 key기 때문에 넣어준다.
		String userName = info.get("userName"); // <input> 태그의 name 속성에 적힌 값이 key기 때문에 넣어준다.
		
		System.out.println(userID);
		System.out.println(userName);
	
		// 클라이언트가 요청한 웹 브라우저로 Model 데이터 그리고 응답할 VIEW(JSP) 명을 저장할 
		// 용도의 객체 생성
		ModelAndView mav = new ModelAndView();
		
		// 응답할 Model 데이터 바인딩(키-값 묶어서 저장)
		mav.addObject("info", info); // 매개변수 info로 받은 Map 자체를 바인딩
			
		mav.setViewName("result");
			
		return mav;
	}
	
	
	// loginForm.jsp 화면에서 아이디와 이름을 입력받고 로그인 버튼을 눌렸을때
	// <form>의 action 속성에 적힌 요청주소 /test/login3.do를 POST 방식으로 요청받으면 호출되는 login3 메소드
	@RequestMapping(value = {"/test/login4.do"}, 
				    method = { RequestMethod.POST})
	public ModelAndView login4(@ModelAttribute("info") LoginVO loginVO,
							   HttpServletRequest request, HttpServletRequest response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("userID : " + loginVO.getUserID());
		System.out.println("userName : " + loginVO.getUserName());
	
		// 클라이언트가 요청한 우베 브라우저로 Model 데이터 그리고 응답할 VIEW(JSP) 명을 저장할 
		// 용도의 객체 생성
		ModelAndView mav = new ModelAndView();
			
		mav.setViewName("result");
			
		return mav;
	}
}
