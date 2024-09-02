
public class Printer {

	// 메소드 오버로딩? 같은 이름의 메소드를 여러 개 작성하는 것
	
			// 1. 문자열 하나를 매개변수로 전달 받아
			//	  전달 받은 매개변수 값을 출력하는 print메소드 정의
			//    출력형식
			//    "문자열 출력 : 매개변수 값"
	public void print(String str) {
		System.out.println("문자열 출력: " + str);
	}
			
			
			// 2. 정수 하나를 매개변수로 받아서 전달받은 매개변수 값을
			//    출력하는 print 메소드 오버로딩해서 정의
			//    출력형식
			//    "정수 출력: 매개변수 값"
	public void print(int a) {
		System.out.println("정수 출력: " + a);
	}
			
			// 3. 실수 하나를 매개변수로 전달받아
			//    전달 받은 매개변수 값을 출력하는 print 메소드 오버로딩 해서 정의
			//    출력형식
			//    "실수 출력: 매개변수 값"
	public void print(double a) {
		System.out.println("실수 출력: " + a);
	}

			// 4. 문자열 하나와 정수 하나를 순서대로 매개변수로 전달 받아
			//    전달받은 매개변수 2개의 값을 하나의 문자열로 만들어서 출력하는
			// 	  print 메소드 오버로딩
			//    출력형식
			//    "문자열과 정수 출력 : 매개변수 1값 매개변수2값
			
	public void print(String str, int a) {
		System.out.println("문자열과 정수 출력: " + str + " " + a);
	}
	
	public static void main(String[] args) {

		// Printer 클래스의 객체 생성
		Printer print = new Printer();
		
		// 문자열 출력
		// 출력형식 "Hello, World!"
		print.print("Hello World!");
		
		// 정수 출력
		// 출력 형식 "정수 출력 : 100"
		print.print(100);
		
		// 실수 출력
		// 출력 형식 "실수 출력 : 99.99"
		print.print(99.99);
		
		// 문자열과 정수 출력
		// 출력 형식 "문자열과 정수 출력: 점수 85"
		print.print("점수", 85);
	}

}
