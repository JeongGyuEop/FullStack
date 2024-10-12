
/*
  스토리
  	  어떤 값을 입력하여 엔터키를 누르면
  	  출력결과가 오랜시간뒤에 출력된다고 가정할때..
  	  출력결과를 보기전에 약간의 효과를 줘서
  	  ~~~ 잠시만 기다려주세요 라는 메세지를
  	  보조작업스레드를 이용하여 처리하기 
  
    예)  숫자 입력 : ~~~~
         "잠시만 기다려 주세요" <- 보조작업스레드가 처리 
         출력 결과 : ~~~~
*/
//Runnable인터페이스의 run추상메소드를 강제로 메소드오버라이딩 시킨
//스레드 객체를 생성하기 위한 보조작업일반클래스 만들기

import java.util.Scanner;

class TestTread implements Runnable{

	//보조작업 스레드 객체가 처리할 일 작성
	@Override
	public void run() {

		System.out.println("\n\n잠시만 기다려주세요...\n\n");
	}
}

public class ThreadEx05 {
	
	//메인(주)스레드
	public static void main(String[] args) {
		
		System.out.print("숫자 입력 : ");
		
		//키보드 표준입력장치로부터 입력받은 숫자를 1바이트 단위로 읽어들일 
		//바이트 스트림 생성
		//읽어들인 바이트 데이터들을 쉽게 형변환 하는 메소드를 제공하는 Scanner객체 생성시
		//바이트 스트림을 넣어서 생성하자
		Scanner scanner = new Scanner(System.in);//키보드와 연결된 통로 
		
		//키보드로 부터 입력받은 숫자는 System.in입력스트림 통로로부터
		//바이트 단위로 읽어들이기때문에 읽어들인 바이트데이터들을
		//long데이터로 변환해서 반환받아오기 위해 Scanner객체의 nextLong메소드 호출후
		//변환해서 반환 받아옵니다.
		long num = scanner.nextLong();//입력한 숫자가 저장되어 있음
		
		
		//보조작업 스레드 객체 생성 하자
		//순서1. run메소드를 강제로 오버라이딩 해놓은 TestThread일반클래스의 객체 생성
		//순서2. 생성된 TestThread객체의 주소번지를 Thread클래스의 생성자로 전달해
		//		저장시킨 new Thread(new TestThread()); 실제 보조작업스레드 객체 생성
		Thread thread  = new Thread(  new TestTread() );
		
		//실제 보조작업 스레드 객체에 일시키기 위해 대기 상태로 만들기
		thread.start(); // run콜백메소드가  JVM에 의해서 자동으로 호출될것임 
		
		//----------------
		//0부터  입력받은 숫자까지의 합을 저장할 누적변수 만들기
		long total = 0;
		
		for(long i=0;   i<=num;  i++) {
			
			total += i; //0부터 1씩 증가시키면서 total에 입력받은 숫자까지 반복해서 누적
		}
		
		System.out.println("총합의 결과 : " + total);
		
		

	}//main  메인(주) 스레드 

}











