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

public class ExceptionHandlingExample2 {
	
	public static void printLength(String data) {
		
		//1.실행 예외가 발생할 것이 예상되는 코드줄의 영역을 
		//자바문법에서 제공해주는 예외처리하는 문법중에서
		//try{   } 블럭으로 묶어 감싸주자.
		
		//2.그리고 묶어준 코드줄 중에서 만약 예외가 발생하는 코드줄이 생기면
		//개발자가 직접 발생하는 예외종류에 따라 예외처리할 코드를 작성하는 
		//catch(예상해서_발생할종류의예외를처리할클래스명  매개변수명){ } 블럭을 이어서 작성해주자.
		
		//catch의 매개변수로는 발생한 예외에 종류에 따라 어떤예외종류가 발생했는지에 대한
		//예외를 처리할 클래스의 객체가 자동으로 생성되어 넘어온다.
		//또한 catch의 {}블럭 내부에는 예외처리할 코드를 작성 한다.
		
		//3.  마지막으로 finally {} 블럭을 작성하여
		//	  발생한 예외랑 상관없이 무조건 한번을 실행해야할 코드를 넣어준다.
	
	 try {
		int result = data.length(); //10	
		System.out.println("전체 문자열의 문자갯수 : " + result);
		
	 }catch(NullPointerException  e) {//new NullPointerException();
					  				  // 발생한 예외종류와, 예외메세지, 예외가발생한 코드줄번호
		 //1번 발생한 예외 메세지 출력가능
		 //출력방법1.
		 //예외가 발생하면 발생한 이유만 문자열로 반환 
		 //->Cannot invoke "String.length()" because "data" is null
		 System.out.println(  e.getMessage()  );
		 //출력방법2.
		 //발생한 예외 종류명 : 발생한 이유를  하나의 문자열로 합쳐서 반환 
		 //-> java.lang.NullPointerException: Cannot invoke "String.length()" because "data" is null
//		 System.out.println(  e.toString()  );
		 		 
		 //출력방법3.
		 //발생한 예외종류명 + 발생한 이유 + 예외발생코드 위치 줄번호 까지
		 //하나의 문자열로 합쳐서 출력!
		 //->java.lang.NullPointerException: Cannot invoke "String.length()" because "data" is null
		 //	at ex1.ExceptionHandlingExample2.printLength(ExceptionHandlingExample2.java:36)
		 //	at ex1.ExceptionHandlingExample2.main(ExceptionHandlingExample2.java:68
//		 e.printStackTrace();
		 
		 //2번 예외처리할 코드를 개발자가 이자리에 직접 작성
		 printLength("진짜문자열받아라");
	 
	 //catch블럭 닫는 기호 
	 }finally {
		//예외 발생여부와 상관없이 무조건 실행될 코드를 이자리에 작성
		System.out.println("[마무리 실행]\n");
	 }
	 
	 
	}//printLength메소드 닫는 기호 	
	
	public static void main(String[] args) {
		System.out.println("[프로그램 시작]\n");
		
		printLength("ThisIsJava");
		
		printLength(null);
		
		
		System.out.println("[프로그램 종료]");
	}

}







