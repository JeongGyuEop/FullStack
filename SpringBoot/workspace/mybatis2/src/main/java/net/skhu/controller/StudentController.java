package net.skhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.skhu.dto.Student;
import net.skhu.mapper.StudentMapper;

@Controller
@RequestMapping("student")
public class StudentController {

	//StudentMapper인터페이스를 구현한 자식 구현객체 의존주입
	 @Autowired
	 StudentMapper studentMapper;
	 /*
	    spring mybatis 라이브러리가 자동으로 해주는 것 (spring mybatis 기능)
	     StudentMapper interface의 자식 클래스를 자동으로 구현해준다.
	     그 자식 클래스가 StudentMapper interface로 부터 상속 받은 메소드를 자동으로 구현해 준다.

	    spring framework이 자동으로 해주는 것 (dependency injection 기능)
	    StudentMapper 인터페이스를 구현한 클래스를 찾아서,
	     그 클래스의 객체를 자동으로 생성해서 studentMapper 멤버 변수에 자동으로 대입해 준다.
	    */


	//student테이블의 모든 학생레코드 조회 해서 보여주자
	//요청주소: http://localhost:8088/student/list1
	@GetMapping("list1")
	public String list(Model model) {

		//1. 매퍼인터페이스 내부에 만들어 놓은 추상메소드를 자동으로 구현한 자식객체의 메소드 호출하여
		//   student2데이터베이스의 student테이블에 저장된 모든 학생 레코드들을
		//   Student DTO객체들에 각각 담고 모든 Student객체들을 List배열에 담아 반환 받자
		List<Student> students = studentMapper.findAll();

		//2. 조회된 List배열 자체를 VIEW(student폴더내부에 만들어져 있는 list.html)에 보여주기 위해
		//   Model객체에 담습니다.
		model.addAttribute("students", students);


		//3. VIEW(student폴더내부에 만들어져 있는 list.html) 경로 리턴
		return "student/list";

	}

	//student테이블의 모든 학생레코드 조회 해서 보여주자
	//요청주소: http://localhost:8088/student/list2
	//요청주소: http://localhost:8088/student/list2?name='최'

	@GetMapping("list2")
	public String list2(Model model,
						@RequestParam(value="name", defaultValue = "") String name) {
														  //"최%"
		List<Student>  students = studentMapper.findByName(name + "%");
		model.addAttribute("students", students);
		model.addAttribute("name", name);

		return "student/list2";
	}


}







