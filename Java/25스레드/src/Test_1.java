/*
   사용자 정의 보조작업스레드 클래스 만드는 방법 2
   
   - Runnable인터페이스 내부에 만들어져 있는 추상메소드 run을 메소드오버라이딩 받은
     일반 클래스를 만들고,
     만든 일반클래스의 객체를 생성해서 주소번지를 실제 자바문법에서 제공해주는
     Thread클래스의 객체 생성시 생성자로 전달하여
     new Thread(일반 클래스의 객체 주소); 보조작업스레드 객체 만들기 

*/

//일반클래스: 스레드가 할일을 코드로 작성해 놓을 run메소드를 오버라이딩 해 놓은 일반클래스
public class Test_1 implements Runnable {
	
	//실제 보조작업스레드객체의 주소를 저장할 변수 선언
	Thread thread;

	//new Test(보조작업스레드이름); 객체 생성시 보조작업스레드 이름을 초기화할 생성자
	public Test_1(String name) { // <- "첫번째 스레드"
		
		//아래의 main메소드 내부에서 Test_1일반클래스에 대한 객체 생성시
		//생성자로 보조스레드객체의 이름을 전달하여 받아 저장
		thread = new Thread(this, name);
		
		//보조작업 스레드 객체에 만들어져 있는 run메소드 실행되게 호출!
		thread.start();
		
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
			/*
			Thread클래스에서 제공해주는 currentThread()메소드
			- 현재 실행중인 보조작업스레드객체메모리의 주소를 반환하는 메소드
			- static메소드 이므로 Thread클래스명.currentThread(); 메소드 호출가능
			- 호출시점은 언제든지 호출할수 있습니다. 
			  이메소드는 주스레드(메인스레드) 또는 다른 스레드안에서 호출 가능합니다.
			- 반환 타입은  Thread클래스타입으로 반환합니다.
		
			Thread클래스에서 제공해주는 getName()메소드
			- 현재 실행중인 보조작업스레드 객체의 인스턴스변수! 
			  즉! 스레드이름을 반환하는 메소드 입니다.
			- 참고로 보조작업스레드 이름을 변경할때는? 
			  setName("변경할스레드명"); 메소드를 이용하면됨.
		
			 */					       
		
							//thread.getName()  
						   //<- 이방법도 현재실중인 보조스레드객체 이름을 얻을수 있다.
		System.out.println(  Thread.currentThread().getName()     + " : " + sum);
		}			
	}//run 메소드 
	
    //주(메인)스레드 역할은 보조작업스레드 객체 생성해서 일처리하게 하기 
	public static void main(String[] args) {
		
		//주 스레드 내부에서 보조작업스레드 객체 2개 생성
		//-> 실제 run추상메소드를 메소드 오버라이딩 해서 적어 놓은 
		//   일반 Test_1클래스의 객체 2개 생성
		new Test_1("첫번쨰 스레드");
		new Test_1("두번쨰 스레드");
	
	
	}

	

}








