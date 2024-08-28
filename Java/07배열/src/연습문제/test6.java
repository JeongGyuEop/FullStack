package 연습문제;
/*
문제.  다음과 같은 배열이 있을 때, 
      arr[3].length의 값은 얼마인가?   2

	
	int[][] arr = {
					{5, 5, 5, 5, 5},
					{10,10,10},
					{20,20,20,20},
					{30,30}
				  };
				  
	풀이:
						      0x100 배열 주소값
	arr[0x100]---> { 0x200,  0x300, 0x400,   0x500 };			  
				  	arr[0], arr[1],  arr[2],  arr[3] index
				  	
		  	      0x200
	{        5,       5,        5,         5,         5}
	 arr[0][0] arr[0][1] arr[0][2]  arr[0][3] arr[0][4]
	 
			  	  0x300
	{        10,     10,        10}
	 arr[1][0] arr[1][1] arr[1][2]   
				  	
		  	      0x400
	{      20,      20,        20,        20}
	 arr[2][0] arr[2][1] arr[2][2]  arr[2][3]
	 
			  	  0x500
	{      20,      20  }
	 arr[3][0] arr[3][1]  				  	
	 				  	
	
	설명 :  arr[3].length는 arr[3]이 가리키는 배열의 크기를 의미함
	       위 그림에서 arr[3]이 가리키는 배열 주소는 0x500번지에 있는 배열 크기는 2입니다
	       arr[3].length의 값은 2가 된다.
	       참고로 arr.length의 값은 4이고,
	            arr[0].length의 값은 5,
	            arr[1].length의 값은 3,
	            arr[2].length의 값은 4이다.  	  
				  	
*/

public class test6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
