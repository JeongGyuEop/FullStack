package net.skhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.skhu.dto.Student;

@Controller
@RequestMapping("student")
@Slf4j
public class StudentController {

	// 이 액션 메소드를 호출하기 위한 URL은
    //http://localhost:8088/student/edit 이다.
    //이 URL이 GET 요청될 때 이 액션 메소드가 호출된다.
	@GetMapping("edit")
	public String edit(Model model) {

		Student student = new Student();
				student.setName("홍길동");

		log.debug(student.toString());

		model.addAttribute("student", student);

		return "student/edit";
	}

	// <form>  post요청 http://localhost:8088/student/edit
	@PostMapping("edit")
	public String edit(Model model, Student student) {// 액션 메소드의 파라미터 Student student 객체
		try {
			log.debug(student.toString());

			//학번이 입력되지 않았으면 exception을 throw한다.
			if(StringUtils.hasText(student.getStudentNo()) == false ) {
				throw new Exception("학번을 입력하세요");
			}


			//이름이 입력되지 않았으면 exception을 throw한다
			if(StringUtils.hasText(student.getName()) == false) {
				throw new Exception("이름을 입력하세요");
			}

//학번과 이름이 모두 입력되었으면 DB에 저장하고, "student/list" URL로 리다이렉트 한다.
//student객체를 DB에 저장하는 구현 구문 생략
			System.out.println(student.getStudentNo() + " " + student.getName() );

			return "redirect:list";



		} catch (Exception e) {
		   // try 블럭 실행 도중 exception이 throw 되면 여기로 점프하게 된다.

			model.addAttribute("errorMsg", e.getMessage() );
		   //에러 메세지를 뷰에 전달하기 위해 Model에 저장

			return "student/edit"; //학생 수정 입력화면 VIEW를 다시 요청해서 보여주기


		}



	}//

    @GetMapping("list")
    public String list() {
        // 학생 목록을 DB에서 조회해서 뷰에 전달하는 코드 생략
        return "student/list";
    }







}















