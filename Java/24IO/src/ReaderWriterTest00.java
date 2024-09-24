

// 주제 : 문자 스트림(2바이트)으로 키보드로부터 입력받은 데이터 읽어와 출력

import java.io.InputStreamReader;
import java.io.Reader;

public class ReaderWriterTest00 {

	public static void main(String[] args) {

		int data = -1;
		
		// 바이트 스트림? (한 바이트 단위로 읽어들일 입력스트림 통로) System.in
		// 문자 스트림? (2바이트(영문 한문자) 단위로 읽어들일 입력스트림 통로)
		//				new InputStreamReader(System.in); 으로 만들어 주자.
		
		// 부모 문자스트림 Reader자료형의 참조변수에 업캐스팅해서 저장
		Reader myIn = new InputStreamReader(System.in);
		// 			  -> 키보드(표준 입력장치)와 연결된 2바이트 단위로 읽어들일 입력스트림
		
		try {
			
			while(true) {
				// 변환된 문자스트림 InputStreamReader를 통해 read 메소드를 호출하면
				// 키보드로부터 입력받은 전체 데이터 중에서 2바이트에 저장될 문자 데이터를
				// 한 번 읽어들인다.
				data = myIn.read();
				
				if(data == -1) {
					break;
				}
				
				// 읽은 문자크기의 데이터를 모니터(표준 출력장치)에 내보낸다(출력)
				System.out.print((char)data);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
