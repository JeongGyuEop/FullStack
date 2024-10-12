

//주제 : 정해진 자료형만 처리하는 클래스

class TestClass{ //<- Object부모클래스 상속받는 형태
	
	private int member;
	
	public void setValue(int value) {
		this.member = value;
	}
	
	public int getValue() {
		return this.member;
	}
}


public class GenericTest01 {
	public static void main(String[] args) {

		TestClass  obj01 = new TestClass();
		
		obj01.setValue(3);
		
		System.out.println("member변수의 값을 반환받아 출력 : " +  obj01.getValue()  ); //3
		
		//obj01.setValue(3.4); //실제 메소드 호출시 전달되는값이 실수형이면 에러 발생
		System.out.println("member변수의 값을 반환받아 출력 : " +  obj01.getValue()); //3
		
		//obj01.setValue("이해할수 있다."); //셀제 메소드 호출시 전달되는값이 문자열형이면 에러 발생
		System.out.println("member변수의 값을 반환받아 출력 : " + obj01.getValue()); //3
		
		/*
			결론 : TestClass는 정수 데이터만 처리하는 클래스로 선언되었기 떄문에
			      실수형이나 문자열형은 처리할수 없다.
			      만일 실수형이나 문자열형 처리하도록하려면
			      setValue메소드를 오버로딩해서 2개더 만들어야 합니다.
			      하지만 Object부모클래스 자료형의 매개변수로 클래스를 설계하면??
			      어떤 자료형도 매개변수로 받을수 있는 클래스가 됩니다.
		*/
		
		
	}

}












