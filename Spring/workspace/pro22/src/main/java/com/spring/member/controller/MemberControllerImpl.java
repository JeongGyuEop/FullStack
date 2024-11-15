package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.service.MemberService;
import com.spring.member.vo.MemberVO;

//MVC������ ������ C������ �ϴ� ����� 


public class MemberControllerImpl extends MultiActionController 
								  implements MemberController {
	// new MemberServiceImpl();��ü�� ������ ���� 
	private MemberService memberService;
	

	//action-servlet.xml���Ͽ��� 
	//MemberControllerImpl�� ���� <bean>���� ��ü(��)�� ������ ��
	//<property>�±׸� �̿��� setMemberService�޼ҵ带 ȣ���Ҷ�...
	//�Ű������� MemberServiceImpl��ü�� ���� �Ͽ� �����ϴ� ��ü�� ���Խ��� �����ϱ� ����
	//�Ʒ��� �޼ҵ尡 �ʿ��ϴ�.	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	
	//��� ȸ�� ���� ��ȸ ��� ��� 
	//   /member/listMembers.do   ��û �ּҰ� ��� ������
	//   DB�� t_member���̺� ����� ��� ȸ�� ��ȸ ��û ���! ����(MemberServiceImpl)���� 
	@Override
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		
	//��û�� �ּҿ� ���� �����Ұ� ����
		//���� MemberServiceImpl��ü�� listMembers()�޼ҵ带 ȣ���Ͽ�
		//��� ȸ�� ��ȸ ��û�� �����!
		//���������� ������ ��ȸ�� �������� ���  List�迭�� ��ȯ �޴´�.
		List membersList = memberService.listMembers();
		
	//������ ���� ������ �� �̸��� ModelAndView��ü �޸𸮿� ���ε� (Ű�� ���� �ѽ����� ��� ����)
		ModelAndView mav = new ModelAndView();
					 mav.addObject("membersList", membersList);//������ Model ���� 
					 mav.setViewName(  getViewName(request) );//������ ��� ���� 
					//��û URL�ּ�  /member/listMembers.do ����  
					//.do�� ������ /listMembers���̸����
		
		
		return mav; //����ó �������� ModelAndView��ȯ 
	}

	@Override
	public String memberForm(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		
		return "/memberForm";
		
	}

	@Override
	public String addMember(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		//VO�� ����
		MemberVO vo = new MemberVO();
				 vo.setId(id);
				 vo.setPwd(pwd);
				 vo.setName(name);
				 vo.setEmail(email);
		
		memberService.addMembers(vo);
		
		return "redirect:/member/listMembers.do";
	}

	//ȸ������  ������ ���� ȸ�� �Ѹ��� ���� ��ȸ ���
	// ������ũ�� ������ ��û �ּ�   ->  /member/memberDetail.do	
	@Override
	public ModelAndView memberDetail(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		
		//http://localhost:8090/pro22/member/memberDetail.do?id=admin1
		
		request.setCharacterEncoding("UTF-8");
		
		//��û�� �� (������ ȸ���� ��ȸ �ϱ� ���� ���̵�)���
		String id = request.getParameter("id");
		
		//���� MemberServiceImpl��ü�� �޼ҵ� ȣ��� vo�� �����Ͽ� SELECT���!
		MemberVO vo = memberService.detailMembers(id);		 
		
		//���������� ��ȸ�� ������ �����ֱ� ���� ModelAndView��ü �޸𸮿� ���ε�
		ModelAndView mav = new ModelAndView( getViewName(request) ); //��� /memberDetail ����
					 mav.addObject("membervo", vo);// ���ε�
					 
					
		return mav;//����ó �������� ModelAndView��ü ��ȯ
	}

	
	//���� ��û /member/UpdateMember.do �ּҸ� �޾�����
	@Override
	public String UpdateMember(HttpServletRequest request, HttpServletResponse reponse) throws Exception {

		request.setCharacterEncoding("UTF-8");
		
		//��û�� ������ �� ���
		//��û�� �� ���
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		System.out.println("������ ȸ�� ���̵� = " + id);
		
		//VO�� ����
		MemberVO vo = new MemberVO();
				 vo.setId(id);
				 vo.setPwd(pwd);
				 vo.setName(name);
				 vo.setEmail(email);
		
		//���� MemberServiceImpl��ü�� �޼ҵ� ȣ��� ������ id�� �����Ͽ� UPDATE���!
		memberService.UpdateMember(vo);		 
			
		//ȸ�� ������ ���ȸ���� ��ȸ �ϴ� ���û �ּ� �ۼ� 
		return "redirect:/member/listMembers.do";
	}

	//������ũ�� ������ ��û �ּ�   ->  /member/memberDel.do
	@Override
	public String memberDel(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
	
		request.setCharacterEncoding("UTF-8");
		
		//ȸ�� ���� ��û�� ������  id���
		String id = request.getParameter("id");
		System.out.println("������ ȸ�� ���̵� : " + id);		
		
		//���� MemberServiceImpl��ü�� �޼ҵ� ȣ���~ ���ڷ� ������ ȸ�� ID�Ѱܼ� DELETE���
		memberService.delMembers(id);
		
		
		//ȸ�� ������ �����ϸ� ���ȸ���� ��ȸ �ؼ� ���� �ֱ� ���� ���ȸ�� ��ȸ ���û!
		//�������� �ּ�â�� ���� �ּҰ� �ڵ����� �����鼭 ���͸� ���� ����ó �������� ��û�� �Ͱ� ����.
		return "redirect:/member/listMembers.do";
	}
	
	//http://localhost:8090/pro21/test/loginForm.do ��û�ּҸ� ������
	//Ȯ���� .do�� ������  loginForm VIEW���� ��� �����ϴ� �޼ҵ� 
	private  String getViewName(HttpServletRequest request) throws Exception {
	    
		// /pro21
		 String contextPath = request.getContextPath();
		
	      String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
	      if(uri == null || uri.trim().equals("")) {
	        
	    	  uri = request.getRequestURI();
	    	  System.out.println("uri : " + uri);
	      }
	      
	      // http://localhost:8090/pro21/test/loginForm.do�� ��û��
	      int begin = 0;  //
	      if(!((contextPath==null)||("".equals(contextPath)))){
	    	  
	    	  //   /pro21  -> ���ڰ��� 6
	         begin = contextPath.length();  // ��ü ��û�� �� ���̸� ����
	      }

	      int end;
	      if(uri.indexOf(";")!=-1){
	         end=uri.indexOf(";");  //��û uri�� ';'�� ���� ��� ';'���� ��ġ�� ����
	      }else if(uri.indexOf("?")!=-1){
	         end=uri.indexOf("?");   //��û uri�� '?'�� ���� ��� '?' ���� ��ġ�� ����
	      }else{
	         end=uri.length();
	      }

	      //http://localhost:8090/pro21/test/loginForm.do�� ��û�� 
	      //���� '.do'�� ������ http://localhost:8081/pro21/test/loginForm�� ���� ��,
	      
	      //�ٽ�http://localhost:8090/pro21/test/loginForm���� 
	      //�������� ù��° '/' ��ġ�� ���� ��, �� ���� loginForm�� ���Ѵ�.
	      String fileName=uri.substring(begin,end);
	      System.out.println("fileName : " + fileName);
	      
	      if(fileName.indexOf(".")!=-1){
	         fileName=fileName.substring(0,fileName.lastIndexOf("."));  //��û���� �������� ���� '.'�� ��ġ�� ������, '.do' �տ������� ���ڿ��� ����
	      }
	      if(fileName.lastIndexOf("/")!=-1){
	         fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length()); //��û���� �������� ���� '/'�� ��ġ�� ������, '/' ���������� ���ڿ��� ����  
	      }
	      
	      System.out.println("result :" + fileName);
	      return fileName;
	   }
	
	

}

















