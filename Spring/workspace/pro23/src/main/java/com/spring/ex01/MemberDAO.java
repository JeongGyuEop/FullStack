package com.spring.ex01;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
	DAO클래스
	(Data Access Object)
	- 데이터베이스의 Data에 접근하기 위한 객체를 생성할 클래스 
	- 데이터베이스의 Data에 접근하기 위한 코드와   
	    데이터베이스연결 후  조회, 추가, 수정, 삭제 등의 CRUD작업을 할 수 있는 클래스 
*/

public class MemberDAO {

	//SqlMapper객체를 저장할 변수 선언
	private static SqlSessionFactory sqlMapper = null;
	
	//SqlMapper객체를 얻어서 반환하는 메소드 
	public static SqlSessionFactory getInstance() {
		
		//SQL문을 실행할 SqlMapper객체가 sqlMapper참조변수에 저장되어 있지 않으면?
		if(sqlMapper == null) {
			try {
				//MemberDAO의 각 메소드 호출시......
				//src/mybatis/SqlMapConfig.xml에서 설정정보를 읽은후  데이터베이스와 연결을 준비합니다.
				String resource = "mybatis/SqlMapConfig.xml";
				Reader reader = Resources.getResourceAsReader(resource);
				
				//마이바티스 프레임워크에서 제공하는 SqlMapper객체를 가져옵니다.
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return sqlMapper; 	
	}
	
	//DB에 저장된 모든 회원을 가입일 기준으로 내림차순 정렬해서 조회하는 기능의 메소드
	public List<MemberVO> selectAllMemberList(){
		
		//SqlMapper객체 얻기
		sqlMapper = getInstance();
		
		//실제 member.xml(매퍼파일)에 접근하여 SQL문을 호출하여 실행할 메소드를 가지고 있는  SqlSession객체 얻기
		SqlSession session = sqlMapper.openSession();
		
		//모든 회원정보를 조회 하기 위해 SqlSession객체의 selectList메소드 호출시
		//select구문이 저장된 매퍼파일의  <mapper>태그의 namespace속성의 값과 
		//<mapper></mapper>태그 안에 작성해 놓은 <select>태그의 id속성값을  하나의 문자열로 만들어 전달 하면  select구문을 실행하게 됩니다.
		List<MemberVO> memlist = session.selectList("mapper.member.selectAllMemberList");
		
		return memlist;//조회된 회원정보들이 담긴 List를  MemberServlet으로 반환 
		/*
		 참고.
			SqlSession클래스
			- mapper파일(member.xml)에 작성된 SQL문을 실행하고 트랜잭션을 관리하는 클래스 입니다.  
			- 메소드 종류
				1. selectOne메소드
				    T selectOne()   ->  조회한 하나의 객체를 리턴하는 메소드 
				    			    
				2. selectList메소드 
					List<E> selectList()  -> 조회한 여러개의 객체가 담긴 ~~ 리스트를 리턴하는 메소드 
					
				3. selectMap메소드 
					Map<E> selectMap()  -> 조회한 여러객체들의 Map에 key,value형태로 담긴  Map을 리턴하는 메소드 
		*/	
	}//selectAllMemberList메소드 닫기 	
	
	
}














