package exam01;


/*
 	
 	Object클래스의 equals()메소드는 두 객체 메모리의 주소번지가 같은지 비교해서 같은 주소번지이면
 	true를 반환하고 틀리면 false를 반환
 		
 		그림
 			class A extends Object {
 			
 				// Object 클래스로 부터 상속받은 메소드
 				public boolean equals(Object obj) {
 				
 					return(this == obj);
 					
 				}
 			
 			}
 	
 		예시코드
 			new A().equals( new A() );
 			 0x11			 0x12
 			-> 두 A 객체 메모리의 주소를 비교하여 같으면 true, 다르면 false를 반환하게 된다.
 			
 			A  a = new A(); -> a.equals(a2);
 			A a2 = new A();
 	
 */
/*
 	
 	주제. Member 객체의 동등비교 (Member 객체 내부의 변수값이 같은지 비교)를 위해서 
 	      Object 부모 클래스의 equals메소드를 재정의(메소드 오버라이딩)한다. 
 	      
 	      각 Member 객체들의 id 인스턴스 변수값이 같은지 비교하여 같을 경우 true리턴,
 	      그 외의 경우에는 false를 리턴한다.
 */
class Member /* extends Object 코드가 생략된 것이다. */ {
	public String id;
	
	// 회원의 아이디를 매개변수로 전달받아 초기화할 생성자
	public Member(String id) {
		this.id = id;
	}
	
	// 메소드 오버라이딩 단축키 alt shift s v
	// Object 부모클래스에 만들어져 있는 equal 메소드 오버라이딩
	// 재구현 내용 : 각 Member 객체들 내부의 인스턴스 변수 id 값 비교 
	@Override
	public boolean equals(Object obj) {
		/*
		 	instanceof 예약어?
		 		- A객체 instanceof A클래스 자료형 또는
		 	      A객체 instanceof Object 자료형 식이 적혀있다고 했을 때
		 	      instanceof 예약어는 A 객체가 A클래스를 이용해서 생성된 객체이거나 ,
		 	      Object 부모클래스를 상속받아 만든 자식객체라면 true반환, 그렇지 않으면
		 	      false를 반환하는 예약
		 */
		// 재구현
		// obj매개변수로 전달받은 두 번째 Member 객체 메모리가 
		// Member 클래스 자료형으로 만든 객체 메모리인지 비교 검사하고
		// Member 자식 클래스로 다운캐스팅한 후 target변수에 저장하게 된다.
		if(obj instanceof Member target) {
			
			// 두 Member 객체 내부에 만들어져 있는 id 인스턴스 변수 값이 같으냐? 비교!
			if(this.id.equals(target.id)) {
				return true;
			}
		} 
		// 그외 다르면 false
		return false;
	} //----------------------------- 메소드 오버라이딩 
}



public class EqualsExample {

	public static void main(String[] args) {

		// 객체메모리의 주소번지 0x1라고 가정
		// Member 객체는 equals 메소드는 오버라이딩 되어 있다.
		Member obj1 = new Member("blue");
		
		// 객체메모리의 주소번지 0x2라고 가정
		// Member 객체는 equals	메소드는 오버라이딩 되어 있다.
		Member obj2 = new Member("blue");
		
		// 객체메모리의 주소번지 0x3라고 가정
		// Member 객체는 equals 메소드는 오버라이딩 되어 있다.
		Member obj3 = new Member("red");
		
		
		// obj1 참조변수에 저장된 new Member("blue"); 객체 내부의 String id 인스턴스변수에
		// 저장된 문자열과 
		// obj2 참조변수에 저장된 new Member("blue") 객체 내부의 String id 인스턴스 변수에 
		// 저장된 문자열이 같으냐?
		if(obj1.equals(obj2)) {
			System.out.println("두 Member 객체의 id 변수에 저장된 문자열은 같습니다.");
		} else {
			System.out.println(" 두 Member 객체의 id 변수에 저장된 문자열은 다릅니다.");
		}
		
		//-----------------------------------
		
		// 2번째로 생성한 Member 객체의 id 인스턴스 변수값이
		// 3번째로 생성한 Member 객체의 id 인스턴스 변수값과 같으냐?
		if(obj2.equals(obj3)) {
			System.out.println("두 객체의 id 변수에 저장된 문자열은 같습니다.");
			
		} else {
			System.out.println("두 객체의 id 변수에 저장된 문자열은 다릅니다.");
			
		}
		
	}

}
