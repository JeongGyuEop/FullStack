package com.spring.ex04;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import InterfaceMappers.MemberMapper;
import InterfaceMappers.MyBatisConfig;

/*

MyBatis가 동작하는 방식
    MyBatis는 Java의 동적 프록시(Dynamic Proxy) 기능을 활용합니다.
    동적 프록시는 인터페이스를 기반으로 구현체를 생성하지 않고도 객체를 동적으로 생성할 수 있는 기술입니다.

기본 과정
    MyBatis가 session.getMapper(MemberMapper.class)를 호출할 때:
   
        MyBatis는 내부적으로 Java의 Proxy 클래스를 사용하여 동적 구현체를 생성합니다.
        
        동적 구현체는 Mapper 인터페이스의 메서드 호출을 가로채고, MyBatis 내부 코드로 요청을 전달합니다.
        
        MyBatis는 SQL 매핑 정보를 바탕으로 해당 메서드와 연결된 SQL 구문을 실행합니다.
*/	


/*
	MemberDAO클래스
	데이터베이스와 연결 및 데이터 접근을 담당하며
	MyBatis의 Mapper인터페이스를 활용하여 작업 처리
*/
public class MemberDAO{
	
	//SqlMapper객체 얻어 변수에 저장
	//방법. MyBatisConfig.java에 작성했던 getSqlSessionFactory()메소드 호출!
	private static SqlSessionFactory sqlMapper = MyBatisConfig.getSqlSessionFactory();
	
	//DB에 저장된 모든 회원 정보를 조회 하여 가입일 기분으로 내림차순 정렬된 리스트를 반환하는 메소드
	public List<MemberVO> selectAllMemberList(){
		
		//Sqlsession객체를 저장할 변수 
		SqlSession session = null;
		
		try {
			//SqlSession객체 얻기 
			session = sqlMapper.openSession();
			
			//MemberMapper인터페이스에 만들어 놓은 추상메소드를 오버라이딩한 
			//자식 구현 객체를 만들어서 동적으로 반환 해줌
			//1. MemberMapper 인터페이스를 가져오고,
			//2. 이를 기반으로 생성된 동적 프록시 객체를 반환하며,
			//3. 이후 이 객체를 통해 SQL을 실행할 수 있게 해줍니다.
			MemberMapper mapper = session.getMapper(MemberMapper.class);

			
			return mapper.selectAllMemberList();
			
		} catch (Exception e) {
			//예외 발생시 스택 트레이스 정보 출력
			e.printStackTrace();
			throw new RuntimeException("Error fetching member list", e);
		}finally {
			if(session != null) session.close();
		}
	}
	
	
	//특정 ID를 가진 회원 정보를 조회하는 메소드 
	public MemberVO selectMemberById(String id) {
		
		//Sqlsession객체를 저장할 변수 
		SqlSession session = null;
		
		try {
			//SqlSession객체 얻기 
			session = sqlMapper.openSession();
			
			//MemberMapper인터페이스에 만들어 놓은 추상메소드를 오버라이딩한 
			//자식 구현 객체를 만들어서 동적으로 반환 해줌
			//1. MemberMapper 인터페이스를 가져오고,
			//2. 이를 기반으로 생성된 동적 프록시 객체를 반환하며,
			//3. 이후 이 객체를 통해 SQL을 실행할 수 있게 해줍니다.
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			
			return mapper.selectMemberById(id);
			
		} catch (Exception e) {
			//예외 발생시 스택 트레이스 정보 출력
			e.printStackTrace();
			throw new RuntimeException("Error fetching member list", e);
		}finally {
			if(session != null) session.close();
		}
		
	}
	
	
}











/*
	DAO클래스
	(Data Access Object)
	- 데이터베이스의 Data에 접근하기 위한 객체를 생성할 클래스 
	- 데이터베이스의 Data에 접근하기 위한 코드와   
	    데이터베이스연결 후  조회, 추가, 수정, 삭제 등의 CRUD작업을 할 수 있는 클래스 
*/
/*
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
		
		  
		레코드 한 행 만 조회 할때 ...  selectOne메소드 호출!
		호출시!~ 
		
		첫번재 매개변수로 전달할 값은  member.xml파일 내부에 작성된  <mapper namespace="mapper.member"></mapper> 태그의
		namespace속성값인 "mapper.member"와 
		<select id="selectMemberById"></select>태그의  id속성에 적힌 값 "selectMemberById"를 하나의 문자열로 합쳐저 전달하고,
		
	        두번째 매개변수로 전달할 값은  실행할 select구문의  where조건절에 사용될 조건값(입력한 검색아이디값)을 전달합니다.
	        
	     selectOne메소드는 항상 ~~ 조회한 한줄의 정보를 저장하는 ~~ 자바빈 역할의  VO 또는 Map을 반환 해 줍니다.   	
					
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
	
	//회원 가입 
	public int insertMember(HttpServletRequest request) {
				
		//SqlMapper객체 얻기
		sqlMapper = getInstance();
		
		//실제 member.xml(매퍼파일)에 접근하여 SQL문을 호출하여 실행할 메소드를 가지고 있는  SqlSession객체 얻기
		SqlSession session = sqlMapper.openSession();		
		
		//입력한 가입할 정보들을 request객체에서 얻어서
		//HashMap객체 하나 생성해서 key-value한쌍으로 묶어 저장
		Map<String, String> memberMap = new HashMap<String, String>();
							memberMap.put("id",request.getParameter("id"));
							memberMap.put("pwd",request.getParameter("pwd"));
							memberMap.put("name",request.getParameter("name"));
							memberMap.put("email",request.getParameter("email"));
							
							
		//입력한 가입할 정보들을 request객체에서 얻어서
		//MemberVO객체 하나 생성해서 변수에 저장
//		MemberVO memberVO = new MemberVO();
//				 memberVO.setId(request.getParameter("id"));
//				 memberVO.setPwd(request.getParameter("pwd"));
//				 memberVO.setName(request.getParameter("name"));
//				 memberVO.setEmail(request.getParameter("email"));
	
		
		
		
		//조회와는 달리 DB의 테이블에 새 회원를 추가할떄는 
	    //Sqlsession객체의  insert()메소드를 사용하자!!!
		int result = session.insert("mapper.member.insertMember2", memberMap);
		
		//int result = session.insert("mapper.member.insertMember", memberVO);
		
		//영구반영
		session.commit(); // 수동 커밋 꼬옥~ 해주자~
		
		return result; //insert에 성공하면 1을 반환 , 실패하면 0을 반환
		
		
	}
	
	//수정을 위해 입력한 수정할 정보들을 t_member테이블의 열에 UPDATE
	public void updateMember(HttpServletRequest request) {
		
		//SqlMapper객체 얻기
		sqlMapper = getInstance();
				
		//실제 member.xml(매퍼파일)에 접근하여 SQL문을 호출하여 실행할 메소드를 가지고 있는  SqlSession객체 얻기
		SqlSession session = sqlMapper.openSession();		
		
		//수정을 위해 입력한 정보들을 request메모리에서 꺼내서
		//MemberVO객체 하나 생성해서 각 인스턴스변수에 저장
		MemberVO memberVO = new MemberVO();
				 memberVO.setId(request.getParameter("id"));
				 memberVO.setPwd(request.getParameter("pwd"));
				 memberVO.setName(request.getParameter("name"));
				 memberVO.setEmail(request.getParameter("email"));
				
		//UPDATE구문을 실행할때 Sqlsession의 update메소드를 활용하자
		session.update("mapper.member.updateMember", memberVO);		 
		
		//영구반영 
		session.commit();
	}
	
	//회원삭제
	public int deleteMember(String id) {
		
		//SqlMapper객체 얻기
		sqlMapper = getInstance();
						
		//실제 member.xml(매퍼파일)에 접근하여 SQL문을 호출하여 실행할 메소드를 가지고 있는  
		//SqlSession객체 얻기
		SqlSession session = sqlMapper.openSession();
		
		//테이블의 데이터(레코드,행)을 삭제하기 위해서 
		//SqlSession객체의 delete메소드를 호출해서 사용하자
		int result = session.delete("mapper.member.deletemember", id);
		
		//영구반영
		session.commit();//수동 커밋
		
		return result;	
	}
	
	
	public List searchMember(MemberVO memberVO) {
		
		//SqlMapper객체 얻기
		sqlMapper = getInstance();
						
		//실제 member.xml(매퍼파일)에 접근하여 SQL문을 호출하여 실행할 메소드를 가지고 있는  
		//SqlSession객체 얻기
		SqlSession session = sqlMapper.openSession();	
		
	
		
		
		return session.selectList("mapper.member.searchMember", memberVO);
	}
	
	
	//t_member테이블에 저장된 name열의 값이 홍길동 또는 이순신 또는 차범근인 레코드의 모든열 값 조회
	public List<String>  foreachSelect(List<String> nameList){
									// ["홍길동","차범근","이순신"]
									//    0       1       2        index			
		//SqlMapper객체 얻기
		sqlMapper = getInstance();
						
		//실제 member.xml(매퍼파일)에 접근하여 SQL문을 호출하여 실행할 메소드를 가지고 있는  
		//SqlSession객체 얻기
		SqlSession session = sqlMapper.openSession();	
		
		 //회원 한사람 이상을 조회 할떄 SqlSession객체의 selectList메소드 호출해서 사용!
		return  session.selectList("mapper.member.foreachSelect", nameList); 
	}
	
	
	public int foreachInsert(List<MemberVO>  memList) {
			
		//SqlMapper객체 얻기
		sqlMapper = getInstance();
						
		//실제 member.xml(매퍼파일)에 접근하여 SQL문을 호출하여 실행할 메소드를 가지고 있는  
		//SqlSession객체 얻기
		SqlSession session = sqlMapper.openSession();	
		
		
		int result = session.insert("mapper.member.foreachInsert", memList);
		
		//영구반영(수동 커밋)
		session.commit();
		 
		return result;	
	}

}//MemberDAO
*/













