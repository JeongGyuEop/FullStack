

// 예제 : switch 문을 사용하여 해당 문자가 영문자 모음인지 확인하는 예제

public class Switch04 {

	public static void main(String[] args) {
		
		char ch = 'i'; // 105
		
		switch (ch) {
			case 'A': // ch변수에 저장된 문자가 대문자 'A'(65)와 같냐?
				//실행문
				System.out.println("해당 문자는 'A'입니다.");
				break; // switch 문을 빠져나갑니다.
				
			case 'e' : // ch 변수에 저장된 문자가 소문자 'e'(101)과 같냐?
				System.out.println("해당 문자는 'e'입니다.");
				break;
	
			case 'i' : // ch 변수에 저장된 문자가 소문자 'i'(105)과 같냐?
				System.out.println("해당 문자는 'i'입니다.");
				break;
				
			case 'o' : // ch 변수에 저장된 문자가 소문자 'o'(111)과 같냐?
				System.out.println("해당 문자는 '0'입니다.");
				break;
				
			case 'u' : // ch 변수에 저장된 문자가 소문자 'u'(117)과 같냐?
				System.out.println("해당 문자는 'u'입니다.");
				break;
				
			// ch 변수에 저장된 문자가 'A', 'e', 'i', 'o', 'u'가 아닌 다른 문자이면
			default:
				System.out.println("해당 문자는 모음이 아닙니다.");
				break;
		}
	}

}
