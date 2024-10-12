
// 주제 : 부모 List인터페이스를 구현한 자식 ArrayList 배열 클래스
//        그리고 Iterator 인터페이스를 구현한 자식 Iterator 배열 객체의
//        메소드( next() 메소드 ) 를 이용해 쉽게 ArrayList 배열에 저장된
//        객체를 얻는 예

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Collections03 {

	public static void main(String[] args) {

		// 업캐스팅을 이용해서 ArrayList 배열 메모리 생성
			// 부모 인터페이스 List자료형의 참조변수 = 자식 ArrayList 배열 생성;
			List list = new ArrayList();
		
			// ArrayList 배열에 "하나", 2, 3.42, "넷", "다섯", 6 추가
			list.add("하나");
			list.add(2);
			list.add(3.42);
			list.add("넷");
			list.add("다섯");
			list.add(6);

			// ["하나"][   2   ][  3.42  ][  "넷"  ][  "five"  ][   6   ]
			//     0	   1     	2          3         4          5      index
		/*
		 	ArrayList 배열에 저장된 모든 객체들을 꺼내어서
		 	iterator부모인터페이스를 구현한 자식 ArrayList$Itr라는 이름의 배열 메모리에
		 	저장한 후에 자식 ArrayList%Itr배열 메모리 자체를 반환해 준다.
		 	
		 	ArrayList%Itr 배열 모습
			// ["하나"][   2   ][  3.42  ][  "넷"  ][  "five"  ][   6   ]
			//     0	   1     	2          3         4          5      index
		 	
		 */
			// ArrayList 배열메모리.iterator();
			Iterator iterator = list.iterator();
			
			// Iterator 부모 인터페이스를 구현한 자식 ArrayList$Itr (이터레이터 배열)메모리
			// boolean hasNext() 메소드와, E next() 메소드를 제공하여
			// 이터레이터 배열에 객체가 저장되어 있는지 물어볼 수도 있고, 
			// 이터레이터 배열에 저장된 객체를 차례대로 꺼내올 수 있다.
			while(iterator.hasNext()) {
				
				System.out.println(iterator.next());
				
			}
			
			// Iterator 인터페이스에서 제공되는 메소드 2개
			// boolean hasNext() : 
			//   -> ArrayList$Itr 배열에 객체가 저장되어 있냐? 물어보는 메소드
			//      저장되어 있으면 true 반환, 저장되어 있지 않으면 false반환 하는 메소드
			// E next()
			//   -> ArrayList$Itr 배열에 저장된 객체들을 순서대로 꺼내와 반환해주는 메소
	
	}

}
