package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 두번쨰 서블릿 요청시에는 request의 getCookies()메소드를 호출해
 웹브라우저로부터 쿠키를 전달 받습니다.
 그리고 전달된 쿠키에서 저장할떄 사용한 이름인 "cookieTest" 로 검색해 쿠키값을 가져옵니다.
*/

@WebServlet("/get")
public class GetCookieValue extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) 
								 throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		//request객체의 getCookies()메소드를 호출해 
		//웹브라우저에게 Cookie객체의 정보를 요청한 후  쿠키 정보를 배열로 가져옵니다.
		Cookie[] allValue = request.getCookies();
		
		//배열에 저장할떄 사용한 쿠키 이름인 cookieTest로 검색해 쿠키 값을 가져와 출력
		for(int i=0;  i<allValue.length;  i++) {
			
			//조건 : 쿠키 이름이 cookieTest인 new Cookie(....); 객체라면?
			if(allValue[i].getName().equals("cookieTest")) {
				
				out.print("Cookie 값 가져오기 :");
				out.print(  URLDecoder.decode( allValue[i].getValue(), "UTF-8") );
			}
			
			
		}
		
		
		
	}
	
	
	
}















