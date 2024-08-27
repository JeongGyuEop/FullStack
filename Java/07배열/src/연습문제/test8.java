package 연습문제;
/*
 문제. 2차원 배열 arr에 담긴 모든 값의 총합과 평균을 구하는
      프로그램을 완성 하시오.
      즉! (1) 과 (2) 영역에 들어갈 코드를 완성 해서 넣으시오.
      
      참고. 1. int형(4byte)보다 크기가 작은 자료형은 int형으로 형변환 후에 연산을 수행함
           2. 두 개의 피연산자 중 자료형의 표현범위가 큰 쪽에 맞춰서 형변환 된 후 연산을 수행함
           3. 정수형 간의 나눗셈에서 0으로 나누는 것은 금지 되어 있다.
      
출력결과
total=325
average=16.25      
 
 */
public class test8 {
	public static void main(String[] args) {
		int[][] arr = {
				{5,5,5,5,5},
				{10,10,10,10,10},
				{20,20,20,20,20},
				{30,30,30,30,30}
		};
		
		int total = 0;
		float average = 0;
		
		/*
		 (1) 총합 구하기
		*/
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				total += arr[i][j];
			}
		}
		
		//(2) 평균 구하기
		int numOfElements = 0;  // 배열 요소의 총 개수를 계산
        for(int i=0; i<arr.length; i++) {
            numOfElements += arr[i].length;
        }
        
        average = total / (float)numOfElements;
		
		
		
		System.out.println("total=" + total);
		System.out.println("average="+average);
		
		
	}

}


