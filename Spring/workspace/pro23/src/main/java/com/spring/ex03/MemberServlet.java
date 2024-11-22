package com.spring.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/mem3.do")      
public class MemberServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
						throws ServletException, IOException {
		doHandle(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
						 throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) 
			 			 throws ServletException, IOException {
	//요청한 데이터 한글처리
		request.setCharacterEncoding("UTF-8");		
		
		MemberDAO dao = new MemberDAO(); //DB작업하는 객체 생성
		MemberVO memberVO = null; //자바빈 역할의 VO객체 생성후 저장할 변수
		String nextPage = "";//포워딩할  뷰주소를 저장할 변수
		List membersList = null;//배열을 저장할 변수 
		
		//셀렉트 박스에서 선택한 검색 조건이 무엇인지 요청한 값 얻기
		//select option들에서 선택한 하나의 option의 value값 얻기 
		String action = request.getParameter("action");
		
		//셀렉트 박스에서 선택한 검색 조건값이 회원 전체 일떄 
		if(action == null || action.equals("listMembers")) {
			
			//회원 전체를 조회 하기 위해 DAO의 selectAllMemberList메소드 호출!
			//호출하면 조회한 회원정보 한쌍들(MemberVO객체들  또는 HashMap객체들)이 
			//저장된 List를 반환 받는다.
			membersList = dao.selectAllMemberList();
			
			//뷰페이지(test02폴더 안에 만들어져 있는 listMembers.jsp)를 재요청해 
			//보여줄 Model을 request에 바인딩
			request.setAttribute("membersList", membersList);
			
			//포워딩(재요청)할 뷰페이지 주소 저장
			nextPage = "test02/listMembers.jsp";
			
		//셀렉트 박스에서 선택한 검색 조건값이 아이디 일때	
		}else if(action.equals("selectMemberById")) {
			
			//선택한 검색조건값은 이미 action변수에 저장되어 있으므로
			//입력한 검색어 만 request에서 꺼내옵니다.(요청한 값 얻기)
			String id = request.getParameter("value");
						
			//선택한 id열에 대한 값이 입력한 아이디 이면 그에 해당하는 레코드를 조회하기 위해 
			//DAO의 메소드 호출!
			memberVO = dao.selectMemberById(id);
			
			//조회된 회원사람의 정보(MemberVO객체에 저장된 Model정보)를 
			//VIEW에 전달해 전달해 보여주기 위해 임시로 request객체 메모리에 바인딩 
			request.setAttribute("member", memberVO);
			
			//조회한 한사람의 정보를 보여줄 VIEW주소 저장 
			nextPage = "test02/memberInfo.jsp";
			
			
			
		//셀렉트 박스에서 선택한 검색 조건값이 비밀번호 일때 	
		}else if(action.equals("selectMemberByPwd")) {
			
			//선택한 검색조건값은 이미 action변수에 저장되어 있으므로
			//입력한 비밀번호 만 request에서 꺼내옵니다.(요청한 값 얻기)
			int pwd =  Integer.parseInt( request.getParameter("value") );
			
			//입력한 비밀번호에 해당하는 모든 회원정보를 조회 해 옴 
			membersList = dao.selectMemberByPwd(pwd);
			
			//조회한 회원 여러 사람의 정보가 저장된 List배열을 request에 바인딩
			request.setAttribute("membersList", membersList);
			
			//재요청할 뷰페이지 주소 저장
			nextPage = "test02/listMembers.jsp";
			
		}
		
		//대스패처 방식으로 VIEW페이지 포워딩(재요청)시~~ request객체 메모리를 공유!합니다
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
						  dispatcher.forward(request, response);
	
	}	
		
}











