package ex1;

/*
  예제에서 printLength()메소드는 문자열의 문자개수를 구해서 출력하는 메소드이다.
  이때 문자열이 존재하지 않는데(문자열을 매개변수로 받지 않는데)..
  문자열의 문자총갯수를 알아내기위해 .length() 메소드를 호출하려할때
  NullPointerException이라는 이름의 예외가 발생 했습니다.
  즉! 값이 없는데 값을 사용하려고 할떄 발생하는 예외입니다. 
 

  참고. NullPointerException 예외는
       참조변수가 null인(값이 저장되어 있지 않는) 상태에서
       메소드에서 참조변수값을 불러와 사용할경우 발생하는 
       예외 종류중 하나 
       
       요약 : 변수를 선언하고 변수에 값을 저장하지 않은 상태에서
       	     변수값을 사용하면 NullPointerException예외가 발생함
       	     
*/

public class ExceptionHandlingExample1 {
	//기능 : 매개변수 data로 전체문자열 하나를 전달받아
	//      전체문자열의 문자갯수를 구해서 int result지역변수에 저장하고
	//		result변수에 저장된 문자갯수 출력하는 메소드 
	public static void printLength(String data) {
		//전체문자열의 문자갯수를 구해서 int result지역변수에 저장하고
		int result = data.length(); //10
				    //"ThisIsJava"
					// 12345678910
		//result변수에 저장된 문자갯수 출력하는 메소드
		System.out.println("전체 문자열의 문자갯수 : " + result);
		
		//참고. String클래스내부 만들어 놓은 length메소드를 호출하면
		//     전체문자열의 총 문자갯수를 구해서 반환 해줍니다.				
	}	
	//기능 : 자바프로그램의 시작시키고 종료시키는 기능의 메소드 
	public static void main(String[] args) {
		System.out.println("[프로그램 시작]\n");
		
		printLength("ThisIsJava");
		
		printLength(null);
		
		
		System.out.println("[프로그램 종료]");
	}

}







