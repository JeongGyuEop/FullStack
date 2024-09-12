package sec04.exam02;

/*
	
	로컬(지역)중첩클래스?
		- 바깥 외부 클래스 내부에 만들기는 하지만
		  바깥 외부 클래스의 생성자 또는 메소드 중괄호{} 내부에 만들어 지는 중첩클래스
	
	
 */
class A { // 외부 클래스
	// 생성자
	A() {
		// 로컬 중첩클래스 선언
		class B{
			// 로컬 중첩클래스를 이용해 객체 생성
			B b = new B();
		}
	}
	// 메소드
	void method() {
		// 로컬 중첩클래스 선언
		class B{}
		// 로컬 중첩클래스를 이요해 객체 생성 
	}
}

public class AExample {

	public static void main(String[] args) {

	}

}
