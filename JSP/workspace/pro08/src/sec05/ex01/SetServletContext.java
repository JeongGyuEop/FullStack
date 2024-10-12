package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
   getServletContext()메소드를 호출해서  반환 값으로  ServletContext객체메모리를 얻은다음
   ArrayList배열에  이름과 나이를 바인딩(저장)한 후 ~~
   ArrayList배열을 ServletContext객체메모리에 setAttribute메소드를 호출해 바인딩합니다.

   클라이언트는 크롬브라우저를 이용해 주소창에 http://localhost:8090/pro08/cset요청주소를
  입력하여 아래의 SetservletContext서블릿을 요청하면
  톰캣 서버는 웹프로젝트(컨텍스트) pro08 하나에 관한 저장소인 ServletContext객체 메모리를 생성하여
  톰캣 서버 메모리에 보관 해 둡니다.

*/
@WebServlet("/cset")
public class SetServletContext extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) 
								 throws ServletException, IOException {
		//웹브라우저로 응답할 MIME-TYPE을  text/html; 로 설정하고
		//문자처리 방식 UTF-8방식으로 HttpServletResponse객체에 설정
		response.setContentType("text/html;charset=utf-8");
		
		//요청한 클라이언트와 연결된 출력 스트림 PrintWriter객체 생성
		PrintWriter out = response.getWriter();
		
		//톰캣서버가 생성해 놓은 ServletContext객체의 주소를 얻어 저장
		//요약 : 현재 작성하는 SetServletContext서블릿객체가 속한 웹프로젝트 전체에서
		//      데이터를 공유한 ServletContext객체 반환
		//참고. ServletContext객체 메모리는 
		//     웹프로젝트(pro08) 전체 에서 공유되는 데이터들을 저장해 놓고 
		//     모든 서버페이지(서블릿 또는 JSP들)에서 공유하는 메모리입니다.
		ServletContext context = this.getServletContext();
		
		//ArrayList배열 생성후 "이순신"과 30 를 저장해 놓자
		List list = new ArrayList();
			 list.add("이순신");
			 list.add(30);
			 
	//ServletContext 공유 객체 메모리에
	//"member"라는 key와 함꼐 ArrayList배열을 바인딩(저장)
		//이렇게 바인딩 하면  pro08웹 프로젝트 내부에 작성된 모든 다른 서블릿에서도
		//ServletContext 공유 객체 메모리를 공유받아 접근해서 
	    //ArrayList배열을 꺼내서 사용할수 있다.
		context.setAttribute("member", list);	 
		
		out.print("이순신 과 30을 ArrayList배열에 저장하고 "
				+ "ArrayList배열 자체를 ServletContext객체메모리에 바인딩 했음");
		
		
	}
	
}











