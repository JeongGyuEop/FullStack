/*
문제9. 다음과 같이 정의된 메서드를 작성하고 테스트 하시오.

메서드명 : max
기  능 : 주어진 int형 배열의 값 중에서 제일 큰값을 반환한다.
		만일 주어진 배열이 null이거나 크기가 0인 경우  -999999를 반환한다
반환타입 : int
매개변수 : int[] arr   <- 최대값을 구할 배열을 받을 매개변수로 만든다 


실행결과
[3, 2, 9, 4, 7]
최대값:9
최대값:-999999
최대값:-999999

*/
public class Exercise6_20 {

	//여기에 max메소드의 코드를 작성 하세요
	
	
	
	public static void main(String[] args) {

	int[] data = {3,2,9,4,7};
	System.out.println(java.util.Arrays.toString(data));
	System.out.println("최대값 :"+max(data)); 
	System.out.println("최대값 :"+max(null)); 
	System.out.println("최대값 :"+max(new int[]{})); //크기가 0인 배열


	}

}
