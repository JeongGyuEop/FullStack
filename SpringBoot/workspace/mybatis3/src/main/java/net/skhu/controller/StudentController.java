package net.skhu.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.skhu.dto.Student;
import net.skhu.mapper.DepartmentMapper;
import net.skhu.mapper.StudentMapper;
import net.skhu.model.StudentEdit;



@Controller
@RequestMapping("student")
@Slf4j
public class StudentController {
	@Autowired
	StudentMapper studentMapper;
	@Autowired
	DepartmentMapper departmentMapper;

	//`ModelMapper`는 이 두 객체(Student,StudentEdit) 간의
	// **데이터를 자동으로 복사**해주는 도구입니다.
	//예를 들어, `,StudentEdit` 객체에 있는 변수값을
	//         `Student` 객체에 그래도 옮겨서 자동으로 저장할때,
	//         `ModelMapper`가 이를 자동으로 해줍니다.
	ModelMapper modelMapper =  new ModelMapper();

/*
	 학생 수정 화면에서 삭제 버튼을 클릭하면, 서버에서 아래 액션메소드가 실행된다.
     studentMapper.deleteById(studentEdit.getId()); 메소드를 호출하여 그 학생 레코드를 삭제한다.
     만약 삭제 과정에서 에러가 발생하면 catch 블럭이 실행되고,
     학생 수정 화면이 다시 출력된다.
*/
	@PostMapping(value="edit", params="cmd=delete")
	public String delete(Model model,
						 @Valid StudentEdit studentEdit,
						 BindingResult bindResult  ) {
		try {
			studentMapper.deleteById(studentEdit.getId());
			return "redirect:list";

		}catch (Exception e) {
			bindResult.reject("", null, e.getMessage());
			return "student/edit";
		}


	}


	@PostMapping("create")
	public String create(Model model,
						 @Valid StudentEdit studentEdit,
						 BindingResult bindingResult   ) {
		try {
			if(bindingResult.hasErrors()) {
				throw new Exception("학생 추가 할수 없습니다.");
			}

			//매개변수로 받은 StudentEdit객체의 인스턴스변수값-> Student객체의 인스턴스변수에 저장후 반환
			Student student = modelMapper.map(studentEdit, Student.class);
			//insert작업
			studentMapper.insert(student);
			//insert에 성공하면 모든 학생목록 다시 조회 요청
			return "redirect:list";  //localhost:8088/student/list

		} catch (Exception e) {
			//유효성 검사에 실패하면
			bindingResult.reject("", null, e.getMessage());

			//하나라도 입력하지않고 저장(등록)버튼을 눌렀을때 다시 edit.html에 select option에
			//조회한 학과정보를 보여주기 위해 DB로 부터 학과정보를 조회해서 보여주기 위함
			model.addAttribute("departments", departmentMapper.findAll() );


			return "student/edit";

		}
	}



	/*
	GET  http://localhost:8088/student/create
	<a href="create" class="btn">학생 등록</a>
	*/
	@GetMapping("create")
	public String create(Model model) {

		StudentEdit studentEdit = new StudentEdit();
		model.addAttribute("studentEdit", studentEdit);
		model.addAttribute("departments",  departmentMapper.findAll()  );
		return "student/edit";//templates/student/edit.html
	}





	//student테이블의 모든 학생레코드 조회 해서 보여주자
	//요청주소: http://localhost:8088/student/list
	//요청주소: http://localhost:8088/student/list?name='최'

	@GetMapping("list")
	public String list(Model model,
						@RequestParam(value="name", defaultValue = "") String name) {
						//이름이 name인 request parameter값이 name변수에 전달된다.
						//이값은 조회 조건 입력 텍스트 박스에 입력한 문자열이다.
														  //"최%"
		List<Student>  students = studentMapper.findByName(name + "%");
						//이름이 조회 조건과 일치하는 학생목록을 조회한다.

		//model attribute 데이터를 뷰에 전달한다.
		model.addAttribute("students", students);//조회 결과 학생 목록을 뷰에 전달
		model.addAttribute("name", name);// 조회 조건 문자열을 뷰에 전달

		return "student/list";
	}



	// 이 액션 메소드를 호출하기 위한 URL은
    //http://localhost:8088/student/edit?id=1 이다.
    //이 URL이 GET 요청될 때 이 액션 메소드가 호출된다.
	@GetMapping("edit")
	public String edit(Model model, @RequestParam("id")  int id) {

		Student student = studentMapper.findById(id);

		StudentEdit studentEdit = modelMapper.map(student, StudentEdit.class);

     	model.addAttribute("studentEdit", studentEdit);
		model.addAttribute("departments",  departmentMapper.findAll());


		return "student/edit";
	}

    //학생 정보를 수정 하기 위해 수정할 내용을 입력하는 student/edit.html에서
    //수정할 정보를 입력하고 <button type="submit" class="btn" name="cmd" value="save">저장</button> 을 클릭하면
    //http://localhost:8088/student/edit?cmd=save 주소는 <form>에 의해 POST 요청으로
	//아래의 메소드가 호출된다.
	@PostMapping( value="edit", params = "cmd=save")
	public String edit(Model model,
					   @Valid StudentEdit studentEdit,
					   BindingResult bindingResult) {// 액션 메소드의 파라미터 Student student 객체

    	/*
    	서버에 전달된 request parameter 데이터가 StudentEdit student 객체에 자동으로 채워진 후
  		validation annotation 규칙이 자동으로 검사된다.
  		검사 결과 오류가 있으면, 오류 메시지가 BindingResult bindingResult 객체에 자동으로 등록된다.
    	*/

		try {
			log.debug(studentEdit.toString());

			//입력된 데이터 검사결과 오류가 있으면 exception을 throw한다.
			if(bindingResult.hasErrors()) {
				throw new Exception("입력한 회원정보를 수정할수 없다.");
			}
			// UPDATE
			 Student student = modelMapper.map(studentEdit, Student.class);
             studentMapper.update(student);

			return "redirect:list";//



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




}















