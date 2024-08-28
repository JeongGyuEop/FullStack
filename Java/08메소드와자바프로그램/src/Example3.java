
// 예제. 반환 값이 없고 매개변수가 없는 메소드 정의 및 호출해서 사용

public class Example3 {
	
	// 기능: "안녕하세요!" 인사메시지를 출력하는 기능
	public static void greet() {
		
		System.out.println("안녕하세요!");

	}

	public static void main(String[] args) {
		
		// "안녕하세요!" 출력하고 싶을 때
		// 위 정의해 놓은 greet 메소드를 호출해서 사용
		Example3.greet();
		greet();
		
		// class 내부에 static 키워드로 정의한 메소드 호출하는 문법
		// 클래스명.메소드명();
		// 또는
		// 메소드명();

	}

}
