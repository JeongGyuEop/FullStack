package com.spring.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.member.vo.MemberVO;

/*
 ������ �����ӿ�ũ���� ������ �ִ� ������ JDBC��ɿ� ���ؼ�..
 	1. ���������� �����ϴ� JdbcTemplateŬ���� ����
 		- JdbcTemplateŬ������ ����ϸ�  Ŀ�ؼ�Ǯ�̳� Connection DB���� ���,
 		  PreparedStatement���ఴü, ResultSet��ü�� ���� �����ؼ� ������� �ʰ�,
 		  JdbcTemplateŬ�������� �����ϴ� �޼ҵ带 �̿��Ͽ� SQL�� ���� �����Ҽ� �ִ�.
 	
 	2. JdbcTemplateŬ������ �����ϴ� ��������  SQL���� ���� �޼ҽ��� 
		
		2.1 select���� DB�� �����Ͽ� ��ȸ ����� �����ϴ� �����ε��� ��ǥ���� �޼ҵ� �ϳ�
			
			"select  * from t_member"
			
			List<T> query(String sql, RowMaaper<T> rowMapper)

			���� : query()�޼ҵ�� ù��° �Ű����� sql�� !! ��ȸ�� SELECT���� �����ϰ�,
				 �ι�° �Ű����� rowMapper�� 
				 RowMapper�������̽��� mapRow(ResultSet rs, int rowNum)�߻�޼ҵ带
				 �������̵��� �����͸�ü�� �����ϰ� �˴ϴ�.
				 
				 �ι�° �Ű����� rowMapper�� ������ �����͸�ü ������ �������̵���Ų
				 mapRow�޼ҵ��  query�޼ҵ��� ù��° �Ű����� sql�� ���޵�
				 select���� ��ȸ�� ���(ResultSet)�� �ϳ��� �� ������ �ݺ��ؼ�  �о��
				 VO��ü�� �����Ͽ� ������ ��
				 VO��ü���� �ٽ� List�迭�� ��� List�迭�� ���� �մϴ�. 

		 
		   2.2 SQL�� DML �� SELECT�� �������� ��  ��ȸ�� ������  ��� ���� ��ü�� �����Ͽ� �޾ƿ��� ����  
		              ����ϴ�  qyeryForObject() �޼ҵ��̴�.
		 	  
		 	  
				"select  * from t_member where ���ǿ�=���ǰ�";
			
				Object  queryForObject(String sql, RowMaaper<T> rowMapper)


	        2.3  JdbcTemplate�� �̿��� INSERT, UPDATE, DELETE ���� ������  update()�޼ҵ带 �������
	        		
				update() �޼����� ����
				
				  1. int update(String sql)
				
				  2. int update(String sql , Object... args)

				����
				public void update(Member member) {
        			jdbcTemplate.update( "update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
                							member.getName(), member.getPassword(), member.getEmail() );
    }
*/

//���  DB�۾��� ���� �ϴ� Ŭ���� 

public class MemberDAOImpl implements MemberDAO{

	//JdbcTempleŬ������ ��ü�� ������ ���� ����
	private JdbcTemplate jdbcTemplate;
	
	//setter
	//action-dataSource.xml���Ͽ���
	//<bean>�±׷� MemberDAOImpl��ü�� �����ϰ�
	//<bean>�±׷� ������ SimpleDriverDataSourceĿ�ؼ�Ǯ��ü��
	//<property>�±׿� ���� MemberDAOImpl�� �ۼ���
	//new JdbcTemplate()��ü�� �����Ű�� ����
	//�Ʒ��� setDataSource�޼ҵ带 ����� ���ƾ� �մϴ�.
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	
	//��� ȸ�� ��ȸ 
	@Override
	public List selectAllMembers() throws DataAccessException {
		
		//SELECT��
		String query = "select id,pwd,name,email,joinDate"
					 + " from t_member order by joinDate desc";
		
		
		//��ȸ�� ȸ���������� �����ų �迭�� ������ ����
		List membersList = null;
		
		
		//JdbcTemplate�� query�޼ҵ带 ȣ���~~
		//ù��° �Ű������� ��ȸ�� SELECT������ ������ 
		//��ȸ�� ���ڵ��� ������ŭ �ݺ��Ͽ� MemberVO��ü�� �����մϴ�.
		//�� ���ڵ��� ���� MemberVO��ü�� �ν��Ͻ������� �����ϰ�  
		//�ٽ�~~~~~ ArrayList�迭�� MemberVO��ü�� �ݺ��ؼ� �����ŵ�ϴ�.
		//���������� query�޼ҵ�� ���� ArrayList�迭�� ��ȯ�� �ݴϴ�.
		membersList = jdbcTemplate.query(query, new  RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				//��ȸ�� ���� ��ġ ��ȣ�� 0���� �Ű����� int rowNum���� �Ѱ� �޴´�.
				System.out.println(rowNum);
				
				//MemberVO��ü ���� ���� setter�� �̿��Ͽ�
				//��ȸ�� �� ���� ������ ResultSet���� ������ MemberVO�� ���� �� ����
				MemberVO membervo = new MemberVO();
						 membervo.setId(  rs.getString("ID") );
						 membervo.setPwd( rs.getString("PWD") );
						 membervo.setName(  rs.getString("NAME") );
						 membervo.setEmail( rs.getString("EMAIL"));
						 membervo.setJoinDate(  rs.getDate("JOINDATE") );
				
				
				return membervo;
			}	
		});
				
				
						
		//ArrayList�迭�� ��ȸ�� ������(MemberVO��ü��)�� ����Ǿ� �ִ� ����
		//����Ǿ� ���� ���� ��츦 �����ؼ� ���� ��ų�� �ִ�.
		//MemberServiceImpl���忡�� ����
		return  membersList.isEmpty() ? null : membersList;
	
	}//selectAllMembers�޼ҵ� �ݴ� ��ȣ 


	//ȸ�� �߰� ��� 
	@Override
	public void InsertMember(MemberVO vo) throws DataAccessException {
	
//		String query = 
//				"INSERT INTO T_MEMBER (ID, PWD, NAME, EMAIL)" +
//				"VALUES ('"+vo.getId()+"','"+vo.getPwd()+"',"
//					  + "'"+vo.getName()+"','"+vo.getEmail()+"')";
//		
//		this.jdbcTemplate.update(query);
		
		
		String query = "INSERT INTO T_MEMBER (ID, PWD, NAME, EMAIL)" +
					   "VALUES(?,?,?,?)";
		
		this.jdbcTemplate.update(query, 
								 vo.getId(), vo.getPwd(), vo.getName(), vo.getEmail());
		
		
		
	}


	//ȸ�� ���� ��� 
	@Override
	public void DeleteMember(String id) throws DataAccessException {
		
		String query = "DELETE FROM T_MEMBER WHERE id='"+id+"'";
		
		this.jdbcTemplate.update(query);
		
	}


	//ȸ������  ������ ���� ȸ�� �Ѹ��� ���� ��ȸ ���
	@Override
	public MemberVO oneMember(String id) throws DataAccessException {

		String query = "SELECT * FROM T_MEMBER WHERE id='"+id+"'";
		
	    MemberVO vo = this.jdbcTemplate.queryForObject(query, new RowMapper<MemberVO>() {
		   
		   @Override
		   public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
				 MemberVO memberVO = new MemberVO();
				 memberVO.setId(rs.getString("id"));
				 memberVO.setPwd(rs.getString("pwd"));
				 memberVO.setName(rs.getString("name"));
				 memberVO.setEmail(rs.getString("email"));
				 memberVO.setJoinDate(rs.getDate("joinDate"));
			
				 return memberVO;   
		   }	
	});
	
	    return vo;
	}


	//ȸ������ ������� 
	@Override
	public void UpdateMember(MemberVO vo) throws DataAccessException {
				
		this.jdbcTemplate.update( "update t_member set  pwd=?, name=?, email=? where id=?",
									vo.getPwd(), vo.getName(), vo.getEmail(), vo.getId() );
		
	}
}//MemberDAOImplŬ���� �ݴ� ��ȣ 
















