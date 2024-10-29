package Vo;

import java.sql.Date;

// 조회한 하나의 글 정보를 저장할 용도
// 또는 
// 수정할 하나의 글정보를 조회한 후 저장할 용도
// 또는
// DB의 Board 테이블에 입력한 새글 정보를 추가하기 위해 임시로 저장할 용도
public class BoardVo {

	// 변수
	private int b_idx, b_gorup, b_level, b_cnt;
	private String b_id, b_pw, b_name, b_email, b_title, b_content;
	private Date b_date;
	
	// 기본 생성자
	public BoardVo() {}

	public BoardVo(int b_idx, int b_gorup, int b_level, int b_cnt, String b_id, String b_pw, String b_name,
			String b_email, String b_title, String b_content, Date b_date) {
		super();
		this.b_idx = b_idx;
		this.b_gorup = b_gorup;
		this.b_level = b_level;
		this.b_cnt = b_cnt;
		this.b_id = b_id;
		this.b_pw = b_pw;
		this.b_name = b_name;
		this.b_email = b_email;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_date = b_date;
	}

	public BoardVo(int b_idx, int b_gorup, int b_level, int b_cnt, String b_id, String b_pw, String b_name,
			String b_email, String b_title, String b_content) {
		super();
		this.b_idx = b_idx;
		this.b_gorup = b_gorup;
		this.b_level = b_level;
		this.b_cnt = b_cnt;
		this.b_id = b_id;
		this.b_pw = b_pw;
		this.b_name = b_name;
		this.b_email = b_email;
		this.b_title = b_title;
		this.b_content = b_content;
	}

	

	// getter, setter 메소드
	public int getB_idx() {
		return b_idx;
	}

	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}

	public int getB_gorup() {
		return b_gorup;
	}

	public void setB_gorup(int b_gorup) {
		this.b_gorup = b_gorup;
	}

	public int getB_level() {
		return b_level;
	}

	public void setB_level(int b_level) {
		this.b_level = b_level;
	}

	public int getB_cnt() {
		return b_cnt;
	}

	public void setB_cnt(int b_cnt) {
		this.b_cnt = b_cnt;
	}

	public String getB_id() {
		return b_id;
	}

	public void setB_id(String b_id) {
		this.b_id = b_id;
	}

	public String getB_pw() {
		return b_pw;
	}

	public void setB_pw(String b_pw) {
		this.b_pw = b_pw;
	}

	public String getB_name() {
		return b_name;
	}

	public void setB_name(String b_name) {
		this.b_name = b_name;
	}

	public String getB_email() {
		return b_email;
	}

	public void setB_email(String b_email) {
		this.b_email = b_email;
	}

	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public String getB_content() {
		return b_content;
	}

	public void setB_content(String b_content) {
		this.b_content = b_content;
	}

	public Date getB_date() {
		return b_date;
	}

	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}
	
	
}
