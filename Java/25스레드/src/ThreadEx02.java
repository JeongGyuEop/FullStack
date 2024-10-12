

//보조작업 스레드 객체를 생성하기 위한 사용자정의 스레드 클래스 만들기
//방법1. Thread클래스를 상속받아 ThreadDemo라는 이름으로 스레드 클래스 만들기
class ThreadDemo extends Thread{
	//숫자를 카운팅해서 저장할 int count변수선언
	int count;
	//보조작업스레드(ThreadDemo)객체 생성시 
	//스레드이름을 매개변수로 받아 초기화할 생성자 선언
	//단! 생성자 내부에서 run메소드가 실행되게 start()메소드를 호출해 주세요
	public ThreadDemo(String name) {
		super(name);
		this.start();
	}
	
	//보조작업스레드가 하는작업을 run메소드를 오버라이딩해서 선언
	@Override
	public void run() {
	//재구현 코드 내용
		//현재 실행되고 있는 보조작업스레드객체의 이름을 얻어 일 "시작"  출력
		System.out.println(this.getName() + "  시작");
		// 0 ~ 9 까지 1씩 증가 시키면서 1초 간격으로 숫자를 반복해서 출력하는일
		do {
			//현재 작업중인 보조스레드객체에게 1초 휴식
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//현재 보조작업스레드가 run메소드를 실행하고 있으며 스레드이름을 받아 같이 출력
			System.out.println(this.getName() + ", 카운터 = " + this.count);
			
			count++;
		}while(count<10);


		//현재 실행되고 있는 보조작업스레드객체의 이름을 얻어 일 "끝"  출력
		System.out.println(this.getName() + "  끝");
	}
		
}			
public class ThreadEx02 {

	//주(메인)스레드
	//하는일 : 보조작업스레드 객체 생성하는일,  
	//        0.5초 간격으로 . 을 10번 반복해서 출력하는일
	public static void main(String[] args) throws InterruptedException {		
		System.out.println("메인스레드 일 시작");
		
		//보조작업스레드 객체 생성
		new ThreadDemo("보조작업스레드객체1");
		
		//0.5초 간격으로 .을 10번 반복해서 출력하는일
		int count = 0;
		
		do {
			//메인(주)스레드에게 0.5초 휴식
			Thread.sleep(500);
			
			System.out.print(".");
			
			count++;
			
		}while(count<10);
		
		
	
		System.out.println("메인스레드 일 끝");

	}
}
/*
결론 :  메인스레드와 보조작업스레드가 각각 실행중일때  0.5초 또는 1초 휴식 하면서 
       서로 번갈아가면서 스레드(하나의작업)을 합니다.
       메인스레드는 0.5초 휴식이니 더빨리 일이 마무리되고, 
       보조작업스레드는 1초 휴식이니 더늦게 일처리를 합니다.
*/










