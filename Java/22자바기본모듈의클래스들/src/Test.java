/*

     Object 클래스
     - 자바의 모든 클래스들 중에서도 최고 상위 클래스
     
     Object클래스 메소드 종류
     
     1. boolean  equals(Object obj)메소드
     
        -> 두객체의 주소번지가 같으냐?라고 컴퓨터에게 물어보는메소드
           같으면 true반환하고 틀리면 false를 반환하는 메소드
           
        ->만약 자식클래스에서 상속받아 사용하는 경우 객체의 주소가 같은지만 비교합니다      
          그리고 다른 자식클래스에서 메소드오버라딩해서 사용하면
          오버라이딩한 그 기능에 맞게 사용할수 있습니다.
        
        ->대표적인 제공되는 자식클래스는 String클래스입니다.
        
          		String클래스에는 Object클래스의 equals메소드가 
          		메소드 오버라이딩되어 만들어져 있습니다
          		
          		String클래스의 오버라이딩된 equals메소드의 기능은
          		두 문자열 객체 내부에 저장된 두 문자열이 같은지 비교해서
          		같으면 true반환하고 틀리면 false를 반환합니다.
          		만약 new String("문자열"); 객체의 주소번지를 비교하려면
          		== 비교연산자를 사용해야 한다. 
        	
      2. int  hashCode()   메소드
          -> 생성된 객체메모리를 식별할 주소번지(16진수)값을 정수로 변환해서
             반환 해주는 메소드.
             거의 안쓰기는 하지만 다른 자식클래스에서 메소드오버라이딩해서
             사용하는 경우가 있습니다.
          
      3. String toString() 메소드
       -> 간단한 클래스이름과 객체를 구분하기 위한 해시코드값을 연결해서
          하나의 문자열 형태로 반환해줍니다.
          
          String toString(){
          
          	  return this.getClass().getName() + '@'  + Integer.toHexString(hashCode());
          	  
          	 // 	"생성된 객체의 클래스명       " + '@'  +  16진수 해시코드;
          	  		     
          }

        예)
              Object obj = new  Object();
                     obj.toString(); 호출하면 반환받는 문자열은
                     				 "java.lang.Object@de6ced"
                
	    -> 예를 들어 Date클래스는 현재 날짜와 시간정보를 제공해주고,
	               String클래스는 저장된 문자열을 리턴하도록 해주고,
	               Object클래스의 toString()메소드를 오버라이딩되어 만들어져 있다 

 --------------------------------------------------------------------
 
   참고. 롬복 라이브러리(lombok.jar) 사용
     
        롬복? DTO클래스를 작성할때 getter, setter, toString, equals메소드등을
        	 자동으로 만들어주는 어노테이션기호들을 포함한 라이브러리.
        	 
        롬복 다운로드 사이트 주소 : https://projectlombok.org/download
 ------------------------------------------------------------
 
     String 클래스
     
      - 문자열을 String클래스의 객체를 생성해서 객체 메모리 내부에 저장하고
        문자열을 조작할때 사용하는 클래스
      - 문자열 연산이 적고 멀티쓰레드 환경일 경우 
      
      예제1. StringTest.Ex1.java 파일 
          
      예제2. StringTest.Ex2.java 파일 
         
      응용 예제3. StringTest.Ex3.java 파일 
      
      응용 예제4. StringTest.Ex4.java 파일
     
     
     
    StringBuffer클래스  
 	 - 문자열 연산이 많고 멀티스레드 환경일 경우 객체 메모리 내부에 문자열을 저장하고
 	   문자열 조작할때 사용되는 클래스 
 	   
 	  예제5. StringBufferTest.Ex1.java 파일 
 	   
 	   
	StringBuilder 클래스
	
	 -  문자열 데이터를 저장하는 객체메모리에 문자열을 저장해두고 그안에서
	    문자열을 수정, 삭제, 다른문자열을 추가해서 다시 저장하는 조작메소드들을 제공하는 클래스
	    
	    메소드 종류
	    
	    리턴타입				메소드				설명
	    StringBuilder		append(문자열)        기존에 저장된 문자열끝에 추가 
	    StringBuilder		insert(위치, 문자열)    문자열을 지정 위치에 추가 
	    StringBuilder		delete(시작위치, 끝위치) 기존에 저장된 문자열의 일부 삭제
	    StringBuilder		replace(시작위치, 끝위치, 치환문자열)   문자열 일부를 치환해서 반환
	    String				toString()			  StringBuilder객체 메모리내부에 저장된
	    										  문자열을 반환  
    
	  예제6. StringBuilderTest.StringBuilderExample.java 파일 



*/
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}




