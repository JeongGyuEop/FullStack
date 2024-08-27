

// 예제. 구구단 2단부터 9단까지 반복해서 출력

public class E03 {

	public static void main(String[] args) {
		
		// 단을 결정하는 변수 선언
		int dan; // 2단 ~ 9단 까지 단수를 저장. 바깥 for에서 사용
		
		// 매 단마다 곱하는 수를 저장할 변수 선언 (1 ~ 9) 안쪽 for문에서 사용
		int n;
		
		
		// 바깥 for문에서 dan = 2일 때 dan <= 9; 조건식은 참이므로
		// 바깥 for문의 {} 내부의 모든 코드가 한 번 실행된다.
		// System.out.println("** " + dan + "단 **");
		
		for(dan = 2; dan <= 9; dan++) { // 2 ~ 9 단
			
			
			System.out.println("** " + dan + "단 **");
			
			
			// 안쪽 for문에서 n = 1 일 때 n <= 9; 조건식은 참이므로
			   // System.out.println(dan + "*" + n + " = " + (dan * n)); 코드가 출력된다.
			for(n = 1; n <= 9; n++ ) { // 1~9 곱하는 수만큼 반복
				
				System.out.println(dan + "*" + n + " = " + (dan * n));
				
			}
			
			System.out.println();
			
		}
		
		
		/*
		 *   *2단*
		 * 2 * 1 = 2
		 * 2 * 2 = 4
		 * ...
		 * 2 * 9 = 18
		 * 
		 *   *3단*
		 * 3 * 1 = 3
		 * 3 * 2 = 6
		 * ...
		 * 3 * 9 = 27
		 * 
		 * .
		 * .
		 * .
		 * 
		 */

	}

}
