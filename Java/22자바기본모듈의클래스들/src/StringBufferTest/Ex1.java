package StringBufferTest;

public class Ex1 {

	public static void main(String[] args) {

//어떤 경우 String클래스로 객체 메모리를 생성하여 문자열을 저장해 놓고 사용하는가?
		
//-> 한번 생성한 String객체 메모리에 변경하지 않을 문자열을 저장하고 싶을때
//	 String클래스의 객체 메모리 생성함
		
		String str = "Play";
	/*	
		그림
			  [ str ] -> [ "Play" ];	
	*/	
			   str = "Life";
		
	/*	
		그림
			             [ "Play" ]; <-컴파일러에 의해 사용하지 않는 객체 메모리 제거됨	
		  	  [ str ] -> [ "Life" ];	
	 */	
	
		//결론:  String클래스를 이용해 객체 메모리를 여러번 생성하면
	    //      참조변수 str은 마지막에 생성한 객체 메모리를 사용하게 된다
	    //      처음 생성한 객체 메모리는 컴파일러에 의해 제거됨
		
	          //"Life"문자열에 추가하고 싶은 문자열이 있을 경우  + 연산시 
			  str = str + "and Play";
//			  	   "Lifeand Play";
		/*
		   그림
			        	   ["play"] <- 컴파일러에 의해 사용하지 않는 메모리 제거됨
			        	   ["Life"] <- 컴파일러에 의해 사용하지 않는 메모리 제거됨		
		  	[str]->		   ["Lifeand Play"]
			  
		*/	  
//결론:  같은 String클래스의 참조변수 str에  문자열객체메모리를 생성해서 주소를 넣을떄마다
//		새로운 String클래스에 대한 객체 메모리의 주소가 할당되기 떄문에
//		프로그램 개발을 하면 프로그램 속도가 나중에 배울 StringBuffer보다 느리다는 단점이 있다.
//변경되지 않을 문자열만? 저장하려면? String클래스를 이용해 객체메모리를 생성해서 사용하자
		 System.out.println("-------------------------------------");
			  
			  
		 //StringBuffer클래스?
		 //- 변경을 많이 하게될 문자열을 저장할 용도의 객체를 생성할때 사용되는 클래스
		 //	 (가변으로 늘어나서 커지게 하는 문자열을 저장)
		 
		 //"JAVA" 문자열을 저장시킬 StringBuffer클래스의 객체 생성
		 StringBuffer sb = new StringBuffer("JAVA");
		 /*
		   그림
		 			 [sb]-> ["JAVA"]
		 */
			//현재 저장된 전체 "JAVA"문자열 끝에 "Programming"문자열 추가해서 저장
		 			 sb.append("Programming");
		 /*			 
		    그림
		    		 [sb]-> ["JAVAProgramming"]
		 */
//결론: 생성된 StringBuffer객체 메모리 내부의 문자열을 수정된다.
//	   새로운 StringBuffer객체 메모리를 만들지 않고 
//     append메소드를 이용해서 수정된 문자열을 다시 저장한다
//     프로그램 속도가 String객체 메모리를 생성해서 
//     문자열을 저장시켜 사용하는 것보다는 빠르다.		 			 
	
	
	
	
	}

}







