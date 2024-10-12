package sec06.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//http://localhost:8090/pro08/sInit 주소요청 또는
//http://localhost:8090/pro08/sInit2 주요요청을 하면 호출되는 서블릿 

@WebServlet(
				//urlPatterns속성을 이용해 매핑주소를 여러개 설정할수 있다.
				urlPatterns = { 
						"/sInit", 
						"/sInit2"
				}, 
				//initParams속성에 @WebInitParam어노테이션 기호 내부에
				//서블릿이 사용할 초기 변수들을 만들어 놓고 사용할수 있다.
				initParams = { 
						@WebInitParam(name = "email", value = "admin@jweb.com"), 
						@WebInitParam(name = "tel", value = "010-1111-2222")
				}
		   )
public class InitParamServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//위 initParams속성에 설정한 초기 매개변수의 값들을
		//ServletConfig객체에서 얻어와 사용
		String email = this.getInitParameter("email");//"admin@jweb.com"
		String tel = this.getInitParameter("tel"); //"010-1111-2222"
		
		out.print("email : " + email + "<br>");
		out.print("tel : " + tel + "<br>");
		
		
		
	}

}






















