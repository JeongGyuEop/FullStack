package ex1;




public class ExceptionHandlingExample3 {

	public static void main(String[] args) {
		

		//1.예외가 예상되는 코드줄을 마우스로 드래그 한다.
		//2. alt + shift  +  z    y 를 눌러 try catch블럭을 자동으로 작성
		

		try {
			//경로에 String클래스가 만들어져 있지 않을수도 있으므로
			//개발자가 일반예외가 예상되는 코드줄을 try블럭으로 감싼다
			Class.forName("java.lang.String");
			
			System.out.println("java.lang패키지에는 String.class가 만들어져 있습니다.");
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
		System.out.println();
		
		
		//참고.
		//Class.forName("특정클래스의 전체경로를 문자열로 전달");
		//이코드는 클래스가저장된 위치(ClassPath)에 만들어진 클래스를 찾는코드이다
		//만약 찾지 못하면 ClassNotFoundException종류의 예외가 발생한다
		//             (그경로에 클래스가 만들어져 있지 않아 찾을수 없다)
		//따라서 소스가 컴파일되려면 일반 예외처리코드를 반드시 작성해야 한다
		
		
		try {
			Class.forName("java.lang.String2");
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
			//예외 처리 코드 개발자가 직접 작성
			try {
				Class.forName("java.lang.String");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
			
		System.out.println("프로그램 코드 끝까지 수행되었음");
	}

}






