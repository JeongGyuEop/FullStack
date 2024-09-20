

//주제 : 모든 자료형을 처리하는 클래스


class TestClass1{
	
	private Object member;
	
	public void setValue(Object value) { 
						// new Integer(3),  
						//new Double(3.4),  
						//"이해할수 있다"
		
		this.member = value;
		
	}
	
	public Object getValue() {
		return this.member;
	}
}


public class GenericTest02 {

	public static void main(String[] args) {
		
		TestClass1  obj01 = new TestClass1();
		
		obj01.setValue(3); //오토박싱이 일어나 new Integer(3)객체를 전달하는 것임 
		
		System.out.println("member변수의 값을 반환받아 출력 : " +  obj01.getValue() );
		
		obj01.setValue(3.4); //오토박싱이 일어나 new Double(3.4)객체를 전달하는 것임
		
		System.out.println("member변수의 값을 반환받아 출력 : " +  obj01.getValue() );
		
		obj01.setValue("이해할수 있다.");
		
		System.out.println("member변수의 값을 반환받아 출력 : " +  obj01.getValue() );
		
	}

}















