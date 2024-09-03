

/*
	static 변수(클래스 변수), static 메소드(클래스 메소드)
	
	인스턴스 변수(객체 변수), 인스턴스 메소드(객체 메소드)
	
	지역 변수,
	
	static  초기화 블럭, 객체 생성 시 메모리 로딩 및 호출 순서
	
	
	--------------------------------------------------------------
	주제: 자바에서 클래스 안의 멤버 변수들 종류 알아보기
 */

class Test {
	// 멤버변수 2가지
	// 1. 인스턴스 변수(객체 변수)
	//		- static을 붙이지 않고 작성된 변수
	//      - 새로운 객체메모리를 생성할 때 마다 .... 객체 메모리 내부에 개별적으로 할당되는 변수 메모리
	//      - 새로운 객체 메모리가 생성될 때 마다 JVM의 Heap 영역의 객체 메모리 안에 할당되는 변수 메모리
	int x;
	
	// 2. 클래스 변수(정적 변수)
	// 		- static을 붙여 작성된 변수
	// 		- class 파일이 JVM 메모리의 Method 영역에 올라가면서 class 파일 내부에 개별적으로
	//		   할당되는 변수 메모리
	//		- class 파일 하나당 한 번만 개별적으로 할당되는 변수 메모리
	//		- 객체 메모리를 생성하지 않고도 클래스명. 으로 접근 가능한 변수 메모리
	//		- 생성된 여러 객체의 메모리들이 공통으로 사용할 데이터를 저장하는데 사용되는 변수메모리
	static int y;
	
	// 생성자
	// 정수 하나를 매개변수로 받아서 인스턴스변수 x와 클래스 변수 y에 각각 누적
	public Test(int z) {
		this.x += z;
		y += z;
	}
}

public class Ex1 {

	// static을 붙여 만들어 놓은 main 메서드 또한 Ex1클래스 내부에 만들어져있는 
	// 클래스 메소드라고 할 수 있다.
	public static void main(String[] args) {
		
		// 1. 클래스 변수 y의 값을 불러와 출력
		// 	 방법 : 객체 생성 없이 클래스명.클래스변수명
		System.out.println("클래스명.클래스변수명 = " + Test.y);
		//					클래스명.클래스변수명 = 0
		
		// 2. 인스턴스 변수 x를 객체 생성없이 클래스명.객체변수명 으로 접근 불가능
//		System.out.println(Test.x); --> 에러 발생
		
		// 3. 인스턴스 변수 x의 값을 불러와 사용하려면?
		//   방법 : 객체 생성 후 참조변수명.인스턴스변수명
//		Test t1 = new Test(); // 이미 생성자를 만들어 놓았기 때문에 기본생성자 생성x
		Test t1 = new Test(10);
		
		System.out.println("Test t1 객체");
		
		// 인스턴스 변수 x의 값 불러와서 출력
		System.out.println("t1.x = " + t1.x);
		// 클래스 변수 y의 값도 불러와서 출력
		System.out.println("Test.y = " + Test.y);
//		System.out.println("t1.y = " + t1.y);
		
		// 결론 :
		// 클래스 변수값을 불러와 사용하려면?
		// 2가지 방법
		//	 1. 클래스명.클래스변수명
		//   2. 객체 생성 후   참조변수명.클래스변수명
		
		//--------------------------------------------------------------------------------
		//--------------------------------------------------------------------------------
		
		// Test 클래스의 객체 메모리 생성 시 생성자로 10 전달해서
		// 인스턴스 변수 x에 10을 누적, 클래스 변수 y에 10을 누적
		Test t2 = new Test(10);
		
		// 참고.new 연산자를 이용해 새로 생성한 Test 객체 메모리는 JVM 메모리의
		//      heap영역에 따로 생성되어 개별적 메모리를 갖기 때문에 
		//      Test 객체 메모리 내부 또한 int x 인스턴스 변수도 개별적 메모리를 갖게 된다.
		System.out.println("Test t2 객체");
		
		// 인스턴스 변수 x에 저장된 값 불러와 출력
		System.out.println("t2.x = " + t2.x);
		// 클래스 변수 y에 저장된 값 불러와 출력
		System.out.println("Test.y = "+ Test.y); // 20
//		System.out.println("t2.y = "+ t2.y); // 20
		
		/*
		 	결론.
		 		출력결과를 보면 static으로 선언된 클래스 변수 y는?
		 		클래스 하나만 하나의 클래스변수만 JVM 메모리에 올라가서 생성되므로
		 		위의 t1, t2참조변수의 객체가 클래스 변수 y를 하나 공유받아 사용하므로
		 		클래스 변수 y 안의 값이 계속 += 10 누적된다고 보면 된다.
		 		
		 		출력결과를 보면 객체를 생성할 때 객체 메모리 내부에 만들어지는 x 변수는?
		 		객체를 생성할 때마다 각각의 객체 메모리에 각각 개별로 JVM메모리의 heap
		 		영역의 x인스턴스 변수 메모리가 생성된다.
		 		그러므로 각각 x인스턴스 변수 2개는 각각 +=에 의해 10이 저장된다.
		 	
		 */
		
	}

}