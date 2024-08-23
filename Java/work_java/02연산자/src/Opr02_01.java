
/*
 *   문제 : 아래는 변수 x, y, z의 값을 서로 바꾸는 예제이다.
 *   
 *   	출력 결과와 같이 출력되도록 (1) 위치의 코드를 완성하시오
 *   
 *   출력 결과
 *   	x = 2
 *   	y = 3
 *   	z = 1
 */

public class Opr02_01 {

	public static void main(String[] args) {
		int x = 1;
		int y = 2;
		int z = 3;
		
		//(1)
		int temp;
		temp = x;
		x = y;
		y = z;
		z = temp;
		
		
		
		System.out.println("x = " + x);
		System.out.println("y = " + y);
		System.out.println("z = " + z);
				

	}

}
