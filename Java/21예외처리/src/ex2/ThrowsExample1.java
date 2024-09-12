package ex2;

public class ThrowsExample1 {

	public static void main(String[] args) {
		// findClass 메소드를 호출한 장소 여기서 try문으로 감싸 예외처
		try {
			findClass(); // 아래의 메소드를 호출한 장소 
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("프로그램을 끝까지 실행한 후 종료");
	}
	
	// 정적메소드
	//			findClass 메소드를 호출한 장소 줄로 가서 예외처리하자
	//				throws 발생한처리할종류의예외클래스
	public static void findClass() throws ClassNotFoundException {
		// 매개변수로 전달한 경로에 클래스를 찾을 수 없는 경우 
		// ClassNotFoundException 예외가 발생할 예상 코드 작성
			Class.forName("java.lang.String2");
			
		
	} // findClass 메소드 닫기 

} // ThrowsExample1 클래스 닫기 
