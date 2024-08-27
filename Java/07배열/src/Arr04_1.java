public class Arr04_1 {
    public static void main(String[] args) {
        // 학생들의 과목별 점수를 초깃값으로 갖는 2차원 배열 생성
        int[][] score = {
            //국어,  영어,   수학
            {85, 60, 70},   // 0행  1번학생
            {90, 95, 80},   // 1행  2번학생
            {75, 80, 100},  // 2행  3번학생
            {80, 70, 95},   // 3행  4번학생
            {100, 65, 80}   // 4행  5번학생 
        };     

        // 각 과목별 총점을 저장할 1차원 배열 생성
        int[] subject = new int[3];

        // 각 학생별 총점을 저장할 1차원 배열 생성
        int[] student = new int[5];

        System.out.println("각 과목별 총점 구하기");

        // 학생 수와 과목 수
        int numStudents = score.length; // 5
        int numSubjects = score[0].length; // 3

        // 각 과목별 총점과 각 학생별 총점 계산
        for (int r = 0; r < numStudents; r++) { // 학생 수 만큼 반복
        	
            int studentTotal = 0; // 각 학생의 총점 초기화
            
            for (int c = 0; c < numSubjects; c++) { // 과목 수 만큼 반복
                studentTotal += score[r][c]; // 학생의 총점 계산
                subject[c] += score[r][c]; // 과목별 총점 계산
            }
            student[r] = studentTotal; // 학생의 총점을 student 배열에 저장
        }

        // 과목별 총점 출력
        for (int c = 0; c < numSubjects; c++) {
            System.out.println("과목 " + (c + 1) + " 총점: " + subject[c]);
        }

        // 각 학생별 총점 출력
        for (int r = 0; r < numStudents; r++) {
            System.out.println("학생 " + (r + 1) + " 총점: " + student[r]);
        }
    }
}
/*
각 과목별 총점 구하기
과목 1 총점: 430
과목 2 총점: 370
과목 3 총점: 425
학생 1 총점: 215
학생 2 총점: 265
학생 3 총점: 255
학생 4 총점: 245
학생 5 총점: 245
*/




