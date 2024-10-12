

//주제 : Object자료형으로 변수나 메소드의 반환타입을 사용하면 
//      단점(업캐스팅, 다운캐스팅)이 있다


class TestClass2{
	
	private Object member;
							    //"apple"
	public void setValue(Object value) {
		
		this.member = value;
		
	}
	
	public Object getValue() {
		return this.member;
	}
}


public class GenericTest03 {

	public static void main(String[] args) {
		
		TestClass2 obj01 = new TestClass2();
		
		obj01.setValue("apple");
		
		Object obj = obj01.getValue();//"apple"문자열객체의 주소를 반환받는다. 업캐스팅됨
		//	   obj.toUpperCase();  //String클래스에 만들어 놓은 메소드 호출 불가능
								   //이유는 ? Object부모클래스에는 toUpperCase()메소드가 만들어져 있지 않아
								   //부모 Object자료형의 obj참조변수로 호출 불가능!
		
		//다운캐스팅
		String temp = (String)obj;//다운캐스팅을 하여 "apple"문자열 객체의 주소를 다시temp변수에 
								  //대입 해서 저장함 으로써 
								  //"apple"문자열 객체 메모리내부에 만들어진
								  //인스턴스 메소드 toUpperCase()메소드 호출 가능하게 됨
		System.out.println( temp.toUpperCase() );  //다운캐스팅 후 호출 가능 
		//"APPLE"
		
		
		
		
	}

}















