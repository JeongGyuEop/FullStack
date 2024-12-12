package net.skhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.skhu.model.StudentEdit;

@Controller
@RequestMapping("student")
@Slf4j
public class StudentController {

	// 이 액션 메소드를 호출하기 위한 URL은
    //http://localhost:8088/student/edit 이다.
    //이 URL이 GET 요청될 때 이 액션 메소드가 호출된다.
	@GetMapping("edit")
	public String edit(Model model) {

		StudentEdit student = new StudentEdit();
					student.setName("홍길동");

		log.debug(student.toString());

		model.addAttribute("studentEdit", student);

		return "student/edit";
	}

    //학생 정보를 수정 하기 위해 수정할 내용을 입력하는 student/edit.html에서
    //수정할 정보를 입력하고 <button type="submit" class="btn">저장</button> 을 클릭하면
    //http://localhost:8088/student/edit 주소는 <form>에 의해 POST 요청으로
	//아래의 메소드가 호출된다.
	@PostMapping("edit")
	public String edit(Model model,
					   @Valid StudentEdit student,
					   BindingResult bindingResult) {// 액션 메소드의 파라미터 Student student 객체

    	/*
    	서버에 전달된 request parameter 데이터가 StudentEdit student 객체에 자동으로 채워진 후
  		validation annotation 규칙이 자동으로 검사된다.
  		검사 결과 오류가 있으면, 오류 메시지가 BindingResult bindingResult 객체에 자동으로 등록된다.
    	*/

		try {
			log.debug(student.toString());

			//입력된 데이터 검사결과 오류가 있으면 exception을 throw한다.
			if(bindingResult.hasErrors()) {
				throw new Exception("입력한 회원정보를 수정할수 없다.");
			}

			// UPDATE 작업 생략

			return "redirect:list";



		} catch (Exception e) {

            // exception이 발생했으면, exception message를 bindingResult 객체에 등록한다.
            // 이 bindingResult 객체에 입력 데이터 자동 검사 결과 오류 메시지들도 들어있다.
            // 이 bindingResult 객체도 자동으로 뷰에 전달된다.
            bindingResult.reject("", null, e.getMessage());
            /*
               bindResult 객체에 e.getMessage() 에러 메시지를 등록한다.
			     첫째 파라미터 "" 부분은 에러 메시지가 등록될 StudentEdit 객체의 속성명이다.
			      즉 에러가 발생한 속성 이름이다.
			     이 값이 빈 문자열이면, 에러 메시지는 속성이 아니고 StudentEdit 객체 자체에 등록된다.
			     StudentEdit 객체 자체는 student/edit.html에 전달되어
			     <div class="error" th:errors="*{studentNo}"></div> 등등에 의해 에러메세지가 출력됨
             */


			return "student/edit"; //학생 수정 입력화면 VIEW를 다시 요청해서 보여주기


		}



	}//

    @GetMapping("list")
    public String list() {
        // 학생 목록을 DB에서 조회해서 뷰에 전달하는 코드 생략
        return "student/list";
    }







}















