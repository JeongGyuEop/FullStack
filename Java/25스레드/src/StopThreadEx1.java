

//주제 : 플래그 변수를 이용하여 현재 run이 실행중인 보조작업스레드 강제로 중지시키기

//보조작업스레드 객체를 생성하기 위한 일반클래스
class StopThreadDemo1 implements Runnable{
	
	//플래그변수: 스레드가 작업을 하고 있는지 
	//          하고있지 않는지 판단할값 true 또는 false를 저장할변수
	private boolean stoped = false;
	
	//보조작업스레드 객체가 할일 구현해 놓는 메소드
	@Override
	public void run() {
		
		//플래그변수에 저장되어 있는 값이 flase일때만 계속 반복해서 일을함
		while(  !stoped ) {
			//메세지 출력하는일
			System.out.println("보조작업스레드 객체는 현재 동작하고 있다.");
			
			//보조작업스레드 객체 0.5초 대기상태로 만들기(휴식)
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//while
		
		System.out.println("보조작업스레드 객체는 현재 Dead..........");
	
	}//run 메소드 
	
	//현재 작업하고 있는 보조작업스레드 객체가 
	//중지되었다고 판단할 값을 저장하는 기능의 메소드
	public void stop() {
		stoped = true;
	}

}//보조작업스레드객체를 만들기 위한 일반 클래스 


public class StopThreadEx1 {

	//메인(주)스레드
	public static void main(String[] args) {
		System.out.println("## 플래그 변수를 이용한 스레드 강제 중지 기능 시작");
		new StopThreadEx1().process();
	}
	//메인스레드가 하는일을 작성해 놓은 일반 메소드 만들기
	//하는일 : 보조작업스레드 객체를 생성하고 작업시작시키는 일
	public void process() {
		
	//순서1. run메소드를 강제로 오버라이딩 해 놓은 일반 StopThreadDemo1객체 생성
		StopThreadDemo1 std = new StopThreadDemo1();
		
	//순서2.생성된 StopThreadDemo1객체 메모리의 주소번지를
	//     생성자로 초기화 한 실제 보조작업스레드 객체 생성
		Thread thread = new Thread(std);
	
		//보조작업 스레드 객체 일 시키기
		thread.start();
		
		//보조작업 스레드 작업시킨 후 메인스레드 1초 휴식 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//StopThreadDemo1일반클래스의 객체 내부에 만들어져 있는
		//stop인스턴스 메소드를 호출하여 플래그 변수값을 false에서 true로변경
		//즉! 강제로 보조작업스레드 객체 작업 중지 시키기!
		std.stop();		
		
	}//process메소드 
	
}//클래스 









