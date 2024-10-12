package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GuguTest
 */
@WebServlet("/guguTest")
public class GuguTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		
		System.out.println("init 메소드 호출");
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		int dan = Integer.parseInt(request.getParameter("dan"));
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter printwriter = response.getWriter();
		
		String data = "<table border=1 width=800 align=center>"
					+ "<tr alin=center bgcolor='#FFFF66'>"
					+ "<td colspan=2>" + dan + " 단 출력 </td>"
					+ "<tr>";
		for (int i = 1; i < 10; i++) {
			   data += "<tr align=center>"
					 + "<td width=400>"
			         + dan + " * " + i
			         + "</td>"
			         + "<td width=400>"
			         + i * dan
			         + "</td>"
			         + "</tr>";
		}
			   data += "</table>";
		
		printwriter.print(data);
	}
	
	public void destroy() {
		
		System.out.println("destroy 메소드 호출");
		
	}

}
