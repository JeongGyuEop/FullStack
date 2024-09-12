package sec03.exam02;


/*
 	정적 멤버 중첩 클래스에 대한 예
 	
 	예제. 정적 멤버 중첩클래스 B내부에는 일반클래스와 똑같이 변수, 생성자, 메소드 선언이 가능 
 */

class A { // 바깥 클래스 
	
	// 정적 멤버 중첩 클래스 선언
	static class B {
		// 인스턴스 변수 선언 가능
		int field1 = 1;
		
		// 정적(클래스) 변수 선언 가능(JDK 17버전부터 만들 수 있음) 
		static int field2 = 2;
		
		// 생성자
		B() {
			System.out.println("B-생성자 실행");
		}
		
		// 인스턴스 메소드 선언 가능
		void method1() {
			System.out.println("B-method1 실행");
		}
		
		// 정적 (클래스) 메소드 선언 가능(JDK 17버전 부터 만들어 놓을 수 있다.)
		static void method2() {
			System.out.println("B-method2 실행");
		}
	} //B 정적 멤버 중첩 클래스 중괄호 닫는 기호 
}

public class AExample {

	public static void main(String[] args) {
		
		// B 정적 멤버 중첩 클래스에 대한 객체 생성
		A.B b = new A.B();
		
		// 인스턴스 변수 및 인스턴스 메소드 사용!
		System.out.println(b.field1); // 인스턴스 변수 값 불러와 출력
		b.method1(); // 인스턴스 메소드 호출해서 출력 
		
		// B 클래스의 정적변수 및 정적메소드 사용!
		// B 정적 멤버 중첩클래스의 정적변수에 접근하는 문법
		// -> 바깥클래스명.정적멤버중첩클래스명.불러와사용할값이저장된정적변수명 
		System.out.println(A.B.field2); // 2 
		
		// B정적 멤버 중첩클래스의 정적메소드 호출하는 문법
		// -> 바깥클래스명.정적멤버중첩클래스명.호출할정적메소드명();
		A.B.method2(); //B-method2 실행
	}

}
