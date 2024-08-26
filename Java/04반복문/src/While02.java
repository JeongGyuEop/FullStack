

// 예제. 1부터 10 사이의 짝수들의 총합 30을 하나 구해서 출력

public class While02 {

	public static void main(String[] args) {
		
		// 2 + 4 + 6 + 8 + 10

		// 1. 총합을 저장할 변수 tot선언 후 0초기화
		int tot = 0;
		
		// 2. while 반복문의 초기식 number 변수선언 후 1로 저장
		int number = 1;
		
		// 3, number 변수의 값이 1부터 10보다 작거나 같을 때 까지 while 반복
		while(number<=10) {
			// 3.1 number 변수 값이 짝수 인지 확인
			//     (number 변수 값이 짝수라면?)
			if(number % 2 == 0) {
				// 3.2 tot 변수에 누적
				tot += number;
			}
			// 3.3 number 변수 값 1 증가
			number++;
		}
	
		// 4. tot = 30 출력
		System.out.println("tot = " + tot);
		
		// 출력 결과
		// tot = 30
	}

}
