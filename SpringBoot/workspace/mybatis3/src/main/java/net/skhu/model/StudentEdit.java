package net.skhu.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

//모델역할의 클래스  ?  입력 폼에 입력한 정보들이 제대로 입력되었는지 검사하기 위한 클래스
@Data
public class StudentEdit {
	int id;

	@NotEmpty @NotBlank
	@Size(min=8, max=12)
	String studentNo;

	@NotEmpty @NotBlank
	@Size(min=2, max=20)
	String name;

	@Min(value=1, message="학과를 선택하세요")
	int departmentId;

	@NotEmpty @NotBlank
	@Pattern(regexp = "010-[0-9]{3,4}-[0-9]{4}",
			 message = "휴대폰 번호를 입력하세요 예:010-000-0000")
	String phone;

	@NotEmpty
	@Pattern(regexp = "남|여")
	String sex;

	@NotEmpty @Email
	String email;
}




















