package com.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface MemberController {

	//��� ȸ�� ���� ��ȸ ���
	public ModelAndView listMembers(HttpServletRequest request,  
			                        HttpServletResponse reponse)
									throws Exception;
	
	//ȸ������ ȭ�� �̵� ���
	public String memberForm(HttpServletRequest request,  
				             HttpServletResponse reponse)
						     throws Exception;
	
	//ȸ������ ���
	public String addMember(HttpServletRequest request,  
				            HttpServletResponse reponse)
						     throws Exception;	
	
	//ȸ������  ������ ���� ȸ�� �Ѹ��� ���� ��ȸ ���
	public ModelAndView memberDetail(HttpServletRequest request,  
						            HttpServletResponse reponse)
									throws Exception;	
	
	//ȸ�� �Ѹ� ���� ���� ���
	public String UpdateMember(HttpServletRequest request,  
				               HttpServletResponse reponse)
				            			throws Exception;	
	//ȸ������ ���� ���
	public String memberDel(HttpServletRequest request,  
            				HttpServletResponse reponse)
        								throws Exception;	
	//ȸ���α��� ó�� ���
	
    //ȸ���α׾ƿ� ó�� ���
	
}














