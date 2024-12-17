package net.skhu.dto;

import lombok.Data;

//DTO역할의 클래스? DB에서 조회한 레코드 하나의 정보를 변수에 저장할 용도의 클래스
//DTO
@Data
public class Student {
	int id;
	String studentNo;
	String name;
	int departmentId;
	String phone;
	String sex;
	String email;
	String departmentName;
}
