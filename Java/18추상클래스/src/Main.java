
// 질문 1. 추상클래스는 무엇인가?
// 답변 1. 구현되지 않은 추상메소드를 포함하고 있는 미완성 클래스이며,
//         상속을 통해서만 사용할 수 있는 클래스이다.

// 게시판 기능 구현에 대한 미완성 설계도(추상클래스) 선언
// 추상클래스명 : Board
abstract class Board {
	// 글 제목, 글 내용, 글 작성자, 글 작성 날짜를 저장할 변수 선언
	// 질문 2. protected키워드는 무엇을 의미하나?
	// 답변 2. 동일한 패키지나 상속받은 하위 클래스에서만 접근할 수 있다.
	protected String title, content, author, date;
	
	
	// 생성자를 작성하세요 
	// 제목, 내용, 작성자, 작성 날짜를 모두 초기화
	public Board(String title, String content, String author, String date) {
		// 질문 3. this 키워드는 왜 사용하는가?
		// 답변 3. 생성자의 매개변수이자 지역변수와 인스턴스 변수의 이름이 같기 때문에 
		//         인스턴스 변수를 구분하기 위해서 사용 
		this.title = title;
		this.content = content;
		this.author = author;
		this.date = date;
	}

	// 게시글 정보(제목, 작성자, 작성일, 내용) 각각 출력후 줄바꿈할 일반 printInfo메소드 선언
	public void printInfo() {
		System.out.println("제목 : " + this.title);
		System.out.println("작성자 : " + this.author);
		System.out.println("작성일 : " + this.date);
		System.out.println("내용 : " + this.content);
	}

	// 새 게시글을 추가하는 이름을 가진 추상 메소드 addPost선언
	// 질문 4. 추상메소드는 무엇인가?
	// 답변 4. 자식(하위)클래스가 반드시 구현해야하는 메서드. 구현부는 없다.
	public abstract void addPost();
	
	// 게시글을 수정하는 이름을 가진 추상메소드 updatePost 선언 
	// 단, 수정한 문자열 정보 하나를 매개변수로 받아서 수정할 수 있게 선언
	// 질문 5. 추상메소드를 사용하는 이유는?
	// 답변 5. 상속받은 자식클래스마다 다른 방식으로 기능을 구현할 수 있도록 강제한다.
	public abstract void updatePost(String updateContent);

	// 기존의 게시글을 삭제하는 이름을 가진 추상메소드 deletePost 선언
	public abstract void deletePost();
	
}

//---------------------------------------------------------------------------------------------

// 일반 게시판 자식 클래스 GeneralBoard 선언 
// 추상클래스 Board를 상속받아 선언
class GeneralBoard extends Board {
	

	// 생성자 작성
	// 새글 제목, 글 내용, 작성자, 글작성날짜를 매개변수로 받아서
	// 위 Board추상클래스의 멤버에 초기화시킬 생성자 작성
	public GeneralBoard(String title, String content, String author, String date) {
		// 질문 6. super키워드는 어떤 역할을 하나요?
		// 답변 6. 부모 클래스의 생성자나 메소드를 호출할 때 사용된다.
		super(title, content, author, date);
		
	}

	// 새 게시글을 추가하는 기능의 Board추상메소드의 addPost메소드 오버라이딩
	// 재 구현 내용
	// "일반 게시판에 글을 추가했습니다." <-- 출력
	@Override
	public void addPost() {
		System.out.println("일반 게시판에 글을 추가했습니다.");
		
	}

	// 게시글을 수정하는 메소드 오버라이딩
	// 재구현 내용
	// "일반 게시판의 글을 수정했습니다." <-- 출력
	@Override
	public void updatePost(String updateContent) {
		System.out.println("일반 게시판에 글을 수정했습니다.");
		
	}

	// 게시글을 삭제하는 메소드 오버라이딩
	// 재구현 내용
	// "일반 게시판의 글을 삭제했습니다." <-- 출력 
	@Override
	public void deletePost() {
		System.out.println("일반 게시판에 글을 삭제했습니다.");
		
	}
	
}
	

//---------------------------------------------------------------------------------------------

// 공지사항 게시판 자식 클래스 NoticeBoard 선언 
// 추상클래스 Board를 상속받아 선언
class NoticeBoard extends Board {

	// 생성자 작성
	// 새글 제목, 글 내용, 작성자, 글작성날짜를 매개변수로 받아서
	// 위 Board추상클래스의 멤버에 초기화시킬 생성자 작성
	public NoticeBoard(String title, String content, String author, String date) {
		super(title, content, author, date);
	}

	// 새 게시글을 추가하는 기능의 Board추상메소드의 addPost메소드 오버라이딩
	// 재 구현 내용
	// "공지사항 게시판에 글을 추가했습니다." <-- 출력
	@Override
	public void addPost() {
		System.out.println("공지사항 게시판에 글을 추가했습니다.");
		
	}

	// 게시글을 수정하는 메소드 오버라이딩
	// 재구현 내용
	// "공지사항 게시판의 글을 수정했습니다." <-- 출력
	@Override
	public void updatePost(String updateContent) {
		System.out.println("공지사항 게시판의 글을 수정했습니다.");
		
	}

	// 게시글을 삭제하는 메소드 오버라이딩
	// 재구현 내용
	// "공지사항 게시판의 글을 삭제했습니다." <-- 출력 
	@Override
	public void deletePost() {
		System.out.println("공지사항 게시판의 글을 삭제했습니다.");
	}

}
	
	

//--------------------------------------------------------------------------------------------

public class Main {

	public static void main(String[] args) {
		// 일반게시판 기능이 있는 클래스의 객체 생성 시 
		// 생성자로 "일반 글" 글제목전달,
		//          "이것은 일반 게시판의 글내용 입니다." 글내용 전달,
		//          "홍길동" 글작성자,
		//          "2024-09-09" 글작성날짜를 전달해 인스턴스 변수 초기화
		// 업케스팅 가능?
		Board board = new GeneralBoard("일반 글",
										"이것은 일반 게시판의 글내용 입니다.",
										"홍길동",
										"2024-09-09");
		
		// printInfo() 메소드를 호출하여 결과를 보시오
		board.printInfo();
		/*
		 	제목 : 일반 글
			작성자 : 홍길동
			작성일 : 2024-09-09
			내용 : 이것은 일반 게시판의 글내용 입니다.
		 */
		
		// addPost()메소드 호출하여 결과를 보시오
		board.addPost();
		// 일반 게시판에 글을 추가했습니다.
		
		// 게시글 내용을 수정하기 위해 updatePsot 메소드 호출 
		// 수정할 내용은 "이것은 수정된 일반 게시판의 글 내용입니다." <- 수정
		board.updatePost("이것은 수정된 일반 게시판의 글 내용입니다.");
		
		// printInfo() 메소드를 다시 호출하여 결과를 보시오
		board.printInfo();
		
		// 게시글을 삭제하기 위해 deletePost 메소드 
		board.deletePost();


	}

}
