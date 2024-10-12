
/*
 *  예제: 문자 형태의 자료형 저장하기
 */

public class Data07 {

	public static void main(String[] args) {
		/*
		 * 	참고. 
		 * 		문자 상수는 각 문자에 대응하는 정수값 형태를 2진수로 변경해서
		 * 		컴퓨터의 특정 영역인 변수 메모리에 저장된다.
		 * 
		 * 		아스키코드란? 문자와 일대일 대응관계에 있는 정수 코드를 말한다.
		 * 		
		 * 		암기하면 유용한 아스키코드값에 대응되는 정수값 (암기)
		 * 			대문자 'A'   -   10진수 정수로 65
		 * 			소문자 'a'   -   10진수 정수로 97
		 * 
		 * 			정수 형태의 문자 '0'   -   10진수 정수로 48
		 * 
		 */
		
		// 2바이트 크기의 한 문자를 정수형태로 변형해 저장할 x변수 선언
		char x;
		// 선언된 x 변수 메모리에 'A'문자 저장!
		x = 'A'; 
		
		
//		System.out.printf("포멧형식의 기호들", 실제출력할 데이터들);
		
		// 참고. x 변수에 저장된 아스키코드표의 문자('A' 또는 'a' 등...)에 대응되는 정수값을 
		//       얻기 위해서는 () 캐스트 기호를 이용하여 문자를 정수로 강제 형 변환해서 
		//       65를 출력해야함.
		System.out.printf("%c -> %d \n", x, (int)x);
		// 				  "A -> 65"
		
		x = '0';
		// 0 -> 48 출력
		System.out.printf("%c -> %d \n", x, (int)x);
		
		x = 0; // 0은 NULL 문자를 의미
		// -> 0 출력
		System.out.printf("%c -> %d \n", x, (int)x);
		
		x = 'a';
		// a -> 97 출력
		System.out.printf("%c -> %d \n", x, (int)x);
		
		
		
	}

}














