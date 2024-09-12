package ex1;


/*
 	주제. 2개 이상의 종류의 예외를 하나의 catch블록으로 동일한 예외처리 코드를 작성하고 싶을 때
 		 catch 블럭의 매개변수 자리에 예외처리클래스자료형1 | 예외처리종류클래스자료형2 와 같이
 		 |(또는) 기호를 작성하여 연결하면 된다.
 		 
 		 catch(NumberFormatException | NullPointerException) {
 		 	처리할 예외처리 코드 작
 		 }
 */

public class ExceptionHandlingExample5 {

	
	public static void main(String[] args) {

		String[] array = { "100", "1oo", null, "200" };
//					index    0      1     2      3
	
		for(int i=0; i<=array.length; i++) {
			
			try {
				
				// array 배열에 저장된 데이터들을 얻어 숫자로 변환 시도
				int value = Integer.parseInt(array[i]);	
				
			} catch(ArrayIndexOutOfBoundsException e) {
				
				System.out.println("배열 인덱스가 초과됨 : " + e.getMessage());
			
			// 2가지 종류의 예외가 발생했을 때 동일한 예외처리 하는 곳 
			} catch(NullPointerException | NumberFormatException e) {
				// 예외정보 출력 
				System.out.println("데이터에 문제가 있음: " + e.getMessage());
			}
		}

	}

}
