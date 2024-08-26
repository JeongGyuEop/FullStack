

// 예제. 구구단 2단 출력하기

public class For04 {

	public static void main(String[] args) {
		
		// for 문을 제저할 제어변수 선언
		int i;
		
		// 출력할 단을 저장할 변수 선언 후 2 저장
		int a = 2;
		
		System.out.println("<<-----" + a + "단----->>");
		
		for(i=1; i<=9; i++) {
			System.out.println(a + " x " + i + " = " + (a * i));
		}

	}

}
