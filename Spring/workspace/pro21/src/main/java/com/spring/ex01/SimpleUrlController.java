package com.spring.ex01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/*
 * 	MVC �߿��� C ������ �ϴ� Ŭ����
 * 
 * action-servlet.xmp ���� ������ ��û�� ó���ϱ� ����
 * Controller �������̽��� �����ؼ� ������ �ϸ�
 * SimpleUrlController Ŭ������ ��û�� ������
 * ModelAndView ��ü �޸𸮸� �����Ͽ� ���������� ������ VIEW�̸��� 
 * index.jsp �߿��� index��
 * �����ڷ� �����Ͽ� ModelAndView ��ü �޸𸮿� ���ε�(����)��
 * ����ó �������� ��ȯ�Ѵ�.
 * 
 */


public class SimpleUrlController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, 
									  HttpServletResponse response) 
											  throws Exception {
		
		// �۾��� ��ģ�� ���������� ������ ���̸��� 
		// ModelAndView ��ü �޸𸮿� ������
		// ����ó �������� ModelAndView ��ü�� ��ȯ
		return new ModelAndView("index");
		
	}
	
}
