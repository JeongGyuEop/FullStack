

/*
 	연습 1. 자바에서 클래스 안의 멤버변수 만들기
 	
 	Student 클래스 만들기
 		정수 하나를 저장할 인스턴스 변수 num 만들기
 		
 		정수 하나를 저장할 클래스 변수 static num 만들기
 		
 		기본 생성자 만들기 --> 아무 일도 하지 않음

		하나의 정수 값을 매개변수로 전달받아 인스턴스 변수, 클래스 변수에 각각 누적시키는
		인스턴스 메소드 add만들기
 */

class Student {
	int num;
	
	static int staticNum;
	
	public Student() {
		
	}
	
	public void add(int a) {
		this.num += a;
		Student.staticNum += a;
	}
}

public class Test1 {

	public static void main(String[] args) {
		
		/*
		 	출력 결과
		 	-- 참조 변수 a_student 객체 --
		 	인스턴스 변수 num = 5
		 	클래스 변수 staticNum = 5
		 	
		 	-- 참조 변수 b_student 객체 --
		 	인스턴스 변수 num = 5
		 	클래스 변수 staticNum = 10
		 	
		 */
		Student a_student = new Student();
		a_student.add(5);
		System.out.println("-- 참조 변수 a_student 객체 --");
		System.out.println("인스턴스 변수 num = " + a_student.num);
//		System.out.println("클래스 변수 staticNum = " + a_student.staticNum);
		System.out.println("클래스 변수 staticNum = " + Student.staticNum);
		
		Student b_student = new Student();
		b_student.add(5);
		System.out.println("-- 참조 변수 b_student 객체 --");
		System.out.println("인스턴스 변수 num = " + b_student.num);
//		System.out.println("클래스 변수 staticNum = " + b_student.staticNum);
		System.out.println("클래스 변수 staticNum = " + Student.staticNum);
		
		
		// 인스턴스 변수와 클래스 변수의 차이를 적어보기
		/*
		 	인스턴스 변수 : 새로운 객체를 생성할때마다 객체 메모리 내부에 개별적으로 할당되는
		 					변수 메모리이며, static이 붙지 않는다.
		 					JVM의 heap 영역의 객체 메모리에 할당된다.
		 	 
		 	 클래스 변수 : 클래스 파일이 JVM 메모리의 Method 영역에 올라가면서 class 파일 내부에
		 	 				개별적으로 할당되는 변수 메모리
		 	 				클래스 파일 하나당 한 번만 개별적으로 할당되는 변수 메모리.
		 	 				객체 메모리를 생성하지 않고도 클래스명. 으로 접근 가능한 메모리.
		 	 				생성된 여러 객체의 메모리들이 공통으로 사용할 데이터를 저장하는데 사용되는
		 	 				변수메모리
		 */

	}

}
