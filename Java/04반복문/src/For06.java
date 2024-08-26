

// 예제. 1부터 5까지의 총합을 구해 출력

public class For06 {

	public static void main(String[] args) {
		
		// for 문을 제어할 제어변수 선언
		int i;
		
		// 1 + 2 + 3 + 4 + 5 총합을 저장할 변수선언 후 0 초기화
		int total = 0;
		
		for(i = 1; i<=5; i++) {
			// total 변수에 1 + 2 + 3 + 4 + 5 누적하여 저장
			total += i; // total = total + i
		}
		
		System.out.println("1부터 " + (i-1) + "까지의 합 : " + total);
		

	}

}
