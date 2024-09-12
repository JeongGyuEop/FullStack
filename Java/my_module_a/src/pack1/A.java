package pack1;

import pack2.B;
import pack3.C;

public class A {
	
	// 메소드 선언
	public void method() {
		System.out.println("A-method 실행");
		
		// B클래스 사용
		B b = new B();
		b.method();
	}
	
	// 메소드
	public C getC() {
		C c = new C();
		return c;
	} // my_module_b 모듈의 pack3.C 타입을 리턴한다.

}
