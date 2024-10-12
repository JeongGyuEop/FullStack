/*
   사용자 정의 보조작업스레드 클래스 만드는 방법 2
   
   - Runnable인터페이스 내부에 만들어져 있는 추상메소드 run을 메소드오버라이딩 받은
     일반 클래스를 만들고,
     만든 일반클래스의 객체를 생성해서 주소번지를 실제 자바문법에서 제공해주는
     Thread클래스의 객체 생성시 생성자로 전달하여
     new Thread(일반 클래스의 객체 주소); 보조작업스레드 객체 만들기 

*/

//일반클래스: 스레드가 할일을 코드로 작성해 놓을 run메소드를 오버라이딩 해 놓은 일반클래스
public class Test implements Runnable {
	
	//new Test(보조작업스레드이름); 보조작업스레드의 이름을 저장할 인스턴스변수 선언
	String name;
	
	//new Test(보조작업스레드이름); 객체 생성시 보조작업스레드 이름을 초기화할 생성자
	public Test(String name) {
		this.name = name;
	}
	
	//보조작업 스레드 객체의 작업	
	@Override
	public void run() {
		//숫자 0 ~ 4까지 sum변수에 누적해서 출력
		int sum = 0;	
		for(int i=0; i<5; i++) {			
			try {
				Thread.sleep(1000); //현재 실행중인 보조작업스레드객체에게 1초 휴식
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
			sum += i;					
			System.out.println(name + ":" + sum);
		}			
	}//run 메소드 
	
    //주(메인)스레드 역할은 보조작업스레드 객체 생성해서 일처리하게 하기 
	public static void main(String[] args) {
		
		//순서1. run메소드를 오버라이딩 해놓은 일반Test클래스의 객체 생성
		Test t1 = new Test("첫번째 스레드");
		//순서2. 자바에서 이미 만들어져 있는 Thread클래스로 객체 생성시
		//      생성자로 run메소드가 적혀 있는 일반 Test객체의 주소를 전달해서
		//      스레드화 시킨다.
		//<- 실제 보조작업스레드 객체 1
		Thread thread1 = new Thread(t1);
		
		 //<- 실제 보조작업스레드 객체 2
		Thread thread2 = new Thread(new Test("두번쨰 스레드"));
		
		//JVM이 run()메소드를 호출할수 있게 보조작업스레드 객체1,객체2를 
		//준비 대기 시킴
		thread1.start();
		thread2.start();
		
		//향후 랜덤으로 두 보조작업스레드객체1, 보조작업스레드 객체2 둘중
		//하나의 run메소드가 번갈아 가면서 실행되어 일처리 한다.
	
	}

	

}








