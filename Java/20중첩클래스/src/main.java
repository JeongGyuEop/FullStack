

/*
 	문제 : 중첩클래스 사용하여 자동차 모델
 */

// class Car 만들기 
class Car {
	// 인스턴스 변수 2개 만들기
	// 1. 차량 모델명을 문자열로 저장할 model변수 선언
	// 2. new Engine(); 생성하여 저장할 engine변수 선언
	private String model;
	private Engine engine;
	

	// 생성자 - 위 두 인스턴스변수값 초기화할 생성자 선언
	// 차량 모델명, 엔진출력(마력)정수값을 매개변수로 전달받아 
	// 1. 차량 모델명은 model변수에 초기화
	// 2. 엔진 출력(마력)정수값 하나는 new Engine(enginPower);생성하여 초기화 
	public Car(String model, int enginePower ) {
		this.model = model;
		this.engine = new Engine(enginePower);
		
	}

	// 자동차 정보를 출력하는 showCarInfo 메소드 선언
	// 출력형식
		// "모델 : Hyundai Sonata"
	// 메소드 호출하여 출력 
		// 엔진 정보를 출력하는 showEngineInfo() 메소드 호출
	public void showCarInfo() {
		System.out.println("모델 : " + this.model);
		engine.showEngineInfo();
	}
	
	

	// 정적 중첩 클래스 Engine클래스 만들기
	static class Engine{
		// 엔진 출력(마력) 정수값 하나 저장할 power 변수 선언(private)
		private int power;

		// 생성자 - 엔진출력(마력) 정수값 하나를 매개변수로 받아 초기화
		public Engine(int power) {
			this.power = power;
		}

		// 엔진정보 출력하는 showEngineInfo 메소드 선언(인스턴스 메소드)
		// 출력형식
		// "엔진 출력: 정수값 마력"
		public void showEngineInfo() {
			System.out.println("엔진 출력 : " + power + "마력");
		}

	}
}
	

public class main {

	public static void main(String[] args) {
		// Car클래스 객체 생성 시 
		// 생성자 호출하여 차량모델명을 "Hyundai Sonata"로 전달
		// 엔진 출력(마력)값을 180 전달
		// 하여 초기화
		Car car = new Car("Hyundai Sonata", 180);
		
		// 자동차 정보 출력하기 위해 showCarInfo()메소드 호출 
		car.showCarInfo();
	}

}
