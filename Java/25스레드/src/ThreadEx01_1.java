
// 참고. 스레드란? -> 하나의 작업

// 예제 : 자바문버에서 제공해 주는 Thread클래스를 상속받아
// 		  개발자가 직접 ThreadEx01 스레드 역할의 클래스 만들기

// Thread 클래스를 상속받아 스레드 역할을 하는 클래스를 만드는 이유
// -> 자바에서 run 메소드의 역할은 하나의 작업을 처리하는 코드를 여기에 작성해 놓기 때문에
//    Thread 클래스에 run 메소드가 만들어져 있으므로 상속받아서 메소드 오버라이딩 해야한다.

// 보조 스레드(작업)
// 	숫자 0 ~ 4까지 5번 반복하면서 sum변수에 차례로 누적하여 sum 변수에 저장된 값을 출력하는 하나의 작업
public class ThreadEx01_1 extends Thread {
	
	// ThreadEx01 스레드 객체 생성지 스레드 이름을 매개변수로 받아 초기화할 생성자
	public ThreadEx01_1(String name) {
		super(name); // extends 해준 Thread 부모 객체의 생성자를 호출해 전달한
					 // 매개변수 값을 스레드 이름으로 저장한다.
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
			
			
			try {
				/*
				 	Thread 클래스의 public static void sleep(long milis) 메소드
				 		- 현재 작업중인 스레드 객체를 매개변수 milis로 전달한 시간동안 일시정지
				 		  시키는 기능의 메소드
				 		  
				 		- 이 메소드를 호출하면 스레드를 일시 정지시켜 다른 보조 스레드 객체에게
				 		  실행기회를 양보하거나, 일정한 시간간격을 가지고 작업을 수행시킬 수 있다.
					
						- 매개변수 long milis 에는 스레드를 일시정지할 시간을 1000분의 1초 단위값
						  (밀리초 단위)로 지정한다.
						  
						- 예외 : InterruptedException
								 sleep() 메소드가 실행 중에 다른 보조스레드에 의해
								 인터럽트 되었을 때 위 예외가 발생한다. 
				*/
				Thread.sleep(3000); // 현재 실행중인 보조작업스레드 객체에게 3초 휴식
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
			sum += i;
							// 현재 CPU가 점유한 보조스레드 객체의 이름을 얻기 위해 
							// 상속받은 getName() 메소드를 호출하여 반환받는다.
			System.out.println(super.getName() + " : " + sum);
		}
		
	}

	// 주스레드 : 주요 작업을 처리하는 주스레드 역할을 하는 main 메소드
	public static void main(String[] args) {

		// 보조 스레드 클래스를 이용해서 보조스레드 역할을 하는 보조스레드 객체 생성해서 사용
		ThreadEx01_1 t1 = new ThreadEx01_1("첫번째 스레드"); // 보조스레드1
		
		ThreadEx01_1 t2 = new ThreadEx01_1("두번째 스레드"); // 보조스레드2

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
