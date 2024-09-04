package com.sub;

import com.sup.Super;


// com.sup 패키지에 만들어 놓은 Super 클래스를 상속받아 새롭게 만드는
// Sub 클래스 작성
public class Sub extends Super{
	
	// private 접근 제어자 사용
	// 4바이트 크기의 정수를 저장시킬 num5 변수 선언
	private int num5;
	
	// public 접근제어자 사용 
	// num1, num2. num3, num4, num5 변수들의 값을 각각 얻어 출력 후 줄바꿈 해주는 기능의
	// print 메소드 선언
	public void print() {
		System.out.println("num1 = " + super.num1);
		System.out.println("num2 = " + super.num2);
		System.out.println("num3 = " + super.getNum3());
		System.out.println("num4 = " + super.getNum4());
		System.out.println("num5 = " + this.num5);
	}

	public static void main(String[] args) {
		Sub s = new Sub();
		s.print();
	/*
	  	출력 형식
	  	num1 = 10
	  	num2 = 20
	  	num3 = 30
	  	num4 = 40
	  	num5 = 0
	 */
	}

}
