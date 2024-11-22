package com.spring.account;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;

//사원
//MyBatis기능을 이용하여 DB작업  명령 하는 클래스 
public class AccountDAO {
	
	/*
	   action-mybatis.xml 설정파일에서
	   
	    1. PooledDataSource커넥션풀 빈 -> SqlSessionFactoryBean객체의 변수에 주입
	    2. SqlSessionFactoryBean객체의 주소를 -> SqlSessionTemplate객체의 변수에 주입
	    3. SqlSessionTemplate객체의 주소를  -> AccountDAO객체의 변수에 주입 
	 */
	private SqlSession sqlSession; //SqlSessionTemplate객체의 주소를 저장(주입)
		
	//Setter
	public void setSqlSession(SqlSession sqlSession) {	
		this.sqlSession = sqlSession;	
	}
	
	//홍길동 계좌에서 500만원 차감 UPDATE
	public void updateBalance1() throws DataAccessException {
	
		sqlSession.update("mapper.account.updateBalance1"); //account.xml 
	}
	//김유신 계좌에서 500만원 증가 UPDATE
	public void updateBalance2() throws DataAccessException {
	
		sqlSession.update("mapper.account.updateBalance2"); //account.xml로 가자 
	}
	
	

}













