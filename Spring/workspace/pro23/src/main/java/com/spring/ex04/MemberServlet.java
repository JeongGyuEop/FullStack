package com.spring.ex04;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*

웹브라우저 주소창에 
http://localhost:8090/pro23/mem4.do?action=foreachSelect 주소를 입력하여
이름이 홍길동 또는 이순신 또는 차범근인 경우  t_member테이블에서  회원을 조회 합니다.

select * from t_member
where name in('홍길동','이순신','차범근');


select * from t_member
where name = '홍길동' or  name = '이순신' or  name = '차범근';
-------------------------------------------------------------------

웹브라우저 주소창에  http://localhost:8090/pro23/mem4.do?action=foreachInsert 입력하여 
박길동, 이길동, 김길동  이렇게 세명의 회원정보를 한꺼번에 추가 하는 요청을 합니다. 
*/




@WebServlet("/mem4.do")      
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
		
		// action=memberForm  회원입력 화면 요청
		// action=insertMember  회원추가 요청
		// action=updateMember 수정시 입력하는 화면 요청
		// action=deleteMember 삭제 요청
		// action=searchMember 회원 검색 요청!
		// action=foreachSelect 회원이름이 이순신 또는 홍길동 또는 차범근인 회원 레코드 조회 요청!
		// action=foreachInsert 새로운 3명의 회원정보를 DB에 테이블에 다중 INSERT해줘 요청!
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
			nextPage = "test03/listMembers.jsp";
			
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
	//		membersList = dao.selectMemberByPwd(pwd);
			
			//조회한 회원 여러 사람의 정보가 저장된 List배열을 request에 바인딩
			request.setAttribute("membersList", membersList);
			
			//재요청할 뷰페이지 주소 저장
			nextPage = "test03/listMembers.jsp";
			
		//입력한 회원 정보들을 DB의 t_member테이블에 추가 요청이 들어 왔을때(회원가입)	
		}else if(action.equals("insertMember")) {//MemberVO사용
			
			//회원가입을 위해 DAO의 메소드 호출시 request객체의 주소를 인자로 전달해
			//insert명령!
//			dao.insertMember(request);
			
			//회원가입에 성공하면 모든 회원 정보들을 다시 조회 해서 보여주기 위해
			//서블릿으로 재요청할 주소 저장
			nextPage="/mem4.do?action=listMembers";
			
		//회원 가입 작성 화면 뷰 요청	
		}else if(action.equals("memberForm")) {
			
			nextPage="test03/memberForm.jsp";
			
		//입력한 회원 정보들을 DB의 t_member테이블에 추가 요청이 들어 왔을때(회원가입)		
		}else if(action.equals("insertMember2")) {//HashMap사용
			
			//회원가입을 위해 DAO의 메소드 호출시 request객체의 주소를 인자로 전달해
			//insert명령!
//			dao.insertMember(request);
			
			//회원가입에 성공하면 모든 회원 정보들을 다시 조회 해서 보여주기 위해
			//서블릿으로 재요청할 주소 저장
			nextPage="/mem4.do?action=listMembers";
			
			
		}else if(action.equals("updateMember")) {//수정시 입력하는  VIEW화면 요청
		
			nextPage = "test03/modMember.jsp";
			
			
		}else if(action.equals("updateMemberPro")) {//입력한 회원 레코드 정보 수정 요청
			
//			dao.updateMember(request);//수정을 위해 입력한 정보들(request) 전달 
			
			
			//회원수정에 성공하면 모든 회원 정보들을 다시 조회 해서 보여주기 위해
			//서블릿으로 재요청할 주소 저장
			nextPage="/mem4.do?action=listMembers";
			
			
		}else if(action.equals("deleteMember")) {//한명의 회원정보 삭제 요청을 받았을때
					
			//요청한 값 중에서 삭제할 회원의 아이디 얻기
			String id = request.getParameter("id");
			
			//listMembers.jsp페이지에화면에서
			//클릭한 <a>삭제하기</a>태그 클릭시  전달받은 회원아이디를 
			//DAO의 메소드 호출시 매개변수로 전달해서 삭제 DELETE 명령함!
/*			
			int result = dao.deleteMember(id);
			
			if(result == 1) {//삭제에 성공하면 
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("window.alert('삭제 성공!');");
				out.print("location.href='/pro23_/mem4.do?action=listMembers'");
				out.print("</script>");        
				return; //doHandle메소드를 빠져나가  아래의  포워딩을 막기 위함 				
				
			}else {//삭제에 실패하면 0
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("window.alert('삭제 실패!');");
				out.print("history.go(-1);");
				out.print("</script>");
				return;				
			}					
*/			
			
	
	    //입력한 이름과 이메일에 해당하는 회원 검색 요청!
		}else if(action.equals("searchMember")) {
				
			//요청한 조건값 얻기
			String name = request.getParameter("name");
			String email = request.getParameter("email");
				
			//vo에 저장
			memberVO = new MemberVO();
			memberVO.setName(name);
			memberVO.setEmail(email);
			
			//회원 검색후 리스트 반환
//			membersList = dao.searchMember(memberVO);
			
		
			
			//VIEW화면에 응답하기 위해 request에 Model(List배열) 바인딩
			request.setAttribute("membersList", membersList);
			
			//응답할 뷰 주소 저장
			nextPage = "test03/listMembers.jsp";
		
		
		//회원 이름이 홍길동 또는 이순신 또는 차범근인 레코드의 모든 열값 조회 
		}else if(action.equals("foreachSelect")) {
			//검색 조건에 들어갈 회원이름들(문자열들)을 ArrayList배열에 저장합니다
			List<String> nameList = new ArrayList<String>();
						 nameList.add("홍길동");
						 nameList.add("차범근");
						 nameList.add("이순신");
			
//			membersList = dao.foreachSelect(nameList);
			
			request.setAttribute("membersList", membersList);//바인딩
			
			//응답할 뷰 주소 저장
			nextPage = "test03/listMembers.jsp";
		
		//박길동, 이길동, 김길동 새 회원 정보 3쌍을 한방에 DB의 t_member테이블에 다중 insert시켜줘~
		//라는 요청 주소를 받았을때..........	
		}else if (action.equals("foreachInsert")) {
			
			List<MemberVO> memList = new ArrayList<MemberVO>();
			
			//박길동 정보를 MemberVO객체의 변수에 저장한 후 ArrayList배열에 추가
			memList.add(new MemberVO("m1", "1234", "박길동", "m1@test.com"));			
			
			//이길동 정보를 MemberVO객체의 변수에 저장한 후 ArrayList배열에 추가
			memList.add(new MemberVO("m2", "1234", "이길동", "m2@test.com"));		
			
			//김길동 정보를 MemberVO객체의 변수에 저장한 후 ArrayList배열에 추가
			memList.add(new MemberVO("m3", "1234", "김길동", "m3@test.com"));			
			
			//ArrayList배열 주소 자체를 DAO의 메소드 호출시 인자로 전달해 다중 INSERT명령
//			int result = dao.foreachInsert(memList);
			
//			System.out.println("다중 INSERT 성공 result변수값 : " + result);
			
			
			//다중 INSERT 후에 ~  모든 회원정보들 조회 요청하기 위한 요청주소저장
			nextPage = "/mem4.do?action=listMembers";
			
		}
		
		//대스패처 방식으로 VIEW페이지 포워딩(재요청)시~~ request객체 메모리를 공유!합니다
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
						  dispatcher.forward(request, response);
	
	}	
		
}











