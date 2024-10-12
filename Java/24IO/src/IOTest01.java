
// 주제 : 키보드로부터 입력된 문자열의 문자개수 세기

import java.io.InputStream;
import java.io.OutputStream;

public class IOTest01 {

	public static void main(String[] args) {
		
		// 키보드를 이용해 입력한 전체 데이터 크기 중
		// 읽어들인 한 바이트 크기의 데이터를 저장할 변수
		int data = 0;
		
		// 읽어들인 문자열의 문자 개수를 계산해서 저장할 변수
		int cnt = 0;
		
		// 키보드로 부터 입력한 전체 데이터 중 1바이트씩 읽어들일
		// 입력스트림 역할의 BufferedInpuntStream자식 객체 얻기
		InputStream myIn = System.in; // <- 입력 스트림 통로
		
		// BufferedInput 입력 스트림 통로를 통해 1바이트씩 읽어들인 데이터를
		// 모니터 등의 출력장치에 1바이트씩 내보낼 역할의 출력스트림 
		// PrintStream 객체 얻기
		OutputStream myOut = System.out; // <- 출력스트림 종료
		
		System.out.println("문자를 입력하세요. IOTest01.class 프로그램을 "
				+ "끝내려면 x혹은 X나 ctrl + z 단축키를 누르세요.");
		
		try {
			// 윕 입력 스트림 통로(BufferedInputStream)를 통해
			// 1바이트씩 읽어 들이는데 ....
			// 입력 스트림 통로에 읽어들일 데이터가 존재할 때만 반복해서 계속 읽어들이기
			while((data = myIn.read()) != -1) {
				// 소문자 x나 대문자 X를 누르면
				// 더이상 읽어들이지 않고 while반복문 빠져나가기
				if(data == 'x' || data == 'X') {
					break; // while반복 빠져나가기
				}
				// 입력받은 문자의 개수 증가
				cnt++;
				// 입력받아 1바이트씩 읽어들인 데이터를 모니터 화면에 출력시
				// 출력 스트림 통로를 이용해 1바이트 단위로 내보내어 출력
				myOut.write(data);
			}
		} catch(Exception e) {
			// 입출력 관련 예외가 발생하면 예외 메시지 출력
			e.printStackTrace();
		}
		
		System.out.println("입력받은 총 문자 개수 -> " + cnt);

	}

}
