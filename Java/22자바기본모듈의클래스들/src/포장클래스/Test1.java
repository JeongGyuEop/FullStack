package 포장클래스;

//boolean 기본자료형 데이터 true 또는 false를 저장시키기 위한
//Boolean 포장(래퍼)클래스 흉내내어 직접 만들어보기
//만들 포장(래퍼)클래스명 -> BooleanWrapper
class BooleanWrapper{
	//변수
	private boolean power;
	//생성자
	public BooleanWrapper(boolean power) {
		this.power = power;
	}
	//toString메소드 오버라이딩
	@Override
	public String toString() {
		return "" + this.power;
	}
}

public class Test1 {
//boolean기본자료형 데이터 true또는 false를 매개변수 Object obj로 받아서
//문자열("true" 또는 "false")로 출력할 기능의 showData메소드를 static로 만들기
	public static void showData(Object obj) {
		System.out.println(obj.toString());
	}
	public static void main(String[] args) {
		//showData메소드 호출시! 
		//포장(래퍼)역할을 하는 BooleanWrapper객체메모리생성후
		//인스턴스변수 power에 true저장한 상태로 
		//BooleanWrapper자식객체의 주소를 showData메소드의 
		//매개변수 Object obj로 전달!
		showData(  new BooleanWrapper( true ) );
		//출력결과
		//"true"

	}

}








