import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ex3 {

	public static void main(String[] args) {

		// 1. 중간 연산
		//		filter(Predicate<T> predicate) 추상 메소드
		//			- 스트림의 각 요소에 대해 주어진 조건을 평가하며,
		//			  Predicate 함수형 인터페이스를 사용한다.
		//			  Predicate는 boolean 값을 반환하는 함수로써,
		//			  이 조건에 맞는 요소들만 다음 연산으로 넘긴다.
		//			
		//			- filter 메소드는 여러 번 체이닝 기법으로  호출할 수 있어,
		//			  복잡한 조건을 단계적으로 나누어서 적용할 수도 있다.
		
		
//		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
//		
//		List<Integer> newNumbers = numbers.stream()
//			   // 스트림의 모든 요소에 대해 2보다 큰지 확인하고
//			   // 조건을 만족하지 않는 요소들은 제외한 스트림 통로 반환
//			   .filter(n -> n > 2) // 3, 4, 5, 6
//			   
//			   // 2의 배수만 스트림 통로에 남기고 스트림 통로 반환
//			   .filter(n -> n % 2 == 0) // 4, 6
//			   
//			   /*
//			    	collect(Collector<T, A, R> collector)
//			    	 	- 이 메소드는 스트림의 결과를 리스트(List), 셋(Set) 또는 다른 컬렉션 배열에
//			    	 	  수집해서 반환해 준다.
//			    */
//			   .collect(Collectors.toList());
//				   
//				System.out.println(newNumbers);
		
		
//-----------------------------------------------------------------------------------------------------

		/*
		 	2. 중간연산
		 		map(Function<T, R> mapper) 메소드
		 			- 이 메소드는 스트림의 각 객체(요소)를 변환할 때 사용된다.
		 			  주어진 함수에 따라 각 요소룰 변환하며, 다른 형식으로 변환할수도 있다.
		 			  Function<T, R>은 입력 타입과 출력 타입이 다른 함수형 인터페이스이다.
		 */
		
//		List<String> names = Arrays.asList("Alice", "Bob", "Chrlie");
//							// {"Alice", "Bob", "Chrlie"}
//		
//		// 각 문자열의 길이를 계산하여 새로운 리스트로 반환
//		List<Integer> nameLengths = names.stream()
//						 // String 클래스의 length() 메소드를 사용하여
//  					     // 각 문자열의 문자길이를 반환한다.
//						 .map(String::length) // 문자열 길이 얻음
//						 .collect(Collectors.toList()); // 결과를 리스트에 반환
//		
//		//결과 출력
//		System.out.println(nameLengths); // [5, 3, 6]
		
//-----------------------------------------------------------------------------------------------------

		/*
		 	collect 메소드
		 		- 스트림의 요소들을 특정 형식으로 수집하여 , 결과를 새로운 컬렉션으로 반환한다.
		 		  즉, 스트림을 처리한 후 최종 결과를 얻기 위해서 사용한다.
		 		
		 		- collection 메소드는 스트림의 종료연산을 수행하기 위한 종류중 하나의 메소드이다.
		 		
		 	Collectors 클래스의 toList()
		 		1. Collectors 클래스는 여러가지 형태로 데이터를 수집하기 위한 유틸리티 클래스로
		 		   다양한 컬렉션으로 데이터를 수집할 수 있는 메소드를 제공한다.
		 		   
		 		2. toList() 메소드는 스트림의 모든 요소를 ArrayLsit 같은 리스트 형태로 수집한다.
		 		   모든 데이터들을 List 부모 인터페이스 타입으로 변환해 준다.
		 		   
		 		   
		 */
	}

}
