

// this() 가 어떻게 사용되는지 보여주는 예제

class Book {
	String title; // 책 제목
	String author; // 책 저자
	int year; // 출판연도
	
	//기본 생성자
	public Book() {
		// "제목 없음", "작자 미상", 0 초기화 하고 싶다.
		// 매개변수가 3개인 생성자 호출 시 위 값 3개 전달 하여 초기화
		this("제목없음", "작자미상", 0);
	}
	
	// 제목과 저자만 매개변수로 받아 초기화 시킬 생성자
	public Book(String title, String author) {
		// 출판연돈는 0으로 기본설정하고 , 매개변수가 3개인 다른 생성자 호출!
		this(title, author, 0);
	}
	// 객체 생성시 모든 객체 변수 값들을 초기화 시킬 생성자
	public Book(String title, String author, int year) {
		this.title = title;
		this.author = author;
		this.year = year;
	}
	
	// 모든 객체 변수에 저장된 값들을 불러와 출력하는 메소드
	public void printInfo() {
		System.out.println("책 제목 : " + this.title);
		System.out.println("저   자 : " + this.author);
		System.out.println("출판연도 :  : " + this.year);
	}
	
}




public class This2 {

	public static void main(String[] args) {
		// 기본생성자를 호출하여 Book 객체 생성
		Book book1 = new Book();
		book1.printInfo();
//		책 제목 : 제목없음
//		저   자 : 작자미상
//		출판연도 :  : 0
		
		System.out.println(); // 한 줄 줄바꿈
		
		// 제목과 저자만 설정하는 생성자를 호출하는 Book 객체 생성
		Book book2 = new Book("책제목1", "저자명1");
		book2.printInfo();
//		책 제목 : 책제목1
//		저   자 : 저자명1
//		출판연도 :  : 0

		System.out.println(); // 한 줄 줄바꿈
		
		// 모든 객체변수들(제목, 저자, 출판연도)를 설정하는 생성자를 호출해서 Book 객체 생성
		Book book3 = new Book("책제목2", "저자명2", 1949);
		book3.printInfo();
//		책 제목 : 책제목2
//		저   자 : 저자명2
//		출판연도 :  : 1949
		
		
		/*
		 * 	요약 : this()는 같은 클래스의 생성자 내부에서 다른 생성자를 호출하는데 사용된다.
		 * 			중복된 객체변수 초기화 코드를 줄이고, 생성자 간의 관계를 체계적으로 유지할 수 있다.
		 * 			생성자 간의 코드 재사용을 통해 유지보수성과 가독성을 높이는 데 도움이된다.
		 * 
		 * 	결론 : this() 의 사용법을 이해하고, 생성자 오버로딩을 활용하여 객체 변수의 값을 초기화를
		 * 			효율적으로 관리하는 방법을 배울 수 있다.
		 */

	}

}
