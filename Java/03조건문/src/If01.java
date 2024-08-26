
// 예제 :  절대값 구하기
// 절대값을 구하는 프로그램 작성
// -> 음수이면 부호를 변경한다.

public class If01 {

	public static void main(String[] args) {
		int num;
		num = -5;
		// 변수 num에 저장된 값이 0보다 작은 음수냐? 라고 컴퓨터에게 묻는 조건식
		if(num < 0) {
			//조건식이 참치면 실행할 실행문
			// num 변수에 저장된 -5를 5로 만들어서 다시 num 변수에 저장
			// num = -5 * -1;
			num = -(num); // -(-5)이므로 부호가 변경된 5를 num 변수에 저장
		}
		// -5의 절댓값 출력
		System.out.println(" absolute num = " + num);
		
		// 변수 num에 양수 5를 저장 
		num = 5;
		
		// 변수 num에 저장된 값이 0보다 작은 음수냐?라고 컴퓨터에게 묻는 조건식
		// 0보다 큰 양수 이기 때문에 조건식에 만족하지 않으므로 
		// if의 {}중괄호 내부의 실행문은 실행되지 않는다.
		if(num < 0) {
			num = -num;
		}
		
		System.out.println(" absolute num = " + num); // 5

	}

}
