package com.spring.ex02;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



//<bean id="loginController"  class="com.spring.ex02.LoginController" /> 빈을 자동생성해서
// 톰캣 컨테이너 메모리에 보관 해 둔다.
@Controller("loginController")
public class LoginController {

	//  http://localhost:8090/pro26/test/loginForm.do주소를 입력하여 요청하면
	//	아이디 비밀번호 입력하는 VIEW화면 요청하면 호출되는 loginForm메소드 입니다.
	//  이때 method = {RequestMethod.GET} 를 설정하면 GET방식으로 요청을 하면 메소드가 호출됩니다.
	@RequestMapping(value = {"/test/loginForm.do","/test/loginForm2.do"}, method = {RequestMethod.GET})
	public ModelAndView loginForm(HttpServletRequest request, 
								  HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		//요청한 클라이언트의 웹브라우저에 보여줄 VIEW명을 ModelAndView객체를 생성해서 저장하자
		//그리고 ModelAndView객체를 DispatcherServet으로 반환 해서 
		//InternalResourceViewResource에게 loginForm을 전달해서 뷰의 전체 경로를 만들어서 찾아감
		return new ModelAndView("loginForm");
	}
	
	
	//loginForm.jsp화면에서 아이디와 이름을 입력받고 로그인 버튼을 눌렀을때
	//<form>의 action속성에 적힌 요청주소 /test/login.do를 POST방식으로 요청받으면 호출되는 login메소드
	@RequestMapping(value = {"/test/login.do"}, method = {RequestMethod.POST})
		public ModelAndView login(HttpServletRequest request, 
								  HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		//클라이언트가 입력하여 요청한 값 2개를 request메모리에서 얻기
		String userID = request.getParameter("userID"); //입력한 아이디 얻기 
		String userName = request.getParameter("userName"); //입력한 이름 얻기 
		
		//클라이언트가 요청한 웹브라우저로 Model데이터 그리고 응답할 VIEW(JSP)명을 저장할 용도의 객체 생성
		ModelAndView mav = new ModelAndView();
		//응답할 Model데이터 바인딩(키-값 묶어서 저장)
					 mav.addObject("userID", userID);//입력한 아이디 저장 
					 mav.addObject("userName", userName);//입력한 이름 저장
		//웹브라우저에 보여줄 VIEW명 (확장자 .jsp를 제외한 jsp명)을  저장
					 mav.setViewName("result");
				
		return  mav;
		
	}
	
	
	
	
	/*
	지금 까지는 요청한 값들(매개변수들)을 getParameter메소드를 이용해 값을 얻었습니다.
	그런데 전송되어온 매개변수의 수가 많아지면 
	일일이  getParameter()메소드를 이용하는 방법은 불편합니다.
	@RequestParam어노테이션 기호를 매개변수에 작성해서 사용하면 쉽게 요청한 값들을 매개변수로 받을수 있다.
	
	loginForm.jsp에서 아이디 입력 폼 <input>의 name속성의 값을 
	@RequestParam(name속성값) String userID 이렇게 적어 주면 매개변수로 바로 전달 받습니다.  
	
	@RequestParam의 required속성 사용하기 
			로그인하는 경우 ID와 비밀번호 같은 정보는 반드시 컨트롤러로 전달되어야 합니다.
			위 required속성을 사용하면 반드시 전달해야하는 필수 요청값인 경우와 그렇지 않은 경우를 설정할수 있다.
			1. @RequestParam적용시 required속성을 생략하면 기본값은 true로 설정 된다.
			2. required속성을 true로 설정하면 메소드 호출시 반드시 지정한 이름의 매개변수로 전달 해야 합니다.
			   (loginForm.jsp에 <input type="hidden" name="email"...>태그를 반드시 작성해서 전달 해야함)
			3. required속성을 false로 설정하면 메소드 호출시 요청한 매개변수가 전달 되면
			   값을 저장하고 없으면 null을 할당 합니다.    
	
	
	
	*/
		
		//loginForm.jsp화면에서 아이디와 이름을 입력받고 로그인 버튼을 눌렀을때
		//<form>의 action속성에 적힌 요청주소 /test/login2.do를 POST방식으로 요청받으면 호출되는 login2메소드
		@RequestMapping(value = {"/test/login2.do"}, method = {RequestMethod.POST})
			public ModelAndView login2(@RequestParam(value="email", required = false) String email,  
									   @RequestParam(value="userID", required = true) String userID,
									   @RequestParam(value="userName", required = true) String userName,
					
									   HttpServletRequest request, 
									   HttpServletResponse response) throws Exception {
			
			request.setCharacterEncoding("UTF-8");
		
			System.out.println("userID : " + userID);
			System.out.println("userName : " + userName);
			System.out.println("email : " + email);
			
			
			
			//클라이언트가 요청한 웹브라우저로 Model데이터 그리고 응답할 VIEW(JSP)명을 저장할 용도의 객체 생성
			ModelAndView mav = new ModelAndView();
			//응답할 Model데이터 바인딩(키-값 묶어서 저장)
						 mav.addObject("userID", userID);//입력한 아이디 저장 
						 mav.addObject("userName", userName);//입력한 이름 저장
			//웹브라우저에 보여줄 VIEW명 (확장자 .jsp를 제외한 jsp명)을  저장
						 mav.setViewName("result");
					
			return  mav;
			
		}
		
	/*
		@RequestParam 이용해 Map에 요청한 값 얻어 설정 하기 
		- 요청하여 전송되는 값들이 많을 경우 일일이  @RequestParam으로 매개변수를 만들어 놓고 사용하면
		    불편합니다. 요청한값들을 자동으로 Map에 저장 후 매개변수로 전달 받겠습니다.
	
	*/	
		@RequestMapping(value = {"/test/login3.do"}, method = {RequestMethod.POST})
		public ModelAndView login3(
								   //매개변수이름 이 info인 Map에 
								   //<input>태그의 name값을 key로  입력한값을  value로 해서
								   //자동 저장후 매개변수로 Map을 전달 받습니다.
								   @RequestParam Map<String, String> info,
				
								   HttpServletRequest request, 
								   HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
	
		//입력한 아이디, 이름 확인
		String userID = info.get("userID");//<input>태그의 name값이 key기 때문에 넣어 준다
		String userName = info.get("userName");//<input>태그의 name속성에 적힌 값이 key기때문에 넣어준다
		
		System.out.println("userID : " + userID);
		System.out.println("userName : " + userName);
		
		
		
		//클라이언트가 요청한 웹브라우저로 Model데이터 그리고 응답할 VIEW(JSP)명을 저장할 용도의 객체 생성
		ModelAndView mav = new ModelAndView();
		//응답할 Model데이터 바인딩(키-값 묶어서 저장)
					 mav.addObject("info", info);//매개변수 info로 받은 Map자체를 바인딩	
		//웹브라우저에 보여줄 VIEW명 (확장자 .jsp를 제외한 jsp명)을  저장
					 mav.setViewName("result");
				
		return  mav;
		
	}

/*	
	@ModelAttribute 어노테이션 기호를 사용해 VO에 요청한 값 자동으로 설정하기 
								   
	@ModelAttribute("info") LoginVO loginVO 매개변수에 대해...
	LoginVO클래스에 대한 객체를 생성합니다. 이어서 <input>태그의 name속성값과 
	같은 LoginVO의 변수명에 관한 변수에 자동으로 값을 설정한 후 매개변수로 
	LoginVO객체 자체를 전달 받습니다. 
	
	예를 들어 loginForm.jsp(로그인창)에서  입력한 <input>태그의 name속성값이 userID이고
	입력한 값이 hong일경우  @ModelAttribute 로  LoginVO loginVO매개변수를 지정하면
	LoginVO객체가 자동으로 생성되고 userID변수에 입력한 값hong이 자동 저장되어 설정됩니다.  
	
	
	@ModelAttribute("info") LoginVO loginVO 라고 적어주면
	
	우리가 ModelAndView객체를 생성하여 LoginVO객체 저장시!! 작성 했던
	 mav.addObject("info", loginVO); 구문을 자동으로 해줍니다. 
	
*/			
		
		
	@RequestMapping(value = {"/test/login4.do"},method = {RequestMethod.POST})
	public ModelAndView login4(@ModelAttribute("info") LoginVO loginVO) 
							  throws Exception {
	
	//클라이언트가 요청한 웹브라우저로 Model데이터 그리고 응답할 VIEW(JSP)명을 저장할 용도의 객체 생성
	ModelAndView mav = new ModelAndView("result");
	//응답할 Model데이터 바인딩(키-값 묶어서 저장)
			   //mav.addObject("info", loginVO); <- 자동으로 작성해 준다.
			
	return  mav;
	
	}	

/*
	Model클래스 이용해 웹브라우저로 응답할 값 전달하기
	- Model클래스를 이용하면 login5메소드 호출시 JSP로 값을 바로 바인딩하여 전달할수 있습니다.
	- Model클래스의 addAttribute("속성명","응답할값")메소드는
	  ModelAndView클래스의 addObject("속성명","응답할값")메소드와 같은 기능을 합니다.
	  Model클래스는 따로 뷰정보를 전달할 필요가 없을때 사용하면 편리 합니다. 
	
	http://localhost:8090/pro26/test/login5.do를 입력해서 GET방식으로 요청하면 호출되는 login5메소드
*/
	@RequestMapping(value = {"/test/login5.do"}, method = {RequestMethod.GET})
	public String  login5(Model model) throws Exception {
						  										
		//매개변수로 전달 받는 Model객체에 클라이언트의 웹브라우저로 응답할 값을 임시로 저장(바인딩)
		model.addAttribute("userID", "hong");
		model.addAttribute("userName", "홍길동");
		
		return "result";//뷰명을 디스패처서블릿으로 반환
	
	}		
			
			
	
}//LoginController클래스 














