
/*
 *  예제 : 수치 데이터로 사칙연산하기
 *  
 *  산술연산자
 *  	- 데이터를 더하고 + 빼고 - 곱하고 * 나누기 / 나머지 % 을 위해서
 *  	   제공되는 연산 기호들
 *  
 *  산술연산자 종류
 *  	연산자					 의미						    사용예					연산 결과
 *  	  +					  	더하기							6 + 2						8
 *  	  - 					빼기							5 - 3						2
 *  	  *						곱하기							2 * 3						6
 *  	  /						나누기							8 / 2 						4
 *  	  %						나머지							8 % 3						2	
 *  
 */		  

public class Opr01 {

	public static void main(String[] args) {
		
		// 4바이트 크기의 정수를 저장할 변수 메모리 a, b, c를 선언하고
		// a변수에는 10을 저장하고, b변수에는 4를 저장하고 c변수는 기본값 0을 저장
		int a = 10, b = 4, c;
		
		// 10 + 4;
		c = a + b;
		
		System.out.println(a + " + " + b + " = " + c);
		
		// c = 14
		// c = 10 - 4
		// c = 6
		c = a - b;
		System.out.printf("%d - %d = %d\n", a, b, c);
		
		// a = 10, b = 4, c = 6
		// 6 = 10 * 4
		// c = 40
		c = a * b;
		System.out.println(a + " * " + b + " = " + c);
		
		// 40 = 10 / 4
		// c = 2
		c = a / b;
		System.out.println(a + " / " + b + " = " + c);
		
		// a = 10, b = 4, c = 2
		//c = 10을 4로 나누었을 때 나머지 값;
		//c = 10 % 4;
		c = a % b;
		System.out.println(a + " % " + b + " = " + c);
		
		
		
	}

}
