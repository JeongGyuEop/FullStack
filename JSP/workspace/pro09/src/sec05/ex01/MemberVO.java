package sec05.ex01;

import java.sql.Date;

/*
  VO(Value Object)역할을 하는 클래스
  
  VO역할이란?
  DB의 테이블에 저장된 정보를 조회한 후 조회한 레코드의 컬럼값들을
  VO클래스 내부의 변수에 저장하거나,
  변수에 저장된 값들을 VO클래스의 객체를 생성하여 DB에 INSERT시키기 위해 사용되는 클래스의 역할
 
  VO역할을 하는 클래스 만드는 방법
  	테이블에서 조회한 레코드의 컬럼(열)값들을 변수에 저장해 하므로  
  	테이블의 열명,데이터유형에 맞게 변수를 선언하고
  	getter메소드, setter메소드들을 추가 하면 됩니다.
  	

 */
public class MemberVO {
	//변수들 -> private으로 변수에 저장된 값을 외부에서 접근 못하도록 막자 
	private String id;  //t_member테이블에 저장된 조회한 아이디 저장 
	private String pwd; //t_member테이블에 저장된 조회한 비밀번호 저장
    private String name; //t_member테이블에 저장된 조회한 회원이름 저장
    private String email; //t_member테이블에 저장된 조회한 회원 이메일 주소 저장 
    private Date joinDate; //t_member테이블에 저장된 조회한 가입 날짜 정보 저장 
	
    //기본생성자
    public MemberVO() {
    	System.out.println("new MemberVO()객체 생성시 호출되는 기본생성자");
    }
    
    //new MemberVO(가입할id, 가입할비밀번호, 가입할이름, 가입할이메일); 객체 생성시 호출되는 생성자로
    //객체를 생성하는 동시에 인스턴스변수값들 모두 초기화 시킵니다.
    public MemberVO(String _id, String _pwd, String _name, String _email) {
    	this.id = _id;
    	this.pwd = _pwd;
    	this.name = _name;
    	this.email = _email;
    }
    
//getter역할을 하는 메소드란?
	//private으로 선언된 위 변수에 저장된 값을 외부로 반환할 목적으로 사용됨
//setter역할을 하는 메소드란?
	//private으로 선언된 위 변수에 값을 새롭게 변경해서 저장할 목적으로 사용됨




	//getter, setter메소드들 -> public접근제어자를 사용해서 외부 클래스에서 호출할수 있도록 합니다.
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














