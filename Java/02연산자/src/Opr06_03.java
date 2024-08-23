
/*
 * 
 */

public class Opr06_03 {

	public static void main(String[] args) {

		// 증감 연산자의 연산순서
		
		int x = 10;
		
		int y = x-- + 5 + --x;
		// 순서 1. x-- + 5
		//         10-- + 5
		//         --는 후위연산자 이므로 10 + 5를 먼저 계산한 다음
		//         1 감소시켜 10을 9로 만들어 준다.
		// x-- + 5 ---> 15
		// x 변수의 값을 9로 만들어준다.
		
		// 순서 2. --x 계산
		//         -- 전위감소연산자가 x 변수명 앞에 붙었으므로 먼저 1 감소시키면
		//  	   x 변수의 값을 9에서 8로 변경 시킨다.
		
		// 순서 3. 순서 1에서 만든 15와 순서 2에서 만든 x값 8을 + 연산하여
		//         15 + 8을 하여 23으로 만든다.
		
		// 순서 4. 마지막으로 int y = 에 의해 23이 대입되어 저장된다.

		// y 변수의 값은 23이 출력될 것이고 x 변수의 값은 8이 출력될 것이다.       
		
		
		System.out.println("x: " + x +", y: " + y);
		//                  x: 8       , y: 23
				
				
	}

}
