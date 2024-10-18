package sec01.ex02;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/personList")
public class PersonController extends HttpServlet {
	
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response )
						throws ServletException, IOException{
		
		// ArrayList 배열 하나 생성
		ArrayList<String> personList = new ArrayList<String>();
		
		personList.add("john Doe");
		personList.add("Jane Smith");
		personList.add("Alice Johnson");
		personList.add("Bob Brown");
		personList.add("Charlie Davis");
		
		// JSP로 전달하기 위해 request에 ArrayList 바인딩
		request.setAttribute("personList", personList);
		
		// JSP 페이지명 리턴
		RequestDispatcher dispatcher = request.getRequestDispatcher("/test03/personList.jsp");
		dispatcher.forward(request, response);
		
	}
		
}
