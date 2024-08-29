

/*
 	주제 : java.exe 프로그램과 jvm의 관계
 	
 	1. java.exe
 		- 자바 프로그램(.class)를 실행하기 위한 실행파일이다.
 		  주로 커멘드창(cmd) 명령줄에서 사용되며, 
 		  사용자가 .class 실행파일을 실행할때  사용된다.
 		- 기능
 			: 자바 바이트코드가 포함된 .class 파일이나 .jar압축파일을 JVM에 로드한다.
 			: JVM을 시작시키고, JVM 내부에서 바이트 코드를 실행한다.
 			
 	2. JVM(Java Virtual Machine)
 		- JVM은 자바 바이트 코드를 실행하기 위한 가상머신 메모리 이다.
 		  플랫폼(window, Linux. MacOS) 독립성을 제공하여, 자바프로그램이 다양한 운영체제에서
 		  동일하게 실행될 수 있도록 하는 가상머신 메모리이다.
 		  jvm.dll 파일
 		- 메모리 관리, 스레드 관리 등의 기능을 가지고 있다.
 		- 자바 프로그램이 실행되는 환경 제공
 		
 	3. 관계
 		- 작동 방식 : 개발자가 java.exe를 통해 자바프로그램을 실행하면,
 					  (java Person)
 					  java.exe는 JVM 메모리를 시작시킨다.
 					  이후 JVM이 바이트 코드들을 해석하고 실행하는 과정을 담당한다.
 		- 플랫폼(OS) 독립성 : 
 			java.exe 는 특정 운영체제에 맞게 컴파일된 실행 파일이지만,
 			JVM은 다양한 플랫폼에서 동일한 바이트 코드를 실행할 수 있도록 설계된 메모리이다
 			
 		결론 : java.exe는 JVM을 호출하여 자바프로그램을 실행하는 역할을 하며,
 			   JVM은 실제로 바이트 코드를 실행하는 환경을 제공하는 메모리이다.
 */

// 예제. Person2.java 파일 컴파일하여 Person2.class 파일로 만들고, class 파일을 실행하는 과정에서 
//		Java.exe와 JVM이 어떻게 동작하는지 단계별로 알아보는 예제.

/*
 1단계 : 자바소스 파일작성
 		파일 생성 : Person2.java 이름의 파일생성
		코드 작성 : Person2.java에 코드를 작성
*/

// 2단계 : 자바소스 컴파일
//		명령어 실행 : 명령프롬프트에서 다음과 같은 명령어를 입력한다.
//			-> javac Person2.java
//		작동방식
//			1. javac.exe (컴파일러)로 Person2.java를 바이트 코드로 변환해서 Person2.class 파일을 생성
//			   만약 문법 오류가 있을 경우 오류 메시지가 출력된다.

// 3단계 : 자바프로그램 .class 파일을 실행한다.
//		명령어 작성 후 실행 : java Person2
//		java -> java.exe이다.
//	순서 1. java.exe는 JVM 메모리를 시작한다.
// 	순서 2. JVM은 Person2.class 파일의 클래스를 JVM 메모리에 로드한다.
//			이 과정에서 클래스의 메타데이터와 바이트코드가 JVM 메모리에 적재된다.
//  순서 3. JVM은 main 메소드를 찾아 실행한다.
//  순서 4. main 메소드 내부에서 Person2 객체를 생성하고, JVM의 heap에 올린다.
//			그 뒤 greet 메소드를 호출하여 "Hello, my name is Alice라는 메시지 출력

// 4단계 : 프로그램 종료
//	작동방식
//		순서 1. greet 메소드의 실행이 완료되면, main 메소드도 JVM에서 없어져서 종료된다.
//		순서 2. JVM은 프로그램의 실행이 끝났음을 인식하고, JVM 메모리에서 로드된 해당 클래스와 객체를 정리한다.


/*
 	요약 :
 		1. Person2.java 파일을 작성
 		2. javac Person2.java 명령어로 컴파일하여 Person2.class 파일을 생성
 		3. java Person2 명령어로 java.exe를 호출하여 JVM 을 시작하고, Person2.class 파일을 실행한다.
 		4. 프로그램이 종료되면 JVM이 객체 메모리들과 클래스 메모리를 정리한다.
 */

public class Ex5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
