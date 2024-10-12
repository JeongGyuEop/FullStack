package sec01.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 	Servlet 인터페이스
 		- 모든 서블릿 클래스가 구현해야 하는 가장 상위 인터페이스이다.
 		  이 인터페이스는 서블릿의 생명주기를 관리하는 기본 메소드들이 만들어져 있다.
 		  주요 메소드로는 init(), service(), destroy(), getServletConfig(),
 		  getServletInfo() 등이 있다.
 		  
 	GenericServlet 클래스
 		- Servlet 인터페이스를 구현한 추상클래스이다.
 		  이 클래스는 프로토콜에 독립적인 서블릿을 만들 때 사용된다.
 		  이 클래스는 service() 메서드만 추상 메소드로 남겨두며, 나머지 메소드는
 		  기본 구현만 제공한다.
 		- 일반적으로 이 클래스를 확장해서 서블릿을 구현하는 경우는 드물다.
 		
 	HttpServlet 클래스
 		- GenericServlet 클래스를 확장한 클래스이다.
 		  HTTP 프로토콜을 기반으로 동작하는 서블릿만들기 위해서 사용된다.
 		  웹 애플리케이션에서 가장 많이 사용되는 서블릿 클래스이다.
 		
 		- 이 클래스는 HTTP 요청에 따라 doGet(), doPost(), doPut(), doDelete()
 		  메소드를 제공하여 클라이언트의 HTTP 요청 방식에 따라 적절하게 처리할 수 있다.
 		  
 	계층구조 요약
 		Servlet(인터페이스)
 			GenericServlet(추상 클래스)
 				HttpServlet(일반(구현) 클래스)
 
 	서블릿?
 		- 클라이언트의 요청 주소를 받아 처리하는 서버페이지 역할을 하는 자바의 클래스
 		- 사용자 정의 서블릿 클래스를 만들때는 항상 HttpServlet 클래스를 상속받아 사용한다.
 
 */

public class FirstServlet extends HttpServlet {

	// 메소드 오버라이딩 alt + shift + s v
	
	// 호출 되는 순서 1위 
	// FirstServlet 서블릿 페이지 요청 시 맨 처음 한 번만 호출되는 콜백메소드로
	// 톰캣 서버가 FirstServlet 서블릿 클래스를 메모리에 로드하고 인스턴스화 시킨 후
	// 서블릿의 초기화 작업을 해야할 때 주로 기능 구현해 놓을 메소드
	@Override
	public void init() throws ServletException {
		System.out.println("init 메소드 호출 당함.");
	}
	
	
	// 호출되는 순서 2위
	// service(ServletRequest req, ServletResponse res) 메소드
	// 역할 : 클라이언트의 요청을 처리하는 메소드로, 서블릿이 요청을 받을 때 마다 호출되는 콜백 메소드
	//
	// 의미 : 이 메소드는 클라이언트로부터 들어온 요청(ServletRequest)을 받아
	//		  적절한 응답(ServletResponse)을 생성한다.
	//		  서블릿은 이 메소드를 통해 다양한 HTTP 요청 방식(GET, POST)를 처리한다.
	//
	// 실행시점 : 이 메소드가 doGet() 또는 doPost() 같은 특정 HTTP 메소드 처리 메소드를 자동으로 호출
	
	
	// 호출되는 순서 3위
	//	- FirstServlet 서블릿 서버 페이지를 GET 전송 방식으로 요청하면
	// 	  호출되는 콜벡메소드로 실제 클라이언트가 요청한 데이터를 기반으로 
	//    클라이언트의 웹 브라우저로 응답할 데이터를 구현하는 메소드
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("doGet 메소드 호출 당함.");
		
	}

	// 호출되는 순서 마지막
	// 	- 역할 : 서블릿이 톰캣서버 메모리에서 소멸될 때 호출되는 메소드로
	//			 서블릿이 더 이상 사용되지 않거나, 톰캣서버가 종료될 때 단 한 번만 호출되는 메소드
	//
	// 	- 의미 : 서블릿이 사용했던 리소스를 해제 하는 작업을 구현해 놓는다.
	//			 예를 들어 데이터 베이스 연결 해제, 파일 입출력 스트림 통로 닫기, 캐시데이터 정리 등의
	//			 작업을 구현해 놓는다.
	@Override
	public void destroy() {

		System.out.println("destroy 메소드 호출 당함.");
		
	}

	
	// init 메소드나 destroy 메소드는 생략이 가능하나
	// do 로 시작하는 doGet이나 doPost 메소드 중 하는 반드시 재구현 해놓아야 한다.

	
	
}
