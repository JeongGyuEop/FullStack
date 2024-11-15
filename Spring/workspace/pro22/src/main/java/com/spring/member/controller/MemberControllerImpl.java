package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.service.MemberService;
import com.spring.member.vo.MemberVO;

//MVC디자인 패턴의 C역할을 하는 사장님 


public class MemberControllerImpl extends MultiActionController 
								  implements MemberController {
	// new MemberServiceImpl();객체를 주입할 변수 
	private MemberService memberService;
	

	//action-servlet.xml파일에서 
	//MemberControllerImpl에 대한 <bean>으로 객체(빈)를 생성한 후
	//<property>태그를 이용해 setMemberService메소드를 호출할때...
	//매개변수로 MemberServiceImpl객체를 전달 하여 의존하는 객체를 주입시켜 저장하기 위해
	//아래의 메소드가 필요하다.	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	
	//모든 회원 정보 조회 기능 명령 
	//   /member/listMembers.do   요청 주소가 들어 왔을때
	//   DB의 t_member테이블에 저장된 모든 회원 조회 요청 명령! 부장(MemberServiceImpl)에게 
	@Override
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		
	//요청한 주소에 관한 응답할값 마련
		//부장 MemberServiceImpl객체의 listMembers()메소드를 호출하여
		//모든 회원 조회 요청을 명령함!
		//웹브라우저로 응답할 조회한 정보들이 담긴  List배열을 반환 받는다.
		List membersList = memberService.listMembers();
		
	//응답할 값과 응답할 뷰 이름을 ModelAndView객체 메모리에 바인딩 (키와 값을 한쌍으로 묶어서 저장)
		ModelAndView mav = new ModelAndView();
					 mav.addObject("membersList", membersList);//응답할 Model 저장 
					 mav.setViewName(  getViewName(request) );//응답할 뷰명 저장 
					//요청 URL주소  /member/listMembers.do 에서  
					//.do를 제외한 /listMembers뷰이름얻기
		
		
		return mav; //디스패처 서블릿으로 ModelAndView반환 
	}

	@Override
	public String memberForm(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		
		return "/memberForm";
		
	}

	@Override
	public String addMember(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		//VO에 저장
		MemberVO vo = new MemberVO();
				 vo.setId(id);
				 vo.setPwd(pwd);
				 vo.setName(name);
				 vo.setEmail(email);
		
		memberService.addMembers(vo);
		
		return "redirect:/member/listMembers.do";
	}

	//회원정보  수정을 위해 회원 한명의 정보 조회 기능
	// 수정링크를 누르면 요청 주소   ->  /member/memberDetail.do	
	@Override
	public ModelAndView memberDetail(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		
		//http://localhost:8090/pro22/member/memberDetail.do?id=admin1
		
		request.setCharacterEncoding("UTF-8");
		
		//요청한 값 (수정할 회원을 조회 하기 위한 아이디)얻기
		String id = request.getParameter("id");
		
		//부장 MemberServiceImpl객체의 메소드 호출시 vo를 전달하여 SELECT명령!
		MemberVO vo = memberService.detailMembers(id);		 
		
		//뷰페이지에 조회된 정보를 보여주기 위해 ModelAndView객체 메모리에 바인딩
		ModelAndView mav = new ModelAndView( getViewName(request) ); //뷰명 /memberDetail 저장
					 mav.addObject("membervo", vo);// 바인딩
					 
					
		return mav;//디스패처 서블릿으로 ModelAndView객체 반환
	}

	
	//수정 요청 /member/UpdateMember.do 주소를 받았을때
	@Override
	public String UpdateMember(HttpServletRequest request, HttpServletResponse reponse) throws Exception {

		request.setCharacterEncoding("UTF-8");
		
		//요청한 수정할 값 얻기
		//요청한 값 얻기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		System.out.println("수정할 회원 아이디 = " + id);
		
		//VO에 저장
		MemberVO vo = new MemberVO();
				 vo.setId(id);
				 vo.setPwd(pwd);
				 vo.setName(name);
				 vo.setEmail(email);
		
		//부장 MemberServiceImpl객체의 메소드 호출시 수정할 id를 전달하여 UPDATE명령!
		memberService.UpdateMember(vo);		 
			
		//회원 수정후 모든회원을 조회 하는 재요청 주소 작성 
		return "redirect:/member/listMembers.do";
	}

	//삭제링크를 누르면 요청 주소   ->  /member/memberDel.do
	@Override
	public String memberDel(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
	
		request.setCharacterEncoding("UTF-8");
		
		//회원 삭제 요청시 전달한  id얻기
		String id = request.getParameter("id");
		System.out.println("삭제할 회원 아이디 : " + id);		
		
		//부장 MemberServiceImpl객체의 메소드 호출시~ 인자로 삭제할 회원 ID넘겨서 DELETE명령
		memberService.delMembers(id);
		
		
		//회원 삭제에 성공하면 모든회원을 조회 해서 보여 주기 위해 모든회원 조회 재요청!
		//웹브라우저 주소창에 밑의 주소가 자동으로 적히면서 엔터를 눌러 디스패처 서블릿으로 요청한 것과 같다.
		return "redirect:/member/listMembers.do";
	}
	
	//http://localhost:8090/pro21/test/loginForm.do 요청주소를 받으면
	//확장자 .do를 제외한  loginForm VIEW명을 얻어 제공하는 메소드 
	private  String getViewName(HttpServletRequest request) throws Exception {
	    
		// /pro21
		 String contextPath = request.getContextPath();
		
	      String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
	      if(uri == null || uri.trim().equals("")) {
	        
	    	  uri = request.getRequestURI();
	    	  System.out.println("uri : " + uri);
	      }
	      
	      // http://localhost:8090/pro21/test/loginForm.do로 요청시
	      int begin = 0;  //
	      if(!((contextPath==null)||("".equals(contextPath)))){
	    	  
	    	  //   /pro21  -> 문자갯수 6
	         begin = contextPath.length();  // 전체 요청명 의 길이를 구함
	      }

	      int end;
	      if(uri.indexOf(";")!=-1){
	         end=uri.indexOf(";");  //요청 uri에 ';'가 있을 경우 ';'문자 위치를 구함
	      }else if(uri.indexOf("?")!=-1){
	         end=uri.indexOf("?");   //요청 uri에 '?'가 있을 경우 '?' 문자 위치를 구함
	      }else{
	         end=uri.length();
	      }

	      //http://localhost:8090/pro21/test/loginForm.do로 요청시 
	      //먼저 '.do'를 제거한 http://localhost:8081/pro21/test/loginForm를 구한 후,
	      
	      //다시http://localhost:8090/pro21/test/loginForm에서 
	      //역순으로 첫번째 '/' 위치를 구한 후, 그 뒤의 loginForm를 구한다.
	      String fileName=uri.substring(begin,end);
	      System.out.println("fileName : " + fileName);
	      
	      if(fileName.indexOf(".")!=-1){
	         fileName=fileName.substring(0,fileName.lastIndexOf("."));  //요청명에서 역순으로 최초 '.'의 위치를 구한후, '.do' 앞에까지의 문자열을 구함
	      }
	      if(fileName.lastIndexOf("/")!=-1){
	         fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length()); //요청명에서 역순으로 최초 '/'의 위치를 구한후, '/' 다음부터의 문자열을 구함  
	      }
	      
	      System.out.println("result :" + fileName);
	      return fileName;
	   }
	
	

}

















