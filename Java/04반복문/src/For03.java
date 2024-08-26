

// 예제. for문의 다양한 활용

public class For03 {

	public static void main(String[] args) {
		
		// 제어 변수 i선언
		int i;
		
		// i의 초기값을 1로 설정하여 1 씩 증가시키면서 10일 때 까지 반복해서 i변수 값 출력
		// 1 2 3 4 5 6 7 8 9 10
		for(i=1; i<=10; i++) {
			System.out.print(i + " ");
		}
		
		System.out.println("\n ------------------------------------->> ");
		
		// i의 초기값을 1로 설정하여 2씩 증가하면서 10일 때 까지 반복해서 i 변수값 출력
		// 1 3 5 7 9
		for(i=1; i<=10; i+=2) {
			System.out.print(i + " ");
		}

		System.out.println("\n ------------------------------------->> ");
		
		// 2 4 6 8 10
		for(i=2; i<=10; i+=2 ) {
			System.out.print(i + " ");
		}

		System.out.println("\n ------------------------------------->> ");
		
		// 10 9 8 7 6 5 4 3 2 1
		for(i = 10; i>=1; i--) {
			System.out.print(i + " ");
		}
	}

}
