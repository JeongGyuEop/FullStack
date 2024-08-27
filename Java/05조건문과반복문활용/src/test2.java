

/*
 문제. 아래의 다중 for문을 while문으로 변경 하시오.

출력결과
*
**
***
****
*****
******
*******
********
*********
**********
***********

*/
public class test2 {
	public static void main(String[] args) {

		//1. 바깥 for의 int i = 0; <-초기식을 따로 빼낸다
		int i = 0;
		//2. while문 형태로 ()중괄호에 조건식 작성한다
		while(i <= 10) {
			
			//2.1 안쪽 while문에서 사용할 초기식
			int j = 0;
			
			//2.2 안쪽 while문의 조건식
			while(j <= i) {
				
				System.out.print("*");
				j++;
			}
			
			System.out.println();
			i++;
		}
		
		
//		for(int i=0; i<=10; i++) {
//			
//			for(int j=0; j<=i; j++) {
		
//				System.out.print("*");
//			}
//			System.out.println();
//		}

		
		/*
		 
		*
		**
		***
		****
		*****
		******
		*******
		********
		*********
		**********
		***********
		 				
		 */		
		
		
		
	}//main메소드 닫기

}//test2 class 닫기





