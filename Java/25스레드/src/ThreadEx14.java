

/*
	Thread스레드객체들 간의 데이터 교환 예제
	
	  1. 특정 파이값(3.141592)을 계산하는 보조작업 스레드 
	  
	  2. 계산 파이값을 가져다가 사용하는 보조작업 스레드
	  
	  3. 각 스레드들간의 파이값을 공유할 코드가 적혀있는 일반 클래스 

	  4. 메인스레드 
*/

//1. 특정 파이값(3.141592)을 계산하는 보조작업 스레드 
class PiCalc  extends Thread {
	
	//공유영역 SharedArea클래스의 result변수에 저장된 데이터를 가져다가 사용하기 위해
	//new SharedArea()객체의 주소를 저장할 참조변수 선언
	SharedArea sa;
	
	//파이값 산출 작업
	@Override
	public void run() {
		//파이값을 계산하여 산출한 값 저장 
		double pi = 3.141592;
		
		//산출한 파이 값을 공유객체메모리인? new SharedArea()객체의 result인스턴스변수에 저장
		sa.result = pi;
		
		//산출한 파이 값을 공유객체메모리인? new SharedArea()객체 내부에 만들어져 있는
		//result인스턴스변수에 저장 했으므로
		//isReady인스턴스변수의 값을  true로 변경하여 저장
		sa.isReady = true;		
	}
	
	
	
}
// 2. 계산 파이값을 가져다가 사용하는 보조작업 스레드
class PiPrint extends Thread {
	
	//공유영역 SharedArea객체의 result변수에 저장된 데이터를 가져다가 사용하기 위해
	//new SharedArea()객체의 주소를 저장할 참조변수 선언	
	SharedArea sa;
	
	//공유영역 SharedArea객체메모리 내부에 만드어져 있는 result변수의 값을 가져다가 사용하는 작업
	@Override
	public void run() {
		//공유영역에 파이값이 저장되어 있지 않으면? 파이값 가져다 사용하는 작업 중지 시키기
		while(sa.isReady != true) {
			continue;//다시 조건식으로 가서 조건검사해~
		}
		//공유영역(SharedArea객체의 인스턴스변수 result)에 파이값이 저장되어 있으면
		//파이값을 공유 받아 출력
		System.out.println("PiPrint스레드객체가 파이값 공유받아 사용한 값 : " + sa.result);
	}

}
//3. 각 스레드들간의 파이값을 공유할 코드가 적혀있는 일반 클래스  
class SharedArea{
	//두개의 스레드가 데이터 교환을 하기위해 공동으로 사용할 공용변수
	double result;
	//현재 공유시킬 산출된 파이값이 공유영역에 저장되어 있는지 여부를 저장하는 플래그 변수
	boolean isReady; // 산출한 파이값을 공유영역의 공용변수에 저장했으면 true 저장
					 //                              저장하지 않았으면? false 저장
}

public class ThreadEx14 {

	//4. 메인스레드 
	public static void main(String[] args) {
		
		//PiCalc, PiPrint 보조작업스레드 객체 2개 생성
		PiCalc piCalc = new PiCalc();
		PiPrint piPrint = new PiPrint();
		
		//공유영역역할을 하는 객체 메모리 생성
		SharedArea sharedArea = new SharedArea();
		
		//파이값 산출 스레드 객체 내부의 인스턴스변수 sa에 공유영역객체(new ShreadArea();)의 주소저장
		piCalc.sa = sharedArea;
		
		//산출된 파이값을 공유받아 사용(출력)할 스레드 객체 내부의 인스턴스변수 sa에 
		//공유영역객체(new ShreadArea();)의 주소를 저장
		piPrint.sa = sharedArea;
		
		//두개의 보조작업스레드 일시키기
		piCalc.start();      piPrint.start();
		
		//PiPrint스레드객체가 파이값 공유받아 사용한 값 : 3.141592
	}

}
















