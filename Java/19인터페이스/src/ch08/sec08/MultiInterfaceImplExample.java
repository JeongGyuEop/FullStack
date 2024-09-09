package ch08.sec08;

// 전원을 켜고, 끄는 추상메소드를 설계해 놓은 인터페이
interface RemoteControl{
	
	// 추상메소드
	/* public abstract */ void turnOn();
						  void turnOff();
		
}

// 검색 기능을 추상메소드로 설꼐해 놓은 인터페이스
interface Searchable {
	
	// 추상메소드
	void search(String url);

}

//-------------------------------------------------------------------------

// 전원을 켜고, 끄고, 검색을 구현하기 위한 SmartTelevision 자식 클래스 새롭게 설계
//class SmartTelevision implements 인터페이스명1, 인터페이스명2 {
//	
//} --> 다중 인터페이스 구현
class SmartTelevision implements RemoteControl, Searchable{
	
	// 두 부모인터페이스 내부에 작성된 모든 추상메소드를 강제로 오버라이딩 해야한다.

	@Override
	public void search(String url) { // Searchable 인터페이스의 추상메소드 오버라이딩 
		System.out.println(url + "을 검색합니다.");
	}

	@Override
	public void turnOn() { // RemoteControl 인터페이스의 추상메소드 오버라이딩 
		System.out.println("Tv를 켭니다.");
	}

	@Override
	public void turnOff() { // RemoteControl 인터페이스의 추상메소드 오버라이딩 
		System.out.println("Tv를 끕니다.");
	}
	
}

public class MultiInterfaceImplExample {

	public static void main(String[] args) {
		
		// 업캐스팅 : 부모인터페이스 자료형의 참조변수에 자식객체의 주소를 저장 
		RemoteControl rc = new SmartTelevision();
		// 부모인터페이스 참조변수 = 자식객체생성;
		
		// 업캐스팅 후 부모 인터페이스 자료형의 참조변수 rc로
		// 뒤에 생성된 SmartTelevision객체의 멤버는 
		// RemoteControl인터페이스에 만들어 놓은 turnOn(), turnOff() 메소드만 호출해서 사용할 수 있다.
		rc.turnOn();
		rc.turnOff();
		
		// Searchable 부모인터페이스에 정의한 메소드 이므로 
		// RemoteControl 부모 인터페이스 참조변수로 호출이 불가능하다.
		// rc.search() --> 호출이 불가능하다.

		//업캐스팅
		Searchable searchable = new SmartTelevision();
//		searchable.turnOn(); // 호출 불가능 
//		searchable.turnOff(); // 호출 불가능
		searchable.search("http://www.naver.com"); // 호출가능 

	}

}
