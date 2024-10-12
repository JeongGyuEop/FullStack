package sec05.ex03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cfile")
public class ContextFileServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 요청한 웹 브라우저에 응답할 데이터 종류를 text/html;로 설정하고
		// 문자처리 방식 UTF-8로 설정
		response.setContentType("text/html;charset=utf-8");
		
		
		// 요청한 클라이언트의 웹 브라우저와 연결된 출력스트림 통로 PrintWriter객체 얻기
		PrintWriter out = response.getWriter();
		
		// 웹 프로젝트의 모든 서버페이지가 공유할 자원이 저장되는 ServletContext 객체 메모리 얻기
		ServletContext context = this.getServletContext();
		
		// 웹 프로젝트 내부에 만들어 놓은 "WEB-INF/bin/init.txt" 파일의 데이터를
		// 바이트 단위로 읽어들이기 위한 init.txt 파일과 연결된 InputStream 입력 스트림 통로 얻기
		InputStream is = context.getResourceAsStream("/WEB-INF/bin/init.txt");
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
		
		// init.txt 파일에서 읽어온 메뉴 데이터 정보들을 저장할 변수들 선언
		String menu = null;
		String menu_member = null;
		String menu_order = null;
		String menu_goods = null;
		
		while((menu = bufferedReader.readLine()) != null) {
			
			// 콤마 , 를 구분자 기호로 정하여 메뉴 항목 전체를 문자열로 부리 시킬 수 있도록 객체 생성
			StringTokenizer tokenizer = new StringTokenizer(menu, ",");
			
			menu_member = tokenizer.nextToken();// "회원등록 회원조회 회원수정"
			menu_order = tokenizer.nextToken(); // "주문조회 주문수정 주문취소"
			menu_goods = tokenizer.nextToken(); // "상품조회 상품등록 상품수정 상품삭제"
		}
		
		// 출력스트림 통로인 PrintWriter의 print 메소드를 이용해서
		// 클라이언트의 웹브라우저로 init.txt 파일에서 읽어들인 메뉴 데이터들을 응답(출력)
		out.print(menu_member + "<br>");
		out.print(menu_order + "<br>");
		out.print(menu_goods + "<br>");
				
	}

}
