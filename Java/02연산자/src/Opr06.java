
/*
 *    참고. 문자 데이터의 비교
 *    
 *    	자료형을 공부할 때 설명했듯이 문자 데이터는 내부적으로 정수 형태로 저장된다.
 *    	문자 데이터 역시 정수형에 포함된다.
 *    	그렇기 때문에 문자 데이터를 관계연산자(>, <, <=, >=, ==, !=)에 적용해서 대소 관계를 비교 판단할 수 있다.
 *    
 *    	문자 데이터의 크기를 비교한다는 의미는 아스키코드값(10진수)를 비교한다는 의미이다.
 *    
 *    	예를 들어 대문자 'A'와 대문자 'B'를 비교하면
 *    	'A'의 아스키코드값이 65이고 'B'의 아스키코드값이 66이므로
 *    	'A'문자가 'B'문자보다 작다.
 *    
 *    	예를들어 대문자와 소문자를 비교해 본다면
 *    	대문자 'A'는 소문자 'a'보다 작다
 *    	대문자 'A'는 아스키코드값이 65이고 'a'의 아스키코드값이 97이기 때문이다.
 *    
 */

// 예제. 변수에 저장된 데이터가 대문자인지~ 아닌지~ 판단하기(논리 연산자 사용)

public class Opr06 {

	public static void main(String[] args) {
		char ch = 'b'; // 문자를 저장할 변수 ch 선언 후 'b' 문자 저장
					   // 'b' 소문자 아스키코드값 -> 98
		
		// "대문자임" 또는 "대문자가 아님" 문자열 중 하나를 저장할 변수
		String s;
		
		// ch 변수에 저장된 문자가 대문자인지? 소문자인지?
		
		// ch >= 'A'
		// ch 변수에 저장된 'b'(98)이 대문자 'A'(65)보다 크거나 같은지
		// 컴퓨터에게 물으면? 컴퓨터는 개발자에게 돌려 주는 값이 true이다.
		
		// ch <= 'Z'
		// ch 변수에 저장된 'b'(98)이 대문자 'Z'(90)보다 작거나 같은지
		// 컴퓨터에게 물으면? 컴퓨터는 개발자에게 돌려 주는 값이 false이다.
		
		// 대문자이냐? 'A' ~ 'Z' 사이냐?
		s = (ch >= 'A' && ch <= 'Z') ? "대문자임" : "대문자가 아님";
		//      true   &&   false    
		//            false
		//								    true  :  false
		
		// b문자는 대문자가 아님
		System.out.println(ch + " 문자는 " + s);
		
		
		


	}

}
