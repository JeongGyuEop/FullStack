package ch08.sec10;

interface A{} // 최고 부모 인터페이스 역할을 한다.

class B implements A{ // A인터페이스의 자식 클래스 B
	
}

class C implements A { // A인터페이스의 자식 클래스 C
	
}

// D 클래스의 부모클래스 -> B 클래스 
// D 클래스의 부모인터페이스 -> A인터페이스 
class D extends B { // A 인터페이스는 간접적 부모인터페이스이고,
	                // B 클래스의 자식 클래스 D 이다.
	
}

// E 클래스의 부모클래스 -> C 클래스
// E 클래스의 부모인터페이스 -> A 인터페이
class E extends C {
	
}


public class Promotion {

	public static void main(String[] args) {
		// 업캐스팅(자동형변환)
		// 부모인터페이스자료형 참조변수 = 자식객체생성;
		
		// 자식 객체 생성
		B b = new B();
		C c = new C();
		D d = new D();
		E e = new E();
		
		// 부모인터페이스자료형 참조변수 선언
		A a;
		
		// 부모인터페이스자료형 참조변수에 자식객체 생성후 주소저장
		a = b; // 자동타입변환(업캐스팅)
		a = c; // 자동타입변환(업캐스팅)
		a = d; // 자동타입변환(업캐스팅)
		a = e; // 자동타입변환(업캐스팅)
//		a = (A)e; // 강제타입변환(업캐스팅)

	}

}
