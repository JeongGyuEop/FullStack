
public class Person2 {
	
	// 변수
	String name;
	
	// 메소드
	public void setName(String newName) {
		name = newName;
	}
	
	public void greet() {
		System.out.println("Hello, my name is " + name);
	}
	
	
	
	
	public static void main(String[] args) {
	
		Person2 person2 = new Person2();
		person2.setName("Alice");
		person2.greet();

	}

}
