
/*
 * 
 */


public class Opr01_03 {

	public static void main(String[] args) {
		
		// 정수형 변수
		int intVar1 = 10;
		int intVar2 = 20;
		
		// 실수형 변수
		double doubleVar = 5.5;
		
		// 1. 정수형 + 정수형의 덧셈
		int intSum = intVar1 + intVar2; // 10 + 20 = 30
		System.out.println("intSum : " + intSum); // "intSum : 30"

		// 2. 정수형 + 실수형의 덧셈
		double mixedSum = intVar1 + doubleVar; // 10 + 5.5  = 15.5
		
		// 정수형 + 실수형
		// 정수형이 실수형데이터로 자동형변환된 후 실수형 데이터랑 + 연산한다.
		System.out.println("mixedSum : " + mixedSum); // "mixedSum : 15.5"
		
		// 3. 실수형 + 실수형의 덧셈
		double doubleSum = doubleVar + 3.3;
		//						5.5 + 3.3 = 8.8
		System.out.println("doubleSum : " + doubleSum); // "doubleSum : 8.8"
		
		// 4. 정수형 * 실수형의 곱셈
		double mixedProduct = intVar2 * doubleVar;
		//						   20 * 5.5
		//						   20 * 5.5 = 110.0
		System.out.println("mixedProduct : " + mixedProduct); // "mixedProduct : 110.0"
		
		// 5. 정수형 / 정수형 나눗셈
		double explicitConversion = (double)intVar1 / 3;
		//   							 10 / 3 
		//								 10.0 / 3
		System.out.println(explicitConversion); // "3.3333333333333335"
		
		
		// 6. 실수형을 정수형으로 변환(소수점 이하 버림)
		// 5.5 -> 5
		int intFormDouble = (int)doubleVar; // (int)5.5 -> 5
		System.out.println(intFormDouble); // "5"
		
		// 7. 나머지 연산
		int result = intVar2 % 3;
		//                 20 % 3;
		System.out.println(result); // "2"
		
	}

}
