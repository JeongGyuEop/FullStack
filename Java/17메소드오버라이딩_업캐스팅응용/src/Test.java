
// 주제 : 업캐스팅을 하는 이유 응용1

// 부모클래스
// 알파벳
class Alphabet {
	
	// Alphabet클래스자료형의 인스턴스 변수 선언
	Alphabet alphabet; // new Alphabet(); ....
	
	// A 객체 메모리 주소 하나를 매개변수 a로 받아서 처리하는 메소드
	public static void printA(A a) {
		a.display();
	}
	// B 객체 메모리 주소 하나를 매개변수 a로 받아서 처리하는 메소드
	public static void printB(B b) {
		b.display();
	}
	// C 객체 메모리 주소 하나를 매개변수 a로 받아서 처리하는 메소드
	public static void printC(C c) {
		c.display();
	}
	
}

// 자식클래스 1

// 자식클래스 2

// 자식클래스 3

public class Test {

	public static void main(String[] args) {

	}

}
