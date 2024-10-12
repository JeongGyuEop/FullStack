



//보조작업스레드1 객체를 생성하기 위한 스레드 클래스 설계
class A4 extends Thread{
	
	@Override
	public void run() {
		//0 ~ 999까지 1씩 증가시키면서 1000번반복해서 i변수 출력
		for(int i=0; i<1000;   i++) {
			System.out.println("A4보조작업스레드객체의 i변수값 출력 : " + i);
		}
	}//run
}

//보조작업스레드2 객체를 생성하기 위한 스레드 클래스 설계
class B4 extends Thread{
	
	@Override
	public void run() {
		//0 ~ 999까지 1씩 증가시키면서 1000번반복해서 i변수 출력
		for(int i=0; i<1000;   i++) {
			System.out.println("B4보조작업스레드객체의 i변수값 출력 : " + i);
		}
	}//run
}

public class PriorityMain {

	//메인스레드
	public static void main(String[] args) {
		//보조작업 스레드 객체 2개 생성
		A4 a4 = new A4();
		B4 b4 = new B4();
		
		a4.setPriority(3); //작업 우선순위 3
	  //a4.setPriority(Thread.NORM_PRIORITY); //작업 우선순위를 5 로 설정 할떄 사용
	  //a4.setPriority(Thread.MAX_PRIORITY); //작업 우선순위를 10으로 설정 할때 사용
		
		b4.setPriority(8); //작업 우선순위 8
		
		System.out.println("A4보조작업스레드객체의 작업우선순위 : " +  a4.getPriority()  );
		System.out.println("B4보조작업스레드객체의 작업우선순위 : " +  b4.getPriority()  );
		
		a4.start();   b4.start();
		
	}

}











