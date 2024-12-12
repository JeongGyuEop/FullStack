package net.skhu.dto;

import lombok.Data;

//DTO
@Data
public class Student {
	String studentNo;
	String name;
	int departmentId;
	String gender;
	boolean absense;
	int year;

}
