package PB;

import PA.A; // 다른 패키지 안에 만들어져 있는 A class 를 불러와야
			 // B extends A를 통해 상속받을 수 있다.

// 자식 클래스 역할을 한다.
public class B extends A{

	void set() {
		// 다른 PA 패키지의 부모 A객체에
		// 만들어 놓은 i 변수에 접근 불가능
		// super.i = 1;
		
		// 다른 PA 패키지의 부모 A객체에
		// protected int pro 변수에 접근 가능
		super.pro = 2;
		
		// 다른 PA 패키지의 부모 A객체에
		// private int pri 변수에 접근 불가능
		// super.pri = 3;
		
		// 다른 PA 패키지의 부모 A객체에
		// public int pub 변수에 접근 불가능
		super.pub = 4;
	}
	
	public static void main(String[] args) {
		
		B b = new B();
		// B 객체 메모리를 생성하면
		// 부모 A 객체 메모리도 같이 생성된다.
		
		// A 부모 객체 메모리 모습
		/*
		 	int i; // default 접근제어자 사용
			protected int pro;
			private int pri;
			public int pub;
	
			public void print() {
				System.out.print("i=" + this.i + ", ");
				System.out.print("pro=" + this.pro +", ");
				System.out.print("pri=" + this.pri +", ");
				System.out.println("pro=" + this.pub +"입니다.");
			}
		 */
		
		 // B 자식 객체 메모리 모습
		 /*
		  	void set() {
		  		super.pro = 2;
		  		super.pub = 4;
		  	}
		  */
		b.set();
		b.print(); // i=0, pro=2, pri=0, pro=4입니다.

	}

}
