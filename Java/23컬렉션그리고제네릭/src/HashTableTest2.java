

//주제 : 
//Map부모인터페이스를 구현한 자식 HashTable클래스로 배열 만들어 사용해 보기

import java.util.Hashtable;
import java.util.Enumeration;

public class HashTableTest2 {
	public static void main(String[] args) {
		
		//해쉬테이블 배열메모리에 데이터를 저장할때
		//(key-value)한쌍 형태로 묶어서 저장됩니다.
		//즉! 해쉬테이블 공간은 표 공간이라고 보면 좋겠죠?
		Hashtable<String, String> ht = new Hashtable<String, String>();
		
		//해쉬테이블 배열메모리에 데이터(key-value)를 저장할때
		//put메소드 사용
		//key  또한 객체로 넣고
		//value 또한 객체로 넣습니다.
		//     key ,   value
		ht.put("사과", "Apple");
		ht.put("딸기", "Strawberry");
		ht.put("포도", "Grapes");
		
		//업캐스팅이 일어나는 동시에 다운캐스팅 해서 저장 
		String value = ht.get("포도");
		
		
		//key("포도")를 통해 value("Grapes")를 꺼내올수 있다면?
		if(value != null) {
			System.out.println("포도 key와 같이 연결되어 저장된 value->" + value);
			
		}
		
/*
  HashTable배열에 저장된 모든 Key(객체)들을 일일이 기억하지 못하므로
  모든 key(객체)들만 뽑아내서 Enumeration배열에 담아 
  Enumeration배열 주소 자체를 반환
  -> keys()메소드 
*/
	  Enumeration enumeration = ht.keys();
		
	  while(enumeration.hasMoreElements()) {
	  //1. Enumeration배열에 저장되어 있는 key들을 차례로 얻어 저장
		   String key = (String)enumeration.nextElement(); 
		                //"사과", "딸기", "포도"  <-- key들 
	  //2. key들을 이용해 HashTable에 저장된 value들을 차례대로 얻어 저장
		   String val = (String)ht.get(key);
		         					//"사과" -> "Apple"
		   							//"딸기" -> "Strawberry"
		   							//"포도" -> "Grapes"
	  //3. HashTable배열에 저장되어 있었던 
	  //   key에 대응되는 value값을 같이 반복해서 출력
		   System.out.println(key + " - " + val);	   
	  }
		

	}

}





