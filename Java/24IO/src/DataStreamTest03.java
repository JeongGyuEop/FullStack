
/*
객체 직렬화란?
- 객체를 출력하려면 변수값들을 일렬로 늘어선 바이트 데이터들로 변경해야 하는데
  객체의 변수에 저장된 값들을 바이트 데이터들로 변경하는 것을 객체 직렬화라 한다.
  
객체 직렬화를 하는 이유?
- JVM메모리에 생성된 객체의 데이터들을 파일또는 네트워크를 통해 화면에 출력해야 하기 떄문

객체 역직렬화란?
- 직렬화된 바이트데이터들을 다시 객체의 변수값으로 복원하는 것을 역직렬화라 한다.

ObjectInputStream과 ObjectOutputStream 클래스는 
객체를 입출력할수 있는 보조 스트림이다.

ObjecOutputStream클래스는  바이트 출력스트림과 연결되어 객체를 직렬화 하고,

ObjectInputStream클래스는 바이트 입력스트림과 연결괴어 객체로 복원하는 역질렬화를 한다.

그림
	바이트 -> InputStream 연결된 통로 ObjectInputStream를 통해 역직렬화 ->   프로그램
																	  객체 

	프로그램  -> ObjectOutputStream를 통해 직렬화 연결된 OutputStream -> 바이트데이터로 변환
	  객체 

- 다음은 ObjectInputStream과 ObjectOutputStream 보조스트림을 생성하는 코드 이다

	ObjectInputStream ois = new ObjectInputStream(바이트 입력스트림);  System.in
	ObjectOutStream oos = new ObjectOutputStream(바이트 출력 스트림);  System.out
	
- ObjectOutputStream으로 객체를 직렬화하기 위해서는 writeObject()메소드를 사용한다

				    oos.writeObject(객체주소);
	
- 반대로 역직렬화를 위해서 ObjectInputStream의 readObject()메소드를 호출해서 사용한다
			
			객체클래스타입    참조변수  = (객체클래스타입)ois.readObject();


주제 : 다양한 객체를 파일에 저장하고  다시 파일로부터 읽어  객체로 복원하는 예제.
	   복수의 객체를 저장할 경우 출력된 객체 순서와 동일한 순서로 객체를 읽어야한다.
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

/*
 	Serializable(시리어즈블)인터페이스
 	
 		-	이 인터페이스를 구현한 클래스만 직렬화할수 있도록 정해 놨습니다.
 		-   Serializable인터페이스는 멤버가 없는 빈 인터페이스이지만, 
 			객체를 직렬화할수 있다고 표시하는 역할을 함
 		
 			interface Serializable {}
 	
 		-   객체가 직렬화될 떄 객체안의 인스턴스변수값은 직렬화 대상이지만 
 		    static 변수값과 transient로 선언된 변수값은 직렬화 대상에서 제외되므로 출력되지 않는다.
 		    
 		    예)
 		    		class  A  implements  Serializable{
 		    				
 		    			public int a;
 		    			protected int b;    직렬화 ->  일렬로 늘어선 바이트 데이터 
 		    			int c;						 [a, b, c, d]
 		    			private int d;
 		    			
 		    			public static int e; //정적변수는 직렬화에서 제외
 		    			transient int f;	//transient로 선언된 변수는 직렬화에서 제외
 		    				
 		    		}
 
 
 		serialVersionUID 상수
 		
 			- 직렬화할 떄 사용된 클래스와 역직렬화할때 사용된 클래스는 기본적으로 동일한 클래스여야 한다.
 			  만약 클래스의 이름이 같더라도 클래스의 내용이 다르면 역직렬화에 실패 한다. 
 			  
 			  그림
 			  		class Member implements Serializable{
 			  			 
 			  			 int field1; 
 			  			 int field2;
 			  		
 			  		}
 			  		
 			  		class Member implements Serializable{
 			  			 
 			  			 int field1; 
 			  			 int field2;
 			  			 int field3;
 			  		}
 			  		
 			  	설명
 			  		위 첫번쨰 Member클래스로 생성한 객체를 직렬화 하면 
 			  		아래 쪽 두번째 Member클래스로 역직렬화 할수 없다.
 			  		그이유는 두번쨰 Member클래스에는 field3변수가 만들어져 있기떄문이다.
 			  		
 		  -클래스 내용이 다르다 할지라도 직렬화된 변수를 공통으로 포함하고 있다면 
 		   역직렬화 할수 있는 방법이 있다.
 		   동일한 serialVersionUID상수값을 가지고 있으면 된다.  		
 			  		
 		   그림  		
  			  		class Member implements Serializable{
 			  			 private static final long serialVersionUID = 1L;
 			  			 int field1; 
 			  			 int field2;
 			  		
 			  		}
 			  		
 			  		class Member implements Serializable{
 			  			  private static final long serialVersionUID = 1L;
 			  			 int field1; 
 			  			 int field2;
 			  			 int field3;
 			  		}			  		
 			설명
 				serialVersionUID의 값은 개발자가 임의로 줄 수 있지만 
 				가능하다면 클래스마다 다른 유일한 값을 갖도록 하는 것이 좋다.
 				이클립스는 serialVersionUID상수를 자동 생성하는 기능을 제공한다.
 				
 				먼저 클래스에 implements Serializable인터페이스를 구현하게 작성해 놓은다음
 				마우스를 클래스 이름위에 갖다 대면 Add generated serial version ID링크가 나온다
 				이것을 클릭하면 자동으로 serialVersionUID상수가 소스에 추가 된다. 
 
 */


//회원 설계도
class Member implements Serializable {
	
	private static final long serialVersionUID = 8230887928381542643L;
	
	private String id, name;
	
	public Member(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@Override
	public String toString() {
		
		return id + ":" + name;
	}
}


//상품 설계도
class Product implements Serializable {
	
	private static final long serialVersionUID = 909380440064313658L;
	
	private String name;
	private int price;
	
	public Product(String name, int price) {
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		
		return name + " : " + price ;
	}

}



public class DataStreamTest03 {

	public static void main(String[] args) throws Exception {
		
		//FileOutputStream에 ObjectOutputStream보조 출력스트림 연결
		FileOutputStream  fos = new FileOutputStream("C:/Temp/object.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		//객체 생성
		Member m1 = new Member("fal1", "단풍이");
		Product p1 = new Product("노트북", 1500000);
		int[] arr1 = {1, 2, 3};
		
		//객체를 직렬화해서 파일에 저장
		oos.writeObject(m1);
		oos.writeObject(p1);
		oos.writeObject(arr1);
		
		//자원해제
		oos.flush();  oos.close(); fos.close();
		
	//------------------------------------------------------------------
		
		
		//FileInputStream에 ObjectInputStream 보조 입력 스트림 연결 
		ObjectInputStream ois  = 
		new ObjectInputStream( new FileInputStream("C:/Temp/object.dat") );  
		
		//파일을 열고 역직렬화 해서 객체로 복원
		Member m2 = (Member)ois.readObject();
		Product p2 = (Product)ois.readObject();
		int[] arr2 = (int[])ois.readObject();
		
		//자원해제
		ois.close();
		
		//복원된 객체 내용 확인을 위해 출력
		System.out.println(m2.toString());
		System.out.println(p2.toString());
		System.out.println( Arrays.toString(arr2));
		
		
		
		
	}

}





















