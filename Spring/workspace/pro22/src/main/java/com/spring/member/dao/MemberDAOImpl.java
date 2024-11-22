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
 스프링 프레임워크에서 제공해 주는 스프링 JDBC기능에 대해서..
 	1. 스프링에서 제공하는 JdbcTemplate클래스 역할
 		- JdbcTemplate클래스를 사용하면  커넥션풀이나 Connection DB연결 얻기,
 		  PreparedStatement실행객체, ResultSet객체를 직접 생성해서 사용하지 않고,
 		  JdbcTemplate클래스에서 제공하는 메소드를 이용하여 SQL을 쉽게 실행할수 있다.
 	
 	2. JdbcTemplate클래스가 제공하는 여러가지  SQL실행 관련 메소스들 
		
		2.1 select문을 DB에 실행하여 조회 기능을 제공하는 오버로딩된 대표적인 메소드 하나
			
			"select  * from t_member"
			
			List<T> query(String sql, RowMaaper<T> rowMapper)

			설명 : query()메소드는 첫번째 매개변수 sql로 !! 조회할 SELECT문을 전달하고,
				 두번째 매개변수 rowMapper로 
				 RowMapper인터페이스의 mapRow(ResultSet rs, int rowNum)추상메소드를
				 오버라이딩한 내부익명객체를 전달하게 됩니다.
				 
				 두번째 매개변수 rowMapper로 전달한 내부익명객체 내부의 오버라이딩시킨
				 mapRow메소드는  query메소드의 첫번째 매개변수 sql로 전달된
				 select문의 조회된 결과(ResultSet)를 하나의 행 단위로 반복해서  읽어와
				 VO객체를 생성하여 저장한 후
				 VO객체들을 다시 List배열에 담아 List배열을 리턴 합니다. 

		 
		   2.2 SQL의 DML 중 SELECT를 실행했을 때  조회된 한행의  결과 값을 객체에 저장하여 받아오기 위해  
		              사용하는  qyeryForObject() 메소드이다.
		 	  
		 	  
				"select  * from t_member where 조건열=조건값";
			
				Object  queryForObject(String sql, RowMaaper<T> rowMapper)


	        2.3  JdbcTemplate을 이용한 INSERT, UPDATE, DELETE 쿼리 실행은  update()메소드를 사용하자
	        		
				update() 메서드의 종류
				
				  1. int update(String sql)
				
				  2. int update(String sql , Object... args)

				예제
				public void update(Member member) {
        			jdbcTemplate.update( "update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
                							member.getName(), member.getPassword(), member.getEmail() );
    }
*/

//사원  DB작업을 직접 하는 클래스 

public class MemberDAOImpl implements MemberDAO{

	//JdbcTemple클래스의 객체를 저장할 변수 선언
	private JdbcTemplate jdbcTemplate;
	
	//setter
	//action-dataSource.xml파일에서
	//<bean>태그로 MemberDAOImpl객체를 생성하고
	//<bean>태그로 생성한 SimpleDriverDataSource커넥션풀객체를
	//<property>태그에 의해 MemberDAOImpl에 작성된
	//new JdbcTemplate()객체에 저장시키기 위해
	//아래의 setDataSource메소드를 만들어 놓아야 합니다.
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	
	//모든 회원 조회 
	@Override
	public List selectAllMembers() throws DataAccessException {
		
		//SELECT문
		String query = "select id,pwd,name,email,joinDate"
					 + " from t_member order by joinDate desc";
		
		
		//조회한 회원정보들을 저장시킬 배열을 저장할 변수
		List membersList = null;
		
		
		//JdbcTemplate의 query메소드를 호출시~~
		//첫번째 매개변수로 조회할 SELECT문장을 전달해 
		//조회한 레코드의 갯수만큼 반복하여 MemberVO객체를 생성합니다.
		//각 레코드의 값을 MemberVO객체의 인스턴스변수에 저장하고  
		//다시~~~~~ ArrayList배열에 MemberVO객체를 반복해서 저장시킵니다.
		//마지막으로 query메소드는 최종 ArrayList배열을 반환해 줍니다.
		membersList = this.jdbcTemplate.query(query, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				//조회한 행의 위치 번호는 0부터  매개변수로 넘겨 받는다.
				System.out.println(rowNum);
				
				MemberVO memberVO = new MemberVO();
						 memberVO.setId(rs.getString("id"));
						 memberVO.setPwd(rs.getString("pwd"));
						 memberVO.setName(rs.getString("name"));
						 memberVO.setEmail(rs.getString("email"));
						 memberVO.setJoinDate(rs.getDate("joinDate"));
				
				
				return memberVO;
			}
			
		});
				
		//ArrayList배열에 조회된 정보들(MemberVO객체들)이 저장되어 있는 경우와
		//저장되어 있지 않은 경우를 구분해서 리턴 시킬수 있다.
		//MemberServiceImpl부장에게 리턴
		return  membersList.isEmpty() ? null : membersList;
	
	}//selectAllMembers메소드 닫는 기호 


	//회원 추가 기능 
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


	//회원 삭제 기능 
	@Override
	public void DeleteMember(String id) throws DataAccessException {
		
		String query = "DELETE FROM T_MEMBER WHERE id='"+id+"'";
		
		this.jdbcTemplate.update(query);
		
	}


	//회원정보  수정을 위해 회원 한명의 정보 조회 기능
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


	//회원정보 수정기능 
	@Override
	public void UpdateMember(MemberVO vo) throws DataAccessException {
				
		this.jdbcTemplate.update( "update t_member set  pwd=?, name=?, email=? where id=?",
									vo.getPwd(), vo.getName(), vo.getEmail(), vo.getId() );
		
	}
}//MemberDAOImpl클래스 닫는 기호 







