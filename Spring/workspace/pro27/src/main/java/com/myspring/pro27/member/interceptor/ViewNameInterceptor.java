package com.myspring.pro27.member.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*
Spring 프레임워크에서 HandlerInterceptorAdapter 클래스는??

MVC 구조에서 요청(request)과 응답(response) 처리 과정에서 필요한 기능을 제공하는 인터셉터(interceptor)를 구현하는 데 사용됩니다.
HandlerInterceptorAdapter 클래스는 HandlerInterceptor 인터페이스를 구현하고, 
미리 정의된 메서드를 포함하여 커스터마이징된 메서드를 추가로 제공합니다. 

HandlerInterceptorAdapter클래스를 사용하면 
컨트롤러(controller)에서 발생하는 작업을 가로채어(request를 처리하기 전/후에) 추가적인 작업을 수행할 수 있습니다.

HandlerInterceptorAdapter 클래스의 메서드에는 다음과 같은 것이 있습니다.
	
	preHandle() : 컨트롤러 메서드를 실행하기 전에 호출되는 메서드입니다. 
	                               이 메서드는 요청에 대한 처리를 수행하고, 반환값으로 boolean 타입을 갖습니다. 
	                               반환값이 true이면 컨트롤러 메서드를 실행하고, false이면 컨트롤러 메서드를 실행하지 않고 요청 처리를 중단합니다.
	
	postHandle() : 컨트롤러 메서드 실행 후에 호출되는 메서드입니다. 
	                                 이 메서드는 컨트롤러 메서드가 반환하는 ModelAndView 객체를 가공하는 작업을 수행할 수 있습니다.
	
	afterCompletion() : 응답이 전송된 후에 호출되는 메서드입니다. 이 메서드는 예외 발생 여부와 상관없이 항상 호출됩니다.


전체 요약:
	HandlerInterceptorAdapter 클래스를 사용하면 전역적으로 인터셉터를 등록하고 적용할 수 있습니다. 
	이를 통해 코드의 중복을 줄이고 유지보수성을 높일 수 있습니다. 
	예를 들어, 사용자 인증, 로깅, 성능 측정 등의 기능을 구현할 때 HandlerInterceptorAdapter 클래스를 사용할 수 있습니다.
*/

import com.myspring.pro27.member.controller.MemberControllerImpl;


//인터셉터 기능을 처리하는 HandlerInterceptorAdapter클래스를 만들때는
//HandlerInterceptorAdapter클래스를 상속받아 만들거나
//HandlerInterceptor인터페이스 내부에 만들어 놓은 추상메소드를 오버라이딩 한 자식클래스로 만들수 있습니다.
public class ViewNameInterceptor extends HandlerInterceptorAdapter {

	
		//LoggerFactory클래스를 이용해  전달한 클래스의 정보를 지닌 Logger객체를 반환해 옴
		private static final Logger logger 
		= LoggerFactory.getLogger(ViewNameInterceptor.class);

	
//홈 로고 이미지를 클릭하면 		
//http://localhost:8090/pro27/main.do   ->   /main

//로그인 <a>를 클릭하면 
//http://localhost:8090/pro27/member/loginForm.do	->  /loginForm	

//	preHandle() : 컨트롤러 메서드를 실행하기 전에 호출되는 메서드입니다. 
//                이 메서드는 요청에 대한 처리를 수행하고, 반환값으로 boolean 타입을 갖습니다. 
//                반환값이 true이면 컨트롤러로 가서  메서드를 실행하고, 
//                false이면 컨트롤러 가지않아 메서드를 실행하지 않고 요청 처리를 중단합니다.
	@Override  
	public boolean preHandle(HttpServletRequest request,     //http://localhost:8090/pro27/member/listMembers.do
							 HttpServletResponse response, 
							 Object handler)
							throws Exception {
		System.out.println();
		System.out.println("================================================================");
		System.out.println("ViewNameInterceptor 클래스의 preHandle 메소드 시작");
		System.out.println();
		//ViewNameInterceptor <bean> 수행시 
		//preHandel메소드의 HttpServletRequest request 매개변수로 전달받은
		//request객체 메모리의 정보(요청한 주소)를  위 만들어 놓은 getViewName메소드 호출시 전달 하여 
		//뷰이름을 가져온 후  request메모리에 바인딩 합니다. 

		System.out.println("ViewNameInterceptor 클래스의 getViewName 메소드 호출");
		String viewName = getViewName(request);// /listMembers    /main
		System.out.println("ViewNameInterceptor 클래스의 getViewName 메소드 에서 반환받은 값 : " + viewName);
		
		request.setAttribute("viewName", viewName);
		
		System.out.println("ViewNameInterceptor 클래스의 preHandle메소드 바인딩 하는 값 : " +  viewName);
		
		
		//Logger클래스의 info메소드를 이용해 로그 메세지 레벨을 info로 설정 하여 출력 합니다.
	//	logger.info("info 레벨 : viewName = " + viewName);
		//Logger클래스의 debug메소드를 이용해 로그 메세지 레벨을 debug로 설정 하여 출력 합니다.
	//	logger.debug("debug 레벨 : viewName = " + viewName);
		

		System.out.println();
		System.out.println("ViewNameInterceptor 클래스의 preHandle 메소드 끝");
		System.out.println("================================================================");
		System.out.println();
		return true; // 반환값이 true이면 컨트롤러로 가서  메서드를 실행!
	}

	//컨트롤러 실행후  호출되는 메소드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
							Object handler,ModelAndView modelAndView) {
		
		try {
			System.out.println();
			System.out.println("================================================================");
			System.out.println("ViewNameInterceptor 클래스의 postHandle 메소드 시작");
			
			logger.info("info 레벨 : MemberControllerImpl실행후 호출되는 컨트롤러 메서드 실행 후에 호출되는 메서드입니다.");
			logger.info("info 레벨 :이 메서드는MemberControllerImpl 컨트롤러 메서드가 반환하는 ModelAndView 객체를 가공하는 작업을 수행할 수 있습니다.");
			
			//Logger클래스의 info메소드를 이용해 로그 메세지 레벨을 info로 설정 하여 출력 합니다.
			logger.info("info 레벨 : viewName = " + modelAndView.getViewName());
			//Logger클래스의 debug메소드를 이용해 로그 메세지 레벨을 debug로 설정 하여 출력 합니다.
			logger.debug("debug 레벨 : ModelAndView 객체에 저장된 Map = " + modelAndView.getModel());
			

			System.out.println("ViewNameInterceptor 클래스의 postHandle 메소드 종료");
			System.out.println("================================================================");
			System.out.println();
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
//		
//	HandlerInterceptorAdapter 클래스의 postHandle 메소드의 매개변수 정보는 다음과 같습니다.
//
//		HttpServletRequest request : 현재 요청(request) 객체를 나타냅니다.
//
//		HttpServletResponse response : 현재 응답(response) 객체를 나타냅니다.
//
//		Object handler : 현재 처리 중인 컨트롤러 객체를 나타냅니다.
//
//		ModelAndView modelAndView : 컨트롤러가 반환한 ModelAndView 객체입니다. 이 객체에는 컨트롤러에서 전달된 모델 데이터와 뷰 정보가 포함되어 있습니다.
//
//		따라서, postHandle 메소드는 컨트롤러 메소드 실행 후에 호출되며, 컨트롤러에서 반환한 ModelAndView 객체를 가공하여 다양한 작업을 수행할 수 있습니다. 
//		
//		이 메소드에서는 modelAndView 객체를 수정하여, 컨트롤러에서 반환된 뷰 정보를 변경하거나, 모델 객체에 데이터를 추가하는 등의 작업을 수행할 수 있습니다.
//
//		또한, 이 메소드에서는 response 객체를 통해 클라이언트에게 전달할 데이터를 설정할 수 있습니다. 
//		
//		예를 들어, 캐시 설정, 쿠키 설정, 브라우저 리다이렉션 등의 작업을 수행할 수 있습니다.
//
//		이 메소드에서 발생하는 예외는 서블릿 컨테이너에 의해 처리됩니다. 
//		
//		따라서, 이 메소드에서는 예외를 던지는 것보다는 로깅(logging) 등의 작업을 통해 예외 처리에 대한 정보를 기록하는 것이 바람직합니다.
//		
		
	}
	
	//뷰(JSP)를 수행한 후 호출되는 메소드 
	@Override
	public void afterCompletion(HttpServletRequest request, 
								HttpServletResponse response, 
								Object handler, 
								Exception ex){
		
		try {

			System.out.println();
			System.out.println("================================================================");
			System.out.println("ViewNameInterceptor 클래스의 afterCompletion 메소드 시작");
			
				if(ex != null) {
					logger.info("info 레벨 :  컨트로러에서 예외가 발생 했을때의 예외 메세지  = " +  ex);
				}
				logger.info("info 레벨 :  컨트로러에서 예외가 발생 하지 않았을때 null이 출력 되어야 한다!!! null이 출력 되는가? : " +  ex);

			System.out.println("ViewNameInterceptor 클래스의 afterCompletion 메소드 종료");
			System.out.println("================================================================");
			System.out.println();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//	HandlerInterceptorAdapter 클래스의 afterCompletion 메소드의 매개변수 정보는 다음과 같습니다.
//
//		HttpServletRequest request : 현재 요청(request) 객체를 나타냅니다.
//
//		HttpServletResponse response : 현재 응답(response) 객체를 나타냅니다.
//
//		Object handler : 현재 처리 중인 컨트롤러 객체를 나타냅니다.
//
//		Exception ex : 컨트롤러에서 발생한 예외 객체입니다. 컨트롤러에서 예외가 발생하지 않았을 경우 null 값입니다.
//
//		따라서, afterCompletion 메소드는 컨트롤러 메소드 실행 후, 응답이 전송된 후에 호출되며, 컨트롤러에서 발생한 예외 정보를 제공합니다. 이 메소드에서는 보통 리소스(resource) 해제 등의 클린업 작업을 수행할 수 있습니다.
//
//		예를 들어, 로그아웃 기능을 구현할 때, afterCompletion 메소드에서 세션(Session)을 제거하여 보안을 강화하는 작업을 수행할 수 있습니다. 또한, 이 메소드에서는 예외 처리에 대한 작업도 수행할 수 있습니다. 예를 들어, 예외 로깅(logging)을 수행하거나, 예외 처리 페이지로 리다이렉션하는 등의 작업을 수행할 수 있습니다.
//
//		이 메소드에서 발생하는 예외는 서블릿 컨테이너에 의해 처리됩니다. 따라서, 이 메소드에서는 예외를 던지는 것보다는 로깅(logging) 등의 작업을 통해 예외 처리에 대한 정보를 기록하는 것이 바람직합니다.
	}

	//요청한 주소에서 View이름만 생성해서 반환 하는 메소드 
	//예) 요청한 주소 :  http://localhost:8090/pro27/member/listMembers.do 
	//   생성한 View이름 : /listMembers
	private  String getViewName(HttpServletRequest request) throws Exception {
	      
		  System.out.println("ViewNameInterceptor 클래스의 getViewName 메소드 진입");
		  
		  
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

		  System.out.println("ViewNameInterceptor 클래스의 getViewName 메소드 종료");
	      return fileName; // .do를 제외한 요청주소를 리턴
	   }	
	
	//요청한 주소에서 View이름만 생성해서 반환 하는 메소드 2   <- 사용하지 말자
		//예) 요청한 주소 :  http://localhost:8090/pro27/member/listMembers.do 
		//   생성한 View이름 :  /member/listMembers  
	/*
		private String getViewName2(HttpServletRequest request) throws Exception {
			String contextPath = request.getContextPath();
			String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
			if (uri == null || uri.trim().equals("")) {
				uri = request.getRequestURI();
			}

			int begin = 0;
			if (!((contextPath == null) || ("".equals(contextPath)))) {
				begin = contextPath.length();
			}

			int end;
			if (uri.indexOf(";") != -1) {
				end = uri.indexOf(";");
			} else if (uri.indexOf("?") != -1) {
				end = uri.indexOf("?");
			} else {
				end = uri.length();
			}

			String fileName = uri.substring(begin, end);
			if (fileName.indexOf(".") != -1) {
				fileName = fileName.substring(0, fileName.lastIndexOf("."));
			}
			if (fileName.lastIndexOf("/") != -1) {
				fileName = fileName.substring(fileName.lastIndexOf("/", 1), fileName.length());
			}
			return fileName;
		}
	*/	

}
