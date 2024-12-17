package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.skhu.dto.Student;

@Mapper
public interface StudentMapper {


	@Delete("DELETE FROM student WHERE id=#{id}")
	void deleteById(int id);



	@Select("""
	        SELECT s.*, d.name departmentName
	        FROM student s JOIN department d ON s.departmentId = d.id
	        WHERE s.name LIKE #{name}
	        ORDER BY id """)
	    /*
	    @Select: MyBatis라는 라이브러리에서 사용되는 어노테이션입니다.
	    - 이 어노테이션은 데이터베이스에서 데이터를 가져올 때 사용됩니다.
	    - SQL 쿼리:
	      - SELECT s.*, d.name departmentName:
	        - student 테이블의 모든 열(s.*)과 department 테이블의 name 열을 departmentName이라는 별칭으로 선택합니다.
	      	- FROM student s JOIN department d ON s.departmentId = d.id:
	        - student 테이블(s)과 department 테이블(d)을 결합(JOIN)합니다.
	        - 결합 조건은 student 테이블의 departmentId와 department 테이블의 id가 같을 때입니다.
	      - WHERE s.name LIKE #{name}:
	        - student 테이블의 name 열이 주어진 이름 패턴과 일치하는 행을 선택합니다.
	        - #{name}: 메서드의 파라미터로 전달된 name 값을 참조합니다.
	      - ORDER BY id:
	        - 결과를 id 열을 기준으로 정렬합니다.
	    */
	List<Student> findByName(String name);
	    /*
	    List<Student> findByName(String name): 메서드 선언부입니다.
	    - findByName: 메서드 이름입니다. 주어진 이름 패턴과 일치하는 학생 정보를 찾는 기능을 합니다.
	    - String name: 학생의 이름을 검색하기 위한 문자열입니다.
	    - List<Student>: 이 메서드는 Student 객체의 목록(리스트)을 반환합니다. 즉, 주어진 이름 패턴과 일치하는 모든 학생 정보를 반환합니다.
	    */






    @Select("SELECT * FROM student WHERE id=#{id}")
    /*
    @Select: MyBatis라는 라이브러리에서 사용되는 어노테이션입니다.
    - 이 어노테이션은 데이터베이스에서 데이터를 가져올 때 사용됩니다.
    - "SELECT * FROM student WHERE id=#{id}":
      - SQL 쿼리입니다.
      - student 테이블에서 id가 특정 값과 일치하는 학생 정보를 가져오겠다는 의미입니다.
      - #{id}: 메서드의 파라미터로 전달된 id 값을 참조합니다.
    */
    Student findById(int id);
    /*
    Student findById(int id): 메서드 선언부입니다.
    - findById: 메서드 이름입니다. id 값을 이용해 학생 정보를 찾는 기능을 합니다.
    - int id: 학생의 고유 번호를 의미하는 정수형 변수입니다.
    - Student: 이 메서드는 Student 객체를 반환합니다. 즉, 특정 id 값을 가진 학생의 정보를 반환합니다.
    */





    @Update("""
    	       UPDATE student
    	       SET
    	         studentNo = #{studentNo},
    	         name = #{name},
    	         departmentId = #{departmentId},
    	         sex = #{sex},
    	         phone = #{phone},
    	         email = #{email}
    	       WHERE id = #{id} """)
    	    /*
    	    @Update: MyBatis라는 라이브러리에서 사용되는 어노테이션입니다.
    	    - 이 어노테이션은 데이터베이스의 기존 정보를 업데이트(수정)할 때 사용됩니다.
    	    - UPDATE student: student 테이블의 데이터를 수정한다는 의미입니다.
    	    - SET: 변경할 컬럼(열)과 새로운 값을 지정하는 부분입니다.
    	      - studentNo = #{studentNo}: studentNo 컬럼의 값을 새롭게 설정할 studentNo 값으로 변경합니다.
    	      - name = #{name}: name 컬럼의 값을 새롭게 설정할 name 값으로 변경합니다.
    	      - departmentId = #{departmentId}: departmentId 컬럼의 값을 새롭게 설정할 departmentId 값으로 변경합니다.
    	      - sex = #{sex}: sex 컬럼의 값을 새롭게 설정할 sex 값으로 변경합니다.
    	      - phone = #{phone}: phone 컬럼의 값을 새롭게 설정할 phone 값으로 변경합니다.
    	      - email = #{email}: email 컬럼의 값을 새롭게 설정할 email 값으로 변경합니다.
    	    - WHERE id = #{id}: id가 특정 값과 일치하는 행을 수정한다는 의미입니다.
    	      - #{id}는 메서드에서 제공된 student 객체의 id 값을 참조합니다.
    	    */
    	void update(Student student);
    	    /*
    	    void update(Student student): 메서드 선언부입니다.
    	    - update: 메서드 이름입니다. 학생 정보를 데이터베이스에서 수정하는 기능을 합니다.
    	    - Student student: 학생 정보를 담고 있는 객체입니다.
    	      - 이 객체에는 studentNo, name, departmentId, phone, sex, email 등의 정보가 포함됩니다.
    	    */



    @Insert("""
    	       INSERT student (studentNo, name, departmentId, phone, sex, email)
    	       VALUES (#{studentNo}, #{name}, #{departmentId}, #{phone}, #{sex}, #{email}) """)
    	    /*
    	    @Insert: MyBatis라는 라이브러리에서 사용되는 어노테이션입니다.
    	    - 이 어노테이션은 데이터베이스에 새로운 정보를 추가할 때 사용됩니다.
    	    - INSERT student (...): student 테이블에 데이터를 추가하겠다는 의미입니다.
    	    - studentNo, name 등은 테이블의 열(컬럼) 이름입니다.
    	    - VALUES (#{...}): 각각의 열에 들어갈 값을 지정합니다.
    	      - #{studentNo} 등은 메서드의 파라미터 값을 참조합니다.
    	      - 예를 들어, #{studentNo}는 메서드에서 제공된 student 객체의 studentNo 값을 의미합니다.
    	    */

    	@Options(useGeneratedKeys=true, keyProperty="id")
    	    /*
    	    @Options: 추가 옵션을 설정하는 어노테이션입니다.
    	    - useGeneratedKeys=true: 데이터베이스에서 자동 생성된 키 값을 사용하겠다는 의미입니다.
    	      - 예를 들어, 데이터베이스가 새로운 행을 추가할 때 자동으로 생성하는 ID 값을 가져옵니다.
    	    - keyProperty="id": 자동 생성된 ID 값을 Student 객체의 id 필드에 저장합니다.
    	    */

    	void insert(Student student);
    	    /*
    	    void insert(Student student): 메서드 선언부입니다.
    	    - insert: 메서드 이름입니다. 학생 정보를 데이터베이스에 추가하는 기능을 합니다.
    	    - Student student: 학생 정보를 담고 있는 객체입니다.
    	      - 이 객체에는 studentNo, name, departmentId, phone, sex, email 등의 정보가 포함됩니다.
    	    */




}






