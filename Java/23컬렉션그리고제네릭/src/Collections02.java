
/*
 	
 	
 	
 	
 	
 	
 	
 */


// 주제 : 부모 List 인터페이스를 구현한 자식 ArrayList 가변길이 배열클래스

import java.util.ArrayList;
import java.util.List;

public class Collections02 {

	public static void main(String[] args) {
		
		// 업캐스팅
		List list = new ArrayList();
//		ArrayList list = new ArrayList();
		
		list.add("하나");
		list.add(2);
		list.add(3.42);
		list.add("넷");
		list.add("five");
		list.add(6);
		
		System.out.println(list.toString()); // [하나, 2, 3.42, 넷, five, 6]
		
		// 결론 : 출력결과 HashSet 배열과 달리 ArrayList 배열에는 add 메소드를
		//        호출하여 추가한 순서대로 객체 (요소) 들이 저장된 것을 확인할 수 있다.
		
		
		
		
		
		
		

	}

}
