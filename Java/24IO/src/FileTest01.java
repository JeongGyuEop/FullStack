
// 
/*
 	File 클래스
 		- java.io 패키지 내부에 만들어져 있는 클래스
 		- 파일이나 디렉터리(폴더)의 정보를 조작하는데 사용되는 클래스
 		- 이 File클래스는 실제로 파일에 데이터를 읽거나 쓰지는 않지만
 		  파일 및 디렉터리 경로를 적용하여 파일 또는 디렉터리 정보를 얻을 수 있다.
 		  
 	- 주요 기능 및 메소드
 		1. 파일 및 디렉토리 생성
 			createNewFile() : 파일을 생성한다.
 			mkdir() : 디렉토리(폴더)를 생성한다.
 			mkdirs() : 필요한 상위 디렉토리를 포함하여 여러 디렉터리를 생성한다.
 		
 		2. 파일 및 디렉토리 정보 확인
 			exists() : 파일이나 디렉토리가 존재하는지 여부를 확인할 수 있다.
 			isFile() : 파일인지 확인한다.
 			isDirectory() : 디렉토리인지 확인한다.
 			getName() : 파일 또는 디렉토리의 이름을 반환한다.
 			length() : 파일의 크기를 바이트 단위로 반환한다.
 			
 		3. 파일 및 디렉토리 삭제
 			delete() : 파일이나 디렉토리를 삭제한다.
 		
 		4. 파일 경로 관련
 			getPath() : 파일의 경로를 문자열로 반환한다.
 			getAbsolutePath() : 파일의 절대 경로를 반환한다.
 			
 		5. 파일 및 디렉토리 내부에 존재하는 목록 정보
 			list() : 디렉토리에 있는 파일 및 디렉터리 이름을 배열에 담아 배열을 반환
 			listFiles() : 디렉토리에 있는 파일 및 디렉토리를 File객체 배열로 반환
 
 */

import java.io.File;
import java.io.IOException;
import java.util.Date;
public class FileTest01 {

	public static void main(String[] args) throws IOException {

		// File 클래스의 객체 메모리 주소번지를 저장할 변수 선언
		File file = null ;
		
		// 파일이름을 입력받아 저장할 byte 배열 생성
		byte[] byteFileName = new byte[100];
		
		// 위 byteFileName byte 배열에 읽어온 파일이름을 String 문자열로 변환해서
		// 저장할 변수 선언
		String fileName = null;
		
		// 키보드에서 입력받은 파일이름을 InpuStream 통로를 통해 
		// 위 byteFileName byte배열의 크기만큼 한번씩 읽어들여
		// 위 byteFileName byte배열에 저장시킨다.
		// 요약 : 키보드에서 입력받은 값을 byteFileName배열에 저장,
		// 		  read(byte[] b) 메소드를 호출해서 사용한다.
		System.in.read(byteFileName);

		// 키보드에서 입력받은 파일이름 중 byteFileName배열에 저장된 바이트 데이터들을 
		// 문자열로 변환시키면서 문자열객체 메모리에 저장한 후 
		// 양쪽 공백을 거 후 반환받아 저장
		fileName = new String(byteFileName).trim();
		
		// 입력한 파일명을 이용해 실제 만들어져 있는 파일에 접근하기 위해
		// 파일명을 포함한 파일경로명을 File클래스의 객체 생성 시 생성자로 전달하여 저장
		
		//	   new File(파일이 저장된 파일명을 포함한 파일의 경로);
		file = new File(fileName);
		
		// 참고. File 클래스의 객체 메모리를 생성하면 실제 파일의 정보를 얻거나 조작할 수 있다.
		System.out.println(fileName + "파일 상세 정보 ***********");
		
		System.out.println("파일의 절대경로 : " + file.getAbsolutePath());
		System.out.println("파일 생성일 : " + new Date(file.lastModified()));
		System.out.println("파일 크기 : " + file.length() + "byte");
		System.out.println("실제 저장된 파일명 : " + file.getName());
		
		// 파일을 읽기 모드로 열어 읽을 수 있으냐? 라고 물어보며
		// 읽을 수 있으면 true, 없으면 false를 반환
		System.out.println("파일을 열러 읽을 수 있는지에 대한 반환값 : " + file.canRead());
		
		// 현재 파일이 쓰기모드로 열어 쓸 수 (기록) 있느냐? 물어보며
		// 쓰기모드로 열수 있으면 true로 반환, 쓰기모드로 열 수 없으면 false반환
		System.out.println("파일을 열러 읽을 수 있는지에 대한 반환값 : " + file.canWrite());
		
		System.out.println("파일이 저장된 부모디렉터리 이름 반환 : " + file.getParent());
		
		System.out.println("파일이 현재 숨겨져 있는 숨김 파일인지에 대한 반환값 : " + file.isHidden());
	}

}




















