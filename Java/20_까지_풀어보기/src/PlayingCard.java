
/*
 문제1. 다음 코드에 정의된 변수들을 종류별로 구분해서 적으시오
 
 클래스변수 : static int width, static int height
 인스턴스변수 : int kind, int num 
 지역변수  : int k, int n, PlayingCard card
*/

public class PlayingCard {
	int kind;
	int num;
	
	static int width;
	static int height;
	
	public PlayingCard(int k, int n) {
		kind = k;
		num = n;
	}
	public static void main(String[] args) {
		PlayingCard card = new PlayingCard(1, 1);

	}

}
