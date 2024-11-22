package InterfaceMappers;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.spring.ex04.MemberVO;

/*
  MyBatis와의 연동을 담당하는 메퍼 역할을 하는 인터페이스 입니다.
  각 메서드는 SQL쿼리문과 매핑되어 데이터베이스에서 정보를 조회하고
  그 결과를 자바 객체로 반환합니다. 
  MyBatis는 이 인터페이스를 구현하여 SQL쿼리 실행을 관리 합니다.

*/
public interface MemberMapper {

	/*
		모든 회원 목록을 조회 하는 메소드 
		
		@Select 어노테이션은  SQL 쿼리를 직접 삽입하는곳이다.
		        
		List<MemberVO> - 조회된 회원 정보 목록을 담은 리스트를 반환하게 설정.
	
		@Results 어노테이션은 SQL 결과(조회된 ResultSet의 결과)를 자바 객체(MemberVO객체) 속성(변수)에
		자동 매핑하는 역할을 합니다.  
	*/

	@Select("SELECT * FROM T_MEMBER ORDER BY joinDate DESC")
	@Results(id="memResult", value={
			
			@Result(property = "id", column = "id"), // "id" 컬럼을 MemberVO 클래스의 "id" 속성에 매핑
            @Result(property = "pwd", column = "pwd"), // "pwd" 컬럼을 MemberVO 클래스의 "pwd" 속성에 매핑
            @Result(property = "name", column = "name"), // "name" 컬럼을 MemberVO 클래스의 "name" 속성에 매핑
            @Result(property = "email", column = "email"), // "email" 컬럼을 MemberVO 클래스의 "email" 속성에 매핑
            @Result(property = "joinDate", column = "joinDate") // "joinDate" 컬럼을 MemberVO 클래스의 "joinDate" 속성에 매핑		
			
	})		
	List<MemberVO> selectAllMemberList();
	
	
	@Select("SELECT * FROM t_member WHERE id = #{id}")
	@ResultMap("memResult") //"memResult"는 앞에서 정의 결과 매핑 규칙을 사용하게 설정
	MemberVO  selectMemberById(String id); //특정 ID를 가진 회원의 상세정보를 조회하는 메소드 
	
	
	
	
	
	
}













