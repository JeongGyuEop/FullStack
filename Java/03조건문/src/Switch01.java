

// 예제 : switch 문을 사용하여 학생 점수를 학점으로 출력하는 예제
//        입력받은 학생 점수에 따라 A, B, C, D, F 중 하나를 출력

import java.util.Scanner;

public class Switch01 {

	public static void main(String[] args) {
		
		// 키보드와 연결된 입력 통로 : Scanner scanner = new Scanner(System.in);
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("학생 점수를 입력하세요 : ");
		
		// 키보드로 부터 입력받은 학생 점수를 정수로 변환해서 변수에 저장
		int score = scanner.nextInt();
		
		// 입력받은 점수가 유효한 범위인지 확인
		if(score < 0 || score >100) {
			System.out.println("잘못된 점수 입력입니다. 0에서 100 사이의 점수를 입력해 주세요");
			return;
		}
		
		// 학생 점수에 따라 학점을 저장할 변수 선언
		String grade; // 'A', 'B', 'C', 'D', 'F' 학점중 하나 저장
		
		switch(score/10) {
		
		case 10: // 100점
		case 9: // 90점
			grade = "A";
			break;
		
		case 8: // 80점
			grade = "B";
			break;
		
		case 7: // 70점
			grade = "C";
			break;
			
		case 6: // 60점
			grade = "D";
			break;
			
		default: // 60점 미만이라면
			grade = "F";
			break;
		}
		
		System.out.println("학생의 학점은 : " + grade);
		
		// 키보드로 부터 입력받은 통로 메모리 해제
		scanner.close();
	}

}
