package Vo;

import java.sql.Date;

//조회한 하나의 글정보를 저장할 용도
//또는
//수정할 하나의 글정보를 조회한후 저장할 용도
//또는
//DB의  Fileboard테이블에 입력한 새글 정보를 추가하기  위해 임시로 저장할 용도
public class FileBoardVo {

	//변수
	private int b_idx, b_group, b_level, b_cnt;
	private String b_id, b_pw, b_name, b_email, b_title, b_content;
	private Date b_date;
	
	private String ofile, sfile; //ofile 업로드할 원본파일명
								 //sfile 실제 업로드한 실제 파일명
	private int downcount;//DB에 업데이트 된 글에 대한 파일 다운로드한 횟수 
	
	//생성자
	public FileBoardVo() {}

	
	public FileBoardVo(int b_idx, int b_group, int b_level, int b_cnt, String b_id, String b_pw, String b_name,
			String b_email, String b_title, String b_content, Date b_date, String ofile, String sfile, int downcount) {
		super();
		this.b_idx = b_idx;
		this.b_group = b_group;
		this.b_level = b_level;
		this.b_cnt = b_cnt;
		this.b_id = b_id;
		this.b_pw = b_pw;
		this.b_name = b_name;
		this.b_email = b_email;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_date = b_date;
		this.ofile = ofile;
		this.sfile = sfile;
		this.downcount = downcount;
	}
	public FileBoardVo(int b_idx, int b_group, int b_level, int b_cnt, String b_id, String b_pw, String b_name,
			String b_email, String b_title, String b_content, String ofile, String sfile, int downcount) {
		super();
		this.b_idx = b_idx;
		this.b_group = b_group;
		this.b_level = b_level;
		this.b_cnt = b_cnt;
		this.b_id = b_id;
		this.b_pw = b_pw;
		this.b_name = b_name;
		this.b_email = b_email;
		this.b_title = b_title;
		this.b_content = b_content;
		this.ofile = ofile;
		this.sfile = sfile;
		this.downcount = downcount;
	}


	//getter,setter메소드 
	public int getB_idx() {
		return b_idx;
	}

	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}

	public int getB_group() {
		return b_group;
	}

	public void setB_group(int b_group) {
		this.b_group = b_group;
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


	public String getOfile() {
		return ofile;
	}


	public void setOfile(String ofile) {
		this.ofile = ofile;
	}


	public String getSfile() {
		return sfile;
	}


	public void setSfile(String sfile) {
		this.sfile = sfile;
	}


	public int getDowncount() {
		return downcount;
	}


	public void setDowncount(int downcount) {
		this.downcount = downcount;
	}
	
    
	
	
	
	
	
	
}








