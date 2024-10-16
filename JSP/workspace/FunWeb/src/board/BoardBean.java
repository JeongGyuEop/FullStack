package board;

import java.sql.Timestamp;

// VO 역할을 하는 클래스
public class BoardBean {

	// 멤버변수
	private int num; // 글번호
	private String name; // 글쓴이
	private String passwd; // 글 비밀번호
	private String subject; // 글 제목
	private String content; // 글 내용
	private int pos; // 주글(부모글)로부터 파생된 답변글(자식글)들이 같은 값을 가지기 위한 그룹값 
	private int depth;
	private int count;
	private String ip;
	private Timestamp regdate;
	private String id;
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {

		this.num = num;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	
	
}
