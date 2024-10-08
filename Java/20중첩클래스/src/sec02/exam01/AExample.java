package sec02.exam01;


/*
 	중첩 클래스란? 
 		- 클래스 내부에 선언한 클래스 
 		
 	중첩 클래스 종류 크게 2가지 
 		1. 멤버 중첩 클래스 - 클래스 내부에 선언한 클래스
 		2. 로컬 중첩 클래스 - 클래스 메소드 내부에 선언한 클래스 
 		
 		
 	멤버 중첩 클래스 2가지
 		[1] 인스턴스 멤버중첩클래스(중요!)
 				class A {
 					class B { <----- static이 붙지 않은 중첩클래스
 					
 					}
 				}
 		
 		[2] 정적 멤버중첩클래스
 				class A {
 					static class B { <---- static이 붙은 중첩 클래스
 					
 					}
 				}
 			
 			
 	로컬 중첩클래스
 		class A {
 			void method(){
 				class B { <---- 로컬 중첩 클래스
 				
 				}
 			}
 		}
 		
 		
 */
// 예제. 인스턴스 멤버 중첩클래스 B는 주로 A외부클래스 내부에서 사용되므로
//		 private 접근 제어자를 갖는 것이 일반적이다.
//		 B객체는 A외부클래스 내부  어디에서나 생성할 수는 없고,
//		 참조변수 field, 생성자, 인스턴스 메소드 내부에서 생성할 수 있다.
//		이유는 A 객체가 생성되어 있어야 내부 안쪽의 B 중첩클래스의 객체도 생성할 수 있다.

class A { // 바깥쪽 클래스(외부 클래스)
	
	class B { // 중첩클래스 종류 중 인스턴스 멤버 중첩클래스 B
		
	}
	
	// 인스턴스(참조) 변수 값으로 B객체를 생성해서 주소 저장
	B field = new B();
	
	// 바깥쪽 A 외부클래스 생성자
	A() {
		B b = new B();
	}
	
	// 바깥쪽 A외부클래스의 인스턴스 메소드
	void method() {
		B b = new B();
	}
}



public class AExample {

	public static void main(String[] args) {
		// B 인스턴스 멤버 중첩클래스에 대한 객체를 A 클래스 외부 AExample 내부에서
		// 생성하려면 default 또는 public 접근제한을 가져야하고
		// 순서는 A외부객체를 먼저 생성한 다음에 안쪽 B 객체를 생성해야 한다.
		
		// 순서 1. A외부클래스의 객체를 먼저 생성
		A a = new A();
		
		// 순서 2. B 인스턴스 멤버 중첩 클래스의 객체 생성
		// 외부클래스명.내부중첩클래스자료형명 참조변수 = 생성된 외부객체의참조변수명.new 내부중첩의생성자();
					A.B							 b  	= 						a.new B();

	}

}
