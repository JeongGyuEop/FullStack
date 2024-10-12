
//함수형인터페이스? 추상메소드를 단 하나만 가진 인터페이스 

//@FunctionalInterface어노테이션 기호는
//이 인터페이스가 함수형 인터페이스임을 자바컴파일러에게 알려주는 기호임

@FunctionalInterface
interface MathOperation{

	//operation이라는 이름의 추상메소드를 정의 함
	//기능 : 두개의 정수 매개변수로 각각 받아  int타입의 값을 반환하는 메소드
	int operation(int a, int b);
	
}

public class Main {
	public static void main(String[] args) {
	/*	
		
		람다식은 본질적으로 함수형 부모인터페이스의 자식 익명객체의 메소드를 구현하는 방식이며
		이 과정은 자바 컴파일러에 의해 자동으로 처리됩니다.
		
		1. 익명클래스의 예시 : 익명클래스는 새로운 클래스에 이름을 정의하지 않고,
		                    바로 부모 인터페이스나 부모클래스를 구현하는
		                    객체를 생성하는 설계도 입니다.	
		
		MathOperation add = new MathOperation() {

			@Override
			public int operation(int a, int b) {
			
				return a + b;
			}
		};
		//설명  여기서 익명클래스는 MathOperation부모인터페이스의 추상메소드
		//    operation를 구현하는 익명객체를 생성하며,
		//    익명객체 내부에는 operation추상메소드를 강제로 오버라이딩 합니다.
		
		System.out.println(  add.operation(5, 3)   );//8
	*/
    /*		
		2. 람다식의 예시 :
		   익명 클래스 대신 람다식을 사용하면 코드가 훨씬 간결해 집니다
		   람다식은 함수형 인터페이스의 
		   추상메소드를 바로 구현하는 이름이없는 익명함수 형태의 식입니다
     */
		
		//두개의 정수를 매개변수로 전달받아서 더한 결괏값을 정수로 반환하는 
		//익명함수를 람다식으로 작성
		MathOperation add = (a, b) -> a + b;
						//  (int a, int b) -> { return a+b;    };
		
		
		System.out.println(  add.operation(5, 3)   );//8
		
		
		
		//두개의 정수를 매개변수로 전달받아서 첫번째 값에서 두번쨰값을 뺀 결과를 정수로 반환하는
		//익명함수를 람다식으로 작성
		MathOperation subtract = (int a, int b) -> { return a - b; };
		
		System.out.println(  subtract.operation(5, 3)   );//2

	}

}













