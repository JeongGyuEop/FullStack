package sec05.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/initMenu")
public class CotextParamServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 웹브라우저로 응답할 MIME-TYPE을 text/html;로 설정하고
		// 문자처리 방식 UTF-8방식으로 HttpServletResponse객체에 설정
		response.setContentType("text/html;charset=utf-8");
				
		// 요청한 클라이언트와 연결된 출력 스트림 PrintWriter 객체 생성
		PrintWriter out = response.getWriter();
				
		// 톰캣서버가 생성해 놓은 ServletContext 객체의 주소를 얻어 저장
		// 요약 : 현재 작성하는 SetServletContext 서블릿객체가 속한 웹 프로젝트 전체에서
		// 데이터를 공유하는 ServletContext 반환
		// 참고. ServletContext 객체 메모리는 웹 프로젝트(pro08) 전체에서 공유되는
		//       데이터들을 저장해 놓고 모든 서버페이지에서 공유하는 메모리이다.
		ServletContext context = this.getServletContext();
		
		// web.xml에 설정된 초기화파라미터의 값을 ServletContext객체 메모리 내부에서 얻기
		// "menu_member", "menu_order", "menu_goods"는
		// web.xml에 설정된 초기화파라미터 값이 저장된 변수명들이다.
		// 이 값들은 웹 애플리케이션 전체에 만들어 놓은 모든 서블릿 또는 jsp페이지에서 사용할수 있는
		// 공통 파라미터(요청값)이다.
		String menu_member = context.getInitParameter("menu_member");
		String menu_order = context.getInitParameter("menu_order");
		String menu_goods = context.getInitParameter("menu_goods");
		
		// 클라이언트의 웹브라우저로 응답(출력)
		out.print("<html><body>");
		out.print("<table border='1' cellspacing='0'>");
		out.print("<tr><td>메뉴이름들</td></tr>");
		out.print("<tr><td>" + menu_member + "</td></tr>");
		out.print("<tr><td>" + menu_order + "</td></tr>");
		out.print("<tr><td>" + menu_goods + "</td></tr>");
		out.print("</body></html>");
		
	}
}
