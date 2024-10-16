package member;

// 회원 관련 기능들을 기능별로 추상메소드를 정의해 놓고 제공하는 인터페이스
public interface IMemberDAO {
	
	// 1. member 테이블에 새 회원을 추가하는 기능의 추상메소드
	public int insertMember(MemberBean memberbean);
	
	// 2. member 테이블에 새 회원을 추가하기 전
	//    가입된 아이디가 member 테이블에 저장되어 있는지 중복 체크하는 추상메소드
	public int idCheck(String id);
	
	// 3. 로그인 처리 시 사용하는 추상 메소드
	public int userCheck(String id, String passwd);
	
}
