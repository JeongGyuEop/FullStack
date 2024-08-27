

// 예제. 2차원 배열에 초깃값을 저장하면서 배열 생성 후
//       행 단위와 열 단위의 데이터들의 합을 구하는 프로그램

public class Arr04 {

	public static void main(String[] args) {
		
		// 초깃값을 저장하는 동시에 배열을 생성하는 문법
		/*
		 	
		 	자료형[][] 배열명 = {
		 	
		 		{값1, 값4}, // 0행
		 		{값2, 값5}, // 1행
		 		{값3, 값6}  // 2행

		 	};
		 	
		 */
		// 학생들의 과목별 점수를 초깃값으로 갖는 2차원 배열을 생성
		int[][] score = {
				
			// 국어, 영어, 수학
			{85, 60, 70}, // 0행 1번학생
			{90, 95, 80}, // 1행 2번학생
			{75, 80, 100},// 2행 3번학생
			{80, 70, 95}, // 3행 4번학생
			{100, 65, 80} // 4행 5번학생
			
		};
		
		// 각 과목별 총점을 저장할 1차원 배열 생성
		int[] subject = new int[3];
		
		for(int r=0; r<score.length; r++) {
			for(int c=0; c<score[r].length; c++) {
				subject[c] += score[r][c];
			}
		}
		
		System.out.print("국어 / 영어 / 수학 = ");
		for(int i=0; i<subject.length; i++) {
			System.out.print(subject[i] + " ");
		}
		
		// 각 학생별 총점을 저장할 1차원 배열 생성
		int[] student = new int[5];
		
		for(int r=0; r<score.length; r++) {
			for(int c=0; c<score[r].length; c++) {
				student[r] += score[r][c];
			}
		}
		
		System.out.println("\n------------------------------\n");
		
		for(int i=0; i<student.length; i++) {
			System.out.println("학생 " + i + "의 점수 : " + student[i]);
		}
		
		
		
	}

}











