

import java.util.function.Function; // 함수형 인터페이스   apply 추상메소드
import java.util.function.Predicate; // 함수형 인터페이스  test 추상메소드
import java.util.function.Consumer; // 함수형 인터페이스   accept 추상메소드

public class Ex1 {

	public static void main(String[] args) {

		/*
		 	1. Function<T, R> : 하나의 인자를 받아서 결과를 반환하는 함수형 인터페이스
		 						T는 입력타입, R은 반환 타입을 의미한다.'
		 			
		 		아래의 예시에서는 입력타입이 Integer이고, 반환타입이 String이다.
		 		intToString람다식은 정수 값 하나를 매개변수로 받아서
		 		"숫자 : "라는 문자열과 i 변수값을 결합하여 반환한다.	
		 */
		
		// 예시로 5를 매개변수로 전달하면 "숫자 : 5"라는 문자열을 반환하는 람다식 작성
		Function<Integer, String> intToString = (i) -> { return "숫자 : " + i; };
											 //  i  ->  "숫자 : " + i;  도 가능하다.
		
		// 이름이 없는 익명 객체 주소는 intToString 참조변수에 자식 객체로 저장되어 있다.
		// 익명 객체 내부에는 Function 부모 인터페이스 내부에 apply 추상메소드를 강제로 
		// 오버라이딩한 이름이 없는 익명 함수 형태로 람다식을 사용하여 정의해 놓았다. 
		System.out.println(intToString.apply(5));
		
		
		/*
		 	2. Predicate<T> : 인자를 받아서 boolean값을 반환하는 test 추상메소드 하나를 가진
		 					  함수형 인터페이스이다.
		 					  
		 		T는 입력타입을 의미하여, boolean값을 반환한다.
		 		isEmpty참조변수에 저장된 람다식은 매개변수 s로 받은 값이 빈 문자열인지 
		 		여부를 확인하는 역할을 한다.
		 		매개변수 s로 문자열을 받지 못하면 ture를 반환하고, 받으면 false를 반환한다. 
		 */
		
		// 문자열이 빈 경우 true를 반환하고, 그렇지 않으면 falseㄹ르 반환한다.
		Predicate<String> isEmpty = s -> s.isEmpty();
								// (s) -> { return s.isEmpty(); }
		
		// test 메소드를 사용하여 빈 문자열 ("")을 테스트하여 true를 반환한다.
		System.out.println(isEmpty.test("")); // 출력 : true
		
		/*
		 	Consumer<T> : 인자를 받아서 처리하지만, 값을 반환하지는 않는 accept 추상메소드를 가진
		 				  함수형 인터페이스이다.
		 		T는 입력 타입을 의미하며, Consumer는 입력값을 받아서 처리만 하고, 반환값은 없다.
		 		
		 */
		
		// print 참조변수에 저장된 람다식은 매개변수로 입력된 문자열을 그대로 출력하는 역할을 한다.
		Consumer<String> print = s -> System.out.println(s);
		
		// accept 메소드를 사용하여 Consumer 인터페이스를 구현한
		// 익명 객체의 익명메소드를 호출할 때 "Hello"를 s 매개변수로 전달해서 출력
		print.accept("Hello");
	}

}






















