package sec05.exam01;


/*
 	정적 멤버 중첩클래스는 바깥 객체가 없어도 사용 가능해야 하므로
 	바깥 클래스의 인스턴스 변수와 인스턴스메소드는 사용하지 못한다.
 	
 	
 	

 */


public class A { // 바깥 클래스
	
	// A의 인스턴스 변수와 메소드
	int field1;
	void method1() {
		
	}
	
	// A의 정적 변수와 메소드
	static int field2;
	static void method2() {
		
	}
	
	// 인스턴스 멤버 중첩클래스
	class B {
		void method() {
			// A의 인스턴스 변수와 메서드 사용가능한지
			field1 = 10; // 가능
			method1(); // 가능
			// A의 정적 변수와 메소드 사용 가능한지
			field2 = 10; // 가능
			method2(); // 가능 
		}
		
	}

	// 정적 멤버 중첩 클래스
	static class C {
		void method() {
			// A의 인스턴스 변수와 메서드 사용가능한지
			// field1 = 10; // 불가능
			// method1(); // 불가능
			// A의 정적 변수와 메소드 사용 가능한지
			field2 = 10; // 가능
			method2(); // 가능 
		}
	
		
	}
	
	
	
}
