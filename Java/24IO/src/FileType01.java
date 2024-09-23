
// 주제 : 파일명을 입력받아 입력받은 파일명의 파일 내용을 읽어와 모니터 화면에 출력

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
public class FileType01 {// javac FileType01.java 컴파일 하면
	 					 // FileType01.class 생성
	
		// 명령프롬프트 CMD창에 입력되는 것.
		// java FileType01 FileTest02.java
		//						0			 index

	public static void main(String[] args) { 
		
		int data = 0; // 입력받은 파일의 정보를 한 바이트씩 읽어 저장할 변수
		int size = 0; // 입력받은 파일의 크기를 계산해서 저장할 변수
		
		// CMD(명령프롬프트)창에 파일명 하나만 입력했다면?
		if(args.length < 1) {
			// 입력받는 방법을 알려주기 위해 메세지 출력
			System.out.println("사용법 : java FileType01 읽어들일파일명");
			// FileType01.class 프로그램을 강제로 종료
			System.exit(0);
		}
		
		// CMD 창에서 입력한 첫번재 파일명을 문자열로 얻어 저장
		String path = args[0];
		System.out.println(path);

		// FileInputStream 객체 생성 문법1.
		// 순서 1.
		//		File f = new File("읽어올파일경로");
		// 순서 2.
		//		FileInputStream fis = new FileInpuStream(f);
		
		try {
			File f = new File(path); // File 객체 생성하여 입력한 FileTest02.java에 접근할 수 있도록 한다.
			
			FileInputStream fis = new FileInputStream(f);
			
			// FileTest02.java 파일에 저장된 데이터들을 1바이트 단위씩 읽어서
			while((data = fis.read()) != -1) {
				// FileTest02.java 파일에서 1바이트씩 읽어들인 데이터를 
				// 모니터 화면에 출력
				System.out.write(data);
				// 한번씩(1바이트단위씩)읽어들여 출력할 때마다 문자개수 계산
				size++;
				// 한글 한글자 -> 3바이트 크기에 저장할 데이터
				// 영문 한글자 -> 1바이트 크기에 저장할 데이터
				
			}
			System.out.println("파일크기 : " + size + "bytes");
			
		} catch(FileNotFoundException e) {
			// 예외처리
			System.out.println("지정된 경로에 파일을 찾을 수 없습니다.");
			e.printStackTrace();
		} catch(IOException e) {
			// 예외처리
			System.out.println("지정된 경로에 파일에서 데이터를 읽어들일 수 없습니다.");
			e.printStackTrace();
			
		}
	}

}
