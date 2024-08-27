

// 예제. 1부터 10 사이의 정수 중에서 3의 배수만 제외하고 반복해서 출력하기

/*
 	continue 보조제어문?
 		- 반복문 내부에 작성하는 구문으로 continue문이 실행되면 for 반복문에서는
 		  증감식으로 제어가 옮겨진다.
 		  또한 while반복문에서는 다시 조건검사를 위해 조건식으로 제어가 옮겨진다.
 		  
 	반복문 {
 		
 		코드 1;

 		continue;
 		
 		코드 2;
 		
 	}
 */

public class F02 {

	public static void main(String[] args) {
		
		for(int n=1; n<=10; n++) {
			
			// n 변수 값을 3으로 나누었을 때 떨어지면?
			// 증감식 n++로 가서 다시 n 변수값 1증가시켜! 라고 명령
			if(n%3 == 0) { 
				continue;
			}
			
			System.out.print("  " + n); //   1  2  4  5  7  8  10
			
		}
		

	}

}
