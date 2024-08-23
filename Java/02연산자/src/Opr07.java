
/*
 * 예제. 변수의 값을 1 증가 시키는 증가 연산자 ++
 */

public class Opr07 {

	public static void main(String[] args) {
		int a = 10;
		int b = 10;
		
		++a;
		System.out.println(a); // 11
		
		b++;
		System.out.println(b); // 11
		
		int c = 1;
		// ++ 증가연산자가 후행처리 되므로 c 변수에  저장된 값 1을
		// 먼저 출력 후 c변수의 값 1증가해서 2를 저장하게 된다.
		System.out.println(c++); // 1
		System.out.println(c); // 2
		
		// 현재 c = 2;
		// ++ 증가 연산자가 선행처리 되므로  c변수에 저장된 값 2를 
		// 먼저 1증가 시킨 후 c 변수 값을 출력하니 3이 출력
		System.out.println(++c); // 3
		System.out.println(c); // 3
		
	}

}
