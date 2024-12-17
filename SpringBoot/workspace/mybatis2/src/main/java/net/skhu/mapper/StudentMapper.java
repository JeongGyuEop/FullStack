package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import net.skhu.dto.Student;


/*
StudentMapper
   student 테이블에 대한 SQL 명령들을 구현한 mybatis mapper 이다.
   클래스가 아니고 interface 임에 주의하자.  (java interface 문법을 공부하라)

@Mapper 어노테이션
   mybatis mapper에는 반드시 이 어노테이션을 붙여야 한다.

spring mybatis 라이브러리가 자동으로 해주는 것
   StudentMapper interface의 자식 클래스를 자동으로 구현해준다.
   그 자식 클래스가 StudentMapper interface로 부터 상속 받은 메소드를 (findAll 메소드)
   자동으로 구현해 준다.
*/

@Mapper
public interface StudentMapper {

	/*
			//실습1.
			@Select("SELECT * FROM student")
			List<Student> findAll();

			@Select("SELECT * FROM student")
			List<Student> findAll();
			   spring mybatis 라이브러리가 이 메소드를 자동으로 구현해준다.
			   자동으로 구현된 이 메소드는
		       SELECT * FROM student SQL 명령을 실행하고
		       이 SQL 명령의 조회 결과 데이터를
		       List<Student> 타입의 객체에 채워서 리턴한다.
		       즉 위 SQL 명령의 조회 결과 레코드 각각을 Student DTO 객체에 채우고
		       그렇게 데이터가 채워진 Student 객체들을 List<Student> 타입의 목록 객체에 채워서 리턴한다.

			위 SQL 명령의 조회 결과 데이터가 Student DTO 객체에 채워질 때,
			조회 결과 컬럼명과 Student DTO 객체의 속성명이 일치해야 한다. (setter 메소드 이름이 일치해야 한다)
			일치하지 않은 속성은 값이 채워지지 않고 무시된다.


			조회 결과 컬럼명      자바  Student DTO객체의 set 메소드 이름
			id 				  setId
			studentNo         setStudentNo
			name			  setName
			departmentId      setDepartmentId
			phone			  setPhone
			sex				  setSex
			email			  setEmail

			Student DTO 클래스의 setter 메소드들은 lombok에 의해 자동 구현된다.

	 */
	/*
	학생 목록을 출력할 때, 소속 학과명도 출력해야 한다.
	그런데 student 테이블에 학과명 필드가 없으니,
	다음과 같이 department 테이블과 조인하여 학과명 필드도 조회해야 한다.

	student 테이블과 department 테이블을 연결(join)해서 조회한다.
	이때 student.departmentId 필드 값과 department.id 필드 값을 비교해서 연결한다.
	즉 각 학생의 소속 학과 id 값을 비교해서
	student 테이블의 레코드와 department 테이블의 레코드를 연결해서 조회한다.
	student 테이블의 모든 필드와 department 테이블의 name 필드값을 조회한다.

	참고.  SELECT s.*, d.name departmentName  줄에서
	      departmentName 부분에 대한 설명

			   조회 결과 컬럼명과 Student DTO 클래스의 속성명(setter 메소드 이름)이 일치해야 한다.
			   setter 메소드는 lombok에 의해서 자동으로 구현된다.
	*/

	//실습2.
	   		@Select("""
			        SELECT s.*, d.name departmentName
			        FROM student s JOIN department d ON s.departmentId = d.id """)
		    List<Student> findAll();

		   //참고.Java 여러줄 문자열 문법
		   //""" 여러줄 문자열 """
		   //SQL 명령이 긴 편이기 때문에 여러줄 문자열 문법을 사용하여 구현하였다.
		   //이 문법은 Java 15 이상 버전에서 지원된다.

	   		@Select("""
	   				 SELECT s.*, d.name departmentName
	   				 FROM student  s JOIN department d ON s.departmentId = d.id
	   				 WHERE s.name LIKE #{name} """)
	   		List<Student> findByName(String name);
	   										//"최%"



}

















