
public class test {

	public static void main(String[] args) {
		
		// 문제 1
		// 다음 코드를 실행 했을 때 출력 결과를 작성해 보시오
//		int x = 10;
//		int y = 20;
//		int z = (++x) + (y--);
//		System.out.println(z); // 31
				
		//-------------------------------------------------------------------------------------------
		
		// 문제 2
		// 다음 코드를 실행 했을 때 출력 결과를 작성해 보시오
//		int score = 85;
//		String result = (!(score>90)) ? "가" : "나";
//		System.out.println(result); // "가"		
		
		//-------------------------------------------------------------------------------------------

		// 문제 3
		// 534 자루의 연필을 30 명의 학생들에게 똑같은 개수로 나누어 줄 떄 1인당 몇 개를 가질 수 있고,
		// 마지막에 몇 개가 남는지를 구하는 코드입니다. ()에 들어갈 알맞은 코드를 차례로 작성하시오.
//		int pencils = 534;
//		int students = 30;
//			// 학생 한 명이 가지는 연필의 수
//		int pencilPerStudent = (pencils / students);
//		System.out.println(pencilPerStudent); // 17
//			// 남은 연필의 수
//		int pencilsLeft = (pencils % students);
//		System.out.println(pencilsLeft); // 24
		
		//-------------------------------------------------------------------------------------------

		// 문제 4
		// 다음은 십의 자리 이하를 버리는 코드이다. 변수 value의 값이 356이라면 300이 나올 수 있도록
		// ()안에 알맞은 코드를 작성하시오(산술연산자만 사용)
//		int value = 356;
//		System.out.println((value/100)*100); // 300
		
		//-------------------------------------------------------------------------------------------
		
		// 문제 5
		// 다음 코드는 사다리꼴의 넓이를 구하는 코드입니다. 정확히 소수자릿수가 나올 수 있도록()에
		// 들어갈 수 있는 코드를 모두 선택하시오
//		int lengthTop = 5;
//		int lengthBottom = 10;
//		int height = 7;
//		System.out.println((lengthTop + lengthBottom) * height / 2.0); // 52.5
//		System.out.println((lengthTop + lengthBottom) * height * 1.0 / 2); // 52.5
//		System.out.println((double)(lengthTop + lengthBottom) * height / 2); // 52.5
//		System.out.println((double)((lengthTop + lengthBottom) * height / 2)); // 52.0
		
		//-------------------------------------------------------------------------------------------

		// 문제 6
		//다음 코드는 비교 연산자와 논리 연산자의 복합 연산식입니다. 연산식의 출력결과를 작성해 보시오
//		int x = 10;
//		int y = 5;
//		System.out.println((x>7) && (y<=5)); // true
//		System.out.println((x%3 == 2) || (y%2 != 1)); // false
		
		//-------------------------------------------------------------------------------------------

		// 문제 7
		// 다음은 % 연산을 수행한 결과값에 10을 더하는 코드이다. NaN 값을 검사해서 올바른 결과가 출력될
		// 수 있도록 () 안에 들어갈 코드를 작성하시오
//		double x = 5.0;
//		double y = 0.0;
//		double z = 5 % y;
//		if( Double.isNaN(z)) {
//			System.out.println("0.0으로 나눌 수 없습니다.");
//		} else {
//			double result = z + 10; 
//			System.out.println("결과 : " + result);
//		}
		
		/*
		 * 	7번 참고.
		 * 		/ 또는 % 연산자를 사용할 때 좌측 피연산자가 정수 타입인 경우 나누는 수는 0을 사용할 수 없다.
		 * 		만약 0으로 나누면 컴파일은 정상적으로 되지만, 실행하면 ArithmeticException(예외) 발생
		 *  	
		 *  			ArithmeticException(예외)  --> 정수를 0으로 나눌 경우 발생!!
		 *  
		 *  	Java는 프로그램 실행도중 예외가 발생하면 실행이 즉시 멈추고 프로램은 종료된다.
		 *   	ArithmeticException(예외)가 발생했을 경우 프로그램이 종료되지 않으려면 예외처리를 해야한다.
		 *   
		 *   	실수 타입인 0.0 또는 0.0f로 나누면 ArithmeticException이 발생하지 않고, / 연산의 결과는 Infinity(무한대)
		 *   	값을 가지며, % 연산의 결과는 NaN(Not a Number)를 가진다.
		 *   
		 *   
		 *   	5 / 0 -->  ArithmeticException
		 *   	5 % 0 -->  ArithmeticException
		 *   	5 / 0.0 --> infinity
		 *   	5 % 0.0 --> NaN
		 *   
		 *   	/ 와 % 연산의 결과가 Infinity 또는  NaN이 나오면 이 값들로 산술 연산을 하면 어떤 수와 연산하더라도
		 *   	infinity와 NaN이 산출되어 데이터가 엉망이 될 수 있다.
		 *   
		 *   	--> 코드에서 /와 % 연산의 결과가 Infinity 또는 NaN인지 확인하려면
		 *   		Double.isinfinite()와 Double.isNaN() 메소드를 이용하면 된다.
		 * 			
		 */

	}

}
