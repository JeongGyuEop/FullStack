

// 자바프로그램의 시작점인 main 메소드 기능을 가지고 있는 Main 클래스
public class Main {

	// 자바 프로그램을 시작시키는 기능의 main 메소드
	public static void main(String[] args) {
		
		// 참고. Car.java 파일에 만들어져 있는 class Car{} 이용
		// 3단계 : class설계도를 이용하여 객체 생성 후 사용
		
		// 1. Car 클래스의 객체 생성 후 참조변수 myCar에 저장
		Car myCar = new Car();
		
		// --------------------------------------------------------------------------------------
		// 2. 생성한 객체 내부에 만들어져 있는 객체 변수들의 값 초기화
		//		색상 -> "빨간색"
		myCar.color = "빨간색";
		
		//		모델명 -> "소나타"
		myCar.model = "소나타";
		
		//		제조연도 -> 2020
		myCar.year = 2020;
		
		// 		현재 속도 -> 0
		myCar.currentSpeed = 0;
		
		//		초기 연료량 -> 50L
		myCar.fuelLevel = 50;
		
		//		초기 엔진상태 -> 꺼짐(false)
		myCar.engineStatus = false;
		
		//-------------------------------------------------------------------------------------------
		// 3. 생성한 객체 내부에 만들어져 있는 메소드를 호출해서 기능들을 수행한다.
		//		엔진 가동
		myCar.start();
		
		//		가속 30
		myCar.accelerate(30);
		
		//		감속 10
		myCar.decelerate(10);
		
		//		연료보충 20
		myCar.refuel(20);
		
		//		엔진 정지
		myCar.stop();

	}

}
