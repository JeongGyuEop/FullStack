package sec02.exam02;

/*
 	중첩클래스 종류 중에서
 		인스턴스 멤버 중첩클래스에 대한 예제
 	
 	예제. 인스턴스 멤버 중첩클래스 B내부에는 일반클래스와 똑같이 변수(필드),
 		  생성자, 메소드 선언이 올 수 있다. 그리고 정적(static)변수와 정적(static) 메소드는
 		  java 17버전 이상 설치해야 선언할 수 있다.
 		  
 */

class A{ // 바깥 클래스(외부 클래스 역할)
	
	// 인스턴스 멤버 중첩클래스 
	class B{
		// 인스턴스 변수
		int field = 1;
		
		// 정적 변수(JDK 17버전부터 허용 
		static int field2 = 2;
		
		// 생성자
		B(){
			System.out.println("B-생성자 실행");
		}
		
		//인스턴스 메소드
		void method1() {
			System.out.println("B-method1 실행");
		}
		
		// 정적 메소드(JDK17 버전부터 허용)
		static void method2() {
				System.out.println("B-method2 실행");
		}
		
	} // B 인스턴스 멤버 중첩 클래스 닫는 중괄호 
	
	// A 바깥 외부 클래스의 인스턴스 메소드 
	void useB() {
		// B 인스턴스 멤버 중첩클래스에 대한 객체 생성 및 메소드 사용 가능
		B b = new B();
		b.method1(); // 메소드 사용가능 
		System.out.println(b.field); // 변수 사용 가능 
	}
	
} // A 바깥 외부클래스 중괄호 닫는 기호 

public class AExample {

	public static void main(String[] args) {
		
		// A 바깥 외부 클래스에 대한 객체 생성
		A a = new A();
		// A 바깥 객체의 useB()메소드 호출 
		a.useB();

	}

}
