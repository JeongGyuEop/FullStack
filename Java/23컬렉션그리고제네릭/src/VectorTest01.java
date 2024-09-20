
//주제 : Vector가변으로 늘어나는 배열 메모리를 만들때
//      사용되는 Vector클래스 이용하기
import java.util.Vector;

public class VectorTest01 {
	public static void main(String[] args) {
		//4개의 객체(요소)를 저장하수 있는 가변으로 늘어나는
		//Vector배열 메모리 생성
		//그리고 Vector배열메모리에 객체들이 모두 저장된 상태에서
		//add메소드를 한번 호출해서 객체를 추가하면
		//3칸씩 가변으로 늘어나게 하는 Vector배열메모리 생성!
		Vector vector = new Vector(4, 3);
		// [ ]   [ ]   [ ]   [ ]
		 //  0    1    2      3    
		
		//Vector는 몇칸 만들어져있는지 칸의 갯수(용량)을
		//알아내기 위해 호출하는 메소드 -> capacity()
		System.out.println("백터 칸의 갯수(용량):"+ 
		                    vector.capacity()); // 4칸
		
		//Vector 4칸에 저장된 객체의 갯수를 반환하는 메소드
		//-> size()  
		System.out.println("백터 칸에 저장된 객체의 갯수 : " +
		                   vector.size()); //0 개 
/*		
		Vector vector = new Vector(4, 3);
		 [ ]   [ ]   [ ]   [  ]
		  0     1     2      3   
*/		
		//for반복문을 이용해 Vector배열에 객체를 추가하여 저장
		for(int i=0; i<5; i++) {
			  vector.add(i * 10);//오토박싱되어 추가됨 
		}
//		 [0]   [10]  [20 ] [30 ] [ 40 ] [   ]  [    ]
//		  0     1     2      3      4     5      6    index

		//Vector는 몇칸 만들어져있는지 칸의 갯수(용량)을
		//알아내기 위해 호출하는 메소드 -> capacity()
		System.out.println("백터 칸의 갯수(용량):"+ 
		                    vector.capacity()); // 7칸
		
		//Vector 4칸에 저장된 객체의 갯수를 반환하는 메소드
		//-> size()  
		System.out.println("백터 칸에 저장된 객체의 갯수 : " +
		                   vector.size()); //5 개 		
		
//		 [0]   [10]  [20 ] [30 ] [ 40 ] [   ]  [    ]
//		  0     1     2      3      4     5      6    index		
		
System.out.println("백터 배열메모리에 저장된 모든객체를 차례로 꺼내어 출력");
		for(int i=0; i<vector.size();   i++) {
			System.out.print("  " + vector.get(i));
									//i=0일때   0
									//i=1일때  10
								    //i=2일때  20
					                //i=3일때  30
		}							//i=4일때  40
									//i=5일때 반복종료 
		System.out.println();
		
		//백터 배열메모리의 마지막으로 저장된 객체를 반환받아 출력
		System.out.println(vector.lastElement());//40
		
		//백터 배열에 첫번째 칸 (0 index)에 저장된 객체를 반환
		// 0   출력
		//실제로는 new Integer(0) 객체 임 
		System.out.println(vector.firstElement());//0
		
	/*	
		java에서 Vector가변길이배열은 동적으로 크기가 조정되는 배열입니다
		즉 배열 처럼 사용되지만, ArrayList와 달리 동기화되어 있어
		멀티 스레딩 환경에서 안전합니다.
		그러나 성능 문제로 요즘은 주로 ArrayList를 사용하는 경우가 많습니다
		
		
		
	*/	
	}

}







