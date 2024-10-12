package ex1;


/*
 	주제. 배열의 index위치의 범위를 벗어나 접근했을 경우 
 	  	  발생하는 ArrayIndexOutOfBoundsException 예외 종류와 
 	  	  
 	  	  숫자 형태의 문자열이 아닌데 숫자로 변경하려고 할때 발생하는 
 	  	  NumberFormatException 예외 종류를
 	  	  
 	  	  각각 다르게 예외처리 하는 코드의 예 
 */

// 참고. 숫자형태 "100"문자열을 숫자로 변환해서 반환하는 메소드
//  	Integer클래스의 int parseInt(String value) 메소드 
//      Integer.parseInt("100") --> 100

public class ExceptionHandlingExample4 {

	public static void main(String[] args) {
		
		// 문자열을 저장한 배열 생성
		String[] array = {"100", "1oo"};
//					index   0      1
		
		// array 배열에 저장된 문자열 2개를 얻어서 정수로 변환
		for(int i=0; i<=array.length; i++) {
			try {
//				NumberFormatException 숫자변환 안됨 
//												"100"   ----->  100
//												"1oo"   ----->  x
				
//				ArrayIndexOutOfBoundsException 존재하지 않는 배열 칸에 접근 안됨
//											 array[2] <- 작성되면 예외발
				int value = Integer.parseInt(array[i]);
				System.out.println("array[" + i + "] : " + value );
				
			} catch(ArrayIndexOutOfBoundsException e) {
				
				// 예외가 발생하면 예외정보 출력
				e.printStackTrace();
				// 예외처리할 코드
				System.out.println("array 배열에 없는 원소에 접근했다.");
				
			} catch(NumberFormatException e) {
				
				// 예외가 발생하면 예외정보 출력
				e.printStackTrace();
				// 예외처리할 코드
				System.out.println("array[1]위치에 저장된 '1oo'은 숫자로 변환할 수 없음.");
				
			}
			
		}
		
	}

}
