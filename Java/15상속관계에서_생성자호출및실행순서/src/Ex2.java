
/*
   		주제 : 상속관계에서 서브(자식)클래스의 객체가 생성될때  생성자가 호출되는데 자동으로 어떤 부모생성자가 호출 되고 실행 되는가 
   				  
   				  
   				  조합 1. 서브(자식) 클래스의 객체가 생성될때
   				  			 서브(자식)클래스의 기본생성자를 호출하면 
   				  			 슈퍼(부모)클래스의 기본생성자가 있으면 자동으로 호출한다.
   				  			 
   				  			 
   				  조합 2. 서브(자식) 클래스의 객체가 생성될때 
   				  			  서브(자식) 클래스의 생성자 
 */

class AA{ // 슈퍼( 부모) 클래스 
//	public AA() { // 기본생성자 
//		// super() 생략된 것임. <- 부모 Object 클래스의 Object()기본 생성자 호출
//		System.out.println("기본생성자 AA ");
//	}
	public AA() {
		// TODO Auto-generated constructor stub
	}
	public AA(int x) { // 매개변수가 1개인 생성자 
		System.out.println("x를 받는 생성자 AA");
	}
}
//-----------------------------------------------------------------------
class BB extends AA{ //  서브(자식) 클래스 
	public BB() { // 기본생성자 
		// super();   // 부모 AA 클래스의 기본생성자 AA()를 호출하는 구문 생략 
		System.out.println("기본생성자 BB");
	}
	public BB(int x) { // 매개변수가 1개인 생성자 
		System.out.println("BB자식의 매개변수가 1개인 생성자 ");
	}
}

public class Ex2 {

	public static void main(String[] args) {

			BB bb = new BB(); //  자식 BB클래스의 객체 생성시 기본생성자 호출 
			
		
	}

}
