

/*
 	메소드 오버로딩?
 		- 같은 클래스 내부에 동일한 이름의 메소드를 여러 개 정의하는 것을 말한다.
 		
 	메소드 오버로딩 조건
 		1. 매개변수의 개수가 달라야 한다.
 			void display() {}
 			void display(int a) {}
 			void display(int a, int b) {}
 			
 		2. 매개변수의 자료형(타입)이 달라야한다.
 			void display(int a){}
 			void display(double a) {}
 			void display(String a) {}
 			
 		3. 매개변수 작성 순서가 달라야 한다.
 			void display(int a, String b) {}
 			void display(String a, int b) {}	
 			
 		주의 : 매개변수의 갯수와 타입이 동일하지만 순서만 다른 경우에만 오버로딩 가능
 			   매개변수가 모두 동일한 경우(개수, 타입, 순서 모두 동일)은 오버로딩 불가능
 			
 		참고. 반환 타입(자료형)만 다르고 매개변수가 동일한 메소드들은 오버로딩이 불가능하다
 			public int geValue(int a) {
 				return a;
 			}
 			
 			public double getValue(int a){
 				return (double)a;
 			}
 */

// 메소드 오버로딩 예
// add 라는 이름의 메소드를 4개 정의한 클래스
class Calculator {
	// 동일한 이름의 add 메소드를 여러개 작성
	
	// 두 정수의 합을 구하는 메소드
	public int add(int a, int b) {
		return a + b;
	}
	
	// 세 정수의 합을 구하는 메소드
	public int add(int a, int b, int c) {
		return a + b + c;
	}
	
	// 두 실수의 합을 구하는 메소드(자료형을 달리해서 메소드 오버로딩)
	public double add(double a, double b) {
		return a + b;
	}
	
	// 정수와 실수의 합을 구하는 메소드(자료형을 달리해서 메소드 오버로딩)
	public double add(int a, double b) {
		return a + b;
	}
}

public class Main {
	
	public static void main(String[] args) {

		Calculator calc = new Calculator();
		
		// 두 정수의 합
		int sum1 = calc.add(5, 10);
		System.out.println("두 정수의 합: " + sum1);
		
		// 세 정수의 합
		int sum2 = calc.add(5,  10, 15);
		System.out.println("세 정수의 합: " + sum2);
		
		// 두 실수 5.5와 10.5의 합
		double sum3 = calc.add(5.5, 10.5);
		System.out.println("두 실수의 합: " + sum3);

		// 정수 5와 실수 10.5의 합
		double sum4 = calc.add(5, 10.5);
		System.out.println("정수와 실수의 합: " + sum4);
	}

}
