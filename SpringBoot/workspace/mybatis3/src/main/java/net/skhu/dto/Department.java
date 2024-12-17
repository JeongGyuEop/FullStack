package net.skhu.dto;

import lombok.Data;

//학과 정보 임시로 저장 해서 DB작업시 사용할 DTO
@Data
public class Department {
	int id;
	String name;
	String shortName;
	String phone;
}
