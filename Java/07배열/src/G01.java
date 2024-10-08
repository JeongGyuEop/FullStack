

// 예제. 1차원 배열을 생성하고 값 저장과 얻어서 출력

public class G01 {

	public static void main(String[] args) {
		
		// 배열 생성 문법 1.
		// 자료형 []변수명 = new 자료형[원소의 갯수] 
		
		// 5명의 점수를 정수로 저장하기 위한 배열 선언(생성)
		int []score = new int[5];
		
		//		배열 메모리의 주소번지는 랜덤으로 16진수로 붙는다.
		//				0x12
		
		//				-----------------------------------------
		//					0	|	0	|	0	|	0	|	0
		//				-----------------------------------------
		//      			0		1		2		3		4 	--> index
		//	| 0x12 | = 
		
		// 생성한 배열의 각 칸(각 원소)에 접근해서 값을 저장하는 문법
		// 배열명[index] = 저장할 값;
		score[0] = 95;
		score[1] = 70;
		score[2] = 80;
		score[3] = 75;
		score[4] = 100;
	
		//				-----------------------------------------
		//					95	|	70	|	80	|	75	|	100
		//				-----------------------------------------
		//      			0		1		2		3		4 	--> index
		
		// 배열에 각 원소에 저장된 데이터를 꺼내오는 문법
		// 배열명[index]
		
		//score 배열에 0 index 위치에 저장된 95를 꺼내와서 출력
		System.out.println(score[0]);
		
		System.out.println();
		
		// for 반복문을 이용하여 score 배열 메모리에 저장된 각 원소를 차례대로 얻어와서 출력
		for(int i=0; i<score.length; i++) {
			System.out.println("score[" + i + "] = " + score[i]);
		}
		
	}

}
