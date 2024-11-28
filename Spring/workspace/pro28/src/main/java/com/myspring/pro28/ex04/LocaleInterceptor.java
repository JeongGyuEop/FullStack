package com.myspring.pro28.ex04;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


//인터셉터 기능을 처리하는 LocaleInterceptor클래스는
//HandlerInterceptorAdapter클래스를 상속받아 만들거나
//HandlerInterceptor인터페이스 내부에 만들어 놓은 추상메소드를 오버라이딩한 자식클래스 형태로 만들수 있습니다
public class LocaleInterceptor extends HandlerInterceptorAdapter {

	/*
		메소드가 호출되는 시점
			컨트롤러의 메소드가 실행되기  직전에 호출되는 콜백메소드입니다.
			이 단계에서 요청처리 여부를 결정할수 있습니다.
			
		메소드 역할
			요청(request)이 유효한지 검증하는 메소드 
			예를 들어, 사용자 인증/권한 확인, 특정 헤더 값 검증 등 사전 처리를 수행하는 메소드 
	
		리턴 값
			true : 요청을 계속 처리합니다.(다음 단계로 진행)
			false : 요청 처리를 중단 합니다. (응답을 즉시 반환)
	*/	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("LocaleController클래스의 메소드 실행전 호출되는 preHandle메소드");
		
		HttpSession session = request.getSession();
		  
		
		//웹브라우저에서 요청한 locale정보 값 가져오기 
		String locale = request.getParameter("locale");
		
		//웹브라우저로 최초 요청시 locale정보를 한국어로 설정
		if(locale == null) locale = "ko";
		
		//locale속성(변수)값을  session영역에 저장해 
		//SessionLocaleResolver가 사용할수 있게 하자.
		session.setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE", 
							new Locale( locale) );
		
		
	
		return true;
	}

	/*
	 메소드가 호출되는 시점
	 			컨트롤러의 메소드가 실행된 직후 에 호출되는 콜백메소드입니다.
	 			즉! 뷰(VIEW)를 웹브라우저에 랜더링 하기 전에 호출되는 콜백메소드입니다.
	 메소드 역할
	  			컨트롤러에서 반환된 ModelAndView의 내용을 내용을 수정하거나, 뷰에 추가 데이터를 전달할수 있습니다
	  			클라이언트의 웹브라우저로 응답할 데이터를 가공할수 있습니다.
	 제약사항
	 	 	비동기 요청의 경우 호출되지 않습니다.
	 	 	이 경우 afterCompletion메소드만 자동으로 호출됩니다.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
							ModelAndView modelAndView) throws Exception {
		
		System.out.println("LocaleController의 메소드 실행후 호출되는 postHandle메소드");
		
		
	}

	/*
		메소드가 호출되는 시점
			클라이언트의 요청처리가 완전히 끝난 후 호출되는 콜백메소드입니다.
			뷰 렌더링 완료 후 실행되는 콜백메소드 입니다.
			요약 : 뷰(JSP)를 웹브라우저에 보여주고 난후 호출되는 콜백메소드 입니다.
			
		역할
			요청 처리 과정에서 사용한 리소스(예: DB연결, 파일 입출력스트림)를 자원해제(정리)합니다.
			요청 처리 도중 발생한 예외를 확인할수 있습니다.	
	*/
	@Override
	public void afterCompletion(HttpServletRequest request, 
								HttpServletResponse response, 
								Object handler, Exception ex)
																throws Exception {
		System.out.println("VIEW locale.jsp를 웹브라우저에 보여준 후 호출되는  afterCompleton메소드");
		
		
	}
}
/*
요청 흐름과의 관계
	인터셉터 메서드가 호출되는 순서를 요청 처리 흐름으로 정리하면 다음과 같습니다:

1. 클라이언트 -> 요청 발생
2. preHandle 실행
		요청 검증 및 사전 처리
		false 반환 시, 요청 중단 및 응답 반환
3.컨트롤러 -> 요청 처리
4.postHandle 실행
		컨트롤러 실행 후 추가 작업
		(단, 비동기 요청 시 생략됨)
5.뷰(View) 렌더링
6.afterCompletion 실행
	리소스 정리 및 최종 작업
	
	
정리
		preHandle: 요청의 사전 처리(중단 가능).
		postHandle: 컨트롤러 실행 후 추가 작업(비동기 요청은 제외).
		afterCompletion: 요청 종료 후 최종 작업(리소스 정리 및 예외 처리).
		이 세 가지 메서드는 요청/응답의 전반적인 흐름을 제어하거나 공통 로직을 삽입하는 데 효과적입니다. 
		이를 활용해 보안, 로깅, 데이터 가공 등의 작업을 체계적으로 처리할 수 있습니다.	
*/
















