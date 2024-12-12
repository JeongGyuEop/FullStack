package net.skhu.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/*
학생 수정 폼(student/editm.html)에서 입력한 데이터를 채우기 위한 모델 클래스이다.
입력된 데이터를 검증하기 위한 spring form validation annotation을 멤버 변수에 붙였다.


annotation 설명
	@NotEmpty 값이 입력되었는지 검사한다
	@NotBlank 공백만 입력된 것은 아닌지 검사한다
	@Size(min=2, max=20) 입력된 문자열의 최소 크기 최대 크기를 검사한다
	@Pattern(regexp="남자|여자") 입력된 문자열이 정규식에 일치하는지 검사한다
	@Email 이메일 주소 문자열인지 검사한다
	@Min(1) 입력된 값이 최소 1 이상인지 검사한다
	@Max(10) 입력된 값이 최대 10 이하인지 검사한다

 */
@Data
public class StudentEdit { //<- DTO가 아니고 Model클래스 입니다.
	int id;

	@NotEmpty(message="학번을 입력하세요~")
	@NotBlank
	@Size(min=8, max=12)
	String studentNo;

	@NotEmpty @NotBlank
	@Size(min=2, max=20, message="이름의 길이가 {min} 이상 {max} 이하여야 합니다~")
	String name;

	@Min(value=1, message="학과를 선택하슈~") //@Min(1) 입력된 값이 최소 1 이상인지 검사한다
	int departmentId;

	@NotEmpty @NotBlank
	@Pattern(regexp = "남자|여자", message="성별을 선택하슈~")
	String gender;

	boolean absense;

	@Min(1) @Max(4)
	int year;
}












