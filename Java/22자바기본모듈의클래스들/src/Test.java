

/*
 	Object 클래스
 		- 자바의 모든 클래스들 중에서도 최고 상위 클래스 
 		
 	Object 클래스 메소드 종류
 		1. boolean equals(Object obj) 메소드 
 			: 두 객체의 주소번지가 같으냐?라고 컴퓨터에게 물어보는 메소드
 			  만약 같으면 true값, 틀리면 false를 반환하는 메소드 
 			  
 			 -> 만약 다른 자식클래스에서 상속받아 사용하는 경우 객체의 주소가 같은지만 비교한다.
 			   그리고 다른 자식클래스에서 메소드오버라이딩해서 사용하면 오버라이딩한 그 기능에
 			   맞게 사용할 수 있다.
 			   
 			 -> 대표적인 제공되는 자식클래스는 String 클래스이다.
 			 		String 클래스에는 Object 클래스의 equals메소드의 기능은 두 문자열 객체 내부에
 			 		저장되어 있는 두 문자열이 같은지  비교해서 같으면 true를 틀리면 false를 반환.
 			 		만약 new String("문자열"); 객체의 주소번지를 비교하려면 == 비교연산자를 사용해야한다.
 			 		
 		2. int hashCode 메소드
 			: 생성된 객체메모리를 식별할 주소번지(16진수)값을 정수로 변환해서 반환 해주는 메소드
 			  거의 사용하지는 않지만 다른 자식 클래스에서 메소드오버라이딩해서 사용하는 경우가 있다.
 		
 		3. String toString() 메소드
 			-> 간단한 클래스 이름과 객체를 구분하기 위한 해시코드 값을 연결해서
 				하나의 문자열 형태로 반환
 			
 				String toString() {
 				
 					return this.getClass().getName() + '@' + Integer.toHexString(hashCode());
 					//	   "생성된 객체의 클래스명"  + '@' +  16진수 해시코드;
 				
 				}
 				
 			예) Object obj = new Object();
 					   obj.toString(); 호출하면 반환받는 문자열은 
 					   					"java.lang.Object@de6ced"
 			-> 예를 들어 Date클래스는 현재 날짜와 시간 정보를 제공해주고,
 						String 클래스는 저장된 문자열을 리턴하도록 해주고, 
 						Object 클래스의 toString() 메소드를 오버라이딩되어 만들어져 있다.
 						
 		-----------------------------------------------------------------------------------------------
 		
 		참고. 롬복 라이브러리(lombok.jar) 사용
 			
 			롬복? DataTransformObject(DTO) 클래스를 작성할 때 getter, setter, toString, equals 메소드 등을
 			      자동으로 만들어주는 어노테이션 기호들을 포함한 라이브러리.
 			      
 			롬복 다운로드 사이트 주소 : https://projectlombok.org/download
 						
 						
 						
 						
 						
 						
 						
 						
 		
 */
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
