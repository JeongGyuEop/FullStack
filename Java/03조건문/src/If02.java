

// 예제 : if else 문을 사용하여, 해당 문자가 영문 소문자인지 아닌지 확인하는 예제

public class If02 {

	public static void main(String[] args) {
		char ch = 'J';
		
		// ch 변수에 저장된 대문자 J는 10진수로 74
		// 소문자 a는 97
		// 소문자 z는 122
		
		// 74 >= 97 --> false
		// 74 <= 122  --> true
		if(ch >= 'a' && ch <= 'z' ) { // ch 변수에 저장된 문자가 소문자냐?
									  // ch 변수에 저장된 문자가 소문자 a이상이고 z이하냐?
			System.out.println("해당 문자는 영문 소문자입니다.");
		} else { // ch 변수에 저장된 문자가 소문자가 아님
			System.out.println("해당 문자는 영문 소문자가 아닙니다.");
		}

	}

}
