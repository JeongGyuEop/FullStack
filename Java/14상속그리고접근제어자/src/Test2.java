

// 같은 패키지에서 접근제어자 연습

// Super라는 이름의 class 만들기
class Super {
	// public 접근제어자를 이용해
	// 4바이트 크기의 정수를 저장시킬 num1이라는 이름의 변수 선언 후 10 저장
	public int num1 = 10;
	
	// protected 접근제어자를 이용해
	// 4바이트 크기의 정수를 저장시킬 num2라는 이름의 변수 선언 후 20 저장
	protected int num2 = 20;
	
	// default 접근제어자를 이용해
	// 4바이트 크기의 정수를 저장시킬 num3이라는 이름의 변수 선언 후 30 저장
	int num3 = 30;
	
	// private 접근제어자를 이용해
	// 4바이트 크기의 정수를 저장시킬 num4이라ㄴ는 이름의 변수 선언 후 40 저장
	private int num4 = 40;
	
	// num4 변수에 저장된 값을 반환 시키는 기능을 하는 getNum4 메소드 선언
	public int getNum4() {
		return this.num4;
	}
} // Super 클래스 끝


// Super 클래스를 부모로 정하여 상속받은 Sub 자식클래스 만들기
class Sub extends Super {
	// private 접근제어자 이용하여
	// 4바이트 크기의 정수를 저장시킬 num5변수 선언
	private int num5;
	
	// num1, num2. num3, num4, num5 변수들의 값을 각각 얻어 출력 후 줄바꿈 해주는 기능의
	// print 메소드 선언
	public void print() {
		System.out.println(super.num1);
		System.out.println(super.num2);
		System.out.println(super.num3);
		System.out.println(super.getNum4());
		System.out.println(this.num5);
	}
}

public class Test2 {

	public static void main(String[] args) {
		Sub sub = new Sub();
		sub.print();
		/*
		  	출력 형식
		  	10
		  	20
		  	30
		  	40
		  	0
		 */
		

	}

}
