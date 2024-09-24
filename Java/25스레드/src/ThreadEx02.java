
// 보조작업스레드 객체를 생성하기 위한 사용자정의 스레드 클래스 만들기
// 방법 1. Thread 클래스를 상속받아 ThreadDemo라는 이름으로 스레드 클래스 만들기
class ThreadDemo extends Thread {
	
	// 숫자를 카운팅해서 저장할 int count 변수 선언
	int count;
	
	// 보조작업스레드(ThreadDemo) 객체 생성시 스레드이름을 매개변수로 받아 
	// 이름을 초기화할 생성자 선언
	// 단! 생성자 내부에서 run메소드가 실행되게 start()메소드를 호출한다.
	public ThreadDemo(String name) {
		super(name);
		
		start();
	}
	
	// 보조 작업 스레드가 하는 작업을 run메소드를 오버라이딩해서 선언
	@Override
	public void run() {
	// 재구현 코드 내용
		// 현재 실행되고 있는 보조작업스레드 객체의 이름을 얻어 일 "시작" 출력
		System.out.println(Thread.currentThread().getName() + "시작");
	
		// 0 ~ 9까지 1씩 증가시키면서 1초 간격으로 숫자를 반복해서 출력하는 일
		for(int i=0; i<10; i++) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
		// 현재 실행되고 있는 보조작업스레드 객체의 이름을 얻어 일 "끝" 출력
		System.out.println(Thread.currentThread().getName() + "끝");
	}
	
}

public class ThreadEx02 {
	
	// 주(메인)스레드
	// 하는일 : 보조작업스레드 객체를 생성하는 일, 0.5초 간격으로 . 을 10번 반복해서 출력하는 일
	public static void main(String[] args) {
		
		System.out.println("메인스레드 일 시작");
		
		ThreadDemo td = new ThreadDemo("보조작업 스레드 1");
		
		for(int i=0; i<10; i++) {
			System.out.print(".");
			
			try {
				Thread.sleep(500);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("메인스레드 일 끝");
		

	}

}
