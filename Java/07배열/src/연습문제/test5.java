package 연습문제;
/*
 문제. 다음은 배열을 선언하거나 초기화 한것이다.
      잘못된 것을 고르고 그 이유를 작성 하시오.  4,5
      
      1. int[] arr[];  올바른 구문
         풀이 : 2차원 배열 주소를 담을 변수를 선언할떄
               int[][] arr; 
               int[] arr[];
               int arr[][];
               
      2. int[] arr = {1,2,3,};  올바른 구문
         풀이 : 마지막의 쉼표 , 는 있어도 상관 없음
      
       
      3. int[] arr = new int[5];  올바른 구문
      
      4. int[] arr = new int[5]{1,2,3,4,5}; 잘못된 구문
         풀이 : 두 번째 대괄호[5]에 숫자를 넣으면 안됨.
               두 번쨰 대괄호[] 안에  배열의 크기를 지정할수 없음
               괄호 {}안의 데이터의 갯수에 따라 자동적으로 결정되기 때문.
         
         
      5. int arr[5]; 잘못된 구문
         풀이 : 배열 주소를 담을 변수 선언에 배열 크기 지정 할수 없음
      
      
      6. int[] arr[] = new int[3][];  올바른 구문
         
      
 */
public class test5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
