/*
	Enumeration 인터페이스
	
	 - Enumeration 인터페이스를 사용하면 
	   Collection인터페이스로 구현한 자식 클래스의 
	   가변길이 배열안에 저장된 객체들을 쉽게 꺼낼수 있는
	   메소드들을 제공합니다.
	   
	   
	   boolean  hasMoreElements()
	   : Collection인터페이스로 구현한 자식클래스의 배열안에 
	     꺼낼수있는 객체가 있으면?
	     true반환, 없으면 false를 반환 
	     
	   Object  nextElement()
	   :  Collection인터페이스를 구현한 자식클래스의 배열안에저장된 
	      객체들을 순서대로 하나씩 꺼내올떄 사용  
*/

import java.util.Enumeration;
import java.util.Vector;

public class EnumerationTest01 {
	public static void main(String[] args) {
		
		//기본생성자로 생성하면 10칸의 Vector배열메모리가 만들어집니다
		//Vector배열메모리 10칸에 객체가 모두 저장된 상태에서
		//add메소드를 호출해서 하나의 객체를 추가하면
		//1칸씩 가변으로 늘어나 저장되게 됩니다.
		Vector vector = new Vector();
		
		//Integer객체 5개를 반복해서 만들어서
		//Vector배열에 차례로 저장
		for(int i=0;  i<5; i++) {
			//vector.add( new Integer( i*10 ) );
			  vector.add(i * 10);
		}
		//0   10  20  30  40 
		
//Vector배열의 elements메소드를 호출하면
//Vector배열에 저장된 모든 Integer객체들을 꺼내어서
//Enumeration부모인터페이스를 구현한 자식 Enumeration배열메모리에
//모두 저장한 후 자식 Enumeration배열메모리 자체를 반환
		Enumeration enu = vector.elements();
		
		while(enu.hasMoreElements()) {
			System.out.println(  enu.nextElement() );
//										0
//										10
//										20
//										30
//										40
		}
/*
참고. 자바에서 Iterator인터페이스와 Enumeration인터페이스는 
      모두 컬렉션라이브러리에서 제공하는 가변길이 배열에 
      저장된  객체들을 반복해서 쉽게 꺼올때 사용되는 
      추상메소드들을 제공하는 인터페이스 들입니다
      
      차이점을 살펴보면?
      
      1. 만들어진 시기
      	- Enumeration인터페이스 JDK 1.0버전 부터 도입되었습니다.
      	- Iterator인터페이스는 JDK 1.2버전에서 추가 도입되었습니다.

	  2. 컬렉션 배열의 타입
	    - Enumeration인터페이스는 주로 
	      Vector나 Hashtable과 같은 구식 컬렉션 클래스에서 사용됩니다.
	
	    - Iterator인터페이스는 모든 컬렉션 클래스에서 사용될수 있습니다.
	    
	  3. 컬렉션 배열에 저장된 객체 제거
	  	- Enumeration인터페이스는 객체를 제거하는 기능의 메소드를 제공하지 않습니다.
	  	- Iterator인터페이스는 remove()메소드를 통해 현재 반복해서 꺼내오는 객체를 꺼내와 제거하는 기능을 제공합니다.
	  	
	  4.  메소드 추가지원
	  	- Iterator인터페이스는 추가로 
	  	  forEachRemaining()메소드를 지원 합니다.
	  	  이메소드는 나머지 모든 객체를 소비하면서 
	  	  특정 동작을 수행할수 있습니다.
	  	- Enumeration인터페이스는 해당 기능을 제공하지 않습니다.

*/
		
		
		
	}

}











