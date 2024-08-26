

// 예제. for 문을 한 번만 사용하여 짝수의 합, 홀수의 합을 각각 구하기 위해
//       for 문 내부에 if문을 사용한다.

public class E02 {

	public static void main(String[] args) {
		// for문의 시작 초기값을 설정할 제어변수 n을 선언
		int n;
		
		// 홀수의 합을 저장할 변수
		int odd_tot;
		// 짝수의 합을 저장할 변수
		int even_tot;
		
		for(n=1, odd_tot=0, even_tot=0; n<=10; n++) {
			if(n%2 == 0) {
				even_tot += n;
			} else {
				odd_tot += n;
			}
		}
		
		System.out.println(" odd_tot 변수값(1+3+5+7+9) = " + odd_tot); // 홀수 누적 합 출력
		System.out.println(" even_tot 변수값(2+4+6+8+10) = " + even_tot); // 짝수 누적 합 출력

		
	}

}
