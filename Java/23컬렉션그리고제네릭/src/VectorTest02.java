
//주제 : Vector배열 메모리에 저장된 객체 얻기(검색)와 객체삭제
import java.util.Enumeration;
import java.util.Vector;

public class VectorTest02 {
	public static void main(String[] args) {
		//기본생성자를 호출하여 생성된 백터배열은?
		//초기 용량(칸의갯수) : 10칸의 공간을 가집니다.
		//증가량 : 용량이 가득차면 기존용량(10)의 1.5배로 증가합니다
		//       예를 들어 10칸이 꽉 차면 용량이 15로,
		//		 다시 꽉 차면 22칸으로 증가합니다.
		Vector vector = new Vector();
		
		//백터 용량(칸의갯수) 출력
		System.out.println(vector.capacity());//10
		
		//기본자료형 double실수값들을 저장시킨 고정크기 배열 6칸생성
		double[] arr = new double[] {38.6, 9.2, 45.3, 6.1, 4.7, 1.6};
								  //  0     1     2     3   4    5
		//arr배열에 저장된 모든 실수 데이터들을 차례대로 반복해서 얻어
		//위 new로 선언한 Vector가변길이배열 메모리에 차례대로 오토박싱하여 저장
		for(int i=0;  i<arr.length;   i++) {	
				 //오토박싱되어 new Double(꺼내온실수);객체들이 저장됨
			     vector.add( arr[i] );
		}
		
		System.out.println("Vector배열에 저장된 모든 객체들을 차례로 얻어출력");
		
		for(int i=0;  i<vector.size();   i++) {
			
			System.out.print("\t" + vector.get(i) );
			//38.6   9.2    45.3	6.1		4.7		1.6
			
		}
	 
		System.out.println();
		
//Vector배열 
//	38.6	9.2	  45.3	6.1	 4.7	1.6
//   0       1      2    3    4      5     6   7    8   9 index		
		//검색할 데이터를 변수에 저장
		double searchData = 6.1;
		
		//searchData변수에 저장된 6.1을 오토박싱하여 indexOf메소드의 
		//매개변수로 전달하면 Vector배열에 new Double(6.1)객체가 저장되어있으면
		//저장된 index위치 번호 3을 int로 반환합니다.  없으면 -1을 반환합니다.
		int index = vector.indexOf(searchData);
		
		//검색되었다면?(index변수에 저장된 값이 -1이 아니면? 찾은것이다)
		if(index != -1) {
			System.out.println("검색성공!:" + index); //<-3
		}else {//검색되지 않으면?
			System.out.println("검색실패!:" + index); //-1 
		}
		
		
		//참고
		/*
			int indexOf(Object o)
			-> 전달인자로 준 객체를 배열의 앞index위치에서 부터
			   검색해서 찾습니다. 만약 찾으면 찾은객체의 해당 index번호 반환
			   만약 찾지 못하면 -1을 반환  
		*/
		
//Vector배열 
//	38.6	9.2	  45.3	6.1	 4.7	1.6
//   0       1      2    3    4      5     6   7    8   9 index			
		//Vector배열에 저장된 삭제할 데이터 변수에 저장
		double delData = 45.3;
		
		//Vector배열에 new Double(45.3);객체가 저장되어 있으면?
		if( vector.contains(delData)  ) {
			
			//저장되어 있기때문에 삭제하자
			//참고.  boolean remove(Object obj) 메소드
			//   -> 매개변수 obj로 전달한 객체를 Vector배열메모리에서 삭제한 후
			//       삭제에 성공하면 true반환 삭제에 실패하면 false반환
			boolean result = vector.remove(delData);
			
			if(result) { 
				System.out.println("삭제완료");
			}else {
				System.out.println("삭제실패");
			}
			
		}//바깥쪾 if
		
		System.out.println("삭제 후 Vector배열에 저장된 객체 모습 출력");
		
		System.out.println(vector.toString());
//		[38.6, 9.2, 6.1, 4.7, 1.6]
		
		//삭제 후 백터 용량(칸의 갯수)
		System.out.println(vector.capacity()); //10
		
//Vector배열 
//	38.6	9.2	   6.1	4.7  1.6
//   0       1      2    3    4      5     6   7    8   9 index			
		
		//참고.
		//boolean contains(Object obj)
		//-> Vector배열에 매개변수로 전달한 객체가 저장되어 있느냐?라고 물어보는 메소드로
		//   저장되어 있으면 true반환, 저장되어 있지 않으면 false를 반환 합니다.
				
//Vector배열 
//	38.6	9.2	   6.1	4.7  1.6
//   0       1      2    3    4      5     6   7    8   9 index				

		//Vector의 elements메소드를 호출하여 
		//Enumeraction인터페이스에서 제공해주는 메소드들을 활용하여
		//Vector에 저장된 요소(객체)들을 반복해서 차례로 얻어 출력
		
		//Enumeration부모인터페이스를 구현한 자식 배열에 
		//Vector배열에 저장된 모든 객체들을 담아 자식 배열 자체를 반환
		Enumeration enumeration = vector.elements();
		
		while (enumeration.hasMoreElements()) {
			
			System.out.println( enumeration.nextElement()  );
			
		}
		
		

	}

}









