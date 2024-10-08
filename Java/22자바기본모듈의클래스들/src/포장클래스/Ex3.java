package 포장클래스;

public class Ex3 {
	public static void main(String[] args) {
/*
박싱?
-> 기본자료형의 데이터를 포장(래퍼)클래스의 객체 메모리에 저장시키는것.

방싱 방법 2가지

-> 1. 해당 기본자료형에 대응되는 포장(래퍼)클래스의 생성자를 호출해서 박싱하는 방법
   예) new Integer(10);
   
-> 2. 해당 기본자료형에 대응되는 포장(래퍼)클래스에 만들어 놓은 메소드 호출해 박싱하는 방법   
   예) Integer.valueOf(10);
*/
     int n = 10;//기본자료형의 값
     
     //수동박싱
     //해석:  10을 Integer객체메모리에 저장하기 위해
     //		 Integer클래스의 valueOf메소드 호출시 매개변수로 10을 전달하면
     //		 10이 저장된 새로운 Integer객체메모리의주소를 반환 해줍니다.	
     Integer num = Integer.valueOf(n);
/*		
	언박싱?
	- 래퍼 객체 메모리에 저장된 기본자료형의 값을 꺼내어 얻는작업
	
	언박싱 방법
	- 해당 래퍼 객체에서 제공해주는 메소드를 호출해서 기본자료형의 값을 얻는다.
	  예) Integer.intValue()메소드를 호출하면 반환값이 기본자료형 값입니다.	
*/		
    //수동 언박싱
    int value = num.intValue(); //10
    
    //참고.
    //박싱(수동박싱),  언박싱(수동언박싱)
    
     
     
	}

}








