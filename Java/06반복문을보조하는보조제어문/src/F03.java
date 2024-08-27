

// 예제. while 반복문을 이용한 무한 루프

/*
 	while(true){
 	
 		조건식 자리에 true가 존재하면 무산 실행될 반복코드;
 	
 	}
 	
 	while 문은 조건식의 결과가 참이면 반복을 계속하고,
 	거짓이면 반복문을 벗어난다.
 	while 문을 이용한 무한 루프는 일반적으로 조건식 자리에 true를 기술하여 표현한다.
 	
 */

public class F03 {

	public static void main(String[] args) {
		
		// 초기식
		int n = 0;
		
		
		// while 반복문
		while(true) {
			System.out.println("Fall Wonderland");
			
			if(++n >= 10) {
				break; // 반복문을 빠져나간다.
			}
		}

		System.out.println("The End");

	}

}
