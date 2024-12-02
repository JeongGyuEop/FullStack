package com.bookshop01.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.common.base.BaseController;
import com.bookshop01.member.service.MemberService;
import com.bookshop01.member.vo.MemberVO;





@Controller("memberController")
@RequestMapping(value="/member")
public class MemberControllerImpl extends BaseController implements MemberController{
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;
	
	
	
	//loginForm.jsp화면에서 아이디 비밀번호를 입력하고 로그인 버튼을 눌러 로그인요청을 했을때.. 호출되는 메소드 
	//   /member/login.do
	@Override
	@RequestMapping(value="/login.do" ,method = RequestMethod.POST)
							  //입력한 아이디와 비밀번호를 Map에 저장후 전달 받습니다. 
	public ModelAndView login(@RequestParam Map<String, String> loginMap,
			                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		 ModelAndView mav = new ModelAndView();
		 
		 //로그인 요청을 위해 입력한 아이디와 비밀번호가 DB에 저장되어 있는지 확인을 위해
		 //입력한 아이디와 비밀번호로 회원을 조회 해옴
		 //조회가 되면 로그인 처리 하고 조회가 되지 않으면 로그인 처리 하면 안됨
		 memberVO=memberService.login(loginMap);
		 
		 //조회가 되고!! 조회한 회원의 아이디가 존재하면?
		if(memberVO!= null && memberVO.getMember_id()!=null){
			
			//조회한 회원 정보를 가져와 isLogOn 속성을 true로 설정하고 
			//memberInfo속성으로  조회된 회원 정보를  session에  저장합니다. 
			HttpSession session=request.getSession();
			session.setAttribute("isLogOn", true);
			session.setAttribute("memberInfo",memberVO);
			
			//상품 주문 과정에서 로그인 했으면 로그인 후 다시 주문 화면으로 진행하고  그외에 는 메인페이지를 표시합니다. 
			String action=(String)session.getAttribute("action");
			
			if(action!=null && action.equals("/order/orderEachGoods.do")){
				mav.setViewName("forward:"+action);
			}else{
				mav.setViewName("redirect:/main/main.do");	
			}
				
		//로그인 요청시 입력한 아이디와 비밀번호로 회원 조회가 되지 않으면?(입력한 아이디와 비밀번호가 DB에 저장되어 있지 않으면?)	
		}else{
			String message="아이디나 비밀번호가 틀립니다. 다시 로그인해주세요";
			mav.addObject("message", message);
			mav.setViewName("/member/loginForm");
		}
		return mav;
	}
	
	//header.jsp페이지에서 로그아웃 <a>를 눌러서 로그아웃 요청 주소 /member/logout.do을 받았을때..
	@Override
	@RequestMapping(value="/logout.do" ,method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session=request.getSession();
		
		session.setAttribute("isLogOn", false); //로그아웃 시키는 false값 저장 
		
		session.removeAttribute("memberInfo");//로그인 요청시 입력한 아이디 비번을 이용해서 조회 했던 회원정보(MemberVO객체)를 삭제 !
		
		mav.setViewName("redirect:/main/main.do");//ViewInterCeptor클래스 -> 
												  //MainController의 main메소드 -> 
												  //그 후 tiles_main.xml 파일에 작성한 <definition name=/main/main ...> 태그 ->
												  // WEB-INF/views/main/main.jsp 를 중앙에 보여준다.
										//참고. 이때 session영역을 이용하여 미로그인 된 화면을 보여 준다.
		return mav;
	}
	
	
	
/*
	참고.
	
	`ResponseEntity`는 Spring Framework의 클래스로, Java 기반의 엔터프라이즈 응용 프로그램을 구축하기 위한 인기있는 프레임워크 중 하나입니다.
    `ResponseEntity`는 RESTful 웹 서비스를 생성하는 맥락에서 사용되며 HTTP 응답 전체를 나타냅니다. 이에는 상태 코드, 헤더 및 본문(body)이 포함됩니다.

	`ResponseEntity`의 사용 방법에 대한 간략한 개요는 다음과 같습니다.

	1. **ResponseEntity 생성:**  
		  RESTful 엔드포인트가 반환해야 하는 응답을 나타내는 `ResponseEntity` 객체를 만들 수 있습니다. 
	      HTTP 상태 코드, 헤더 및 응답 본문을 지정할 수 있습니다.

	2. **상태 코드 설정:** 
	    `ok()`, `created()`, `badRequest()`, `notFound()` 등의 메서드를 사용하여 요청의 결과를 나타내는 HTTP 상태 코드를 설정할 수 있습니다.

	3. **헤더 설정:** 
	     `header(String headerName, String headerValue)`와 같은 메서드를 사용하여 응답에 사용자 지정 HTTP 헤더를 설정할 수 있습니다.

	4. **응답 본문 설정:** 
	      응답 본문은 응답으로 반환하려는 객체를 전달함으로써 설정할 수 있습니다. 
	      이는 도메인 객체, DTO(Data Transfer Object) 또는 직렬화 가능한 데이터 등이 될 수 있습니다.

	5. **ResponseEntity 반환:** 
	     RESTful 엔드포인트가 `ResponseEntity` 객체를 반환하면 Spring은 자동으로 HTTP 응답으로 직렬화하여 클라이언트에게 보냅니다.


직렬화(serialization)는 데이터 구조나 객체를 바이트 스트림 또는 다른 표현 형식으로 변환하는 프로세스를 의미합니다. 
이렇게 직렬화된 데이터는 파일에 저장하거나 네트워크를 통해 전송하고, 나중에 역직렬화(deserialization)를 통해 원래의 데이터 구조나 객체로 복원할 수 있습니다.

------------------------------------------------------------------------------------------------------	
	@ResponseEntity 어노테이션을 사용해 웹브라우저로 응답하기
	
	 - @RestController어노테이션은 별도의 View를 제공하지 않은채 데이터를 웹브라우저로 전달하므로
	     전달 과정에서 예외가 발생할수 있습니다.
	     예외에 대해  좀더 세밀한 제어가 필요한 경우 ResponseEntity클래스를 사용하면 됩니다.
		
	     예를 들어 안드로이드 기반의 어떤 모바일 쇼일몰 앱 이 있는데, 
	     명절 기간에 주문자가 한꺼번에 몰리면서  톰캣 서버에 부하가 걸렸다고 가정합시다
	    일정 시간이 지나도 주문이 처리 되지 않으면 서버에서 ResponseEntity클래스에 Http 상태 코드를 설정하여 앱으로 전송하도록 합니다.
	    그러면 앱에서 Http 상태코드를 인식할수 있는 기능을 이용해 주문 상태나 예외 발생 메세지를 알려 줄수 있습니다. 

그룹들
서버 오류 응답 코드들
      500  - INTERNAL_SERVER_ERROR 상수   :  처리할수 없는 내부 오류가 발생했다는 의미 
      501  - NOT_IMPLEMENTED 상수 : 요청 메소드는 서버가 지원하지 않거나 처리할수 없다는 의미
      503  - SERVICE_UNAVAILABLE 상수 : 서버는 요청을 처리할 준비가 되지 않았다는 의미
성공 응답 코드들
	  200 -  OK 상수 : 요청이 성공적으로 완료되었다는 의미 
	  201 -  CREATED 상수 : 요청이 성공적이었으며 그결과로 새로운 리소스가 생성되었다는 의미
	  202 -  ACCEPTED 상수 : 요청은 수신 했지만 그에 응하여 행동할수 없는다는 의미
정보 응답 코드들
	  100 
	  101
리다이렉션 메세지
	  300
	  301 
	  302
	  303
클라이언트 오류 응답 
	  400 - BAD_REQUEST 상수 : 이응답은 잘못된 문법으로 인해 서버가 요청을 이해할수 없다는 의미 
	  401 - UNAUTHORIZED 상수 : 인증되지 않았다는 의미 
	  403 - FORBIDDEN 상수 : 클라이언트가 콘텐트에 접근할 권리를 가지고 있지 않다는 의미 
	  404 - NOT_FOUND 상수 : 서버는 요청 받은 리소스를 찾을수 없다는 의미 
*/	


	//회원 가입 요청을 받았을떄...
	@Override
	@RequestMapping(value="/addMember.do" ,method = RequestMethod.POST)
															//회원가입시 입력한 회원 정보를 MemberVO 객체의 각변수에 저장후 전달 받음 
	public ResponseEntity addMember(@ModelAttribute("memberVO") MemberVO _memberVO, 
			                		HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
		    memberService.addMember(_memberVO);//새 회원 정보를 DB에 추가~ 
		    message  = "<script>";
		    message +=" alert('회원가입에 성공 했습니다.');";
		    message += " location.href='"+request.getContextPath()+"/member/loginForm.do';";
		    message += " </script>";
		    
		}catch(Exception e) {
			message  = "<script>";
		    message +=" alert('회원가입 실패 했어요.');";
		    message += " location.href='"+request.getContextPath()+"/member/memberForm.do';";
		    message += " </script>";
			e.printStackTrace();
		}
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	
	
	//memberForm.jsp페이지에서  회원가입시 입력한 아이디가 DB에 존재 하는지 유무 요청 주소 /member/overlapped.do을 받았을때... 
	@Override
	@RequestMapping(value="/overlapped.do" ,method = RequestMethod.POST)
	public ResponseEntity overlapped(@RequestParam("id") String id,
									 HttpServletRequest request, 
									 HttpServletResponse response) throws Exception{
		
		ResponseEntity resEntity = null;
		
		String result = memberService.overlapped(id);
		
		resEntity =new ResponseEntity(result, HttpStatus.OK);
		
		return resEntity;
	}
	
	@RequestMapping(value="/kakao/callback", method=RequestMethod.GET)
	public ResponseEntity<String> kakaoCallback(@RequestParam("code") String code, HttpServletRequest request) {
	    try {
	        // 1. 액세스 토큰 요청
	        String accessToken = memberService.getAccessToken(code);
	        
	        // 2. 사용자 정보 가져오기
	        KakaoVO kakaoUser = memberService.getUserInfo(accessToken);

	        // 3. DB 확인
	        boolean userExists = memberService.checkKakaoUser(kakaoUser.getId());
	        if (userExists) {
	            // 로그인 처리
	            memberService.loginByKakao(kakaoUser.getId(), request);
	            return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/main/main.do").build();
	        } else {
	            // 회원가입 페이지로 리다이렉트
	            String signupRedirect = "/member/memberForm.do?kakaoId=" + kakaoUser.getId() +
	                                    "&email=" + kakaoUser.getEmail() + "&name=" + kakaoUser.getName();
	            return ResponseEntity.status(HttpStatus.FOUND).header("Location", signupRedirect).build();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>("카카오 로그인 실패", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}









