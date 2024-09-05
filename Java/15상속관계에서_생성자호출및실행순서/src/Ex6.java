


// 부모클래스
class Person{
	int age = 20; // 0 -> 20
	String name; //null
	
	// 사람의 이름(name)변수 값을 초기화할 생성자
	public Person(String name) {
		this.name = name;
	}
}
// Person 클래스의 멤버를 상속받아 새로운 Man 자식 클래스 만들기
class Man extends Person {
	// Person 부모 클래스 내부에 만들어져 있는 age 변수명과 Man 자식 클래스 내부에
	// 만들어져 있는 age 변수명이 같다!
	int age = 40;
	
	public Man(String name) {
		// Man 객체 생성하면
		// name 인스턴스 변수는 부모 Person 객체 메모리 내부에 만들어져 있으므로
		// 부모 Person 객체의 name 인스턴스 변수 위치에 저장해야하므로
		// 부모 Person 클래스의 매개변수가 1개인 생성자를 호출할때 이름을 전달해서
		// 저장해야한다.
		// 그러므로 아래의 코드 작성이 필요하다.
		super(name);
	}
	
	// 메소드 : age인스턴스 변수의 값을 얻어 출력
	public void getInfo() {
		System.out.println("자식 Man객체의 나이 출력: " + age);
		
		//Person 부모 클래스의 변수 age와
		// Man 자식클래스 변수 age의 이름이 같을 때
		// 이때 부모 Person 객체의 age 변수값을 얻어 사용하고 싶으면
		System.out.println("부모 Person 객체의 인스턴스 변수 age값 : " + super.age);
	}
	
	// 메소드
	// name 인스턴스 변수에 초기화된 값을 얻어와 출력
	public void getInfo2() {
		System.out.println("부모 Person 객체의 인스턴스 변수 name 값 : ");
		System.out.println(" -> "+ super.name);
		// 부모 클래스 (부모 객체) 와 자식 클래스(자식 객체) 내부에 인스턴스 변수명이 같지 않을때는 
		//생성된 자식객체 메모리 내부에서 부모객체의 인스턴스 변수 name 값을  불러와서 사용시 super.
		// 은 생략 가능하다.
		
		
	}
}


public class Ex6 {

	public static void main(String[] args) {
		
		// 자식 Man클래스의 객체 생성하는 동시에 매개변수가 1개인 생성자 호출
		// 이때 String name 매개변수로 "홍길동" 문자열을 전달해서
		// 부모 Person 객체 내부의 name 인스턴스 변수값으로 초기화!
		Man man = new Man("홍길동");

		man.getInfo();
		man.getInfo2();
	}

}
