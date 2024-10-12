package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//엣지 웹브라우저 주소창에 
//http://localhost:8090/pro08/cget요청주소를 입력하여 아래의 서블릿 요청

//설명 : 
//getServletContext메소드를 호출해 ServletContext객체 메모리주소를 얻어와 접근합니다.
//그리고 getAttribute(key); 메소드를 호출하여 ServletContext객체 메모리 안에 바인딩되었던
//ArrayList배열을 가져와 사용 합니다.

@WebServlet("/cget")
public class GetServletContext extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) 
								 throws ServletException, IOException {
		 response.setContentType("text/html;charset=utf-8");
		
		 PrintWriter out = response.getWriter();
		 
		 //톰캣 서버가 생성 해 놓은  ServletContext객체의 주소를 얻어 저장 
		 ServletContext context = this.getServletContext();
		 
		 //ServletContext공유 객체 저장소 메모리에 바인딩 했던
		 //ArrayList배열을 얻어 옵니다. 
		 List list = (List)context.getAttribute("member");
		 
		 //ArrayList 배열에 저장된 이름과 나이 정보를 얻습니다
		 String name = (String)list.get(0); // "이순신" 문자열 객체 얻기
		 int age = (Integer)list.get(1); // new Integer(30); 객체를 얻어 
		 								 //오토 언박싱해서 30을 저장
		 
		 out.print(name + "<br>" + age);
		 
		 //결론 -  이번 예제는 ServletContext공유 객체 메모리 영역을 사용해서
		 //       하나의 웹애플리케이션(pro08) 내부에 만들어 놓은 모든 서블릿(서버페이지)들이
		 //       공유해서 사용할 데이터가 있을때  저장 (바인딩) 해서 얻어 사용하는 예제 입니다
		 
		 
		 
	}
	
	
}




























