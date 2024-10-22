package sec02.ex01;

import java.sql.Date;

//MVC중에서  Model(웹브라우저로 응답할 데이터)을 제공할 역할을 하는 클래스 

//VO역할을 하는 클래스
//- 회원테이블(t_member)의 정보를 조회해와 변수에 저장하고,
//  회원한사람의 정보를 MemberVO객체 단위로 공유하기 위한 클래스 
public class MemberVO {

	//t_member회원테이블의 컬럼명 자료형과 동일하게 변수이름과 자료형으로 변수들을 선언
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	
	//아무일도 하지 않는 기본생성자
	public MemberVO() {}

	//MemberVO클래스의 객체 생성시 가입날짜를 제외한 인스턴스변수값들을 모두 초기화할 생성자
	public MemberVO(String id, String pwd, String name, String email) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
	}
	//MemberVO클래스의 객체 생성시 모든 인스턴스변수값들을 초기화 시킬 생성자
	public MemberVO(String id, String pwd, String name, String email, Date joinDate) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.joinDate = joinDate;
	}

	
	
	//private으로 선언된 위 인스턴스변수에 저장된 값을 외부 클래스에서 변경하기 위해
	//setter메소드들과
	//private으로 선언된 위 인스턴스변수에 저장된 값을 외부 클래스에 반환해서 제공할 목적의
	//getter메소드들 만들기
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	
	
	
	
	
}












