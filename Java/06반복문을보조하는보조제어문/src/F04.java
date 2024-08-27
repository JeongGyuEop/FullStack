

// 예제. for 반복문을 이용한 무한 루프

/*
 	for( ; ; ) {
 		
 		무한 반복해서 실행할 코드;
 	
 	}
 	
 	for 문은 세미콜론 ; 만 두번 기술하면 문법적으로 문제가 없다.
 	하지만 반복문을 벗어날 조건을 기술하지 않았기 때문에 무한 루프에 빠지게 된다.
 	
 */

public class F04 {

	public static void main(String[] args) {

		int n = 0;
		
		for( ; ; ) {
			System.out.println("Fall Wonderland");
			
			// 특정 조건식이 참일 때 빠져나가자
			if(++n >= 10) {
				break; // 반복문을 빠져나간다.
			}
		}
		
		System.out.println("The End");

	}

}

/*
 	결론
 		break 보조 제어문은 무한 루프에서 탈출할 목적으로 많이 사용되기 때문에
 		반복문에서 무한 루프에 대한 개념과 작성 방법도 함께 살펴 보았다.
 */


















