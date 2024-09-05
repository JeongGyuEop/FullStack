
/*
 * 	참고.
 * 		super 예약어
 * 			- 부모 객체 메모리의 주소번지를 기억하고 있는 참조변수이다.
 * 			- 용도 : 자식 객체 메모리 내부에서 부모객체 메모리에 만들어져 있는
 * 					 변수나 메소드에 접근해서 값을 가져오거나 사용하기 위함
 * 					 예) super.부모객체의인스턴스변수명;
 * 							-> 해설 : 부모객체 메모리내부의 인스턴스 변수값 불러오기
 * 						 super.부모객체의인스턴스메소드호출();
 * 							-> 해설 : 부모객체 메모리 내부의 인스턴스메소드 호출
 * 
 * 		super() 예약어
 * 			- 서브(자식) 클래스의 객체 메모리 생성 시 (부모 + 자식 객체 메모리 둘 다 생성)
 * 			  자식 객체 메모리 내부의 생성자 안에서 부모 객체 메모리의 다른 생성자를 호출하는 예약어
 * 			- 사용 방식
 * 				super(); // 부모의 기본 생성자 호출
 * 				super(값1); // 부모의 매개변수가 1개인 생성자 호출
 * 				super(값1, 값2); // 부모의 매개변수가 2개인 생성자 호출
 * 			- 주의할 점
 * 				반드시 자식 클래스의 생성자 내부의 가장 첫 줄에 super();를 작성해야 한다.
 */

// 예제.
// 조합 4. 상속관계에서 자식 객체 생성시 매개변수가 1개인 자식의 생성자 호출하면
//		   호출 당한 매개변수가 1개인 자식클래스의 생성자 내부에서 부모 클래스의
//		   매개변수가 1개인 생성자 호출구문 super(값);을 작성해 부모 클래스의 매개변수가
//		   1개인 생성자를 호출해서 실행하게 되는 조합
/*
 	new 자식 클래스 생성자(10);
 	
 	class 부모클래스 {
 		public 부모클래스(){} // 기본생성자
 		
 		public 부모클래스(int num) {
 			변수값 초기화;
 		} 
 	}
 	
 	class 자식 클래스 extends 부모클래스 {
 		public 자식클래스(){} // 기본생성자
 		
 		public 자식클래스(int num) {
 			super(num);
 		} // 매개변수 1개인 생성자
 	}
 */

class H {// 부모 클래스
	
	//기본생성자
	public H() {
		System.out.println("H 부모의 기본 생성자 H");
	}
	
	// 매개변수가 1개인 생성자
	public H(int x) {
		System.out.println("H 부모의 매개변수 1개인 생성자 H : " + x);
	}
	
}

class I extends H {// 자식 클래스
	
	//기본생성자
	public I() {
		System.out.println("I 자식의 기본 생성자 I");
	}
	
	// 매개변수가 1개인 생성자
	public I(int x) {
		// 개발자가 직접 매개변수가 1개인 부모클래스의 생성자를 호출할때 가장 첫줄에 이 코드 작성
		// 만약 작성하지 않으면 컴파일러는 super(); 처럼 부모 클래스의 기본 생성자 호출 코드가 
		// 자동으로 적힌다.
		super(x);
		System.out.println("I 부모의 매개변수 1개인 생성자 I : " + x);
	}
	
}

public class Ex5 {

	public static void main(String[] args) {
		
		I i = new I(5);
		// 1. I(int x) 자식 클래스의 I의 매개변수가 1개인 생성자 호출 시 5를 전달
		// 2. I(int x) {  } 자식 클래스 I의 생성자 내부에서 super(x);를 작성하여
		//    부모 H클래스의 매개변수가 1개인 H(int x) {} 생성자를 강제(명시적)으로 호출한다
		// 3. 부모 H클래스의 객체가 생성되면서 부모 객체 메모리의 인스턴스 변수 int x에 5를 저장하게 된다.
	}

}
