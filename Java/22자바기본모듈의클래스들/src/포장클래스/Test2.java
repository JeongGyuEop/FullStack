package 포장클래스;

//박싱(수동박싱)? 
//기본자료형값을 포장(래퍼)클래스의 객체 메모리에 저장하는 것

//언박싱(수동언박싱)? 
//포장(래퍼)클래스의 객체 메모리에 저장된 기본자료형값을 꺼내오는 작업

//연습
public class Test2 {
	public static void main(String[] args) {		
		//정수 10을 래퍼역할을 하는 Integer클래스의 객체 메모리에 저장
		//요약 : 정수 10을 수동박싱 
		//방법1.
	//	Integer i = new Integer(10);
		
		//방법2. Integer클래스에서 제공해주는 valueOf메소드 사용
		Integer i = Integer.valueOf(10);
		
		
		//실수 3.14를 래퍼역할을 하는 Double클래스의 객체 메모리에 저장
		//요약 : 실수 3.14를 수동박싱
		//방법1.
	//	Double d = new Double(3.14);
		
		//방법2. Double클래스에서 제공해주는 valueOf메소드 사용
		Double d = Double.valueOf(3.14);
		
		
		//정수 10을 수동언박싱해서 "10"형태로 출력
		System.out.println( String.valueOf( i.intValue() ) ); 
											 // 10 
							//"10"
		
		//실수 3.14를 수동언박싱해서 "3.14"형태로 출력
		String result2 = String.valueOf( d.doubleValue()  );
											//3.14
			   				//"3.14"
		
		System.out.println(result2);//"3.14"
		
		//위 Integer객체에 저장되어 있는 정수값 10을 얻어와(수동언박싱)
		//int i2변수를 만들어 저장
		int i2 = i.intValue();//수동 언박싱을 통해 10을 꺼내옴
		System.out.println(i2);
		
		//위 두번째 Double객체에 저장되어 있는 실수값 3.14를 얻어와(수동언박싱)
		//double d2변수를 만들어 저장
		double d2 = d.doubleValue();//수동 언박싱을 통해 3.14를 꺼내옴
		System.out.println(d2);
		
//위 Double래퍼 클래스의 객체 메모리 저장되어 있는 실수값 3.14얻어오기(수동언박싱)
//얻어온 3.14값에 1.2를 더하여 4.34로 만들어
//다시~ 수동박싱 해준다.
//		d = new Double( d.doubleValue() + 1.2 );
		d = Double.valueOf(d.doubleValue() + 1.2);
		
		System.out.println(d.toString());//"4.34"
		
		
		
	}

}










