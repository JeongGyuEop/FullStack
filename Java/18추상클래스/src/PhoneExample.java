


/*
 	추상?
 		사전적의미로 추상은 실체 간의 공통되는특성을 추출한 것을 말한다.
 		
 		예를들어 새, 곤충, 물고기 등의 공통점은 동물이다.
 		여기서 동물은 실체들의 공통되는 특성을 가지고 있는 추상적인 것이라 볼 수 있다.
 		
 	추상클래스?
 		- 객체를 생성할 수 있는 클래스를 일반 클래스(완성된 설계도)
 													메소드가 완전히 구현부까지 완성된 클래스
 		  이런 일반클래스들의 공통적인 변수나 메소드들을 추출해서 선언한
 		  미완성 설계도를 추상클래스라고 한다.
 		  (메소드의 선언만 존재하고 구현부는 완성되지 않음)
 		  
 		- 추상클래스는 일반, 실체클래스(완성된 설계도)의 부모클래스의 역할을 한다.
 		  따라서 일반 클래스는 추상클래스를 상속해서 공통적인 변수나 메소드를 물려받을 수 있다.
 		  
 		- 예를 들어 Bird, Insect, Fish와 같은 일반 실체클래스에서
 		  공통된 변수나 메소드를 따로 선언한 Animal클래스를 만들 수 있는 것이고
 		  이것을 상속해서 일반(자식, 실체)클래스를 새롭게 만들 수 있다.
 		  
 	그림	
 		  		Animal.class <--------------- 추상클래스(부모 추상클래스)
 		  		
 		  			상속
 		  			
 		  Bird.class	Insect.class	Fish.class <--- 실체 클래스
 		  
 		- 추상클래스는 실체 클래스의 공통되는 변수나 메소드를 추출해서 만들었기 때문에
 		  new 연산자를 사용해 객체를 직접 생성할 수 없다.
 		  예) Animal animal = new Animal(); --> 객체 생성 x
 		  
 		- 추상클래스는 새로운 자식(실체)클래스를 만들기 위한 부모 클래스의 역할로만 사용된다.
 		  즉 추상클래스는 extend 뒤에만 올 수 있다.
 		  
 		  	예) class Fish extends Animal{
 		  	
 		  	    }
 		  	    
 		- 추상클래스 선언 문법
 			public abstract class 추상클래스명 {
 				// 변수
 				// 상수
 				// 생성자
 				// 일반메소드
 				// 추상메소드
 			}
 			
 			설명 : 추상클래스도 상수, 변수, 메소드를 선언 할 수 있다.
 				   그리고 자식 객체가 생성될 때 super()로 추상클래스의 생성자가 호출되기 때문에
 				   생성자도 반드시 1개 이상은 있어야 한다.
 				   또한 추상메소드가 추가로 작성된다.
 				   
 */

// 전화기 종류 : 스마트폰, 아이폰, 일반전화, 공중전화
// 		추상화 : 전화를 걸 수 있다.

// 모든 전화기의 공통변수와 메소드만 뽑아내서 추상클래스 Phone으로 선언하자
abstract class Phone {
	// 변수 
	String owner;
	
	// 생성자
	Phone(String owner){
		this.owner = owner;
	}
	
	// 메소드
	void turnOn() {
		System.out.println("폰 전원을 켭니다.");
	}
	// 일반메소드
	void turnOff() {
		System.out.println("폰 전원을 니다.");
	}
	
}

// 추상클래스 Phone은 부모역할로 상속받아 자식클래스 설계
class SmartPhone extends Phone {
	// 생성자
	SmartPhone(String owner){
		super(owner); // 부모 Phone 생성자를 호출해서 owner 매개변수의 값으로 전달 
	}
	
	//메소드
	void internetSearch() {
		System.out.println("인터넷을 검색합니다.");
	}
}


public class PhoneExample {

	public static void main(String[] args) {
		// 추상클래스 Phone에 대한 객체 생성 x
//		Phone phone = new Phone();  // x
		
		// 추상 클래스 Phone 은 자식클래스(실체클래스)에 대한 객체 생성은 허용 O
		SmartPhone smartPhone = new SmartPhone("홍길동");
					smartPhone.turnOn(); // Phone의 메소드 호출가능
					smartPhone.internetSearch();
					smartPhone.turnOff();
	}
	
	/*
		결론 : Phone 객체는 new 연산자로직접 생성할 수 없었지만
				자식 객체인 SmartPhone은 new 연산자로 객체 생성이 가능하고, 
				Phone으로 부터 상속(물려)받은 turnOn메소드와 turnOff 메소드를 호출할 수 있다.
	 */

}


























