package com.spring.ex03;

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
	
	
	//id열 의 값이 'hong'인 회원이름을 조회 하는 메소드 
	public String selectName() {
		//SqlMapper객체 얻기
		sqlMapper = getInstance();
		
		//실제 member.xml(매퍼파일)에 접근하여 SQL문을 호출하여 실행할 메소드를 가지고 있는  SqlSession객체 얻기
		SqlSession session = sqlMapper.openSession();
		
		//SqlSession객체의 selectOne메소드를 호출시
		//매퍼파일(member.xml)에 작성 해놓은 <mapper></mapper>태그 내부에 접근하기 위해
		//namespace속성의 값과   <select>태그의 id속성값을 하나의 문자열로 만들어 전달 합니다.
		String name = session.selectOne("mapper.member.selectName");
		
		return name; //조회한 회원 이름 (MemberServlet으로 )리턴 
				
	}
	
	//id열의 값이 'hong'인 회원의 비밀번호를 정수로 조회 하는 기능의 메소드
	public int selectPwd() {
		
		//SqlMapper객체 얻기
		sqlMapper = getInstance();
		
		//실제 member.xml(매퍼파일)에 접근하여 SQL문을 호출하여 실행할 메소드를 가지고 있는  SqlSession객체 얻기
		SqlSession session = sqlMapper.openSession();		
		

		//매퍼파일(member.xml)에 작성해 놓은
		//<member>태그의 namespace속성 값 mapper.member과 
		//<select>태그의 id속성 값 selectPwd을  selectOne메소드호출시  경로를 전달하면
		//select pwd from t_member where id='hong' 구문을 실행 하여 
		//조회한 비밀번호를 반환 받습니다. 
		int pwd = session.selectOne("mapper.member.selectPwd");
		
		return pwd;
	}
	
	
	
	//search.jsp에서 입력한 검색 아이디를 매개변수로 전달 받아서 DB에 있는 회원 한사람 조회
	public MemberVO selectMemberById(String id) {
		//SqlMapper객체 얻기
		sqlMapper = getInstance();
		
		//실제 member.xml(매퍼파일)에 접근하여 SQL문을 호출하여 실행할 메소드를 가지고 있는  SqlSession객체 얻기
		SqlSession session = sqlMapper.openSession();
		
		/*
		레코드 한 행 만 조회 할때 ...  selectOne메소드 호출!
		호출시!~ 
		
		첫번재 매개변수로 전달할 값은  member.xml파일 내부에 작성된  <mapper namespace="mapper.member"></mapper> 태그의
		namespace속성값인 "mapper.member"와 
		<select id="selectMemberById"></select>태그의  id속성에 적힌 값 "selectMemberById"를 하나의 문자열로 합쳐저 전달하고,
		
	        두번째 매개변수로 전달할 값은  실행할 select구문의  where조건절에 사용될 조건값(입력한 검색아이디값)을 전달합니다.
	        
	     selectOne메소드는 항상 ~~ 조회한 한줄의 정보를 저장하는 ~~ 자바빈 역할의  VO 또는 Map을 반환 해 줍니다.   	
		*/			
		MemberVO memberVO = session.selectOne("mapper.member.selectMemberById", id);
		
		return memberVO;//조회된 한행의 정보가 저장된 MemberVO객체의 주소를 서블릿으로 반환 합니다.
		
	}
	
	//입력한 비밀번호가 저장된 모든 회원을 조회
	public List selectMemberByPwd(int pwd) {
		//SqlMapper객체 얻기
		sqlMapper = getInstance();
		
		//실제 member.xml(매퍼파일)에 접근하여 SQL문을 호출하여 실행할 메소드를 가지고 있는  SqlSession객체 얻기
		SqlSession session = sqlMapper.openSession();
		
		//입력한 비밀번호가 같은 회원이 여러명이 조회될수 있으므로 
		//selectOne메소드 대신 selectList메소드로 조회 요청합니다.
		return session.selectList("mapper.member.selectMemberByPwd", pwd);
			
	}
	
	
	
	
}














