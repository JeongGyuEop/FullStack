

// 예제. 반환 값이 없고 여러 매개변수를 가지는 메소드

public class Example4 {
	
	// 문자열 1개(학생 이름) 그리고 정수 1개(나이)를 매개변수로 전달받아
	// "이름: 홍길동, 나이: 30" 출력하는 기능의 반환값이 없는 printStudentInfo
	// 메소드 정의
	public static void printStudentInfo(String name, int age) {

		System.out.println("이름: " + name + ", 나이: " + age);

	}
	
	// 정수  2개를 매개변수 전달받아 더해 더한 연산결괏값을 반환하는 기능의
	// add 메소드 정의
	public static int add(int a, int b) {
		return a + b;
	}
	
	// 실수 2개를 매개변수 전달받아 더해 더한 연산결괏값을 반환하는 기능의
	// add2 메소드 정의
	public static float add2 (float d, float e) {
		return d + e;
	}
	
	
	public static void main(String[] args) {

		// "이름: 홍길동, 나이: 30" 한번 출력하기 위해
		// 정의해 놓은 printStudentInfo 메소드 호출!
		printStudentInfo("홍길동", 30);

		// 5, 10의 합 15를 구해서 얻기 위해 
		// 정의해 놓은 add 메소들 호출!
		// 반환 받은 15를 출력!
		int add1 = add(5, 10);
		System.out.println(add1);
		
		// 5.5, 10.5의 합을 구해서 얻기 위해 정의해놓은 add2 메소드 호출!
		// 반환받은 16.0을 호출
		float add2 = add2(5.5f, 10.5f);
		System.out.println(add2);
		
	}

}
