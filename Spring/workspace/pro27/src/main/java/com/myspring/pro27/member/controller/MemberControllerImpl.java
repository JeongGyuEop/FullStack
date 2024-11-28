package com.myspring.pro27.member.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.pro27.member.service.MemberService;
import com.myspring.pro27.member.vo.MemberVO;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
//MVC중에 C  
//사장 


//    /member/login.do

//   /member/logout.do


//  /member/kakaologin.do


@RequestMapping("/member")
//id속성값이 memberController인 
//<bean id="memberController" 
//      class="com.spring.member.controller.MemberControllerImpl">을 자동 생성해 줍니다.
@Controller("memberController")
public class MemberControllerImpl  implements MemberController {

	//LoggerFactory클래스를 이용해  전달한 클래스의 정보를 지닌 Logger객체를 반환해 옴
	private static final Logger logger 
	= LoggerFactory.getLogger(MemberControllerImpl.class);
	
	
	
	//id속성값이 memberService인 
	//<bean id="memberService" 
	//    class="com.spring.member.service.MemberServiceImpl">을 자동 주입 해줍니다.
	@Autowired
	private MemberService memberService; 

	//id속성값이 memberVO인
	//<bean id="memberVO" 
	//      class="com.spring.member.vo.MemberVO">을 자동 주입해 줍니다. 
	@Autowired
	private MemberVO memberVO;
	


	//로그 아웃 요청이 들어오면 호출되는 메소드 
	@Override
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, 
							   HttpServletResponse response) 
											throws Exception {	
		

		  System.out.println("MemberControllerImpl 클래스의 logout 메소드 호출");
		  
		  
		//기존에 만들어져 있던 session메모리 영역 다시 얻기
		HttpSession session = request.getSession();
		//로그아웃 요청 했으므로 session메모리 영역에 저장된  로그인 정보와  조회된 회원정보 삭제 합니다.
		session.removeAttribute("isLogOn"); // true 삭제
		session.removeAttribute("member"); //MemberVO객체 삭제

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/member/listMembers.do");

		return mav;
	} 
	

//	get방식의 요청  -> 웹브라우저 주소창에 주소입력해서 요청 하는 것,  <form>태그의 method=get으로 설정 후 요청하는것.
//				 -> <a>태그를 클릭하여 href주소로 요청 하는것.
//	
//	post방식의 요청 -> <form>태그의 method=post으로 설정 후 요청 하는것, $.ajax메소드로 post설정후 요청 하는것.			 
				 
	@Override
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public ModelAndView login(
							 //1.로그인창에서 입력한 아이디 비밀번호를 request에서 자동으로 꺼내와
							 //MemberVO객체가 생성된 후 MemberVO객체의 각 변수에 자동 저장후
							 //MemberVO객체 자체를 매개변수로 전달 받는다.
							 //2.ModelAndView객체에  setViewName메소드 호출 없이  뷰이름 저장 
							  @ModelAttribute("member") MemberVO member,
							  		
							  //RedirectAttributes클래스를 이용해 로그인 실패시 다시 로그인 창으로
							  //재요청하여 실패 메세지를 전달하게 설정
							  RedirectAttributes rAttr,
							 							  
							  HttpServletRequest request, 
							  HttpServletResponse response) 
					          throws Exception {	
		

		  System.out.println("MemberControllerImpl 클래스의 login 메소드 호출");
		
			ModelAndView mav = new ModelAndView();
			
			//로그인 요청시 입력한 정보를 전달하여 DB작업 명령 합니다.
			//우리가 입력한 정보가 DB에 있는지 없는지 판단해서 회원을 조회 해옴
			memberVO = memberService.login(member);
			
			//memberVO객체로 반환된 값이 있으면 세션영역을 이용해 로그인 한 상태를 true로 저장합니다.
			if(memberVO != null) {
				//session영역 얻기
				HttpSession session = request.getSession();
				//세션에 조회한 회원정보를 저장
				session.setAttribute("member", memberVO);
				//세션에 로그인한 상태 메세지를 보여주기 위한 조건값 true 저장
				session.setAttribute("isLogOn", true);
				//웹브라우저를 거쳐 모든회원 조회 재요청!
				mav.setViewName("redirect:/member/listMembers.do");
				
			//우리가 입력한 아이디와 비밀번호에 관한 회원이 DB에 없으면?	
			}else {
				//로그인 실패시 실패 메세지를 로그인창으로 전달 합니다. 
				rAttr.addAttribute("result","loginFailed");
				
				//로그인 실패시 다시 ~ loginForm.jsp로그인창 화면을  재요청 합니다. 
				mav.setViewName("redirect:/member/loginForm.do");
			}
			
			return mav;
		
	}
	
	
	// /member/listMembers.do DB에 저장된 모든 회원 조회 요청 주소를 받았을때 호출 되는 메소드로
	
	@Override
	@RequestMapping(value="/listMembers.do", method=RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, 
									HttpServletResponse response) 
											throws Exception {	
		System.out.println();
		System.out.println("================================================================");
		  System.out.println("MemberControllerImpl 클래스의 listMembers 메소드 시작");
		  System.out.println();
		
	//요청한 주소를 이용해 응답할 값을 마련
		//부장 MemberServiceImpl객체의 listMembers()메소드를 호출하여
		//모든 회원 조회 요청을 명령함!
		//웹브라우저로 응답할 조회한 정보들이 담긴  List배열을 반환 받는다.
		System.out.println("MemberServiceImpl 클래스의 listMembers 메소드 호출");
		List membersList = memberService.listMembers();
		System.out.println("MemberServiceImpl 클래스의 listMembers 메소드에서 반환 받은 값 : " + membersList);
	//응답할 뷰 이름 얻기 	
		//요청 URL주소  /member/listMembers.do 에서  .do를 제외한 /listMembers뷰이름얻기
//		String viewName = getViewName(request);   //주석처리~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		//개발자가 직접 만든 인터셉터 기능을 하는  ViewNameInterceptor 클래스 내부의
		//preHandle 메소드 안에 작성 해 놓은 
		//String viewName = getViewName(request);
		//request.setAttribute("viewName", viewName);
		//위 코드에 request에 바인딩된 뷰이름 얻기 
		String viewName = (String)request.getAttribute("viewName"); //  /listMembers
		
	//	System.out.println(viewName);
		//Logger클래스의 info메소드를 이용해 로그 메세지 레벨을 info로 설정 하여 출력 합니다.
		logger.info("info 레벨 : viewName = " + viewName);
		//Logger클래스의 debug메소드를 이용해 로그 메세지 레벨을 debug로 설정 하여 출력 합니다.
		logger.debug("debug 레벨 : viewName = " + viewName);
		
		
		
	//응답할 값 과 응답할 뷰 이름을  ModelAndView객체 메모리에 바인딩(저장)
		ModelAndView mav = new ModelAndView();
					 //응답할 데이터 저장
					 mav.addObject("membersList", membersList);	
					//뷰 이름 저장 
					 mav.setViewName(viewName);

		System.out.println("MemberControllerImpl 클래스의 listMembers 메소드에서 바인딩하는 뷰명 : " + viewName);
					 
		System.out.println();
		System.out.println("MemberControllerImpl 클래스의 listMembers 메소드 종료");
		System.out.println("================================================================");
		System.out.println();
		return mav;//디스팩처 서블릿으로 ModelAndView객체 반환 
		
	} 
	
	
//  /loginForm.do	
	
//	/memberForm.do
	//여러 요청주소에 대해 한개의 메소드를 호출할 경우 정규식을 이용해 매핑하는 역할을 합니다. 
	//요청한 주소가 Form.do로 끝나는 주소로 요청하면 Form메소드가 호출되게 작성 
	@Override     
	@RequestMapping(value="/*Form.do", method= {RequestMethod.GET,RequestMethod.POST} )
	public ModelAndView       Form(
								   //로그인 창 요청시 result의 값이 전송되면 String result매개변수에 값이 저장되어 넘어 옵니다.
								   //로그인창 화면 loginForm.jsp를 최초에 한번 요청했을때는 result의 값이 전송되지 않으므로
								   //무시 합니다.
								   @RequestParam(value="result", required=false) String result,
								   HttpServletRequest request, 
							       HttpServletResponse response) 
									throws Exception {	

		  System.out.println("MemberControllerImpl 클래스의 Form 메소드 호출");
		
//    아래 줄 주석 처리~~~
//		String viewName = getViewName(request); //   /memberForm   
											   //   /loginForm
	
		//개발자가 직접 만든 인터셉터 기능을 하는  ViewNameInterceptor 클래스 내부의
		//preHandle 메소드 안에 작성 해 놓은 
		//String viewName = getViewName(request);
		//request.setAttribute("viewName", viewName);
		//위 코드에 request에 바인딩된 뷰이름 얻기 
		String viewName = (String)request.getAttribute("viewName");   //  /loginForm
		
		
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("result", result); //로그인 실패시 메세지 저장 "loginFailed"
		
		mav.setViewName(viewName); // /loginForm
		
		return mav;
	}
	
//	public String memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//		String viewName = getViewName(request); //  /member/memberForm.do  ->  memberForm
//		
//		 return viewName;
//	}
	
	// 회원가입 요청 주소 /member/addMember.do를 받았을때....
	@Override
	@RequestMapping(value="/addMember.do", method=RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member,
								  HttpServletRequest request, 
								  HttpServletResponse response) 
										  throws Exception {	

		  System.out.println("MemberControllerImpl 클래스의 addMember 메소드 호출");
		
		request.setCharacterEncoding("UTF-8");
			
		
//		request.getParameter("")
						
//		System.out.println("kakaoid : " + request.getParameter("kakaoid").getClass().getName());
		System.out.println("kakaoid : " + request.getParameter("kakaoid"));
	
		//부장 MemberServiceImpl객체의 메소드 호출시 vo를 전달하여 INSERT명령!
		memberService.addMembers(member);	
		
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
			
		//회원가입 후 모든회원을 조회 하는 재요청 주소 작성 
		return mav;
 
	}
	
	//회원삭제 기능 
	///member/memberDel.do
	@Override
	@RequestMapping(value="/memberDel.do", method=RequestMethod.GET)
	public ModelAndView memberDel(@RequestParam("id") String id,
									HttpServletRequest request, 
									HttpServletResponse response) 
									throws Exception {
			

		  System.out.println("MemberControllerImpl 클래스의 memberDel 메소드 호출");
		  
		  
		request.setCharacterEncoding("UTF-8");

	
		//부장 MemberServiceImpl객체의 메소드 호출시 vo를 전달하여 DELETE명령!
		memberService.delMembers(id);		 
			
		//회원 삭제후 모든회원을 조회 하는 재요청 주소 작성 
		return new ModelAndView("redirect:/member/listMembers.do");
	}
	
	//회원정보  수정을 위해 회원 한명의 정보 조회 기능
	// 수정링크를 누르면 요청 주소   ->  /member/memberDetail.do
	@Override
	@RequestMapping(value="/memberDetail.do", method=RequestMethod.GET)
	public ModelAndView memberDetail(HttpServletRequest request, 
									 HttpServletResponse response) 
											 throws Exception {

		  System.out.println("MemberControllerImpl 클래스의 memberDetail 메소드 호출");
		
		request.setCharacterEncoding("UTF-8");
			
		//요청한 값(수정할 회원을 조회 하기 위한 아이디값) 얻기
		String id = request.getParameter("id");
		
		//부장 MemberServiceImpl객체의 메소드 호출시 vo를 전달하여 SELECT명령!
		MemberVO vo = memberService.detailMembers(id);		 
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("membervo",vo);
		
		
		//개발자가 직접 만든 인터셉터 기능을 하는  ViewNameInterceptor 클래스 내부의
		//preHandle 메소드 안에 작성 해 놓은 
		//String viewName = getViewName(request);
		//request.setAttribute("viewName", viewName);
		//위 코드에 request에 바인딩된 뷰이름 얻기 
		String viewName = (String)request.getAttribute("viewName");
		
		mav.setViewName( viewName ); // /memberDetail
	 
		
		
		
		return mav;
		
		
	}
	
	//수정 요청 /member/UpdateMember.do 주소를 받았을때
	@Override
	@RequestMapping(value="/UpdateMember.do", method=RequestMethod.POST)
	public ModelAndView UpdateMember(@ModelAttribute("member") MemberVO member,
									 HttpServletRequest request, 
									 HttpServletResponse response) throws Exception {


		  System.out.println("MemberControllerImpl 클래스의 UpdateMember 메소드 호출");
		
		request.setCharacterEncoding("UTF-8");
		
		//부장 MemberServiceImpl객체의 메소드 호출시 수정할 id를 전달하여 UPDATE명령!
		memberService.UpdateMember(member);		 
			
		//회원 수정후 모든회원을 조회 하는 재요청 주소 작성 
		return  new ModelAndView("redirect:/member/listMembers.do");
		
	
	}


	 
/*
참고.  인터셉터 기능 사용을 위해!
개발자가 직접 만든 인터셉터 기능을 하는  ViewNameInterceptor 클래스 내부에 
private String getViewName(HttpServletRequest request) throws Exception {..}메소드를
만들어 놓고!!!!!! preHandle 메소드 안에서   String viewName = getViewName(request); 와 같이 호출 해서 
사용하므로!! 아래에 작성한 getViewName메소드는 주석 처리 함.
 
	
	//request 객체에서 URL 요청명을 가져와 .do를 제외한 요청명을 구하는 메소드 
	private  String getViewName(HttpServletRequest request) throws Exception {
	      
		  String contextPath = request.getContextPath();
	      // /pro21
		  String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
	      
		  if(uri == null || uri.trim().equals("")) {
	       //요청한 주소 전체 얻기 
	         uri = request.getRequestURI();
	       //http://localhost:8090/pro21/test/memberForm.do
	         
//	         System.out.println(uri);
	      }
	      int begin = 0;
	      if(!((contextPath==null)||("".equals(contextPath)))){
	         begin = contextPath.length();
	      }
	      int end;
	      if(uri.indexOf(";")!=-1){
	         end=uri.indexOf(";");
	      }else if(uri.indexOf("?")!=-1){
	         end=uri.indexOf("?");
	      }else{
	         end=uri.length();
	      }
	      String fileName=uri.substring(begin,end);
	
	      if(fileName.indexOf(".")!=-1){
	         fileName=fileName.substring(0,fileName.lastIndexOf("."));
	      }
	      if(fileName.lastIndexOf("/")!=-1){
	         fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length());
	         fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length());
		      
	      }
	      return fileName; // .do를 제외한 요청주소를 리턴
	   }

*/




	
}
