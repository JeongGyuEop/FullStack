

// 예제 : if else if else 문을 사용하여
//        해당 문자가 영문 소문자나 영문 대문자인지, 아니면 영문자가 아닌지 확인하는 예제

public class If03 {

	public static void main(String[] args) {
		
		char ch = 'J';
		
		// ch 변수에 저장된 대문자 J -> 74
		// 소문자 a -> 97
		// 소문자 z -> 122
		// 대문자 A -> 65
		// 대문자 Z -> 90
		
		// ch변수에 저장된 값이 영문 소문자?
		// 74 >= 97 --> false
		// 74 <= 122 --> true
		if(ch >= 'a' && ch <= 'z') {
			
			System.out.println("해당 문자는 영문 소문자 입니다.");// 출력안됨
			
		} else if( ch >= 'A' && ch <= 'Z' ) { // 영문 대문자냐?
			// 74 >= 65 --> true
			// 74 <= 90 --> true
			
			System.out.println("해당 문자는 영문 대문자 입니다."); // 출력됨
			
		} else { // 영문자가 아니냐?
			
			System.out.println("해당 문자는 영문자가 아닙니다."); // 출력안됨 
			
		}
	
		
		
		
		
	}

}
