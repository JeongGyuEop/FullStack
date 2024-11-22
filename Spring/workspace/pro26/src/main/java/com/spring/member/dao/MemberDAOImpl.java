package com.spring.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.member.vo.MemberVO;



/* 
<bean id="memberDAO"   class="com.spring.member.dao.MemberDAOImpl"></bean>
*/
@Repository("memberDAO")
//사원  DB작업을 직접 하는 클래스 
public class MemberDAOImpl implements MemberDAO{

	// action-mybatis.xml 파일에 작성해 놓은
	// <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate"></bean>의
	// id를 이용하여 아래의 sqlSession 변수에 @Autowired 로 자동 주입한다.
	@Autowired
	private SqlSession sqlSession;
	
		
	//모든 회원 조회 
	@Override
	public List selectAllMembers() throws DataAccessException {
		
		//SqlSesssionTemplate내부에 주입된 SqlSessionFactoryBean의 selectList 메소드 호출!
		return sqlSession.selectList("mapper.member.selectAllMemberList");
	
	}//selectAllMembers메소드 닫는 기호 


	//회원 추가 기능 
	@Override
	public void InsertMember(MemberVO memberVO) throws DataAccessException {
			
		//SqlSessionFactoryBean의 insert메소드 호출!
		sqlSession.insert("mapper.member.insertMember",  memberVO );
		
	}


	//회원 삭제 기능 
	@Override
	public int DeleteMember(String id) throws DataAccessException {
		
		//SqlSessionFactoryBean의 delete메소드 호출!
		int result = sqlSession.delete("mapper.member.deleteMember", id);
	 
		return result;                  
	}


	//회원정보  수정을 위해 회원 한명의 정보 조회 기능
	@Override
	public MemberVO oneMember(String id) throws DataAccessException {

		return  (MemberVO)sqlSession.selectOne("mapper.member.selectMemberById", id);
		
	}


	//회원정보 수정기능 
	@Override
	public void UpdateMember(MemberVO memberVO) throws DataAccessException {
	
		sqlSession.update("mapper.member.updateMember", memberVO);
		
		
	}
}//MemberDAOImpl클래스 닫는 기호 

















