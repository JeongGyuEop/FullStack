class Parent{  //부모
  String name; //이름
  int age;  //나이
  
  //기본생성자 
  public Parent() {}
  
  //Person객체가 생성된 후 
  //Person객체 메모리 내부에 만들어진 name,age인스턴스 변수값 초기화할 생성자
  public Parent(String name, int age) {
	  this.name = name;
	  this.age = age;
  }
}

//자식
class Child extends Parent{
   String address;//주소
   
   public Child() {} //기본생성자
   
   //주소, 이름, 나이를 매개변수로 받아 인스턴스변수들을 각각 초기화 할 생성자 
   public Child(String address, String name, int age) {
	   this.address = address; //Child객체에 만들어진 address인스턴스변수값 초기화 
//	        address = address;
	   
	   super.name = name;     //Parent객체에 만들어진 name인스턴스변수값 초기화 
//	         name = name;
	   super.age = age;       //Parent객체에 만들어진 age인스턴스 변수값 초기화 
//           age = age;   
   }
     
   // 완성된 Child 객체의 정보 출력하는 메서드
   public void printInfo() {
	   System.out.println("이름: " + this.name);    // 부모 클래스의 name 변수 출력
	   System.out.println("이름: " + super.name);    // 부모 클래스의 name 변수 출력	   
       System.out.println("이름: " + name);    // 부모 클래스의 name 변수 출력
       
       System.out.println("나이: " + age);      // 부모 클래스의 age 변수 출력
       System.out.println("주소: " + address);  // 자식 클래스의 address 변수 출력
   }
}



public class Ex7 {
	public static void main(String[] args) {
		 // Child 객체 생성 (이름, 나이, 주소 정보를 넘겨줌)
        Child child = new Child("Seoul", "John", 10);
        
        // Child 객체의 정보 출력
        child.printInfo();

	}

}
