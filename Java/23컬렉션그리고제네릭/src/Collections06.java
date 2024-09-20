

/*
 주제 : Vector객체 생성시 <>오브라는 기호와 함께 
       String제네릭타입을 지정하여 
       객체를 생성함으로써  Vector에 저장되는 객체를 String객체만 저장되기 떄문에
       Vector의 get메소드를 호출하면 반환타입은 String으로 변경되서
       업캐스팅과 다운캐스팅을 하지 않아도 되는 예
*/

import java.util.List;
import java.util.Vector;

public class Collections06 {
	public static void main(String[] args) {
		
		//업캐스팅을 하여 Vector배열 생성
		//- Vector객체를 생성할때 <>오브라는 기호와 String제네릭자료형을 함께 기술하면
		//  Vector클래스 내부에 E라고 적혀 있던 아직 결정되지 않은 변수 그리고 메소드의 매개변수 타입(자료형)이
		//  String제네릭자료형으로 변경(결정)되어 모두 ~~~~~~~~~~~~~ 바뀐다.
		
		List<String>  vector = new Vector<String>();
		
		//Vector배열에 문자열 객체 추가
		vector.add("Apple");
		vector.add("banana");
		vector.add("oRANGE");
		
		//확장for문을 사용하여 아래처럼 반복해서 출력
		for( String str :  vector ) {
			System.out.println(str.toUpperCase());
		}
		
		System.out.println();
		
		//다운캐스팅을 하여 저장할 String변수 선언
		String temp;
		
		for(int i=0;  i<vector.size();   i++) {
			
			temp = vector.get(i); 
			
			
			
			//위 다운캐스팅을 함으로써 String의 toUpperCase메소드를 호출하여
			//모든 소문자들을 대문자로 변경하여 반환받을수 있습니다.
			//"Apple" -> "APPLE"
			//"banana" -> "BANANA"
			//"oRANGE" -> "ORANGE"
			System.out.println(    temp.toUpperCase() ) ;
			
		}
		
		
	}

}
