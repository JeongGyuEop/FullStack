package ch08.sec10;

// 부모인터페이스
interface Vehicle {
	// 추상메소드
	/* public abstract */ void run();
}

// 부모인터페이스를 구현한 자식클래스
class Bus implements Vehicle {

	// 부모인터페이스의 추상메소드 강제로 오버라이딩
	@Override
	public void run() {	
		System.out.println("버스가 달립니다.");
	}
	
		
	// Bus 클래스의 메소드
	public void checkFare() {
		System.out.println("승차요금을 체크합니다.");
	}

}

public class CastingExample {

	public static void main(String[] args) {

		// 업캐스팅
		Vehicle vehicle = new Bus();
		// 부모인터페이스 참조변수 = 자식객체 생성;
		
		vehicle.run(); // 뒤 생성된 Bus객체 내부의 오버라이딩된 메소드가 실행
//		vehicle.checkFare(); // 호출불가능!
		//이유 : 부모인터페이스 Vehicle에 만들어져 있지 않는 메소드
		//       호출이 불가능하기 때문 
		
		// 업캐스팅의 단점을 해결하기 위해 인터페이스자료형도 다운캐스팅 가능
		// 다운캐스팅 (강제타입변환)
		// 자식클래스자료형 참조변수 = 
		//			(자식클래스자료형)자식객체주소가저장된 부모인터페이즈자료형의 참조변수명;
		Bus bus = (Bus)vehicle;
		// 강제 타입변환 후 bus 참조변수로 모든 메소드를 호출할 수 있다.
		// 이유 : Bus 클래스 내부에는 run 메소드도 만들어져 있고, checkFare 메소드도 만들어져 있기 때문
		bus.run(); // 호출가능 
		bus.checkFare(); // 호출가능 
		
	}

}
