package StringTokenizerTest;

import java.util.StringTokenizer;

public class StringTokenizerExample {

	public static void main(String[] args) {
		
		String data1 = "홍길동&이수홍,박연수";
		
						//구분자기호 & 기준으로 "홍길동"과 "이수홍"을 잘라내어
						//새로운 String[]배열이 자동으로 생성되어 
						//0 index, 1 index에 차례도 저장됩니다.
						//그리고 구분자 기호 , 기준으로 뒤에 남아있는 "박연수"도 잘라내어
						//2 index 위치에 저장하게 됩니다.
						//마지막으로 새롭게 만들어진 String[] 배열 자체의 주소를 반환
		String[] arr =  data1.split("&|,");
					//  ["홍길동", "이수홍", "박연수"];
					//     0        1        2
		
		//확장 for반복문을 이용해서 arr배열에 저장된 문자열들을 차례로 얻어 출력
		for(String token   : arr  ) {
			System.out.println(token); //"홍길동"
									   //"이수홍"
									   //"박연수"
		}
		
		System.out.println("---------------------------------------------------");
		
		//자를 문자열이 저장된 변수
		String data2 = "홍길동 이수홍 박연수";
		
		//자를 전체 문자열과  구분자기호를 함께 StringTokenizer객체 생성시 생성자로 전달해 저장
		StringTokenizer  st = new StringTokenizer(data2, " ");

		//nextToken()메소드 : 빈공백" " 구분자 기호를 기준으로  차례대로 문자열을 잘라내어 반환
		
		//hasMoreTokens()메소드 : StringTokenizer객체에 잘라내올 문자열이 있으면 true반환
		//						 더이상 없으면 false를 반환하는 메소드 
		//즉! 잘라내올 문자열이 있느냐? 라고 물어보는 메소드!
		
		while (  st.hasMoreTokens()  ) {
			
			String token = st.nextToken();//"홍길동"	
										  //"이수홍"
										  //"박연수"
			System.out.println(token);
			
		}	

	}

}






