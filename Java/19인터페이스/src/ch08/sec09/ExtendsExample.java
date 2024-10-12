package ch08.sec09;


/*
 	인터페이스 끼리 상속
 		- 인터페이스도 다른 인터페이스를 상속할 수 잇으며,
 		  클래스와 달리 다른 인터페이스를 하나 이상 상속(다중 상속)을 허용한다.
 		  
 		요약 :하나의 인터페이스는 1개 이상의 인터페이스 상속할 수 있다.
 		      상속은 extends 예약어 사용 
 		      
 		하나의 인터페이스가 다른 인터페이스를 다중 상속시키는 문법
 			
 			interface 자식인터페이스명 extends 부모인터페이스명1, 부모인터페이스명2 {
 			
 				// 부모인터페이스1, 2로 부터 상속받은 멤버들 안쪽에서 사용가능 
 			
 			}
 			
 		자식 인터페이스의 구현 클래스(자식클래스)는 자식인터페이스 메소드 뿐만아니라
 		부모 인터페이스 1, 2의 추상메소드를 재정의(메소드 오버라이딩) 해야한다.
 		그리고 구현 객체는 다음과 같이 자식 및 부모 인터페이스 참조변수에 업캐스팅해서 저장될 수 있다.
 		
 			자식인터페이스자료형 참조변수 = new 구현된자식클래스의생성자(); // 업캐스팅 
 			
 			부모인터페이스1자료형 참조변수 = new 구현된자식클래스의 생성자(); // 업캐스팅 

 			부모인터페이스2자료형 참조변수 = new 구현된자식클래스의 생성자(); // 업캐스팅  
 			
 	예재. 구현 객체가 자식인터페이스 참조변수에 저장되면(업캐스팅되면)
 			자식 및 부모인터페이스 추상메소드를 모두 호출할 수 있으나
 			
 			부모인터페이스 참조변수에 구현객체가 저장되면
 			부모 인터페이스에 만들어 놓은 추상메소드만 호출가능하다.
 */

interface InterfaceA{ // 부모인터페이스1 
	void methodA();
}

interface InterfaceB{ // 부모인터페이스2 
	void methodB();
}
// 자식 인터페이스 역할을 하는 인터페이스 
interface InterfaceC extends InterfaceA, InterfaceB{
	/*
	 	void methodA(); // 추상메소드    <-- InterfaceA로 부터 상속받음
	 	void methodB(); // 추상메소드    <-- InterfaceB로 부터 상속받음
	 */
	
	// InterfaceC에 직접 만들어 놓은 추상메소드
	void methodC();
}

// InterfaceC 자식인터페이스를 구현받아 설계한 InterfaceCImpl자식클래스 설계 
class InterfaceCImpl implements InterfaceC{

	@Override
	public void methodA() {
		System.out.println("InterfaceCImpl - methodA() 실행");
	}

	@Override
	public void methodB() {
		System.out.println("InterfaceCImpl - methodB() 실행");		
	}

	@Override
	public void methodC() {
		System.out.println("InterfaceCImpl - methodC() 실행");
	}
	
}


public class ExtendsExample {

	public static void main(String[] args) {
		
		// InterfaceCImpl 자식클래스의 객체 생성 
		InterfaceCImpl imCImpl = new InterfaceCImpl();
		
		// 업캐스팅 가능할까요?
		// 부모인터페이스 자료형의 참조변수 선언후에 자식객체 생성후 주소번지 저장
		InterfaceA ia = imCImpl;
		ia.methodA(); // 부모인터페이스 InterfaceA에 만들어 놓은 메소드만 호출가능 
//		ia.methodB(); // 호출 불가능 
		
		System.out.println("--------------------------");
		
		// 업캐스팅
		InterfaceB ib = imCImpl;
//		ia.methodA(); // 호출 불가능 
		ib.methodB(); // 부모인터페이스 InterfaceB에 만들어 놓은 메소드만 호출가능 
		
		System.out.println("--------------------------");
		
		// 업캐스팅
		InterfaceC ic = imCImpl;
		ic.methodA();
		ic.methodB();
		ic.methodC();
		
		
		/*
		 	InterfaceC 부모 인터페이스는 다른 InterfaceA와InterfaceB의 추상메소드를 상속받아 새롭게
		 	인터페이스이기 때문에 생성된 자식객체가 InterfaceCImpl일지라도 참조변수 ic로 모두 접근해서
		 	호출할 수 있다.
		 */
	}

}



























