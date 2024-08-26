

// 예제. 1부터 10사이의 짝수의 합과 홀수의 합을 각각 for문을 이용해 구해서 출력
public class E01 {

	public static void main(String[] args) {
		
		// 두 개의 for 문을 제어할 제어변수 n 선언
		int n;
		
		// 홀수들의 합과 짝수들의 합을 각각 누적해서 저장할 변수 2개 선언
		int odd_tot, even_tot;
		
		// 제어 변수 값이 홀수가 구해지도록 하여 그 제어 변수값을 누적해서 홀수의 합을 구한다.
		for(odd_tot=0, n=1; n<=10; n+=2) {
			// 홀수들을 odd_tot 변수에 누적
			odd_tot += n;
		}
		// 참고 : 만일 두 개 이상의 변수에 초기값을 지정하려고 할 경우 ','로 연결하여 작성하면 된다.
		System.out.println(" odd_tot 변수값(1+3+5+7+9) = " + odd_tot); // 홀수 누적 합 출력
		
		// 제어 변수 값이 짝수가 구해지도록 하여 그 제어 변수 값을 누적해서 짝수의 값을 구한다.
		for(even_tot=0, n=2; n<=10; n+=2) {
			// 짝수들을 even_tot 변수에 누적
			even_tot += n;
		}
		System.out.println(" even_tot 변수값(2+4+6+8+10) = " + even_tot);
		
		
		// 결론
		// 위 예제는 for 반복문을 2번 사용하여 홀수의 합과 짝수의 합을 구해도 되지만
		// for 문을 한 번만 사용하여 홀수의 합과 짝수의 합을 구할 수도 있다.
		// 다음 예제에서 진행해보자.

	}

}
