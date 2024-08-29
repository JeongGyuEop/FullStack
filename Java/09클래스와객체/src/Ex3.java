

// 예제. 생성된 객체 메모리 주소들을 배열메모리의 각 칸(원소)에 저장하여
//        사용해 보기

/* 
 	객체 지향 프로그래밍 기법 3단계
 	
 	1단계 - 현실에 존재하는 철수 사람 객체를 모델링
 			데이터 -> 이름(철수), 나이(30)
 			 행동  -> 자신의 이름을 알려주는 행동
 	
 	2단계 - 1단계를 이용하여 철수 사람객체의 공통점을 찾아내면
 	 		사람이라는 것을 알 수 있다.
 	 		요약: 사람 클래스 정의(사람 설계도 정의)
 	 	
 */
class Person {
	// 변수
	String name; // 사람 이름 저장
	int age;
	
	
	// 메서드
	// name 객체 변수에 저장된 이름을 반환하는 기능의 메소드
	public String getName() {
		return name;
	}
}

public class Ex3 {

	public static void main(String[] args) {

		// 기본 자료형(int, float, double, boolean ...) 값을 저장하기 위한
		// 배열 메모리를 만드는 예
		
		// 1. 정수들만 저장할 배열 메모리 생성 후 배열 메모리의 주소번지를 저장할
		//     참조 변수 선언
		int[] array;
		
		// 2. 정수 3개를 저장할 배열 메모리 3칸 생성후
		//    배열 메모리의 주소 번지는 array 참조 변수에 저장
		array = new int[3];
		
		// 3. 배열 메모리 전체 중 각 원소에 정수들을 저장
		array[0] = 10;
		array[1] = 20;
		array[2] = 30;
		
		// {	10,		20,		30		}
		// 		0		1		2		index
		// ----------------------------------------|
		
		// 객체 메모리의 주소번지를 저장할 배열 메모리(객체 배열메모리)
		// - 생성된 객체 메모리의 주소값 하나하나를 값으로 갖는 배열 메모리

		// Person이라는 클래스를 이용해 Person 객체 생성 후 
		// 객체 배열 메모리 주소를 저장하기 위한 참조 변수를 선언
		// 클래스자료형명[]  참조변수명;
		Person[] persons;
		
		// new Person(); 객체들을 10개를 생성해서 각 원소에 주소를 저장할
		// 10칸의 객체 배열 메모리 생성 후 배열 메모리 주소번지를 persons 참조 변수에 대입
		// 문법
		//    참조변수 = new 클래스자료형명[칸의 갯수];
		persons = new Person[10];
		// [ 0x11 ] = { null, null, null, null, null, null, null, null, null, null }
		// 				 0	   1     2     3     4     5     6     7     8     9      index
	
		
		
		/*
		 	3단계 : 2단계에서 설계한 class를 이용해 객체 메모리 생성 후 사용
		 */
		// persons 객체 배열의 크기만큼 반복하여 new 연산자를 이용해 Person 객체 메모리를 생성하여
		// 생성된 new Person(); 객체 메모리들의 주소 번지를 각  칸에 저장시키자
		for(int i=0; i<persons.length; i++) {
			persons[i] = new Person();
			
			persons[i].age = 30 + i; // 30
									 // 31
									 // 32 .....
			
			// i가 0일 때 Person 객체 생성 후 주소번지가 만약 0x12라고 한다면
			// persons 객체 배열의 0 index 위치 칸에 0x12가 대입되어 null은 제거되고 저장된다.
			
			// i가 1일 때 Person 객체 생성 후 주소번지가 만약 0x13라고 한다면
			// persons 객체 배열의 1 index 위치 칸에 0x13가 대입되어 null은 제거되고 저장된다.

			// i가 2일 때 Person 객체 생성 후 주소번지가 만약 0x14라고 한다면
			// persons 객체 배열의 2 index 위치 칸에 0x14가 대입되어 null은 제거되고 저장된다.
			// 
			// .......
			// .......
			//
			// i가 9일 때 까지 new Person 객체를 10개 생성해서 주소 10개를 0~9 index 각 칸에 저장하게 된다.
			// [ 0x11 ] = { 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x20, 0x21 }
			// 				 0	   1     2     3     4     5     6     7     8     9      index
			

			// [ 0x11 ] = { Person객체, Person, Person, Person, Person, Person, Person, Person, Person, Person }
			// 				 	0	   	  1       2       3       4       5       6       7       8       9      index
			
			// persons 객체 배열 메모리의 각 원소에 저장된 new Person();으로 생성한 
			// 각 객체 메모리를 차례로 얻어 객체 메모리 내부에 있는 변수 값을 출력
			/*
			 	persons 배열 1번째 index의 Person 객체  1의 객체변수 age = 30
			 	persons 배열 2번째 index의 Person 객체  2의 객체변수 age = 31
			 	persons 배열 3번째 index의 Person 객체  3의 객체변수 age = 32
			 	persons 배열 4번째 index의 Person 객체  4의 객체변수 age = 33
			 	persons 배열 5번째 index의 Person 객체  5의 객체변수 age = 34
			 	persons 배열 6번째 index의 Person 객체  6의 객체변수 age = 35
			 	persons 배열 7번째 index의 Person 객체  7의 객체변수 age = 36
			 	persons 배열 8번째 index의 Person 객체  8의 객체변수 age = 37
			 	persons 배열 9번째 index의 Person 객체  9의 객체변수 age = 38           
			 	persons 배열 10번째 index의 Person 객체 10의 객체변수 age = 39
			 */
//			System.out.println("person 배열 " + i + "번째 index의 Person 객체 " + (i+1) + "의 객체변수 age = " + persons[i].age );
		}

		// 향상된 for문
		int i = 0;
		for(Person person : persons) {
			
			System.out.println("person 배열 " + i + "번째 index의 Person 객체 " + (i+1) + "의 객체변수 age = " + person.age );
			i++;
		}
		 
		
		
		

	}

}
