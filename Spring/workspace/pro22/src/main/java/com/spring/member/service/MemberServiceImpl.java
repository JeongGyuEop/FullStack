package com.spring.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.member.dao.MemberDAO;
import com.spring.member.vo.MemberVO;

//����
public class MemberServiceImpl implements MemberService {


	//��� MemberDAOImpl��ü�� ���Թ޾� ������ �������� ����
	private MemberDAO memberDAO;// = new MemberDAOImpl();
	
	//action-service.xml���� <property>�±׿� ���� ȣ��Ǵ� �޼ҵ��
	//�ݵ�� ����� ���ƾ� �մϴ�.
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	//��� ȸ�� ���� ��ȸ ���!  ������� (MemberDAOImpl)
	@Override
	public List listMembers() throws DataAccessException {
	
		//��� MemberDAOImpl��ü�� selectAllMembers()�޼ҵ带 ȣ���� 
		//��� ȸ�� ���� ��ȸ ��û�� �����!
		//��ȸ�� ȸ��������(MemberVO��ü��)�� �����  List�迭�� ��ȯ ���� 		
		List membersList = memberDAO.selectAllMembers();
		
		return membersList;//����� MemberControllerImpl��ü�� ��ȯ
	}

	@Override
	public void addMembers(MemberVO vo) throws DataAccessException {
		memberDAO.InsertMember(vo);
	}

	//ȸ�� ���� ������ ����  ������ ȸ�� ���� �ϳ� ��ȸ ��� �ϴ� �޼ҵ� 
	@Override
	public MemberVO detailMembers(String id) throws DataAccessException {
		
		return memberDAO.oneMember(id);
	}

	//ȸ�� ���� ���� UPDATE ����ϴ� �޼ҵ� 
	@Override
	public void UpdateMember(MemberVO vo) throws DataAccessException {
		
		memberDAO.UpdateMember(vo);
		
	}

	//ȸ�� ���� ���
	@Override
	public void delMembers(String id) throws DataAccessException {
		
		memberDAO.DeleteMember(id);
	}

	
	
	
}












