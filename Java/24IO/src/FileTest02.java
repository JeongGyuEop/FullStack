
// 주제 : 현재 디렉터리 내부에 저장되어 있는 파일 및 폴더 목록 출력하기

import java.io.File;

public class FileTest02 {

	public static void main(String[] args) {

		// 현재 디렉터리에 접근하기 위해 File 클래스의 객체 생성 
		// 참고. 현재 디렉터리  .
		File fileDir = new File("."); 
		
		// 현재 디렉터리에 만들어져 있는 파일명들을 String[] 배열에 담아 얻어오기
		String[] strs = fileDir.list();
		
		for(int i = 0; i < strs.length; i++ ){
			   System.out.println(strs[i]);
			   /*
				    	.DS_Store
						bin
						.classpath
						.gitignore
						.settings
						.project
						src

			    */
		}
		// d.java 파일에 접근하기 위해 File 클래스의 객체 생성
		File file = new File("/Users/jeong-gyueob/a/d.java");
		
		// File 객체 생성시 전달한 경로를 이용해 File 객체로 d.java 파일 삭제
		// d.java 파일을 삭제하기위해 File 클래스의 delete() 메소드를 호출하자
//		if(file.delete()) { // d.java 파일 삭제에 성공했다면?
//			System.out.println("파일 삭제 완료!");
//		}else {
//			System.out.println("파일 삭제 실패!");
//		}
		
		// 만들어져 있지 않은 b 디렉터리를 a 디렉터리 내부에 새로 만들기 위해 File 객체 메모리 생성 
		File file2 = new File("/Users/jeong-gyueob/a/b"); // b 디렉터리를 생성해 놓을 경로를 포함해 작성 
		
		// File 클래스의 mkdir() 메소드를 호출하면 경로로 지정된 부모 디렉터리가 존재해야 지정한 경로에 디렉터리를 생성한다.
//		file2.mkdir(); ///Users/jeong-gyueob/a/b
	
		// File 클래스의 mkdirs() 메소드를 호출하면 지정한 경로에 디렉터리가 존재하지 않으면 생성한다.
		file2.mkdirs(); ///Users/jeong-gyueob/c
		
		// File 클래스의 isFile() 메소드는 File 객체 생성시 생성자로 전달한 전체경로의 주소에
		// 실제 파일이 저장되어 있으면 true 반환, 실제 파일이 아니고 폴더면 false 반환 
		if(file2.isFile()) {
			System.out.println("파일이다.");
		}else {
			System.out.println("파일이 아닌 디렉터리이다.");
		}
		
		System.out.println("----------------------------------");
		
		
		// File 클래스의 isDirectory() 메소드는 File 객체 생성자로  전달한 전체 경로의 주소가 실제 디렉터리냐? 라고 물어보는 메소드
		// 디렉터리 이면 	true 반환, 아니면 false 반환 
		if(file2.isDirectory()) {
			System.out.println("디렉터리이다.");
		}else {
			System.out.println("파일이다.");
		}
	
	}

}
