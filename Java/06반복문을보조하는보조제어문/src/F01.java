

// 예제. break 문의 사용 예 1

// break 문?
// 반복문을 빠져나갈 때 사용하는 보조제어문
public class F01 {

	public static void main(String[] args) {
		
		// 1부터 10까지 자연수를 반복해서 출력
		// 1 2 3 4 5 6 7 8 9 10
		
		int n; // 제어 변수
		
		for(n=1; n<=10; n++) {
			System.out.println("  " + n);
		}
		System.out.println();
		
		// 1부터 10까지의 자연수를 반복해서 출력하되
		// 제어변수 n 변수의 값을 3으로 나누었을 때 나머지가 0과 같으면?
		// 1   2  출력하고 for 반복문을 벗어나자
		for(n=1; n<11; n++) {
			
			if(n%3 == 0) {// n 변수의 값을 3으로 나누었을 때, 반복문을 벗어난다.
				break;
			}
			System.out.println("  " + n);
			
		}
		
		System.out.println("\n break에 의해 두 번째 for {} 중괄호 빠져나온다.");
		
		/*
		 * 	switch 문에서 벗어나고자 할 때는 break문을 사용한다.
		 * 	break 문은 switch~case 문 이외의 for 문과 같은 반복문에서도 사용한다.
		 * 
		 * 	for 문에서 반복처리를 하다가 break 문을 만나면 반복문 밖으로 제어가 이동된다.
		 * 
		 * 
		 */
		

	}

}
