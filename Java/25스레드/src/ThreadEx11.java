

//보조작업스레드 객체를 생성하기 위한 클래스(설계도)
class ThreadT extends Thread{
	
	//100번반복해서 0~99까지 누적해서 저장할 변수 
	int result = 0;
	
	//생성자
	public ThreadT(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		//1.100번 반복해서 0~99까지 result변수에 누적
		//2.현재 보조작업스레드가 실행중이면 보조작업스레드 이름을 얻어 출력하고
		//  누적된 result변수 값 출력
		for(int i=0;  i<100;  i++) {
			result = result + i; //누적
			System.out.println( Thread.currentThread().getName() + " result변수 : " + result);
		
			Thread.yield();
			//- 같은 우선순위에 있는 스레드객체들에게 실행기회를 양보하는 메소드
			//- 만약 같은 우선순위의 스레드가 하나도 없으면 현재 실행중인 자기자신의 스레드객체를 다시 실행시킴
			//- 따로 휴게소(휴식상태)로 가지 않고 바로 준비방(대기상태)으로 간다
			//- OS JVN내부의 (스케줄러)에 현재 실행하고 있는 스레드객체의 양보를 알리는 목적의 메소드로
			//  스레드들의 작업순서를 정하는 과정을 랜덤으로 정하기 떄문에 양보에 큰효과를 볼수 없습니다.
			//  yield메소드는 interrupt()메소드와 함께 사용해야 효과를 볼수 있습니다.	
		}
	}
}

public class ThreadEx11 {

	public static void main(String[] args) {
		//보조작업스레드 객체 3개 생성후 일시키기
		new ThreadT("첫번째 스레드").start();
		new ThreadT("두번째 스레드").start();
		new ThreadT("세번째 스레드").start();
		

	}

}












