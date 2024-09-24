
// 참고. 스레드란? -> 하나의 작업

// 예제 : 자바문버에서 제공해 주는 Thread클래스를 상속받아
// 		  개발자가 직접 ThreadEx01 스레드 역할의 클래스 만들기

// Thread 클래스를 상속받아 스레드 역할을 하는 클래스를 만드는 이유
// -> 자바에서 run 메소드의 역할은 하나의 작업을 처리하는 코드를 여기에 작성해 놓기 때문에
//    Thread 클래스에 run 메소드가 만들어져 있으므로 상속받아서 메소드 오버라이딩 해야한다.

// 보조 스레드(작업)
// 	숫자 0 ~ 4까지 5번 반복하면서 sum변수에 차례로 누적하여 sum 변수에 저장된 값을 출력하는 하나의 작업
public class ThreadEx01 extends Thread {
	
	// 현재 작업중인 스레드의 이름을 저장할 변수 
	String name;
	
	// ThreadEx01 스레드 객체 생성지 스레드 이름을 매개변수로 받아 초기화할 생성자
	public ThreadEx01(String name) {
		this.name = name;
	}
	
	// Thread 부모클래스의 run 메소드 오버라이딩
	// 이유 : 스레드의 작업을 처리하는 콜백 메소드이기 때문이다.
	// 콜백메소드란? 개발자가 직접 호출하는 메소드가 아니라 거꾸로 자바 컴파일러가 특정시점에
	// 				 자동으로 호출해주는 메소
	@Override
	public void run() {

		// 숫자 0 ~ 4까지 sum 변수에 누적해서 출력
		int sum = 0;
		
		for(int i = 0; i < 4; i++) {
			sum += i;
			System.out.println(this.name + " : " + sum);
		}
		
	}

	// 주스레드 : 주요 작업을 처리하는 주스레드 역할을 하는 main 메소드
	public static void main(String[] args) {

		// 보조 스레드 클래스를 이용해서 보조스레드 역할을 하는 보조스레드 객체 생성해서 사용
		ThreadEx01 t1 = new ThreadEx01("첫번째 스레드"); // 보조스레드1
		
		ThreadEx01 t2 = new ThreadEx01("두번째 스레드"); // 보조스레드2

		// 참고. 총 스레드는 3개이다. main(주스레드), 보조스레드1, 보조스레드2
		/*
		  	Thread 클래스는 start() 메소드를 제공해 준다.
		  	start()메소드는 직접 run() 메소드를 호출하게 하는 메소드는 아니며
		  	자바 JVM가상머신에게 보조스레드 객체가 각각 준비가 되었으니 
		  	자바 JVM가상머신에게 run()메소드를 호출하라고 부탁하는 메소드이다.
		  	
		  	요약 : run()메소드를 호출해서 스레드 작업을 할 수 있게 보조스레드 객체를 준비시킨다.
		 */
		t1.start();
		t2.start();
	}


}
