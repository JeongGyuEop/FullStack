

//보조작업 스레드 클래스 설계
public class ThreadDemo3 extends Thread{

	//보조작업스레드 일시키기
	//하는일 : 예외를 강제로 발생 시키기 
	@Override
	public void run() {
		//강제로 예외 발생
		throwException();
	}
	
	//메소드
	public void throwException() {
		try {
			throw new Exception(); //실제 예외 강제로 발생시키는 구문 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}









