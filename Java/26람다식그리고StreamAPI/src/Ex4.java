import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Ex4 {

	public static void main(String[] args) {

	/*	
		distinct()메소드
		- 스트림 통로에서 중복된 요소(객체)를 제거합니다.
		  이는 equals()메소드를 기준으로 중복을 판단별하므로,
		  객체의 경우 equals메소드를 적절히 구현해야 정확하게 중복이 제거됩니다.
		- 내부 동작
		   Stream API는 모든 객체(요소)들을 Set구조와 유사하게 관리하여
		   중복을 제거 합니다. Set은 내부적으로 해시 기반이므로 성능이 좋습니다
		  
		   중복 제거 과정에서   기존 순서가 유지됩니다.

	*/	
/*		
 List<String> items	= Arrays.asList("apple","banana","apple","orange");
		
 List<String> distinctItems = items.stream()
				                   .distinct() //중복된 "apple" 제거
				                   .collect(Collectors.toList());
 
		System.out.println(distinctItems); //[apple, banana, orange]
*/
		
/*
sorted() 메소드 

- 스트림통로에 있는 객체(요소)들을 오름차순 또는 지정된 Comparator에 따라 정렬합니다
  기본적으로 Comparator인터페이스를 구현한 클래스(예: String, Intger등) 들이
  오름차순으로 정렬됩니다. 정렬기준을 변경하려면 Comparator인터페이스의 자식구현객체를 전달할수 있습니다
 */
		List<Integer> numbers = Arrays.asList(3, 5, 1, 4, 2);
		
	 	List<Integer> sortedNumbers	=  numbers.stream()
									  		 .sorted()  //기본 오름 차순 정렬
									  		 .collect(Collectors.toList());
		
		System.out.println(sortedNumbers);//[1, 2, 3, 4, 5]
		
		List<Integer> newNumbers   =
							numbers.stream()
							   	   .sorted(Comparator.reverseOrder()) //내림 차순 정렬
							   	   .collect(Collectors.toList());
											//Collectors.toList()는 이 숫자들을 ArrayList배열로 변환해서 반환
									//collect():
										//이 메소드는 스트림의 요소들을 특정 형식으로 수집하여, 결과를 새로운 컬렉션으로 반환합니다. 
										//즉, 스트림을 처리한 후 최종 결과를 얻기 위해 사용됩니다.
										//collect()는 스트림의 종료 연산(terminal operation) 중 하나입니다. 
										//종료 연산이란 스트림의 데이터 처리를 마무리하고 결과를 반환하는 작업입니다.
		 
		System.out.println(newNumbers);//[5, 4, 3, 2, 1]
		
	}

}
/*
참고.
Comparator란?

Comparator는 객체를 비교하기 위한 함수형 인터페이스입니다. 

특정 기준에 따라 두 객체를 비교하고, 그 결과에 따라 정렬 순서를 결정합니다.

이 인터페이스는 compare(T o1, T o2) 메소드를 구현하여 두 객체의 크기를 비교합니다. 

이 compare메소드는 다음과 같은 값을 반환합니다:
		0: 두 객체가 동일함
		양수: 첫 번째 객체가 두 번째 객체보다 큼
		음수: 첫 번째 객체가 두 번째 객체보다 작음
		
		
Comparator.reverseOrder()의 사용

Comparator.reverseOrder()는 기본 정렬 순서(오름차순)를 반전시켜 내림차순 정렬을 위한 Comparator객체를 생성합니다.

이 메소드는 숫자, 문자열 등 Comparable 인터페이스를 구현한 클래스의 객체를 내림차순으로 정렬하는 데 유용합니다.

 */







