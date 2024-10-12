package sec07.exam01;


/*
 	익명 객체?
 		- 이름이 없는 클래스를 이용해서 생성한 이름이 없는 객체.
 		
 	익명 객체 종류
 		1. 익명 자식 객체 - 클래스를 상속해서 만든 이름 없는 자식 객체 
 		2. 익명 구현 객체 - 인터페이스를 구현받아 만든 이름 없는 자식객체
 		
 	익명 자식 객체
 		- 부모 클래스를 상속받아 다음과같은 문법으로 생성할 수 있다. 
 			new 상속받은부모클래스의생성자호출() {
 				이름이 없는 익명자식객체의 구현코드가 들어갈 곳;
 			};
 			
 			의미 1. 상속받은 부모클래스의 생성자를 호출하여 이름이 없는
 					익명 자식클래스를 만드는 동시에 
 			의미 2. 이름이 없는 익명자식객체 메모리를 생성한다는 의미.
 		
 		
 */
class Tire{ // 부모 클래스 
	// 부모 클래스의 인스턴스 메소드
	public void roll() {
		System.out.println("일반 타이어가 굴러갑니다.");
	}
}

class Car { // 자동차 클래스
	// 변수
	// Tire클래스의 참조변수를 만들고 new Tire() 객체를 생성해서 주소번지를 저장
	private Tire tire1 = new Tire();
	
	// Tire 부모 클래스의 roll() 인스턴스 메소드를 메소드 오버라이딩하여
	// 이름이 없는 자식클래스를 만드는 동시에 이름이 없는 자식익명객체 생성하여 주소번지를 저장
	private Tire tire2 = new Tire() {
		// 이름이 없는 익명자식객체의 구현코드가 들어갈 곳;
		@Override
		public void roll() {
			System.out.println("익명 자식 객체1이 굴러갑니다.");
		}
	};
	
//	public Car() {
//		// TODO Auto-generated constructor stub
//	}
	
	// Car클래스의 인스턴스 메소드 run1 만들기
	public void run1() {
		this.tire1.roll(); // 부모 new Tire() 객체의 메소드 호출
		this.tire2.roll(); // 부모 Tire의 익명자식객체의 메소드 호출 
	}
	
	// Car 클래스의 인스턴스 메소드 run2 만들기
	public void run2() {
		// 로컬 (지역) 변수를 참조변수 형태로 만들어서
		// Tire 부모클래스의 roll 메소드를 오버라이딩시킨
		// 익명 자식클래스이자 익명 자식 객체를 생성해서 주소번지를 저장
		Tire tire = new Tire() {
			// Tire 부모 클래스의 roll 메소드 오버라이딩해서
			@Override
			public void roll() {
				System.out.println("익명 자식 객체2가 굴러갑니다.");
			}
		};

		tire.roll(); // 호출가능
		
	} // run2 메소드 닫기 
	
	// Car 클래스의 인스턴스 메소드 - 매개변수 이용
	public void run3(Tire tire) {
					// 매개변수로 생성된 자식 익명자식객체의 주소를 받아 사용할 수 있음
		// 매개변수 tire로 받은 익명 자식 객체 내부의 
		// 오버라이딩된 roll 메소드 호출!
		tire.roll();
	}
	
} // Car 클래스 닫기

public class CarExample {

	public static void main(String[] args) {
		// Car 객체 생성
		Car car = new Car();
		
		car.run1(); // 익명 자식 객체가 대입된 참조변수 사용
	
		car.run2(); // 익명 자식 객체가 대입된 로컬변수 사용 
		
		car.run3(new Tire() {
			@Override
			public void roll() {
				System.out.println("익명 자식 객체3이 굴러갑니다.");
			}
		});
		
	

	}

}
