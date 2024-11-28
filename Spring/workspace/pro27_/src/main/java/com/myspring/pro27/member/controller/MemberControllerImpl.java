package com.myspring.pro27.member.controller;

import java.util.List;

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


@RequestMapping("/member")
//id속성값이 memberController인 
//<bean id="memberController" 
//      class="com.spring.member.controller.MemberControllerImpl">을 자동 생성해 줍니다.
@Controller("memberController")
public class MemberControllerImpl  implements MemberController {

	//LoggerFactory클래스를 이용해  전달한 클래스의 정보를 지닌 Logger객체를 반환해 옴
	private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);
	
	
	
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
	
	
	
	// /member/listMembers.do DB에 저장된 모든 회원 조회 요청 주소를 받았을때 호출 되는 메소드로
	
	@Override
	@RequestMapping(value="/listMembers.do", method=RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, 
									HttpServletResponse response) 
											throws Exception {	
		
	//요청한 주소를 이용해 응답할 값을 마련
		//부장 MemberServiceImpl객체의 listMembers()메소드를 호출하여
		//모든 회원 조회 요청을 명령함!
		//웹브라우저로 응답할 조회한 정보들이 담긴  List배열을 반환 받는다.
		List membersList = memberService.listMembers();
	
	//응답할 뷰 이름 얻기 	
		//요청 URL주소  /member/listMembers.do 에서  .do를 제외한 /listMembers뷰이름얻기
		String viewName = getViewName(request); 
		
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
		
		return mav;//디스팩처 서블릿으로 ModelAndView객체 반환 
		
	} 
	
	
//	/memberForm.do
	//여러 요청주소에 대해 한개의 메소드를 호출할 경우 정규식을 이용해 매핑하는 역할을 합니다. 
	//요청한 주소가 Form.do로 끝나는 주소로 요청하면 Form메소드가 호출되게 작성 
	@Override     
	@RequestMapping(value="/*Form.do", method= {RequestMethod.GET,RequestMethod.POST} )
	public ModelAndView Form( @RequestParam(value="result", required = false) String result,
							  HttpServletRequest request, 
							  HttpServletResponse response) 
									  		throws Exception {	
		
		String viewName = getViewName(request); //   /memberForm
												//  /loginForm
		
		ModelAndView mav = new ModelAndView();
					 mav.addObject("result",result);
					 mav.setViewName(viewName);
		
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
		
		request.setCharacterEncoding("UTF-8");
			
						 
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
		
		request.setCharacterEncoding("UTF-8");
			
		//요청한 값(수정할 회원을 조회 하기 위한 아이디값) 얻기
		String id = request.getParameter("id");
		
		//부장 MemberServiceImpl객체의 메소드 호출시 vo를 전달하여 SELECT명령!
		MemberVO vo = memberService.detailMembers(id);		 
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("membervo",vo);
		mav.setViewName( getViewName(request) ); // /memberDetail
	 
		return mav;
		
		
	}
	
	//수정 요청 /member/UpdateMember.do 주소를 받았을때
	@Override
	@RequestMapping(value="/UpdateMember.do", method=RequestMethod.POST)
	public ModelAndView UpdateMember(@ModelAttribute("member") MemberVO member,
									 HttpServletRequest request, 
									 HttpServletResponse response) throws Exception {

		
		request.setCharacterEncoding("UTF-8");
		
		//부장 MemberServiceImpl객체의 메소드 호출시 수정할 id를 전달하여 UPDATE명령!
		memberService.UpdateMember(member);		 
			
		//회원 수정후 모든회원을 조회 하는 재요청 주소 작성 
		return  new ModelAndView("redirect:/member/listMembers.do");
		
	
	}
	
	@RequestMapping(value="/login.do", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member")  MemberVO member,
							  RedirectAttributes rAttr,
							  HttpServletRequest  request,
							  HttpServletResponse response
							 ) throws Exception {
							 //RedirectAttributes클래스를 이용해 로그인 실패시 
							 // 다시 로그인창으로 리다이렉트 재요청하여 실패 메세지를 전달 합니다.
							
		
		ModelAndView mav = new ModelAndView();
		
		//서비스 부장의 login메소드를 호출하면서 로그인처리를 위해 입력한 아이디와 비밀번호를 전달
		memberVO = memberService.login(member);
		
		if(memberVO != null) {//로그인 요청시 입력한 아이디와 비밀번호가 DB에 저장되어 있으면?
							  //즉! MemberVO객체를 반환받으면?
			HttpSession session = request.getSession();//세션을 만들어서 
			session.setAttribute("member", memberVO); //세션에 로그인한 사람의 회원정보 저장
			session.setAttribute("isLogOn", true); //세션에 로그인된 상태의 값을 true로 저장 
			mav.setViewName("redirect:/member/listMembers.do");
			
		}else {//로그인 요청시 입력한 아이디와 비밀번호가 DB에 저장되어 있지 않으면?
			   //즉! MemberVO객체를 반환 받지 못하면?
			
			//로그인 실패시 실패 메세지를 로그인창으로 전달합니다.
			rAttr.addAttribute("result", "loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");	
		}
		
		return mav;
	}
	
	
	@RequestMapping(value="/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest  request,
							   HttpServletResponse response
							   ) throws Exception {
		
		HttpSession session = request.getSession();//기존 세션을 얻어서 
		//세션에 저장되어 있던 MemberVO객체와  isLogOn true 를 삭제 
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		
		ModelAndView mav = new ModelAndView();
					 mav.setViewName("redirect:/main.do");
	
		return mav;
	}
	
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






	
}
