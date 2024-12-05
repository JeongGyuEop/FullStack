package com.myspring.pro29.ex03;


//게시판의 하나의 글정보를 저장할 변수들이 있는 VO클래스 
public class ArticleVO {

	private int articleNO; //글번호
	private String writer; //글쓴이
	private String title;//글제목
	private String content; //글내용
	
	
	public int getArticleNO() {
		return articleNO;
	}
	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
